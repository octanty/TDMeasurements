/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.fleetmanagement.client;

import cz.muni.fi.pa165.fleetmanagement.dto.EmployeeDTO;
import cz.muni.fi.pa165.fleetmanagement.enums.GenderEnum;
import cz.muni.fi.pa165.fleetmanagement.enums.UserClassEnum;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author xsvec3
 */
public class EmployeeForm extends javax.swing.JFrame {

    private Client client = ClientBuilder.newClient();
    private WebTarget webTarget = client.target("http://localhost:8084/pa165/webresources");
    private WebTarget resourceWebTarget;
    private Response response;
    private Invocation.Builder invocationBuilder;

    private void add() {
        String firstname = jTextField1.getText();
        String lastname = jTextField2.getText();
        String phonenumber = jTextField3.getText();
        String password = jTextField4.getText();
        UserClassEnum userclass = null;

        if ("MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.MANAGER;
        } else if ("CEO".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.CEO;
        } else if ("PRODUCT MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.PRODUCT_MANAGER;
        } else if ("SALES MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.SALES_MANAGER;
        }

        GenderEnum gender = null;
        if (jRadioButtonMale.isSelected()) {
            gender = GenderEnum.MALE;
        } else {
            gender = GenderEnum.FEMALE;
        }


        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(firstname);
        employeeDTO.setLastName(lastname);
        employeeDTO.setPhoneNumber(phonenumber);
        employeeDTO.setPassword(password);
        employeeDTO.setUserClass(userclass);
        employeeDTO.setGender(gender);

        // odoslem na REST

    }

    private void update(String employeeId) {
        String id = employeeId; 
        String firstname = jTextField1.getText();
        String lastname = jTextField2.getText();
        String phonenumber = jTextField3.getText();
        String password = jTextField4.getText();
        UserClassEnum userclass = null;

        if ("MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.MANAGER;
        } else if ("CEO".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.CEO;
        } else if ("PRODUCT MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.PRODUCT_MANAGER;
        } else if ("SALES MANAGER".equals(jComboBoxUserClass.getSelectedItem().toString())) {
            userclass = UserClassEnum.SALES_MANAGER;
        }

        GenderEnum gender = null;
        if (jRadioButtonMale.isSelected()) {
            gender = GenderEnum.MALE;
        } else {
            gender = GenderEnum.FEMALE;
        }


        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFirstName(firstname);
        employeeDTO.setLastName(lastname);
        employeeDTO.setPhoneNumber(phonenumber);
        employeeDTO.setPassword(password);
        employeeDTO.setUserClass(userclass);
        employeeDTO.setGender(gender);

        // odoslem na REST
    }

    private void getAll() {

//        resourceWebTarget = webTarget.path("employee");
//        invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
//        invocationBuilder.header("accept", "application/json");
//
//        response = invocationBuilder.get();
//        System.out.println(response.readEntity(String.class));
//        String json = response.readEntity(String.class);

        String json = "[{\"id\":2,\"password\":\"pass\",\"firstName\":\"firstname\",\"lastName\":\"last name\",\"userClass\":\"CEO\",\"email\":\"email\",\"gender\":null,\"phoneNumber\":\"908654999\"},{\"id\":2,\"password\":\"pass\",\"firstName\":\"firstname\",\"lastName\":\"last name\",\"userClass\":\"CEO\",\"email\":\"email\",\"gender\":null,\"phoneNumber\":\"908654999\"}]";

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<EmployeeDTO> employees = new LinkedList<EmployeeDTO>();
            employees = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, EmployeeDTO.class));

            String[] columnNames = {"id", "Firstname", "Lastname", "Phonenumber", "Password", "Userclass", "Gender"};
            DefaultTableModel model = new DefaultTableModel(null, columnNames);

            for (EmployeeDTO employeeDTO : employees) {
                Vector row = new Vector();
                row.add(employeeDTO.getId());
                row.add(employeeDTO.getFirstName());
                row.add(employeeDTO.getLastName());
                row.add(employeeDTO.getPhoneNumber());
                row.add(employeeDTO.getPassword());
                row.add(employeeDTO.getUserClass());
                row.add(employeeDTO.getGender());

                model.addRow(row);
            }
            jTable1.setModel(model);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates new form EmployeeForm
     */
    public EmployeeForm() {
        initComponents();

        getAll();
    }

    private void delete(String id) {
        resourceWebTarget = webTarget.path("employee/id/" + id);
        invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.header("accept", "application/json");
        response = invocationBuilder.delete();

        getAll();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxUserClass = new javax.swing.JComboBox();
        jRadioButtonMale = new javax.swing.JRadioButton();
        jRadioButtonFamale = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Firstname");

        jLabel2.setText("Lastname\n");

        jLabel3.setText("User class");

        jLabel4.setText("Gender");

        jLabel5.setText("Phonenumber");

        jLabel6.setText("Password");

        jLabel7.setText("Password confirm");

        jLabel8.setText("jLabel8");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextField3.setText("jTextField3");

        jTextField4.setText("jTextField4");

        jTextField5.setText("jTextField5");

        jTextField6.setText("jTextField6");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBoxUserClass.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MANAGER", "CEO", "PRODUCT MANAGER", "SALES MANAGER" }));

        jRadioButtonMale.setText("MALE");

        jRadioButtonFamale.setText("FEMALE");

        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                                    .addComponent(jTextField1)
                                    .addComponent(jComboBoxUserClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(206, 206, 206)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jRadioButtonMale)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButtonFamale)))
                        .addGap(396, 396, 396)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(427, 427, 427)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel1)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jComboBoxUserClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jRadioButtonFamale)
                    .addComponent(jRadioButtonMale)
                    .addComponent(jLabel8)
                    .addComponent(jLabel4)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        if (id != null) {
            delete(id);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        add();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
        if (id != null) {
            update(id);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString(); 
        
        String firstname = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
        String lastname = jTable1.getValueAt(jTable1.getSelectedRow(), 2).toString();
        String phonenumber = jTable1.getValueAt(jTable1.getSelectedRow(), 3).toString();
        UserClassEnum userclass = null;

        if ("MANAGER".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString())) {
            userclass = UserClassEnum.MANAGER;
        } else if ("CEO".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString())) {
            userclass = UserClassEnum.CEO;
        } else if ("PRODUCT MANAGER".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString())) {
            userclass = UserClassEnum.PRODUCT_MANAGER;
        } else if ("SALES MANAGER".equals(jTable1.getValueAt(jTable1.getSelectedRow(), 5).toString())) {
            userclass = UserClassEnum.SALES_MANAGER;
        }

        jTextField1.setText(firstname);
        jTextField2.setText(lastname);
        jTextField3.setText(phonenumber);
        
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBoxUserClass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButtonFamale;
    private javax.swing.JRadioButton jRadioButtonMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
