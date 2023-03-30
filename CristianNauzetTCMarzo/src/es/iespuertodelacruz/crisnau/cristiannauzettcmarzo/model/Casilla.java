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

    public Casilla(Punto posicion) {
        this.posicion = posicion;
    }

    public Casilla(Punto posicion, Barco barco) {
        this.posicion = posicion;
        this.barco = barco;
    }

    public Punto getPosicion() {
        return posicion;
    }

    public void setPosicion(Punto posicion) {
        this.posicion = posicion;
    }

    public boolean isHasBeenShot() {
        return hasBeenShot;
    }

    public void setHasBeenShot(boolean hasBeenShot) {
        this.hasBeenShot = hasBeenShot;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    @Override
    public String toString() {
        return "Casilla{" + "posicion=" + posicion + ", hasBeenShot=" +
                hasBeenShot + ", barco=" + barco + '}';
    }
    
    
}
