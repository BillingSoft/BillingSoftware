/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;


import billingsoftware.Java2sAutoComboBox;
import billingsoftware.PrintPDF;
import billingsoftware.RowBean;
import billingsoftware.Test;
import static billingsoftware.hello.createPDF;
import billingsoftware.numToWord;
import freemarker.template.Configuration;
import freemarker.template.Template;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList; 
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.JTextComponent;
import net.proteanit.sql.DbUtils;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Rishabh
 */
public class ProformaInvFrame extends javax.swing.JFrame {
    //public ResultSet rs;
    //public int a;
    /**
     * Creates new form ProformaInvFrame
     */
    public ProformaInvFrame() {
        initComponents();
        setupAutoComplete();
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

    
    
    private void setupAutoComplete() {
    	TableColumn column = jTable1.getColumnModel().getColumn(0);
    	  //Set up the editor for the sport cells.
        jTable1.setRowHeight(jTable1.getRowHeight()+10);
        ArrayList<String> listSomeString = new ArrayList<String>();        
        
        
        
        
        ArrayList<String> rate = new ArrayList<String>();
        ArrayList<String> unit = new ArrayList<String>();
        String a = "";
        String b = "";
        String c = "";
        
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(Number) FROM producttable");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(Number)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select * from producttable where Number = '"+z+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs1 = statement1.executeQuery();
            if(rs1.next())
                a = rs1.getString("Product");
                //b = rs1.getString("Rate");
                //c = rs1.getString("Unit");
            
                listSomeString.add(a);
                rate.add(b);
                unit.add(c);
                
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

        
        
        
        
        
        
        
        
        
        
        Java2sAutoComboBox comboBox = new Java2sAutoComboBox(listSomeString);
        comboBox.setDataList(listSomeString);
        comboBox.setStrict(false);
        comboBox.setMaximumRowCount(3);
        //column.setCellEditor(new DefaultCellEditor(comboBox));
        
        
        
        
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
        
        defaultCellEditor.addCellEditorListener(new CellEditorListener() {
			
			@Override
			public void editingStopped(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Editing Done ");
				DefaultCellEditor source = (DefaultCellEditor)arg0.getSource();
				JComboBox component2 = (JComboBox)source.getComponent();
				System.out.println(" value " + component2.getSelectedIndex() + " ** " + component2.getSelectedItem());
                                String a = ""+component2.getSelectedItem().toString();
                                String b = "";
                                String c = "";
                                int d =  jTable1.getSelectedRow();
                                
                                
                                try {  
                                        Class.forName("com.mysql.jdbc.Driver");  
                                        Connection connection1 = DriverManager.getConnection(  
                                          "jdbc:mysql://localhost:3306/mydb", "root", "");  
                                        PreparedStatement statement1 = connection1.prepareStatement("Select Rate,Unit from producttable where Product = '"+a+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
                                        ResultSet rs1 = statement1.executeQuery();
                                        if(rs1.next()){
                                           b = rs1.getString("Rate");
                                           c = rs1.getString("Unit");
                                           jTable1.setValueAt(b, d, 2);
                                           jTable1.setValueAt(c, d, 3);}
                                           
                                           
                                           
                                           
                                           
                                }
                                catch(Exception ea){
                                    ea.printStackTrace();
                                    JOptionPane.showMessageDialog(null,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

                                }
                                
                                
                                
                                
        
			}
			
			@Override
			public void editingCanceled(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				
			}

                
		});
        column.setCellEditor(defaultCellEditor);
        
        
        
        
        
        //Set up tool tips for the sport cells.
//        DefaultTableCellRenderer renderer;
  //      renderer = new DefaultTableCellRenderer();
    //    renderer.setToolTipText("Click for combo box");
      //  column.setCellRenderer(renderer);
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
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Create Proforma Invoice - Automotive Solutions v1.0");

        jPanel1.setPreferredSize(new java.awt.Dimension(1160, 660));

        jPanel2.setBackground(new java.awt.Color(175, 255, 164));
        jPanel2.setPreferredSize(new java.awt.Dimension(1160, 660));

        jLabel1.setText("Invoice No.");

        jLabel2.setText("Dated");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String date = sdf.format(new Date());
        jTextField2.setText(date);

        jLabel3.setText("Sender");

        jLabel4.setText("Receiver");

        jLabel5.setText("Mode/Terms of Payment");

        jLabel6.setText("Delivery Note");

        jLabel7.setText("Supplier's Reference");

        jLabel8.setText("Other Reference(s)");

        jLabel10.setText("Buyer's Order No. and Date");

        jLabel9.setText("Despatch Document No. and Date");

        jLabel11.setText("Despatched Through");

        jLabel12.setText("Destination");

        jLabel13.setText("Terms Of Delivery");

        jLabel14.setText("Other Information");

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Store In Database");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {"", null, "", null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Description", "Quantity", "Rate", "per", "Amount"
            }
        ));
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setUpdateSelectionOnSort(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(500);
        jTable1.getColumnModel().getColumn(1).setResizable(false);
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(2).setResizable(false);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(3).setResizable(false);
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTable1.getColumnModel().getColumn(4).setResizable(false);
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(253);
        jTable1.getModel().setValueAt("Total", 16, 0);
        jTable1.getModel().setValueAt(" ", 15, 0);
        jTable1.getModel().setValueAt(" ", 14, 0);
        jTable1.getModel().setValueAt(" ", 15, 1);
        jTable1.getModel().setValueAt(" ", 14, 1);
        jTable1.getModel().setValueAt(" ", 15, 2);
        jTable1.getModel().setValueAt(" ", 14, 2);
        jTable1.getModel().setValueAt(" ", 15, 3);
        jTable1.getModel().setValueAt(" ", 14, 3);
        jTable1.getModel().setValueAt(" ", 13, 1);
        jTable1.getModel().setValueAt(" ", 16, 2);
        jTable1.getModel().setValueAt(" ", 16, 3);
        jTable1.getModel().setValueAt(" ", 12, 1);
        jTable1.getModel().setValueAt(" ", 12, 2);
        jTable1.getModel().setValueAt(" ", 12, 3);
        jTable1.getModel().setValueAt("VAT", 13, 0);
        jTable1.getModel().setValueAt("Sub-Total", 12, 0);
        jTable1.getModel().setValueAt("%", 13, 3);
        jTable1.getModel().setValueAt("12.5", 13, 2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton2.setText("Create PDF");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("Mail PDF");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Print PDF");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1)
                            .addComponent(jTextField3)
                            .addComponent(jTextField5)
                            .addComponent(jTextField7)
                            .addComponent(jTextField13)
                            .addComponent(jTextField15))
                        .addGap(79, 79, 79)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(40, 40, 40)
                                                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(97, 97, 97))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(103, 103, 103)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(95, 95, 95)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(97, 97, 97))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton2)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "root", "");
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(InvoiceNo) FROM proformatable");

            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            {abc = rs.getInt("MAX(InvoiceNo)");
            }
            abc++;
            jTextField1.setText(""+abc);

        }
        catch (Exception ea) {
            ea.printStackTrace();
        }
        hello();
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

    public void hello(){


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
        setupAutoComplete(jTextField3, items);
        }

    public void hello1(){


ArrayList<String> items = new ArrayList<String>();
        String a = "";
        
        
        try { 
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement = connection.prepareStatement("SELECT MAX(Number) FROM customertable");  
            
            ResultSet rs = statement.executeQuery();
            int abc = 0;
            while(rs.next())
            abc = rs.getInt("MAX(Number)");
            
        
        
        for(int z=1; z<=abc;z++){
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
            Connection connection1 = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");  
            PreparedStatement statement1 = connection1.prepareStatement("Select Name from customertable where Number = '"+z+"'");  
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
        setupAutoComplete(jTextField4, items);
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
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
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
        String description = "";
        String quantity = "";
        String rate = "";
        String per = "";
        String amount = "";
        String lastdescription = "";
        String lastquantity = "";
        String lastrate = "";
        String lastper = "";
        String lastamount = "";
        String a2 = "";
        String b2 = "";
        String c2 = "";
        String d2 = "";
        String e2 = "";
        String f2 = "";
        String g2 = "";
        String h2 = "";
        String i2 = "";
        String j2 = "";
        String k2 = "";
        String l2 = "";
        String m2 = "";
        String n2 = "";
        
        
        
        
        
        String a3 = "";
        String b3 = "";
        String c3 = "";
        String d3 = "";
        String e3 = "";
        String f3 = "";
        String g3 = "";
        String h3 = "";
        String i3 = "";
        String j3 = "";
        String l3 = "";
        
        
        String a4 = "";
        String b4 = "";
        String c4 = "";
        String d4 = "";
        String e4 = "";
        
        
        
        String an = "";
        
        
        
        
        
        
        try{
        Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
            
            
            
            /*
             * 
            a2 = a2+" ";
            b2 = b2+" ";
            c2 = c2+" ";
            d2 = d2+" ";
            e2 = e2+" ";
            f2 = f2+" ";
            g2 = g2+" ";
            h2 = h2+" ";
            i2 = i2+" ";
            j2 = j2+" ";
            k2 = k2+" ";
            l2 = l2+" ";
            
            
             */
            
            
            
            
            PreparedStatement statement = connection.prepareStatement("Select * from proformatable where InvoiceNo = '"+z+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs = statement.executeQuery();
            if(rs.next())
            
            //example1 = rs.getInt("PinCode");
            //example1 = Integer.toString(y);
            a = rs.getString("InvoiceNo");
            b = rs.getString("Dated");
            c = rs.getString("Sender");
            d = rs.getString("Receiver");
            e = rs.getString("ModeTOP");
            f = rs.getString("DeliveryNote");
            g = rs.getString("SuppliersRef");
            h = rs.getString("OtherRef");
            i = rs.getString("BuyersOrderNo");
            j = rs.getString("DatedBON");
            k = rs.getString("DespatchDocNo");
            l = rs.getString("DatedDDN");
            m = rs.getString("DespatchedThrough");
            n = rs.getString("Destination");
            o = rs.getString("Terms");
            p = rs.getString("OtherInfo");
            
            
            
            a = a+" ";
            b = b+" ";
            c = c+" ";
            d = d+" ";
            e = e+" ";
            f = f+" ";
            g = g+" ";
            h = h+" ";
            i = i+" ";
            j = j+" ";
            k = k+" ";
            l = l+" ";
            m = m+" ";
            n = n+" ";
            o = o+" ";
            p = p+" ";
            
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
            
            
            
            
            PreparedStatement dbstatement = connection.prepareStatement("Select description,quantity,rate,per,amount from proformatabledescription where InvoiceNo = '"+z+"'");  
            ResultSet rs1 = dbstatement.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs1));
            rs1.previous();
            lastdescription = rs1.getString("description") + "";
            lastamount = rs1.getString("amount") + "";
            lastper = rs1.getString("per") + "";
            lastrate = rs1.getString("rate") + "";
            lastquantity = rs1.getString("quantity") + "";
                
            String finalamt = rs1.getString("amount");
            Double amnt = Double.parseDouble(finalamt);
            numToWord ntoword = new numToWord();
            String ntw = ntoword.plzz(amnt.intValue());
            
            
            
            ArrayList<RowBean> rowList = new ArrayList<RowBean>();
            rs1.first();
            
            do {
                description = rs1.getString("description") + " ";
                amount = rs1.getString("amount") + " ";
                per = rs1.getString("per") + " ";
                rate = rs1.getString("rate") + " ";
                String qty = rs1.getString("quantity") + " ";
                RowBean rowBean = new RowBean();
                rowBean.setAmt(amount);
                rowBean.setDesc(description);
                rowBean.setPer(per);
                rowBean.setRate(rate);
                rowBean.setQty(qty);
                rowList.add(rowBean);
            }while(rs1.next());
        
            
            for( int q=rowList.size() ; q<17;q++){
                RowBean rowBean = new RowBean();
                rowBean.setAmt("");
                rowBean.setDesc("");
                rowBean.setPer("");
                rowBean.setRate("");
                rowBean.setQty("");
                rowList.add(rowList.size()-5 , rowBean);
            }
            
            /*
             * if(rs1.last()){
                lastdescription = rs1.getString("description") + "";
                lastamount = rs1.getString("amount") + "";
                lastper = rs1.getString("per") + "";
                lastrate = rs1.getString("rate") + "";
                lastquantity = rs1.getString("quantity") + "";
                
            }
            else{
            }
            
             */
            
            
            
             for(int q=0;q<15;q++){
//                /rs1.absolute(q);
            if(rs1.next()){
                            
                //amount = rs1.getString("description");
                            }
            else{
                break;
            }
            }
             
            
            
            
            
            
            
            
            //JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);
            //JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);
        
        
            PreparedStatement statement2 = connection.prepareStatement("Select * from owndetailstable where Name = '"+c+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            
            //example1 = rs.getInt("PinCode");
            //example1 = Integer.toString(y);
            a2 = rs2.getString("Name");
            b2 = rs2.getString("Address");
            c2 = rs2.getString("City");
            d2 = rs2.getString("State");
            e2 = rs2.getString("Country");
            f2 = rs2.getString("PinCode");
            g2 = rs2.getString("PhoneNumber");
            h2 = rs2.getString("Mobile");
            i2 = rs2.getString("Email");
            j2 = rs2.getString("TinNumber");
            k2 = rs2.getString("Password");
            l2 = rs2.getString("CSTNo");
            m2 = rs2.getString("PIP");
            n2 = rs2.getString("PIS");
        
            
            a2 = a2+" ";
            b2 = b2+" ";
            c2 = c2+" ";
            d2 = d2+" ";
            e2 = e2+" ";
            f2 = f2+" ";
            g2 = g2+" ";
            h2 = h2+" ";
            i2 = i2+" ";
            j2 = j2+" ";
            k2 = k2+" ";
            l2 = l2+" ";
            m2 = m2+" ";
            n2 = n2+" ";
            
            an = a2.toUpperCase();
            
            c = c+"<br></br>"+b2+"<br></br>"+c2+"-"+f2+"<br></br>Tin No. "+j2+"<br></br>Email : "+i2;
            
            
            
            
            
            PreparedStatement statement3 = connection.prepareStatement("Select * from customertable where Name = '"+d+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs3 = statement3.executeQuery();
            if(rs3.next())
            
            //example1 = rs.getInt("PinCode");
            //example1 = Integer.toString(y);
            a3 = rs3.getString("Name");
            b3 = rs3.getString("Address");
            c3 = rs3.getString("City");
            d3 = rs3.getString("State");
            e3 = rs3.getString("Country");
            f3 = rs3.getString("PinCode");
            g3 = rs3.getString("PhoneNumber");
            h3 = rs3.getString("Mobile");
            i3 = rs3.getString("Email");
            j3 = rs3.getString("TinNumber");
            l3 = rs3.getString("CSTNo");
        
            a3 = a3+" ";
            b3 = b3+" ";
            c3 = c3+" ";
            d3 = d3+" ";
            e3 = e3+" ";
            f3 = f3+" ";
            g3 = g3+" ";
            h3 = h3+" ";
            i3 = i3+" ";
            j3 = j3+" ";
            l3 = l3+" ";
                       
            d = "<br/>"+d+"<br></br>"+b3+"<br></br>"+c3+"-"+f3;
            
            
            
            
            
            try{
        Configuration cfg = new Configuration();
            //Load template from source folder
            new File("C:/Rishabh/files").mkdirs();
        
            cfg.setDirectoryForTemplateLoading(new File(
    "C:/Rishabh/files"));
            Template template = cfg.getTemplate("t1.html");
             
            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("InvoiceNo", a);
            data.put("Dated", b); 
            data.put("Sender", c); 
            data.put("Receiver", d); 
            
            data.put("ModeTOP", e); 
            data.put("DeliveryNote", f); 
            data.put("SuppliersNote", g); 
            data.put("OtherRef", h); 
            data.put("BuyersOrderNo", i); 
            data.put("DatedBON", j); 
            data.put("DespatchDocNo", k); 
            data.put("DatedDDN", l); 
            data.put("DespatchedThrough", m); 
            data.put("Destination", n); 
            data.put("Terms", o); 
            data.put("OtherInfo", p); 
            data.put("tin", j2); 
            data.put("cst", l2); 
            data.put("bTin", j3); 
            data.put("ntw", ntw); 
            data.put("len",rowList.size());
            data.put("arr", rowList);
            data.put("lastdescription", lastdescription);
            data.put("lastquantity", lastquantity);
            data.put("lastrate", lastrate);
            data.put("lastper", lastper);
            data.put("lastamount", lastamount);
            data.put("companyname", an);
            data.put("prefix", m2);
            data.put("suffix", n2);
            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();
 
            // File output
            Writer file = new FileWriter (new File("C:/Rishabh/files/out.html"));
            template.process(data, file);
            file.flush();
            file.close();
            
            // ******************* Flying Saucer start
        String url = "C:/Rishabh/files/out.html" ; //args[0];
        if (url.indexOf("://") == -1) {
            // maybe it's a file
            File f1 = new File(url);
            if (f1.exists()) {
                url = f1.toURI().toURL().toString();
            }
        }
        new File("C:/Rishabh/Invoices/Proforma Invoice").mkdirs();
        createPDF(url, "C:/Rishabh/Invoices/Proforma Invoice/AS-P "+a+" 14-15.pdf");
        createPDF(url, "AS-P "+a+" 14-15.pdf");
            }
            catch(Exception ea){
                ea.printStackTrace();
                JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);
        
            }
        
        try{
        Configuration cfg = new Configuration();
            //Load template from source folder
        cfg.setDirectoryForTemplateLoading(new File(
    "C:/Rishabh/files"));
            
            Template template = cfg.getTemplate("t1print.html");
             
            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("InvoiceNo", a);
            data.put("Dated", b); 
            data.put("Sender", c); 
            data.put("Receiver", d); 
            
            data.put("ModeTOP", e); 
            data.put("DeliveryNote", f); 
            data.put("SuppliersNote", g); 
            data.put("OtherRef", h); 
            data.put("BuyersOrderNo", i); 
            data.put("DatedBON", j); 
            data.put("DespatchDocNo", k); 
            data.put("DatedDDN", l); 
            data.put("DespatchedThrough", m); 
            data.put("Destination", n); 
            data.put("Terms", o); 
            data.put("OtherInfo", p); 
            data.put("tin", j2); 
            data.put("cst", l2); 
            data.put("bTin", j3); 
            data.put("ntw", ntw); 
            data.put("len",rowList.size());
            data.put("arr", rowList);
            data.put("lastdescription", lastdescription);
            data.put("lastquantity", lastquantity);
            data.put("lastrate", lastrate);
            data.put("lastper", lastper);
            data.put("lastamount", lastamount);
            data.put("companyname", an);
            data.put("prefix", m2);
            data.put("suffix", n2);
            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out);
            out.flush();
 
            // File output
            Writer file = new FileWriter (new File("C:/Rishabh/files/out.html"));
            template.process(data, file);
            file.flush();
            file.close();
            
            // ******************* Flying Saucer start
        String url = "C:/Rishabh/files/out.html" ; //args[0];
        if (url.indexOf("://") == -1) {
            // maybe it's a file
            File f1 = new File(url);
            if (f1.exists()) {
                url = f1.toURI().toURL().toString();
            }
        }
        createPDF(url, "AS-P "+a+" 14-15 Print.pdf");
            }
            catch(Exception ea){
                ea.printStackTrace();
                JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

            }
        
        
        }catch(Exception ea){
            ea.printStackTrace();
        JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);
                }
                
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        
        
        float tot1 = 0;
        float tot2 = 0;
        float qty = 0;
        float rte = 0;
        
        DecimalFormat form1 = new DecimalFormat("0.00");
        

        for(int q=0;q<12;q++){

            String quant= ""+jTable1.getValueAt(q,1);
            String rat= ""+jTable1.getValueAt(q,2);
            try{
                if(quant.equals("null") || rat.equals("null")) 
                {
                    continue;
                }
                    qty = Float.parseFloat(quant);
                    rte = Float.parseFloat(rat);

                    float amt = qty * rte ;

                    tot1 += qty ;
                    tot2 += amt;
                    jTable1.setValueAt(form1.format(amt) , q, 4);
                
            }
            catch(Exception e){
                e.printStackTrace();
                JOptionPane.showMessageDialog(this,e.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

            }
        }

        
        jTable1.setValueAt(tot1 , 16, 1);
        jTable1.setValueAt(form1.format(tot2) , 12, 4);
        
        String vat=""+tot2 ;
        String vat1="" + jTable1.getValueAt(13,2) ;
        String val141=""+jTable1.getValueAt(14,1) ;
        String val142=""+jTable1.getValueAt(14,2) ;
        
        
        try
        {
        if(val141.equals(" ")||val142.equals(" "))
        {
            jTable1.setValueAt(val141 , 14, 1);
            jTable1.setValueAt(val142 , 14, 2);            System.out.println("14if");

        
        }
        else
        {
            float fval141 = Float.parseFloat(val141.trim());
            float fval142 = Float.parseFloat(val142.trim());
            float fval144 = fval141*fval142;
            jTable1.setValueAt(fval144, 14, 4);
                        System.out.println("14else");
System.out.println(fval141);
System.out.println(fval142);
System.out.println(fval144);

            
        }
        }
        catch(Exception ed){
            ed.printStackTrace();
            JOptionPane.showMessageDialog(this,ed.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }
        
        
        String val151=""+jTable1.getValueAt(15,1) ;
        String val152=""+jTable1.getValueAt(15,2) ;
        try
        {
        if(val151.equals(" ")||val152.equals(" "))
        {
            jTable1.setValueAt(val151 , 15, 1);
            jTable1.setValueAt(val152 , 15, 2);
                        System.out.println("15if");

        
        }
        else
        {
            float fval151 = Float.parseFloat(val151.trim());
            float fval152 = Float.parseFloat(val152.trim());
            float fval154 = fval151*fval152;
            jTable1.setValueAt(fval154, 15, 4);
                        System.out.println("15else");

            
        }
        }
        catch(Exception ed){
            ed.printStackTrace();
            JOptionPane.showMessageDialog(this,ed.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

        }

        
        

        float val1=0;
        float val2=0;
        
        
        String blahh1 = ""+jTable1.getValueAt(14,4);
        String blahh2 = ""+jTable1.getValueAt(15,4);
        if(blahh1.equals("null")){
        val1=0;
        
        
        }
        else
        {
        val1=Float.parseFloat(blahh1.trim());
        
        }
        
        
        
        if(blahh2.equals("null")){
        val2=0;
        
        
        }
        else
        {
        val2=Float.parseFloat(blahh2.trim());
        
        }
        
        
        
        
        /*
         * 
         * if(val1==null)
            val1 = "0";
        if(val2 == null)
            val2= "0";
        
         * 
         * 
         */
        
        
        DecimalFormat form = new DecimalFormat("0.00");
        Float fvat = Float.parseFloat(vat);
        Float fvat1 = Float.parseFloat(vat1);
        Float fval1 = val1;
        Float fval2 = val2;
        Float fvat2 = (fvat*fvat1)/100;
        Float fvat3 = fvat+fvat2+fval1+fval2;
         
        jTable1.setValueAt(form.format(fvat2) , 13, 4);
        jTable1.setValueAt(form.format(fvat3) , 16, 4);
        jTable1.setValueAt(form.format(fval1) , 14, 4);
        jTable1.setValueAt(form.format(fval2) , 15, 4);
        
        
            
        int len1=jTextField1.getText().length();
        int len2=jTextField2.getText().length();
        int len3=jTextField3.getText().length();
        int len4=jTextField4.getText().length();

        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>InvoiceNo</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len2==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Date</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len3==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Sender</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len4==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Receiver</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
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
            String k = jTextField11.getText();
            String l = jTextField12.getText();
            String m = jTextField13.getText();
            String n = jTextField14.getText();
            String o = jTextField15.getText();
            String p = jTextField16.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO proformatable (InvoiceNo,Dated,Sender,Receiver,ModeTOP,DeliveryNote,SuppliersRef,OtherRef,BuyersOrderNo,DatedBON,DespatchDocNo,DatedDDN,DespatchedThrough,Destination,Terms,OtherInfo)"
                    + "VALUES ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"','"+m+"','"+n+"','"+o+"','"+p+"');");
                statement.executeUpdate();

                Statement dbStatement=connection.createStatement();

                String description = "";
                String quantity = "";
                String rate = "";
                String per = "";
                String amount = "";

                for(int q=0;q<jTable1.getRowCount();q++){

                    description=(String) jTable1.getValueAt(q,0);
                    quantity= ""+jTable1.getValueAt(q,1);
                    rate=(String) jTable1.getValueAt(q,2);
                    per=""+jTable1.getValueAt(q,3);
                    amount=""+jTable1.getValueAt(q,4);
                    
                    if(amount.equals("null") || "".equals(amount.trim()))
                    {continue;
                    }else{
                        dbStatement.addBatch("INSERT INTO proformatabledescription (invoiceno,Description,Quantity,Rate,per,Amount) VALUES('"+a+"','"+description+"','"+quantity+"','"+rate+"','"+per+"','"+amount+"')");
                    }
                }
                dbStatement.executeBatch();
                JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);

            }
            catch (ClassNotFoundException | SQLException| ClassCastException | HeadlessException | NullPointerException ea) {
                JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);
                ea.printStackTrace();
            }

        }

        
        
        /*
 * 
        int tot1 = 0;
        int tot2 = 0;

        for(int q=0;q<13;q++){

            String quant= "" + jTable1.getValueAt(q,1)  ;
            String rat="" + jTable1.getValueAt(q,2) ;
            try{
                if(quant == null || rat==null)
                continue;

                int qty = Integer.parseInt(quant.trim());
                int rte = Integer.parseInt(rat.trim());

                int amt = qty * rte ;

                tot1 += qty ;
                tot2 += amt;
                jTable1.setValueAt(amt , q, 4);

            }
            catch(Exception e){

            }
        }

        
        jTable1.setValueAt(tot1 , 16, 1);
        jTable1.setValueAt(tot2 , 12, 4);
        String vat="" + jTable1.getValueAt(12,4) ;
        String vat1="" + jTable1.getValueAt(13,2) ;
        String val1=(String)jTable1.getValueAt(14,4) ;
        String val2=(String)jTable1.getValueAt(15,4) ;
        if(val1==null)
            val1 = "0";
        if(val2 == null)
            val2= "0";
        Float fvat = Float.parseFloat(vat);
        Float fvat1 = Float.parseFloat(vat1);
        Float fval1 = Float.parseFloat(val1);
        Float fval2 = Float.parseFloat(val2);
        Float fvat2 = (fvat*fvat1)/100;
        Float fvat3 = fvat+fvat2+fval1+fval2;
        jTable1.setValueAt(fvat2 , 13, 4);
        jTable1.setValueAt(fvat3 , 16, 4);
        jTable1.setValueAt(val1 , 14, 4);
        jTable1.setValueAt(val2 , 15, 4);
        
        
            
        int len1=jTextField1.getText().length();
        int len2=jTextField2.getText().length();
        int len3=jTextField3.getText().length();
        int len4=jTextField4.getText().length();

        if(len1==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>InvoiceNo</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len2==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Date</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len3==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Sender</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
        }else if(len4==0){
            JOptionPane.showMessageDialog(this,"<html><body>Please enter a Valid<br>Receiver</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
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
            String k = jTextField11.getText();
            String l = jTextField12.getText();
            String m = jTextField13.getText();
            String n = jTextField14.getText();
            String o = jTextField15.getText();
            String p = jTextField16.getText();

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO proformatable (InvoiceNo,Dated,Sender,Receiver,ModeTOP,DeliveryNote,SuppliersRef,OtherRef,BuyersOrderNo,DatedBON,DespatchDocNo,DatedDDN,DespatchedThrough,Destination,Terms,OtherInfo)"
                    + "VALUES ('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"','"+f+"','"+g+"','"+h+"','"+i+"','"+j+"','"+k+"','"+l+"','"+m+"','"+n+"','"+o+"','"+p+"');");
                statement.executeUpdate();

                Statement dbStatement=connection.createStatement();

                String description = "";
                String quantity = "";
                String rate = "";
                String per = "";
                String amount = "";

                for(int q=0;q<jTable1.getRowCount();q++){

                    description=(String) jTable1.getValueAt(q,0);
                    quantity= ""+jTable1.getValueAt(q,1);
                    rate=(String) jTable1.getValueAt(q,2);
                    per=""+jTable1.getValueAt(q,3);
                    amount=""+jTable1.getValueAt(q,4);
                    
                    if(amount.equals("null") || "".equals(amount.trim()))
                    {continue;
                    }else{
                        dbStatement.addBatch("INSERT INTO proformatabledescription (invoiceno,Description,Quantity,Rate,per,Amount) VALUES('"+a+"','"+description+"','"+quantity+"','"+rate+"','"+per+"','"+amount+"')");
                    }
                }
                dbStatement.executeBatch();
                JOptionPane.showMessageDialog(this, "Values Stored","", JOptionPane.INFORMATION_MESSAGE);

            }
            catch (ClassNotFoundException | SQLException| ClassCastException | HeadlessException | NullPointerException ea) {
                JOptionPane.showMessageDialog(this,"<html><body>Something Went Wrong!</body></html>","WARNING", JOptionPane.ERROR_MESSAGE);
                ea.printStackTrace();
            }

        }

 */
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        MainFrame obj1 = new MainFrame();
        obj1.setVisible(true);
        setVisible(false);
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        
        String z = jTextField1.getText();
        String c = jTextField3.getText();
        String d = jTextField4.getText();
        String a2 = "";
        String b2 = "";
        String c2 = "";
        String d2 = "";
        String e2 = "";
        String f2 = "";
        String g2 = "";
        String h2 = "";
        String i2 = "";
        String j2 = "";
        String k2 = "";
        String l2 = "";
        String i3 = "";
        String an = "";
        
        
        
        try{
                    Class.forName("com.mysql.jdbc.Driver");  
            Connection connection = DriverManager.getConnection(  
                    "jdbc:mysql://localhost:3306/mydb", "root", "");
        
                    PreparedStatement statement2 = connection.prepareStatement("Select * from owndetailstable where Name = '"+c+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs2 = statement2.executeQuery();
            if(rs2.next())
            
            //example1 = rs.getInt("PinCode");
            //example1 = Integer.toString(y);
            i2 = rs2.getString("Email");
            k2 = rs2.getString("Password");
            
            
                PreparedStatement statement3 = connection.prepareStatement("Select * from customertable where Name = '"+d+"'");  
            //(Name,Address,City,State,Country,PinCode,PhoneNumber,Mobile,Email,TinNumber)    
            ResultSet rs3 = statement3.executeQuery();
            if(rs3.next())
            
            //example1 = rs.getInt("PinCode");
            //example1 = Integer.toString(y);
            i3 = rs3.getString("Email");
            
        
        
        
        
        
                final String username = i2;
		final String password = k2;
                an = c.toUpperCase();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
 
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(i2));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(i3));
			message.setSubject(c+" Proforma Invoice");
			MimeBodyPart messageBodyPart = new MimeBodyPart();

		        Multipart multipart = new MimeMultipart();

		        messageBodyPart = new MimeBodyPart();
		        String file = "AS-P "+z+" 14-15.pdf";
		        String fileName = "AS-P "+z+" 14-15.pdf";
		        FileDataSource fileDataSource = new FileDataSource(file);
		        System.out.println(fileDataSource.getFile().getAbsolutePath());
		        DataSource source = new FileDataSource(file);
		        
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(fileName);
		        multipart.addBodyPart(messageBodyPart);
                        
                        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
		        messageBodyPart2.setText("Sir,"
				+ "\n\nAttached is the Invoice.\n\n"+
                                "Regards,\n"+
                                "Sachiv Dhawan(CEO)\n"+
                                "Automotive Solutions(TM).\n"+
                                "ISO(9001:2008)\n"+
                                "Sales Off :  WZ-75 A | TODAPUR | NEW DELHI-110012\n\n"+
                                "Regd Off :  11/9 GF | OLD RAJINDER NAGAR | NEW DELHI-110060\n"+
                                "Mobile : +91  93500 42380 | +91 98100 37026.\n"+
                                "Office   :+91  011329 37026 | Support 011 470 37026");
			multipart.addBodyPart(messageBodyPart2);
                        
		        message.setContent(multipart);
			Transport.send(message);
			System.out.println("Done");
 
		} catch (MessagingException e) {
                    JOptionPane.showMessageDialog(this,e.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

			throw new RuntimeException(e);
		}
                }catch(Exception ea){
                    JOptionPane.showMessageDialog(this,ea.getMessage(),"WARNING", JOptionPane.ERROR_MESSAGE);

                    ea.printStackTrace();
                }
        

        
        
        
        
        
        
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        
        String z = jTextField1.getText();
        
        PrintPDF obj1 = new PrintPDF();
        try {
            String pdfFile = "AS-P "+z+" 14-15 Print.pdf";
            obj1.main(pdfFile);
        }
        catch(Exception ea){
            ea.printStackTrace();
        }
        
        
        
        
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

   
    
    
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
            java.util.logging.Logger.getLogger(ProformaInvFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProformaInvFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProformaInvFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProformaInvFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProformaInvFrame().setVisible(true);
            }
        });
    }
    
    public void increment(){
            }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
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
