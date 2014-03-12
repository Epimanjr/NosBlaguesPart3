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
public interface BlagueProviderPairApair extends Remote {

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
}
