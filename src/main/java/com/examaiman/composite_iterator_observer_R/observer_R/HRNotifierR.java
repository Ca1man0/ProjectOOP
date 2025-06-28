// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 3 (OBSERVER PATTERN) - CONCRETE OBSERVER - HRNOTIFIER.JAVA

package com.examaiman.composite_iterator_observer_R.observer_R;

import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * OBSERVER PATTERNN (CONCRETE OBSERVER):
 * Qui implemento i metodi dell'interfaccia TeamObserver, quindi il metodo update
 * quindi fornisco un implementazione specificc
 */

 // Public accessibile da qualsiasi codice e Implements vuol dire che mi sto impegnando a fare quello che mi viene richiesto, quindi furnisco implementazione nell'interfaccia TeamObserver
public class HRNotifierR implements TeamObserverR {
    
    /**
     * L'implementazione è quello di stampare una specie di messaggio per indicare la variazione ai miei observer
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public void update(String azione, TeamR team, ComponentePersonaleR membro) {
        System.out.println(
            "Queste sono le informazioni legate alla modifica della squadra: il membro " + membro.getNome() +
            " e' stato " + azione + " al team di riferimento " + team.getNome() + "."
        );
    }
}