/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author cristian & nauzet
 */
public class Partida {

    Tablero ia;
    Tablero jugador;
    HashMap<String, ArrayList<Punto>> resultadoDisparos;
    ArrayList<Punto> disparos;
    int contadorTurnos;
    ArrayList<Punto> posicionesAtacadasTocado;
    boolean tocado;
    ArrayList<String> posicionesCercanas;
    Punto ultimaPosicionTocada;

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

        if (ia.casillas[(int) punto.getX()][(int) punto.getY()].barco != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean confirmarImpactoIA(Punto punto) {

        if (jugador.casillas[(int) punto.getX()][(int) punto.getY()].barco != null) {
            return true;
        } else {
            return false;
        }
    }

    public String disparar() {
        return "";
    }

    public String verDisparo(Tablero tablero, int x, int y) {
        Punto p = new Punto(x, y);
        disparos.add(p);
        String respuesta = "";
        if (!tablero.isOcupado(p)) {
            respuesta = "AGUA!";
        } else {
            if (tablero.getCasilla(x, y).barco.hundido) {
                respuesta += "HUNDIDO!";
            } else {
                respuesta = "TOCADO!";
            }
            return respuesta;
        }

        return respuesta;

    }

    private void addPosicionCercana(int x, int y, Tablero escenarioJugador) {
        if (x > 0 && !escenarioJugador.getCasilla(x - 1, y).isHasBeenShot()) {
            posicionesCercanas.add((x - 1) + " " + y);
        }
        if (x < escenarioJugador.casillas.length - 1 && !escenarioJugador.getCasilla(x + 1, y).isHasBeenShot()) {
            posicionesCercanas.add((x + 1) + " " + y);
        }
        if (y > 0 && !escenarioJugador.getCasilla(x, y - 1).isHasBeenShot()) {
            posicionesCercanas.add(x + " " + (y - 1));
        }
        if (y < escenarioJugador.casillas.length - 1 && !escenarioJugador.getCasilla(x, y + 1).isHasBeenShot()) {
            posicionesCercanas.add(x + " " + (y + 1));
        }
    }

    private void verificarCercanos(Tablero escenarioJugador) {
        for (Punto posicionTocada : posicionesAtacadasTocado) {
            String[] split = posicionTocada.toString().split(" ");
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[1]);
            Barco barcoTocado = escenarioJugador.getCasilla(x, y).getBarco();
            if (!barcoTocado.hundido) {
                addPosicionCercana(x, y, escenarioJugador);
            }
        }
    }

    public String disparoIA() {
        Random rnd = new Random();
        String respuesta = "";
        String strEstado;
        int x, y;
        if (!tocado) {
            if (posicionesCercanas.isEmpty()) {
                do {
                    x = rnd.nextInt(jugador.casillas.length);
                    y = rnd.nextInt(jugador.casillas.length);
                } while (jugador.getCasilla(x, y).hasBeenShot || ((x + y) % 2) != 0);
                strEstado = verDisparo(jugador, x, y);
                respuesta = "IA ataca posicion " + x + ", " + y + "\n"
                        + strEstado;

            } else {
                String[] coordenadas = posicionesCercanas.get(0).toString().split(" ");
                x = Integer.parseInt(coordenadas[0]);
                y = Integer.parseInt(coordenadas[1]);
                strEstado = verDisparo(jugador, x, y);
                respuesta = "IA ataca posicion " + x + ", " + y + "\n"
                        + strEstado;
            }
            disparos.add(new Punto(x, y));
            if (strEstado.equals("TOCADO!")) {
                posicionesAtacadasTocado.add(new Punto(x, y));
                addPosicionCercana(x, y, jugador);
                ultimaPosicionTocada = new Punto(x, y);
                tocado = true;
            } else if (strEstado.contains("HUNDIDO!")) {
                posicionesCercanas.clear();
                verificarCercanos(jugador);
            }
        } else {

            if (posicionesCercanas.isEmpty()) {
                String[] ataqueAnterior = ultimaPosicionTocada.toString().split(" ");
                int xAtaqueAnterior = Integer.parseInt(ataqueAnterior[0]);
                int yAtaqueAnterior = Integer.parseInt(ataqueAnterior[1]);
                addPosicionCercana(xAtaqueAnterior, yAtaqueAnterior, jugador);
            }

            String[] coordenadas = posicionesCercanas.get(0).toString().split(" ");
            x = Integer.parseInt(coordenadas[0]);
            y = Integer.parseInt(coordenadas[1]);
            strEstado = verDisparo(jugador, x, y);
            respuesta = "La IA ha atacado la posicion " + x + ", " + y + "\n"
                    + strEstado;
            disparos.add(new Punto(x, y));

            if (strEstado.equals("TOCADO!")) {
                tocado = true;
                posicionesAtacadasTocado.add(new Punto(x, y));
                String[] ataqueAnterior = ultimaPosicionTocada.toString().split(" ");
                int xAtaqueAnterior = Integer.parseInt(ataqueAnterior[0]);
                int yAtaqueAnterior = Integer.parseInt(ataqueAnterior[1]);
                ultimaPosicionTocada = new Punto(x, y);

                if (x > xAtaqueAnterior && (x + 1) < jugador.casillas.length
                        && !jugador.getCasilla((x + 1), y).isHasBeenShot()) {
                    posicionesCercanas.clear();
                    posicionesCercanas.add((x + 1) + " " + y);
                    if ((xAtaqueAnterior - 1) > 0 && !jugador.getCasilla((xAtaqueAnterior - 1), y).isHasBeenShot()) {
                        posicionesCercanas.add((xAtaqueAnterior - 1) + " " + y);
                    }
                } else if (x < xAtaqueAnterior && (x - 1) > 0 && !jugador.getCasilla((x - 1), y).isHasBeenShot()) {
                    posicionesCercanas.clear();
                    posicionesCercanas.add((x - 1) + " " + y);
                    if ((xAtaqueAnterior + 1) < jugador.casillas.length && !jugador.getCasilla((xAtaqueAnterior + 1), y).isHasBeenShot()) {
                        posicionesCercanas.add((xAtaqueAnterior + 1) + " " + y);
                    }
                } else if (y > yAtaqueAnterior && (y + 1) < jugador.casillas.length
                        && !jugador.getCasilla(x, (y + 1)).isHasBeenShot()) {
                    posicionesCercanas.clear();
                    posicionesCercanas.add(x + " " + (y + 1));
                    if ((yAtaqueAnterior - 1) > 0 && !jugador.getCasilla(x, (yAtaqueAnterior - 1)).isHasBeenShot()) {
                        posicionesCercanas.add(x + " " + (yAtaqueAnterior - 1));
                    }
                } else if (y < yAtaqueAnterior && (y - 1) > 0 && !jugador.getCasilla(x, y - 1).isHasBeenShot()) {
                    posicionesCercanas.clear();
                    posicionesCercanas.add(x + " " + (y - 1));
                    if ((yAtaqueAnterior + 1) < jugador.casillas.length && !jugador.getCasilla(x, yAtaqueAnterior + 1).isHasBeenShot()) {
                        posicionesCercanas.add(x + " " + (yAtaqueAnterior + 1));
                    }
                }
            } else if (strEstado.contains("HUNDIDO!")) {
                posicionesCercanas.clear();
                verificarCercanos(jugador);
                tocado = false;
            }
        }

        //System.out.println(posicionesCercanas);
        return respuesta;
    }
    //turnos
    //intelegencia artificial
}

// T[0,1] -> A[0,0], A[1,1], T[0,2] -> T[0,3]
