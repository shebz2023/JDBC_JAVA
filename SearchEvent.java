import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class SearchEVent {
    public static void search(String title) {
        try {
            Properties props = new Properties();
            FileInputStream fs = new FileInputStream("config.Properties");
            props.load(fs);
            String url = props.getProperty("db.url");
            String name = props.getProperty("db.name");
            String password = props.getProperty("db.password");
            String query = "select title from events";
            Connection con = DriverManager.getConnection(url, name, password );
            System.out.println("connection instablished successfully");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println(rs);
            while (rs.next()) {
                String a = rs.getString("title");
                System.out.println(a + "==== res =====");
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("====== The error =====>>> " + e);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Event title");
        String title = scanner.nextLine();
        search(title);
        scanner.close();
    }

}