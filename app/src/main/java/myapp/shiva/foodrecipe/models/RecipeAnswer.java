package myapp.shiva.foodrecipe.models;

import java.io.Serializable;

public class RecipeAnswer implements Serializable {
    private  String recipeName;
    private String recipeQuestionID;
    private  String recipeIngredients;
    private String cookingTechniques;
    private String answerBy;


    public RecipeAnswer() {
    }

    public RecipeAnswer(String recipeName, String recipeQuestionID, String recipeIngredients, String cookingTechniques, String answerBy) {
        this.recipeName = recipeName;
        this.recipeQuestionID=recipeQuestionID;
        this.recipeIngredients = recipeIngredients;
        this.cookingTechniques = cookingTechniques;
        this.answerBy=answerBy;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeQuestionID() {
        return recipeQuestionID;
    }

    public void setRecipeQuestionID(String recipeQuestionID) {
        this.recipeQuestionID = recipeQuestionID;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getCookingTechniques() {
        return cookingTechniques;
    }

    public void setCookingTechniques(String cookingTechniques) {
        this.cookingTechniques = cookingTechniques;
    }

    public String getAnswerBy() {
        return answerBy;
    }

    public void setAnswerBy(String answerBy) {
        this.answerBy = answerBy;
    }
}
