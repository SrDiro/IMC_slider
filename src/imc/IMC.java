package imc;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IMC extends Application {
    
    private Stage stagePrincipal;
    
    @Override
    public void start(Stage stage) throws Exception {
        this.stagePrincipal = stage;
        
        mostrarVentanaPrincipal();
        
    }
    
    public void mostrarVentanaPrincipal() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stagePrincipal.setScene(scene);
            stagePrincipal.show();
        } catch (IOException e) {
        }
    }
    
    public void mostrarVentanaSecundaria() {
        try {
            FXMLLoader loader = new FXMLLoader(FXMLDocumentController.class.getResource("ventanaDos.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Venta Dos");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            FXMLVentanaDosController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
    
        
    
}
