/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

/**
 *
 * @author Kaz
 */
public class Barco {
    private int tamanio;
    private boolean tocado;
    private boolean hundido;
    private int vecesTocado;
    /**
     * Constructor con 1 parametro
     * @param tamanio 
     */
    public Barco(int tamanio) {
        this.tamanio = tamanio;
        this.hundido=false;
        this.tocado=false;
        this.vecesTocado=0;
    }
    //Getters

    public int getTamanio() {
        return tamanio;
    }

    public boolean isTocado() {
        return tocado;
    }

    public boolean isHundido() {
        return hundido;
    }

    public int getVecesTocado() {
        return vecesTocado;
    }
    //Setters

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public void setTocado(boolean tocado) {
        this.tocado = tocado;
    }

    public void setHundido(boolean hundido) {
        this.hundido = hundido;
    }

    public void setVecesTocado(int vecesTocado) {
        this.vecesTocado = vecesTocado;
    }

    @Override
    public String toString() {
        return "Barco{" + "tamanio=" + tamanio + ", tocado=" + tocado + ", hundido=" + hundido + ", vecesTocado=" + vecesTocado + '}';
    }
    
    
}
