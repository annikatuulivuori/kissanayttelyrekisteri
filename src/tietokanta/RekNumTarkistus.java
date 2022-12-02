package tietokanta;

/**
 * Luokka rekisterinumeron tarkistamiseksi
 * @author atuul
 * @version 3.3.2021
 *
 */
public class RekNumTarkistus {
    
    /**
     * Arpoo satunnaisen rekisterinumeron, jossa on 7 numeroa
     * @return satunnainen rekisterinumero
     */
    public static String arvoRekNum() {
        int temp = rand(0, 9999999);
        return String.format("%07d", temp);
    }
    
    
    /**
     * Arpoo satunnaisen kokonaisluvun välille [ala, yla]
     * @param ala alaraja
     * @param yla yläraja
     * @return satunnainen luku väliltä [ala, yla]
     */
    public static int rand(int ala, int yla) {
        double n = (yla-ala)*Math.random() + ala;
        return (int)Math.round(n);
    }
    
    
    /**
     * Tarkistaa, onko rekisterinumerossa seitsemän numeroa
     * @param rekNum rekisterinumero, jota tarkistetaan
     * @return true jos oikein, muuten false
     * @example
     * <pre name="test">
     *      tarkistaRekNum("0985001") === true;
     *      tarkistaRekNum("00012") === false;
     *      tarkistaRekNum("134529487") === false;
     *      tarkistaRekNum("") === false;
     * </pre>
     */
    public static boolean tarkistaRekNum(String rekNum) {
        if (rekNum.length() == 7) return true;
        return false;
    }
    
}
