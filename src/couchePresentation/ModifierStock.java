package couchePresentation;


import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ButtonBase;
import java.io.File;


import ClassMetier.*;
import coucheAccesDB.*;


    public class ModifierStock {
        private final int Largeur = 900;
        private final int Hauteur = 400;

        private Stage Fenetre = new Stage();
        private Scene SceneObj;
        private HBox HBSaisies = new HBox(15);
        private HBox HBBoutons = new HBox(15);
        private HBox HBoxImg = new HBox(15);
      //  private GridPane GPSaisies = new GridPane();
        private VBox VBZonesFenetre = new VBox();
        private ComboBox<Produit> CBProduit = new ComboBox<>();
        private TextField TFNumArticle = new TextField();
        private TextField TFnom = new TextField();
        private TextField TFnomIMage = new TextField();
        private TextField TFprix = new TextField();
        private TextField TFquantiteStock = new TextField();
        private Button BChargerImage = new Button("...");
        private Button BModifier = new Button("Appliquer les modifications");
        private Button BFermer = new Button("Fermer");
        private Separator SLigne = new Separator();
        private Separator SLigne1 = new Separator();
        private ImageView IVImage = new ImageView();
        private File FichierSrc;
        private TextField TFChangementQuantite = new TextField();
        private  TextField TFArchiver = new TextField();
        //private ComboBox<Produit> CBChoixProduit = new ComboBox();
        private GridPane GPModifications = new GridPane();
        private HBox HBSaisie = new HBox(15);
        private Label labChoixProduit = new Label("Choix du Produit :");
        private Label labQuantite = new Label("Quantité :");
        private CheckBox CBox = new CheckBox("Cocher pour archiver le produit");



        public ModifierStock() {

            /**
             * On affiche d'abord la liste des produits
             */


            try {

                CBProduit.setItems(FXCollections.observableArrayList(
                        FabriqueDAO.getInstance().getInsProduitDAO().ListerTous()
                ));
            } catch (ExeceptionAccessBD e) {
                GererErreur.GererErreurSQL("ModifierStock, ", "ModifierProduit()", e.getMessage());
                new MessageBox(AlertType.INFORMATION,
                        "Un problème est survenu lors du listage des produits.");

                return;
            }

            if (CBProduit.getItems().size() == 0) {
                new MessageBox(AlertType.INFORMATION, "Il n'y a encore aucun produit dans la DB.");
                return;
            }

            CBProduit.setVisibleRowCount(10);
            CBProduit.getSelectionModel().selectFirst();


            /**
             * On gérer le changement de Produit courant dans la boîte combo CBChoixProduit
             */

            CBProduit.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                                   ancProduit, nouvProduit) ->
            {
                if (nouvProduit != null) BChangerQuantie((nouvProduit));

            });


            // LAYOUT ----------------------------------------------------------------

            // Fenêtre principale



            //taille TFQuantite
            TFChangementQuantite.setMaxWidth(80);
           // TFChangementID.setMaxWidth(80);
            CBProduit.setPrefSize(300,20);

            // Taille liste des produits
            CBProduit.setMaxWidth(300);

            //Construction du gridpane
            GPModifications.add(labChoixProduit,0,0);
            GPModifications.add(CBProduit,1,0);
            GPModifications.add(labQuantite,0,1);
            GPModifications.add(TFChangementQuantite, 1, 1);
            GPModifications.add(CBox, 0, 2);


            // espacement entre les cellules de GPSaisies
            GPModifications.setHgap(8);
            GPModifications.setVgap(8);

            //construction des HB  boutons + GPane
            HBBoutons.getChildren().addAll(BModifier,BFermer);
            // HBSaisie.getChildren().add(GPModifications);


            // paramétrer les boutons BAjouter et BFermer
            BModifier.setPrefSize(480, 70);
            BModifier.setOnAction(event -> {BModifierQuantite();});
            BFermer.setPrefSize(300, 70);
            BFermer.setOnAction(e -> { Fenetre.close(); });


            // GPModifications + Sligne + HBBoutons -> VBZoneFenetre
            VBZonesFenetre.getChildren().addAll(GPModifications,SLigne,HBBoutons);

            // définir les marges autour des objets dans VBZonesFenetre
            VBox.setMargin(GPModifications, new Insets(15, 15, 10, 15));
            VBox.setMargin(SLigne, new Insets(0, 15, 0, 15));
            VBox.setMargin(HBBoutons, new Insets(10, 20, 15, 20));

            // VBZonesFenetre -> Scene; Scene -> Stage
            SceneObj = new Scene(VBZonesFenetre, Largeur, Hauteur);
            Fenetre.setScene(SceneObj);

            // charger les styles CSS
            SceneObj.getStylesheets().add("couchePresentation/styleComboBox.css");

            // paramétrer la fenêtre, puis l'afficher
            Fenetre.setTitle("Modifier le stock ou supprimer un produit");
            Fenetre.setResizable(true);
            Fenetre.setX(FenetrePrincipale.getInstance().getX() +
                    (FenetrePrincipale.getInstance().getWidth() - Largeur) / 2);
            Fenetre.setY(FenetrePrincipale.getInstance().getY() +
                    (FenetrePrincipale.getInstance().getHeight() - Hauteur) / 2);
            Fenetre.initOwner(FenetrePrincipale.getInstance());
            Fenetre.initModality(Modality.APPLICATION_MODAL);
            Fenetre.showAndWait();

        }

        private void BModifierQuantite()
        {
            try
            {
                Produit produit = new Produit();

                produit.setActive(Boolean.parseBoolean(CBox.getText()));
                produit.setQuantiteStock(Integer.parseInt(TFChangementQuantite.getText()));
            //    produit.setNumArticle(Integer.parseInt(TFChangementID.getText()));


                    if (FabriqueDAO.getInstance().getInsProduitDAO().Modifier((Produit)produit) == false)
//                Produit p = CBProduit.getValue();
//
//                p.setQuantiteStock(Integer.parseInt(TFChangementQuantite.getText()));
//                p.setActive(Boolean.parseBoolean(TFArchiver.getText()));
//
//                    if(FabriqueDAO.getInstance().getInsProduitDAO().Modifier((Produit) p) == false)
                    {
                        new MessageBox(AlertType.INFORMATION, "Err 1 : La modification n'a pas eu lieu!");
                    }

                    else{
                        new MessageBox(AlertType.INFORMATION, "La modification s'est bien déroulée!");
                    }

            }
            catch (ExceptionAccessBD e)
            {
                GererErreur.GererErreurSQL("ModifierStock", "BModifierQuantite()", e.getMessage());
            }
            catch (Exception e)
            {
                GererErreur.GererErreurSQL("ModifierStock", "BModifierQuantite()", e.getMessage());
                new MessageBox(AlertType.ERROR, "Err 2 : Problème inattendu lors de la modification du produit !");
            }
            Fenetre.close();

        }


        private void BChangerQuantie(Produit nouvProduit)
        {
            TFChangementQuantite.setText(String.valueOf(nouvProduit.getQuantiteStock()));
            TFArchiver.setText(String.valueOf(nouvProduit.getActive()));
        }
    }