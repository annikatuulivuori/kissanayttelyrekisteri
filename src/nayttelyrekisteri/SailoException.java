package nayttelyrekisteri;

/**
 * Poikkeusluokka tietorakenteesta aiheutuville poikkeuksille
 * @author atuul
 * @version 3.3.2021
 *
 */
public class SailoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    
    /**
     * Poikkeuksen muodostaja jolla tuodaan poikkeuksessa
     * käytettävä viesti
     * @param viesti poikkeuksen viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
