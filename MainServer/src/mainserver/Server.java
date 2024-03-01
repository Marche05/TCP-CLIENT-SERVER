package mainserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }

    public void avvia() throws IOException {
        serverSocket = new ServerSocket(porta);
        System.out.println("Server avviato sulla porta " + porta);
        clientSocket = serverSocket.accept();
        System.out.println("Connessione accettata da: " + clientSocket.getInetAddress());
    }

    public BufferedReader leggi() throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public PrintWriter scrivi() throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void chiudi() throws IOException {
        clientSocket.close();
        serverSocket.close();
    }

    public void termina() throws IOException {
        chiudi();
        System.out.println("Server terminato");
    }
}
