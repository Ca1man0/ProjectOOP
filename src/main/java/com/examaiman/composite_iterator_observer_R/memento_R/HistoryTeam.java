// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 1 (MEMENTO PATTERN) - RIF IL CARETAKER - STORICOTEAM.JAVA

package com.examaiman.composite_iterator_observer_R.memento_R;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * MEMENTO PATTERN (CARETAKER):
 * Questo si occupa solo di conservera i cosidetti mementi o gli stati, quindi richiede all'originator e gli restituisce
 * quando è richiesto
 */

 /**
  *  TECNOLOGIE USATE:
  *  - LIST, ARRAY LIST - COLLECTION FRAMEWORK | GENERICS: 
  *    --> private Stack<TeamMemR> cronologia = new Stack<>(); --> inizializzo uno stack che puo contenere solo oggetti stack di tipo TeamMem
  */
public class HistoryTeam {
    private static final Logger logger = Logger.getLogger(HistoryTeam.class.getName());
    private final Stack<TeamMemR> cronologia = new Stack<>(); // La logica è stack quindi LIFO, l'ultima ad entrare e il primo ad uscire

    // Questo metodo fai il push o aggiunge allo stack lo stato del originator
    public void salvaStato(TeamMemR memento) {
        cronologia.push(memento);
        logger.info("Nuovo stato salvato nello storico per il team: " + memento.getNomeTeam());

    }

    // Questo metodo permette di recupare il suo ultimo stato, facendo poi la sua eliminazione con pop visto che l'ho ripristinaot
    public TeamMemR annulla() {
        if (!cronologia.isEmpty()) {
            TeamMemR memento = cronologia.pop();
            logger.info("Ripristino all'ultimo statodel team " + memento.getNomeTeam());
            return memento;
        }
        logger.warning("Nessuno stato precedente da ripristinare.");
        return null;
    }
}