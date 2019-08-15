package ru.job4j.io.socketbot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private static final int PORT = 5000;
    private static final String IP = "127.0.0.1";
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void start() throws IOException {
        try (PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                Scanner console = new Scanner(System.in);
                String request = null;
                String response = null;
                System.out.println("Введите фразу для передачи серверу: ");
                while (!("exit".equals(request))) {
                    request = console.nextLine();
                    out.println(request);
                    response = in.readLine();
                    System.out.println("Сервер ответил: " + response);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName(IP), PORT)) {
            System.out.println("Подключаемся к серверу: " + IP + ":" + PORT);
            Client client = new Client(socket);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
