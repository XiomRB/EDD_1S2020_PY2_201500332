package blockchain;

public class Red {
    Nodo primero, ultimo;

    public Red() {
        primero = ultimo = null;
    }
    
    public void insertar(String ip){
        Nodo nuevo = new Nodo(ip);
        if(primero==null) primero = ultimo = nuevo;
        else{
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
    }
    
    public void eliminar(String ip){
        if(primero != null){
            Nodo aux = primero;
            while(aux != ultimo){
                if(aux.getSiguiente().getIp().equals(ip)) {
                    aux.setSiguiente(aux.getSiguiente().getSiguiente());
                    return;
                }
                aux = aux.getSiguiente();
            }
        }
    }
}
