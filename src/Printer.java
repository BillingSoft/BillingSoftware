/* 
 * Copyright (c) 2007 Patrick Wright 
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */
  

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.print.*;
import javax.print.attribute.Attribute;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.JobName;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PrintQuality;
import javax.print.event.PrintJobEvent;
import javax.print.event.PrintJobListener;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xhtmlrenderer.event.DocumentListener;
import org.xhtmlrenderer.extend.TextRenderer;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.simple.Graphics2DRenderer;
import org.xhtmlrenderer.swing.Java2DRenderer;


/**

 *
 * @author rk
 */
public class Printer implements Runnable, DocumentListener, Printable, PrintJobListener {

    /**
     *
     */
    private static final long serialVersionUID = 6115417677320950273L;

    private final String template = "../../src/Printer.java";

    /**
     * the base directory of the templates
     */
    private String base;

    /**
     * the logging mechanism log4j
     */
    private Logger log;

    /**
     * the tread that runs after initialization
     */
    private Thread runner;

    /**
     * the xhtml element dom tree
     */
    private Document doc;

    /**
     * the print job to be printed
     */
    private DocPrintJob job;

    /**
     * the renderer used to render the xhtml dom tree into the page
     */
    private Java2DRenderer j2dr;

    /**
     * initialization values of the Renderer
     */
    private SharedContext ctx;
    private final File file;

    /**
     * the constructor of the cameventprinter: starts logging and starts the
     * thread
     *
     * @param file
     */
    public Printer(File file) {
        this.file = file;
        log = LogManager.getLogManager().getLogger(Printer.class.getName());
        // initialization of the template path
        base = System.getProperty("user.dir") + File.separator + "config"
                + File.separator + "template" + File.separator;

        // </snip>

       // System.out.println("template printing");

        start();
    }

    /**
     * we're starting the thread
     */
    public void start() {
        if (runner == null) {
            runner = new Thread(this, "Runner");
            runner.start();
        }
    }

    /**
     * we're running now
     */
    public void run() {
        File file = new File(base + template);
        try {
            if (file.exists()) {
                // load the xml template here
                FileInputStream xmldoc = new FileInputStream(file);
                System.out.println("loading template from: " + file.getName());
                /* FIXME
                DOMParser parser = new DOMParser();
                parser.parse(xmldoc);
                doc = parser.getDocument();
                */
                Element e = doc.getDocumentElement();

                // show the document in the log for debugging purpose
                //log.fine("--------------------------------");

                // we want to use printing
                DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;

                PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
                attrs.add(OrientationRequested.PORTRAIT);
                attrs.add(PrintQuality.HIGH);
                attrs.add(new JobName(file.getName() + ".rio", null));

                PrintService service = PrintServiceLookup.lookupDefaultPrintService();

                // maybe we want to show the printer choice dialog

                // PrintService[] services = PrintServiceLookup
                // .lookupPrintServices(null, null);
                // PrintService service = ServiceUI.printDialog(null, 100, 100,
                // services, svc, flavor, attrs);

                if (service != null) {
                    System.out.println("printer selected : " + service.getName());
                    job = service.createPrintJob();
                    job.addPrintJobListener(this);
                    PrintJobAttributeSet atts = job.getAttributes();
                    Attribute[] arr = atts.toArray();
                    for (int i = 0; i < arr.length; i++) {
                        log.fine("arr[" + i + "]= " + arr[0].getName());
                    }

                    Doc sdoc = new SimpleDoc(this, flavor, null);
                    ctx = null; // new SharedContext(this);
                    ctx.setBaseURL(base);

                    // print the doc as specified
                    job.print(sdoc, attrs);

                } else {
                    System.out.println("printer selection cancelled");
                }

            } else {
            	System.out.println("file " + file.getAbsolutePath() + " doesn't exist");
            }
        } catch (Exception e) {
            //System.out.println(Level.SEVERE, "error loading file " + file.getName(), e);
            e.printStackTrace();
        }
        // stop the thread
        runner = null;
    }

    /**
     * The main function is made for debugging this application separately only.
     */
    public static void main(String args[]) {
        System.out.println("test program for template printing");
        String kk = "src/Printer.java";
        if (true || args.length > 0) {
            File file = new File(kk);
            System.out.println("printing file: " + file.getName());
            if (file.exists()) {
                // this will be the standard future use of this class: fire and
                // forget
                new Printer(file);
            } else {
                System.out.println("file " + file.getAbsolutePath()
                        + " doesn't exist!");
            }
        } else {
            System.out.println("usage: <filename>");
        }

    }

    public void documentStarted() {

    }

    public void documentLoaded() {
        System.out.println("document loaded");
    }

    public void onLayoutException(Throwable t) {

    }

    public void onRenderException(Throwable t) {

    }

    public int print(Graphics graphics, PageFormat pf, int pi)
            throws PrinterException {
        System.out.println("print");

        try {
            if (j2dr == null) {

                j2dr = new Java2DRenderer(file, 1024);
                SharedContext context = j2dr.getSharedContext();
                context.setPrint(true);
                context.setInteractive(false);
                context.setDPI(72f);

                context.getTextRenderer().setSmoothingThreshold(0);

            }

            return Printable.PAGE_EXISTS;
        } catch (Exception ex) {
            System.out.println("error while printing: " + ex);
            return Printable.NO_SUCH_PAGE;
        }
    }

    public void printDataTransferCompleted(PrintJobEvent pje) {
        System.out.println("print data transfer completed");
    }

    public void printJobCanceled(PrintJobEvent pje) {
        System.out.println("print job cancelled");
    }

    public void printJobCompleted(PrintJobEvent pje) {
        System.out.println("print job completed");
    }

    public void printJobFailed(PrintJobEvent pje) {
    	System.out.println("print job failed");
    }

    public void printJobNoMoreEvents(PrintJobEvent pje) {
        System.out.println("print job no more events");
    }

    public void printJobRequiresAttention(PrintJobEvent pje) {
        System.out.println("print job requires attention");
    }
}