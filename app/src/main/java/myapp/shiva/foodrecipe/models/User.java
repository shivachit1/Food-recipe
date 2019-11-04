package myapp.shiva.foodrecipe.models;

public class User {
    private String userName;
    private String UserEmail;

    public User() {
    }

    public User(String userName, String userEmail) {
        this.userName = userName;
        UserEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }
}
