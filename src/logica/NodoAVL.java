package logica;

public class NodoAVL {
    private String categoria;
    private int fe;
    private NodoAVL izq;
    private NodoAVL der;

    public NodoAVL(String categoria) {
        this.categoria = categoria;
        this.fe = 0;
        this.izq = this.der = null;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
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