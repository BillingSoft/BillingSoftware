/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billingsoftware;

/**
 *
 * @author Rishabh
 */
public class RowBean {

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setPer(String per) {
        this.per = per;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getDesc() {
        return desc;
    }

    public String getQty() {
        return qty;
    }

    public String getRate() {
        return rate;
    }

    public String getPer() {
        return per;
    }

    public String getAmt() {
        return amt;
    }
    private String desc , qty , rate , per ,amt ;
    
}
