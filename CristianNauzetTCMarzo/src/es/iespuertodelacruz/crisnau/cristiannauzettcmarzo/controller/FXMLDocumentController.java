/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.controller;

import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model.Barco;
import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model.Partida;
import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model.Punto;
import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model.Tablero;
import es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.view.CasillaFX;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
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
    Partida game;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crearGrids();

        iniciarPartida();
    }

    @FXML
    private void reiniciarPartida(ActionEvent event) {
        txaRegistro.clear();
        iniciarPartida();
    }

    private void disableButtons() {
        gpJugador.setDisable(true);
    }

    private void crearGrids() {
        grids = new GridPane[]{gpEnemigo, gpJugador};

        for (GridPane grid : grids) {
            for (int i = 1; i <= Tablero.size; i++) {
                for (int j = 1; j <= Tablero.size; j++) {
                    CasillaFX casillaFX = new CasillaFX();
                    casillaFX.setOnAction(evt -> seleccionarCasilla(evt));
                    casillaFX.x = i - 1;
                    casillaFX.y = j - 1;
                    grid.add(casillaFX, i, j);
                }
            }
        }

    }

    public void iniciarPartida() {
        limpiarGrid();
        disableButtons();
        game = new Partida();
        Tablero tableroia = new Tablero();
        game.setIa(tableroia);

        Tablero tableroJugador = new Tablero();
        game.setJugador(tableroJugador);
        game.getIa().generarBarcos();
        game.getJugador().generarBarcos();
    }

    public void limpiarGrid() {
        for (Node node : gpJugador.getChildren()) {
            Button boton = (Button) node;
            boton.setText("  ");
        }
        for (Node node : gpEnemigo.getChildren()) {
            Button boton = (Button) node;
            boton.setText("  ");
            boton.setDisable(false);
        }
    }

    //No se enlaza el metodo a hacer click en los botones
    private void seleccionarCasilla(ActionEvent event) {
        Button boton = (Button) event.getSource();
        CasillaFX casilla = (CasillaFX) boton;
        int x = casilla.x;
        int y = casilla.y;
        String respuestaJugador = game.verDisparo(game.getIa(), x, y);

        if (respuestaJugador.equals("AGUA!")) {
            boton.setText("A");
            txaRegistro.appendText("Jugador: " + respuestaJugador + "\n");
        } else if ((respuestaJugador).equals("TOCADO!")) {
            boton.setText("T");
            txaRegistro.appendText("Jugador: " + respuestaJugador + "\n");
        } else {
            txaRegistro.appendText("Jugador: " + respuestaJugador + "\n");
            Barco barcoHundido = game.getIa().getCasilla(x, y).getBarco();
            boton.setText("H");

            for (Punto punto : game.getIa().getPosiciones(barcoHundido)) {
                int xHundido = punto.getX();
                int yHundido = punto.getY();

                for (Node node : gpEnemigo.getChildren()) {
                    Button botonHundido = (Button) node;
                    if (Objects.equals(GridPane.getRowIndex(botonHundido), xHundido) && Objects.equals(GridPane.getColumnIndex(botonHundido), yHundido)) {
                        botonHundido.setText("H");
                    }
                }
            }
        }
        boton.setDisable(true);
        boton.setOpacity(90);

        String respuestaIA = game.disparoIA();
        System.out.println(respuestaIA);

        String[] splitIA = game.getDisparos().get((game.getDisparos().size()) - 1).toString().split(" ");

        Integer xIA = (Integer.parseInt(splitIA[0]) > 0) ? Integer.valueOf(splitIA[0]) : null;
        Integer yIA = (Integer.parseInt(splitIA[1]) > 0) ? Integer.valueOf(splitIA[1]) : null;

        if (respuestaIA.contains("AGUA!")) {
            estadoCasillasJugador(xIA, yIA, "A");
            txaRegistro.appendText("IA: AGUA!\n");
        } else if ((respuestaIA).endsWith("TOCADO!")) {
            estadoCasillasJugador(xIA, yIA, "T");
            txaRegistro.appendText("IA: TOCADO!\n");
        } else {
            txaRegistro.appendText("IA: TOCADO! y HUNDIDO!\n");
            xIA = (xIA == null) ? 0 : xIA;
            yIA = (yIA == null) ? 0 : yIA;
            Barco barcoHundido = game.getJugador().getCasilla(xIA, yIA).getBarco();
            for (Punto punto : game.getIa().getPosiciones(barcoHundido)) {
                int xHundido = punto.getX();
                int yHundido = punto.getY();
                for (Node node : gpJugador.getChildren()) {
                    Button botonHundido = (Button) node;
                    if (Objects.equals(GridPane.getRowIndex(botonHundido), xHundido) && Objects.equals(GridPane.getColumnIndex(botonHundido), yHundido)) {
                        estadoCasillasJugador(xHundido, yHundido, "H");
                    }
                }
            }
        }

        if (game.getIa().perdiste()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Has ganado");
            alert.showAndWait();
            iniciarPartida();
        } else if (game.getJugador().perdiste()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Has perdido");
            alert.showAndWait();
            iniciarPartida();
        }
    }

    private void estadoCasillasJugador(Integer x, Integer y, String estado) {
        for (Node node : gpJugador.getChildren()) {
            Button boton = (Button) node;
            if (Objects.equals(GridPane.getRowIndex(boton), x) && Objects.equals(GridPane.getColumnIndex(boton), y)) {
                boton.setText(estado);
            }
        }
    }
}
