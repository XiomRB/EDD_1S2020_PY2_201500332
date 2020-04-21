package logica;

public class NodoAVL {
    private String categoria;
    private int altura;
    private NodoAVL izq;
    private NodoAVL der;
    public ABLibro libros;

    public NodoAVL(String categoria) {
        this.categoria = categoria;
        this.altura = 1;
        this.izq = this.der = null;
        this.libros = new ABLibro();
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }
    
}