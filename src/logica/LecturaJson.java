package logica;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LecturaJson {
    JSONParser parser = new JSONParser();
    
    public JSONArray leerJson(String arch){
        try {
            Object obj = parser.parse(new FileReader(arch));
            JSONObject jsonob = (JSONObject) obj;
            JSONArray usuarios = (JSONArray)jsonob.get("Usuarios");
            return usuarios;
        } catch (FileNotFoundException e) {}
        catch(IOException e){}
        catch(ParseException e){}
        return null;
    }
    
    public Object[][] leerUsuario(String arch){
        JSONArray usuarios = leerJson(arch);
        Object[][] users = new Object[usuarios.size()][5];
        for (int i = 0; i < usuarios.size(); i++) {
            JSONObject user = (JSONObject)usuarios.get(i);
            users[i][0] = user.get("Carnet");
            users[i][1] = user.get("Nombre");
            users[i][2] = user.get("Apellido");
            users[i][3] = user.get("Carrera");
            users[i][4] = user.get("Password");
        }
        for (int i = 0; i < users.length; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(users[i][j] + " ");
            }
            System.out.println("");
        }
        return users;
    }
    
    public void leerLibro(){
        
    }
}
