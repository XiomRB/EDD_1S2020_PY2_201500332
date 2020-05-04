package logica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Archivo {
    
    public void guardarArchivo(String contenido, String nombre,String carpeta,String extension){
        File archivo = new File(System.getProperty("user.dir") + "\\" + carpeta + "\\" + nombre + extension);
        try{
            FileOutputStream reporte = new FileOutputStream(archivo);
            byte[] salida = contenido.getBytes();
            reporte.write(salida);
        }catch(Exception e){JOptionPane.showMessageDialog(null,e.getMessage());}
    }
    
    public void generarGraphviz(String nombre,String reporte){
        String d = System.getProperty("user.dir");
        guardarArchivo(reporte, nombre,"reportes", ".dot");
        String dotPath = "C:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";  
        String dot = "\"" + d + "\\reportes\\" + nombre + ".dot\"";
        String png = "\"" + d + "\\reportes\\" + nombre + ".png\"";
        String tParam = "-Tpng";
        String oParam = "-o";
        
        String[] cmd = new String[5];
        cmd[0] = dotPath;
        cmd[1] = tParam;
        cmd[2] = dot;
        cmd[3] = oParam;
        cmd[4] = png;
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec(cmd);
        } catch (IOException ex) { JOptionPane.showMessageDialog(null, ex.getMessage());}
    }
    
    public String crearCarpeta(String carpeta){
        File directorio = new File("C:/Users/OLGA BARRIOS/Desktop/GraficasOLC1P1/" + carpeta);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                return carpeta;
            }
        }
        return carpeta;
    }
    
    public String guardarBloque(String data, String nombre){
        
            try {
			FileWriter file = new FileWriter(System.getProperty("user.dir") + "\\Bloques\\"+ nombre);
			file.write(data);
			file.flush();
			file.close();
                        return "Bloque generado";
		} catch (IOException e) {return "No se pudo guardar el bloque";}
    }
    
}