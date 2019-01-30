package couchePresentation;

import vues.*;
import coucheAccesDB.*;
import ClassMetier.*;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.stage.Stage;


public class MsgBox extends BaseFenetre
    {
        @FXML private Label LMsgErreur;
        @FXML private ImageView IVImage;
        /**
         * Constructeur : il crée la fenêtre
         * @param fenParent : l'objet Stage représentant la fenêtre parent
         * @param typeErreur : la nature du message à afficher (information, attention ou erreur)
         * @param msgErreur : le message à afficher
         */
        public MsgBox(Stage fenParent, AlertType typeErreur, String msgErreur)
        {
            super(fenParent, "MsgBoxVue.xml", "", 470, 100);
// fixer l'image et le message dans la fenêtre
            if(typeErreur == AlertType.INFORMATION)
            {
                IVImage.setImage(new Image("file:imgs/imgsmsgbox/information.jpg"));
                setTitle("Information");
            }else if(typeErreur == AlertType.WARNING)
        {
            IVImage.setImage(new Image("file:imgs/imgsmsgbox/attention.jpg"));
            setTitle("Attention");
        }else if(typeErreur == AlertType.ERROR)
        {
            IVImage.setImage(new Image("file:imgs/imgsmsgbox/erreur.jpg"));
            setTitle("Erreur");
        }
            LMsgErreur.setText(msgErreur);
            showAndWait();
        }
        /**
         * méthode exécutée quand on clique sur le bouton Fermer
         */
        @FXML
        private void BFermer(ActionEvent event)
        {
            close();
        }
    }

