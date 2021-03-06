package pl.coderslab.dao;


import pl.coderslab.model.Admin;
import pl.coderslab.model.DayMealRecipe;
import pl.coderslab.model.Plan;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {

    private static final String CREATE_PLAN_QUERY =
            "INSERT INTO plan(name,description,created, admin_id) VALUES (?,?,current_timestamp(),?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String FIND_ALL_USER_PLANS = "SELECT * FROM plan where admin_id = ?;";
    private static final String READ_PLAN_QUERY = "SELECT * FROM plan WHERE id = ?;";
    private static final String READ_LAST_PLAN_QUERY = "SELECT * FROM plan WHERE admin_id = ? order by created desc limit 1;";
    private static final String UPDATE_PLAN_QUERY =
            "UPDATE	plan SET name=?, description=?, created=?, admin_id=? WHERE id = ?";
    private static final String COUNT_USERS_PLAN =
            "SELECT count(admin_id) FROM plan WHERE admin_id=?";
    private static final String SHOW_PLAN_DETAILS =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description, " +
                    "recipe.id as recipe_id FROM `recipe_plan`\n" +
                    "JOIN day_name on day_name.id=day_name_id\n" +
                    "JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
                    "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String FIND_ALL_RECIPES_FROM_PLAN =
            "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.id as recipe_id FROM `recipe_plan`\n" +
                    "JOIN day_name on day_name.id=day_name_id JOIN recipe on recipe.id=recipe_id WHERE plan_id = ?\n" +
                    "ORDER by day_name.display_order, recipe_plan.display_order;";

    public Plan create(Plan plan) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement =
                     conn.prepareStatement(CREATE_PLAN_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setInt(3, plan.getAdmin().getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }


    public void delete(int planId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_PLAN_QUERY);
            statement.setInt(1, planId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                int adminId = resultSet.getInt("admin_id");
                AdminDao adminDao = new AdminDao();
                planToAdd.setAdmin(adminDao.read(adminId));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;
    }

    public List <Plan> findAllUserPlans(int admin_id) {

        List<Plan> planList = new ArrayList<>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_PLANS)) {
            statement.setInt(1, admin_id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Plan planToAdd = new Plan();
                    planToAdd.setId(resultSet.getInt("id"));
                    planToAdd.setName(resultSet.getString("name"));
                    planToAdd.setDescription(resultSet.getString("description"));
                    planToAdd.setCreated(resultSet.getString("created"));
                    planList.add(planToAdd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planList;
    }

    public Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    int adminId = resultSet.getInt("admin_id");
                    AdminDao adminDao = new AdminDao();
                    plan.setAdmin(adminDao.read(adminId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public void update(Plan plan) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_PLAN_QUERY);
            if (plan.getAdmin().getId() > 0) {
                statement.setInt(1, plan.getAdmin().getId());
            } else {
                throw new IllegalArgumentException("admin_id nie może być mniejsze niż 0");
            }
            statement.setString(2, plan.getName());
            statement.setString(3, plan.getDescription());
            statement.setString(4, plan.getCreated());
            statement.setInt(5, plan.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Plan lastPlan(int admin_id) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_LAST_PLAN_QUERY)
        ) {
            statement.setInt(1, admin_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    int adminId = resultSet.getInt("admin_id");
                    AdminDao adminDao = new AdminDao();
                    plan.setAdmin(adminDao.read(adminId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;
    }

    public List<DayMealRecipe> printList(List<DayMealRecipe> main, int admin_id, String day_name) {

        PlanDao pd = new PlanDao();
        List<DayMealRecipe> result = new ArrayList<>();
        main = pd.printDashboardInfo(admin_id);

        for (DayMealRecipe dmr : main) {
            if (dmr.getDay_name().equalsIgnoreCase(day_name)) {
                result.add(dmr);
            }
        }
        return result;
    }

    public List<DayMealRecipe> printListFromPlan (List<DayMealRecipe> main, int plan_id, String day_name) {

        PlanDao pd = new PlanDao();
        List<DayMealRecipe> result = new ArrayList<>();
        main = pd.allPlansRecipies(plan_id);

        for (DayMealRecipe dmr : main) {
            if (dmr.getDay_name().equalsIgnoreCase(day_name)) {
                result.add(dmr);
            }
        }
        return result;
    }


    public List<DayMealRecipe> printDashboardInfo(int admin_id) {
        Plan plan = new Plan();
        PlanDao pd = new PlanDao();
        plan = pd.lastPlan(admin_id);
        int planId = plan.getId();
        List<DayMealRecipe> list = new ArrayList<DayMealRecipe>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(SHOW_PLAN_DETAILS)) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String day_name = resultSet.getString("day_name");
                    String meal_name = resultSet.getString("meal_name");
                    String recipe_name = resultSet.getString("recipe_name");
                    int recipe_id = resultSet.getInt("recipe_id");
                    DayMealRecipe dmr = new DayMealRecipe(day_name, meal_name, recipe_name, recipe_id);
                    list.add(dmr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DayMealRecipe> allPlansRecipies (int plan_id) {
        List<DayMealRecipe> list = new ArrayList<DayMealRecipe>();

        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_FROM_PLAN)) {
            statement.setInt(1, plan_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String day_name = resultSet.getString("day_name");
                    String meal_name = resultSet.getString("meal_name");
                    String recipe_name = resultSet.getString("recipe_name");
                    int recipe_id = resultSet.getInt("recipe_id");
                    DayMealRecipe dmr = new DayMealRecipe(day_name, meal_name, recipe_name, recipe_id);
                    list.add(dmr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countUsersPlans(int admin_id) {
        int counter = -1;
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(COUNT_USERS_PLAN)) {
            statement.setInt(1, admin_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    counter = resultSet.getInt("count(admin_id)");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

}