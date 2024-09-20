package Admin;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class recordForm extends javax.swing.JInternalFrame {
    
    // Constructor for the gradeForm class
    public recordForm() {
        // Initialize the form components (this method is auto-generated by GUI builders of NetBeans)
        initComponents();
        
        // Get the toolkit for screen size information
        Toolkit toolkit = getToolkit();
        
        // Retrieve the screen dimensions
        Dimension size = toolkit.getScreenSize();
        
        // Set the location of the form to a specific position on the screen
        // Positioning formula centers the form based on the screen size
        // Adjusts the location so that the form appears slightly to the left and above the center of the screen
        setLocation(size.width/15-getWidth()/16,size.height/5 - getHeight()/4);
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
        jLabel2 = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblGraded = new javax.swing.JTable();
        btnDownloadCSV = new javax.swing.JButton();

        setClosable(true);
        setTitle("Record");

        jPanel1.setBackground(new java.awt.Color(253, 177, 4));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/course.gif"))); // NOI18N
        jLabel2.setText("jLabel2");

        btnGenerate.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnGenerate.setText("GENERATE");
        btnGenerate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGenerateMouseClicked(evt);
            }
        });

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Firstname", "Lastname", "Year and Section", "Student Number", "Course"
            }
        ));
        jScrollPane2.setViewportView(tblStudent);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Students", jPanel3);

        tblGraded.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Firstname", "Lastname", "Year and Section", "Student Number", "Course", "Grade"
            }
        ));
        jScrollPane3.setViewportView(tblGraded);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1056, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Graded", jPanel4);

        btnDownloadCSV.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnDownloadCSV.setText("DOWNLOAD CSV");
        btnDownloadCSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDownloadCSVMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDownloadCSV)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerate)
                    .addComponent(btnDownloadCSV))
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
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerateMouseClicked
        
        // When the "Generate" button is clicked, this method is triggered.
    
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/summercamp";  // Database URL
        String user = "root";  // Database username
        String password = "Java@2024";  // Database password

        // SQL queries to fetch student data and grade data
        String studentQuery = "SELECT firstname, lastname, yas, usn, course FROM student";  // Query to fetch student records
        String gradeQuery = "SELECT firstname, lastname, yas, usn, course, grade FROM grade";  // Query to fetch graded records
        
        // Try-with-resources block ensures that resources are automatically closed after use
        // Connection, PreparedStatement, and ResultSet are declared within the parentheses and will be closed automatically
        try (
            // Establishing the database connection
            Connection con = DriverManager.getConnection(url, user, password);
            // Preparing the SQL statements for execution
            PreparedStatement studentStmt = con.prepareStatement(studentQuery);
            PreparedStatement gradeStmt = con.prepareStatement(gradeQuery);
            // Executing the queries and getting the result sets
            ResultSet studentRs = studentStmt.executeQuery();
            ResultSet gradeRs = gradeStmt.executeQuery()
        ) {
            // Populate the student table with the data from the student query
            tblStudent.setModel(DbUtils.resultSetToTableModel(studentRs));

            // Populate the graded table with the data from the grade query
            tblGraded.setModel(DbUtils.resultSetToTableModel(gradeRs));

            // Customize the column headers for the student table
            tblStudent.getColumnModel().getColumn(0).setHeaderValue("Firstname");
            tblStudent.getColumnModel().getColumn(1).setHeaderValue("Lastname");
            tblStudent.getColumnModel().getColumn(2).setHeaderValue("Year and Section");
            tblStudent.getColumnModel().getColumn(3).setHeaderValue("Student Number");
            tblStudent.getColumnModel().getColumn(4).setHeaderValue("Course");

            // Customize the column headers for the graded table
            tblGraded.getColumnModel().getColumn(0).setHeaderValue("Firstname");
            tblGraded.getColumnModel().getColumn(1).setHeaderValue("Lastname");
            tblGraded.getColumnModel().getColumn(2).setHeaderValue("Year and Section");
            tblGraded.getColumnModel().getColumn(3).setHeaderValue("Student Number");
            tblGraded.getColumnModel().getColumn(4).setHeaderValue("Course");
            tblGraded.getColumnModel().getColumn(5).setHeaderValue("Grade");

            // Repaint the tables to ensure the new headers are applied visually
            tblStudent.getTableHeader().repaint();
            tblGraded.getTableHeader().repaint();

        } catch (SQLException ex) {
            // Handle SQL exceptions (e.g., connection issues or query failures)
            JOptionPane.showMessageDialog(this, "Error retrieving data from database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnGenerateMouseClicked

    private void btnDownloadCSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDownloadCSVMouseClicked
        
        // Triggered when the "Download CSV" button is clicked.

        // Step 1: Prompt the user to choose a location to save the CSV file
        JFileChooser fileChooser = new JFileChooser();  // File chooser for selecting the save location
        fileChooser.setDialogTitle("Save CSV File");  // Set the dialog title for better user experience

        // Step 2: Set a default file name for the exported CSV file
        fileChooser.setSelectedFile(new File("exported_data.csv"));  // Suggest a default file name

        // Step 3: Open the file chooser dialog
        int userSelection = fileChooser.showSaveDialog(this);  // Show the save dialog and capture user selection

        // Step 4: If the user approves the save action
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Retrieve the file chosen by the user
            File fileToSave = fileChooser.getSelectedFile();

            // Step 5: Try writing the data to the file
            try (FileWriter writer = new FileWriter(fileToSave)) {

                // Step 6: Write the headers for the Student table
                writer.append("Firstname,Lastname,Year and Section,Student Number,Course\n");

                // Step 7: Loop through the Student table rows and write each row's data
                for (int row = 0; row < tblStudent.getRowCount(); row++) {
                    for (int col = 0; col < tblStudent.getColumnCount(); col++) {
                        writer.append(tblStudent.getValueAt(row, col).toString());  // Get the value from the table
                        if (col < tblStudent.getColumnCount() - 1) {
                            writer.append(",");  // Add a comma between columns
                        }
                    }
                    writer.append("\n");  // Move to the next line after each row
                }

                // Add an empty line between the two tables for better formatting
                writer.append("\n");

                // Step 8: Write the headers for the Graded table
                writer.append("Firstname,Lastname,Year and Section,Student Number,Course,Grade\n");

                // Step 9: Loop through the Graded table rows and write each row's data
                for (int row = 0; row < tblGraded.getRowCount(); row++) {
                    for (int col = 0; col < tblGraded.getColumnCount(); col++) {
                        writer.append(tblGraded.getValueAt(row, col).toString());  // Get the value from the table
                        if (col < tblGraded.getColumnCount() - 1) {
                            writer.append(",");  // Add a comma between columns
                        }
                    }
                    writer.append("\n");  // Move to the next line after each row
                }

                // Step 10: Notify the user that the export was successful
                JOptionPane.showMessageDialog(this, "Data exported successfully!", "Export Successful", JOptionPane.INFORMATION_MESSAGE);

            } catch (IOException ex) {
                // Step 11: Handle any errors that occur during file writing
                JOptionPane.showMessageDialog(this, "Error exporting data: " + ex.getMessage(), "Export Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_btnDownloadCSVMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDownloadCSV;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblGraded;
    private javax.swing.JTable tblStudent;
    // End of variables declaration//GEN-END:variables
}