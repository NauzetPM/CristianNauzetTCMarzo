/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Kaz
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private GridPane gpEnemigo;
    @FXML
    private GridPane gpJugador;
    @FXML
    private TextArea txaRegistro;
    @FXML
    private Button btnReiniciar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void reiniciarPartida(ActionEvent event) {
    }
    
}
