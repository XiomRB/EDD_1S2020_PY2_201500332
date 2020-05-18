package vista;

import blockchain.Data;
import blockchain.Nodo;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import logica.AVLCategoria;
import logica.Archivo;
import logica.LecturaJson;
import logica.Libro;
import logica.NodoAVL;
import logica.THUsuario;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import proyecto2edd.Proyecto2EDD;

public class Operaciones {
    public static AVLCategoria categorias = new AVLCategoria(); //categorias de toda la biblioteca
    public static THUsuario usuarios = new THUsuario(); // usuarios de toda la biblioteca   
    public static ArrayList<LibrosUsuario> categoriasUsuario = new ArrayList<>(); //categorias del usuario logueado
    public static Libro librovisitado = null;
    public static ArrayList<LibrosUsuario> categoriasBiblioteca = new ArrayList<>();
    public static ArrayList<Book> buscados = new ArrayList<>();
    
    public static Nodo nodo = new Nodo("00");
    
    public String cat = "";
    public JSONArray data = new JSONArray();
    
    private LecturaJson carga = new LecturaJson();
    private Archivo archi = new Archivo();
    public Data operacionData = new Data();
    
    public String crearUsuario(int usuario,String nombre, String apellido,String carrera, String contrasena){
        String msj = usuarios.insertar(usuario, nombre, apellido,carrera, contrasena);
        if(msj.equals("Usuario agregado"))  data.add(operacionData.crearUs(usuario, nombre, apellido, carrera, contrasena));
        return msj;
    }
    
    public void cargarUsuarios(File archivo){//carga masiva de usuarios al sistema
        Object[][] users = carga.leerUsuario(archivo);
            if (users != null){
                for (int i = 0; i < users.length; i++) {
                    usuarios.insertar(Integer.parseInt(users[i][0].toString()), users[i][1].toString(), users[i][2].toString(), users[i][3].toString(), users[i][4].toString());
                    data.add(operacionData.crearUs(Integer.parseInt(users[i][0].toString()), users[i][1].toString(), users[i][2].toString(), users[i][3].toString(), users[i][4].toString()));
                }
                JOptionPane.showMessageDialog(null, "Usuarios cargados con exito");    
            }else JOptionPane.showMessageDialog(null, "No se encontraron usuarios en el archivo");
    }
    
    public void cargarLibros(File archivo, int prop){//carga masiva de libros al sistema
        Object[][] libros = carga.leerLibro(archivo);
        if (libros != null){
            for (int i = 0; i < libros.length; i++) {
                int isbn = Integer.parseInt(libros[i][0].toString());
                int edicion = Integer.parseInt(libros[i][5].toString());
                int anio = Integer.parseInt(libros[i][4].toString());
                crearLibro(isbn, libros[i][1].toString(), libros[i][2].toString(), libros[i][3].toString(), anio, edicion, libros[i][6].toString(), prop, libros[i][7].toString());
            }
            JOptionPane.showMessageDialog(null, "Libros cargados con exito");    
        }else JOptionPane.showMessageDialog(null, "No se encontraron libros en el archivo");
    }
    
    public String crearLibro(int isbn, String tit, String autor, String edit, int anio, int ed, String idioma, int prop, String cat){ //inserta un nuevo libro al arbol de la categoria seleccionada
        NodoAVL categoria  = categorias.buscar(cat, categorias.getRaiz());
        String msj = "";
        if ( categoria == null) {
            categorias.insertar(cat); // si no existe la categoria, se crea antes de agregar el libro
            categoria = categorias.buscar(cat, categorias.getRaiz());
            categoriasUsuario.add(new LibrosUsuario(cat, new ArrayList<>()));
            categoriasBiblioteca.add(new LibrosUsuario(cat, new ArrayList<>()));
        }
        int j = 0;
        while(j < categoriasUsuario.size()){
            if (categoriasUsuario.get(j).getCategoria().equalsIgnoreCase(cat))break;
            j++;
        }
        if(j == categoriasUsuario.size()){
            categoriasUsuario.add(new LibrosUsuario(categoria.getCategoria(), new ArrayList<>()));
        }
        msj = categoria.libros.insertar(isbn, tit, autor, edit, anio, ed, idioma, prop);
        if (msj.equals("Libro agregado")) {
            categoriasUsuario.get(j).setLibro(isbn, tit);
            j = 0;
            while (j < categoriasBiblioteca.size()) {            
                if (categoriasBiblioteca.get(j).getCategoria().equalsIgnoreCase(cat)) break;
                j++;
            }
            categoriasBiblioteca.get(j).setLibro(isbn, tit);
            data.add(operacionData.crearLib(isbn, tit, autor, edit, anio, ed, idioma, prop, cat));
        }
        return msj;
    }
    
    public String editarCuenta(int carnet,String nombre, String apellido, String carrera, String pass){
        String msj =usuarios.modificarUsuario(carnet,nombre,apellido,carrera,pass);
        data.add(operacionData.editarUs(carnet, nombre, apellido, carrera,pass));
        return msj;
    }
    
    public void eliminarCuenta(int cuenta){
        /*for (int i = 0; i < categoriasUsuario.size(); i++) {
            NodoAVL categoria = categorias.buscar(categoriasUsuario.get(i).getCategoria(), categorias.getRaiz());
            for (int j = 0; j < categoriasUsuario.get(i).getLibros().size(); j++) {
                categoria.libros.eliminar(categoriasUsuario.get(i).getLibro(j).getIsbn()); //elimina de la biblioteca virtual, todos los libros pertenecientes a su cuenta
            }
        }*/
        usuarios.eliminar(cuenta);
        data.add(operacionData.eliminUsuario(cuenta));
        generarBloque();
        JOptionPane.showMessageDialog(null, "Cuenta eliminada");
    }
    
    public void verLibro(String cat, int isbn){//trae el libro buscado
        NodoAVL categoria = categorias.buscar(cat, categorias.getRaiz());
        if (categoria!= null) librovisitado = categoria.libros.buscar(isbn);
        else librovisitado = null;
    }
    
    public void llenarLibrosUsuario(int carnet){//prepara la interfaz al momento de loguearse
        categoriasUsuario.clear();
        categorias.buscarCatUsuarios(carnet, categoriasUsuario, categorias.getRaiz());
        categoriasBiblioteca.clear();
        categorias.crearCategBiblioteca(categoriasBiblioteca, categorias.getRaiz());
    }
    
    public String eliminarLibro(int isbn, String cat){
        NodoAVL nodo = categorias.buscar(cat, categorias.getRaiz());
        nodo.libros.eliminar(isbn);
        data.add(operacionData.elimLibro(cat, isbn));
        int i = 0;
        while(i < categoriasUsuario.size()){
            if (categoriasUsuario.get(i).getCategoria().equals(cat)) {
                int j = 0;
                while(j < categoriasUsuario.get(i).getLibros().size()){
                    if(categoriasUsuario.get(i).getLibro(j).getIsbn() == isbn){
                        categoriasUsuario.get(i).getLibros().remove(j);
                        break;
                    }
                    j++;
                }
                break;
            }
            i++;
        }
        i = 0;
        while(i < categoriasBiblioteca.size()){
            if (categoriasBiblioteca.get(i).getCategoria().equals(cat)) {
                int j = 0;
                while (j < categoriasBiblioteca.get(i).getLibros().size()) {                    
                    if(categoriasBiblioteca.get(i).getLibro(j).getIsbn() == isbn){
                        categoriasBiblioteca.get(i).getLibros().remove(j);
                        break;
                    }
                    j++;
                }
                break;
            }
            i++;
        }
        return "Libro eliminado";
    }
    
    //--------------------------------------------------------------------------------------REPORTES-------------------------------------------------------------------------
    public void reportarCategorias(){
        archi.generarGraphviz("Categorias", categorias.dibujar());
        archi.generarGraphviz("CategoriasInOrden", categorias.dibujarRecorrido(categorias.inOrden(categorias.getRaiz())));
        archi.generarGraphviz("CategoriasPreOrden", categorias.dibujarRecorrido(categorias.preOrder(categorias.getRaiz())));
        archi.generarGraphviz("CategoriasPostOrden", categorias.dibujarRecorrido(categorias.postOrder(categorias.getRaiz())));
    }
    
    public String reportarUsuarios(){
        String reporte = "digraph g{\n" + usuarios.dibujar() + "}";
        archi.generarGraphviz("Usuarios", reporte);
        return "Reporte generado";
    }
    public String reportarLibro(String cat){
        NodoAVL categoria = categorias.buscar(cat, categorias.getRaiz());
        if (categoria!=null) {
            String dibujo = "digraph g{\n node[shape = record];\n" + categoria.libros.dibujar() + "}";
            archi.generarGraphviz(cat, dibujo);
            return "Reporte generado";
        }
        return "No existe esa categoria";
    }
    
    public String reportarBloques(){
        String dibujo = nodo.getBloques().dibujar();
        if(nodo.getBloques().getPrimero() == null) return "No se han generado bloques";
        archi.generarGraphviz("Bloques", dibujo);
        return "Reporte generado";
    }
    
    //--------------------------------------------------------------------------BUSQUEDA DE LIBROS--------------------------------------------------------------------------
    public void buscarPorTitulo(String tit){
        buscados.clear();
        int i = 0;
        while(i < categoriasUsuario.size()){
            int j = 0;
            while(j < categoriasUsuario.get(i).getLibros().size()){
                if (categoriasUsuario.get(i).getLibro(j).getTitulo().contains(tit)){
                    Book encontrado = new Book(categoriasUsuario.get(i).getLibro(j).getTitulo(), categoriasUsuario.get(i).getLibro(j).getIsbn());
                    encontrado.setCat(categoriasUsuario.get(i).getCategoria());
                    buscados.add(encontrado);
                }
                j++;
            }
            i++;
        }
    }
    
    public void buscarPorTituloBiblioteca(String tit){
        buscados.clear();
        int i = 0;
        while(i < categoriasBiblioteca.size()){
            int j = 0;
            while(j < categoriasBiblioteca.get(i).getLibros().size()){
                if (categoriasBiblioteca.get(i).getLibro(j).getTitulo().contains(tit)){
                    Book encontrado = new Book(categoriasBiblioteca.get(i).getLibro(j).getTitulo(), categoriasBiblioteca.get(i).getLibro(j).getIsbn());
                    encontrado.setCat(categoriasBiblioteca.get(i).getCategoria());
                    buscados.add(encontrado);
                }
                j++;
            }
            i++;
        }
    }
    
    public void buscarLibroPorISBN(int isbn){
        librovisitado = null;
        cat = "";
        buscarPorISBN(categorias.getRaiz(), isbn);
    }
    
    public void buscarPorISBN(NodoAVL root,int isbn){
        if (root!= null) {
            librovisitado = root.libros.buscar(isbn);
            cat = root.getCategoria();
            if (librovisitado == null) buscarPorISBN(root.getIzq(), isbn);
            if (librovisitado == null) buscarPorISBN(root.getDer(), isbn);
        }
    }
    
    public String generarBloque(){
        if(!data.isEmpty() || data != null){
            SimpleDateFormat d = new SimpleDateFormat("dd:MM:yy::HH:mm:ss");
            nodo.getBloques().agregarBloque(d.format(new Date()),data.toString());
            JSONObject block = new JSONObject();
            block.put("INDEX",nodo.getBloques().getUltimo().getIndex());
            block.put("TIMESTAMP",nodo.getBloques().getUltimo().getTimestamp());
            block.put("NONCE",nodo.getBloques().getUltimo().getNonce());
            block.put("DATA",data);
            block.put("PREVIUSHASH",nodo.getBloques().getUltimo().getPreviushash());
            block.put("HASH",nodo.getBloques().getUltimo().getHash());
            String msj = archi.guardarBloque(block.toJSONString(), "Bloque" + Proyecto2EDD.b);
            if(msj.equals("Bloque generado")) Proyecto2EDD.b++;
            data.clear();
            return msj;
        }
        return "No se ha generado ninguna accion dentro de la biblioteca";
    }

}