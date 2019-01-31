package couchePresentation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import coucheAccesDB.*;


public class FenetrePrincipale extends Application {
    private final int Largeur = 1500;
    private final int Longueur = 1000;

    private static Stage Instance;

    private Scene SceneObj;
    private BorderPane BPane = new BorderPane();
    private MenuBar BarreMenu = new MenuBar();

    private Menu MenuClient = new Menu("Clients");
    private MenuItem MIAjouterClient = new MenuItem("Ajouter un client");
    private MenuItem MISupprimerClient = new MenuItem("Supprimer un client");
    private MenuItem MIModifierClient = new MenuItem("Modifier un client");
    private MenuItem MIListerClient = new MenuItem("Lister les clients");


    private Menu MenuCommande = new Menu("Commandes");
    private MenuItem MIListerCommandes = new MenuItem("Détail d'une commande");
    private MenuItem MILivraison = new MenuItem("Livraison");
    private MenuItem MICOmmandesClient = new MenuItem("Commandes par client");

    private Menu MenuAlcool = new Menu("Alcools");
    private MenuItem MIAjouterAlcool = new MenuItem("Ajouter un alcool");

    private Menu MenuStock = new Menu("Disponibilité des articles");
    private MenuItem MIModifierStock = new MenuItem("Modifier le stock ou supprimer un produit");

    private Menu Quitter = new Menu("Quitter");
    private MenuItem QuitterProgramme = new MenuItem("Quitter le programme");


    /**
     * Méthode statique qui retourne l'objet Stage de la fenêtre principale
     *
     * @return l'objet Stage de la fenêtre
     */

    public static Stage getInstance()
    {
        return Instance;
    }

    /**
     * Constructeur: il ajoute tous les objets à la fenêtre
     * @param fenetre: l'objet Stage représentant la fenêtre principale
     */

    @Override
    public void start(Stage fenetre) {
        Instance = fenetre;

        // barre des menus

        MenuClient.getItems().addAll(MIAjouterClient, MISupprimerClient, MIModifierClient, MIListerClient);
        MenuAlcool.getItems().addAll(MIAjouterAlcool);
        MenuStock.getItems().addAll(MIModifierStock);
        MenuCommande.getItems().addAll(MIListerCommandes, MILivraison, MICOmmandesClient);


        BarreMenu.getMenus().addAll(MenuClient);
        BarreMenu.getMenus().addAll(MenuAlcool);
        BarreMenu.getMenus().addAll(MenuStock);
        BarreMenu.getMenus().addAll(MenuCommande);

        BarreMenu.prefWidthProperty().bind(fenetre.widthProperty());

        // Actions liées éléments de menu

       /* MIAjouterClient.setOnAction(event -> {
            new AjouterClient();
        });
        MISupprimerClient.setOnAction(event -> {
            new SupprimerClient();
        });
        MIModifierClient.setOnAction(event -> {
            new ModifierClient();
        });
        MIListerClient.setOnAction(event -> {
            new ListerClient();
        });

        MIAjouterAlcool.setOnAction(event -> {
                    new AjouterAlcool();
                });

        MIModifierStock.setOnAction(event -> {
            new ModifierStock();
        });

        MIListerCommandes.setOnAction(event -> {
            new ListerCommandes();
        });

        MILivraison.setOnAction(event -> {
            new Livraisons();
        });

        MICOmmandesClient.setOnAction(event -> {
            new CommandesClient();
        });
*/
        QuitterProgramme.setOnAction(event -> {
            System.exit(0);
        });

        // Barre des menus BPaine et Scene

        BPane.setTop(BarreMenu);
        SceneObj = new Scene(BPane, Largeur, Longueur, Color.WHITE);
        fenetre.setScene(SceneObj);

        // Style CSS

        SceneObj.getStylesheets().add("couchePresentation/styleMenu.css");

        // Parametrer la fenetre puis l'afficher

        fenetre.setTitle("Backoffice WebShop");
        fenetre.setResizable(false);
        fenetre.centerOnScreen();
        fenetre.show();
    }
        /**
         * Méthode exécutée au démarrage de l'application
         */

//        public static void main(String[] args)
//        {
//            try
//            {
//                FabriqueDAO.getInstance().creerConnexion();
//            }
//            catch (ExceptionAccessBD e)
//            {
//                GererErreur.GererErreurGen("FenêtrePrincipale", "start()", e.getMessage() );
//                System.out.println("problème pour se connecter à la DB");
//                System.exit(0);
//            }


        // Charger la fenêtre principale

//        launch(args);
//    }

}
