// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (COMPOSITE PATTERN) - RIF LEAF O FOGLIA - DIPENDENTE.JAVA
// ORDINE 2 (STRATEGY PATTERN) - RIF CONTEXT CLASS - DIPENDENTE.JAVA

/**
 * COMPOSITE PATTERN (LEAF):
 * Questa rappresenta la foglia della mia gerarchia, quindi non può avere altri componenti,
 * inoltre fornisce l'implementazione concretta ai metodi del CompoenntePersonale (component),
 * quindi getNome, getRuolo e getStipendio.
 * In questo caso leaf si riferisce al dipendente del mio ristorante.
 */

 /**
  * STRATEGY PATTERN (CONTEXT CLASS):
  * Qui viene utilizzato una strategia per il calcolo dello stipendio, quindi abbiamo
  * il riferimento "CalcoloStipendio" a cui delego il calcolo dello stipendio. Quindi
  * la strategia viene iniettata dal costruttore Dipedendente.
  */

/**
 * TECNOLOGIE USATE:
 *  - Inversion control --> la scelta dell'algoritmo di calcolo dello stipendio non è
 *    responsabilità della classe Dipendente, ma viene delegato al App (componente esterno) che
 *    inietta la strategia desiderata
 */

package com.examaiman.composite_iterator_observer_R.composite_R;
import com.examaiman.strategy_R.CalcoloStipendioR;
import java.util.logging.Logger;
import java.util.logging.Level;
/*
 * Questa è come una classe concreta che implementa i metodi che ho specificato
 * nel ComponentePersonale in cui avevo la classe astratta
 */

// Public accessibile da qualsiasi codice e Extends vuol dire che sto ereditando i metodi da ComponentePersonale per poi implementarli
public class DipendenteR extends ComponentePersonaleR {
    private static final Logger logger = Logger.getLogger(DipendenteR.class.getName());
    
    private String nome;
    private String ruolo;
    private double stipendioBase;
    
    /**
     * RIF STRATEGY PATTERN (CONTEXT CLASS) P1-2:
     * Mi riferisco all'intefaccia Strategy, quindi CalcoloStipendio per cacolare il mio stipendio calcolato
     * Inversion control --> qui avviene anche il cosidetto iniezione della strategia da App
     */
    private CalcoloStipendioR calcoloStrategy;
    
    // Inizializzo il mio dipendente con questo costruttore, infatti dovrà avere un nome, ruolo, stipendio base e come deve essere calcolato lo stipendio base
    public DipendenteR(String nome, String ruolo, double stipendioBase, CalcoloStipendioR strategy) {
        this.nome = nome;
        this.ruolo = ruolo;
        this.stipendioBase = stipendioBase;
        this.calcoloStrategy = strategy;
        logger.log(Level.FINE, "è stato creato il dipendent " +nome+ " con lo stipendio di " +stipendioBase+ " per il ruolo di " +ruolo);

    }

     /**
      * RIF COMPOSITE PATTERN (LEAF):
      * Qui implemento i metodi che ho specificato nella classe astratta ComponentePersonale,
      * quindi getNome, getRuolo e getStipendio
      * La dicitura @override serve per far verificare l'esecuzione della sovrascrittura da parte del compilatore
      */
    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getRuolo() {
        return ruolo;
    }

    /**
     * RIF STRATEGY PATTERN (CONTEXT CLASS) P2-2:
     * Qui faccio il calcolo dello stipendio delgando l'operazione ad una specifica
     * strategia, quindi se è AzioneStipendioBase oppure AzioneStipendioBonus
     * Questo mi permette di separare la logica di calcolo da Dipendente 
     */
    @Override
    public double getStipendio() {
        double stipendioCal = this.calcoloStrategy.calcola(this.stipendioBase);
        logger.log(Level.FINER, "lo stipendio di " + nome + " e stato calcolato così: " + stipendioCal);
        return stipendioCal;
    }
}