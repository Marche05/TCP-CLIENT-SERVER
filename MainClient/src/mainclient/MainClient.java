package mainclient;

import java.io.IOException;

public class MainClient {
    public static void main(String[] args) {
        String nomeDefault = "Utente";
        String coloreDefault = "Blu";

        try {
            Client client = new Client(nomeDefault, coloreDefault);
            client.connessione("localhost", 12345);

            // Esempio di utilizzo
            client.scrivi("Ciao, sono il client!");
            client.leggi();

            client.chiudi();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
