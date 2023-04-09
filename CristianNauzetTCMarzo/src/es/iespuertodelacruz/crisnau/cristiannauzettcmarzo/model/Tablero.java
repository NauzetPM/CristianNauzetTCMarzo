/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

import java.util.ArrayList;
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
    public HashMap<Barco,ArrayList<Punto>> barcoPosiciones;
    public ArrayList<Barco> barcos;
    public Tablero() {
        casillas = new Casilla[4][4];
        for (int i = 0; i < casillas.length; i++) {
            for (int j = 0; j < casillas.length; j++) {
                casillas[i][j] = new Casilla(new Punto(i, j));

            }
        }
        barcos=new ArrayList<Barco>();
        barcoPosiciones=new HashMap<>();
    }

    public boolean isOcupado(Punto punto) {
        return casillas[(int) punto.getX()][(int) punto.getY()].barco != null;
    }

    public void generarBarcos() {
        Random rnd = new Random();
        int numBarcos = 3;
        int[] tamaniosBarcos = {2, 3, 4};

        HashMap<String, Barco> posicionesGeneradas = new HashMap<>();
        for (int i = 0; i < numBarcos; i++) {
            int shipSize = tamaniosBarcos[i];
            Barco barco = new Barco(shipSize);
            int contador = 0;
            boolean isAvalible = false;
            while (!isAvalible && contador < 100) {
                int posX = rnd.nextInt(casillas.length);
                int posY = rnd.nextInt(casillas.length);
                boolean isVertical = rnd.nextBoolean();
                String posicionInicial = posX + "," + posY;
                isAvalible = true;

                for (int j = 0; j < shipSize; j++) {
                    int posXActual;
                    int posYActual;
                    if (isVertical) {
                        posXActual = posX + j;
                        posYActual = posY;
                    } else {
                        posXActual = posX;
                        posYActual = posY + j;
                    }
                    if (posXActual >= casillas.length || posYActual >= casillas.length
                            || posicionesGeneradas.containsKey(posXActual + "," + posYActual)) {
                        isAvalible = false;
                    }
                }

                if (isAvalible) {
                    posicionesGeneradas.put(posicionInicial, barco);
                    for (int j = 1; j < shipSize; j++) {
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
                }

                contador++;
            }
        }

        for (Map.Entry<String, Barco> entry : posicionesGeneradas.entrySet()) {
            String[] posiciones = entry.getKey().split(",");
            int posX = Integer.parseInt(posiciones[0]);
            int posY = Integer.parseInt(posiciones[1]);
            casillas[posX][posY].setBarco(entry.getValue());
            ArrayList<Punto> puntos;
            barcos.add(entry.getValue());
            if(barcoPosiciones.get(entry.getValue())==null){
                puntos=new ArrayList<Punto>();
                puntos.add(new Punto(posX,posY));
                barcoPosiciones.put(entry.getValue(), puntos);
            }else{
                puntos=barcoPosiciones.get(entry.getValue());
                puntos.add(new Punto(posX,posY));
            } 
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
                    resultado += casillas[i][j].getPosicion() + "\t";
                } else {
                    resultado += casillas[i][j].getBarco().toString() + "\t";
                }
            }
            resultado += "\n";
        }
        return resultado;
    }
    public boolean perdiste(){
        boolean resultado=false;
        int barcosHundidos=0;
        for (Barco barco : barcos) {
            if(barco.hundido){
                barcosHundidos++;
            }
        }
        if(barcosHundidos==3){
            resultado=true;
        }
        return resultado;
    } 
    public ArrayList<Punto> getPosiciones(Barco barco){
        return barcoPosiciones.get(barco);
    }
    public Casilla[][] getCasillas() {
        return casillas;
    }

    public void setCasillas(Casilla[][] casillas) {
        this.casillas = casillas;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        Tablero.size = size;
    }

    public HashMap<Barco, ArrayList<Punto>> getBarcoPosiciones() {
        return barcoPosiciones;
    }

    public void setBarcoPosiciones(HashMap<Barco, ArrayList<Punto>> barcoPosiciones) {
        this.barcoPosiciones = barcoPosiciones;
    }
    
}
