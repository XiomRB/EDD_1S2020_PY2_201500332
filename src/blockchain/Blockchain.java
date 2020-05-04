package blockchain;

public class Blockchain {
    private Bloque primero;
    Bloque ultimo;
    int tam;

    public Blockchain() {
        primero = ultimo = null;
        tam = 0;
    }
    
    public void agregarBloque(String timestamp, String data){
        Bloque nuevo;
        if(tam == 0){
            nuevo = new Bloque(tam, timestamp, data, "0000");
            nuevo.minarBloque(4);
            primero = ultimo = nuevo;
        }
        else{
            nuevo = new Bloque(tam, timestamp, data, ultimo.getHash());
            nuevo.minarBloque(4);
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        tam++;
    }
    
    public String dibujar(){
        String dibujo = "digraph g{\n rankdir = LR;\nnode [shape = box];\n";
        if (primero != null) {
            Bloque aux = primero;
            if(primero == ultimo){
                dibujo += aux.dibujar()+ "\n}";
                return dibujo;
            }
            while(aux != ultimo){
                dibujo += aux.dibujar() + " -> " + aux.getSiguiente().dibujar();
                aux = aux.getSiguiente();
            }
            dibujo += "\n}";
        }
        return dibujo;
    }

    public Bloque getPrimero() {
        return primero;
    }
}
