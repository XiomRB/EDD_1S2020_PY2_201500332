package logica;

public class THUsuario {
    LUsuario[] usuarios;
    int tam;

    public THUsuario() {
        tam = 45;
        usuarios = new LUsuario[tam];
        for (int i = 0; i < tam; i++) {
            usuarios[i] = new LUsuario();
        }
    }
    
    public LUsuario buscarIndice(int carnet){
        int indice = carnet%tam;
        return usuarios[indice];
    }
    
    public String loguear(int carnet, String pass){
        LUsuario users = buscarIndice(carnet);
        return users.loguear(carnet, pass);
    }
    
    public String insertar(int carnet, String nombre, String apellido, String carrera, String clave){
        LUsuario usuario = buscarIndice(carnet);
        if(usuario.buscar(carnet) == null) usuario.insertar(carnet, nombre, apellido, carrera, clave);
        else return "El usuario ya existe";
        return "Usuario agregado";
    }
    
    public String eliminar(int carnet){
        LUsuario usuario = buscarIndice(carnet);
        if(usuario.eliminar(carnet) == null) return "El usuario no existe";
        else return "Usuario eliminado";
    }
    
    public String dibujar(){
        String dibujo = "rankdir = LR;\nnode [shape = box]\n";
        dibujo += "n [shape = record, label=\"";
        for (int i = 0; i < tam-1; i++) dibujo += "<f" + i + ">" + i + " | ";
        dibujo += "<f" + 44 + ">" + 44 + "\"];\n";
        for (int i = 0; i < tam; i++) if(usuarios[i].getPrimero()!= null)dibujo += " n:f" +  i + " -> " + usuarios[i].dibujar() + ";\n";
        return dibujo;
    }
}