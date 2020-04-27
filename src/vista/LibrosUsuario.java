package vista;

import java.util.ArrayList;

public class LibrosUsuario {
    private String categoria;
    private ArrayList<Book> libros;

    public LibrosUsuario(String categoria, ArrayList<Book> libros) {
        this.categoria = categoria;
        this.libros = libros;
    }

    public ArrayList<Book> getLibros() {
        return libros;
    }

    public void setLibros(ArrayList<Book> libros) {
        this.libros = libros;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public void setLibro(int isbn, String tit){
        libros.add(new Book(tit, isbn));
    }
    
    public Book getLibro(int i){
        return libros.get(i);
    }
}
