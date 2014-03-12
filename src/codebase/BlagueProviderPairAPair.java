package codebase;

import blague.Blague;
import exception.BlagueAbsenteException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Maxime Blaise
 * @author Antoine Nosal
 */
public interface BlagueProviderPairAPair extends Remote {

    /**
     * récupère le nom du blagueprovider
     *
     * @return
     * @throws java.rmi.RemoteException
     */
    public String getNom() throws RemoteException;

    /**
     * Retournant la liste des noms des blagues connues par le
     * BlagueProviderPairAPair
     *
     * @return
     * @throws java.rmi.RemoteException
     */
    public String[] getAllNames() throws RemoteException;

    /**
     * Retournant la blague ayant pour nom le String passé en parametre.
     *
     * @param nom
     * @return
     * @throws exception.BlagueAbsenteException
     * @throws java.rmi.RemoteException
     */
    public Blague getBlague(String nom) throws BlagueAbsenteException, RemoteException;

    /**
     * Méthode appelé par Annuaire sur les serveurs distants qu'il référence
     * lorsqu'un nouveau BlagueProvider s'enregistre
     *
     * @param ref
     * @throws java.rmi.RemoteException
     */
    void notify(BlagueProviderPairAPair ref) throws RemoteException;

    /**
     * Méthode appelée par Annuaire lorqu'un BlagueProvider se déconnecte
     *
     * @param ref
     * @throws java.rmi.RemoteException
     */
    void notifyDeconnect(BlagueProviderPairAPair ref) throws RemoteException;

}
