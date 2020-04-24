package proyecto2edd;

import logica.ABLibro;


public class Proyecto2EDD {

    public static void main(String[] args) {
        ABLibro t = new ABLibro();
        t.insertar(10,"","","",0,0,"",0); 
        t.insertar(20,"","","",0,0,"",0);
        t.insertar(5,"","","",0,0,"",0);
        t.insertar(6,"","","",0,0,"",0);
        t.insertar(9,"","","",0,0,"",0);
        t.insertar(30,"","","",0,0,"",0);
        t.insertar(1,"","","",0,0,"",0);
        t.insertar(4,"","","",0,0,"",0);
        t.insertar(3,"","","",0,0,"",0);
        t.insertar(40,"","","",0,0,"",0);
        t.insertar(400,"","","",0,0,"",0);
        t.insertar(2,"","","",0,0,"",0);
        t.insertar(450,"","","",0,0,"",0);
        t.insertar(465,"","","",0,0,"",0);
        t.insertar(470,"","","",0,0,"",0);
        t.insertar(475,"","","",0,0,"",0);
        t.insertar(480,"","","",0,0,"",0);
        t.insertar(485,"","","",0,0,"",0);
        /*t.insertar(490,"","","",0,0,"",0);
        t.insertar(495,"","","",0,0,"",0);
        t.insertar(500,"","","",0,0,"",0);*/
        if(t.buscar(40) == null)System.out.println("no esta");
        else System.out.println("encontrado");
        if(t.buscar(1) == null)System.out.println("no esta");
        else System.out.println("encontrado");
        if(t.buscar(20) == null)System.out.println("no esta");
        else System.out.println("encontrado");
        if(t.buscar(6) == null)System.out.println("no esta");
        else System.out.println("encontrado");
        //t.getRaiz().recorrer();
        t.dibujar();
    }
    
}
