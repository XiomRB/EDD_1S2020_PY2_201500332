package logica;
import java.math.BigInteger;
import java.security.MessageDigest;

public class LUsuario {
    private NodoL primero, ultimo;

    public LUsuario() {
        primero = ultimo = null;
    }
    
    public static String getMD5(String clave){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] msgdgst = md.digest(clave.getBytes());
            BigInteger numero = new BigInteger(1, msgdgst);
            String hash = numero.toString(16);
            while(hash.length()<32) hash = "0" + hash;
            return hash;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public void insertar(int carnet, String nombre, String apellido, String carrera, String password){
        String pass = getMD5(password);
        NodoL nuevo = new NodoL(carnet, nombre, apellido, carrera, pass);
        if(this.primero == null) primero = ultimo = nuevo;
        else{
            ultimo.setSig(nuevo);
            ultimo = nuevo;
        }
    }
    
    public NodoL eliminar(int carnet){
        NodoL eliminado;
        NodoL aux = primero;
        if(aux != null && aux.getCarnet() == carnet){
            eliminado = primero;
            primero = aux.getSig();
            return eliminado;
        }
        while(aux != ultimo){
            if(aux.getSig().getCarnet() == carnet){
                eliminado = aux.getSig();
                aux.setSig(eliminado.getSig());
                if(eliminado == ultimo) ultimo = aux;
                return eliminado;
            }
            aux = aux.getSig();
        }
        return null;
    }
    
    public NodoL buscar(int carnet){
        NodoL aux = this.primero;
        while(aux!= null && aux.getCarnet() != carnet)aux = aux.getSig();
        return aux;
    }
    
    public void modificar(int carnet, String nombre, String apellido, String carrera, String password){
        NodoL aux = buscar(carnet);
        if(aux != null){
            String pass = getMD5(password);
            aux.setNombre(nombre);
            aux.setApellido(apellido);
            aux.setCarrera(carrera);
            aux.setPassword(pass);
        }
        System.out.println(aux.getCarnet());
        System.out.println(aux.getNombre());
        System.out.println(aux.getApellido());
        System.out.println(aux.getCarrera());
        System.out.println(aux.getPassword());
    }
    
    public String loguear(int carnet, String pass){
        String password = getMD5(pass);
        NodoL usuario = buscar(carnet);
        if(usuario == null) return "El usuario no existe";
        if(usuario.getPassword().equals(password)) return "";
        else return "Contraseña incorrecta";
    }
    
    public String dibujar(){
        String dibujo = "";
        NodoL aux = primero;
        while(aux != ultimo){
            dibujo += aux.dibujarNodo();
            aux = aux.getSig();
        }
        dibujo += aux.getCarnet();
        return dibujo;
    }

    public NodoL getPrimero() {
        return primero;
    }

    public void setPrimero(NodoL primero) {
        this.primero = primero;
    }

    public NodoL getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoL ultimo) {
        this.ultimo = ultimo;
    }
}
