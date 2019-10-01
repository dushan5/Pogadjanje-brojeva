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
public class ServerskiOdgovor implements Serializable {

    private String poruka;
    private int operacija;
    private boolean pocetak;
    private boolean igra;
    private int prvi;
    private int drugi;
    private int treci;

    private int ukupanBrPokusaja;

    public ServerskiOdgovor(String poruka, int operacija, boolean pocetak, boolean igra, int prvi, int drugi, int treci, int ukupanBrPokusaja) {
        this.poruka = poruka;
        this.operacija = operacija;
        this.pocetak = pocetak;
        this.igra = igra;
        this.prvi = prvi;
        this.drugi = drugi;
        this.treci = treci;
        this.ukupanBrPokusaja = ukupanBrPokusaja;
    }

    

    public ServerskiOdgovor() {

    }

    public int getOperacija() {
        return operacija;
    }

    public void setOperacija(int operacija) {
        this.operacija = operacija;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }

    public int getUkupanBrPokusaja() {
        return ukupanBrPokusaja;
    }

    public void setUkupanBrPokusaja(int ukupanBrPokusaja) {
        this.ukupanBrPokusaja = ukupanBrPokusaja;
    }

    public boolean isPocetak() {
        return pocetak;
    }

    public void setPocetak(boolean pocetak) {
        this.pocetak = pocetak;
    }

    public boolean isIgra() {
        return igra;
    }

    public void setIgra(boolean igra) {
        this.igra = igra;
    }

    public int getPrvi() {
        return prvi;
    }

    public void setPrvi(int prvi) {
        this.prvi = prvi;
    }

    public int getDrugi() {
        return drugi;
    }

    public void setDrugi(int drugi) {
        this.drugi = drugi;
    }

    public int getTreci() {
        return treci;
    }

    public void setTreci(int treci) {
        this.treci = treci;
    }

    
    

}
