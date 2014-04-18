/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Rishabh
 */
public class ModifySupplierFrame extends javax.swing.JFrame {

    /**
     * Creates new form ModifySupplierFrame
     */
    public ModifySupplierFrame() {
        initComponents();
        setIcon();
    }
    
    
    private void setIcon() {
        
        try
        {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("logo.png")));
            //setIconImage(Toolkit.getDefaultToolkit().createImage("C:\\Users\\Rishabh\\Desktop\\K B Label\\logo.png"));
        }
        catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,e.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }
    }

    
        
    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }
   
    
    public static void setupAutoComplete(final JTextField jTextField3, final ArrayList<String> items) {
        final DefaultComboBoxModel model = new DefaultComboBoxModel();
        
        final Map<String,ImageIcon> IMAGE_ICON_CACHE = new HashMap<String, ImageIcon>();
        
        final JComboBox cbInput = new JComboBox(model) {
            public Dimension getPreferredSize() {
                return new Dimension(super.getPreferredSize().width, 0);
            }
        };
        setAdjusting(cbInput, false);
        for (String item : items) {
            model.addElement(item);
        }
        cbInput.setSelectedItem(null);
        cbInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdjusting(cbInput)) {
                    if (cbInput.getSelectedItem() != null) {
                        jTextField3.setText(cbInput.getSelectedItem().toString());
                    }
                }
            }
        });
        
        cbInput.setRenderer(new DefaultListCellRenderer(){
         public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
          super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
          if(value != null){
           this.setText(value.toString());
           String url = value.toString().replaceAll(" ", "_") + ".png";
           
           
           
           
//           ImageIcon icon = getImageIcon(url, IMAGE_ICON_CACHE);
  //         if(icon != null){
    //        this.setIcon(icon);
      //     }
          }
          return this;
         }
        });

        jTextField3.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                setAdjusting(cbInput, true);
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    if (cbInput.isPopupVisible()) {
                        e.setKeyCode(KeyEvent.VK_ENTER);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                    e.setSource(cbInput);
                    cbInput.dispatchEvent(e);
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        jTextField3.setText(cbInput.getSelectedItem().toString());
                        cbInput.setPopupVisible(false);
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    cbInput.setPopupVisible(false);
                }
                setAdjusting(cbInput, false);
            }
        });
        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateList();
            }

            public void removeUpdate(DocumentEvent e) {
                updateList();
            }

            public void changedUpdate(DocumentEvent e) {
                updateList();
            }

            private void updateList() {
                setAdjusting(cbInput, true);
                model.removeAllElements();
                String input = jTextField3.getText();
                if (!input.isEmpty()) {
                    for (String item : items) {
                        if (item.toLowerCase().startsWith(input.toLowerCase())) {
                            model.addElement(item);
                        }
                    }
                }
                cbInput.hidePopup();
                cbInput.setPopupVisible(model.getSize() > 0);
                setAdjusting(cbInput, false);
            }
        });
        jTextField3.setLayout(new BorderLayout());
        jTextField3.add(cbInput, BorderLayout.SOUTH);

    }
    
    
    public void hello1(){


ArrayList<String> items = new ArrayList<String>();
        String a = "";
        
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(Number) FROM suppliertable");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(Number)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select Name from suppliertable where Number = '"+z+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs1 = statement1.executeQuery();
            if(rs1.next())
                a = rs1.getString("Name");
            
                items.add(a);
               
        }
        catch(Exception ea){
            ea.printStackTrace();
            JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }
        }}
            catch (Exception ea) {  
            ea.printStackTrace();
            JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }
        setupAutoComplete(jTextField1, items);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modify Supplier Details v1.0 Automotive Solutions");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 660));

        jLabel1.setText("Name");

        jLabel2.setText("Address");

        jLabel3.setText("City");

        jLabel4.setText("State");

        jLabel5.setText("Country");

        jLabel6.setText("Pin Code");

        jLabel7.setText("Phone Number");

        jLabel8.setText("Mobile");

        jLabel9.setText("Email");

        jButton1.setText("Store In Database");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Tin No.");

        jButton3.setText("Get Details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel12.setText("CST No.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(58, 58, 58)
                                .addComponent(jButton3)))))
                .addContainerGap(594, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(273, Short.MAX_VALUE))
        );

        hello1();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int len1=jTextField1.getText().length();
        int len2=jTextField2.getText().length();
        int len3=jTextField3.getText().length();
        int len4=jTextField4.getText().length();
        int len5=jTextField5.getText().length();
        int len6=jTextField6.getText().length();
        int len7=jTextField7.getText().length();
        int len8=jTextField8.getText().length();
        int len10=jTextField10.getText().length();
        int len12=jTextField12.getText().length();

        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Name</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len2==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Address</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len3==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>City</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len4==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>State</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len5==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Country</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len6==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Pin Code</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len7==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Phone Number</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len8==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Mobile</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(!(jTextField9.getText().toString().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"))){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Email</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len10==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Tin Number</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len12==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>CST Number</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else{

            String a = jTextField1.getText();
            String b = jTextField2.getText();
            String c = jTextField3.getText();
            String d = jTextField4.getText();
            String e = jTextField5.getText();
            String f = jTextField6.getText();
            String g = jTextField7.getText();
            String h = jTextField8.getText();
            String i = jTextField9.getText();
            String j = jTextField10.getText();
            String l = jTextField12.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("UPDATE suppliertable SET Address = '"+b+"',City = '"+c+"',State = '"+d+"',Country = '"+e+"',PinCode = '"+f+"',PhoneNumber = '"+g+"',Mobile = '"+h+"',Email = '"+i+"',TinNumber = '"+j+"',CSTNo = '"+l+"' WHERE Name = '"+a+"';");

                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ea) {
                ea.printStackTrace();
                JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        ModifyFrame obj1 = new ModifyFrame();
        obj1.setVisible(true);
        setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int len1=jTextField1.getText().length();
        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Name</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else{

            String z = jTextField1.getText();
            String a = "";
            String b = "";
            String c = "";
            String d = "";
            String e = "";
            String f = "";
            String g = "";
            String h = "";
            String i = "";
            String j = "";
            String k = "";
            String l = "";
            //int y;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("Select * from suppliertable where Name = '"+z+"'");
                //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)
                ResultSet rs = statement.executeQuery();
                if(rs.next())

                //example1 = rs.getInt("PinCode");
                //example1 = Integer.toString(y);
                a = rs.getString("Name");
                b = rs.getString("Address");
                c = rs.getString("City");
                d = rs.getString("State");
                e = rs.getString("Country");
                f = rs.getString("PinCode");
                g = rs.getString("PhoneNumber");
                h = rs.getString("Mobile");
                i = rs.getString("Email");
                j = rs.getString("TinNumber");
                l = rs.getString("CSTNo");

                jTextField1.setText(a);
                jTextField1.setEditable(false);
                jTextField2.setText(b);
                jTextField3.setText(c);
                jTextField4.setText(d);
                jTextField5.setText(e);
                jTextField6.setText(f);
                jTextField7.setText(g);
                jTextField8.setText(h);
                jTextField9.setText(i);
                jTextField10.setText(j);
                jTextField12.setText(l);

                //JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ea) {
                ea.printStackTrace();
                JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(ModifySupplierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifySupplierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifySupplierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifySupplierFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifySupplierFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
