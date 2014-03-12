/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annuaire;

import codebase.AnnuaireInterface;
import codebase.BlagueProviderPairAPair;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxime Blaise
 * @author Antoine Nosal
 */
public class Annuaire implements AnnuaireInterface {

    //Liste contenant tous les BlagueProviderPairAPair connecté
    private final ArrayList<BlagueProviderPairAPair> bplist;

    /**
     * Construit un annuaire vide
     */
    public Annuaire() {
        this.bplist = new ArrayList<>();
    }

    /**
     * Méthode qui prend en paramètre la référence distante du BlagueProvider à
     * enregistrer et retourne le tableau des références déjà enregistrées
     *
     * @param ref
     * @return
     */
    @Override
    public BlagueProviderPairAPair[] register(BlagueProviderPairAPair ref) {
        // Ajout à la liste
        bplist.add(ref);

        // Conversion
        BlagueProviderPairAPair[] res = new BlagueProviderPairAPair[bplist.size()];
        int iterateur = 0;
        for (BlagueProviderPairAPair bpp : bplist) {
            res[iterateur] = bpp;
            iterateur++;
        }

        // Notify
        for (BlagueProviderPairAPair bppp : res) {
            try {
                bppp.notify(ref);
            } catch (RemoteException ex) {
                Logger.getLogger(Annuaire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return res;
    }

    /**
     *
     * @param ref
     */
    @Override
    public void disconnect(BlagueProviderPairAPair ref) {
        bplist.remove(ref);
        // Notify
        try {
            for (BlagueProviderPairAPair bppp : bplist) {

                bppp.notifyDeconnect(ref);

            }
        } catch (RemoteException ex) {
            Logger.getLogger(Annuaire.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Disconnect");
    }

}
