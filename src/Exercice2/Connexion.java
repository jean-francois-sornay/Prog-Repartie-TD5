package Exercice2;

import java.io.*;
import java.net.Socket;

public class Connexion implements Runnable {
    private Socket socket;
    private BufferedReader ins;
    private PrintWriter outs;

    public Connexion(Socket sock) throws IOException {
        this.socket = sock;
        ins = new BufferedReader(
                new InputStreamReader(this.socket.getInputStream()) );
        this.outs = new PrintWriter( new BufferedWriter(
                new OutputStreamWriter(this.socket.getOutputStream())), true);
    }

    @Override
    public void run() {
        while (true){
                try {
                    String message_received = ins.readLine();
                    if (message_received.equals("stop"))
                        break;
                    String message_to_send = "Reçu : " + message_received;
                    outs.println(message_to_send);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        try {
            ins.close();
            outs.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
