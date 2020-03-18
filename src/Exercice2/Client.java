package Exercice2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
// Q1 : Il n'accepte pas d'autres clients. Il ne tient pas compte des autres clients et ferment des que le 1er client "stop".

public class Client {
    public static void main(String[] args) throws Exception {
        Socket sock = new Socket(args[0],6020);

        BufferedReader ins = new BufferedReader(
                new InputStreamReader(sock.getInputStream()) );
        PrintWriter outs = new PrintWriter( new BufferedWriter(
                new OutputStreamWriter(sock.getOutputStream())), true);
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Veuillez saisir un message :");
            String message_to_send = scan.nextLine();
            outs.println(message_to_send);
            if (message_to_send.equals("stop"))
                break;
            String message_received = ins.readLine();
            System.out.println(message_received);
        }
        ins.close();
        outs.close();
        sock.close();
    }
}