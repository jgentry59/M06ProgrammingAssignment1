import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StaffDatabaseProgram {
    private static final String DB_URL = "jdbc:sqlite:database.db"; // Use database file name here

    public static void main(String[] args) {
        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(DB_URL);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Options:");
                System.out.println("1 - View record");
                System.out.println("2 - Insert record");
                System.out.println("3 - Update record");
                System.out.println("4 - Quit");

                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter ID to view: ");
                        String idToView = scanner.nextLine();
                        viewRecord(connection, idToView);
                        break;

                    case "2":
                        insertRecord(connection);
                        break;

                    case "3":
                        System.out.print("Enter ID to update: ");
                        String idToUpdate = scanner.nextLine();
                        updateRecord(connection, idToUpdate);
                        break;

                    case "4":
                        connection.close();
                        scanner.close();
                        System.out.println("Program terminated.");
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void viewRecord(Connection connection, String id) throws SQLException {
        // Prepare and execute SQL statement to view a record
        String sql = "SELECT * FROM Staff WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            System.out.println("Record found:");
            System.out.println("ID: " + resultSet.getString("id"));
            System.out.println("Last Name: " + resultSet.getString("lastName"));
            System.out.println("First Name: " + resultSet.getString("firstName"));
            // Include other fields as needed
        } else {
            System.out.println("Record not found");
        }
    }

    private static void insertRecord(Connection connection) throws SQLException {
        // Collect user input for a new record and insert it into the database
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter MI: ");
        String mi = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
    
        // Prepare and execute SQL statement to insert a new record
        String sql = "INSERT INTO Staff (id, lastName, firstName, mi, address, city, state, telephone, email) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        preparedStatement.setString(2, lastName);
        preparedStatement.setString(3, firstName);
        preparedStatement.setString(4, mi);
        preparedStatement.setString(5, address);
        preparedStatement.setString(6, city);
        preparedStatement.setString(7, state);
        preparedStatement.setString(8, telephone);
        preparedStatement.setString(9, email);
    
        int rowsInserted = preparedStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Record inserted successfully");
        } else {
            System.out.println("Failed to insert record");
        }
    }

    private static void updateRecord(Connection connection, String id) throws SQLException {
        // Collect user input for updating a record and execute the update in the database
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter MI: ");
        String mi = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter City: ");
        String city = scanner.nextLine();
        System.out.print("Enter State: ");
        String state = scanner.nextLine();
        System.out.print("Enter Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
    
        // Prepare and execute SQL statement to update an existing record
        String sql = "UPDATE Staff SET lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, " +
                     "telephone = ?, email = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, mi);
        preparedStatement.setString(4, address);
        preparedStatement.setString(5, city);
        preparedStatement.setString(6, state);
        preparedStatement.setString(7, telephone);
        preparedStatement.setString(8, email);
        preparedStatement.setString(9, id);
    
        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Record updated successfully");
        } else {
            System.out.println("Failed to update record");
        }
    }
}
