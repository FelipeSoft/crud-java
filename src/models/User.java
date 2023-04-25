package models;
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    public void setUserId(int id){
        this.id = id;
    }
    public void setUserName(String name){
        this.name = name;
    }
    public void setUserEmail(String email){
        this.email = email;
    }
    public void setUserPassword(String password){
        this.password = password;
    }

    public int getUserId(){
        return this.id;
    }

    public String getUserName(){
        return this.name;
    }

    public String getUserEmail(){
        return this.email;
    }
    public String getUserPassword(){
        return this.password;
    }
}
