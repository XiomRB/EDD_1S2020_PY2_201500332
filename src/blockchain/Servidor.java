package blockchain;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;

public class Servidor extends Observable implements Runnable {
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }
    
    @Override
    public void run() {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado");
            while (true) {
                sc = servidor.accept(); //devuelve el socket del cliente

                in = new DataInputStream(sc.getInputStream());
                
                String msj = in.readUTF();
                System.out.println(msj);
                this.setChanged();
                this.notifyObservers(msj);
                this.clearChanged();
                sc.close();
                System.out.println("Cliente desconectado");
            }
        } catch (IOException ex) {
        }
    }
}
