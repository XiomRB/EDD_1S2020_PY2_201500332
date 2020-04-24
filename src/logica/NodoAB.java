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
    
    /*public void insertar(NodoAB nodo,Libro nuevo){
        int i = clavesactuales-1;
        System.out.println(i);
        if(hoja){
            while(i>=0 && claves[i].getISBN()>nuevo.getISBN()){
                claves[i+1] = claves[i];
                i--;
            }
            claves[i+1] = nuevo;
            clavesactuales++;
        }else{
            while(i >= 0 && claves[i].getISBN()>nuevo.getISBN()) i--;
            if(hijos[i+1].clavesactuales == 4){
                dividirHijos(i+1, hijos[i+1]);
                if(claves[i+1].getISBN() < nuevo.getISBN()) i++;
            }
            hijos[i+1].insertar(nuevo);
        }
    }
    
    public void dividirHijos(int indice, NodoAB nodo){
        NodoAB nuevonodo = new NodoAB(nodo.hoja);
        nuevonodo.clavesactuales = 2; //se le da el numero minimo de claves
        for (int i = 0; i < nuevonodo.clavesactuales; i++) nuevonodo.claves[i] = nodo.claves[i+2];
        if(!nodo.hoja){
            for (int i = 0; i < 3; i++) nuevonodo.hijos[i] = nodo.hijos[i+3];
        }
        nodo.clavesactuales = 2;
        for (int i = clavesactuales; i >= indice + 1; i--) hijos[i+1] = hijos[i];
        hijos[indice + 1] = nuevonodo;
        for (int i = clavesactuales-1; i >= indice; i--) claves[i+1] = claves[i];
        claves[indice] = nodo.claves[2];
        clavesactuales++;
    }*/
}