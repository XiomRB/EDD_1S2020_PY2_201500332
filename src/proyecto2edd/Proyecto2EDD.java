package proyecto2edd;

import java.io.File;
import logica.LecturaJson;
import vista.JPrincipal;


public class Proyecto2EDD {
    
    public static int b = 0;

    public static void main(String[] args) {
        LecturaJson arch = new LecturaJson();
        File bloques = new File( "C:\\Users\\Oliveira Raymundo\\Documents\\gaby\\semestre6\\EDD20\\Lab\\Proyecto2EDD\\Bloques");
        if (bloques.exists()) {
            if(bloques.isDirectory()){
                File[] archivos = bloques.listFiles();
                b = archivos.length;
                for (int i = 0; i < archivos.length; i++) {
                    arch.leerBloque(archivos[i]);
                }
            }
        }
        JPrincipal principal = new JPrincipal();
        principal.setVisible(true);
    }

}