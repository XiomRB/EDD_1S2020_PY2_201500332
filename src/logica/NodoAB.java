package logica;

import java.util.ArrayList;
import vista.LibrosUsuario;

public class NodoAB {
    Libro[] claves;
    int clavesactuales;
    NodoAB[] hijos;
    boolean hoja;
    NodoAB padre;//ver si se borra
    int numero;
    
    public NodoAB(boolean hoja, int numero) {
        this.hoja = hoja;//cambiar a true
        this.claves = new Libro[5];
        this.hijos = new NodoAB[6];
        this.clavesactuales = 1;
        this.padre = null;
        this.numero = numero;
    }
    
    public void buscarCarnet(int carnet,LibrosUsuario libUs){//ubica los libros del estudiante logueado
        if(hoja){
            for (int i = 0; i < clavesactuales; i++)
                if(claves[i].getPropietario()== carnet) libUs.setLibro(claves[i].getISBN(), claves[i].getTitulo());
        }else{
            for (int i = 0; i < clavesactuales; i++) if(claves[i].getPropietario()== carnet) libUs.setLibro(claves[i].getISBN(), claves[i].getTitulo());
            for (int i = 0; i < clavesactuales +1; i++) hijos[i].buscarCarnet(carnet,libUs);
        }
    }
    
    public void crearLibrosBiblioteca(LibrosUsuario libs){//para la interfaz de la biblioteca virtual
        if(hoja){
            for (int i = 0; i < clavesactuales; i++) libs.setLibro(claves[i].getISBN(), claves[i].getTitulo());
        }else{
            for (int i = 0; i < clavesactuales; i++) libs.setLibro(claves[i].getISBN(), claves[i].getTitulo());
            for (int i = 0; i < clavesactuales +1; i++) hijos[i].crearLibrosBiblioteca(libs);
        }
    }
    
    public String recorrer(){
        String dibujo = numero + "[label = \"";
        for (int j = 0; j < clavesactuales; j++) {
            if (j == clavesactuales-1) dibujo += "<f" +j + "> " + claves[j].getISBN() + "\\n" + claves[j].getTitulo() + "\"];\n";
            else dibujo += "<f" +j + "> " + claves[j].getISBN() + "\\n" + claves[j].getTitulo() + " | ";          
        }
        if(!hoja){
            for (int j = 0; j < clavesactuales +1; j++) dibujo += hijos[j].recorrer();
        }
        return dibujo;
    }
    
    public String recorrer2(){
        String dibujo = "";
        if (!hoja) {
            for (int j = 0; j < clavesactuales; j++) {
                if (j == clavesactuales-1){
                    dibujo += numero + ":f" + j + " -> " + hijos[j].numero + ";\n";
                    dibujo += numero + ":f" + j + " -> " + hijos[clavesactuales].numero + ";\n";
                }
                else  dibujo += numero + ":f" + j + " -> " + hijos[j].numero + ";\n";
            }
            for (int j = 0; j < clavesactuales+1; j++) dibujo += hijos[j].recorrer2();
        }
        return dibujo;
    }
    
    private NodoAB buscar(int isbn){
        int i = 0;
        while(i<clavesactuales-1 && isbn >claves[i].getISBN())i++;
        if(claves[i].getISBN()==isbn) return this;
        if(hoja) return null;
        if(isbn >claves[i].getISBN()) return hijos[i+1].buscar(isbn);
        else return hijos[i].buscar(isbn);
    }
    
    public Libro buscarLibro(int isbn){
        NodoAB nodo = buscar(isbn);
        if (nodo != null) {
            int i = 0;
            while(i < nodo.clavesactuales){
                if (nodo.claves[i].getISBN() == isbn) return nodo.claves[i];
                i++;
            }
        }
        return null;
    }
}