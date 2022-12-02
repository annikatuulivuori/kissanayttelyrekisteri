package nayttelyrekisteri;

import java.io.*;
import java.util.Comparator;

import fi.jyu.mit.ohj2.Mjonot;

import static tietokanta.RekNumTarkistus.*;

/**
 * Näyttelyrekisterin kissa
 * 
 * @author atuul
 * @version 26.4.2021
 * ==============================
 * karvalakki-malli;
 * TODO: get/set-metodit fiksummin
 *
 */
public class Kissa implements Cloneable {

    private int tunnusNro;
    private String kasvattaja   = "";
    private String rekNimi      = "";
    private String kutsumaNimi  = "";
    private String syntymaAika  = "";
    private String sukupuoli    = "";
    private String rotu         = "";
    private String ems          = "";
    private String rekNum       = "";
    
    private static int seuraavaNro     = 1;
    
    
    /** 
     * Luokka, joka osaa verrata kahta kissaa
     */ 
    public static class Vertailija implements Comparator<Kissa> { 
        private int k;  
         
        @SuppressWarnings("javadoc") 
        public Vertailija(int k) { 
            this.k = k; 
        } 
         
        @Override 
        public int compare(Kissa kissa1, Kissa kissa2) { 
            return kissa1.getAvain(k).compareToIgnoreCase(kissa2.getAvain(k)); 
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
        case 1: return "" + kasvattaja;
        case 2: return "" + rekNimi.toUpperCase();
        case 3: return "" + kutsumaNimi.toUpperCase();
        case 4: return "" + syntymaAika; //vaihda vuosi eteen
        case 5: return "" + sukupuoli;
        case 6: return "" + rotu;
        case 7: return "" + ems;
        case 8: return "" + rekNum;
        default: return "dumdum";
        }
    }
    
    
    /**
     * Eka kenttä, joka on mielekäs kysyttäväksi
     * @return ekan kentän indeksi
     */
    public int ekaKentta() {
        return 1;
    }
    
    
    /**
     * Palauttaa kissan kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    public int getKenttia() {
        return 9;
    }
    
    
    /**
     * @return kissan nimi
     */
    public String getNimi() {
        return rekNimi;
    }
    
    
    /**
     * @return kissan kasvattaja
     */
    public String getKasvattaja() {
        return kasvattaja;
    }
    
    
    /**
     * @return kissan kutsumanimi
     */
    public String getKutsumanimi() {
        return kutsumaNimi;
    }
    
    
    /**
     * @return kissan syntymäaika
     */
    public String getSpv() {
        return syntymaAika;
    }
    

    /**
     * Palauttaa kissan sukupuolen
     * @return sukupuoli
     */
    public String getSukupuoli() {
        return sukupuoli;
    }
    
    
    /**
     * Palauttaa kissan rodun
     * @return rotu
     */
    public String getRotu() {
        return rotu;
    }
    
    
    /**
     * Palauttaa kissan ems-koodin
     * @return kissan kutsumanimi
     */
    public String getEMS() {
        return ems;
    }
    
    
    /**
     * Palauttaa kissan rekisterinumeron
     * @return kissan rekisterinumero
     */
    public String getRekNum() {
        return rekNum;
    }
    
    
    /**
     * @param s kissalle laitettava rekisterinimi
     * @return virheilmoitus, null jos ok
     */
    public String setRekNimi(String s) {
        rekNimi = s;
        return null;
    }
    
    
    /**
     * @param s kissan kasvattaja
     * @return virheilmoitus, null jos ok
     */
    public String setKasvattaja(String s) {
        kasvattaja = s;
        return null;
    }
    
    
    /**
     * @param s kissalle laitettava kutsumanimi
     * @return virheilmoitus, null jos ok
     */
    public String setKutsumanimi(String s) {
        kutsumaNimi = s;
        return null;
    }
    
    
    /**
     * @param s kissalle laitettava syntymäaika
     * @return virheilmoitus, null jos ok
     * @example
     * <pre name="test">
     * Kissa kissa = new Kissa();
     * kissa.setSpv("22092017") === null;
     * kissa.setSpv("22917") === "Syntymäaika väärin";
     * kissa.setSpv("") === "Syntymäaika väärin";
     * </pre>
     */
    public String setSpv(String s) {
        String regex = "[0-9]+";
        if (s.matches(regex) && s.length() == 8) {
            syntymaAika = s;
            return null;
        }
        return "Syntymäaika väärin";
        
    }
    
    
    /**
     * Asettaa kissalle sukupuolen
     * @param s kissan sukupuoli
     * @return virheilmoitus, null jos ok
     * @example
     * <pre name="test">
     * Kissa kissa = new Kissa();
     * kissa.setSukupuoli("Sisu") === "Sukupuoli väärin";
     * kissa.setSukupuoli("uros") === null;
     * </pre>
     */
    public String setSukupuoli(String s) {
        if (s.toLowerCase().equals("naaras") || s.toLowerCase().equals("uros")) {
            sukupuoli = s;
            return null;
        }
        return "Sukupuoli väärin";
    }
    
    
    /**
     * Asettaa kissalle rodun
     * @param s rotu
     * @return virheilmoitus, null jos ok
     */
    public String setRotu(String s) {
        rotu = s;
        return null;
    }
    
    
    /**
     * Asettaa kissalle ems-koodin
     * @param s kissan ems-koodi
     * @return virheilmoitus, null jos ok
     */
    public String setEMS(String s) {
        ems = s;
        return null;
    }
    
    
    /**
     * Asettaa kissalle rekisterinumeron
     * @param s kissan rekisterinumero
     * @return virheilmoitus, null jos ok
     * @example
     * <pre name="test">
     * Kissa kissa = new Kissa();
     * kissa.setRekNum("1822560") === null;
     * kissa.setRekNum("1822") === "Rekisterinumero väärin";
     * kissa.setRekNum("") === "Rekisterinumero väärin";
     * </pre>
     */
    public String setRekNum(String s) {
        String regex = "[0-9]+";
        if (s.matches(regex) && s.length() == 7) {
            rekNum = s;
            return null;
        }
        return "Rekisterinumero väärin";
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä kissalle testiarvot
     * @param apuRekNum rekisterinumero, joka annetaan kissalle
     */
    public void alustaKissa(String apuRekNum) {
        kasvattaja = "FI*Siniidan";
        rekNimi = "Electric India";
        kutsumaNimi = "TOFU";
        syntymaAika = "22092018";
        sukupuoli = "uros";
        rotu = "Norjalainen Metsäkissa";
        ems = "NFO ds 03 22";
        rekNum = apuRekNum;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot kissalle.
     * Reksiterinumero arvotaan, jotta kahdella kissalla ei olisi
     * samoja tietoja.
     */
    public void alustaKissa() {
        String apuRekNum = arvoRekNum();
        alustaKissa(apuRekNum);
    }
    
    
    /**
     * Tulostetaan kissan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", tunnusNro, 3) + "  " + kasvattaja + "  "
                + rekNimi);
        out.println("  " + kutsumaNimi);
        out.println(syntymaAika + ",  " + sukupuoli);
        out.println(rotu + "  " + ems);
        out.println(rekNum);
    }
    
    
    /**
     * Tulsotetaan kissan tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Antaa kissalle seuraavan rekisterinumeron.
     * @return kissan uusi tunnusNro
     * @example
     * <pre name="test">
     *      Kissa kissa1 = new Kissa();
     *      kissa1.getTunnusNro() === 0;
     *      kissa1.rekisteroi();
     *      Kissa kissa2 = new Kissa();
     *      kissa2.rekisteroi();
     *      int n1 = kissa1.getTunnusNro();
     *      int n2 = kissa2.getTunnusNro();
     *      n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa kissan tunnusnumeron
     * @return kissan tunnusnumero
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
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monennenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     * @example
     * <pre name="test">
     *      Kissa kissa = new Kissa();
     *      kissa.parse("3|Electric India      |    FI*Siniidan | Tofu");
     *      kissa.toString().startsWith("3|Electric India|FI*Siniidan|Tofu|") === true;
     * </pre>
     */
    public String anna(int k) {
        switch (k) {
            case 0: return "" + tunnusNro;
            case 1: return "" + kasvattaja;
            case 2: return "" + rekNimi;
            case 3: return "" + kutsumaNimi;
            case 4: return "" + syntymaAika;
            case 5: return "" + sukupuoli;
            case 6: return "" + rotu;
            case 7: return "" + ems;
            case 8: return "" + rekNum;
            default: return "dumdum";
        }
    }
    
    
    /**
     * Palauttaa k:tta kissan kenttää vastaava kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan
     * @return k:netta kenttää vastaava kysymys
     */
    public String getKysymys(int k) {
        switch (k) {
        case 0: return "Tunnusnumero";
        case 1: return "Kasvattaja";
        case 2: return "Rekisteröity nimi";
        case 3: return "Kutsumanimi";
        case 4: return "Syntymäaika";
        case 5: return "Sukupuoli";
        case 6: return "Rotu";
        case 7: return "EMS-koodi";
        case 8: return "Rekisterinumero";
        default: return "dumdum";
        }
    }

    
    /**
     * Palauttaa kissan tiedot merkkijonona, jonka voi tallentaa tiedostoon
     * @return kissan tiedot tolppaeroteltuna merkkijonona
     * @example
     * <pre name="test">
     *      Kissa kissa = new Kissa();
     *      kissa.parse(" 3|Electric India|FI*Siniidan|Tofu");
     *      kissa.toString().startsWith("3|Electric India|FI*Siniidan|Tofu|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + 
                getTunnusNro() + "|" + 
                rekNimi + "|" + 
                kasvattaja + "|" +
                kutsumaNimi + "|" +
                sukupuoli + "|" +
                syntymaAika + "|" +
                rotu + "|" +
                ems + "|" +
                rekNum;
    }
    
    
    /**
     * Tekee identtisen kloonin kissasta
     * @return Object kloonattu kissa
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException 
     *   Kissa kissa = new Kissa();
     *   kissa.parse("   3  |  Electric India   | FI*Siniidan");
     *   Kissa kopio = kissa.clone();
     *   kopio.toString() === kissa.toString();
     *   kissa.parse("   4  |  Black Halo   | FI*Siniidan");
     *   kopio.toString().equals(kissa.toString()) === false;
     * </pre>
     */
    @Override
    public Kissa clone() throws CloneNotSupportedException {
        Kissa uusi;
        uusi = (Kissa) super.clone();
        return uusi;
    }
    
    
    /**
     * Selvittää kissan tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta jäsenen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Kissa kissa = new Kissa();
     *   kissa.parse(" 3|Electric India|FI*Siniidan|Tofu");
     *   kissa.getTunnusNro() === 3;
     *   kissa.toString().startsWith("3|Electric India|FI*Siniidan|Tofu|") === true;
     *
     *   kissa.rekisteroi();
     *   int n = kissa.getTunnusNro();
     *   kissa.parse(""+(n+20));
     *   kissa.rekisteroi();
     *   kissa.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        rekNimi = Mjonot.erota(sb, '|', rekNimi);
        kasvattaja = Mjonot.erota(sb, '|', kasvattaja);
        kutsumaNimi = Mjonot.erota(sb, '|', kutsumaNimi);
        sukupuoli = Mjonot.erota(sb, '|', sukupuoli);
        syntymaAika = Mjonot.erota(sb, '|', syntymaAika);
        rotu = Mjonot.erota(sb, '|', rotu);
        ems = Mjonot.erota(sb, '|', ems);
        rekNum = Mjonot.erota(sb, '|', rekNum);
    }
    
    
    /**
     * Testiohjelma kissalle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
        
        kissa1.rekisteroi();
        kissa2.rekisteroi();
        
        kissa1.tulosta(System.out);
        kissa1.alustaKissa();
        kissa1.tulosta(System.out);
        
        kissa2.alustaKissa();
        kissa2.tulosta(System.out);
        
        kissa2.alustaKissa();
        kissa2.tulosta(System.out);
    }
}
