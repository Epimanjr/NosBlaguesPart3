/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebase;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Maxime Blaise
 * @author Antoine Nosal
 */
public interface AnnuaireInterface extends Remote {

    /**
     * Méthode qui prend en paramètre la référence distante du BlagueProvider à
     * enregistrer et retourne le tableau des références déjà enregistrées
     *
     * @param ref
     * @return
     * @throws java.rmi.RemoteException
     */
    public BlagueProviderPairAPair[] register(BlagueProviderPairAPair ref) throws RemoteException;

    /**
     *
     * @param ref
     * @throws java.rmi.RemoteException
     */
    public void disconnect(BlagueProviderPairAPair ref) throws RemoteException;
}
