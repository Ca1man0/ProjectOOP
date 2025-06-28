// REVISIONATO (OKAY) - VERSIONE 2.0

// ORDINE 2 (STRATEGY PATTERN) - RIF STRATEGY INTERFACE - CALCOLOSTIPENDIO.JAVA

package com.examaiman.strategy_R;

 /**
  * STRATEGY PATTERN (STRATEGY INTERFACE):
  * Qui viene definito il metodo che dovrà essere implementato dalle
  * sottoclassi AzioneStipendioBase e AzioneStipendioBonus
  * Quindi queste classi devono fare una loro implementazione o logica
  * a questo metodo
  */

public interface CalcoloStipendioR {
    // Ovviamente l'input per il calcolo dello stipendio è quello di partenza, stipendioBase
    double calcola(double stipendioBase);
}