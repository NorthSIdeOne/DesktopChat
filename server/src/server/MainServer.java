package server;

import dto.Mesaj;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainServer {

    private static List<ClientThread> clients = new ArrayList<>();
    private static ReentrantReadWriteLock  lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(4545);

        while (true){
            Socket clientSocket = ss.accept();
            ClientThread clientThread = new ClientThread(clientSocket);
            clientThread.start();

            try {
                lock.writeLock().lock();
                clients.add(clientThread);
            }finally {
                lock.writeLock().unlock();
            }

        }

    }

    public static void trimiteMesajTuturor(Mesaj mesaj){
        try {
            lock.readLock().lock();

        clients.forEach( client -> {
            try {
                client.trimiteMesaj(mesaj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        }finally {
            lock.readLock().unlock();
        }
    }
}
