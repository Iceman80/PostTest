import java.sql.*;

public class MySql {
    Connection c = null;
    Statement stmt = null;

    public void greatStatement() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/testdb",
                            "ice", "123");

            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate("create table if not exists COMPANY (ID SERIAL , NAME varchar(256)  ,AGE int, ADDRESS varchar(256), SALARY int, primary key (ID));");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToSql(String name, int age, String address, double salary) {
        try {
            c.setAutoCommit(false);
            String temp = "INSERT INTO  COMPANY (NAME,AGE,ADDRESS,SALARY) VALUES ('" + name + "', " + age + ", '" + address + "', " + salary + " );";
            stmt.executeUpdate(temp);
            c.commit();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public boolean addUser(String name, int age, String address, double salary) {
        boolean temp = false;
        if (!containSql("NAME", name)) {
            addToSql(name, age, address, salary);
            temp = true;
        } else {
            System.out.println("User with the same name already exists");
        }
        return temp;
    }

    public void printSql(String table, String find) {
        int x = 0;

        try {
            String tmp = "select * from COMPANY where " + table + "='" + find + "';";
            stmt.executeQuery(tmp);
            ResultSet result = stmt.getResultSet();
            x = result.getMetaData().getColumnCount();
            System.out.println();

            while (result.next()) {
                for (int i = 1; i <= x; i++) {
                    System.out.print(result.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println();
    }

    public boolean containSql(String table, String find) {
        boolean ret = false;
        try {
            String tmp = "select * from COMPANY where " + table + "='" + find + "';";
            stmt.executeQuery(tmp);
            ResultSet result = stmt.getResultSet();
            ret = result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public void closeStatement() {
        try {
            c.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

