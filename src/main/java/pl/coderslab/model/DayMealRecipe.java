package pl.coderslab.model;

public class DayMealRecipe {

    String day_name;
    String meal_name;
    String recipe_name;

    public DayMealRecipe(String day_name, String meal_name, String recipe_name) {
        this.day_name = day_name;
        this.meal_name = meal_name;
        this.recipe_name = recipe_name;
    }

    public String getDay_name() {
        return day_name;
    }

    public void setDay_name(String day_name) {
        this.day_name = day_name;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }
}
