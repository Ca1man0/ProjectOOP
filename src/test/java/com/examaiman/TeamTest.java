package com.examaiman;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.examaiman.composite_iterator_observer_R.TeamR;
import com.examaiman.composite_iterator_observer_R.composite_R.ComponentePersonaleR;
import com.examaiman.composite_iterator_observer_R.composite_R.DipendenteR;
import com.examaiman.strategy_R.AzioneStipendioBaseR;
import com.examaiman.strategy_R.AzioneStipendioBonusR;
import com.examaiman.strategy_R.CalcoloStipendioR;

public class TeamTest {

    private CalcoloStipendioR stipendioStandard;
    private CalcoloStipendioR stipendioConBonus;

    /**
     * Qui faccio la mia preparazione del test, inizializzando le strategice di calcolo dello
     * stipendio     * 
     * @Before --> mi serve per far ricordare che questo metodo deve essere eseguito prima
     * 
     * 25 sta per 25% del bonus che voglio dare al dipendente
     */
    @Before
    public void setUp() {
        stipendioStandard = new AzioneStipendioBaseR();
        stipendioConBonus = new AzioneStipendioBonusR(25.0);
    }

    /**
     * TEST 1 "STIPENDIO SENZA BONUS"
     * Verifico che lo stipendio se è assegnato correttamente senza bonus
     * 
     */
    @Test
    public void testStipendioDipendenteSenzaBonus() {
        ComponentePersonaleR dipendente = new DipendenteR("Giuseppina", "Lavapiatti", 1500, stipendioStandard);
        assertEquals(1500.0, dipendente.getStipendio(), 0.001); // Controllo se corrisponde a 1500, 0.001 è la tolleranza per il confronto dei valori
    }

    /**
     * TEST 2 "STIPENDIO CON BONUS"
     * Verifico che lo stipendio con bonus è stato calcolato correttamente
     * 
     */
    @Test
    public void testStipendioDipendenteConBonus() {
        ComponentePersonaleR dipendente = new DipendenteR("Peppina", "Chef", 3200, stipendioConBonus);
        assertEquals(4000.0, dipendente.getStipendio(), 0.001); // Controllo se il calcolo è svolto correttamente, quindi 3200 + 3200*0.25 = 4000, 0.001 è la tolleranza per il confronto dei valori
    }

    /**
     * TEST 3 "STIPENDIO TOTALE DEL TEAM"
     * Verifico che lo stipendio totale del team (che comprende anche quello standard e bonus)
     * 
     */
    @Test
    public void testStipendioTotaleTeam() {
        ComponentePersonaleR chef = new DipendenteR("Balotelli", "Chef", 3200, stipendioConBonus); // 4000
        ComponentePersonaleR cuoco = new DipendenteR("Jackoso", "Cuoco", 2200, stipendioStandard);   // 2200
        TeamR teamCucina = new TeamR("Team cucina (Chef e Cuoco)");
        teamCucina.aggiungiMembro(chef);
        teamCucina.aggiungiMembro(cuoco);

        assertEquals(6200.0, teamCucina.getStipendio(), 0.001); // 4000 (bonus del 25%) + 2200 = 6200
    }

    /**
     * TEST 4 "STIPENDIO TOTALE DEL TEAM FINALE COMPOSTO DA ALTRI TEAM"
     * Verifico che lo stipendio totale del team (che comprende anche quello standard e bonus)
     * 
     */
    @Test
    public void testStipendioTeamComposito() {
        // Alle cucine
        ComponentePersonaleR chef = new DipendenteR("Sandokan", "Chef", 3200, stipendioConBonus); // 4000
        TeamR teamCucina = new TeamR("Team Cucina");
        teamCucina.aggiungiMembro(chef);

        // Alla plonge
        ComponentePersonaleR lavapiatti = new DipendenteR("Morandi", "Lavapiatti", 1500, stipendioStandard); // 1500
        TeamR teamSupporto = new TeamR("Team Supporto");
        teamSupporto.aggiungiMembro(lavapiatti);

        // Squadra completta
        TeamR brigata = new TeamR("Squadra finale (cucina e supporto)");
        brigata.aggiungiMembro(teamCucina);
        brigata.aggiungiMembro(teamSupporto);

        assertEquals(5500.0, brigata.getStipendio(), 0.001); // 3200 (bonus del 25%) + 1500 = 5500
    }
}