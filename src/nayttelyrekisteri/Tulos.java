package nayttelyrekisteri;

import java.io.*;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author atuul
 * @version 23.4.2021
 * ==============================
 * karvalakki-malli;
 * TODO: get/set-metodit fiksummin
 */
public class Tulos {
    
    private int    tunnusNro;
    private String kissanRekNimi    = "";
    private int    kissaNro;
    private int    nayttelyNro;
    private String serti            = "";
    private String tuomari          = "";
     
    private static int seuraavaNro  = 1;
    
    /**
     * Oletusmuodostaja
     */
    public Tulos() {
        // ei tee vielä mitään
    }
    
    
    /**
     * Alustaa tietyn kissan tuloksen
     * @param kissaNro kissan viitenumero
     */
    public Tulos(int kissaNro) {
        this.kissaNro = kissaNro;
    }
    
    
    /**
     * Alustetaan tietyn kissan tulos
     * @param kissaNro kissan viitenumero
     * @param nayttelyNro näyttelyn viitenumero
     */
    public Tulos(int kissaNro, int nayttelyNro) {
        this.kissaNro = kissaNro;
        this.nayttelyNro = nayttelyNro;
    }
    
    
    /**
     * @param k minkä kentän sisältä annetaan
     * @return valitun kentän sisältö merkkijonona
     */
    public String anna(int k) {
        switch (k) {
        case 0: return "" + tunnusNro;
        case 1: return "" + kissaNro;
        case 2: return "" + nayttelyNro;
        case 3: return "" + serti;
        case 4: return "" + tuomari;
        default: return "???";
        }
    }
    
    
    /**
     * Alustaa uuden näyttelytuloksen
     * @param kissanro viite kissaan, jonka tuloksesta on kyse
     * @param nayttelynro viite nayttelyyn, jossa kissa kävi
     */
    public void alustaTulos(int kissanro, int nayttelynro) {
        kissanRekNimi = "Electric India";
        serti = "CAPS";
        tuomari = "Saarela Veikko";
        kissaNro = kissanro;
        nayttelyNro = nayttelynro;
    }


    /**
     * Antaa yksittäiselle tulokselle seuraavan numeron tunnukseksi
     * @return tuloksen uusi tunnusNro
     * @example
     * <pre name="test">
     *      Tulos tulos1 = new Tulos();
     *      tulos1.getTunnusNro() === 0;
     *      tulos1.rekisteroi();
     *      Tulos tulos2 = new Tulos();
     *      tulos2.rekisteroi();
     *      int n1 = tulos1.getTunnusNro();
     *      int n2 = tulos2.getTunnusNro();
     *      n2 === n1+1;
     * </pre>
     */
    public int rekisteroi() {
        tunnusNro = seuraavaNro;
        seuraavaNro++;
        return tunnusNro;
    }
    
    
    /**
     * Tulostetaan tuloksen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("Kissa: " + kissanRekNimi + "   " + String.format("%03d", kissaNro));
        out.println("   Näyttely: " + nayttelyNro);
        out.println("   Näyttelytulos: " + serti);
        out.println("   Tuomari: " + tuomari);
        out.println("       Tunnusnumero: " + tunnusNro);
    }
    
    
    /**
     * Tulostetaan tuloksen tiedot
     * @param os tietovirta, johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa tuloksen tiedot merkkijonona, jonka voi tallentaa tiedostoon
     * @return tulos tolppaeroteltuna merkkijonona
     * @example
     * <pre name="test">
     *      Tulos tulos = new Tulos();
     *      tulos.parse("3 |1 |1 |CAC |Turpeinen Riikka");
     *      tulos.toString().startsWith("3|1|1|CAC|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return tunnusNro + "|" + 
                kissaNro + "|" +
                nayttelyNro + "|" + 
                serti + "|" + tuomari;
    }
    
    
    /**
     * Palautetaan, mille kissalle tulos kuuluu
     * @return kissan id
     */
    public int getKissaNro() {
        return kissaNro;
    }
    
    /**
     * Palauttaa tuloksen tunnusnumeron
     * @return tuloksen tunnusnumero
     */
    public int getTunnusNro() {
        return tunnusNro;
    }
    
    
    /**
     * Palauttaa näyttelyn viitenumeron
     * @return näyttelyn tunnusnumero
     */
    public int getNayttelyNro() {
        return nayttelyNro;
    }
    
    
    /**
     * Palauttaa tuloksen sertifikaatin nimen
     * @return serti
     */
    public String getSerti() {
        return serti;
    }
    
    
    /**
     * Palauttaa tuloksen antaneen tuomarin nimen
     * @return tuomari
     */
    public String getTuomari() {
        return tuomari;
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
     * Asettaa näyttelyn numeron
     * @param nr asetettava tunnusnumero
     */
    public void setNayttelyNro(int nr) {
        nayttelyNro = nr;
    }
    
    
    /**
     * Asettaa tulokselle sertinimikkeen
     * @param s serti
     * @return virheilmoitus, null jos onnistui
     */
    public String setSerti(String s) {
        serti = s.toUpperCase();
        return null;
    }
    
    
    /**
     * Asettaa tulokselle tuomarin
     * @param s tuomari
     * @return virheilmoitus, null jos onnistui
     */
    public String setTuomari(String s) {
        tuomari = s;
        return null;
    }
    
    
    /**
     * Selvittää tuloksen tiedot | erotellusta merkkijonosta
     * Pitää huolen että seuraavaNro on suurempi kuin tuleva tunnusNro.
     * @param rivi josta tuloksen tiedot otetaan
     * 
     * @example
     * <pre name="test">
     *   Tulos tulos = new Tulos();
     *   tulos.parse("3 |2 |1   |CAC |Turpeinen Riikka");
     *   tulos.getTunnusNro() === 3;
     *   tulos.toString().startsWith("3|2|1|CAC|") === true;
     *
     *   tulos.rekisteroi();
     *   int n = tulos.getTunnusNro();
     *   tulos.parse(""+(n+20));
     *   tulos.rekisteroi();
     *   tulos.getTunnusNro() === n+20+1;
     *     
     * </pre>
     */
    public void parse(String rivi) {
        var sb = new StringBuilder(rivi);
        setTunnusNro(Mjonot.erota(sb, '|', getTunnusNro()));
        setKissaNro(Mjonot.erota(sb, '|', getTunnusNro()));
        setNayttelyNro(Mjonot.erota(sb, '|', getTunnusNro()));
        serti = Mjonot.erota(sb, '|', serti);
        tuomari = Mjonot.erota(sb, '|', tuomari);
    }
    
    
    /**
     * Testiohjelma tulokselle
     * @param args ei käytössä
     */
    public static void main(String args[]) {
                
        Tulos tulos = new Tulos();
        tulos.rekisteroi();
        tulos.alustaTulos(1, 1);
        
        Tulos tulos2 = new Tulos();
        tulos2.rekisteroi();
        tulos2.alustaTulos(2, 2);
        
        tulos.tulosta(System.out);
        tulos2.tulosta(System.out);
    }
}
