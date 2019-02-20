package myapp.shiva.foodrecipe.Objects;

public class Recipe {
    private  String recipeName;
    private  String recipeIngredients;
    private String nutrition;


    public Recipe() {
    }

    public Recipe(String recipeName, String recipeIngredients, String nutrition) {
        this.recipeName = recipeName;
        this.recipeIngredients = recipeIngredients;
        this.nutrition = nutrition;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getNutrition() {
        return nutrition;
    }

    public void setNutrition(String nutrition) {
        this.nutrition = nutrition;
    }
}
