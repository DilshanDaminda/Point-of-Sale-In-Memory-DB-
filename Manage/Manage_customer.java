package lk.ijse.dep.fx.Manage;

public class Manage_customer {
    private String cusid;
    private String cusname;
    private String cusaddress;

    public Manage_customer(String cusid, String cusname, String cusaddress) {
        this.cusid = cusid;
        this.cusname = cusname;
        this.cusaddress = cusaddress;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public String getCusaddress() {
        return cusaddress;
    }

    public void setCusaddress(String cusaddress) {
        this.cusaddress = cusaddress;
    }
}
