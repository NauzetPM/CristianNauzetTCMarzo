/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

/**
 *
 * @author cristian & nauzet
 */
public class Casilla {

    Punto posicion;
    boolean hasBeenShot;
    Barco barco;
    
    Escenario escenario;

    /**
     * Constructor con 1 parametro
     *
     * @param posicion Clase Punto con la posicion de la casilla
     */
    public Casilla(Punto posicion) {
        this.posicion = posicion;
    }
 
    /**
     * Constructor con 2 parametros
     *
     * @param posicion Clase Punto con la posicion de la casilla.
     * @param barco Clase Barco que se encuentra en la casilla, si es null
     * significa que no hay ningun barco.
     */
    public Casilla(Punto posicion, Barco barco) {
        this.posicion = posicion;
        this.barco = barco;
    }

    //Getters
    public Punto getPosicion() {
        return posicion;
    }

    public Barco getBarco() {
        return barco;
    }

    public boolean isHasBeenShot() {
        return hasBeenShot;
    }

    //Setters
    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }

    public void setHasBeenShot(boolean hasBeenShot) {
        this.hasBeenShot = hasBeenShot;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    @Override
    public String toString() {
        return "Casilla{" + "posicion=" + posicion + ", hasBeenShot="
                + hasBeenShot + ", barco=" + barco + '}';
    }

}
