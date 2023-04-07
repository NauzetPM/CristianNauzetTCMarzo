/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author cristian & nauzet
 */
public class Tablero {

    Casilla[][] casillas;
    public static int size = 4;

    public Tablero() {
        casillas = new Casilla[4][4];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                casillas[i][j] = new Casilla(new Punto(i, j));

            }
        }
    }

    public boolean isOcupado(Punto punto) {
        return casillas[(int) punto.getX()][(int) punto.getY()].barco != null;
    }

    public void generarBarcos() { // Hay que arreglar una cosa y optimizarlo.
        Random rnd = new Random();
        int shipSize = 2;
        int numBarcos = 3;

        Barco barco = new Barco(shipSize);
        int contador = 0;

        HashMap<String, Barco> posicionesGeneradas = new HashMap<>();
        while (posicionesGeneradas.size() < numBarcos) {
            if (contador >= 2) {
                contador = 0;
                barco = new Barco(shipSize);
            }
            int posX = rnd.nextInt(casillas.length);
            int posY = rnd.nextInt(casillas.length);
            boolean isVertical = rnd.nextBoolean();
            String posicionInicial = posX + "," + posY;
            boolean isAvalible = true;

            int i = 1;
            while (isAvalible && i < numBarcos) {
                int posXActual;
                int posYActual;
                if (isVertical) {
                    posXActual = posX + i;
                    posYActual = posY;
                } else {
                    posXActual = posX;
                    posYActual = posY + i;
                }
                if (posXActual >= casillas.length || posYActual >= casillas.length) {
                    isAvalible = false;
                } else {
                    String posicionActual = posXActual + "," + posYActual;
                    if (posicionesGeneradas.containsKey(posicionActual)) {
                        isAvalible = false;
                    } else {
                        i++;
                    }
                }
            }
            if (isAvalible) {
                posicionesGeneradas.put(posicionInicial, barco);
                for (int j = 1; j < numBarcos; j++) {
                    int posXActual;
                    int posYActual;
                    if (isVertical) {
                        posXActual = posX + j;
                        posYActual = posY;
                    } else {
                        posXActual = posX;
                        posYActual = posY + j;
                    }
                    String posicionActual = posXActual + "," + posYActual;
                    posicionesGeneradas.put(posicionActual, barco);

                }
                contador++;
            }
        }
        for (Map.Entry<String, Barco> entry : posicionesGeneradas.entrySet()) {
            String[] posiciones = entry.getKey().split(",");
            int posX = Integer.parseInt(posiciones[0]);
            int posY = Integer.parseInt(posiciones[1]);
            casillas[posX][posY].setBarco(entry.getValue());
        }

    }

    public Casilla getCasilla(int x, int y) {
        return casillas[x][y];
    }

    public String mostrarTablero() {
        String resultado = "";

        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                if (casillas[i][j].getBarco() == null) {
                    resultado += casillas[i][j].getPosicion() + " ";
                }else{
                    resultado += casillas[i][j].getBarco().toString() + " ";
                }
            }
            resultado += "\n";
        }
        return resultado;
    }

    public static void main(String[] args) {
        Tablero tablero = new Tablero();

        tablero.generarBarcos();
        System.out.println(tablero.mostrarTablero());
    }
}
