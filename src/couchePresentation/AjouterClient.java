package couchePresentation;

import com.intellij.diagnostic.TestMessageBoxAction;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.io.File;
import java.nio.file.Files;
import ClassMetier.*;
import coucheAccesDB.*;

public class AjouterClient {

    private final int Largeur = 900;
    private final int Hauteur = 500;
    private Stage Fenetre = new Stage();
    private Scene SceneObj;
    private HBox HBSaisies = new HBox(15);
    private HBox HBBoutons = new HBox(15);
    private HBox HBImg = new HBox(15);
    private GridPane GPSaisies = new GridPane();
    private VBox VBZonesFenetre = new VBox();

    private TextField TFNumClient = new TextField();
    private TextField TFNom = new TextField();
    private TextField TFPrenom = new TextField();
    private TextField TFAdresse = new TextField();
    private TextField TFEmail = new TextField();

    private Button BAjouterClient = new Button("Ajouter");
    private Button BFermer = new Button("Fermer");
    private Separator SLigne = new Separator();

    /**
     * Constructeur : il crée la fenêtre
     *
     * @param  : l'objet Stage représentant la fenêtre principale
     */
    public AjouterClient() {
        GPSaisies.add(new Label("Numéro de Client"), 0, 0);
        GPSaisies.add(TFNumClient, 1, 0);

        GPSaisies.add(new Label("Nom"), 0, 1);
        GPSaisies.add(TFNom, 1, 1);

        GPSaisies.add(new Label("Prénom"), 0, 2);
        GPSaisies.add(TFPrenom, 1, 2);

        GPSaisies.add(new Label("Adresse"), 0, 3);
        GPSaisies.add(TFAdresse, 1, 3);

        GPSaisies.add(new Label("Email"), 0, 4);
        GPSaisies.add(TFEmail, 1, 4);

        // espacement entre les cellules de GPSaisies
        GPSaisies.setHgap(8);
        GPSaisies.setVgap(8);

        // changer la taille des boites
        TFAdresse.setMaxWidth(600);
        TFEmail.setMaxWidth(600);

        // paramétrer les boutons BAjouter et BFermer
        BAjouterClient.setPrefSize(150, 20);
        BAjouterClient.setOnAction(event -> {BAjouterClient();} );

        BFermer.setPrefSize(150, 20);
        BFermer.setOnAction(e -> {Fenetre.close();});

        // GPSaisies et IVImage -> HBSaisies
        HBSaisies.getChildren().addAll(GPSaisies);  // LIGNE QUI VA FAIRE APPARAITRE LES CHAMPS !!

        // BAjouter et BFermer -> HBBoutons
        HBBoutons.getChildren().addAll(BAjouterClient, BFermer);

        // HBSaisies, SLigne et HBBoutons -> VBZonesFenetre
        VBZonesFenetre.getChildren().addAll(HBSaisies, SLigne, HBBoutons);

        // définir les marges autour des objets dans VBZonesFenetre
         VBox.setMargin(HBSaisies, new Insets(15, 15, 10, 15));
        VBox.setMargin(SLigne, new Insets(0, 15, 0, 15));
        VBox.setMargin(HBBoutons, new Insets(10, 0, 15, 400));


        // définir les marges autour des objets dans VBZonesFenetre
        VBox.setMargin(HBSaisies, new Insets(15, 15, 10, 15));
        VBox.setMargin(SLigne, new Insets(0, 15, 0, 15));
        VBox.setMargin(HBBoutons, new Insets(10, 0, 15, 400));

        SceneObj = new Scene(VBZonesFenetre, Largeur, Hauteur);
        Fenetre.setScene(SceneObj);

        SceneObj.getStylesheets().add("couchePresentation/styleComboBox.css");

        // paramétrer la fenêtre, puis l'afficher
        Fenetre.setTitle("Ajouter un nouveau Client");
        Fenetre.setResizable(true);
        Fenetre.setX(FenetrePrincipale.getInstance().getX() +
                (FenetrePrincipale.getInstance().getWidth() - Largeur) / 2);
        Fenetre.setY(FenetrePrincipale.getInstance().getY() +
                (FenetrePrincipale.getInstance().getHeight() - Hauteur) / 2);
        Fenetre.initOwner(FenetrePrincipale.getInstance());
        Fenetre.initModality(Modality.APPLICATION_MODAL);
        Fenetre.showAndWait();
    }

    /**
     * Méthode qui exécute l'ajout quand on clique sur le bouton ajouter.
     */

    private void BAjouterClient() {
        try {
            Client client = new Client();

            client.setNumClient(Integer.parseInt(TFNumClient.getText()));
            client.setNom(TFNom.getText());
            client.setPrenom(TFPrenom.getText());
            client.setAdresse(TFAdresse.getText());
            client.setEmail(TFEmail.getText());

            if (FabriqueDAO.getInstance().getInstClientDAO().Ajouter(client) == false)
                new MessageBox(AlertType.INFORMATION, "L'ajout n'a pas eu lieu.");

            else
                new MessageBox(AlertType.INFORMATION, "L'ajout s'est bien passé.");
        } catch (ExceptionAccessBD e) {
            GererErreur.GererErreurSQL("AjouterClient", "BAAjouterClient()", e.getMessage());
            new MessageBox(AlertType.ERROR, "Problème de base de données lors de l'ajout du client");
        } catch (ExceptionMetier e) {
            new MessageBox(AlertType.WARNING, e.getMessage());
            return;
        } catch (Exception e) {
            GererErreur.GererErreurSQL("AjouterClient", "BAAjouterClient", e.getMessage());
            new MessageBox(AlertType.ERROR, "problème inattendu lors de l'ajout du client");
        }

        Fenetre.close();

    }
}
