// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 0 (OBSERVER PATTERN) - RIF SUBJECT INTERFACE - TEAM.JAVA

package com.examaiman.composite_iterator_observer_R.observer_R;

import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * OBSERVER PATTERN (SUBJECT INTERFACE):
 * Definisco i metodi che dovranno essere implementati nel Team (cocnrete subject), quindi
 * viene definito il contratto per gli oggetti che possono essere osservati
 */

public interface TeamObserverIntR {
    //Aggiung un observer che vuole essere notificato
    void registerObserver(TeamObserverR observer);

    //Rimuovo un observer che vuole silenziare le notifiche
    void unregisterObserver(TeamObserverR observer);

    // Invio la notifica agli observer registrati
    void notifyObservers(String azione, ComponentePersonaleR membro);
}