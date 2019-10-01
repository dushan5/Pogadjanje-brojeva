/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kom;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Nikola
 */
public class KomunikacijaSaKlijentom {

    private static KomunikacijaSaKlijentom instance;

    private KomunikacijaSaKlijentom() {
    }

    public static KomunikacijaSaKlijentom getInstance() {
        if (instance == null) {
            instance = new KomunikacijaSaKlijentom();

        }
        return instance;
    }

    public void posaljiOdgovor(ServerskiOdgovor so, Socket s) {

        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public KlijentskiZahtev primiZahtev(Socket s) {

        KlijentskiZahtev kz = new KlijentskiZahtev();

        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KomunikacijaSaKlijentom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kz;
    }

}
