/*
 * Author: Mai Evans
 * Project: LegoDB (Lego Database)
 * 
 * Internal Java Librairies: .sql.* and .util.* as well as the JDBC to interact with MariaDB
 * Modified: 4/25/2024
 */


import java.sql.*;
import java.util.*;

public class LegoDB
 {

    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        LegoDB mdb = new LegoDB();
        
        String jdbcUrl = "jdbc:mariadb://localhost:3306/legos";
        String username = "root";
        String password = "password";
        
        // Try-with-resources to automatically close the connection
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to MariaDB!");
        
           mdb.mainMenu(connection);

        } catch (SQLException e) {
            System.err.println("Error connecting to MariaDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mainMenu(Connection connection) throws SQLException{
        
        Statement statement = connection.createStatement();
        System.out.println("\nSelect an action from the menu below: Usage: 1-11");

        System.out.println("\t1. Print all Lego set info\n"+"\t2. Print all Lego keychain info\n" +"\t3. Print all themes info\n" + "\t4. Search for Lego sets\n"+"\t5. Search for Lego keychains\n"
        + "\t6. Search for themes by name\n" +"\t7. Add Lego set\n" +"\t8. Remove Lego set\n" +"\t9. Add Lego keychain\n" +"\t10. Remove Lego keychain\n"+"\t11. Quit\n");
        System.out.print(">> ");
        int choice = scan.nextInt();

        switch(choice) {
            case 1:
              System.out.println("Selected: 1. Print all Lego set info\n");
              printLegoSets(connection, statement);
              break;
            case 2:
              System.out.println("Selected: 2. Print all Lego keychain info\n");
              printLegoKeychains(connection, statement);
              break;
            case 3:
              System.out.println("Selected: 3. Print all themes info\n");
              printThemes(connection, statement);
              break;
            case 4:
              System.out.println("Selected: 4. Search Lego sets\n");
              searchLegoSets(connection, statement);
              break;  
            case 5:
              System.out.println("Selected: 5. Search Lego keychains\n");
              searchLegoKeychains(connection, statement);
              break;
            case 6:
              System.out.println("Selected: 6. Search for themes by name\n");
              searchThemes(connection, statement);
              break;
            case 7:
              System.out.println("Selected: 7. Add Lego set\n");
              addLegoSets(connection, statement);
              break;  
            case 8:
              System.out.println("Selected: 8. Remove Lego set\n");
              removeLegoSets(connection, statement);
              break;
            case 9:
              System.out.println("Selected: 9. Add Lego keychain\n");
              addLegoKeychains(connection, statement);
              break;  
            case 10:
              System.out.println("Selected: 10. Remove Lego keychain\n");
              removeLegoKeychains(connection, statement);
              break;
            case 11:
              System.out.print("Bye~Bye!");
              System.exit(0);
            default:
          }

    }

    private void pause(){

        try {
            Thread.sleep(500); // Pauses execution for 0.5 seconds 
        } catch (InterruptedException e) {
            System.out.print("crashed");
        }
        
    }
    // option 1
    private void printLegoSets(Connection connection, Statement statement) throws SQLException{

        String query = "SELECT * FROM LegoSets";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("#      Name                                Theme                     Year  Age  Pieces  Retired");
        
        // Process the query results
        while (resultSet.next()) {
            // Retrieve data from each row
            int itemNum = resultSet.getInt("itemNumber");
            String name = resultSet.getString("name"); 
            String theme = resultSet.getString("theme"); 
            int year = resultSet.getInt("year");
            String ageRange = resultSet.getString("ageRange"); 
            int piecesNum = resultSet.getInt("piecesNum");
            boolean retired = resultSet.getBoolean("retired");
    
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.printf("%-6d %-35s %-25s %-5d %-4s %-7d %-7b\n", itemNum, name, theme, year, ageRange, piecesNum, retired);

        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        pause();
        mainMenu(connection);

    }
    // option 2
    private void printLegoKeychains(Connection connection, Statement statement) throws SQLException{
        String query = "SELECT * FROM LegoKeychains";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("#       Name                         Theme         Year");

        // Process the query results
        while (resultSet.next()) {
            // Retrieve data from each row
            int itemNum = resultSet.getInt("itemNumber");
            String name = resultSet.getString("name"); 
            String theme = resultSet.getString("theme"); 
            int year = resultSet.getInt("year");

            System.out.println("---------------------------------------------------------");
            System.out.printf("%-7d %-28s %-13s %d\n", itemNum, name, theme, year);
        }
        System.out.println("---------------------------------------------------------");
        pause();
        mainMenu(connection);
    }
    // option 3
    private void printThemes(Connection connection, Statement statement) throws SQLException{
        String query = "SELECT * FROM Themes";
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Name                     Films   VideoGames");

            while (resultSet.next()) {
                // Retrieve data from each row
                String name = resultSet.getString("name"); 
                Boolean films = resultSet.getBoolean("films"); 
                Boolean videoGames = resultSet.getBoolean("videoGames");

                System.out.println("-------------------------------------------");
                System.out.printf("%-24s %-7b %-11b\n", name, films, videoGames);
            }
            System.out.println("-------------------------------------------");
            pause();
            mainMenu(connection);
    }
//  4
private void searchLegoSets(Connection connection, Statement statement) throws SQLException {
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String query = null;
    
    System.out.println("Choose an attribute to search by:\n" + "\t1. Item Number\n" + "\t2. Name\n" + "\t3. Theme\n" + "\t4. Year\n");
    System.out.print(">> ");
    int choice = scan.nextInt();

    switch (choice) {
        case 1:
            System.out.println("Selected: 1. Item Number\n");
            System.out.print("Enter the item number of the LEGO set: ");
            int itemNum = scan.nextInt(); // Read the item number
            // Prepare the query with a placeholder for the item number
            query = "SELECT * FROM LegoSets WHERE itemNumber = ?";
            // Create a prepared statement
            preparedStatement = connection.prepareStatement(query);
            // Set the parameter value
            preparedStatement.setInt(1, itemNum);
            break;
        case 2:
            System.out.println("Selected: 2. Name\n");
            System.out.print("Enter the name of the LEGO set:");
            scan.nextLine(); // Consume the newline character

            String name = scan.nextLine(); // Read the name
            query = "SELECT * FROM LegoSets WHERE name LIKE ?";
            // Create a prepared statement
            preparedStatement = connection.prepareStatement(query);
            // Set the parameter value
            preparedStatement.setString(1, "%" + name + "%");
            break;
        case 3:
            System.out.println("Selected: 3. Theme\n");
            System.out.print("Enter the theme of the LEGO set:");
            scan.nextLine(); // Consume the newline character

            String theme = scan.nextLine(); // Read the theme
            query = "SELECT * FROM LegoSets WHERE theme LIKE ?";
            // Create a prepared statement
            preparedStatement = connection.prepareStatement(query);
            // Set the parameter value
            preparedStatement.setString(1, "%" + theme + "%");
            break;
        case 4:
            System.out.println("Selected: 4. Year\n");
            System.out.print("Enter the year of the LEGO set:");
            int year = scan.nextInt(); // Read the year
            // Prepare the query with a placeholder for the year
            query = "SELECT * FROM LegoSets WHERE year = ?";
            // Create a prepared statement
            preparedStatement = connection.prepareStatement(query);
            // Set the parameter value
            preparedStatement.setInt(1, year);
            break;
        default:
            // Handle invalid choice
            break;
    }

    // Execute the query if preparedStatement is not null
    if (preparedStatement != null) {
        // Execute the query
        resultSet = preparedStatement.executeQuery();
        // Process the results
        System.out.println("#      Name                                Theme                     Year  Age  Pieces  Retired");
        while (resultSet.next()) {
            // Access each column in the current row
            int itemNumber = resultSet.getInt("itemNumber");
            String name = resultSet.getString("name");
            String theme = resultSet.getString("theme");
            int resultYear = resultSet.getInt("year");
            String ageRange = resultSet.getString("ageRange");
            int piecesNum = resultSet.getInt("piecesNum");
            boolean retired = resultSet.getBoolean("retired");
            // Print results
            System.out.println("----------------------------------------------------------------------------------------------------");
            System.out.printf("%-6d %-35s %-25s %-5d %-4s %-7d %-7b\n", itemNumber, name, theme, resultYear, ageRange, piecesNum, retired);
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    pause();
    mainMenu(connection);
}

// option 5
    private void searchLegoKeychains(Connection connection, Statement statement) throws SQLException{
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = null;
        
        System.out.println("Choose an attribute to search by:\n" + "\t1. Item Number\n" + "\t2. Name\n" + "\t3. Theme\n" + "\t4. Year\n");
        System.out.print(">> ");
        int choice = scan.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Selected: 1. Item Number\n");
                System.out.print("Enter the item number of the LEGO keychain: ");
                int itemNum = scan.nextInt(); // Read the item number
                // Prepare the query with a placeholder for the item number
                query = "SELECT * FROM LegoKeychains WHERE itemNumber = ?";
                // Create a prepared statement
                preparedStatement = connection.prepareStatement(query);
                // Set the parameter value
                preparedStatement.setInt(1, itemNum);
                break;
            case 2:
                System.out.println("Selected: 2. Name\n");
                System.out.print("Enter the name of the LEGO keychain:");
                scan.nextLine(); // Consume the newline character
    
                String name = scan.nextLine(); // Read the name
                query = "SELECT * FROM LegoKeychains WHERE name LIKE ?";
                // Create a prepared statement
                preparedStatement = connection.prepareStatement(query);
                // Set the parameter value
                preparedStatement.setString(1, "%" + name + "%");
                break;
            case 3:
                System.out.println("Selected: 3. Theme\n");
                System.out.print("Enter the theme of the LEGO keychain:");
                scan.nextLine(); // Consume the newline character
    
                String theme = scan.nextLine(); // Read the theme
                query = "SELECT * FROM LegoKeychains WHERE theme LIKE ?";
                // Create a prepared statement
                preparedStatement = connection.prepareStatement(query);
                // Set the parameter value
                preparedStatement.setString(1, "%" + theme + "%");
                break;
            case 4:
                System.out.println("Selected: 4. Year\n");
                System.out.print("Enter the year of the LEGO keychain:");
                int year = scan.nextInt(); // Read the year
                // Prepare the query with a placeholder for the year
                query = "SELECT * FROM LegoKeychains WHERE year = ?";
                // Create a prepared statement
                preparedStatement = connection.prepareStatement(query);
                // Set the parameter value
                preparedStatement.setInt(1, year);
                break;
            default:
                // Handle invalid choice
                break;
        }
    
        // Execute the query if preparedStatement is not null
        if (preparedStatement != null) {
            // Execute the query
            resultSet = preparedStatement.executeQuery();
            // Process the results
            System.out.println("#       Name                         Theme         Year");
            while (resultSet.next()) {
                // Access each column in the current row
                int itemNumber = resultSet.getInt("itemNumber");
                String name = resultSet.getString("name");
                String theme = resultSet.getString("theme");
                int year = resultSet.getInt("year");
                // Print results
                System.out.println("---------------------------------------------------------");
            System.out.printf("%-7d %-28s %-13s %d\n", itemNumber, name, theme, year);
            }
        System.out.println("---------------------------------------------------------");
        }
    
        pause();
        mainMenu(connection);
    }
// option 6
private void searchThemes(Connection connection, Statement statement) throws SQLException {
    System.out.print("Please enter name: ");
    System.out.print("Please enter name: ");
    scan.nextLine(); // Consume the newline character
    String themeName = scan.nextLine(); // Read the theme name and trim any leading/trailing whitespace

    String query = "SELECT * FROM Themes WHERE name LIKE ?";
    // Create a prepared statement
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    // Set the parameter value
    preparedStatement.setString(1, "%" + themeName + "%");

    // Execute the prepared statement
    ResultSet resultSet = preparedStatement.executeQuery();
    
    System.out.println("Name                     Films   VideoGames");

        while (resultSet.next()) {
            // Retrieve data from each row
            String name = resultSet.getString("name"); 
            Boolean films = resultSet.getBoolean("films"); 
            Boolean videoGames = resultSet.getBoolean("videoGames");

            System.out.println("-------------------------------------------");
            System.out.printf("%-24s %-7b %-11b\n", name, films, videoGames);
        }
    System.out.println("-------------------------------------------");

    pause();
    mainMenu(connection);
}



// option 7
    private void addLegoSets(Connection connection, Statement statement) throws SQLException{

        try {
            // Prompt the user for input
            System.out.println("Enter the item number of the LEGO set:");
            int itemNum = scan.nextInt(); // Read the item number
            scan.nextLine(); // Consume the newline character
            
            System.out.println("Enter the name of the LEGO set:");
            String name = scan.nextLine(); // Read the name
            
            System.out.println("Enter the theme of the LEGO set:");
            String theme = scan.nextLine(); // Read the theme
            
            System.out.println("Enter the year of the LEGO set:");
            int year = scan.nextInt(); // Read the year
            scan.nextLine(); // Consume the newline character
            
            System.out.println("Enter the age range of the LEGO set:");
            String ageRange = scan.nextLine(); // Read the age range
            
            System.out.println("Enter the number of pieces in the LEGO set:");
            int piecesNum = scan.nextInt(); // Read the number of pieces
            
            System.out.println("Is the LEGO set retired? (true/false):");
            boolean retired = scan.nextBoolean(); // Read the retired status
            scan.nextLine(); // Consume the newline character
    
            // Prepare the SQL statement
            String sql = "INSERT INTO LegoSets (itemNumber, name, theme, year, ageRange, piecesNum, retired) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
            // Set the parameters
            preparedStatement.setInt(1, itemNum);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, theme);
            preparedStatement.setInt(4, year);
            preparedStatement.setString(5, ageRange);
            preparedStatement.setInt(6, piecesNum);
            preparedStatement.setBoolean(7, retired);
    
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("LEGO set added successfully.");
            } else {
                System.out.println("Failed to add LEGO set.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding LEGO set: " + e.getMessage());
            e.printStackTrace();
        }
        pause();
        mainMenu(connection);
        
    }
// option 8
    private void removeLegoSets(Connection connection, Statement statement) throws SQLException{

        try {
            // Prompt the user for input
            System.out.println("Enter the item number of the LEGO set:");
            int itemNum = scan.nextInt(); // Read the item number
           
            
            // Prepare the SQL statement
            String sql = "DELETE FROM LegoSets WHERE itemNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
            // Set the parameters
            preparedStatement.setInt(1, itemNum);
            
    
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("LEGO set removed successfully.");
            } else {
                System.out.println("Failed to remove LEGO set.");
            }
        } catch (SQLException e) {
            System.err.println("Error removing LEGO set: " + e.getMessage());
            e.printStackTrace();
        }
        pause();
        mainMenu(connection);
        
    }
// option 9
    private void addLegoKeychains(Connection connection, Statement statement) throws SQLException{

        try {
            // Prompt the user for input
            System.out.println("Enter the item number of the LEGO keychain:");
            int itemNum = scan.nextInt(); // Read the item number
            scan.nextLine(); // Consume the newline character
            
            System.out.println("Enter the name of the LEGO keychain:");
            String name = scan.nextLine(); // Read the name
            
            System.out.println("Enter the theme of the LEGO keychain:");
            String theme = scan.nextLine(); // Read the theme
            
            System.out.println("Enter the year of the LEGO keychain:");
            int year = scan.nextInt(); // Read the year
            scan.nextLine(); // Consume the newline character
    
            // Prepare the SQL statement
            String sql = "INSERT INTO LegoKeychains (itemNumber, name, theme, year) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
            // Set the parameters
            preparedStatement.setInt(1, itemNum);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, theme);
            preparedStatement.setInt(4, year);
    
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("LEGO keychain added successfully.");
            } else {
                System.out.println("Failed to add LEGO keychain.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding LEGO set: " + e.getMessage());
            e.printStackTrace();
        }
        pause();
        mainMenu(connection);
        
    }
// option 10
    private void removeLegoKeychains(Connection connection, Statement statement) throws SQLException{

        try {
            // Prompt the user for input
            System.out.println("Enter the item number of the LEGO keychain:");
            int itemNum = scan.nextInt(); // Read the item number
           
            
            // Prepare the SQL statement
            String sql = "DELETE FROM LegoKeychains WHERE itemNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    
            // Set the parameters
            preparedStatement.setInt(1, itemNum);
            
    
            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
    
            if (rowsAffected > 0) {
                System.out.println("LEGO keychain removed successfully.");
            } else {
                System.out.println("Failed to remove LEGO keychain.");
            }
        } catch (SQLException e) {
            System.err.println("Error removing LEGO keychain: " + e.getMessage());
            e.printStackTrace();
        }
        pause();
        mainMenu(connection);        
    }

}
