package nayttelyrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Näyttelyrekisterin näyttely
 * @author atuul
 * @version 23.4.2021
 * ==============================
 * karvalakki-malli;
 * TODO: get/set-metodit fiksummin
 *
 */
public class Nayttely {
    
    private int tunnusNro;
    private String pvm          = "";
    private String yhdistys     = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * @return yhdistyksen nimi
     */
    public String getYhdistys() {
        return yhdistys;
    }
    
    
    /**
     * @return näyttelyn päivämäärä
     */
    public String getPvm() {
        return pvm;
    }
    
    
    /**
     * Asettaa näyttelylle yhdistyksen
     * @param s yhdistys
     * @return virheilmoitus, null jos onnistui
     */
    public String setYhdistys(String s) {
        yhdistys = s.toUpperCase();
        return null;
    }
    
    
    /**
     * Asettaa näyttelylle päivämäärän
     * @param s päivämäärä
     * @return virheilmoitus, null jos onnistui
     * @example
     * <pre name="test">
     * Nayttely nayttely = new Nayttely();
     * nayttely.setPvm("26012020") === null;
     * nayttely.setPvm("2612020") === "Päivämäärä väärin";
     * </pre>
     */
    public String setPvm(String s) {
        String regex = "[0-9]+";
        if (s.matches(regex) && s.length() == 8) {
            pvm = s;
            return null;
        }
        return "Päivämäärä väärin";
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä näyttelylle testiarvot
     */
    public void alustaNayttely() {
        pvm = "31102020";
        yhdistys = "PIROK";
    }
    
    
    /**
     * Tulostetaan näyttelyn tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3)
                + yhdistys);
        out.println("  Päivämäärä  " + pvm);
    }
    
    
    /**
     * Tulsotetaan näyttelyn tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa näyttelylle seuraavan rekisterinumeron.
     * @return näyttelyn uusi tunnusNro
     * @example
     * <pre name="test">
     *      Nayttely show1 = new Nayttely();
     *      show1.getTunnusNro() === 0;
     *      show1.rekisteroi();
     *      Nayttely show2 = new Nayttely();
     *      show2.rekisteroi();
     *      int n1 = show1.getTunnusNro();
     *      int n2 = show2.getTunnusNro();
     *      n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa näyttelyn tunnusnumeron
     * @return näyttelyn tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Asettaa tunnusnumeron ja samalla varmistaa että
     * seuraava numero on aina suurempi kuin tähän mennessä suurin.
     * @param nr asetettava tunnusnumero
     */
    private void setTunnusNro(int nr) {
        tunnusNro = nr;
        if (tunnusNro >= seuraavaNro) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * Selvittää näyttelyn tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta näyttelyn tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Nayttely nayttely = new Nayttely();
     *   nayttely.parse(" 3|PIROK|31102020");
     *   nayttely.getTunnusNro() === 3;
     *   nayttely.toString().startsWith("3|PIROK|31102020") === true;
     *
     *   nayttely.rekisteroi();
     *   int n = nayttely.getTunnusNro();
     *   nayttely.parse(""+(n+20));
     *   nayttely.rekisteroi();
     *   nayttely.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        yhdistys = Mjonot.erota(sb, '|', yhdistys);
        pvm = Mjonot.erota(sb, '|', pvm);
    }
    
    
    /**
     * Palauttaa näyttelyn tiedot merkkijonona, jonka voi tallentaa tiedostoon
     * @return näyttelyn tolppaeroteltuna merkkijonona
     * @example
     * <pre name="test">
     *      Nayttely nayttely = new Nayttely();
     *      nayttely.parse("3|PIROK|31102020");
     *      nayttely.toString().startsWith("3|PIROK|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + 
                getTunnusNro() + "|" + 
                yhdistys + "|" + 
                pvm;
    }
    
    
    /**
     * Testiohjelma näyttelylle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Nayttely show1 = new Nayttely(), show2 = new Nayttely();
        show1.rekisteroi();
        show2.rekisteroi();
        show1.tulosta(System.out);
        show1.alustaNayttely();
        show1.tulosta(System.out);
        
        show2.alustaNayttely();
        show2.tulosta(System.out);
    }

}
