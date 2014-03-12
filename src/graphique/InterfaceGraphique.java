package graphique;

import blague.Blague;
import blagueprovider.BlagueProvider;
import codebase.BlagueProviderPairApair;
import exception.BlagueAbsenteException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NotBoundException;
import java.util.Iterator;
import java.util.Set;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InterfaceGraphique extends JFrame {

    /**
     * blague provider observ�
     */
    public BlagueProvider bp;

    /**
     * la liste � gerer pour l'affichage des blagues locales mise � jour avec
     * MaJBlagues()
     */
    JList blaguesLocales;

    /**
     * la liste � gerer pour l'affichage des blagues distantes mise � jour avec
     * MaJBlaguesDistantes()
     */
    JList blaguesDistantes;

    /**
     * la liste � gerer pour l'affichage des serveurs mise � jour avec
     * MaJServeurs()
     */
    JList serveurs;

    /**
     * ajoute l'onglet distant � l'interface
     *
     * @return
     */
    public JPanel ongletDistant() {

        JPanel pdistant = new JPanel();

        //la boite contenant tout
        Box distant = new Box(BoxLayout.Y_AXIS);

        //etiquette 1
        distant.add(new JLabel("Serveur distant"));

        //la partie serveur distant
        JPanel Pserveurs = new JPanel();
        serveurs = new JList();
        serveurs.setPreferredSize(new Dimension(300, 200));
        Pserveurs.add(serveurs);
        //ajouter boite
        distant.add(Pserveurs);

        //etiquette2
        distant.add(new JLabel("Blague sur serveur selectionne"));

        //la partie blague distante
        JPanel PblaguesDistantes = new JPanel();
        blaguesDistantes = new JList();
        MaJServeurs();
        blaguesDistantes.setPreferredSize(new Dimension(300, 200));
        PblaguesDistantes.add(blaguesDistantes);
        //ajoute ? la boite
        distant.add(PblaguesDistantes);

        // bouton de sauvegarde
        JButton bouton = new JButton("telecharge");
        
        // Ajout du listener sur telecharge
        bouton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // On récupère le nom de la blague
                String nomblague = (String) blaguesDistantes.getSelectedValue();
                
                // On récupère la ref
                String nomref = (String) serveurs.getSelectedValue();
                BlagueProviderPairApair ref = bp.getListeRef().get(nomref);
                
                bp.telechargeBlague(ref, nomblague);
                
                // Mise à jour des blagues locales
                MaJBlagues();
                
            }
        });
        distant.add(bouton);

        //encapsuler dans un jpanel
        pdistant.add(distant);

        serveurs.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                //Mise à jour de la liste des blagues distantes
                MaJBlagueDist();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

        });

        pdistant.repaint();
        return (pdistant);
    }

    /**
     * ajoute l'onglet local � l'interface
     *
     * @return
     */
    public JPanel ongletLocal() {
        //l'onglet local
        final JPanel local = new JPanel();
        Box blocal = new Box(BoxLayout.Y_AXIS);

        //etiquette 1
        blocal.add(new JLabel("Blagues connues en local"));

        //blagues locales
        JPanel PblaguesLocales = new JPanel();
        blaguesLocales = new JList();
        MaJBlagues();
        blaguesLocales.setPreferredSize(new Dimension(300, 200));
        PblaguesLocales.add(blaguesLocales);
        //ajout dans boite
        blocal.add(PblaguesLocales);

        //etiquette2
        blocal.add(new JLabel("Information blague locale"));

        //les informations sur les blagues
        //nom de la blague
        final JTextField nom = new JTextField();
        blocal.add(nom);

        //contenu 
        final JTextField question = new JTextField();
        blocal.add(question);

        //reponse
        final JTextArea reponse = new JTextArea();
        reponse.setPreferredSize(new Dimension(300, 200));
        blocal.add(reponse);

        //bouton de sauvegarde
        JButton bouton = new JButton("sauve");
        
        // Ajout Listener sur sauve
        bouton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                // Création de la blague
                Blague b = new Blague(nom.getText(), question.getText(), reponse.getText());
                
                // Ajout à bp
                bp.ajoutBlague(b);
                
                // Mise à jour des blagues
                MaJBlagues();
                
                local.repaint();
            }
        });
        
        blocal.add(bouton);

        local.add(blocal);

        blaguesLocales.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                String nomblague = (String) blaguesLocales.getSelectedValue();
                try {
                    Blague b = bp.getBlague(nomblague);
                    nom.setText(b.getNom());
                    question.setText(b.getQuestion());
                    reponse.setText(b.getReponse());
                } catch (BlagueAbsenteException ex) {
                    Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
            public void mouseExited(MouseEvent me) {
            }

        });

        local.repaint();
        return (local);
    }

    /**
     * construction de l'interface
     *
     * @param nom
     * @param bp
     */
    public InterfaceGraphique(String nom, BlagueProvider bp) {
        super("Blaguemule: " + nom);

        //mise � jour du lien vers le modele
        this.bp = bp;

        //construction de l'interface
        JTabbedPane onglets = new JTabbedPane();
        onglets.addTab("local", ongletLocal());
        onglets.addTab("distant", ongletDistant());

        //affichage du JFRAME
        setContentPane(onglets);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * methode charg�e de mettre � jour l'affichage des blagues � partir de bp
     */
    public void MaJBlagues() {
        blaguesLocales.setListData(bp.getAllNames());
    }

    /**
     * methode charg�e de mettre � jour l'affichage des serveurs � partir de bp
     * quand lisRef est modifi�
     */
    public void MaJServeurs() {
        //Création d'un tableau contenant les noms des serveurs
        String[] tabServeurs = new String[bp.getListeRef().size()];

        //Parcourt de la HashMap via un itérateur
        Set cles = bp.getListeRef().keySet();
        Iterator it = cles.iterator();
        int iterateurRes = 0;
        while (it.hasNext()) {
            String cle = (String) it.next();
            tabServeurs[iterateurRes] = cle;
            iterateurRes++;
        }
        //Remplissage de l'attribut serveurs avec la liste de noms
        serveurs.setListData(tabServeurs);
    }

    /**
     * methode charg�e de mettre � jour l'affichage des blagues distantes quand
     * on selectionne un listeref
     */
    public void MaJBlagueDist() {
        //Récupération du nom du serveur actuellement sélectionné
        String nomserveur = (String) serveurs.getSelectedValue();
        //Récupération du serveur à partir du nom
        BlagueProviderPairApair serveur = bp.getListeRef().get(nomserveur);
        try {
            //Remplissage de l'attribut blaguesDistantes avec le nom des blagues
            blaguesDistantes.setListData(serveur.getAllNames());
        } catch (RemoteException ex) {
            Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Méthode principale.
     *
     * @param args
     */
    public static void main(String[] args) {
        //On test args
        if (args.length < 1) {
            System.out.println("Erreur : Manque un argument !");
        } else {
            //Création de l'objet
            BlagueProvider bp = new BlagueProvider(args[0]);

            // Ajout des références
            //contact avec le rmiregistry de host
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry();

                for (int i = 1; i < args.length; i++) {
                    // affichage
                    System.out.println("Récupération de " + args[i] + " ...");

                    // Récuperation de la reference distante
                    BlagueProviderPairApair proxy = (BlagueProviderPairApair) registry.lookup(args[i]);

                    // Ajout de la référence
                    bp.ajoutReference(args[i], proxy);
                }

                //Export
                BlagueProviderPairApair ri = (BlagueProviderPairApair) UnicastRemoteObject.exportObject(bp, 0);
                registry.rebind(args[0], ri);

                //testUnitaire(args[0], bp);
                System.out.println("Client lancé !");

                bp.ajoutBlague(new Blague("nom1", "question1", "reponse1"));
                bp.ajoutBlague(new Blague("nom2", "question2", "reponse2"));

                // Création de l'interface
                InterfaceGraphique ig = new InterfaceGraphique(bp.getNom(), bp);

            } catch (RemoteException | NotBoundException ex) {
                Logger.getLogger(InterfaceGraphique.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
