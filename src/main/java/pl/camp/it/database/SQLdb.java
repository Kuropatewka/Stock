package pl.camp.it.database;

import pl.camp.it.model.Product;
import pl.camp.it.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLdb {
    public static final Connection connection = connect();

    private static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/stock?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&characterEncoding=UTF8", "root", "");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Brak połączenia z bazą");
        return null;
    }

    public static void saveProduct(String name, int amount, long barCode, String category) {
        //jako argumenty pola klasy zamiast nowego obiektu bo nie wiem jakie id ustawiać
        try {
            String sql = "INSERT INTO tproduct (name, amount, barCode, category) VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, amount);
            preparedStatement.setLong(3, barCode);
            preparedStatement.setString(4, category);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Product> getAllProducts() {
        List<Product> resultList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct");

            while (wyniki.next()) {
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Integer amount = wyniki.getInt("amount");
                Long barCode = wyniki.getLong("barCode");
                String category = wyniki.getString("category");

                resultList.add(new Product(id, name, amount, barCode, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static List<ProductCategory> getAllCategories() {
        List<ProductCategory> resultList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tcategory WHERE deleted = false;");

            while (wyniki.next()) {
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Boolean deleted = wyniki.getBoolean("deleted");

                resultList.add(new ProductCategory(id, name, deleted));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static void saveCategory(String name) {
        try {
            String sql = "INSERT INTO tcategory (name, deleted) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, false);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static List<Product> getAllProductsByCategory(String whichCategory) {
        List<Product> resultList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();

            ResultSet wyniki = statement.executeQuery("SELECT * FROM tproduct WHERE category = '" + whichCategory + "'");

            while (wyniki.next()) {
                Integer id = wyniki.getInt("id");
                String name = wyniki.getString("name");
                Integer amount = wyniki.getInt("amount");
                Long barCode = wyniki.getLong("barCode");
                String category = wyniki.getString("category");

                resultList.add(new Product(id, name, amount, barCode, category));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultList;
    }

    public static void updateCategory(String name) {

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE tcategory SET deleted = true WHERE name = '" + name + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void updateProduct(String name) {

        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("UPDATE tproduct SET category = 'Brak kategorii' WHERE category = '" + name + "'");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
