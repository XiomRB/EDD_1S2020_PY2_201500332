package proyecto2edd;

import logica.LUsuario;


public class Proyecto2EDD {

    public static void main(String[] args) {
        LUsuario avl = new LUsuario();
        avl.insertar(1,"g","g","g","gaby");
        avl.insertar(2,"g","g","g","comer");
        avl.insertar(3,"g","g","g","jackson");
        avl.insertar(4,"g","g","g","jw4ever");
        avl.modificar(1, "gabs", "w", "sis", "jwloveu1_4");
        avl.eliminar(3);
        System.out.println(avl.dibujar());
        if(avl.eliminar(4)==null)System.out.println("No existe ese usuario");
        System.out.println(avl.dibujar());
        System.out.println(avl.loguear(1, "gaby"));
        System.out.println(avl.loguear(1, "jwloveu1_4"));
    }
    
}
