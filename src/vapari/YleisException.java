/**
 * 
 */
package vapari;

/**
 * Yleinen poikkeusluokka tapahtuville poikkeuksille.
 * @author mikar
 * @version 17 Feb 2020
 *
 */
public class YleisException extends Exception {
    // Poikkeusluokan sarjanumero.
    private static final long serialVersionUID = 1L;
    
    /**
     * Poikkeuksen muodostaja, joka saa parametrina poikkeuksena käytettävän viestin
     * @param viesti viesti joka annetaan poikkeuksen yhteydessä
     */
    public YleisException(String viesti) {
        super(viesti);
    }
    
}
