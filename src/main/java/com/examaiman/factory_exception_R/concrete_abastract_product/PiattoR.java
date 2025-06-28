// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 0 (FACTORY PATTERN) - RIF. PRODUCT INTERFACE - PIATTO.JAVA

/**
 * FACTORY PATTERN (PRODUCT INTERFACE):
 * Qui definisco l'interfaccia comune per tutti i prodotti (ConcreteProduct --> Pasta.java e Carne.java) che posso creare con il metodo factory,
 * quindi si dichiarano i metodi che tutti i piatti del ristorante devono avere.
 * Di conseguenza il client può far riferimento a Piatto.java senza conoscere i dettagli delle sottoclassi, ovvero i ConcreteProduct
 */

/**
 * TECNOLOGIE USATE:
 *  - LIST - COLLECTION FRAMEWORK | GENERICS:
 *    --> protected List<String> ingredienti; --> dichiaro una lista che puo contenere solo oggetti di tipo String
 */

package com.examaiman.factory_exception_R.concrete_abastract_product;
import java.util.List;

// Public accessibile da qualsiasi codice e Abstract perchè sarà un riferimento alla sottoclassi (Pasta e Carne)
public abstract class PiattoR {
    // Protected perchè sono attributi accessibili dalla classe Piatto o sottoclassi che lo estendono
    
    // Se definiscono il nome del piatto e la sua lista di ingredienti
    protected String nome;
    protected List<String> ingredienti; // Esplicito che la lista di ingredienti puo avere solo il tipo String
    
    // Questo è un costruttore che mi serve per l'inizializzazione degli attributi dichiarati in precedenza
    public PiattoR(String nome, List<String> ingredienti) {
        this.nome = nome;
        this.ingredienti = ingredienti;
    }

    /**
     * Questo è un metodo astratto dove obbliga le sottoclassi (Pasta e Carne) a implementarlo
     * a modo loro, specificando la preparazione del piatto. Quindi definisco un'attività comune
     * poi sta alle sottoclassi di implementarlo con la loro logica o comportamento
     */
    public abstract void prepara();

    /**
     * Questi sono dei metodi getter per la LETTURA dei valori all'esterno della classe,
     * infatti ho la dicitura public, quindi posso chiedere il nome del piatto e i suoi ingredienti
     */
    public String getNome() {
        return this.nome;
    }
    public List<String> getIngredienti() {
        return this.ingredienti;
    }
}