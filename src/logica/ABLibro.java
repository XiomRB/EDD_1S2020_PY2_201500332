package logica;

public class ABLibro {
    private NodoAB raiz;
    int maxhijos = 5;
    int maxclaves = maxhijos -1;
    int minclaves = (maxhijos +1)/2-1;
    int splitindex = (maxhijos-1)/2;

    public ABLibro() {
        raiz = null;
    }
    
    public void dibujar(){
        if(raiz!=null){
            raiz.recorrer();
        System.out.println("recorrido 2");
            raiz.recorrer2();
        }
    }
    
    public NodoAB buscar(int isbn){
        if(raiz == null)return null;
        return raiz.buscar(isbn);
    }
    
    public void insertar(int isbn, String tit, String autor, String editorial, int año, int edicion, String idioma, int prop){
        Libro nuevo = new Libro(isbn, tit, autor, editorial, año, edicion, idioma, prop);
        if(raiz == null){
            raiz = new NodoAB(true);
            raiz.claves[0] =nuevo;
        }else insertarNodo(raiz,nuevo);
    }

    public NodoAB dividirNodo(NodoAB nodo){
        NodoAB derecho = new NodoAB(true);
        derecho.clavesactuales = nodo.clavesactuales - splitindex -1;
        Libro padre = nodo.claves[splitindex];
        if(nodo.padre != null){
            NodoAB padreactual = nodo.padre;
            int ipadre;
            for (ipadre = 0; (ipadre < padreactual.clavesactuales + 1) && (padreactual.hijos[ipadre]!= nodo); ipadre++);
            for (int i = padreactual.clavesactuales; i > ipadre; i--) {
                padreactual.hijos[i+1] = padreactual.hijos[i];
                padreactual.claves[i] = padreactual.claves[i-1];
            }
            padreactual.clavesactuales++;
            padreactual.claves[ipadre] = padre;
            padreactual.hijos[ipadre+1] = derecho;
            derecho.padre = padreactual;
        }
        int i;
        for (i = splitindex+1; i < nodo.clavesactuales+1; i++) {
            derecho.hijos[i - splitindex-1] = nodo.hijos[i];
            if (nodo.hijos[i]!=null) {
                derecho.hoja = false;
                if (nodo.hijos[i]!= null) nodo.hijos[i].padre = derecho;
                nodo.hijos[i] = null;
            }
        }
        for (i = splitindex +1; i < nodo.clavesactuales; i++) {
            derecho.claves[i-splitindex-1] = nodo.claves[i];
        }
        NodoAB izquierdo = nodo;
        izquierdo.clavesactuales = splitindex;
        if (nodo.padre !=null) return nodo.padre;
        else{
            raiz = new NodoAB(false);
            raiz.claves[0] = padre;
            raiz.hijos[0] = izquierdo;
            raiz.hijos[1] = derecho;
            izquierdo.padre = raiz;
            derecho.padre = raiz;
            return raiz;
        }
    }
    
    public void insertarNodo(NodoAB root,Libro nuevo){
        if(root.hoja){
            root.clavesactuales++;
            int i = root.clavesactuales -1;
            while(i >0 && root.claves[i-1].getISBN() > nuevo.getISBN()){
                root.claves[i] = root.claves[i-1];
                i--;
            }
            root.claves[i] = nuevo;
            repararInsercion(root);
        }else{
            int i = 0;
            while(i < root.clavesactuales && root.claves[i].getISBN() < nuevo.getISBN()) i++;
            insertarNodo(root.hijos[i], nuevo);
        }
    }
    
    public void repararInsercion(NodoAB nodo){
        if(nodo.clavesactuales <= maxclaves) return;
        else if(nodo.padre == null){
            raiz = dividirNodo(nodo);
            return;
        }else{
            NodoAB nuevo = dividirNodo(nodo);
            repararInsercion(nuevo);
        }
    }
    
    public void eliminar(int isbn){
        eliminarNodo(raiz,isbn);
        if(raiz.clavesactuales == 0){
            raiz = raiz.hijos[0];
            raiz.padre = null;
        }
    }
    
    public void eliminarNodo(NodoAB root,int isbn){
        if (root != null) {
            int i;
            for (i = 0; (i < root.clavesactuales) && (root.claves[i].getISBN()<isbn); i++);
            if(i == root.clavesactuales){
                if(!root.hoja) eliminarNodo(root.hijos[root.clavesactuales], isbn);
            }else if(root.claves[i].getISBN() > isbn){
                if(!root.hoja) eliminarNodo(root.hijos[i], isbn);
            }else{
                if(root.hoja){
                    for (int j = i; j < root.clavesactuales - 1; j++) root.claves[j] = root.claves[j+1];
                    root.clavesactuales--;
                    balancear(root);
                }else{
                    NodoAB maximo = root.hijos[i];
                    while(!maximo.hoja){
                        maximo = maximo.hijos[maximo.clavesactuales];
                    }
                    root.claves[i] = maximo.claves[maximo.clavesactuales-1];
                    maximo.clavesactuales--;
                    balancear(maximo);
                }
            }
        }
    }
    
    public void balancear(NodoAB root){
        if (root.clavesactuales < minclaves) {
            if (root.padre == null) {
                if (root.clavesactuales == 0) {
                    raiz = root.hijos[0];
                    if (raiz != null) raiz.padre = null;
                }
            }else{
                NodoAB padre = root.padre;
                int ipadre;
                for (ipadre = 0; padre.hijos[ipadre]!= root; ipadre++);
                if (ipadre > 0 && padre.hijos[ipadre-1].clavesactuales > minclaves) {
                    pedirDeIzq(root,ipadre);
                }else if(ipadre < padre.clavesactuales && padre.hijos[ipadre + 1].clavesactuales > minclaves){
                    pedirDeDer(root,ipadre);
                }else if(ipadre == 0){
                    NodoAB siguiente = unir(root);
                    balancear(siguiente.padre);
                }else{
                    NodoAB siguiente = unir(padre.hijos[ipadre -1]);
                    balancear(siguiente.padre);
                }
            }
        }
    }
    
    private NodoAB pedirDeIzq(NodoAB root,int ipadre){
        NodoAB padre = root.padre;
        root.clavesactuales++;
        for (int i = root.clavesactuales - 1; i > 0; i--) root.claves[i] = root.claves[i-1];
        NodoAB izquierdo = padre.hijos[ipadre - 1];
        if (!root.hoja) {
            for (int i = root.clavesactuales; i >0; i--) root.hijos[i] = root.hijos[i-1];
            root.hijos[0] = izquierdo.hijos[izquierdo.clavesactuales];
            izquierdo.hijos[izquierdo.clavesactuales] = null;
            root.hijos[0].padre = root;
        }
        root.claves[0] = padre.claves[ipadre -1];
        padre.claves[ipadre -1] = izquierdo.claves[izquierdo.clavesactuales -1];
        izquierdo.clavesactuales--;
        return root;
    }
    
    private NodoAB pedirDeDer(NodoAB root,int ipadre){
        NodoAB padre = root.padre;
        NodoAB derecho = padre.hijos[ipadre +1];
        root.clavesactuales++;
        root.claves[root.clavesactuales -1] = padre.claves[ipadre];
        padre.claves[ipadre] = derecho.claves[0];
        if(!root.hoja){
            root.hijos[root.clavesactuales] = derecho.hijos[0];
            root.hijos[root.clavesactuales].padre = root;
            for (int i = 1; i < derecho.clavesactuales + 1; i++) derecho.hijos[i-1] = derecho.hijos[i];        
        }
        for (int i = 1; i < derecho.clavesactuales; i++) derecho.claves[i-1] = derecho.claves[i];
        derecho.clavesactuales--;
        return root;
    }
    
    private NodoAB unir(NodoAB root){
        NodoAB padre = root.padre;
        int ipadre = 0;
        for (ipadre = 0; padre.hijos[ipadre] != root; ipadre++);
        NodoAB derecho = padre.hijos[ipadre+1];
        root.claves[root.clavesactuales] = padre.claves[ipadre];
        for (int i = 0; i < derecho.clavesactuales; i++) root.claves[root.clavesactuales+1+i] = derecho.claves[i];
        if (!root.hoja) {
            for (int i = 0; i <= derecho.clavesactuales; i++) {
                root.hijos[root.clavesactuales+1+i] = derecho.hijos[i];
                root.hijos[root.clavesactuales+1+i].padre = root;
            }
        }
        for (int i = ipadre+1; i < padre.clavesactuales; i++) {
            padre.hijos[i] = padre.hijos[i+1];
            padre.claves[i-1] = padre.claves[i];
        }
        padre.clavesactuales--;
        root.clavesactuales = root.clavesactuales + derecho.clavesactuales +1;
        return root;
    }
    
    public NodoAB getRaiz() {
        return raiz;
    }
}