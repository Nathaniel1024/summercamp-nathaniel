package Admin;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class gradeForm extends javax.swing.JInternalFrame {

    // Constructor for the gradeForm class
    public gradeForm() {
        // Initialize the form components (this method is auto-generated by GUI builders of NetBeans)
        initComponents();
        
        // Get the default toolkit (which provides access to system-related information and services)
        Toolkit toolkit = getToolkit();

        // Get the screen size (resolution) from the toolkit
        Dimension size = toolkit.getScreenSize();

        // Set the location of the form relative to the screen size.
        // Position the form slightly offset from the center of the screen.
        // The form's X position is set to 1/15th of the screen width minus a small offset.
        // The form's Y position is set to 1/5th of the screen height minus a small offset.
        setLocation(size.width / 15 - getWidth() / 18, size.height / 5 - getHeight() / 4);
    }
    
    // Method to refresh the tblGrade table by retrieving all records from the Grade table in the database
    private void refreshGradeTable() {
        Connection conn = null;  // Declare the database connection object
        Statement stmt = null;   // Declare the statement object for executing SQL queries
        ResultSet rs = null;     // Declare the result set to store the query result

        try {
            // Database connection details
            String url = "jdbc:mysql://localhost:3306/summercamp";  // JDBC URL for connecting to the database
            String user = "root";  // Username for database access
            String password = "Java@2024";  // Password for database access

            // Establish the connection to the database
            conn = DriverManager.getConnection(url, user, password);

            // SQL query to retrieve all records from the Grade table
            String query = "SELECT * FROM Grade";
            stmt = conn.createStatement();  // Create a statement object for executing the query
            rs = stmt.executeQuery(query);  // Execute the query and store the result in the result set

            // Get the DefaultTableModel from the tblGrade (the table displayed in the UI)
            DefaultTableModel model = (DefaultTableModel) tblGrade.getModel();
            model.setRowCount(0);  // Clear any existing rows in the table model

            // Iterate over the result set and add rows to the table model
            while (rs.next()) {
                // Create a row object with the data from the current row of the result set
                Object[] row = new Object[]{
                    rs.getString("firstname"),  // First name of the student
                    rs.getString("lastname"),   // Last name of the student
                    rs.getString("yas"),        // Age or year of study
                    rs.getString("usn"),        // USN (unique student number)
                    rs.getString("course"),     // Course the student is enrolled in
                    rs.getDouble("grade")       // Grade of the student
                };
                // Add the row to the table model
                model.addRow(row);
            }

        } catch (Exception e) {
            // Print any exception that occurs during the execution
            e.printStackTrace();
        } finally {
            // Close all the resources to avoid memory leaks
            try {
                if (rs != null) rs.close();    // Close the result set
                if (stmt != null) stmt.close();  // Close the statement
                if (conn != null) conn.close();  // Close the database connection
            } catch (Exception e) {
                e.printStackTrace();  // Print any exception that occurs during resource closing
            }
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtUsn = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnEnter = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrade = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtGrade = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Grade");

        jPanel1.setBackground(new java.awt.Color(253, 177, 4));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("ENTER USN");

        txtUsn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtUsn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUsn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsnKeyPressed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/course.gif"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        btnEnter.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnEnter.setText("ENTER");
        btnEnter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEnterMouseClicked(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        tblGrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Firstname", "Lastname", "Year and Section", "Student Number", "Course", "Grade"
            }
        ));
        tblGrade.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGradeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGrade);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1054, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel4.setText("ENTER GRADE");

        txtGrade.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtGrade.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtGrade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGradeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(btnEnter, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUsn, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
                            .addComponent(txtGrade))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtUsn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnter)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(113, 113, 113))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEnterMouseClicked
        
        // Retrieve the USN (Unique Student Number) and grade entered by the user
        String usn = txtUsn.getText();  // Get the USN from the text field
        double grade = Double.parseDouble(txtGrade.getText());  // Get the grade from the text field

        // Database connection details (URL, username, and password)
        String url = "jdbc:mysql://localhost:3306/summercamp";
        String user = "root";
        String password = "Java@2024";

        // Initialize database connection objects
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the MySQL database
            conn = DriverManager.getConnection(url, user, password);

            // SQL query to retrieve student information based on the provided USN
            String selectSql = "SELECT firstname, lastname, yas, course FROM Student WHERE usn = ?";
            pstmt = conn.prepareStatement(selectSql);
            pstmt.setString(1, usn);  // Set the USN in the query
            rs = pstmt.executeQuery();  // Execute the query

            if (rs.next()) {
                // If the student exists, retrieve their details
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String yas = rs.getString("yas");
                String course = rs.getString("course");

                // SQL query to insert the retrieved student information along with the grade into the Grade table
                String insertSql = "INSERT INTO Grade (firstname, lastname, yas, usn, course, grade) VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = conn.prepareStatement(insertSql);
                pstmt.setString(1, firstname);
                pstmt.setString(2, lastname);
                pstmt.setString(3, yas);
                pstmt.setString(4, usn);
                pstmt.setString(5, course);
                pstmt.setDouble(6, grade);

                // Execute the insert statement to save the data in the Grade table
                pstmt.executeUpdate();

                // SQL query to delete the student record from the Student table after the grade has been recorded
                String deleteSql = "DELETE FROM Student WHERE usn = ?";
                pstmt = conn.prepareStatement(deleteSql);
                pstmt.setString(1, usn);  // Set the USN for deletion

                // Execute the delete statement to remove the student record
                pstmt.executeUpdate();

                // Show confirmation dialog indicating success
                JOptionPane.showMessageDialog(null, "Grade information successfully inserted and student record deleted.");

                // Clear the input fields after successful insertion
                txtUsn.setText("");
                txtGrade.setText("");

                // Refresh the table (tblGrade) to show updated data
                refreshGradeTable();

                // Make the table editable and enable row selection
                tblGrade.setEnabled(true);  // Enable table interaction
                tblGrade.setFocusable(true);  // Allow table to receive focus
                tblGrade.setRowSelectionAllowed(true);  // Enable row selection

            } else {
                // Show error dialog if no student is found with the given USN
                JOptionPane.showMessageDialog(null, "No student found with USN: " + usn);
            }

        } catch (Exception e) {
            // Handle any exceptions that occur during the database interaction
            e.printStackTrace();
        } finally {
            try {
                // Close the database resources to prevent memory leaks
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();  // Handle exception during resource cleanup
            }
        }
        
    }//GEN-LAST:event_btnEnterMouseClicked

    private void txtUsnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsnKeyPressed
        
        // Check if the Enter key was pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String usn = txtUsn.getText();  // Get the input USN from the text field

            // Database connection details (URL, username, password)
            String url = "jdbc:mysql://localhost:3306/summercamp";
            String user = "root";
            String password = "Java@2024";

            // Initialize database connection objects
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Establish a connection to the MySQL database
                conn = DriverManager.getConnection(url, user, password);

                // SQL query to fetch student details based on the input USN
                String sql = "SELECT * FROM Student WHERE usn = ?";

                pstmt = conn.prepareStatement(sql);  // Prepare the query
                pstmt.setString(1, usn);  // Set the USN parameter in the query

                // Execute the query and get the results
                rs = pstmt.executeQuery();

                // Clear all existing rows from the tblGrade table before inserting new data
                for (int i = tblGrade.getRowCount() - 1; i >= 0; i--) {
                    ((javax.swing.table.DefaultTableModel) tblGrade.getModel()).removeRow(i);
                }

                // Iterate through the result set and populate the table with student data
                while (rs.next()) {
                    // Create an array containing the row data (student information)
                    Object[] rowData = new Object[] {
                        rs.getString("firstname"),  // Get student's first name
                        rs.getString("lastname"),   // Get student's last name
                        rs.getString("yas"),        // Get student's age
                        rs.getString("usn"),        // Get student's USN
                        rs.getString("course")      // Get student's course
                    };

                    // Add the row data to the table model
                    ((javax.swing.table.DefaultTableModel) tblGrade.getModel()).addRow(rowData);
                }

                // After populating the table, make it non-editable and disable user interactions
                tblGrade.setEnabled(false);  // Disable interaction with the table
                tblGrade.setFocusable(false);  // Prevent focus on the table
                tblGrade.setRowSelectionAllowed(false);  // Disable row selection

            } catch (Exception e) {
                // Print the stack trace for any exceptions that occur during database interaction
                e.printStackTrace();
            } finally {
                try {
                    // Close the database resources to prevent memory leaks
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    e.printStackTrace();  // Handle any exceptions during resource cleanup
                }
            }
        }
        
    }//GEN-LAST:event_txtUsnKeyPressed

    private void txtGradeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradeKeyPressed
        
        // Check if the Enter key was pressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String usn = txtUsn.getText();  // Get the USN entered by the user
            double grade = Double.parseDouble(txtGrade.getText());  // Get the grade entered by the user

            // Database connection details (URL, username, password)
            String url = "jdbc:mysql://localhost:3306/summercamp";
            String user = "root";
            String password = "Java@2024";

            // Initialize database connection objects
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Establish a connection to the MySQL database
                conn = DriverManager.getConnection(url, user, password);

                // SQL query to fetch student details based on the entered USN
                String selectSql = "SELECT firstname, lastname, yas, course FROM Student WHERE usn = ?";
                pstmt = conn.prepareStatement(selectSql);  // Prepare the query
                pstmt.setString(1, usn);  // Set the USN parameter in the query

                // Execute the query and retrieve the results
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    // If a student with the given USN is found, retrieve their information
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    String yas = rs.getString("yas");
                    String course = rs.getString("course");

                    // SQL query to insert the student information and grade into the Grade table
                    String insertSql = "INSERT INTO Grade (firstname, lastname, yas, usn, course, grade) VALUES (?, ?, ?, ?, ?, ?)";
                    pstmt = conn.prepareStatement(insertSql);  // Prepare the insert statement
                    pstmt.setString(1, firstname);  // Set student's first name
                    pstmt.setString(2, lastname);   // Set student's last name
                    pstmt.setString(3, yas);        // Set student's age
                    pstmt.setString(4, usn);        // Set student's USN
                    pstmt.setString(5, course);     // Set student's course
                    pstmt.setDouble(6, grade);      // Set the grade

                    // Execute the insert statement
                    pstmt.executeUpdate();

                    // SQL query to delete the student record from the Student table after grade insertion
                    String deleteSql = "DELETE FROM Student WHERE usn = ?";
                    pstmt = conn.prepareStatement(deleteSql);  // Prepare the delete statement
                    pstmt.setString(1, usn);  // Set the USN for deletion

                    // Execute the delete statement to remove the student record
                    pstmt.executeUpdate();

                    // Show a confirmation message indicating successful insertion and deletion
                    JOptionPane.showMessageDialog(null, "Grade information successfully inserted and student record deleted.");

                    // Clear the input fields after the operation
                    txtUsn.setText("");  // Clear the USN text field
                    txtGrade.setText("");  // Clear the Grade text field

                    // Refresh the table (tblGrade) to reflect the updated data
                    refreshGradeTable();

                    // Enable and make the table editable for interaction
                    tblGrade.setEnabled(true);  // Enable interaction with the table
                    tblGrade.setFocusable(true);  // Allow focus on the table
                    tblGrade.setRowSelectionAllowed(true);  // Enable row selection

                } else {
                    // If no student is found with the provided USN, show an error message
                    JOptionPane.showMessageDialog(null, "No student found with USN: " + usn);
                }

            } catch (Exception e) {
                // Handle any exceptions that occur during database interaction
                e.printStackTrace();
            } finally {
                try {
                    // Close the database resources to avoid memory leaks
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    e.printStackTrace();  // Handle exception during resource cleanup
                }
            }
        }
        
    }//GEN-LAST:event_txtGradeKeyPressed

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        
        // Get the selected row index from the tblGrade table
        int selectedRow = tblGrade.getSelectedRow();

        if (selectedRow == -1) {
            // No row is selected, show an error message and return
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
            return;  // Exit the method if no row is selected
        }

        // Confirm deletion with the user through a dialog box
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Get the USN value from the selected row, assuming USN is in column 3 (index 3)
            String usn = tblGrade.getValueAt(selectedRow, 3).toString();

            // Database connection details (URL, username, password)
            String url = "jdbc:mysql://localhost:3306/summercamp";
            String user = "root";
            String password = "Java@2024";

            // Initialize the database connection objects
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                // Establish a connection to the MySQL database
                conn = DriverManager.getConnection(url, user, password);

                // SQL query to delete the record from the Grade table based on the USN
                String deleteSql = "DELETE FROM Grade WHERE usn = ?";
                pstmt = conn.prepareStatement(deleteSql);  // Prepare the delete statement
                pstmt.setString(1, usn);  // Set the USN parameter in the query

                // Execute the delete statement and get the number of affected rows
                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    // If a row was deleted, remove the selected row from the table model
                    ((javax.swing.table.DefaultTableModel) tblGrade.getModel()).removeRow(selectedRow);

                    // Show a success message to the user
                    JOptionPane.showMessageDialog(null, "Record successfully deleted.");
                } else {
                    // If no rows were affected, show an error message (record might have been already deleted)
                    JOptionPane.showMessageDialog(null, "No record found for USN: " + usn);
                }

                // Clear the input fields after deletion
                txtUsn.setText("");  // Clear the USN text field
                txtGrade.setText("");  // Clear the Grade text field

            } catch (Exception e) {
                // Print any exceptions that occur during the database interaction
                e.printStackTrace();
            } finally {
                try {
                    // Close the database resources to prevent memory leaks
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    e.printStackTrace();  // Handle exceptions during resource cleanup
                }
            }
        }
        
    }//GEN-LAST:event_btnDeleteMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        
        // Ensure that both the USN and Grade fields are not empty
        if (txtUsn.getText().trim().isEmpty() || txtGrade.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Fields must not be empty");  // Alert if any field is empty
            return;  // Exit the method if validation fails
        }

        // Get the selected row index from the table
        int selectedRow = tblGrade.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(rootPane, "Please select a row to update");  // Alert if no row is selected
            return;  // Exit the method if no row is selected
        }

        // Get the table model and check the number of columns in the table
        DefaultTableModel updateTable = (DefaultTableModel) tblGrade.getModel();
        int columnCount = updateTable.getColumnCount();

        // Ensure the table has the expected number of columns (6: firstname, lastname, yas, usn, course, grade)
        if (columnCount != 6) {
            JOptionPane.showMessageDialog(rootPane, "Table does not have the expected number of columns");  // Alert if the column count is incorrect
            return;  // Exit the method if column validation fails
        }

        // Retrieve the old USN and Grade from the selected row (USN in column 3, Grade in column 5)
        String oldUsn = updateTable.getValueAt(selectedRow, 3).toString();  // Get the old USN
        String oldGrade = updateTable.getValueAt(selectedRow, 5).toString();  // Get the old Grade

        // Get the new USN and Grade from the input fields
        String newUsn = txtUsn.getText();  // Retrieve new USN from text field (should match old USN)
        String newGrade = txtGrade.getText();  // Retrieve new Grade from text field

        // Check if no changes have been made to the USN and Grade
        if (oldUsn.equals(newUsn) && oldGrade.equals(newGrade)) {
            JOptionPane.showMessageDialog(rootPane, "No changes detected");  // Alert if nothing has changed
            return;  // Exit the method if no changes are detected
        }

        // If the USN is modified, show an error and revert to the old USN
        if (!oldUsn.equals(newUsn)) {
            JOptionPane.showMessageDialog(rootPane, "You cannot change the student number (USN). Only the grade will be updated.");  
            txtUsn.setText(oldUsn);  // Revert the USN field to the original value
            return;  // Exit the method if the user attempts to change the USN
        }

        // Confirm the update action with the user
        int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to update this record?", "Confirm Update", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;  // Exit if the user cancels the update
        }

        try {
            // Load the MySQL JDBC driver and establish a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/summercamp?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", "root", "Java@2024");

            // SQL query to update the Grade in the database where the USN and old Grade match
            String sql = "UPDATE Grade SET grade = ? WHERE usn = ? AND grade = ?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, newGrade);  // Set the new Grade
            pst.setString(2, oldUsn);  // Use the old USN to locate the record
            pst.setString(3, oldGrade);  // Match the old Grade for the update

            // Execute the update query and get the result (number of affected rows)
            int k = pst.executeUpdate();

            if (k == 1) {
                // If one row was updated, show success message and refresh the table
                JOptionPane.showMessageDialog(null, "Grade has been updated successfully");
                refreshGradeTable();  // Refresh the table to reflect the update
                txtUsn.setText("");  // Clear the input fields after update
                txtGrade.setText("");
            } else {
                // If no rows were updated, show an error message
                JOptionPane.showMessageDialog(null, "Record not found or not updated");
            }

            // Close the database connection
            con.close();
        } catch (Exception e) {
            // If an exception occurs, show the error message
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void tblGradeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGradeMouseClicked
        
        try {
            // Get the selected row index from the table
            int selectedRow = tblGrade.getSelectedRow();

            // Check if a valid row is selected (selectedRow will be -1 if no row is selected)
            if (selectedRow == -1) {
                throw new Exception("Enter Grade First");  // Custom error message if no row is selected
            }

            // Retrieve the values from the selected row for USN and Grade
            // Assuming column 3 holds the USN and column 5 holds the Grade
            String usn = tblGrade.getValueAt(selectedRow, 3).toString();  // Get the USN from column 3
            String grade = tblGrade.getValueAt(selectedRow, 5).toString();  // Get the Grade from column 5

            // Set the retrieved USN and Grade values into the respective text fields
            txtUsn.setText(usn);  // Display the selected USN in the txtUsn field
            txtGrade.setText(grade);  // Display the selected Grade in the txtGrade field

        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            // Handle cases where the selected row data is invalid or missing
            JOptionPane.showMessageDialog(this, "Invalid data selection. Please select a valid row.", "Error", JOptionPane.ERROR_MESSAGE);  // Show error message

        } catch (Exception ex) {
            // Handle any other general exceptions, such as no row being selected
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  // Display the exception's message as an error
        }
        
    }//GEN-LAST:event_tblGradeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblGrade;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtUsn;
    // End of variables declaration//GEN-END:variables
}
