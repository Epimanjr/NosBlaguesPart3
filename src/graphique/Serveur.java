/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import annuaire.Annuaire;
import blague.Blague;
import codebase.AnnuaireInterface;
import codebase.BlagueProviderPairAPair;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blaise
 */
public class Serveur {

    /**
     * Méthode principale qui lance le serveur RMI.
     *
     * @param args
     */
    public static void main(String[] args) {
        Registry registry;
        try {

            registry = LocateRegistry.getRegistry();

            // Création de l'annuaire
            Annuaire a = new Annuaire();
            // Ref distante
            AnnuaireInterface ai = (AnnuaireInterface) UnicastRemoteObject.exportObject(a, 0);

            // Export
            registry.rebind("Annuaire", ai);
            
            System.out.println("Annuaire crée !");

        } catch (RemoteException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
