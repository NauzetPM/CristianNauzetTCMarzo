/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author cristian & nauzet
 */
public class Partida {

    Tablero[] tableros;
    HashMap<String, ArrayList<Punto>> resultadoDisparos;
    ArrayList<Punto> disparos;
    int contadorTurnos;

    public Partida() {
        contadorTurnos = 0;
        resultadoDisparos = new HashMap<>();
        disparos = new ArrayList<>();
        //generar tablero
    }

    public void turno() {
        if (contadorTurnos % 2 == 0) {
            disparar();
        } else {
            disparoIA();
        }
        contadorTurnos++;
    }

    public boolean confirmarImpactoJugador(Punto punto) {

        if (tableros[0].casillas[(int) punto.getX()][(int) punto.getY()].barco != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean confirmarImpactoIA(Punto punto) {

        if (tableros[1].casillas[(int) punto.getX()][(int) punto.getY()].barco != null) {
            return true;
        } else {
            return false;
        }
    }

    public String disparar() {
        return "";
    }

    public String disparoIA() {
        return "";
    }
    //turnos
    //intelegencia artificial
}

// T[0,1] -> A[0,0], A[1,1], T[0,2] -> T[0,3]
