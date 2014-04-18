/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsoftware;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 *
 * @author Rishabh
 */
public class SetIcon extends JFrame{
    public static void main(String args[]){
        
        icon();
    }


    public static void icon()
        {



        SetIcon application  = new SetIcon();
        BufferedImage image = null;
        try{
            File imageFile = new File("C:\\Users\\Rishabh\\Desktop\\K B Label\\logo.ico");
            image = ImageIO.read(imageFile);
            
        }
        catch(Exception e){
            e.printStackTrace();;
        }
        application.setIconImage(image);
        
}

}