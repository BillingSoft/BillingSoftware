/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import static frames.ModifyCustomerFrame.setupAutoComplete;
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
public class ModifyOwnDetails extends javax.swing.JFrame {

    /**
     * Creates new form ModifyOwnDetails
     */
    public ModifyOwnDetails() {
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
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(Number) FROM owndetailstable");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(Number)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select Name from owndetailstable where Number = '"+z+"'");  
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
        jPanel2 = new javax.swing.JPanel();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modify Own Details - Automotive Solutions v1.0");

        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 660));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1160, 660));

        jLabel1.setText("Company Name");

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

        jButton2.setText("Back To Home Page");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel10.setText("Tin No.");

        jLabel11.setText("Tax Invoice Prefix");

        jLabel12.setText("Tax Invoice Suffix");

        jLabel13.setText("Retail Invoice Prefix");

        jLabel14.setText("Retail Invoice Suffix");

        jLabel15.setText("Proforma Invoice Prefix");

        jLabel16.setText("Proforma Invoice Suffix");

        jLabel17.setText("Purchase Order Prefix");

        jLabel18.setText("Purchase Order Suffix");

        jLabel19.setText("Password");

        jLabel20.setText("CSTNo");

        jButton4.setText("Get Details");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(67, 67, 67)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(jButton2))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel15)
                                .addComponent(jLabel17))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel13)
                            .addGap(58, 58, 58)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                .addComponent(jLabel11))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(67, 67, 67)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(76, 76, 76)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel19)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 219, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(136, 136, 136)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(129, Short.MAX_VALUE))
        );

        hello1();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        ModifyFrame obj1 = new ModifyFrame();
        obj1.setVisible(true);
        setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
        
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
        String m = "";
        String n = "";
        String o = "";
        String p = "";
        String q = "";
        String r = "";
        String s = "";
        String t = "";
        //int y;
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("Select * from owndetailstable where Name = '"+z+"'");  
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
            k = rs.getString("TIP");
            l = rs.getString("TIS");
            m = rs.getString("RIP");
            n = rs.getString("RIS");
            o = rs.getString("PIP");
            p = rs.getString("PIS");
            q = rs.getString("POP");
            r = rs.getString("POS");
            s = rs.getString("Password");
            t = rs.getString("CSTNo");
            
            
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
            jTextField11.setText(k);
            jTextField12.setText(l);
            jTextField13.setText(m);
            jTextField14.setText(n);
            jTextField15.setText(o);
            jTextField16.setText(p);
            jTextField17.setText(q);
            jTextField18.setText(r);
            jTextField19.setText(s);
            jTextField20.setText(t);
            
            
                
                
                
            //JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ea) {  
            ea.printStackTrace();  
            JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }        
        
    }

        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
        int len19=jTextField19.getText().length();
        int len20=jTextField20.getText().length();
        
        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Name</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                
        }
        else if(len2==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Address</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                
        }
        else if(len3==0){
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
        }
        else if(len10==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Tin Number</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                
        }
        else if(len19<=5){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Password</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                
        }
        else if(len20==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>CST Number</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                
        }
        else{
                
        String a = jTextField1.getText();
        jTextField1.setEditable(false);
        String b = jTextField2.getText();
        String c = jTextField3.getText();
        String d = jTextField4.getText();
        String e = jTextField5.getText();
        String f = jTextField6.getText();
        String g = jTextField7.getText();
        String h = jTextField8.getText();
        String i = jTextField9.getText();
        String j = jTextField10.getText();
        String k = jTextField11.getText();
        String l = jTextField12.getText();
        String m = jTextField13.getText();
        String n = jTextField14.getText();
        String o = jTextField15.getText();
        String p = jTextField16.getText();
        String q = jTextField17.getText();
        String r = jTextField18.getText();
        String s = jTextField19.getText();
        String t = jTextField20.getText();
        
        
        
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("UPDATE owndetailstable SET Address = '"+b+"',City = '"+c+"',State = '"+d+"',Country = '"+e+"',PinCode = '"+f+"',PhoneNumber = '"+g+"',Mobile = '"+h+"',Email = '"+i+"',TinNumber = '"+j+"',TIP = '"+k+"',TIS = '"+l+"',RIP = '"+m+"',RIS = '"+n+"',PIP = '"+o+"',PIS = '"+p+"',POP = '"+q+"',POS = '"+r+"',Password = '"+s+"',CSTNo = '"+t+"';");  
            statement.executeUpdate();  
                             JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);
   
        } catch (Exception ea) {  
            ea.printStackTrace(); 
            JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }        
        
        
        
        

        
        }
        

        
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ModifyOwnDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifyOwnDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifyOwnDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifyOwnDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifyOwnDetails().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
