package couchePresentation;

import ClassMetier.*;
import coucheAccesDB.*;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ListerCommandes
{
    private final int Largeur = 845;
    private final int Hauteur = 450;

    // Stage containing Scene containing layout
    private Stage Fenetre = new Stage();
    private Scene SceneObj;
    private ComboBox <Commande> CBChoixCommande = new ComboBox();
    private ComboBox<Client> CBChoixClient = new ComboBox();
    private TableView<Commande> TVCommande = new TableView<>();
//    private Label LBMontantTotal = new Label("Montant totale (eur) : ");
//    private TextField TFMontantTotal = new TextField();

    private AnchorPane APZonesFenetre = new AnchorPane();
    private TableView<Produit> TVProduit = new TableView<>();
    private Button BFermer = new Button("Fermer");

    private TableColumn<Produit,String> TCidProduit;
    private TableColumn<Produit,String> TCnom;
    private TableColumn<Produit,String> TCquantiteStock;
    private TableColumn<Produit,String> TCprix;



    private TableColumn<Commande, String> TCidCommande;
    private TableColumn<Commande, String> TCdate;
    private TableColumn<Commande, String> TCmontant;
    private TableColumn<Commande, String> TClivre;
    private TableColumn<Commande, String> TCheure;

    private ImageView IVImage = new ImageView();
    /**
     * Constructeur : il crée la fenêtre
     *
     */
    @SuppressWarnings("unchecked")

    public ListerCommandes()
    {

        //créer les objets TableColumn
        TCidCommande = new TableColumn<>("idCommande");
   //     TCdate = new TableColumn<>("Date");
        TCmontant = new TableColumn<>("Montant");
        TClivre = new TableColumn<>("Etat livraison");
   //     TCheure = new TableColumn<>("Heure");

        //Set leur largueur
        TCidCommande.setMinWidth(60);
   //     TCdate.setMinWidth(80);
  //     TCheure.setMinWidth(80);
        TCmontant.setMinWidth(80);
        TClivre.setMinWidth(100);
  //      TFMontantTotal.setMaxWidth(90);

        // les ajouter a la TableView
//        TVCommande.getColumns().addAll(TCidCommande, TCdate,TCheure, TCmontant, TClivre);
        TVCommande.getColumns().addAll(TCidCommande, TCmontant, TClivre);
        // configurer la tableView
        TVCommande.setPrefSize(600,Hauteur-100);
        TVCommande.setEditable(false);

        // indiquer le nom de chaque propriété d'une Commande...
        // ... à associer à chaque colonne dans la table
        TCidCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
   //     TCdate.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        TCmontant.setCellValueFactory(new PropertyValueFactory<>("montant"));
        TClivre.setCellValueFactory(new PropertyValueFactory<>("livre"));
   //     TCheure.setCellValueFactory(new PropertyValueFactory<>("heure"));



        // remplit la comboBox Commande des commandes en DB
        try
        {
            List<Commande> commandes = FabriqueDAO.getInstance().getInsCommandeDAO().ListerTous();
            CBChoixCommande.setItems(FXCollections.observableArrayList(commandes));
        }

        catch (Exception e)
        {
            GererErreur.GererErreurSQL("ListerProduitCommande","ListerProduitCommande()",e.getMessage());
        }

        if (CBChoixCommande.getItems().size() == 0)
        {
            new MessageBox(AlertType.INFORMATION,"Il n'y a aucune commande dans la base de données!");
            return;
        }
        // gérer le changement de commande courant dans la boîte combo CBCommande
        CBChoixCommande.getSelectionModel().selectedItemProperty().addListener((obs, ancCom,
                                                                                nouvCom) ->
        {
            if (nouvCom != null) CBChangerCommande(nouvCom);
        });

        // selectionner la premiere commande dans le comboBox
        CBChoixCommande.getSelectionModel().selectFirst();
        CBChoixCommande.setVisibleRowCount(5);

        // gérer l'affichage de l'image quand on clique sur un produit dans la table
        TVProduit.getSelectionModel().selectedItemProperty().addListener((obs,ancProduit,nouvProduit)->{
            AfficheImage(nouvProduit);
        });
        //afficher ce premier produit BUG : pourquoi ca ne s'affiche pas ????
        TVProduit.getSelectionModel().selectFirst();
        // tenter l'affichage de FORCE :
        AfficheImage(TVProduit.getSelectionModel().getSelectedItem());

        // LAYOUT ----------------------------------------------------------------

        //créer les objets TableColumn
        TCidProduit = new TableColumn<>("ID Produit");
        TCnom = new TableColumn<>("IdLigne");
        TCquantiteStock = new TableColumn<>("Quantité en Stk");
        //TCprix = new TableColumn<>("Prix");
        //Set leur largueur
        TCidProduit.setMinWidth(200);
        TCnom.setMinWidth(200);
        TCquantiteStock.setMinWidth(250);
       // TCprix.setMinWidth(150);
        // les ajouter a la TableView
        TVProduit.getColumns().addAll(TCidProduit,TCnom,TCquantiteStock);

        // configurer la tableView
        TVProduit.setPrefSize(850,Hauteur-200);
        TVProduit.setEditable(false);

        // indiquer le nom de chaque propriété d'un Produit...
        // ... à associer à chaque colonne dans la table
        TCidProduit.setCellValueFactory(new PropertyValueFactory<>("IdProduit"));
        TCnom.setCellValueFactory(new PropertyValueFactory<>("IdLigne"));
        TCquantiteStock.setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        //TCprix.setCellValueFactory(new PropertyValueFactory<>("prix"));

        // paramétrer le bouton BFermer
        BFermer.setPrefSize(220, 20);
        BFermer.setOnAction(e -> { Fenetre.close(); });

        // Bfermer, IVImage et TVProduit -> APZonesFenetre
        APZonesFenetre.getChildren().addAll(TVProduit,CBChoixCommande, IVImage, BFermer);
        AnchorPane.setTopAnchor(TVProduit, 15.0);
        AnchorPane.setLeftAnchor(TVProduit, 15.0);
        AnchorPane.setTopAnchor(IVImage, 55.0);
        AnchorPane.setRightAnchor(IVImage, 15.0);
        AnchorPane.setBottomAnchor(BFermer, 15.0);
        AnchorPane.setRightAnchor(BFermer, 15.0);
        AnchorPane.setLeftAnchor(CBChoixCommande,15.0);
        AnchorPane.setBottomAnchor(CBChoixCommande,15.0);

        // APZonesFenetre -> Scene; Scene -> Stage
        SceneObj = new Scene(APZonesFenetre, Largeur, Hauteur);
        Fenetre.setScene(SceneObj);

        // charger les styles CSS
        SceneObj.getStylesheets().add("couchePresentation/stylesTableView.css");

        // paramétrer la fenêtre, puis l'afficher
        Fenetre.setTitle("Lister les produits d'une commande");
        Fenetre.setResizable(false);
        Fenetre.setX(FenetrePrincipale.getInstance().getX() +
                (FenetrePrincipale.getInstance().getWidth() - Largeur) / 2);
        Fenetre.setY(FenetrePrincipale.getInstance().getY() +
                (FenetrePrincipale.getInstance().getHeight() - Hauteur) / 2);
        Fenetre.initOwner(FenetrePrincipale.getInstance());
        Fenetre.initModality(Modality.APPLICATION_MODAL);
        Fenetre.showAndWait();

    }

    private void AfficheImage(Produit nouvProduit)
    {
        if(nouvProduit != null)
        {
            String type = (nouvProduit instanceof Chemise)?"imgschemise/":"imgsalcool/";
            String url = "file:C:/Users/rolan"+type+nouvProduit.getNomImage();
//            file:D:/CODE/PROJECTS/exotic/exoticJava/imgs
            IVImage.setImage(new Image(url));
        }
    }

//    private void CBListerLignCom (LigneCom com)
//            // piste : on devrait utiliser la fct° dans une nouvelle fenêtre/interface mais il n'en existe aucune.
//            // en fait on devrait créer une nouvelle fenêtre(nouveau fichier ListerDetailCommande) qui
//            // serait le même combobix que pour lister résultats élèves.
//    {
//        try {
//            int idLigne = com.getIdLigne();
//            List<LigneCom> commande = FabriqueDAO.getInstance().getInsLigneComDAO().ListerTous();
//
//            //   List<Alcool> alcools = FabriqueDAO.getInstance().getInstAlcoolDAO().ListerCommande(idCommande);
//
//            List<LigneCom> produits = new ArrayList<>();
//            produits.addAll(produits);
//
//
//            TVProduit.itemsProperty().setValue(FXCollections.observableArrayList(produits));
//            TVProduit.getSelectionModel().selectFirst();
//        }
//
//        catch(ExceptionAccessBD e)
//        {
//            GererErreur.GererErreurSQL("ListerProduitCommande", "ListerProduitCommande()", e.getMessage());
//            new MessageBox(AlertType.ERROR,
//                    "Problème de base de données lors du listage des produits!");
//            return;
//        }
//    }

    private void CBChangerCommande(Commande c)
    {
        try
        {
            int idCommande = c.getIdCommande();
            List<Produit> commande = FabriqueDAO.getInstance().getInsProduitDAO().ListerCommande(idCommande);
         //   List<Alcool> alcools = FabriqueDAO.getInstance().getInstAlcoolDAO().ListerCommande(idCommande);

            List<Produit> produits = new ArrayList<>();
            produits.addAll(produits);
        //    produits.addAll(chemises);
        //    produits.addAll(alcools);

            TVProduit.itemsProperty().setValue(FXCollections.observableArrayList(produits));
            TVProduit.getSelectionModel().selectFirst();
        }
        catch(ExceptionAccessBD e)
        {
            GererErreur.GererErreurSQL("ListerProduitCommande", "ListerProduitCommande()", e.getMessage());
            new MessageBox(AlertType.ERROR,
                    "Problème de base de données lors du listage des produits!");
            return;
        }
    }

}

