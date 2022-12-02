package nayttelyrekisteri;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Näyttelyrekisterissä oleva titteli
 * @author atuul
 * @version 23.4.2021
 * ==============================
 * karvalakki-malli;
 * TODO: get/set-metodit fiksummin
 *
 */
public class Titteli implements Cloneable {
    
    private int tunnusNro;
    private String pvm          ="";
    private String vahvistusPvm ="";
    private String luokka       ="";
    private int    kissaNro;
    
    private static int seuraavaNro = 1;
    
    
    /** 
     * Luokka, joka osaa verrata kahta titteliä
     */ 
    public static class Vertailija implements Comparator<Titteli> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        @Override 
        public int compare(Titteli titteli1, Titteli titteli2) { 
            return titteli1.getAvain(k).compareToIgnoreCase(titteli2.getAvain(k)); 
        } 
    }
    
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monennenko kentän sisältö palautetaan
     * @return kentän sisältä merkkijonona
     */
    public String getAvain(int k) {
        switch (k) {
        case 0: return "" + tunnusNro;
        case 1: return "" + kissaNro;
        case 2: return "" + luokka;
        case 3: return "" + pvm;
        case 4: return "" + vahvistusPvm;
        default: return "dumdum";
        }
    }
    
    
    /**
     * Oletusmuodostaja
     */
    public Titteli() {
        // ei tee vielä mitään
    }
    
    /**
     * Alustetaan tietyn kissan titteli
     * @param kissaNro kissan viitenumero
     */
    public Titteli(int kissaNro) {
        this.kissaNro = kissaNro;
    }
    
    /**
     * Apumetodi, joka palauttaa tittelin nimen
     * @return tittelin nimi
     */
    public String getTitteli() {
        return luokka;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä tittelille testiarvot
     * @param nro viite kissaa, jonka tittelistä on kyse
     */
    public void alustaTitteli(int nro) {
        kissaNro = nro;
        pvm = "28092019";
        vahvistusPvm = "03102019";
        luokka = "Premior";
    }
    
    
    /**
     * Tulostetaan tittelin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(luokka + "  Päivämäärä  " + pvm + ",  Vahvistettu  " + vahvistusPvm);
    }
    
    /**
     * Tulostetaan tittelin tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa tittelille seuraavan numeron tunnukseksi
     * @return tittelin uusi tunnusNro
     * @example
     * <pre name="test">
     *      Titteli titteli1 = new Titteli();
     *      titteli1.getTunnusNro() === 0;
     *      titteli1.rekisteroi();
     *      Titteli titteli2 = new Titteli();
     *      titteli2.rekisteroi();
     *      int n1 = titteli1.getTunnusNro();
     *      int n2 = titteli2.getTunnusNro();
     *      n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa tittelin tunnusnumeron
     * @return tittelin tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa, mille kissalle titteli kuuluu
     * @return kissan id
     */
    public int getKissaNro() {
        return kissaNro;
    }
    
    
    /**
     * Palauttaa tittelin luokan
     * @return luokka
     */
    public String getLuokka() {
        return luokka;
    }
    
    
    /**
     * Palauttaa tittelin päivämäärän
     * @return päivämäärä
     */
    public String getPvm() {
        return pvm;
    }
    
    
    /**
     * Palauttaa tittelin vahvistuspäivämäärän
     * @return päivämäärä, jolloin titteli vahvistettiin
     */
    public String getVahvistus() {
        return vahvistusPvm;
    }
    
    
    /**
     * Asettaa tittelin luokan
     * @param s luokka
     * @return virheilmoitus, null jos onnistui
     */
    public String setLuokka(String s) {
        luokka = s;
        return null;
    }
    
    
    /**
     * Asettaa tittelin päivämäärän
     * @param s päivämäärä
     * @return virheilmoitus, null jos onnistui
     * @example
     * <pre name="test">
     * Titteli titteli = new Titteli();
     * titteli.setPvm("26012020") === null;
     * titteli.setPvm("lol") === "Päivämäärä väärin";
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
     * Asettaa tittelin vahvistuspäivämäärän
     * @param s vahvituksen päivämäärä
     * @return virheilmoitus, null jos onnistui
     * @example
     * <pre name="test">
     * Titteli titteli = new Titteli();
     * titteli.setVahvistus("26012020") === null;
     * titteli.setVahvistus("lol") === "Vahvistuksen päivämäärä väärin";
     * </pre>
     */
    public String setVahvistus(String s) {
        String regex = "[0-9]+";
        if (s.matches(regex) && s.length() == 8) {
            vahvistusPvm = s;
            return null;
        }
        return "Vahvistuksen päivämäärä väärin";
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
     * Asettaa kissan numeron
     * @param nr asetettava tunnusnumero
     */
    private void setKissaNro(int nr) {
        kissaNro = nr;
    }
    
    
    /**
     * @param k monennenko kentän sisältö palautetaan
     * @return valitun kentän sisältö
     */
    public String anna(int k) {
        switch (k) {
        case 0: return "" + tunnusNro;
        case 1: return "" + kissaNro;
        case 2: return "" + luokka;
        case 3: return "" + pvm;
        case 4: return "" + vahvistusPvm;
        default:
            return "???";
        }
    }
    
    
    /**
     * Palauttaa tittelin tiedot merkkijonona, jonka voi tallentaa tiedostoon
     * @return tittelin tolppaeroteltuna merkkijonona
     * @example
     * <pre name="test">
     *      Titteli titteli = new Titteli();
     *      titteli.parse("3 |1 |Grand International Premior |31102020 |10112020");
     *      titteli.toString().startsWith("3|1|Grand International Premior|31102020|10112020") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + 
                getTunnusNro() + "|" + 
                kissaNro + "|" + 
                luokka + "|" + 
                pvm + "|" + vahvistusPvm; 
    }
    
    
    /**
     * Tekee identtisen kloonin tittelistä
     * @return Object kloonattu titteli
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Titteli titteli = new Titteli();
     *   titteli.parse("   3  |  Premior   | 00000000 | 00000000");
     *   Titteli kopio = titteli.clone();
     *   kopio.toString() === titteli.toString();
     *   titteli.parse("   4  |  Supreme Premior   | 11111111 | 11111111");
     *   kopio.toString().equals(titteli.toString()) === false;
     * </pre>
     */
    @Override
    public Titteli clone() throws CloneNotSupportedException {
        Titteli uusi;
        uusi = (Titteli) super.clone();
        return uusi;
    }
    
    
    
    /**
     * Selvittää tittelin tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta tittelin tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Titteli titteli = new Titteli();
     *   titteli.parse("3 |1 |Grand International Premior |31102020 |10112020");
     *   titteli.getTunnusNro() === 3;
     *   titteli.toString().startsWith("3|1|Grand International Premior|") === true;
     *
     *   titteli.rekisteroi();
     *   int n = titteli.getTunnusNro();
     *   titteli.parse(""+(n+20));
     *   titteli.rekisteroi();
     *   titteli.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        setKissaNro(Mjonot.erota(sb, '|', getKissaNro()));
        luokka = Mjonot.erota(sb, '|', luokka);
        pvm = Mjonot.erota(sb, '|', pvm);
        vahvistusPvm = Mjonot.erota(sb, '|', vahvistusPvm);
    }
    
    
    /**
     * Testiohjelma titteleille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Titteli titteli1 = new Titteli(1), titteli2 = new Titteli(2);
        titteli1.rekisteroi();
        titteli2.rekisteroi();
        titteli1.tulosta(System.out);
        titteli1.alustaTitteli(1);
        titteli1.tulosta(System.out);
        
        titteli2.alustaTitteli(1);
        titteli2.tulosta(System.out);
    }
}
