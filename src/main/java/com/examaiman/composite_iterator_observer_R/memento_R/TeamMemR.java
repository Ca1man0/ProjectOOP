// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 0 (MEMENTO PATTERN) - RIF IL MEMENTO - TEAMMEM.JAVA

package com.examaiman.composite_iterator_observer_R.memento_R;

import java.util.ArrayList;
import java.util.List;

import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * MEMENTO PATTERN (MEMENTO):
 * Qui è presente lo stato dell'oggetto Originator in questo caso Team, così da ripristinarlo se  ci fosse
 *  la necessità.
 */
public class TeamMemR {
    private final String nomeTeam;
    private final List<ComponentePersonaleR> membri;


    // Qui ho il costruttore che iniziliazza le variabili per poi avere lo stato (nome team e membri del team) salvato come copia
    public TeamMemR(String nomeTeam, List<ComponentePersonaleR> membri) {
        this.nomeTeam = nomeTeam;
        this.membri = new ArrayList<>(membri);
    }


    // Qui dichiaro i metodi pubblici per la restituzione delle variabili salvate, ovvero nome del team e i membri
    public String getNomeTeam() {
        return nomeTeam;
    }
    public List<ComponentePersonaleR> getMembri() {
        return new ArrayList<>(membri); // Restituisco una copia per sicurezza
    }
}