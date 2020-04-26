package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LecturaJson {
    JSONParser parser = new JSONParser();
    
    public JSONArray leerJson(File arch, String dato){
        try {
            Object obj = parser.parse(new FileReader(arch));
            JSONObject jsonob = (JSONObject) obj;
            JSONArray usuarios = (JSONArray)jsonob.get(dato);
            return usuarios;
        } catch (FileNotFoundException e) {}
        catch(IOException e){}
        catch(ParseException e){}
        return null;
    }
    
    public Object[][] leerUsuario(File arch){
        JSONArray usuarios = leerJson(arch,"Usuarios");
        Object[][] users;
        if (usuarios != null) {
            users = new Object[usuarios.size()][5];
            for (int i = 0; i < usuarios.size(); i++) {
                JSONObject user = (JSONObject)usuarios.get(i);
                users[i][0] = user.get("Carnet");
                users[i][1] = user.get("Nombre");
                users[i][2] = user.get("Apellido");
                users[i][3] = user.get("Carrera");
                users[i][4] = user.get("Password");
            }
        }else users = null;
        return users;
    }
    
    public Object[][] leerLibro(File arch){
        JSONArray books = leerJson(arch,"Libros");
        Object[][] libros = new Object[books.size()][8];
        for (int i = 0; i < books.size(); i++) {
            JSONObject libro = (JSONObject)books.get(i);
            libros[i][0] = libro.get("ISBN");
            libros[i][1] = libro.get("Titulo");
            libros[i][2] = libro.get("Autor");
            libros[i][3] = libro.get("Editorial");
            libros[i][4] = libro.get("Año");
            libros[i][5] = libro.get("Edicion");
            libros[i][6] = libro.get("Idioma");
            libros[i][7] = libro.get("Categoria");
        }
        return libros;
    }
}
