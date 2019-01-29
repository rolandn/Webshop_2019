package couchePresentation;

import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.*;

public class BaseFenetre extends Stage {

    protected BaseFenetre(Stage fenParent, String vue, String titre, int largeur, int hauteur)
    {
// charger la vue dans la scene
        try
        {
            FXMLLoader loader = new FXMLLoader(new URL("file:src/vues/" + vue));
            loader.setController(this);
            Scene scene = new Scene(loader.load(), largeur, hauteur);
            scene.getStylesheets().add("vues/boostrap.css");
            setScene(scene);
        }catch(Exception e)
        {
            System.out.println("Problème : " + e.getMessage());
            System.exit(0);
        }
// paramétrer la fenêtre
        setTitle(titre);
        setResizable(false);
        setX(fenParent.getX() + (fenParent.getWidth() - largeur) / 2);
        setY(fenParent.getY() + (fenParent.getHeight() - hauteur) / 2);
        initOwner(fenParent);
        initModality(Modality.APPLICATION_MODAL);
    }

}
