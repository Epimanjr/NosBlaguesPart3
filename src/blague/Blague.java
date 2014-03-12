package blague;

import java.io.Serializable;

/**
 * 
 * @author Maxime Blaise
 * @author Antoine Nosal
 */
public class Blague implements Serializable {
    
    /**
     * Nom de la blague.
     */
    private String nom;
    
    /**
     * La question de la blague.
     */
    private String question;
    
    /**
     * Et la réponse.
     */
    private String reponse;

    /**
     * Construit une blague, à partir du nom, de la question et de la réponse.
     * 
     * @param nom
     * @param question
     * @param reponse 
     */
    public Blague(String nom, String question, String reponse) {
        this.nom = nom;
        this.question = question;
        this.reponse = reponse;
    }

    /**
     * Permet de récupérer le nom de la blague.
     * 
     * @return 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de modifier le nom de la blague.
     * 
     * @param nom 
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Permet de récupérer la question de la blague.
     * 
     * @return 
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Permet de modifier la question de la blague.
     * 
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Permet de récupérer la réponse.
     * 
     * @return 
     */
    public String getReponse() {
        return reponse;
    }

    /**
     * Permet de modifier la réponse.
     * 
     * @param reponse 
     */
    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
    
    
    @Override
    public String toString() {
        String s = "\n~~ Blague #"+this.getNom()+"\n";
        
        s+= getColor(91) + this.getQuestion()+"\n" + getColor(0);
        
        s+= "\n\t\t\t\t-->"+getColor(92)+this.getReponse()+getColor(0) + "\n";
                
        return s;
    }
    
    /**
     * Permet de colorer le terminal Linux.
     *
     * @param i
     * @return
     */
    public static String getColor(int i) {
        return "\033[" + i + "m";
    }
}
