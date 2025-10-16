import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class SearchEvent {
    public static void search(String title) {
        try {
            Properties props = new Properties();
            FileInputStream fs = new FileInputStream("config.Properties");
            props.load(fs);
            String url = props.getProperty("db.url");
            String name = props.getProperty("db.name");
            String password = props.getProperty("db.password");
            String query = "select * from events Where title = ?";
            Connection con = DriverManager.getConnection(url, name, password);
            System.out.println("connection instablished successfully");
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, title);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String tit = rs.getString("title");
                String desc = rs.getString("description");
                System.out.println(tit + "\n" + "Description:" + desc);
            } else {
                System.out.println("Not found");
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
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