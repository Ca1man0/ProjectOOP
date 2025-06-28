// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 0 E 1 (ITERATOR PATTERN PATTERN) - RIF INTERFACE ITERATOR E CONCRETE ITERATOR - TEAMITERATOR.JAVA


package com.examaiman.composite_iterator_observer_R.iterator_R;

/**
 * ITERATOR PATTERN (INTERFACE ITERATOR):
 * Importo l'interfaccia Iterator standard di Java, in cui
 * ho già alcuni metodi definiti come hasNext, next e remove
 */
import java.util.Iterator;

import java.util.List;
import java.util.NoSuchElementException;

import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;

/**
 * ITERATOR PATTERN (CONCRETE ITERATOR):
 * Qui implemento l'interfaccia Iterator<ComponentePersonale>
 * quindi fornisco un modo per scorrere la collezione di una lista che
 * in questo caso sono i dipendenti prensenti nel team
 */

 /**
 * TECNOLOGIE USATE:
 *  - LIST - COLLECTION FRAMEWORK | GENERICS:
 *    --> private final List<ComponentePersonaleR> membri; --> dichiaro una lista che puo contenere solo oggetti di tipo ComponentePersonale
 *    --> public TeamIteratorR(List<ComponentePersonaleR> membri) --> lista che puo contenere solo oggetti di tipo ComponentePersonale
 */

// Public accessibile da qualsiasi codice e Implements vuol dire che mi sto impegnando a fare quello che mi viene richiesto, quindi furnisco implementazione nell'interfaccia Iterator<ComponentePersonale>
public class TeamIteratorR implements Iterator<ComponentePersonaleR> { 
    
    // Definizio la liste dei dipendenti o membri che andrò a scorrere
    private final List<ComponentePersonaleR> membri;
    
    // Definizione la variabile posizione per lo scorrimento
    private int posizione = 0;

    // Inizializzo i membri con questo costruttore
    public TeamIteratorR(List<ComponentePersonaleR> membri) {
        this.membri = membri;
    }

    /**
     * Implemento i metodi richiesti (interfaccia iterator):
     * - hasNext --> verifico se la posizione attuale è inferiore al numero degli elementi presenti nel team
     *   è un modo di controllare se devo scorrere o visitare un altro elemento o dipendente del team
     * - next() --> serve per scorrere la lista di membri, quindi restituisco il membro che ho visitao e vado avanti
     * 
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public boolean hasNext() {
        return posizione < membri.size();
    }
    @Override
    public ComponentePersonaleR next() {
        
        // Sfrutto il metodo in precedenza per verificare se ho altri dipendenti di scorrere o visitare
        if (!hasNext()) {
            throw new NoSuchElementException("Non ci sono più membri nel team, abbiamo visitato tutti gli elementi nella lista");
        }
        
        // Io so la posizione, quindi mi basta usare il metodo get sulla lista per ottenere il membro del team
        ComponentePersonaleR membro = membri.get(posizione);
        
        // Nel mentre incremento il contattore per andare nella posizione successiva
        posizione++;
        
        // Infine restituisco il membro nella posizione attuale
        return membro;
    }
}
