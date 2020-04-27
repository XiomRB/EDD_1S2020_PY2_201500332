package vista;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.AVLCategoria;
import logica.LecturaJson;
import logica.NodoAVL;
import logica.THUsuario;

public class Operaciones {
    public static AVLCategoria categorias = new AVLCategoria(); //categorias de toda la biblioteca
    public static THUsuario usuarios = new THUsuario(); // usuarios de toda la biblioteca
    
    public static ArrayList<LibrosUsuario> categoriasUsuario = new ArrayList<>(); //categorias del usuario logueado
    
    private LecturaJson carga = new LecturaJson();
    
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
                int j = 0;
                while(j < categoriasUsuario.size()){
                    if (categoriasUsuario.get(j).getCategoria().equalsIgnoreCase(libros[i][7].toString()))break;
                    j++;
                }
                if (j >= categoriasUsuario.size()) categoriasUsuario.add(new LibrosUsuario(libros[i][7].toString(), new ArrayList<Book>()));
                String msj = crearLibro(isbn, libros[i][1].toString(), libros[i][2].toString(), libros[i][3].toString(), anio, edicion, libros[i][6].toString(), prop, libros[i][7].toString());
                if (msj.equals("Libro agregado")) categoriasUsuario.get(j).setLibro(isbn, libros[i][1].toString());
            }
            JOptionPane.showMessageDialog(null, "Libros cargados con exito");    
        }else JOptionPane.showMessageDialog(null, "No se encontraron libros en el archivo");
    }
    
    public String crearLibro(int isbn, String tit, String autor, String edit, int anio, int ed, String idioma, int prop, String cat){ //inserta un nuevo libro al arbol de la categoria seleccionada
        NodoAVL categoria  = categorias.buscar(cat, categorias.getRaiz());
        if ( categoria == null) {
            categorias.insertar(cat); // si no existe la categoria, se crea antes de agregar el libro
            categoria = categorias.buscar(cat, categorias.getRaiz());
        }
        return categoria.libros.insertar(isbn, tit, autor, edit, anio, ed, idioma, prop);
    }
    
}
