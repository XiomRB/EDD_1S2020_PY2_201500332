package vista;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.AVLCategoria;
import logica.Archivo;
import logica.LecturaJson;
import logica.Libro;
import logica.NodoAVL;
import logica.THUsuario;

public class Operaciones {
    public static AVLCategoria categorias = new AVLCategoria(); //categorias de toda la biblioteca
    public static THUsuario usuarios = new THUsuario(); // usuarios de toda la biblioteca   
    public static ArrayList<LibrosUsuario> categoriasUsuario = new ArrayList<>(); //categorias del usuario logueado
    public static Libro librovisitado = null;
    public static ArrayList<LibrosUsuario> categoriasBiblioteca = new ArrayList<>();
    
    private LecturaJson carga = new LecturaJson();
    private Archivo archi = new Archivo();
    
    public void cargarUsuarios(File archivo){//carga masiva de usuarios al sistema
        Object[][] users = carga.leerUsuario(archivo);
            if (users != null){
                for (int i = 0; i < users.length; i++) {
                    usuarios.insertar(Integer.parseInt(users[i][0].toString()), users[i][1].toString(), users[i][2].toString(), users[i][3].toString(), users[i][4].toString());
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
        msj = categoria.libros.insertar(isbn, tit, autor, edit, anio, ed, idioma, prop);
        if (msj.equals("Libro agregado")) {
            categoriasUsuario.get(j).setLibro(isbn, tit);
            j = 0;
            while (j < categoriasBiblioteca.size()) {            
                if (categoriasBiblioteca.get(j).getCategoria().equalsIgnoreCase(cat)) break;
                j++;
            }
            categoriasBiblioteca.get(j).setLibro(isbn, tit);
        }
        return msj;
    }
    
    public void eliminarCuenta(int cuenta){
        for (int i = 0; i < categoriasUsuario.size(); i++) {
            NodoAVL categoria = categorias.buscar(categoriasUsuario.get(i).getCategoria(), categorias.getRaiz());
            for (int j = 0; j < categoriasUsuario.get(i).getLibros().size(); j++) {
                categoria.libros.eliminar(categoriasUsuario.get(i).getLibro(j).getIsbn()); //elimina de la biblioteca virtual, todos los libros pertenecientes a su cuenta
            }
        }
        usuarios.eliminar(cuenta);
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
    
    //REPORTES
    public void reportarCategorias(){
        archi.generarGraphviz("Categorias", categorias.dibujar());
        archi.generarGraphviz("CategoriasInOrden", categorias.dibujarRecorrido(categorias.inOrden(categorias.getRaiz())));
        archi.generarGraphviz("CategoriasPreOrden", categorias.dibujarRecorrido(categorias.preOrder(categorias.getRaiz())));
        archi.generarGraphviz("CategoriasPostOrden", categorias.dibujarRecorrido(categorias.postOrder(categorias.getRaiz())));
    }
    
    public void reportarUsuarios(){
        String reporte = "digraph g{\n" + usuarios.dibujar() + "}";
        archi.generarGraphviz("Usuarios", reporte);
    }
    public String reportarLibro(String cat){
        NodoAVL categoria = categorias.buscar(cat, categorias.getRaiz());
        if (categoria!=null) {
            String dibujo = "digraph g{\n node[shape = record];\n" + categoria.libros.dibujar() + "}";
            System.out.println(dibujo);
            //archi.generarGraphviz(cat, dibujo);
            return dibujo;
        }
        return "No existe esa categoria";
    }
}