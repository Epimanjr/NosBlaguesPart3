/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codebase;

/**
 *
 * @author Maxime Blaise
 * @author Antoine Nosal
 */
public interface AnnuaireInterface {

    /**
     * Méthode qui prend en paramètre la référence distante du BlagueProvider à
     * enregistrer et retourne le tableau des références déjà enregistrées
     *
     * @param ref
     * @return
     */
    public BlagueProviderPairAPair[] register(BlagueProviderPairAPair ref);

    /**
     *
     * @param ref
     */
    public void disconnect(BlagueProviderPairAPair ref);
}
