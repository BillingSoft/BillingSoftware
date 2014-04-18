/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsoftware;

/**
 *
 * @author Rishabh
 */
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;


public class Test {
 
    public static void main(String[] args) throws Exception {
        
        
        ArrayList<String> items = new ArrayList<String>();
        String a = "";
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(InvoiceNo) FROM proformatable");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(InvoiceNo)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select Sender from proformatable where InvoiceNo = '"+z+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs1 = statement1.executeQuery();
            if(rs1.next())
                a = rs1.getString("Sender");
            
                items.add(a);
               
        }
        catch(Exception ea){
            ea.printStackTrace();
        }
        }}
            catch (Exception ea) {  
            ea.printStackTrace();  
        }

        
        
        
        
        
        
        
        
        
//        Locale[] locales = Locale.getAvailableLocales();
//        for (int i = 0; i < locales.length; i++) {
//            String item = locales[i].getDisplayName();
//            items.add(item);
//        }
        JTextField jTextField3 = new JTextField();
        setupAutoComplete(jTextField3, items);
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
    
    
    
}
