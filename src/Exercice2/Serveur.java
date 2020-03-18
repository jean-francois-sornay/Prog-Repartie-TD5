package Exercice2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur {
    public static void main(String[] args) throws Exception {
        ExecutorService fixedPoolExecutors = Executors.newFixedThreadPool(4);
        ServerSocket s = new ServerSocket(6020);
        System.out.println("START");

        try {
            while (true){
                try {
                    Socket soc = s.accept();
                    Connexion c = new Connexion(soc);
                    fixedPoolExecutors.execute(c);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            try {
                fixedPoolExecutors.shutdown();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}