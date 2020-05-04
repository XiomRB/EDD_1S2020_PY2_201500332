package blockchain;

import java.io.UnsupportedEncodingException;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jdk.nashorn.internal.ir.Block;

public class Bloque {
    private int index;
    private String timestamp;
    private int nonce;
    private String data;
    private String previushash;
    private String hash;
    private Bloque anterior;
    private Bloque siguiente;

    public Bloque(int index, String timestamp, String data, String previushash) {
        this.index = index;
        this.timestamp = timestamp;
        this.nonce = 0;
        this.data = data;
        this.previushash = previushash;
        this.hash = calcularHash();
        this.anterior = this.siguiente = null;
    }

    public Bloque(int index, String timestamp, int nonce, String data, String previushash, String hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.data = data;
        this.previushash = previushash;
        this.hash = hash;
        this.anterior = this.siguiente = null;
    }
    
    
    
    public String calcularHash() {
        String dataToHash = this.previushash + timestamp + Integer.toString(nonce) + data;
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes(UTF_8));
        } catch (NoSuchAlgorithmException ex) {}
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) buffer.append(String.format("%02x", b));
        return buffer.toString();
    }
    
    public String minarBloque(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '0');
        while (!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calcularHash();
        }
        return hash;
    }    

    public String dibujar(){
        return "\"Indice: " + index + "\\nFecha: " + timestamp + "\\nNonce: " + nonce + "\\nPreviusHash: " + previushash + "\\nHash: " + this.hash + "\"";
    }
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviushash() {
        return previushash;
    }

    public void setPreviushash(String previushash) {
        this.previushash = previushash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Bloque getAnterior() {
        return anterior;
    }

    public void setAnterior(Bloque anterior) {
        this.anterior = anterior;
    }

    public Bloque getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Bloque siguiente) {
        this.siguiente = siguiente;
    }
}
