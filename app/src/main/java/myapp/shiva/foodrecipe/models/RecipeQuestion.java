package myapp.shiva.foodrecipe.models;

import java.io.Serializable;

public class RecipeQuestion implements Serializable {
    private String questionID;
    private String askBy;
    private String recipeName;
    private String foodImageuri;

    public RecipeQuestion() {
    }

    public RecipeQuestion(String questionID, String askBy, String recipeName, String foodImageuri) {
        this.questionID = questionID;
        this.askBy=askBy;
        this.recipeName = recipeName;
        this.foodImageuri = foodImageuri;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getAskBy() {
        return askBy;
    }

    public void setAskBy(String askBy) {
        this.askBy = askBy;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getFoodImageuri() {
        return foodImageuri;
    }

    public void setFoodImageuri(String foodImageuri) {
        this.foodImageuri = foodImageuri;
    }
}
