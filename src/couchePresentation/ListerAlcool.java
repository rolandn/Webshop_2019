package couchePresentation;

import ClassMetier.*;
import coucheAccesDB.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class ListerAlcool extends BaseFenetre{

    @FXML private TableView<Alcool> TVAlcool;
    @FXML private Button BFermer;
    @FXML private ImageView IVImage;


    /**
     * Constructeur : il crée la fenêtre
     * @param fenParent : l'objet Stage représentant la fenêtre parent
     */
    public ListerAlcool(Stage fenParent)
    {
            // créer la fenêtre
        super(fenParent, "ListerProfesseursVue.xml", "Lister les professeurs", 475, 300);
            // ajouter la liste des professeurs à la table TVProfs

        try
        {
            TVAlcool.itemsProperty().setValue(FXCollections.observableArrayList(
                    FabriqueDAO.getInstance().getInstAlcoolDAO().ListerTous()));
        }
        catch(ExeceptionAccessBD e)
        {
            GererErreur.GererErreurSQL("ListerProfesseurs", "ListerProfesseurs()", e.getMessage());
            new MsgBox(this, AlertType.ERROR,
                    "Problème de base de données lors du listage des professeurs!");
            return;
        }
        if(TVAlcool.getItems().size() == 0)
        {
            new MsgBox(this, AlertType.INFORMATION,
                    "Il n'y a aucun professeur dans la base de données!");
            return;
        }
// gérer l'affichage de l'image quand on clique sur un professeur dans la table
        TVAlcool.getSelectionModel().selectedItemProperty().addListener((obs, ancProf,
                                                                        nouvProf) ->
        {
            if (nouvProf != null)
                IVImage.setImage(new Image("file:imgs/imgsprofs/" + nouvProf.getNomImage()));
        });
// sélectionner le 1er prof dans la table TVProfs
        TVAlcool.getSelectionModel().selectFirst();
// afficher la fenêtre
        showAndWait();
    }
    /**
     * Méthode exécutée lorsque l'utilisateur clique sur le bouton Fermer
     */
    @FXML
    private void BFermer(ActionEvent event)
    {
        close();
    }
}

