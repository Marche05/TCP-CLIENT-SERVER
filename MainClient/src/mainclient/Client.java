package mainclient;

import java.io.*;
import java.net.Socket;

class Client {
    private String nome;
    private String colore;
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
    }

    public void connessione(String nomeServer, int portaServer) throws IOException {
        socket = new Socket(nomeServer, portaServer);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void leggi() throws IOException {
        String messaggioDalServer = input.readLine();
        System.out.println("Messaggio dal server: " + messaggioDalServer);
    }

    public void scrivi(String messaggio) {
        output.println(nome + ": " + messaggio);
    }

    public void chiudi() throws IOException {
        input.close();
        output.close();
        socket.close();
    }
}
