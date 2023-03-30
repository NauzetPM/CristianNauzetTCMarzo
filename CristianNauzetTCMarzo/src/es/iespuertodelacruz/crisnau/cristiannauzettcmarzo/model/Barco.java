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
    private Casilla[] tamanio;
    /**
     * Constructor por defecto
     * @param tamanio 
     */
    public Barco() {
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
        return "Barco{" + "tamanio=" + tamanio + '}';
    }
    
    
    
}
