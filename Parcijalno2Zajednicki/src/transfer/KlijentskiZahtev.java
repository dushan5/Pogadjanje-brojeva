/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import java.io.Serializable;

/**
 *
 * @author Nikola
 */
public class KlijentskiZahtev implements Serializable {

    private int operacija;
    private String broj1;
    private String broj2;
    private String broj3;

    public KlijentskiZahtev() {
    }

    public KlijentskiZahtev(int operacija, String broj1, String broj2, String broj3) {
        this.operacija = operacija;
        this.broj1 = broj1;
        this.broj2 = broj2;
        this.broj3 = broj3;
    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public String getBroj1() {
        return broj1;
    }

    public void setBroj1(String broj1) {
        this.broj1 = broj1;
    }

    public String getBroj2() {
        return broj2;
    }

    public void setBroj2(String broj2) {
        this.broj2 = broj2;
    }

    public String getBroj3() {
        return broj3;
    }

    public void setBroj3(String broj3) {
        this.broj3 = broj3;
    }

}
