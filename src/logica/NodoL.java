package logica;

public class NodoL {
    private int carnet;
    private String nombre;
    private String apellido;
    private String carrera;
    private String password;
    private NodoL sig;

    public NodoL(int carnet, String nombre, String apellido, String carrera, String password) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carrera = carrera;
        this.password = password;
        sig = null;
    }

    public int getCarnet() {
        return carnet;
    }

    public void setCarnet(int carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NodoL getSig() {
        return sig;
    }

    public void setSig(NodoL sig) {
        this.sig = sig;
    }
    
    public String dibujarNodo(){
        return "\"" + carnet + "\\n" + nombre + " " + apellido + "\\n" + password + "\" -> ";
    }
}
