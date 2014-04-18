import java.awt.print.PrinterJob;

import javax.print.PrintService;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

/**
 * This is a command line program that will print a PDF document.
 *
 * @author <a href="ben@benlitchfield.com">Ben Litchfield</a>
 * @version $Revision: 1.4 $
 */
public class PrintPDF
{

    private static final String PASSWORD     = "-password";
    private static final String SILENT       = "-silentPrint";
    private static final String PRINTER_NAME = "-printerName";

    /**
     * private constructor.
    */
    private PrintPDF()
    {
        //static class
    }

    /**
     * Infamous main method.
     *
     * @param args Command line arguments, should be one and a reference to a file.
     *
     * @throws Exception If there is an error parsing the document.
     */
    public static void main( String[] args ) throws Exception
    {
        String password = "";
        String pdfFile = "kk.pdf";
        boolean silentPrint = false;
        String printerName = null;
        /*
        for( int i=0; i<args.length; i++ )
        {
            if( args[i].equals( PASSWORD ) )
            {
                i++;
                if( i >= args.length )
                {
                    usage();
                }
                password = args[i];
            }
            else if( args[i].equals( PRINTER_NAME ) )
            {
                i++;
                if( i >= args.length )
                {
                    usage();
                }
                printerName = args[i];
            }
            else if( args[i].equals( SILENT ) )
            {
                silentPrint = true;
            }
            else
            {
                pdfFile = args[i];
            }
        }*/

        if( pdfFile == null )
        {
            usage();
        }

        PDDocument document = null;
        try
        {
        	File file = new File(pdfFile);
        	System.out.println(file.getAbsolutePath());
            document = PDDocument.load( pdfFile );

            if( document.isEncrypted() )
            {
                document.decrypt( password );
            }

            PrinterJob printJob = PrinterJob.getPrinterJob();
            printJob.setJobName(new File(pdfFile).getName());

            if(printerName != null )
            {
                PrintService[] printService = PrinterJob.lookupPrintServices();
                boolean printerFound = false;
                for(int i = 0; !printerFound && i < printService.length; i++)
                {
                    if(printService[i].getName().indexOf(printerName) != -1)
                    {
                        printJob.setPrintService(printService[i]);
                        printerFound = true;
                    }
                }
            }

            if( silentPrint )
            {
                document.silentPrint( printJob );
            }
            else
            {
                document.print( printJob );
            }
        }
        finally
        {
            if( document != null )
            {
                document.close();
            }
        }
    }

    /**
     * This will print the usage requirements and exit.
     */
    public static void usage()
    {
        System.err.println( "Usage: java org.apache.pdfbox.PrintPDF [OPTIONS] <PDF file>\n" +
            "  -password  <password>        Password to decrypt document\n" +
            "  -silentPrint                 Print without prompting for printer info\n"
            );
        System.exit( 1 );
    }
}