// REVISIONATO (OKAY) - VERSIONE 2.0

// RIFERIMENTO AL PATTERN PRESENTE:
// ORDINE 2 (FACTORY PATTERN) - RIF CREATOR CLASS - PIATTOFACTORY.JAVA

package com.examaiman.factory_exception_R.concrete_abstract_factory;
import java.util.List;

import com.examaiman.factory_exception_R.concrete_abastract_product.PiattoR;

/**
 * FACTORY PATTERN (CREATOR CLASS):
 * Questa classe astratta dichiara i metodi che dovranno essere implementati dalle sottoclassi, quindi
 * PrimoFactory e SecondoFactory per produrre oggetti specifici della classe Piatto
 */

 /**
 * TECNOLOGIE USATE:
 *  - LIST - COLLECTION FRAMEWORK | GENERICS:
 *    --> public abstract PiattoR creaPiatto(String tipo, List<String> ingredienti) --> lista che puo contenere solo oggetti di tipo String
 */


// Public accessibile da qualsiasi codice e Abstract perchè sarà un riferimento alla sottoclassi (PrimoFactory e SecondoFactory)
public abstract class PiattoFactoryR {
    
    // Ogni sua sottoclasse quindi PrimoFactory e SecondoFactory dovrà implementare il metodo creaPiatto
    public abstract PiattoR creaPiatto(String tipo, List<String> ingredienti);
}