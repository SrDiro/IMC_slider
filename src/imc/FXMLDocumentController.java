package imc;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private Label labelTitulo;
    @FXML
    private Label labelAltura;
    @FXML
    private Label labelPeso;
    @FXML
    private TextField textFieldAltura;
    @FXML
    private TextField textFieldPeso;
    @FXML
    private Label labelCm;
    @FXML
    private Label labelKilos;
    @FXML
    private RadioButton RBObesidad;
    @FXML
    private ToggleGroup opciones;
    @FXML
    private RadioButton RBSobrepeso;
    @FXML
    private RadioButton RBNormal;
    @FXML
    private RadioButton RBExtremaDelgadez;
    @FXML
    private Slider sliderAltura;
    @FXML
    private Slider sliderPeso;
    @FXML
    private Label labelIMC;
    @FXML
    private ListView<String> listTipoPeso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldAltura.setEditable(false);
        textFieldPeso.setEditable(false);
        
        ObservableList<String> listTipos = FXCollections.observableArrayList("Obesidad", "Sobrepeso", "Normal", "Extrema-delgadez");
        listTipoPeso.setItems(listTipos);
    }
    

    @FXML
    private void seleccionar(MouseEvent event) {
        
        double altura, peso, resultado;
        String alturaFormat, pesoFormat, resultadoFormat;
        
        altura = sliderAltura.getValue();
        peso = sliderPeso.getValue();
        
        if (altura < 40 || altura > 220) {
            this.textFieldAltura.setStyle("-fx-background-color: rgba(255, 89, 89, 0.5);");
        } else {
            this.textFieldAltura.setStyle("-fx-background-color: rgba(106, 249, 142, 0.5);");
        }

        if (peso < 20 || peso > 180) {
            this.textFieldPeso.setStyle("-fx-background-color: rgba(255, 89, 89, 0.5);");
        } else {
            this.textFieldPeso.setStyle("-fx-background-color: rgba(106, 249, 142, 0.5);");
        }
        
        DecimalFormat formateador = new DecimalFormat("00.00");
        alturaFormat = formateador.format(altura);
        pesoFormat = formateador.format(peso);
        
        textFieldAltura.setText(alturaFormat);
        textFieldPeso.setText(pesoFormat);
        
        
        //CALCULO DEL IMC
        resultado = peso / ((altura/100) * (altura/100));
        
        if (resultado > 30) {
            RBObesidad.fire();
            listTipoPeso.getSelectionModel().select(0);
        } else if (resultado > 25 && resultado < 29.9) {
            RBSobrepeso.fire();
            listTipoPeso.getSelectionModel().select(1);
        } else if (resultado > 18.5 && resultado < 24.9) {
            RBNormal.fire();
            listTipoPeso.getSelectionModel().select(2);
        } else if (resultado < 18.5) {
            RBExtremaDelgadez.fire();
            listTipoPeso.getSelectionModel().select(3);
        }
        
        resultadoFormat = formateador.format(resultado);
        
        labelIMC.setText(resultadoFormat);
        
    }

}
