package logica;

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
    
    public void insertar(String cat){
        NodoAVL nuevo = new NodoAVL(cat);
        if(raiz == null) raiz = nuevo;
        else raiz = insertarNodo(nuevo, raiz);
    }
    
    public void inOrden(NodoAVL root){
        if(root != null){
            inOrden(root.getIzq());
            System.out.print(root.getCategoria() + "  ");
            inOrden(root.getDer());
        }
    }
    
    public void preOrder(NodoAVL root){
        if(root != null){
            System.out.print(root.getCategoria() + "  ");
            preOrder(root.getIzq());
            preOrder(root.getDer());
        }
    }
    
    public void postOrder(NodoAVL root){
        if(root != null){
            preOrder(root.getIzq());
            preOrder(root.getDer());
            System.out.print(root.getCategoria() + "  ");
        }
    }
    
    public NodoAVL insertarNodo(NodoAVL nuevo, NodoAVL sub){
        NodoAVL nuevaraiz = sub;
        if(nuevo.getCategoria().compareToIgnoreCase(sub.getCategoria())<0){
            if(sub.getIzq() == null) sub.setIzq(nuevo);
            else{ 
                sub.setIzq(insertarNodo(nuevo, sub.getIzq()));
                if(obtenerFE(sub.getIzq()) - obtenerFE(sub.getDer()) == 2){
                    if(nuevo.getCategoria().compareToIgnoreCase(sub.getIzq().getCategoria())<0) nuevaraiz = rotacionIzq(sub);
                    else nuevaraiz = dobleIzq(sub);   
                }
            }
        }else if(nuevo.getCategoria().compareToIgnoreCase(sub.getCategoria())>0){
            if(sub.getDer()== null) sub.setDer(nuevo);
            else{
                sub.setDer(insertarNodo(nuevo, sub.getDer()));
                if(obtenerFE(sub.getDer()) - obtenerFE(sub.getIzq()) == 2){
                    if(nuevo.getCategoria().compareToIgnoreCase(sub.getDer().getCategoria())>0){
                        nuevaraiz = rotacionDer(sub);
                    }else nuevaraiz = dobleDer(sub);
                }
            }
        }
        if((sub.getIzq()== null) && (sub.getDer()!= null))sub.setFe(sub.getDer().getFe()+1);
        else if((sub.getDer()== null) && (sub.getIzq()!= null)) sub.setFe(sub.getIzq().getFe()+1);
        else sub.setFe(Math.max(obtenerFE(sub.getIzq()), obtenerFE(sub.getDer()))+1);
        return nuevaraiz;
    }
   
    public NodoAVL buscar(String cat,NodoAVL r){
        if(this.raiz == null)return null;
        else if(r.getCategoria().equalsIgnoreCase(cat)) return r;
        else if(r.getCategoria().compareToIgnoreCase(cat)<0) return buscar(cat,r.getDer());
        else return buscar(cat,r.getIzq());
    }
    
    public int obtenerFE(NodoAVL nodo){
        if(nodo==null) return -1;
        else return nodo.getFe();
    }
    
    public NodoAVL rotacionIzq(NodoAVL nodo){//rot simple izq
        NodoAVL aux = nodo.getIzq();
        nodo.setIzq(aux.getDer());
        aux.setDer(nodo);
        nodo.setFe(Math.max(obtenerFE(nodo.getIzq()), obtenerFE(nodo.getDer()))+1);
        aux.setFe(Math.max(obtenerFE(aux.getIzq()), obtenerFE(aux.getDer()))+1);
        return aux;
    }
    
    public NodoAVL rotacionDer(NodoAVL nodo){//rot simple derecha
        NodoAVL aux = nodo.getDer();
        nodo.setDer(aux.getIzq());
        aux.setIzq(nodo);
        nodo.setFe(Math.max(obtenerFE(nodo.getIzq()), obtenerFE(nodo.getDer()))+1);
        aux.setFe(Math.max(obtenerFE(aux.getIzq()), obtenerFE(aux.getDer()))+1);
        return aux;
    }
    
    public NodoAVL dobleIzq(NodoAVL nodo){ // rotacion doble izq
        NodoAVL aux;
        nodo.setIzq(rotacionDer(nodo.getIzq()));
        aux = rotacionIzq(nodo);
        return aux;
    }
    
    public NodoAVL dobleDer(NodoAVL nodo){//Rotacion doble derecha
        NodoAVL aux;
        nodo.setDer(rotacionIzq(nodo.getDer()));
        aux = rotacionDer(nodo);
        return aux;
    }
}
