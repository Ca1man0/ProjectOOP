// REVISIONATO (OKAY) - VERSIONE 2.0

// COMPOSITE, ITERATOR, OBSERVER E BUILDER PATTERN

// ORDINE 2 (COMPOSITE PATTERN) - RIF COMPOSITE - TEAM.JAVA
// ORDINE 3 e 4 (ITERATOR PATTERN) - RIF AGGREGATE E CONCRETE AGGREGATE - TEAM.JAVA
// ORDINE 1 (OBSERVER PATTERN) - RIF CONCRETE SUBJECT - TEAM.JAVA
// ORDINE 2 (BUILDER PATTERN) - RIF PRODUCT - TEAM.JAVA
// ORDINE 2 (MEMENTO PATTERN) - RIF ORIGINATOR - TEAM.JAVA

/**
 * SPIEGAZIONE CIASCUN PATTERN
 * 
 * 1) COMPOSITE PATTERN (COMPOSITE): rappresenta l'oggetto che può contenere LEAF (dipendente) o CONTAINER (team), estende ComponentePersonale e usa
 * metodi come aggiungiMembro e rimuoviMembro per gestire la composizioen della squadra
 * 
 * 2) ITERATOR PATTERN (CONCRETE AGGREGATE): implementa l'interfaccia iterable, quindi Iterator<ComponentePersonale> e fornisce l'implementazione concreta del 
 * metodo iterator() che ritorna un'instanza TeamIterator (passando la lista dei membri)
 * 
 * 3) OBSERVER PATTERN (CONCRETE SUBJECT): implementa l'interfaccia Subject, ovvero TeamObserverInt, quindi implementa i metodi registerObserver, unregisterObserver e notifyObservers
 * 
 * 4) BUILDER PATTERN (PRODUCT): la classe Team è il risultato della classe TeamBuilder in cui continee la logica per la costruzione della squadra, quindi è
 *    l'oggetto che creato nel processo di costruzione
 * 
 * 5) MEMENTO PATTERN (ORIGINATOR): implementa i metodi di salvataggio e ripristino dello stato
 */

 /**
  * TECNOLOGIE USATE:
  * - LIST, ARRAY LIST - COLLECTION FRAMEWORK | GENERICS: 
  *   --> private List<ComponentePersonaleR> membri --> dichiaro una lista che può contenere solo oggetti di tipo ComponentePersonale
  *   --> private List<TeamObserverR> observers = new ArrayList<>(); --> dichiaro una lista che puo contenere solo oggetti di tipo TeamObserver
  *       e inizializza un'istanza ArrayList (è implementazione dell'interfaccia List) e lo si utilizza per ottimizzare gli accessi rapidi
  *   --> this.membri = new ArrayList<>(); -->  creo una lista vuota che andrà a contenere i membri del team
  * 
  * - STREAM API E LAMBDA EXPRESSION:
  * --> return membri.stream() --> converto la lista membri in un flusso di dati dove posso fare una serie di operazioni concatenate
  * --> .mapToDouble --> converto ogni elemento del flusso in un valore di tipo double
  * --> sum --> sommo tutti i valori convertiti in double
  */
package com.examaiman.composite_iterator_observer_R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;
import com.examaiman.composite_iterator_observer_R.iterator_R.TeamIteratorR;
import com.examaiman.composite_iterator_observer_R.observer_R.TeamObserverIntR;
import com.examaiman.composite_iterator_observer_R.observer_R.TeamObserverR;
import com.examaiman.composite_iterator_observer_R.memento_R.TeamMemR;


// Extends vuol dire che sto ereditando i metodi da ComponentePersonale --> eredito i metodi della classe genitore
// Implements vuol dire che sto implementando l'interfaccia Iterable<ComponentePersonaleR> --> devi implementare tutti i metodi dell'interfaccia
public class TeamR extends ComponentePersonaleR implements Iterable<ComponentePersonaleR>, TeamObserverIntR {
    private static final Logger logger = Logger.getLogger(TeamR.class.getName());
    private String nomeTeam;
    private List<ComponentePersonaleR> membri;

    // RIFERIMENTO OBSERVER PATTERN (CONCRETE SUBJECT) P1-3: creo una lista dove ci metto chi ha diritto ad essere notificato al riguardo delle modifiche sul team
    private List<TeamObserverR> observers = new ArrayList<>();

    // BUILDER PATTENR (PRODUCT): Qui ho il mio costruttore che inizializza la squadra con il nome e i membri
    public TeamR(String nomeTeam) {
        this.nomeTeam = nomeTeam;
        this.membri = new ArrayList<>();
        logger.fine("Team creato con il nome: " + nomeTeam);

    }
    
    // RIFERIMENTO OBSERVER PATTERN (CONCRETE SUBJECT) P2-3: Qui implemento i metodi per aggiungere e rimuovere la persona presente nella lista degli observer
    @Override
    public void registerObserver(TeamObserverR observer) {
        observers.add(observer);
        logger.fine("Observer registrato: " + observer.getClass().getSimpleName());

    }

    @Override
    public void unregisterObserver(TeamObserverR observer) {
        observers.remove(observer);
        logger.fine("Observer rimosso: " + observer.getClass().getSimpleName());

    }

    // RIFERIMENTO OBSERVER PATTERN (CONCRETE SUBJECT) P3-3: Qui implemento il metodo per inviare la notifica agli observer, indicando l'azione, team e il membro
    @Override
    public void notifyObservers(String azione, ComponentePersonaleR membro) {
        for (TeamObserverR observer : observers) { // sto ciclando per ogni observer per inviare una notifica
            observer.update(azione, this, membro); // "this" indica l'istanza della classe Team su cui chiamo il metodo
            logger.log(Level.FINER, "Notifica in esecuzione per " + azione + " su membro " + membro.getNome() + " in riferimento al team " + nomeTeam);

        }
    }

    // RIFERIMENTO MEMENTO PATTERN (ORIGINATOR):
    // Metodo per salvare lo stato del memento
    public TeamMemR salvaStato() {
        logger.info("Salvataggio dello stato per il team: " + nomeTeam);
        return new TeamMemR(this.nomeTeam, new ArrayList<>(this.membri)); // Salvo lo stato indicando il nome e i membri del team
    }
    // Metodo per fare undo, quindi ritornare allo stato precedente
    public void ripristinaStato(TeamMemR memento) {
        if (memento != null) { // è presente lo stato?
            this.nomeTeam = memento.getNomeTeam();
            this.membri.clear(); // pulisco la lista
            this.membri.addAll(memento.getMembri()); // ci butto dentro la lista salvata nel memento
            System.out.println("è stato fatto undo allo stato del team '" + this.nomeTeam + " quindi ora è ripristinato");
            logger.info("è stato fatto undo allo stato del team '" + this.nomeTeam + " quindi ora è ripristinato");
        }else{
            logger.warning("Non è stato possibile ripristinare lo stato per il team " + nomeTeam + " per via dell'assenza del memento");
        }
    }

    /**
     * MIX PATTERN:
     *  - COMPOSITE: aggiungo o rimuove il membro al team esistente
     *  - OBSERVER: invio la notifica agli observer
     */
    public void aggiungiMembro(ComponentePersonaleR membro) {
        membri.add(membro);
        logger.fine("Membro " + membro.getNome() + " aggiunto al team " + nomeTeam);
        notifyObservers(" è stato aggiunto ", membro);
    }
    public void rimuoviMembro(ComponentePersonaleR membro) {
        membri.remove(membro);
        logger.fine("Membro " + membro.getNome() + " rimosso al team " + nomeTeam);
        notifyObservers(" è stato rimosso ", membro);
    }

    // COMPOSITE PATTERN (COMPOSITE): metodi getNome e getRuolo che ho specificato nella classe astratta ComponentePersonale per poi implementarli qui
    @Override
    public String getNome() {
        return nomeTeam;
    }
    @Override
    public String getRuolo() {
        return "Team";
    }


    // TECNOLOGIA JAVA STREAM: calcolo il costo del team per il ristorante
    @Override
    public double getStipendio() {
        /**
         * .stream --> prende la lista "membri" e lo converte in un flusso di elementi ComponentePersonaleR
         * .mapToDouble --> trasformo ogni oggetto del flusso in un valore di tipo double
         * .sum() --> sommo tutti i valori trasformati in dobule
         */
        double totale = membri.stream().mapToDouble(ComponentePersonaleR::getStipendio).sum();
        logger.finer("Calcolo stipendio totale per il team " + nomeTeam + ": " + totale);
        return totale;

    }
    

    // RIFERIMENTO ITERATOR PATTERN:
    /**
     * Questo metodo mi serve per scorrere i membri del team, quindi
     * restituisce un oggetto iterator, in questo caso ComponentePersonale in cui
     * sa come scorrere i membri di un team
     */
    @Override
    public Iterator<ComponentePersonaleR> iterator() {
        return new TeamIteratorR(this.membri); // creo un'istanza TeamIterator in cui passo la lista dei membri
    }
}