/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.KlijentForma;
import oper.Boje;
import oper.Operacija;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Nikola
 */
public class OcekujOdgNit extends Thread {

    KlijentForma kf;
    boolean kraj = false;
    int brojac = 1;

    public OcekujOdgNit(KlijentForma kf) {
        this.kf = kf;
    }

    @Override
    public void run() {
        while (!kraj) {

            ServerskiOdgovor so = komunikacija.KomunikacijaSaServerom.getInstance().primiOdgovor();
            switch (so.getOperacija()) {
                case Operacija.LOGIN:

                    if (!so.isPocetak()) {
                        kf.srediNije(so);

                    } else {

                        if (so.isIgra()) {
                            kf.srediIgra();
                        } else {
                            kf.srediNeIgra();
                        }

                    }

                    break;
                case Operacija.OBOJI:

                    if (so.getPrvi() == Boje.ZELENA) {
                        kf.srediPrviZelena(brojac);
                    } else {
                        if (so.getPrvi() == Boje.ZUTA) {
                            kf.srediPrviZuta(brojac);
                        } else {
                            if (so.getPrvi() == Boje.CRVENA) {
                                kf.srediPrviCrvena(brojac);
                            }
                        }
                    }
                    if (so.getDrugi() == Boje.ZELENA) {
                        kf.srediDrugiZelena(brojac);
                    } else {
                        if (so.getDrugi() == Boje.ZUTA) {
                            kf.srediDrugiZuta(brojac);
                        } else {
                            if (so.getDrugi() == Boje.CRVENA) {
                                kf.srediDrugiCrvena(brojac);

                            }
                        }
                    }
                    if (so.getTreci() == Boje.ZELENA) {
                        kf.srediTreciZelena(brojac);

                    } else {
                        if (so.getTreci() == Boje.ZUTA) {
                            kf.srediTreciZuta(brojac);

                        } else {
                            if (so.getTreci() == Boje.CRVENA) {
                                kf.srediTreciCrvena(brojac);

                            }
                        }
                    }

                    brojac++;
                    kf.srediNeIgra2();
                    break;
                case Operacija.POKUSAJ:

                    kf.srediIgra2(brojac);

                    break;
                case Operacija.KRAJ:
                    kf.srediKraj(so.getPoruka());
                    kraj = true;
                    break;
            }

        }
    }
}
