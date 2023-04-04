/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.iespuertodelacruz.crisnau.cristiannauzettcmarzo.model;

/**
 *
 * @author cristian & nauzet
 */
public class Tablero {

    Casilla[][] casillas;
    public static int size = 4;

    public Tablero(){
        
    }
    
    public boolean isOcupado(Punto punto){
        if(casillas[(int)punto.getX()][(int)punto.getY()].barco != null){
            return true;
        }
        return false;
    }
    public Casilla getCasilla(int x,int y){
        return casillas[x][y];
    }
    
}
