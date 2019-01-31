package couchePresentation;

import ClassMetier.*;
import ClassMetier.Biere;
import coucheAccesDB.FabriqueDAO;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class ListerBiere extends BaseFenetre {

    @FXML private TableView<Biere> TVBiere;
    @FXML private Button BFermer;
    @FXML private ImageView IVImage;


    public ListerBiere(Stage fenParent) {

        // créer la fenêtre
        super(fenParent, "ListerBiere.xml", "Lister les bières", 475, 300);

        // ajouter la liste des bières à la table TVBiere

        TVBiere.itemsProperty().setValue(FXCollections.observableArrayList(
                FabriqueDAO.getInstance().getInstBiereDAO().ListerTous()));
        if (TVBiere.getItems().size() == 0) {
            new MsgBox(this, AlertType.INFORMATION,
                    "Il n'y a aucune bière dans la base de données!");
            return;
        }

        // gérer l'affichage de l'image quand on clique sur une bière dans la table
        TVBiere.getSelectionModel().selectedItemProperty().addListener((obs, ancBiere,
                                                                        nouvBiere) ->
        {
            if (nouvBiere != null)
                IVImage.setImage(new Image("file:imgs/imgsprofs/" + nouvBiere.getNomImage()));
        });
        // sélectionner le 1er prof dans la table TVProfs
        TVBiere.getSelectionModel().selectFirst();
        // afficher la fenêtre
        showAndWait();
    }

    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton Fermer
     */
    @FXML private void BFermer(ActionEvent event) {
        close();
    }



}