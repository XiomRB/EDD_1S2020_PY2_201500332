package blockchain;

public class Nodo {
    private String ip;
    private Blockchain bloques;
    private Nodo siguiente;

    public Nodo(String ip) {
        this.ip = ip;
        bloques = new Blockchain();
        siguiente = null;
    }

    public String getIp() {
        return ip;
    }

    public Blockchain getBloques() {
        return bloques;
    }

    public void setBloques(Blockchain bloques) {
        this.bloques = bloques;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}
