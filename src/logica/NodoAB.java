package logica;

import java.util.ArrayList;

public class NodoAB {
    Libro[] claves;
    int clavesactuales;
    NodoAB[] hijos;
    boolean hoja;
    NodoAB padre;//ver si se borra

    public NodoAB(boolean hoja) {
        this.hoja = hoja;//cambiar a true
        this.claves = new Libro[5];
        this.hijos = new NodoAB[6];
        this.clavesactuales = 1;
        this.padre = null;
    }
    
    public void recorrer(){
            for (int i = 0; i < clavesactuales; i++) {
                System.out.print(" " + claves[i].getISBN());
            }
            System.out.println("");
            for (int i = 0; i < clavesactuales+1; i++) {
                for (int j = 0; j < hijos[i].clavesactuales; j++) {
                    System.out.print(" " + hijos[i].claves[j].getISBN());
                }
                System.out.print("  ");
        }
    }
    
    public void recorrer2(){
        if(hoja){
            for (int i = 0; i < clavesactuales; i++) {
                System.out.print(" " + claves[i].getISBN());
            }
            System.out.println("");
        }else{
            for (int i = 0; i < clavesactuales; i++) {
                    System.out.print(" " + claves[i].getISBN());
            }
            System.out.println("");
            for (int i = 0; i < clavesactuales +1; i++) {
                hijos[i].recorrer2();
            }
            
        }
    }
    
    public NodoAB buscar(int isbn){
        int i = 0;
        while(i<clavesactuales-1 && isbn >claves[i].getISBN())i++;
        if(claves[i].getISBN()==isbn) return this;
        if(hoja) return null;
        if(isbn >claves[i].getISBN()) return hijos[i+1].buscar(isbn);
        else return hijos[i].buscar(isbn);
    }
}