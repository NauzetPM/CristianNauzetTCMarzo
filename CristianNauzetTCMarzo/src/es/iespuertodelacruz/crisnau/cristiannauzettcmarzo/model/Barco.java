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
    int tocado;
    private static int contador = 1;

    /**
     * Constructor por defecto
     *
     * @param size
     */
    public Barco(int size) {
        this.nombre = "B" + contador;
        contador++;
        this.tamanio = new Casilla[size];
        hundido=false;
        tocado=0;
    }

    //Getters Y Setters
    public Casilla[] getTamanio() {
        return tamanio;
    }

    public void setTamanio(Casilla[] tamanio) {
        this.tamanio = tamanio;
    }

    public int getTocado() {
        return tocado;
    }

    public void setTocado(int tocado) {
        this.tocado = tocado;
    }
    
    public void setContador(int num){
        this.contador = 0;
    }
    @Override
    public String toString() {
        return nombre;
    }

}
