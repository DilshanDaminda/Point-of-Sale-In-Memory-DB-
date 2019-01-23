package lk.ijse.dep.fx.Manage;

public class System_USER_DB {

    private String System_Admin_User_Name;
    private String System_Admin_Password;

    public String getSystem_Admin_User_Name() {
        return System_Admin_User_Name;
    }

    public System_USER_DB(String system_Admin_User_Name, String system_Admin_Password) {
        System_Admin_User_Name = system_Admin_User_Name;
        System_Admin_Password = system_Admin_Password;
    }

    public void setSystem_Admin_User_Name(String system_Admin_User_Name) {
        System_Admin_User_Name = system_Admin_User_Name;
    }

    public String getSystem_Admin_Password() {
        return System_Admin_Password;
    }

    public void setSystem_Admin_Password(String system_Admin_Password) {
        System_Admin_Password = system_Admin_Password;
    }
}
