package blockchain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Data {
    
    public JSONObject crearUs(int carnet, String nombre, String apellido,String carrera,String contraseña){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject usuario = new JSONObject();
        usuario.put("Password",contraseña);
        usuario.put("Carrera", carrera);
        usuario.put("Apellido",apellido);
        usuario.put("Nombre", nombre);
        usuario.put("Carnet",carnet);
        lista.add(usuario);
        ob.put("CREAR_USUARIO", lista);
        return ob;
    }
    
     public JSONObject editarUs(int carnet, String nombre, String apellido,String carrera,String contraseña){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject usuario = new JSONObject();
        usuario.put("Password",contraseña);
        usuario.put("Carrera", carrera);
        usuario.put("Apellido",apellido);
        usuario.put("Nombre", nombre);
        usuario.put("Carnet",carnet);
        lista.add(usuario);
        ob.put("EDITAR_USUARIO", lista);
        return ob;
    }
    
    public JSONObject eliminUsuario(int carnet){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject accion = new JSONObject();
        accion.put("Carnet",carnet);
        lista.add(accion);
        ob.put("ELIMINAR_USUARIO", lista);
        return ob;
    }
    
    public JSONObject elimLibro(String cat, int isbn){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject libro = new JSONObject();
        libro.put("Categoria",cat);
        libro.put("ISBN",isbn);
        lista.add(libro);
        ob.put("ELIMINAR_LIBRO", lista);
        return ob;
    }
    
    public JSONObject crearLib(int isbn,String tit, String autor, String editorial, int anio, int ed, String idioma, int carnet, String categoria){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject libro = new JSONObject();
        libro.put("ISBN",isbn);
        libro.put("Titulo", tit);
        libro.put("Autor", autor);
        libro.put("Editorial",editorial);
        libro.put("Edicion", ed);
        libro.put("Anio",anio);
        libro.put("Idioma",idioma);
        libro.put("Carnet", carnet);
        libro.put("Categoria",categoria);
        lista.add(libro);
        ob.put("CREAR_LIBRO", lista);
        return ob;
    }
    
    public JSONObject operacionCategoria(String operacion, String dato){
        JSONObject ob = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject categoria = new JSONObject();
        categoria.put("Categoria",dato);
        lista.add(categoria);
        ob.put(operacion, lista);
        return ob;
    }
}
