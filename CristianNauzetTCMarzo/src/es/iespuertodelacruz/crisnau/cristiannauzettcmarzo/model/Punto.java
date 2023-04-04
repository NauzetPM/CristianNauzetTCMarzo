/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

/**
 *
 * @author cristian & nauzet
 */
public class Punto {

    double x;
    double y;

    /**
     * Constructor con dos parametros
     *
     * @param x posicion x
     * @param y posicion y
     */
    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    //Getters
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    //Setters
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return x+" "+y;
    }

}
