package ru.job4j.io.socketbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;
    private final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {

        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {

            String ask = null;
            do {
                System.out.println("Ожидаем команду клиента..");
                ask = in.readLine();
                if ("hello".equals(ask)) {
                    out.println("Hello, dear friend, I'm a oracle.");
                    out.println();
                } else if ("exit".equals(ask)) {
                    out.println("Пока.");
                } else {
                    System.out.println("Мы получили сообщение: " + ask);
                    System.out.println("Отправляем сообщение обратно.");
                    out.println(ask);
                }
            } while (!("exit".equals(ask)));

       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) {
        System.out.println("Сервер ждет подключения.");
        try (Socket socket = new ServerSocket(PORT).accept()) {
            System.out.println("Подключение состоялось.");
            Server server = new Server(socket);
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
