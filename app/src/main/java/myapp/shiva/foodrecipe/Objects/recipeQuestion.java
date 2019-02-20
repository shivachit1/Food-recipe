package myapp.shiva.foodrecipe.Objects;

public class recipeQuestion {
    private String questionID;
    private String recipeName;
    private String foodImageuri;

    public recipeQuestion() {
    }

    public recipeQuestion(String questionID, String recipeName, String foodImageuri) {
        this.questionID = questionID;
        this.recipeName = recipeName;
        this.foodImageuri = foodImageuri;
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
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
