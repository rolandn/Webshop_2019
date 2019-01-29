package couchePresentation;
import coucheAccesDB.*;

import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class FenetrePrincipaleJava extends Application

{
    Stage Fenetre;
    /**
     * méthode start exécutée au démarrage : elle crée la fenêtre principale
     * @param fenetre : l'objet Stage représentant la fenêtre principale
     */
    @Override
    public void start(Stage fenetre)
    {
        Fenetre = fenetre;
// charger la vue dans la scene et associer le présenteur à cette vue
        try
        {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/vues/FenetrePrincipaleVue.xml"));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), 750, 500, Color.WHITE);
            scene.getStylesheets().add("vues/boostrap.css");
            fenetre.setScene(scene);
        }
        catch(Exception e)
        {
            System.out.println("Problème : " + e.getMessage());
            System.exit(0);
        }
// paramétrer et afficher la fenêtre principale
        fenetre.setTitle("Ecole Fantastique");
        fenetre.setResizable(false);
        fenetre.centerOnScreen();
        fenetre.show();
    }
    /**
     * Méthode exécutée au démarrage de l'application
     */

    public static void main(String[] args)
    {
        try
        {
            FabriqueDAO.getInstance().creerConnexion();
        }
        catch (Exception e) {

        }


        // charger la fenêtre principale
        launch(args);
    }
    /**
     * Méthodes exécutées lorsque l'utilisateur clique sur les rubriques des menus
     */
    @FXML
    private void MListerProfs(ActionEvent event)
    {
        new ListerClient(Fenetre);
    }



    @FXML
    private void MListerCours(ActionEvent event)
    {
        new ListerCours(Fenetre);
    }
    @FXML
    private void MListerEleves(ActionEvent event)
    {
        new ListerEleves(Fenetre);
    }
    @FXML
    private void MAjouterEleve(ActionEvent event)
    {
        new BAjouterAlcool(Fenetre);
    }
    @FXML
    private void MModifierEleve(ActionEvent event)
    {
        new ModifierStock(Fenetre);
    }
    @FXML
    private void MSupprimerEleve(ActionEvent event)
    {
        new SupprimerEleve(Fenetre);

    }
    @FXML
    private void MAjouterResultat(ActionEvent event)
    {
        new AjouterResultat(Fenetre);
    }
    @FXML
    private void MListerResultatsEleve(ActionEvent event)
    {
        new ListerResultatsEleve(Fenetre);
    }
    @FXML
    private void MQuitter(ActionEvent event)
    {
        System.exit(0);
    }
}