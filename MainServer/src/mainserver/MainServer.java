
package mainserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MainServer {
    public static void main(String[] args) {
        int porta = 12345;

        try {
            Server server = new Server(porta);
            server.avvia();

            // Esempio di utilizzo
            BufferedReader input = server.leggi();
            PrintWriter output = server.scrivi();

            String messaggioDalClient = input.readLine();
            System.out.println("Messaggio dal client: " + messaggioDalClient);

            output.println("Ciao, sono il server!");

            server.chiudi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
