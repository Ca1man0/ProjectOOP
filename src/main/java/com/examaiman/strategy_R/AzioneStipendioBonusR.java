// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 1 (STRATEGY PATTERN) - RIF CONCRETE STRATEGIES P2-2 - AZIONESTIPENDIOBASE.JAVA

package com.examaiman.strategy_R;

/**
  * STRATEGY PATTERN (CONCRETE STRATEGIES):
  * Qui implemento l'interfaccia CalcoloStipendio, quindi modifico il metodo di calcolo
  */

// Public accessibile da qualsiasi codice e Implements vuol dire che mi sto impegnando a fare quello che mi viene richiesto, quindi furnisco implementazione del metodo CalcoloStipendio
public class AzioneStipendioBonusR implements CalcoloStipendioR {
    private double percentualeBonus;

    // Qui inizializzo il bonus che voglio dare, bisogna dichiarare il numero senza la percentuale
    public AzioneStipendioBonusR(double percentualeBonus) {
        this.percentualeBonus = percentualeBonus;
    }
    /**
     * Qui implemento il metodo di calcolo dello stipendio, incrementando lo stipendioBase con una percentuale
     * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
     */
    @Override
    public double calcola(double stipendioBase) {
        return stipendioBase * (1 + percentualeBonus / 100.0);
    }
}