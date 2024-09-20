package Admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class studentForm extends javax.swing.JInternalFrame {

    // Declare SQL-related variables for database operations
    String sql;              // SQL query string to be used in PreparedStatement
    ResultSet rs;           // ResultSet object to hold the data retrieved from the database
    Connection con;         // Connection object to manage the connection to the database
    PreparedStatement pst;  // PreparedStatement object to execute parameterized SQL queries

    // Declare updateTable as a class-level variable
    private DefaultTableModel updateTable;
    
    // Constructor for the gradeForm class
    public studentForm() {
        // Initialize the form components (this method is auto-generated by GUI builders of NetBeans)
        initComponents();

        // Get the toolkit for screen size information
        Toolkit toolkit = getToolkit();

        // Retrieve the screen dimensions
        Dimension size = toolkit.getScreenSize();

        // Set the location of the form to a specific position on the screen
        // Positioning formula centers the form based on the screen size
        // Adjusts the location so that the form appears slightly to the left and above the center of the screen
        setLocation(size.width / 17 - getWidth() / 18, size.height / 5 - getHeight() / 4);

        // Populate the course combo box with available courses from the Inventory table
        courseCbo();

        // Load and display data in the table from the Student table in the database
        DisplayTable();
    }
    
    private void tblHeader() {
        // Method to customize the header and column widths of the tblStudent table

        // Get the table header object
        JTableHeader thead = tblStudent.getTableHeader();

        // Set the text color of the header to blue
        thead.setForeground(Color.BLUE);

        // Set the font of the header to bold Tahoma with a size of 14
        thead.setFont(new Font("Tahoma", Font.BOLD, 14));

        // Customize the width of individual columns

        // Set the preferred width of the "Firstname" column (index 0) to 100 pixels
        TableColumn col = tblStudent.getColumnModel().getColumn(0);
        col.setPreferredWidth(100);

        // Set the preferred width of the "Lastname" column (index 1) to 100 pixels
        TableColumn col1 = tblStudent.getColumnModel().getColumn(1);
        col1.setPreferredWidth(100);

        // Set the preferred width of the "Year and Section" column (index 2) to 100 pixels
        TableColumn col2 = tblStudent.getColumnModel().getColumn(2);
        col2.setPreferredWidth(100);

        // Set the preferred width of the "Student Number" column (index 3) to 100 pixels
        TableColumn col3 = tblStudent.getColumnModel().getColumn(3);
        col3.setPreferredWidth(100);

        // Set the preferred width of the "Course" column (index 4) to 100 pixels
        TableColumn col4 = tblStudent.getColumnModel().getColumn(4);
        col4.setPreferredWidth(100);
    }

    private Map<Integer, Integer> rowToIdMap = new HashMap<>();
    // This map is used to store the mapping between the row index of the table and the corresponding student ID.
    // Key: The index of the row in the table (tblStudent).
    // Value: The ID of the student from the database.
    // This is useful for identifying and performing actions on specific rows in the table without displaying the ID column.

    private void DisplayTable() {
        // Method to display student data in the table (tblStudent)

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/summercamp";
        String username = "root";
        String password = "Java@2024";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql;

        try {
            // Establish a connection to the MySQL database
            con = DriverManager.getConnection(url, username, password);

            // SQL query to select all records from the Student table
            sql = "SELECT * FROM Student";

            // Prepare the statement for execution
            pst = con.prepareStatement(sql);

            // Execute the query and store the result set
            rs = pst.executeQuery();

            // Initialize the table model (updateTable) if it is not already initialized
            if (updateTable == null) {
                updateTable = (DefaultTableModel) tblStudent.getModel();
            }

            // Clear any existing rows in the table model
            updateTable.setRowCount(0);

            // Clear the row-to-ID map to reset it for fresh mapping
            rowToIdMap.clear();

            // Variable to track the row index
            int rowIndex = 0;

            // Loop through the result set and add rows to the table model
            while (rs.next()) {
                int id = rs.getInt("id");            // Retrieve the ID of the student
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String yas = rs.getString("yas");    // Year and Section
                String usn = rs.getString("usn");
                String course = rs.getString("course");

                // Add a new row to the table model with 5 columns (excluding the ID)
                updateTable.addRow(new Object[]{firstname, lastname, yas, usn, course});

                // Store the ID in the map, associating it with the current row index
                rowToIdMap.put(rowIndex++, id);
            }

        } catch (Exception e) {
            // Handle any exceptions by printing the stack trace
            e.printStackTrace();
        } finally {
            // Close the database resources (ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                // Handle any exceptions during resource closing
                e.printStackTrace();
            }
        }
    }

    public void courseCbo() {
        // Method to populate the course combo box from the Inventory table in the database

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/summercamp";
        String username = "root";
        String password = "Java@2024";

        try {
            // Establishing the connection to the MySQL database
            con = DriverManager.getConnection(url, username, password);

            // SQL query to fetch all course names from the Inventory table
            sql = "SELECT course FROM inventory";

            // Prepare and execute the SQL query
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            // Clear any existing items in the combo box before adding new ones
            cboCourse.removeAllItems();

            // Iterate through the result set and add each course to the combo box
            while (rs.next()) {
                String course = rs.getString("course");
                cboCourse.addItem(course); // Add each course to the combo box
            }

        } catch (Exception e) {
            // Handle any exceptions and print the stack trace
            e.printStackTrace();
        } finally {
            // Close the database resources (ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                // Catch and handle any exceptions during resource closing
                e.printStackTrace();
            }
        }
    }

    private void clearFields() {
        // Clear the text fields by setting them to empty strings
        txtFirstname.setText("");    // Clear the firstname field
        txtLastname.setText("");     // Clear the lastname field
        txtYearSection.setText("");  // Clear the year and section field
        txtUsn.setText("");          // Clear the student number field

        // Reset the combo box selection to no selection (-1 indicates no item selected)
        cboCourse.setSelectedIndex(-1); 

        // Optionally, make the combo box editable (based on your requirements)
        cboCourse.setEditable(true); // Set to true if the combo box should be editable, or false if not
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
        txtFirstname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtYearSection = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboCourse = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        txtUsn = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Student");

        jPanel1.setBackground(new java.awt.Color(253, 177, 4));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("FIRSTNAME");

        txtFirstname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtFirstname.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/course.gif"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });

        btnCreate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnCreate.setText("CREATE");
        btnCreate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateMouseClicked(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDeleteMouseClicked(evt);
            }
        });

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Firstname", "Lastname", "Year and Section", "Student Number", "Course"
            }
        ));
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudent);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1051, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 144, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel3.setText("LASTNAME");

        txtLastname.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtLastname.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel5.setText("Year & Section");

        txtYearSection.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtYearSection.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel6.setText("Course");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel7.setText("Student Number");

        txtUsn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtUsn.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(158, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtYearSection, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(60, 60, 60)
                                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(60, 60, 60)
                                .addComponent(cboCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(60, 60, 60)
                                .addComponent(txtUsn, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(257, 257, 257))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCreate)
                        .addGap(134, 134, 134)
                        .addComponent(btnUpdate)
                        .addGap(131, 131, 131)
                        .addComponent(btnDelete)
                        .addGap(147, 147, 147))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(396, 396, 396)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFirstname))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtYearSection, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsn, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(cboCourse))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDelete)
                    .addComponent(btnUpdate)
                    .addComponent(btnCreate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateMouseClicked
        
        // Event handler for the create button click

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/summercamp";
        String username = "root";
        String password = "Java@2024";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql;

        try {
            // Retrieve input values from the form fields
            String firstname = txtFirstname.getText().trim();
            String lastname = txtLastname.getText().trim();
            String yas = txtYearSection.getText().trim();  // Year and Section
            String usn = txtUsn.getText().trim();
            String course = cboCourse.getSelectedItem().toString();

            // Establishing a connection to the database
            con = DriverManager.getConnection(url, username, password);

            // SQL query to check available slots for the selected course from the Inventory table
            sql = "SELECT slot FROM Inventory WHERE course = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, course);
            rs = pst.executeQuery();

            // Check if the course exists and retrieve the available slots
            if (rs.next()) {
                int slotsAvailable = rs.getInt("slot");

                if (slotsAvailable > 0) {
                    // If slots are available, proceed to insert the student record
                    sql = "INSERT INTO Student (firstname, lastname, yas, usn, course) VALUES (?, ?, ?, ?, ?)";

                    // Prepare the SQL insert statement with student data
                    pst = con.prepareStatement(sql);
                    pst.setString(1, firstname);
                    pst.setString(2, lastname);
                    pst.setString(3, yas);
                    pst.setString(4, usn);
                    pst.setString(5, course);

                    // Execute the update to insert the student record
                    int rowCount = pst.executeUpdate();

                    if (rowCount > 0) {
                        // If the student was inserted, decrease the slot count by 1 in the Inventory table
                        sql = "UPDATE Inventory SET slot = slot - 1 WHERE course = ?";
                        pst = con.prepareStatement(sql);
                        pst.setString(1, course);
                        pst.executeUpdate();

                        // Notify the user of a successful insertion
                        JOptionPane.showMessageDialog(null, "Student record inserted successfully!");

                        // Clear the input fields after the successful insert
                        clearFields();

                        // Refresh the table to display the new record
                        DisplayTable();
                    } else {
                        // If the insert failed, show an error message
                        JOptionPane.showMessageDialog(null, "Failed to insert student record.");
                    }
                } else {
                    // If no slots are available for the selected course, show a warning message
                    JOptionPane.showMessageDialog(null, "No slots available for this course.");
                }
            } else {
                // If the course is not found in the Inventory table, show an error message
                JOptionPane.showMessageDialog(null, "Course not found in the inventory.");
            }

        } catch (Exception e) {
            // Catch and handle any exceptions, showing an error message
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        } finally {
            // Close the database resources (ResultSet, PreparedStatement, Connection)
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_btnCreateMouseClicked

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        
        // Event handler for when a row in the tblStudent is clicked

        // Get the table model from tblStudent to access its data
        DefaultTableModel updateTable = (DefaultTableModel) tblStudent.getModel();
        int row = tblStudent.getSelectedRow(); // Get the selected row index

        // Check if a row is selected
        if (row == -1) {
            // If no row is selected, show a message and exit
            JOptionPane.showMessageDialog(rootPane, "Please select a record to edit.");
            return;
        }

        // Populate the input fields with the selected row's data
        txtFirstname.setText(updateTable.getValueAt(row, 0).toString()); // Firstname is stored in column 0
        txtLastname.setText(updateTable.getValueAt(row, 1).toString());  // Lastname is stored in column 1
        txtYearSection.setText(updateTable.getValueAt(row, 2).toString()); // Year and Section is stored in column 2
        txtUsn.setText(updateTable.getValueAt(row, 3).toString()); // Student Number is stored in column 3
        cboCourse.setSelectedItem(updateTable.getValueAt(row, 4).toString()); // Course is stored in column 4

        // Make the input fields editable to allow updates
        txtFirstname.setEditable(true);
        txtLastname.setEditable(true);
        txtYearSection.setEditable(true);
        txtUsn.setEditable(true);
        cboCourse.setEditable(true); // ComboBox allows selecting new course

        // Optionally, set focus on the firstname field after row selection
        txtFirstname.requestFocus();
        
    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        
        // Event handler for the update button click

        // Check if a row is selected in the table
        int row = tblStudent.getSelectedRow();
        if (row == -1) {
            // If no row is selected, show an alert and exit
            JOptionPane.showMessageDialog(rootPane, "Please select a record to update.");
            return;
        }

        // Retrieve updated data from the input fields
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String yearSection = txtYearSection.getText();
        String usn = txtUsn.getText();
        String course = cboCourse.getSelectedItem().toString();

        // Retrieve the student ID from the map using the selected row index
        int id = rowToIdMap.get(row);

        // Ask for confirmation before proceeding with the update
        int response = JOptionPane.showConfirmDialog(
            rootPane,
            "Are you sure you want to update this record?",
            "Confirm Update",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );

        if (response == JOptionPane.NO_OPTION) {
            // If the user selects NO, exit the method
            return;
        }

        // Establish a MySQL database connection
        Connection con = null;
        PreparedStatement ps = null;

        try {
            // Establish a connection to the MySQL database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/summercamp", "root", "Java@2024");

            // SQL query to update the student record in the database based on the selected ID
            String sql = "UPDATE Student SET firstname=?, lastname=?, yas=?, usn=?, course=? WHERE id=?";

            // Prepare the statement with the new data from input fields
            ps = con.prepareStatement(sql);
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setString(3, yearSection); // "yas" corresponds to yearSection
            ps.setString(4, usn);
            ps.setString(5, course);
            ps.setInt(6, id); // Use the student ID to locate the record to update

            // Execute the update query
            int result = ps.executeUpdate();
            if (result > 0) {
                // If the update is successful, notify the user
                JOptionPane.showMessageDialog(rootPane, "Record updated successfully!");

                // Refresh the table to show the updated data
                DisplayTable();

                // Clear the input fields after a successful update
                clearFields();
            } else {
                // If the update fails, show an error message
                JOptionPane.showMessageDialog(rootPane, "Update failed. Please try again.");
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions and show the error message
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        } finally {
            // Close the database resources in the finally block to ensure they are always closed
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void btnDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteMouseClicked
        
        // Event handler for the delete button click

        // Check if a row is selected in the table
        int row = tblStudent.getSelectedRow();
        if (row == -1) {
            // If no row is selected, show an alert to the user and exit
            JOptionPane.showMessageDialog(rootPane, "Please select a record to delete.");
            return;
        }

        // Confirm deletion from the user
        int confirm = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to delete this record?", "Delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            // If the user cancels, exit the method
            return;
        }

        // Retrieve the student ID from the map using the selected row index
        int id = rowToIdMap.get(row);

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String course = "";

        try {
            // Establish a connection to the database
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/summercamp", "root", "Java@2024");

            // Get the course of the student being deleted
            String getCourseSql = "SELECT course FROM Student WHERE id=?";
            ps = con.prepareStatement(getCourseSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Store the course of the student
                course = rs.getString("course");
            }

            // SQL query to delete the student record based on the selected ID
            String sql = "DELETE FROM Student WHERE id=?";

            // Prepare the statement with the student ID
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            // Execute the delete operation
            int result = ps.executeUpdate();
            if (result > 0) {
                // If the delete was successful, show a confirmation message
                JOptionPane.showMessageDialog(rootPane, "Record deleted successfully!");

                // Increase the slot count in the Inventory table for the corresponding course
                String updateSlotSql = "UPDATE Inventory SET slot = slot + 1 WHERE course = ?";
                ps = con.prepareStatement(updateSlotSql);
                ps.setString(1, course);
                ps.executeUpdate();

                // Refresh the table to reflect the deletion
                DisplayTable();

                // Clear the input fields
                clearFields(); 
            } else {
                // If delete failed, show an error message
                JOptionPane.showMessageDialog(rootPane, "Delete failed. Please try again.");
            }
        } catch (SQLException ex) {
            // Handle any SQL exceptions and display the error message
            ex.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
        } finally {
            // Close the database resources in the finally block to ensure they are always closed
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_btnDeleteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cboCourse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtUsn;
    private javax.swing.JTextField txtYearSection;
    // End of variables declaration//GEN-END:variables
}