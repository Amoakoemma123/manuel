import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.EventObject;


public class DatabaseGUI extends JFrame implements ActionListener {
	private JTextField idField, nameField, genderField;
    private JButton insertButton, deleteButton, updateButton, retrieveButton, searchButton;

    private Connection connection;
    private Statement statement;
	private EventObject e;
	
public  DatabaseGUI() {
	initializeUI();
	
	  String url = "jdbc:mysql://localhost:3306/Uenr";
      String username = "Root";
      String password = "!@1234";
	  String jdbcUrl = url;
		
		System.out.println("WELCOME TO THE DATABASE");
          try {
			Connection	connection = DriverManager.getConnection(jdbcUrl, username, password);
			} catch (Exception e) {
				e.printStackTrace();
			}
	
}
    private void initializeUI() {
      //Initialize UI components
    	idField = new JTextField(10);
        nameField = new JTextField(10);
        genderField = new JTextField(10);
    	
    	 insertButton = new JButton("Insert");
         deleteButton = new JButton("Delete");
         updateButton = new JButton("Update");
         retrieveButton = new JButton("Retrieve");
         searchButton = new JButton("Search");
    	
      // Set up layout
         JPanel inputPanel = new JPanel();
         inputPanel.setLayout(new GridLayout(3, 2));
         inputPanel.add(new JLabel("ID:"));
         inputPanel.add(idField);
         inputPanel.add(new JLabel("Name:"));
         inputPanel.add(nameField);
         inputPanel.add(new JLabel("Gender:"));
         inputPanel.add(genderField);
         
         JPanel buttonPanel = new JPanel();
         buttonPanel.add(insertButton);
         buttonPanel.add(deleteButton);
         buttonPanel.add(updateButton);
         buttonPanel.add(retrieveButton);
         buttonPanel.add(searchButton);
         
        // Add action listeners to buttons
        insertButton.addActionListener((ActionListener) this);
        deleteButton.addActionListener((ActionListener) this);
        updateButton.addActionListener((ActionListener) this);
        retrieveButton.addActionListener((ActionListener) this);
        searchButton.addActionListener((ActionListener) this);

        // Add components to the frame
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == insertButton) {
            performInsertOperation();
        } else if (e.getSource() == deleteButton) {
            performDeleteOperation();
        } else if (e.getSource() == updateButton) {
            performUpdateOperation();
        } else if (e.getSource() == retrieveButton) {
            performRetrieveOperation();
        } else if (e.getSource() == searchButton) {
            performSearchOperation();
        }
    }

    // Insert Button
    private void performInsertOperation() {
        String id = idField.getText();
        String name = nameField.getText();
        // Extract other fields here

        String insertQuery = "INSERT INTO your_table_name (id, name, dob, gender, , column9) " +
                             "VALUES ('" + id + "', '" + name + "', value3, value4, ..., value9)";

        try {
            statement.executeUpdate(insertQuery);
            JOptionPane.showMessageDialog(this, "Record inserted successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error inserting record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Delete Button
    private void performDeleteOperation() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this record?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            String id = idField.getText();
            String deleteQuery = "DELETE FROM your_table_name WHERE id = '" + id + "'";

            try {
                int rowsAffected = statement.executeUpdate(deleteQuery);
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "No record found with the specified ID.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // Update Button
    private void performUpdateOperation() {
        String id = idField.getText();
        String name = nameField.getText();
        // Extract other fields here

        String updateQuery = "UPDATE your_table_name " +
                             "SET name = '" + name + "', column3 = value3, column4 = value4, ..., column9 = value9 " +
                             "WHERE id = '" + id + "'";

        try {
            int rowsAffected = statement.executeUpdate(updateQuery);
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with the specified ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating record: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Retrieve Button
    private void performRetrieveOperation() {
        String retrieveQuery = "SELECT * FROM student";

        try {
            ResultSet resultSet = statement.executeQuery(retrieveQuery);
            // Process and display retrieved records in a separate UI component
            // ...
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error retrieving records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    // Search Button
    private void performSearchOperation() {
        String searchName = nameField.getText();
        String searchId = idField.getText();
        String searchGender = genderField.getText();

        String searchQuery = "SELECT * FROM student WHERE name = '" + searchName + "' OR id = '" + searchId + "' OR gender = '" + searchGender + "'";

        try {
            ResultSet resultSet = statement.executeQuery(searchQuery);
            // Process and display search results in a separate UI component
            // ...
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error searching records: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();} 
    }


    public static void main(String[] args) {
    	DatabaseGUI gui = new DatabaseGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.pack();
        gui.setVisible(true);
  
      
        
        }
	}
   


