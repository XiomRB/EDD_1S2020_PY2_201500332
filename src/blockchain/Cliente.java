package blockchain;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {
    private int puerto;
    private String msj;

    public Cliente(int puerto, String msj) {
        this.puerto = puerto;
        this.msj = msj;
    }
    
    @Override
    public void run() {
       final String host = "127.0.0.1";
        
        DataOutputStream out;
        try {
            Socket sc = new Socket(host, puerto);
            out = new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF(msj);
            sc.close();
        } catch (IOException ex) {}
    }
}
