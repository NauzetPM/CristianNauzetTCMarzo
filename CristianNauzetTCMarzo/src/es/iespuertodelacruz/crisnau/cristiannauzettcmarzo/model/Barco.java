/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

/**
 *
 * @author cristian & nauzet
 */
public class Barco {
    public String nombre;
    private Casilla[] tamanio;
    boolean hundido;
    boolean tocado;
    int contador = 1;
    /**
     * Constructor por defecto
     * @param size 
     */
    public Barco(int size) {
        this.nombre = "B" + contador;
        this.tamanio = new Casilla[size];
        contador++;
    }

    //Getters Y Setters
    
    public Casilla[] getTamanio() {
        return tamanio;
    }

    public void setTamanio(Casilla[] tamanio) {
        this.tamanio = tamanio;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}
