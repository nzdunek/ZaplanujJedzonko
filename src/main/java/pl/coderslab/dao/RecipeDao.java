package pl.coderslab.dao;


import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {
    private static final String CREATE_RECIPE_QUERY =
            "INSERT INTO recipe (name, ingredients, description, created, preparation_time, preparation, admin_id) VALUES (?,?,?, current_timestamp(),?,?,?)";
    private static final String DELETE_RECIPE_QUERY = "DELETE * FROM recipe WHERE id = ?";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe";
    public static final String READ_RECIPE_QUERY = "SELECT * FROM recipe WHERE id=?";
    public static final String UPDATE_RECIPE_QUERY =
            "UPDATE recipe SET updated = current_timestamp(), name = ?, ingredients = ?, description = ?, created = ?, preparation_time = ?, preparation = ?, admin_id=? WHERE id = ?";

    public Recipe create(Recipe recipe) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_RECIPE_QUERY, Statement.RETURN_GENERATED_KEYS);
            if (recipe.getAdmin().getId() > 0) {
                statement.setInt(1, recipe.getAdmin().getId());
            } else {
                throw new IllegalArgumentException("admin_id nie może być mniejsze niż 0");
            }
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getIngredients());
            statement.setString(4, recipe.getDescription());
            statement.setInt(5, recipe.getPreparation_time());
            statement.setString(6, recipe.getPreparation());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(int recipeId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_RECIPE_QUERY);
            statement.setInt(1, recipeId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                int adminId = resultSet.getInt("admin_id");
                AdminDao adminDao = new AdminDao();
                recipeToAdd.setAdmin(adminDao.read(adminId));
                recipeList.add(recipeToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipeList;
    }

    public Recipe read(Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_RECIPE_QUERY)) {
            statement.setInt(1, recipeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    int adminId = resultSet.getInt("admin_id");
                    AdminDao adminDao = new AdminDao();
                    recipe.setAdmin(adminDao.read(adminId));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    public void update(Recipe recipe) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_RECIPE_QUERY);
            if (recipe.getAdmin().getId() > 0) {
                statement.setInt(1, recipe.getAdmin().getId());
            } else {
                throw new IllegalArgumentException("admin_id nie może być mniejsze niż 0");
            }
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getIngredients());
            statement.setString(4, recipe.getDescription());
            statement.setInt(6, recipe.getPreparation_time());
            statement.setString(7, recipe.getPreparation());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

