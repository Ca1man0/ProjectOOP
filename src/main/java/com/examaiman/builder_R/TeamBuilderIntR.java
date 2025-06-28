// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (BUILDER PATTERN) - RIF BUILDER INTERFACE - TEEAMBUILDERINT.JAVA

/**
 * BUILDER PATTERN (BUILDER INTERFACE):
 * Qui definisco i metodi che dovranno poi essere implementati nel builder concreto TeamBuilder
 * come l'agiungi membro e build che mi serviranno per costruire la mia squadra
 */

package com.examaiman.builder_R;
import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

public interface TeamBuilderIntR {

    // Aggiungo un membro al team
    TeamBuilderIntR aggiungiMembro(ComponentePersonaleR membro);

    // Costruisco e restituisco la squadra completa
    TeamR build();
}