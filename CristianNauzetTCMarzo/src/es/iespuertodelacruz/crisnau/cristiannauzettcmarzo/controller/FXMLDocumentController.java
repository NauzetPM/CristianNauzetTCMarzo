/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.controller;

import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model.Tablero;
import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.view.CasillaFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author cristian & nauzet
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

    GridPane[] grids;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        grids = new GridPane[]{gpEnemigo, gpJugador};
        for (GridPane grid : grids) {
            for (int i = 1; i <= Tablero.size; i++) {
                for (int j = 1; j <= Tablero.size; j++) {
                    CasillaFX casillaFX = new CasillaFX();
                    //casillaFX.setOnAction(evt -> apostar(evt));
                    casillaFX.x = i-1;
                    casillaFX.y = j-1;
                    grid.add(casillaFX, i, j);
                }
            }
        }
    }

    @FXML
    private void reiniciarPartida(ActionEvent event) {
    }

    private void apostar(ActionEvent evt) {

    }

}
