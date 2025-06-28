// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 2 (OBSERVER PATTERN) - OBSERVER INTERFACE - TEAMOBSERVER.JAVA

package com.examaiman.composite_iterator_observer_R.observer_R;

import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * OBSERVER PATTERNN (OBSERVER INTERFACE):
 * Qui definisco l'interfaccia  per i miei observer,
 * quindi i metodi da implmentare
 */

public interface TeamObserverR {
    /**
     * Definisco il metodo di aggiornamento dove riporto l'azione che è stata effettuata (aggiunto o rimosso membro),
     * la squadra a cui ho fatto la modifica e il membro in cui ha subito l'azione di modifica
     */
    void update(String azione, TeamR team, ComponentePersonaleR membro);
}