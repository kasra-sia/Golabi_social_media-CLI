package models.like;

public class Like {
    private String username;
    public Like(String username){
        this.username = username;
    }
    public Like(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
