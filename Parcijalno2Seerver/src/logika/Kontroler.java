/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nikola
 */
public class Kontroler {

    private static Kontroler instance;
    private ArrayList<Socket> listaSoketa;
    private int brojac = 1;
    private int broj1zadat;
    private int broj2zadat;
    private int broj3zadat;
    

    private Kontroler() {
        listaSoketa = new ArrayList<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();

        }
        return instance;
    }

    public void sacuvajS1(Socket s1) {
        listaSoketa.add(s1);
    }

    public void sacuvajS2(Socket s2) {
        listaSoketa.add(s2);
    }

    public ArrayList<Socket> getListaSoketa() {
        return listaSoketa;
    }

    public void setListaSoketa(ArrayList<Socket> listaSoketa) {
        this.listaSoketa = listaSoketa;
    }

    public int getBrojac() {
        return brojac;
    }

    public void setBrojac(int brojac) {
        this.brojac = brojac;
    }

    public int getBroj1zadat() {
        return broj1zadat;
    }

    public void setBroj1zadat(int broj1zadat) {
        this.broj1zadat = broj1zadat;
    }

    public int getBroj2zadat() {
        return broj2zadat;
    }

    public void setBroj2zadat(int broj2zadat) {
        this.broj2zadat = broj2zadat;
    }

    public int getBroj3zadat() {
        return broj3zadat;
    }

    public void setBroj3zadat(int broj3zadat) {
        this.broj3zadat = broj3zadat;
    }

}
