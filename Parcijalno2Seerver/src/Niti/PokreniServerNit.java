/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niti;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import kom.KomunikacijaSaKlijentom;
import logika.Kontroler;
import oper.Operacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Nikola
 */
public class PokreniServerNit extends Thread {

    ServerskaForma sf;

    public PokreniServerNit(ServerskaForma sf) {
        this.sf = sf;
    }

    int brojac = Kontroler.getInstance().getBrojac();

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            System.out.println("Server pokrenut");

            Socket s1 = ss.accept();
            Kontroler.getInstance().sacuvajS1(s1);
            System.out.println("Klijent 1 povezan");

//            if (Kontroler.getInstance().getListaSoketa().size() > 2) {
//                JOptionPane.showMessageDialog(null, "Ne moze vise od 2 klijenta");
//                return;
//            }
            ServerskiOdgovor so = new ServerskiOdgovor();
            so.setPocetak(false);
            so.setPoruka("Sacekajte drugog igraca");
            
            so.setOperacija(oper.Operacija.LOGIN);
            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, s1);

            Socket s2 = ss.accept();
            Kontroler.getInstance().sacuvajS2(s2);
            System.out.println("Klijent 2 povezan");
            sf.prikaziTekst();

            ServerskiOdgovor so2 = new ServerskiOdgovor();
            so2.setPocetak(true);
            so2.setPoruka("Igra je pocela");
            so2.setOperacija(Operacija.LOGIN);
            if (brojac % 2 == 1) {
                so2.setIgra(true);
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so2, s1);
                so2.setIgra(false);
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so2, s2);
            } else {
                so2.setIgra(false);
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so2, s1);
                so2.setIgra(true);
                KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so2, s2);

            }

            ObradaKZ okz1 = new ObradaKZ(s1, sf);
            okz1.start();
            ObradaKZ okz2 = new ObradaKZ(s2, sf);
            okz2.start();

        } catch (IOException ex) {
            Logger.getLogger(PokreniServerNit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
