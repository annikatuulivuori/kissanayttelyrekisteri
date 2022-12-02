package nayttelyt;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import nayttelyrekisteri.Nayttelyrekisteri;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author atuul
 * @version 8.4.2021
 *
 */
public class NayttelytMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("NayttelytGUIView.fxml"));
            final Pane root = ldr.load();
            final NayttelytGUIController nayttelytCtrl = (NayttelytGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("nayttelyt.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Nayttelyrekisteri");
            
            Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
            nayttelytCtrl.setRekisteri(rekisteri);
            
            primaryStage.show();
            Application.Parameters params = getParameters(); 
            if ( params.getRaw().size() > 0 ) 
                nayttelytCtrl.lueTiedosto(params.getRaw().get(0));  
            else
                if ( !nayttelytCtrl.avaa() ) Platform.exit();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}