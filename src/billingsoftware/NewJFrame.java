/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsoftware;

/**
 *
 * @author Rishabh
 */
import javax.swing.*;
import javax.swing.JTable.*;
import javax.swing.table.*;
import java.awt.event.*;

/**
 *
 * @author  yccheok
 */
public class NewJFrame extends javax.swing.JFrame {
    
    /** Creates new form NewJFrame */
    public NewJFrame() {
        initComponents();
        
                /* Combo Box Added In JFrame. Work as expected. */
                final JComboBox comboBox = new JComboBox();
                comboBox.addItem("Snowboarding");
                comboBox.addItem("Rowing");
                comboBox.addItem("Chasing toddlers");    

                comboBox.setEditable(true);
                comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                   public void keyReleased(KeyEvent e) {
                       if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                           System.out.println("is enter");
                           return;
                       }
                       
                       System.out.println("typed");
                       comboBox.setSelectedIndex(0);
                       comboBox.showPopup();
                   } 
                });  
                
                getContentPane().add(comboBox, java.awt.BorderLayout.SOUTH);
    }
    
    public JTable getMyTable() {
        return new JTable() {
            /* 
             Combo Box Added In JTable as cell editor. Didn't work as expected:
             1. Exception thrown when show pop up.
             2. Unable to capture enter key event.
             */
            
            public TableCellEditor getCellEditor(int row, int column) {
                final JComboBox comboBox = new JComboBox();
                comboBox.addItem("Snowboarding");
                comboBox.addItem("Rowing");
                comboBox.addItem("Chasing toddlers");    

                comboBox.setEditable(true);
                comboBox.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
                   public void keyReleased(KeyEvent e) {
                       if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                           System.out.println("is enter");
                           return;
                       }
                       
                       System.out.println("typed");
                       comboBox.setSelectedIndex(0);
                       comboBox.showPopup();
                       
                   } 
                });
                
                return new DefaultCellEditor(comboBox);
            }
            
        };
    }
            
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">                          
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = getMyTable();

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
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>                        
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
    
}