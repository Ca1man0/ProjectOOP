// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (BUILDER PATTERN) - RIF CONCRETE BUILDER - TEEAMBUILDER.JAVA

package com.examaiman.builder_R;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * BUILDER PATTERN (CONCRETE BUILDER):
 * Qui ho l'implementazione concretta dell'interfaccia TeamBuilderInterface,
 */

/**
 * TECNOLOGIE USATE:
 * - LIST, ARRAY LIST - COLLECTION FRAMEWORK | GENERICS: 
 *   --> private List<ComponentePersonaleR> membri = new ArrayList<>(); --> --> dichiaro una lista che puo contenere solo oggetti di tipo ComponentePersonaleR
*       e inizializza un'istanza ArrayList (è implementazione dell'interfaccia List), lo si utilizza per ottimizzare gli accessi rapidi
 * 
 * OSS: Ho la flessibilità di cambiare la parte destra le codice, ad esempio usare i linkedlist. Come generic posso usare solo oggetti
 *      di ComponentePersonale
 */

public class TeamBuilderR implements TeamBuilderIntR {
    private static final Logger logger = Logger.getLogger(TeamBuilderR.class.getName());
    private String nomeTeam;
    private List<ComponentePersonaleR> membri = new ArrayList<>();

    // Inizializzo il nome della squadra come questo costruttore
    public TeamBuilderR(String nomeTeam) {
        this.nomeTeam = nomeTeam;
        logger.fine("Inizializzo il team che ha come nome: " + nomeTeam);

    }

    // Aggiungiamo l'annotazione @Override per chiarezza

    /**
     * Implemento il metodo aggiungiMembro dell'interfaccia TeamBuilderInt
     * dove aggiungi il membro alla lista
     * 
     * OSSERVAZIONE IMPORTANTE: qui faccio il return this chem i permette di concatenare
     * le mie chiamate ai metodi, quindi posso fare così --> .aggiungiMembro(X).aggiungiMembro(Y)
     * 
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public TeamBuilderIntR aggiungiMembro(ComponentePersonaleR membro) {
        this.membri.add(membro);
        logger.finer("Membro aggiunto al team: " + membro.getNome());
        return this;
    }

    /**
     * Qui implemento il metodo build richiesto dall'interfaccia TeamBuilderInt
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public TeamR build() {

        // Creo la mia istanza di Team in cui gli passo il nome
        // Vedi file Team
        TeamR team = new TeamR(nomeTeam);

        // Itero sulla mia lista dei membri per aggiungere alla squadra
        for (ComponentePersonaleR membro : membri) {
            team.aggiungiMembro(membro);
        }

        // Restituisco il mio team completo
        logger.info("La squadra" + nomeTeam + "è composta da " + membri.size() + " membri");
        return team;
    }
}