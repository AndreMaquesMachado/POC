
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class connection {

    public static void main(String[] args) throws SQLException {
        Connection connec = null;
        String SQLPath = "jdbc:mysql://localhost/LocalDatabase?characterEncoding=latin1&useConfigs=maxPerformance";
        //?autoReconnect=true&useSSL=false&serverTimezone=Europe/Lisbon
        String SQLuser = "root";
        String SQLpass = "password";


        try {

            //Class.forName("com.mysql.cj.jdbc.Driver");
//              connec = DriverManager.getConnection(SQLPath,SQLuser,SQLpass);
            //Class.forName("com.mysql.jdbc.Driver");// include this line in your code.
            connec = DriverManager.getConnection(SQLPath, SQLuser, SQLpass);
            connec.createStatement();
            System.out.println(connec);
            Class.forName("com.mysql.jdbc.Driver");

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            /*This block should be added to your code
             * You need to release the resources like connections
             */
            if (connec != null)
                connec.close();
        }
    }
}

