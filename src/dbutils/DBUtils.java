package dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost/crousDB";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("Running dbutils.DBUtils.main() function");
        try (Connection con = getConnection()) {
            if (con == null) {
                System.out.println("Error: No connection established with DBMS!");
            } else {
                System.out.println("Connection to # "+ con.toString()+" # Established!");
            }
        }catch(SQLException e) {
            e.printStackTrace();
            printSQLException(e);
        }
    }


    /**
     * This helper method is used to established the connection
     * with the Data Base Management System (DBMS).
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        }catch(SQLException e) {
            System.err.println("An SQLException has raised!");
            e.printStackTrace();
            printSQLException(e);
        }catch(ClassNotFoundException e) {
            System.err.println("The driver class wasn't found!");
            e.printStackTrace();
        }

        return connection;
    }

    static public void printSQLException (SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState : "+ ((SQLException) e).getSQLState());
                System.err.println("Error Code : " + ((SQLException) e).getErrorCode());
                System.err.println("Message : "+ e.getMessage());
                Throwable t = ex.getCause();

                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


}
