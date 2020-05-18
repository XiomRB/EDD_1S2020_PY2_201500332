package logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vista.Operaciones;

public class LecturaJson {
    JSONParser parser = new JSONParser();
    public JSONArray leerJson(File arch, String dato){
        try {
            Object obj = parser.parse(new InputStreamReader(new FileInputStream(arch), "utf-8"));
            //Object obj = parser.parse(new FileReader(arch));
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
        Object[][] libros;
        if (books != null) {
            libros = new Object[books.size()][8];
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
        }else libros = null;
        return libros;
    }
    
    public void leerBloque(File arch){
        try {
            Object obj = parser.parse(new InputStreamReader(new FileInputStream(arch), "utf-8"));
            JSONObject jsonob = (JSONObject) obj;
            Operaciones.nodo.getBloques().añadirBloqueAnterior(Integer.parseInt(jsonob.get("INDEX").toString()), jsonob.get("TIMESTAMP").toString(), Integer.parseInt(jsonob.get("NONCE").toString()), jsonob.get("DATA").toString(), jsonob.get("PREVIUSHASH").toString(), jsonob.get("HASH").toString());
        } catch (FileNotFoundException e) {}
        catch(IOException e){}
        catch(ParseException e){}
        JSONArray lectura = leerJson(arch, "DATA");
        for (int i = 0; i < lectura.size(); i++) {
            JSONObject objetos = (JSONObject) lectura.get(i);
            JSONArray crearUsuarios = (JSONArray) objetos.get("CREAR_USUARIO");
            JSONArray eliminarUsuarios = (JSONArray) objetos.get("ELIMINAR_USUARIO");
            JSONArray editarUsuarios = (JSONArray) objetos.get("EDITAR_USUARIO");
            JSONArray crearLibros = (JSONArray) objetos.get("CREAR_LIBRO");
            JSONArray eliminarLibros = (JSONArray) objetos.get("ELIMINAR_LIBRO");
            JSONArray crearCateg = (JSONArray) objetos.get("CREAR_CATEGORIA");
            JSONArray eliminarCateg = (JSONArray) objetos.get("ELIMINAR_CATEGORIA");
            if (crearUsuarios != null) {
                for (int j = 0; j < crearUsuarios.size(); j++) {
                    JSONObject ob = (JSONObject) crearUsuarios.get(j);
                    
                    Operaciones.usuarios.insertar(Integer.parseInt(ob.get("Carnet").toString()), ob.get("Nombre").toString(), ob.get("Apellido").toString(), ob.get("Carrera").toString(), ob.get("Password").toString());                  
                }
            }
            if (crearLibros != null) {
                for (int j = 0; j < crearLibros.size(); j++) {
                    JSONObject ob = (JSONObject) crearLibros.get(j);
                    NodoAVL categoria  = Operaciones.categorias.buscar(ob.get("Categoria").toString(), Operaciones.categorias.getRaiz());
                    if (categoria == null)Operaciones.categorias.insertar(ob.get("Categoria").toString());
                    categoria = Operaciones.categorias.buscar(ob.get("Categoria").toString(), Operaciones.categorias.getRaiz());
                    categoria.libros.insertar(Integer.parseInt(ob.get("ISBN").toString()), ob.get("Titulo").toString(), ob.get("Autor").toString(), ob.get("Editorial").toString(), Integer.parseInt(ob.get("Anio").toString()) , Integer.parseInt(ob.get("Edicion").toString()), ob.get("Idioma").toString(), Integer.parseInt(ob.get("Carnet").toString()));
                }
            }
            if (editarUsuarios != null) {
                for (int j = 0; j < editarUsuarios.size(); j++) {
                    JSONObject ob = (JSONObject) editarUsuarios.get(j);
                    Operaciones.usuarios.modificarUsuario(Integer.parseInt(ob.get("Carnet").toString()), ob.get("Nombre").toString(), ob.get("Apellido").toString(), ob.get("Carrera").toString(), ob.get("Password").toString());
                }
            }
            if (crearCateg != null) {
                for (int j = 0; j < crearCateg.size(); j++) {
                    JSONObject ob = (JSONObject) crearCateg.get(j);
                    Operaciones.categorias.insertar(ob.get("Categoria").toString());
                }
            }
            if (eliminarLibros != null) {
                for (int j = 0; j < eliminarLibros.size(); j++) {
                    JSONObject ob = (JSONObject) eliminarLibros.get(j);
                    NodoAVL categoria = Operaciones.categorias.buscar(ob.get("Categoria").toString(), Operaciones.categorias.getRaiz());
                    if(categoria != null) categoria.libros.eliminar(Integer.parseInt(ob.get("ISBN").toString()));
                }
            }
            if (eliminarCateg != null) {
                for (int j = 0; j < eliminarCateg.size(); j++) {
                    JSONObject ob = (JSONObject) eliminarCateg.get(j);
                    Operaciones.categorias.eliminar(ob.get("Categoria").toString());
                }
            }
            if (eliminarUsuarios != null) {
                for (int j = 0; j < eliminarUsuarios.size(); j++) {
                    JSONObject ob = (JSONObject) eliminarUsuarios.get(j);
                    Operaciones.usuarios.eliminar(Integer.parseInt(ob.get("Carnet").toString()));
                }
            }
        }
    }
}
