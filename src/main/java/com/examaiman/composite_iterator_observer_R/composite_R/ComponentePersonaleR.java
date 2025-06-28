// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 0 (COMPOSITE PATTERN) - RIF COMPONENT - COMPONENTEPERSONALE.JAVA

package com.examaiman.composite_iterator_observer_R.composite_R;

/**
 * COMPOSITE PATTERN (COMPONENT):
 * Questa classe astratta rappresenta l'interfaccia comune per le mie sottoclassi nella gerarchia,
 * quindi sia per gli oggetti singoli (Leaf, ad esempio Dipendente) che per i contenitori (Composite, ad esempio Team)
 * 
 * In sintesi dichiara i metodi che dovranno essere implementati dalle sottoclassi,
 * quindi dipendente e team. 
 */

// Public accessibile da qualsiasi codice e Abstract perchè sarà un riferimento alla sottoclassi (PrimoFactory e SecondoFactory)
public abstract class ComponentePersonaleR {

    // Restituisce il nome del team o dipendente
    public abstract String getNome();
    // Restituisce il ruolo del team o dipendente
    public abstract String getRuolo();
    // Restituisce lo stipendio del singolo dipendente o di tutto il team
    public abstract double getStipendio();
}
