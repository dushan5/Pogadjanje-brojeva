/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niti;

import forme.ServerskaForma;
import java.net.Socket;
import kom.KomunikacijaSaKlijentom;
import logika.Kontroler;
import oper.Boje;
import oper.Operacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Nikola
 */
public class ObradaKZ extends Thread {

    Socket s;
    ServerskaForma sf;
    boolean kraj = false;
    int brojac;

    public ObradaKZ(Socket s, ServerskaForma sf) {
        this.s = s;
        this.sf = sf;
    }

    @Override
    public void run() {
        while (!kraj) {
            KlijentskiZahtev kz = KomunikacijaSaKlijentom.getInstance().primiZahtev(s);
            ServerskiOdgovor so = new ServerskiOdgovor();

            switch (kz.getOperacija()) {

                case oper.Operacija.POKUSAJ:
                    brojac = Kontroler.getInstance().getBrojac();
                    int br1 = Integer.parseInt(kz.getBroj1());
                    int br2 = Integer.parseInt(kz.getBroj2());
                    int br3 = Integer.parseInt(kz.getBroj3());

                    boolean krajJe = true;
                    if (br1 == Kontroler.getInstance().getBroj1zadat()) {
                        so.setPrvi(Boje.ZELENA);
                    } else {
                        if ((br1 == Kontroler.getInstance().getBroj2zadat() && br2 != Kontroler.getInstance().getBroj2zadat()) || (br1 == Kontroler.getInstance().getBroj3zadat() && br3 != Kontroler.getInstance().getBroj3zadat())) {
                            so.setPrvi(Boje.ZUTA);
                            krajJe = false;
                        } else {
                            so.setPrvi(Boje.CRVENA);
                            krajJe = false;
                        }
                    }
                    if (br2 == Kontroler.getInstance().getBroj2zadat()) {
                        so.setDrugi(Boje.ZELENA);
                    } else {
                        if ((br2 == Kontroler.getInstance().getBroj1zadat() && br1 != Kontroler.getInstance().getBroj1zadat()) || (br2 == Kontroler.getInstance().getBroj3zadat() && br3 != Kontroler.getInstance().getBroj3zadat() && br1 != Kontroler.getInstance().getBroj3zadat())) {
                            so.setDrugi(Boje.ZUTA);

                            krajJe = false;
                        } else {
                            so.setDrugi(Boje.CRVENA);
                            krajJe = false;
                        }
                    }
                    if (br3 == Kontroler.getInstance().getBroj3zadat()) {
                        so.setTreci(Boje.ZELENA);
                    } else {
//                        if ((br3 == Kontroler.getInstance().getBroj2zadat() && br2 != Kontroler.getInstance().getBroj2zadat() && br1 != Kontroler.getInstance().getBroj2zadat()) || (br3 == Kontroler.getInstance().getBroj1zadat() && br1 != Kontroler.getInstance().getBroj1zadat() && br2 != Kontroler.getInstance().getBroj1zadat())) {
                        if ((br3 == Kontroler.getInstance().getBroj1zadat() && ((br2 != br3) || (so.getDrugi() == Boje.ZELENA)) && so.getPrvi() != Boje.ZELENA) || (br3 == Kontroler.getInstance().getBroj2zadat() && (br3 != br1 || so.getPrvi() == Boje.ZELENA) && so.getDrugi() != Boje.ZELENA)) {
                            so.setTreci(Boje.ZUTA);

                            krajJe = false;
                        } else {
                            so.setTreci(Boje.CRVENA);
                            krajJe = false;
                        }
                    }

                    if (krajJe) {

                        if (s == Kontroler.getInstance().getListaSoketa().get(0)) {
                            System.out.println("PRVI POGODIO");
                            so.setOperacija(Operacija.OBOJI);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(0));

                            so.setOperacija(Operacija.KRAJ);
                            so.setPoruka("Bravo! Pogodili ste!");
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(0));
                            so.setPoruka("Protivnik je pogodio tacan broj! KRAJ");
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(1));

                        } else {
                            System.out.println("DRUGI POGODIO");
                            so.setOperacija(Operacija.OBOJI);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(1));

                            so.setOperacija(Operacija.KRAJ);
                            so.setPoruka("Bravo! Pogodili ste!");
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(1));
                            so.setPoruka("Protivnik je pogodio tacan broj! KRAJ");
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(0));

                        }

                        kraj = true;
                        break;
                    } else {
                        if (brojac % 2 == 0) {
                            so.setIgra(true);
                            so.setOperacija(Operacija.POKUSAJ);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(0));
                            so.setIgra(false);
                            so.setOperacija(Operacija.OBOJI);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(1));
                        } else {
                            so.setIgra(false);
                            so.setOperacija(Operacija.OBOJI);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(0));
                            so.setIgra(true);
                            so.setOperacija(Operacija.POKUSAJ);
                            KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so, Kontroler.getInstance().getListaSoketa().get(1));

                        }
                    }

                    System.out.println("Brojac :" + brojac);
                    Kontroler.getInstance().setBrojac(Kontroler.getInstance().getBrojac() + 1);

                    if (brojac >= 8) {
                        System.out.println("KRAJ IGRE! NEMA VISE POKUSAJA");
                        ServerskiOdgovor so1 = new ServerskiOdgovor();
                        so1.setPoruka("KRAJ IGRE! Nemate vise pokusaja");
                        so1.setOperacija(Operacija.KRAJ);
                        KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so1, Kontroler.getInstance().getListaSoketa().get(0));

                        KomunikacijaSaKlijentom.getInstance().posaljiOdgovor(so1, Kontroler.getInstance().getListaSoketa().get(1));

                        kraj = true;

                    }
                    break;
            }
        }

    }
}
