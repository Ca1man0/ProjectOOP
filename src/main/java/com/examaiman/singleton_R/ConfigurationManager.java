// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 1 (SINGLETON PATTERN) - RIF INITIALIZATION ON DEMAND HOLDER - CONFIGURATIONMANAGER.JAVA

package com.examaiman.singleton_R;

/**
 * SINGLETON PATTERN (Singleton):
 * Qui uso il pattern Singleton nella versione initialization on demand holder,
 * quindi ho la possibilità di creare un'istanza unica da utilizzare su tutta l'applicazione, quindi
 * questo è l'unico punto di accesso.
 */
public class ConfigurationManager {

    // Parametro di configurazione sulla percentuale del bonus
    private double bonusScelto = 25.0;

    // Private così evito altre classe di creare nuove istanze
    private ConfigurationManager() {}

    // Questa classe mi serve per garantire l'istanza singola, quindi non viene caricata se non richiamata
    private static class SingletonHelper {
        // final evito la modifica dopo l'istanza
        private static final ConfigurationManager istanzaCreata = new ConfigurationManager();
    }

    /**
     * Spiegazione:
     * CASO INIZIALE --> (1) accedo alla classe SingletonHelper (2) è la prima volta? si (3) creo l'istanza
     * (4) getIstance restituisce l'istanza appena create
     * 
     * CASO SUCCESSIVO --> (1) chiamo getIstance (2) JVM vede che SingletonHelper è già stato caricato (3) non creo l'istanza, 
     * ma ti do quella che ho
     */
    public static ConfigurationManager getInstance() {
        return SingletonHelper.istanzaCreata;
    }

    /**
     * Ho creato la mia istanza in precedenza, NON HO STATIC, quindi vuuol dire che il metodo
     * appartiene all'istanza che ho creato
     */
    public double getDefaultBonusPercentage() {
        return bonusScelto;
    }
}