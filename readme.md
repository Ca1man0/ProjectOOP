# Nome del progetto: Restaurant Project

------------------------------------------------------------------------------------------------------

## Autore: Aiman Hamdouni
## Data completamento: 28/06/2025
## Breve descrizione: Gestionale per un ristorante utilizzando tecnologie e  alcuni pattern

------------------------------------------------------------------------------------------------------

## Panoramica e funzionalità dell'applicazione

In questo progetto ho provato a simulare un gestionale di un ristorante che va a coprire alcune funzionalità provando ad implementare 
alcuni pattern con una logica

* **Creazione di piatti**: Si possono creare piatti come Pasta e Carne, leggendo gli ingredienti presenti in un file di testo (`ingredienti_pasta.txt` e `ingredienti_carne.txt`). I piatti creati vengono salvati in un file di log (`lista_menu_preparati.txt`) simulando delle note che riceve lo chef in cucina prendendo l'ordine.
* **Gestione del personale**: Si possono gestire le brigate siamo come team e come singolo dipendente (come chef, cuochi, lavapiatti).
* **Calcolo degli stipendi**: Si possono gestire anche il bonus applicato al dipendente e calcolare  anche il costo totale di un intero team.
* **Gestione dei team**: Le squadre possono essere combinati per costruire una brigata completa, ad esempio Team Cucina e Team supporto vengono combinati per costruire la squadra per il turno.
* **Monitoraggio delle modifiche del team**: HR o Manager (Observers) possono ricevere le notifhce in caso di modifiche alla composizione di un team (aggiunta o rimozione di membri).
* **Annullamento delle modifiche**: In caso di errore nella costruzione del team è possibile applicare una specie di undo, per riportare indietro le modifiche allo stato precedente. 
## b. Tecnologie e pattern utilizzati, con giustificazione

------------------------------------------------------------------------------------------------------

### Tecnologie

All'interno di questo progetto sono stati utilizzate diverse tecnologie per differente esigenze, come ad esempio il testing, la costruzione di un team con la lista ecc.

* **Maven**: Usato come tool di build e gestione delle dipendenze del progetto.
* **JUnit**: Tecnologia usata per la creazione di testing che si può trovare all'interno dei file `FactoryTest.java` e `TeamTest.java`.
* **Java Collection Framework**: Vengono utilizzate interfacce come `List` e classi come `ArrayList` e `Stack` per la gestione di collezioni di oggetti, come gli ingredienti dei piatti o i membri di un team.
* **Java Stream API**: Utilizzata per operazioni funzionali sulle collezioni, come il calcolo della somma degli stipendi di un team in modo efficiente.
* **Java Logging API**: Per registrare gli eventi dell'applicazione, come la creazione di piatti e le modifiche ai team, in un file di log (`restaurant.log`).

------------------------------------------------------------------------------------------------------

### Design Pattern

* **Factory Pattern** (CREAZIONE PIATTI): Questo pattern mi è servito per la creazione dei piatti, quindi ho la creazione oggetto piatti in `PiattoR`, dopodichè ho usato le classi `PrimoFactoryR` e `SecondoFactoryR` creano rispettivamente oggetti `PastaR` e `CarneR`. Tutto ciò ci permette di disaccopiare le classi concrette dei piatti e quindi facilità l'aggiunta di altre tipologie di piatti.
* **Singleton Pattern** (GESTIONE PARAMETRO BONUS): Questo pattern lo ritroviamo in `ConfigurationManager` e serviva per avere una configurazione globale che esista in una sola istanza per tutta l'applicazione. La configurazione riguardava la percentuale di bonus da applicare sugli stipendi delle persone scelte.
* **Strategy Pattern** (SCELTA SE DARE O MENO IL BONUS): Questa tecnica mi serviva per definire una famiglia di algoritmi per il calcolo dello stipendio`CalcoloStipendioR`, per semplicità uno mantiene lo stipendio base, invece l'altro ci applica una percentuale di bonus. Queste strategie concrette, `AzioneStipendioBaseR` e `AzioneStipendioBonusR`, vengono iniettate in oggetto `DipendenteR` permettendo la modifica del modo in cui calcolo lo stipendio senza modificare la classe `DipendenteR`.
* **Composite Pattern** (ESEGUIRE OPERAZIONI COMUNI SUL SINGOLO DIPENDENTE O SUL TEAM): Mi ha permesso di creaare gerarchie di personale, un team può avere dipendenti o altri team (formando la cosidetta brigata) e di eseguire operazioni come `getStipendio()` su un'intera brigata.
* **Builder Pattern** (SEMPLFICAZIONE NELLA CREAZIONE DI TEAM COMPLESSI): Infatti la classe `TeamBuilderR` mi consente, passo dopo passo, di aggiungere i membri del team alla volta e poi dare conferma finale per ottenere `TeamR` completo.
* **Observer Pattern** (MONITORAGGIO SULLA MODIFICA DEL TEAM): Quando un membro del team viene modificato, `TeamR`, allora si andrà a notificare gli osservatori come`HRNotifierR`. Quindi mi permette di controllare le aggiunta e le rimozioni dei membri.
* **Memento Pattern** (SE SBAGLIO A CREARE LA SQUADRA? POSSO TORNARE INDIETRO): Questo pattern mi permette di salvare e ripristinare lo stato precedente di un oggettoo `TeamR`. La classe `HistoryTeam` salva gli stati `TeamMemR` e può ripristinarli quando necessario.
* **Exception Shielding Pattern** (): Questo pattern mi permette di nascondere le eccezioni tecniche e di mostrare quelle personalizzate o significative (`FactoryExceptionR`) al client.

------------------------------------------------------------------------------------------------------

## Istruzioni di setup ed esecuzione

1) Scarichi il progetto
2) Metti la cartella nel desktop
3) Apri Visual Studio e apri la cartella del progetto
4) Esegui questi comandi Maven nel codice sorgente:
4.1) Compili il progetto --> mvn compile
4.2) Esegui i test --> mvn test
4.3) Esegui l'applicazione --> mvn exec:java

Dopo l'esecuzione, verrà compila un file `restaurant.log` nella directory principale del progetto con i dettagli delle operazioni eseguite e un file `lista_menu_preparati.txt` con l'elenco dei piatti preparati.# ProjectOOP
