package lk.ijse.dep.fx.DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBCON {
  private static   Connection con;
public static Connection  getcon() throws SQLException {

    if (con==null){
    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/placeorderdb", "root", "1234");
    }
    return con;

}

}
