/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import static frames.ModifyProductFrame.setupAutoComplete;
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
public class ModifyDemandProductFrame extends javax.swing.JFrame {

    /**
     * Creates new form ModifyDemandProductFrame
     */
    public ModifyDemandProductFrame() {
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

    public void hello1(){


ArrayList<String> items = new ArrayList<String>();
        String a = "";
        
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(Number) FROM demandproduct");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(Number)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select Product from demandproduct where Number = '"+z+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs1 = statement1.executeQuery();
            if(rs1.next())
                a = rs1.getString("Product");
            
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

    
        
        
        
        
        
        
    private static boolean isAdjusting(JComboBox cbInput) {
        if (cbInput.getClientProperty("is_adjusting") instanceof Boolean) {
            return (Boolean) cbInput.getClientProperty("is_adjusting");
        }
        return false;
    }

    private static void setAdjusting(JComboBox cbInput, boolean adjusting) {
        cbInput.putClientProperty("is_adjusting", adjusting);
    }
   
    
    
    /*
    public static ImageIcon getImageIcon(String url,Map<String, ImageIcon> IMAGE_ICON_CACHE) {
  if (url == null) {
   return null;
  }
  if (IMAGE_ICON_CACHE.get(url) == null) {
   ImageIcon image = null;
   InputStream in =ResourceAgent.class.getResourceAsStream(url);
   if (in != null) {
    try {
     byte buffer[] = new byte[in.available()];
     for (int i = 0, n = in.available(); i < n; i++) {
      buffer[i] = (byte) in.read();
     }
     Toolkit toolkit = Toolkit.getDefaultToolkit();
     Image img = toolkit.createImage(buffer);
     image = new ImageIcon(img);
     in.close();
    } catch (IOException ex) {
     ex.printStackTrace();
     return null;
    }
   }
   if (image == null) {
    if (ClassLoader.getSystemResource(url) != null) {
     image = new ImageIcon(ClassLoader.getSystemResource(url));
    } else {
     image = new ImageIcon(url);
    }
   }
   if (image == null) {
    System.err.println("can't load image '" + url + "'");
   } else {
    IMAGE_ICON_CACHE.put(url, image);
   }
  }
  return (ImageIcon) IMAGE_ICON_CACHE.get(url);
 }

  */
  
    
    
    
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
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modify Demand Product Automotive Solutions v1.0");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 660));

        jLabel2.setText("Product Description");

        jLabel3.setText("Rate per Unit");

        jLabel4.setText("Unit");

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

        jButton3.setText("Get Details");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(jButton3)))))
                .addContainerGap(552, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(428, Short.MAX_VALUE))
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

        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Name</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len2==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Address</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len3==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>City</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else{

            String a = jTextField1.getText();
            String b = jTextField2.getText();
            String c = jTextField3.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("UPDATE demandproduct SET Rate = '"+b+"',Unit = '"+c+"' WHERE Product = '"+a+"';");

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
            //int y;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("Select * from demandproduct where Product = '"+z+"'");
                ResultSet rs = statement.executeQuery();
                if(rs.next())

                //example1 = rs.getInt("PinCode");
                //example1 = Integer.toString(y);
                a = rs.getString("Product");
                b = rs.getString("Rate");
                c = rs.getString("Unit");

                jTextField1.setText(a);
                jTextField1.setEditable(false);
                jTextField2.setText(b);
                jTextField3.setText(c);

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
            java.util.logging.Logger.getLogger(ModifyDemandProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyDemandProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyDemandProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyDemandProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyDemandProductFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
