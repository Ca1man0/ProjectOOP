package com.examaiman;

import com.examaiman.builder_R.TeamBuilderR;
import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;
import com.examaiman.composite_iterator_observer_R.composite_R.DipendenteR;
import com.examaiman.composite_iterator_observer_R.observer_R.HRNotifierR;
import com.examaiman.composite_iterator_observer_R.observer_R.TeamObserverR;
import com.examaiman.factory_exception_R.FactoryExceptionR;
import com.examaiman.factory_exception_R.concrete_abastract_product.PiattoR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.PiattoFactoryR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.PrimoFactoryR;
import com.examaiman.factory_exception_R.concrete_abstract_factory.SecondoFactoryR;
import com.examaiman.singleton_R.ConfigurationManager;
import com.examaiman.strategy_R.AzioneStipendioBaseR;
import com.examaiman.strategy_R.AzioneStipendioBonusR;
import com.examaiman.strategy_R.CalcoloStipendioR;
import com.examaiman.composite_iterator_observer_R.memento_R.HistoryTeam;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.*;

public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    // Qui dentro ho una specie di cronologia dei menu che sono stati prodotti in quella serata o giornata
    private static final String FILE_OUTPUT = "lista_menu_preparati.txt";

    public static void main(String[] args) {


        // CONFIGURAZIONE DEL MIO LOGGING
        // Configurazione console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new SimpleFormatter()); // imposto formato semplice
        consoleHandler.setLevel(Level.ALL); // Imposto il livello per la console, ALL cosi prendo tutti i gradi di livello di messaggio
        logger.addHandler(consoleHandler); // Aggiungo il console handler al logger
        // Configurazione file handler
        try {
            FileHandler fileHandler = new FileHandler("restaurant.log");
            fileHandler.setFormatter(new SimpleFormatter()); // imposto formato semplice
            fileHandler.setLevel(Level.ALL); // Imposto il livello per la console, ALL cosi prendo tutti i gradi di livello di messaggio
            logger.addHandler(fileHandler); // Aggiungo il file handler al logger
        } catch (IOException e) {
            logger.log(Level.SEVERE, "C'è un problema con il file", e);
        }

        logger.setLevel(Level.ALL); 

        /**
         * FACTORY PATTERN --> PRODUZIONE PIATTI PIATTI (PRIMO E SEOCNDO)
         */
        // System.out.println("RIFERIMENTO FACTORY PATTERN: PRODUZIONE PIATTI PIATTI (PRIMO E SECONDO)");
        logger.info("RIFERIMENTO FACTORY PATTERN: PRODUZIONE PIATTI PIATTI (PRIMO E SECONDO) \n");

        PiattoFactoryR primoFactory = new PrimoFactoryR();
        PiattoFactoryR secondoFactory = new SecondoFactoryR();
        try {
            // System.out.println("\n IN FASE CREAZIONE PIATTO GUSTOSO");
            logger.info("RIFERIMENTO AL FACTORY PATTERN: IN FASE CREAZIONE PIATTO GUSTOSO \n");

            // CREO I MIEI PIATTI DI TIPO PASTA
            List<String> ingredientiPasta = leggiIngredientiDaFile("ingredienti_pasta.txt");
            PiattoR pasta = primoFactory.creaPiatto("pasta", ingredientiPasta);
            pasta.prepara();
            //Riporto gli ingredienti per la preparazione del piatto e come un foglietto che lo vede il chef durante la preparazione
            scriviPiattoSuFile(pasta, FILE_OUTPUT); 

            // CREO I MIEI PIATTI DI TIPO CARNE
            List<String> ingredientiCarne = leggiIngredientiDaFile("ingredienti_carne.txt");
            PiattoR carne = secondoFactory.creaPiatto("carne", ingredientiCarne);
            carne.prepara();
            //Riporto gli ingredienti per la preparazione del piatto e come un foglietto che lo vede il chef durante la preparazione
            scriviPiattoSuFile(carne, FILE_OUTPUT);

        } catch (FactoryExceptionR e) {
            // Qui dopo aver catturato l'errore, lo stampo per mostrarlo
            // System.err.println("Ops c'è un errore nella creazione del piatto" + e.getMessage());
            logger.log(Level.SEVERE, "Errore nella factory, problema con la creazione piatto. ", e);
        }




        /**
         * MIX PATTERN: SINGLETON, STRATEGY, COMPOSITE
         * - SINGLETON --> Per recupare il valore che ha il manager sui bonus
         * - STRATEGY --> Scelgo come calcolare lo stipendio, ovvero se mantenerlo come base o aumentarlo con una percentuale
         * - COMPOSITE --> Creo i miei dipendenti
         */
        // System.out.println("\n RIFERIMENTO SINGLETON, STRATEGY E COMPOSITE");
        logger.info("\n RIFERIMENTO SINGLETON, STRATEGY E COMPOSITE \n");

        // SINGLETON PATTERN (raccolta del dato bonus)
        // System.out.println("\n RIFERIMENTO AL SINGLETON PATTERN: IN FASE RACCOLTA DEL DATO BONUS");
        logger.info("\n RIFERIMENTO AL SINGLETON PATTERN: IN FASE RACCOLTA DEL DATO BONUS\n");
        // Recupero l'istanza e prendi il valore del bonus
        double bonusPercentuale = ConfigurationManager.getInstance().getDefaultBonusPercentage();
        // System.out.println("Percentuale bonus applicata: " + bonusPercentuale + "%");
        logger.info("\n Percentuale bonus applicata: " + bonusPercentuale + "% \n");

        // STRATEGY PATTERN (creo le mie due strategie, ovvero decisione su come calcolo lo stipendio)
        // System.out.println("\n RIFERIMENTO AL STRATEGY PATTERN: CREAZIONE STRATEGIA SE DARE O MENO IL BONUS AL DIPENDENTE");
        logger.info("\n RIFERIMENTO AL STRATEGY PATTERN: CREAZIONE STRATEGIA SE DARE O MENO IL BONUS AL DIPENDENTE \n");
        CalcoloStipendioR stipendioStandard = new AzioneStipendioBaseR();
        CalcoloStipendioR stipendioConBonus = new AzioneStipendioBonusR(bonusPercentuale);

        // COMPOSITE PATTERN (creazione dei dipendenti) E STRATEGY PATTERN (injection, ovvero  è qui che inietto e deciso se dare il bonus)
        // System.out.println("\n RIFERIMENTO AL COMPOSITE PATTERN: CREAZIONE DEI DIPENDENTI | RIFERIMENTO AL STRATEGY PATTERN: INJECTION DELLA STRATEGIA");
        logger.info("\n RIFERIMENTO AL COMPOSITE PATTERN: CREAZIONE DEI DIPENDENTI | RIFERIMENTO AL STRATEGY PATTERN: INJECTION DELLA STRATEGIA \n");
        ComponentePersonaleR chef = new DipendenteR("Ahmed", "Chef", 3200, stipendioConBonus); // SI BONUS
        ComponentePersonaleR cuoco1 = new DipendenteR("GinoGino", "Cuoco", 2100, stipendioStandard); // NO BONUS
        ComponentePersonaleR cuoco2 = new DipendenteR("Calcutta", "Cuoco", 2200, stipendioStandard); // NO BONUS
        ComponentePersonaleR lavapiatti = new DipendenteR("Morandi", "Lavapiatti", 1500, stipendioStandard); // NO BONUS



        
        /**
         * MIX PATTERN: BUILDER E OBSERVER
         * - BUILDER -->  Semplficia la creazione di oggetti complessi, in questo caso team con molti dipendenti
         * - OBSERVER --> Notifico i cambiamenti nel team ad un observer in questo caso l'HR
         */
        //Creo il mio observer
        TeamObserverR hr = new HRNotifierR();
        logger.info("\n Creazione observer per controllare i cambiamenti nel team \n");

        // Creazione primo team --> supporto in cucina
        TeamR teamCucina = new TeamBuilderR(" Team cucina (Chef e Cuoco 1) ")
            .aggiungiMembro(chef)
            .aggiungiMembro(cuoco1)
            .build(); // finalizzo la creazione quando ho finito
        teamCucina.registerObserver(hr); // registro il mio observer nella lista di quelli che saranno notificati
        logger.info("\n Team cucina creato con il Builder e Observer registrato \n");

        // Creazione secondo team --> supporto alla plunge
        TeamR teamSupporto = new TeamBuilderR(" Team supporto (lavapiatti) ")
            .aggiungiMembro(lavapiatti)
            .build(); // finalizzo la creazione quando ho finito
        logger.info("\n Team supporto creato con il Builder \n");

        // Unisco i due team per creare il team finale ovvero la mia brigata
        TeamR brigata = new TeamBuilderR(" Brigata completa ")
            .aggiungiMembro(teamCucina)
            .aggiungiMembro(teamSupporto)
            .build(); // finalizzo la creazione quando ho finito
        logger.info("\n Team composito è completo ora \n");

        // Adesso simulo il funzioanmento del mio observer
        // System.out.println("\n RIFERIMENTO AL OBSERVER PATTERN:SIMULAZIONE DEL MIO OBSERVER HR");
        logger.info("\n RIFERIMENTO AL OBSERVER PATTERN:SIMULAZIONE DEL MIO OBSERVER HR \n");

        // System.out.println("Aggiungo un nuovo cuoco, ovvero cuoco2");
        logger.info("\n AAggiungo un nuovo cuoco, ovvero cuoco2 \n");
        teamCucina.aggiungiMembro(cuoco2);

        // System.out.println("\n Rimuovo il mio cuoco, ovvero cuoco1");
        logger.info("\n Rimuovo il mio cuoco, ovvero cuoco1 \n");
        teamCucina.rimuoviMembro(cuoco1);





        // MEMENTO PATTERN (per tornare indientro quando si è sbagliato la composizione del team)
        // System.out.println("\n RIFERIMENTO AL MEMENTO PATTERN: IN CASO DI ERRORE");
        logger.info("\n RIFERIMENTO AL MEMENTO PATTERN: IN CASO DI ERRORE \n");

        // Creo il mio caretake, colui che dovrà tenere la cronologia del mio team
        HistoryTeam storicoTeam = new HistoryTeam();

        // Mostro lo stato attuale del mio team
        // System.out.println("\n Stato AS IS del mio team");
        logger.info("\n Stato AS IS del mio team \n");
        stampaTeam(brigata, 0);

        // Salvo lo stato corrente del team
        storicoTeam.salvaStato(brigata.salvaStato());
        // System.out.println("\n Ho salvato la composizione del team come richiesto");
        logger.info("\n Ho salvato la composizione del team come richiesto \n");

        // Simulo la modifica per errore aggiungendo il cameriere Farshad
        ComponentePersonaleR cameriere = new DipendenteR("Farshad", "Cameriere", 1800, stipendioStandard);
        brigata.aggiungiMembro(cameriere);

        // Mostro lo stato attuale del mio team dopo il cambiamento
        // System.out.println("\n Stato TO BE del mio team");
        logger.info("\n Stato TO BE del mio team \n");
        stampaTeam(brigata, 0);

        // Ritorno al suo stato precedente, ovvero quello AS IS
        // .out.println("\n Ripristino lo stato precedente del team");
        logger.info("\n Ripristino lo stato precedente del team \n");
        brigata.ripristinaStato(storicoTeam.annulla());
        
        // Mostro lo stato del team dopo il ripristino
        // System.out.println("\n Stato del Team dopo undo:");
        logger.info("\n Stato del Team dopo undo \n");
        stampaTeam(brigata, 0);




        // RECAP DEL TEAM
        // System.out.println("\n RECAP DELLA STIAZIONE");
        logger.info("\n RECAP DELLA STIAZIONE \n");

        // System.out.println("\n Stampo ogni per sona del team");
        logger.info("\n Stampo ogni persona del team, itero sulla brigata \n");
        stampaTeam(brigata, 0);

        // System.out.println("Costo totale della brigata" + brigata.getStipendio() + " euro");
        logger.info("\n Costo totale della brigata " + brigata.getStipendio() + " euro \n");
    }




    /**
     * Qui stampo il team (componente) in modo ricorsivo, il livello mi serve
     * per gestire l'indentazione in modo da creare uno schema gerarchico.
     * componente è inteso come team o singolo dipendente
     */
    private static void stampaTeam(ComponentePersonaleR componente, int livello) {
        // Creo una stringa di spazi per l'indentazione, conferendo una struttura gerachica
        String indent = "  ".repeat(livello);

        // System.out.println(indent + "- " + componente.getNome() + " (" + componente.getRuolo() + "): " + componente.getStipendio() + " euro");
        logger.log(Level.FINE, indent + "- " + componente.getNome() + " (" + componente.getRuolo() + "): " + componente.getStipendio() + " euro"); //Definisco fine per log (dettaglio che posso isolarlo)

        // Se il componente personale rappresenta un team, lo stampo ricorsivamente
        // quindi se il componente esiste ed è il tipo Team R allora True e poi ciclo
        if (componente instanceof TeamR team) {
            for (ComponentePersonaleR membro : team) {
                // Eseguo l'idnetazione
                stampaTeam(membro, livello + 1);
            }
        }
    }




    /**
     * Qui leggo l'elenco degli ingredienti da un file txt
     */
    private static List<String> leggiIngredientiDaFile(String nomeFile) {
        logger.info("\n Avvio lettura del file di risorse: " + nomeFile + "\n");
        //System.out.println("Avvio lettura del file di risorse: " + nomeFile);

        /**
         * QUI STO APRENDO LA MIA RISORSA (TRY MI PERMETTE DI GESTIRE LA CHIUSURA AUTOMATICA, EVITANDO RISCHIO DI CONSUMO RISORSE)
         * InputStream --> è il mio oggetto file che sto creando
         * App.class.getClassLoader().getResourceAsStream(nomeFile):
         *   App.class --> in riferimento alla classe App
         *  .getClassLoader() --> prendi il riferimento alla classe che mi ha permesso di caricare le risorse
         *  .getResourceAsStream(path) --> prendi la risorsa che ti sto specificando
         */
        try (InputStream flussoDatiProcessatiLettura = App.class.getClassLoader().getResourceAsStream(nomeFile)) {

            // È fondamentale verificare se lo stream è stato aperto correttamente.
            // Se la risorsa non viene trovata, getResourceAsStream restituisce null.
            //VERIFICA ERRORE: SE IL FILE è NULL ALLORA ERRORE PERCHè NON L'HO TROVATO
            if (flussoDatiProcessatiLettura == null) {
                throw new IOException("\n La risorsa non è stata trovata: " + nomeFile + "\n");
            }

            /**
             * flussoDatiProcessati --> qui dentro ho i miei dati della lista ingredienti (come flusso di byte)
             * new InputStreamReader(flussoDatiProcessati) --> converto il flusso di byte in un flusso di caratteri
             * new BufferedReader () --> converto il flusso di caratteri in un flusso di linee, per velocizzare la lettura
             * BufferedReader --> accumula i dati in una memoria temporanea buffer
             */
            BufferedReader bufferRigheLettura = new BufferedReader(new InputStreamReader(flussoDatiProcessatiLettura));
            
            // Mi serve questo array per il ritorno delle righe in output
            List<String> righeLette = new ArrayList<>();

            /**
             * reader.readLine() --> legge la riga che è vista come flusso di caratteri e restituisce come stringa
             * leggo questi flow di righe e lo aggiungo al array
             */
            String rigaCorrente;
            while ((rigaCorrente = bufferRigheLettura.readLine()) != null) {
                rigaCorrente = rigaCorrente.trim(); // Esempio di sanificazione di dati in cui mi sono assicurato che non abbia spazi ulteriori
                righeLette.add(rigaCorrente);
            }
            logger.info("\n Lettura delle righe è completata \n");
            //System.out.println("Lettura delle righe è completata");
            
            return righeLette;

        } catch (IOException e) {
            // QUI HO INTERCETTATO L'ERRORE, INFATTI STAMPIAMO IL MESSAGGIO
            System.err.println("\n Errore durante la lettura della risorsa: " + nomeFile + "; errore specifico: " + e.getMessage() + "\n");

            // Ritorno una lista vuota piuttosto che fa crashare il programma
            return Collections.emptyList();
        }
    }

private static void scriviPiattoSuFile(PiattoR piatto, String nomeFile) {
    //Costruisco la mia stringa di testo da inserire poi nel file
    String testo = "Piatto: " + piatto.getNome() + ", Ingredienti: " + piatto.getIngredienti() + "\n";

    /**
     * TRY CATCH per garantire la chisura automatica delle risrose 
     * FileWriter --> è character stream usato per scrivere testo su un file, si indica il path e se voglio append alla fine
     * new BufferWriter () --> converto il flusso di caratteri in un flusso di linee, per velocizzare la scrittura
     * flussoDatiProcessatiScrittura --> qui dentro ho i miei dati
     * BufferedWriter --> accumula i dati in una memoria temporanea buffer
     */
    try (BufferedWriter bufferRigheScrittura = new BufferedWriter(new FileWriter(nomeFile, true))) {
        
        bufferRigheScrittura.write(testo);
        
        //System.out.println("Il piatto " + piatto.getNome() + "è stato scritto correttamente" + nomeFile);
        logger.info("\n Il piatto " + piatto.getNome() + " è stato scritto correttamente " + nomeFile + "\n");

    } catch (IOException e) {
        // QUI HO INTERCETTO L'ERRORE, INFATTI STAMPIAMO IL MESSAGGIO
        //System.err.println("Errore durante la scrittura: " + nomeFile + "; errore specifico: " + e.getMessage());
        logger.log(Level.WARNING, "\n Errore durante la scrittura: " + nomeFile + "; errore specifico: " + e.getMessage() + "\n");
    }
}
}