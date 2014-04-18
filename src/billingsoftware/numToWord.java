/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsoftware;

/**
 *
 * @author Rishabh
 */
public class numToWord {
    public static void main(String[] args){
        String abc = numToWord(414619120);
        System.out.println(abc);
    }

public static String numToWord(Integer i) {

 final  String[] units = { "Zero", "One", "Two", "Three",
        "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
        "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen" };
 final  String[] tens = { "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    if (i < 20)
        return units[i];
    if (i < 100)
        return tens[i / 10] + ((i % 10 > 0) ? " " + numToWord(i % 10) : "");
    if (i < 1000)
        return units[i / 100] + " Hundred"
                + ((i % 100 > 0) ? " and " + numToWord(i % 100) : "");
    if (i < 100000)
        return numToWord(i / 1000) + " Thousand "
                + ((i % 1000 > 0) ? " " + numToWord(i % 1000) : "");
    if (i < 10000000)
        return numToWord(i / 100000) + " Lakh "
                + ((i % 100000 > 0) ? " " + numToWord(i % 100000) : "");
    return numToWord(i / 10000000) + " Crore "
            + ((i % 10000000 > 0) ? " " + numToWord(i % 10000000) : "");
}


public String plzz(int i){
        
    final  String[] units = { "Zero", "One", "Two", "Three",
        "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
        "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen" };
 final  String[] tens = { "", "", "Twenty", "Thirty", "Forty",
        "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
    if (i < 20)
        return units[i];
    if (i < 100)
        return tens[i / 10] + ((i % 10 > 0) ? " " + numToWord(i % 10) : "");
    if (i < 1000)
        return units[i / 100] + " Hundred"
                + ((i % 100 > 0) ? " and " + numToWord(i % 100) : "");
    if (i < 100000)
        return numToWord(i / 1000) + " Thousand "
                + ((i % 1000 > 0) ? " " + numToWord(i % 1000) : "");
    if (i < 10000000)
        return numToWord(i / 100000) + " Lakh "
                + ((i % 100000 > 0) ? " " + numToWord(i % 100000) : "");
    return numToWord(i / 10000000) + " Crore "
            + ((i % 10000000 > 0) ? " " + numToWord(i % 10000000) : "");

    
    
    


}
}