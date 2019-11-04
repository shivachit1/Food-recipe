package myapp.shiva.foodrecipe.models;

public class VoteCounter {
    private String voteId;
    private String recipeQuestionID;
    private String answeredBy;
    private String voterBy;

    public VoteCounter() {
    }

    public VoteCounter(String voteId, String recipeQuestionID, String answeredBy, String voterBy) {
        this.voteId = voteId;
        this.recipeQuestionID = recipeQuestionID;
        this.answeredBy = answeredBy;
        this.voterBy = voterBy;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getRecipeQuestionID() {
        return recipeQuestionID;
    }

    public void setRecipeQuestionID(String recipeQuestionID) {
        this.recipeQuestionID = recipeQuestionID;
    }

    public String getAnsweredBy() {
        return answeredBy;
    }

    public void setAnsweredBy(String answeredBy) {
        this.answeredBy = answeredBy;
    }

    public String getVoterBy() {
        return voterBy;
    }

    public void setVoterBy(String voterBy) {
        this.voterBy = voterBy;
    }
}
