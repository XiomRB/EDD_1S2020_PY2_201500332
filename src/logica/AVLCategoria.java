package logica;

import java.util.ArrayList;
import vista.LibrosUsuario;

public class AVLCategoria {
   private NodoAVL raiz;

    public AVLCategoria() {
        this.raiz = null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoAVL raiz) {
        this.raiz = raiz;
    }
    
    public String dibujarRecorrido(String dib){
        String dibujo = "digraph g{\nrankdir = LR;\nnode[shape=box];\n";
        dibujo += dib;
        dibujo = dibujo.substring(0, dibujo.length()-4) + ";\n";
        dibujo += "}";
        return dibujo;
    }
    
    public String inOrden(NodoAVL root){
        String dot = "";
        if(root != null){
            dot += inOrden(root.getIzq());
            dot += "\"" + root.getCategoria() + "\\nCantidad: " + root.libros.total + "\" -> ";
            dot += inOrden(root.getDer());
        }
        return dot;
    }
    
    public String preOrder(NodoAVL root){
        String dot = "";
        if(root != null){
            dot += "\"" + root.getCategoria() + "\\nCantidad: " + root.libros.total + "\" -> ";
            dot += preOrder(root.getIzq());
            dot += preOrder(root.getDer());
        }
        return dot;
    }
    
    public String postOrder(NodoAVL root){
        String dot = "";
        if(root != null){
            dot += preOrder(root.getIzq());
            dot += preOrder(root.getDer());
            dot += "\"" + root.getCategoria() + "\\nCantidad: " + root.libros.total + "\" -> ";
        }
        return dot;
    }
    
    public String dibujar(){
        String dibujo = "digraph g{\nnode[shape = circle];\n";
        if(raiz.getIzq() == null && raiz.getDer() == null) dibujo += "\"" + raiz.getCategoria() + "\\nCantidad: " + raiz.libros.total + "\"";
        else dibujo += dibujarAVL(raiz);
        dibujo += "}";
        return dibujo;
    }
    
    private String dibujarAVL(NodoAVL root){
        String dibujo = "";
    if(root!=null){
        if(root.getIzq()!=null) dibujo += "\"" + root.getCategoria() + "\\nCantidad: " + root.libros.total + "\" -> \"" + root.getIzq().getCategoria() + "\\nCantidad: " + root.getIzq().libros.total + "\";\n";
        if(root.getDer()!=null) dibujo += "\"" + root.getCategoria() + "\\nCantidad: " + root.libros.total + "\" -> \"" + root.getDer().getCategoria() + "\\nCantidad: " + root.getDer().libros.total + "\";\n";
        dibujo += dibujarAVL(root.getIzq());
        dibujo += dibujarAVL(root.getDer());
    }
    return dibujo;
}
    
    private int definirAltura(NodoAVL nodo){
        if(nodo == null) return 0;
        return nodo.getAltura();
    }
    
    private int encontrarAlturaMax(int a1, int a2){
        return Math.max(a1, a2);
    } 
    
    private int getEquilibrio(NodoAVL nodo){
        if(nodo == null) return 0;
        return definirAltura(nodo.getIzq()) - definirAltura(nodo.getDer());
    }
    
    private NodoAVL encontrarMinimo(NodoAVL nodo){
        NodoAVL actual = nodo;
        while(actual.getIzq()!= null) actual = actual.getIzq();
        return actual;
    }
    //ROTACIONES
    private NodoAVL simpleDer(NodoAVL nodo){
        NodoAVL aux = nodo.getIzq();
        NodoAVL aux2 = aux.getDer();
        aux.setDer(nodo);
        nodo.setIzq(aux2);
        nodo.setAltura(encontrarAlturaMax(definirAltura(nodo.getIzq()), definirAltura(nodo.getDer())) + 1);
        aux.setAltura(encontrarAlturaMax(definirAltura(aux.getIzq()), definirAltura(aux.getDer())) + 1);
        return aux;
    }
    
    private NodoAVL simpleIzq(NodoAVL nodo){
        NodoAVL aux = nodo.getDer();
        NodoAVL aux2 = aux.getIzq();
        aux.setIzq(nodo);
        nodo.setDer(aux2);
        nodo.setAltura(encontrarAlturaMax(definirAltura(nodo.getIzq()), definirAltura(nodo.getDer())) + 1);
        aux.setAltura(encontrarAlturaMax(definirAltura(aux.getIzq()), definirAltura(aux.getDer())) + 1);
        return aux;
    }
    //METODOS
    public void insertar(String cat){
        this.raiz = insertarNodo(raiz, cat);
    }
    
    public void eliminar(String cat){
        this.raiz = eliminarNodo(raiz, cat);
    }
    
    private NodoAVL insertarNodo(NodoAVL nodo, String cat){
        if(nodo == null) return new NodoAVL(cat);
        if(cat.compareToIgnoreCase(nodo.getCategoria())<0) nodo.setIzq(insertarNodo(nodo.getIzq(),cat));
        else if(cat.compareToIgnoreCase(nodo.getCategoria())>0) nodo.setDer(insertarNodo(nodo.getDer(), cat));
        else return nodo;
        //redefinir altura
        nodo.setAltura(1 + encontrarAlturaMax(definirAltura(nodo.getIzq()), definirAltura(nodo.getDer())));
        int fe = getEquilibrio(nodo);
        if(fe > 1 && cat.compareToIgnoreCase(nodo.getIzq().getCategoria()) < 0) return simpleDer(nodo);
        if(fe < -1 && cat.compareToIgnoreCase(nodo.getDer().getCategoria()) > 0)return simpleIzq(nodo);
        if(fe > 1 && cat.compareToIgnoreCase(nodo.getIzq().getCategoria()) > 0){
            nodo.setIzq(simpleIzq(nodo.getIzq()));
            return simpleDer(nodo);
        }
        if(fe < -1 && cat.compareToIgnoreCase(nodo.getDer().getCategoria()) < 0){
            nodo.setDer(simpleDer(nodo.getDer()));
            return simpleIzq(nodo);
        }
        return nodo;
    }
    
    private NodoAVL eliminarNodo(NodoAVL root, String cat){
        if(root == null) return root;
        if(cat.compareToIgnoreCase(root.getCategoria())<0) root.setIzq(eliminarNodo(root.getIzq(), cat));
        else if(cat.compareToIgnoreCase(root.getCategoria())>0) root.setDer(eliminarNodo(root.getDer(), cat));
        else{
            if(root.getIzq() == null || root.getDer() == null){
                NodoAVL aux = null;
                if(aux == root.getIzq()) aux = root.getDer();
                else aux = root.getIzq();
                if(aux == null){
                    aux = root;
                    root = null;
                }else root = aux;
            }else{
                NodoAVL aux = encontrarMinimo(root.getDer());
                root.setCategoria(aux.getCategoria());
                root.setDer(eliminarNodo(root.getDer(), aux.getCategoria()));
            }
        }
        if(root == null) return root;
        root.setAltura(1 + encontrarAlturaMax(definirAltura(root.getIzq()), definirAltura(root.getDer())));
        int fe = getEquilibrio(root);
        if(fe > 1 && getEquilibrio(root.getIzq())>=0) return simpleDer(root);
        if(fe > 1 && getEquilibrio(root.getIzq())<0){
            root.setIzq(simpleIzq(root.getIzq()));
            return simpleDer(root);
        }
        if(fe < -1 && getEquilibrio(root.getDer())<=0) return simpleDer(root);
        if(fe < -1 && getEquilibrio(root.getDer())>0){
            root.setDer(simpleDer(root.getDer()));
            return simpleIzq(root);
        }
        return root;
    }
    
    public NodoAVL buscar(String cat,NodoAVL root){
        if(root == null)return null;
        else if(root.getCategoria().equalsIgnoreCase(cat)) return root;
        else if(root.getCategoria().compareToIgnoreCase(cat)<0) return buscar(cat,root.getDer());
        else return buscar(cat,root.getIzq());
    }
    
    public void buscarCatUsuarios(int carnet,ArrayList<LibrosUsuario> libUs, NodoAVL root){
        if(root!=null){
            libUs.add(new LibrosUsuario(root.getCategoria(), new ArrayList<>()));
            if(root.libros.getRaiz()!=null)root.libros.getRaiz().buscarCarnet(carnet, libUs.get(libUs.size()-1));
            if(libUs.get(libUs.size()-1).getLibros().isEmpty()) libUs.remove(libUs.size()-1);
            buscarCatUsuarios(carnet, libUs, root.getIzq());
            buscarCatUsuarios(carnet, libUs, root.getDer());
        }
    }
    
    public void crearCategBiblioteca(ArrayList<LibrosUsuario> libUs, NodoAVL root){
        if(root!=null){
            libUs.add(new LibrosUsuario(root.getCategoria(), new ArrayList<>()));
            if(root.libros.getRaiz()!= null)root.libros.getRaiz().crearLibrosBiblioteca(libUs.get(libUs.size()-1));
            crearCategBiblioteca(libUs, root.getIzq());
            crearCategBiblioteca(libUs, root.getDer());
        }
    }
}