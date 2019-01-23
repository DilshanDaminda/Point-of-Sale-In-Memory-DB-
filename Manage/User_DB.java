package lk.ijse.dep.fx.Manage;

public class User_DB {

    private String User_Username;
    private String User_Userpassword;


    public String getUser_Username() {
        return User_Username;
    }

    public User_DB(String user_Username, String user_Userpassword) {
        User_Username = user_Username;
        User_Userpassword = user_Userpassword;
    }

    public void setUser_Username(String user_Username) {
        User_Username = user_Username;
    }

    public String getUser_Userpassword() {
        return User_Userpassword;
    }

    public void setUser_Userpassword(String user_Userpassword) {
        User_Userpassword = user_Userpassword;
    }
}
