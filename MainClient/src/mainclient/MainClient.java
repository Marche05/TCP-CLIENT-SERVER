package mainclient;

import java.io.IOException;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) throws IOException {
        Scanner r = new Scanner(System.in);
        String nomeDefault = "Utente1";
        String coloreDefault = "Blu";
        Client client = new Client(nomeDefault, coloreDefault);
        client.connessione("localhost", 12345);
        
        while(true){
            try {
                // Esempio di utilizzo, prendendo input da tastiera il messaggio da inviare al server
                System.out.println("Inserire messaggio da inviare:");
                String messaggio = r.nextLine();
                
                // invio il messaggio al server
                client.scrivi(messaggio);
                
                // se ho inviato il messaggio di chiusura connessione
                if (messaggio.equals("chiudi")) {
                    // Leggo il messaggio di conferma di chiusura dal server
                    String rispostaDalServer = client.leggi();
                    
                    // se il server mi risponde "Arrivederci!", termino il processo
                    if (rispostaDalServer.equals("Arrivederci!")) {
                        break;
                    }
                }
            
                // leggo il messaggio del server in risposta
                client.leggi();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        client.chiudi();
    }
}
