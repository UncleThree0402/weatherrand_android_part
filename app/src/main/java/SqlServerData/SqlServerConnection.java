package SqlServerData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlServerConnection {
    private static Connection connection = null;
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static Statement statement = null;

    public static void connect(){
        try{
            Class.forName(Classes);
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://140.116.234.166:721/Android","Administrator","PDP@123");
            statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try{
            statement.close();
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }
}
