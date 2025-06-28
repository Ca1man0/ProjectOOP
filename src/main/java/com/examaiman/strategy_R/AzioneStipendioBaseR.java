// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 1 (STRATEGY PATTERN) - RIF CONCRETE STRATEGIES P1-2 - AZIONESTIPENDIOBASE.JAVA

package com.examaiman.strategy_R;

/**
  * STRATEGY PATTERN (CONCRETE STRATEGIES):
  * Qui implemento l'interfaccia CalcoloStipendio, quindi modifico il metodo di calcolo
  */

// Public accessibile da qualsiasi codice e Implements vuol dire che mi sto impegnando a fare quello che mi viene richiesto, quindi furnisco implementazione del metodo CalcoloStipendio
public class AzioneStipendioBaseR implements CalcoloStipendioR {
    
    /**
     * In questo caso qui restituisco solo lo stipendioBase, quindi non 
     * viene effettuata nessuna azione
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public double calcola(double stipendioBase) {
        return stipendioBase;
    }
}
