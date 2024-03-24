
package mainserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainServer {
    
    public static void main(String[] args) throws IOException {
        int porta = 12345;
        
        // creo il server
        Server server = new Server(porta);
        
        //avvio il server, in questo momento si mette in ascolto
        server.avvia();
                
        while(true){
            // quando trova un tentivo di connessione, la accetta ed avvia un thread che gestisce questa connessione
            server.accetta();
            
            // se ci sono più client connessi, ad ogni client verrà avviato un thread che si occuper di quella conversazione (multithreading)
            new Thread(() -> {
                while(true){
                    try {
                        // Esempio di utilizzo
                        BufferedReader input = server.leggi();
                        PrintWriter output = server.scrivi();

                        // leggo il messaggio del client
                        String messaggioDalClient = input.readLine();
                        
                        // se il messaggio finisce con "chiudi", termino la connessione ed invio il messaggio "Arrivederci!" al client
                        if(messaggioDalClient.endsWith("chiudi")){
                            output.println("Arrivederci!");
                            server.chiudi();
                            break;
                        }
                        // altrimenti stampo il messaggio che ho ricevuto
                        else{
                            output.println("Messaggio ricevuto dal server!");
                            System.out.println("Messaggio dal client: " + messaggioDalClient);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }).start();
        }
    }
}
