import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java30";
        String user = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement mystmt = conn.createStatement();
            mystmt.executeUpdate
            ("insert into users " + " (fullname,username,email,password) " + " values('Fred Gates','fred','fred@gmail.com','1234') ");
            System.out.println("All good");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

// Connection con = null;
//try {
//    //registering the jdbc driver here, your string to use
//    //here depends on what driver you are using.
//    Class.forName("something.jdbc.driver.YourFubarDriver");
//    con = DriverManager.getConnection("jdbc:apache:commons:dbcp:test");
//} catch (SQLException e) {
//    throw new RuntimeException(e);
//}
