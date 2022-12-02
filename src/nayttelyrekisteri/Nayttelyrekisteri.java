package nayttelyrekisteri;

import java.util.Collection;
import java.util.List;

/**
 * Näyttelyreksiteri-luokka, joka huolehtii luokkien välisestä
 * yhteistyöstä
 * @author atuul
 * @version 20.4.2021
 *
 * Testien alustus
 * @example
 * <pre name="testJAVA">
 * #import nayttelyrekisteri.SailoException;
 *  private Nayttelyrekisteri rekisteri;
 *  private Kissa kissa1;
 *  private Kissa kissa2;
 *  private int kid1;
 *  private int kid2;
 *  private Titteli titteli1;
 *  private Titteli titteli2;
 *  
 *  
 *  public void alustaRekisteri() {
 *    rekisteri = new Nayttelyrekisteri();
 *    kissa1 = new Kissa(); kissa1.alustaKissa(); kissa1.rekisteroi();
 *    kissa2 = new Kissa(); kissa2.alustaKissa(); kissa2.rekisteroi();
 *    kid1 = kissa1.getTunnusNro();
 *    kid2 = kissa2.getTunnusNro();
 *    titteli2 = new Titteli(kid2); titteli2.alustaTitteli(kid2);
 *    titteli1 = new Titteli(kid1); titteli1.alustaTitteli(kid2);
 *    try {
 *    rekisteri.lisaaKissa(kissa1);
 *    rekisteri.lisaaKissa(kissa2);
 *    rekisteri.lisaaTitteli(titteli1);
 *    rekisteri.lisaaTitteli(titteli2);
 *    } catch ( Exception e) {
 *       System.err.println(e.getMessage());
 *    }
 *  }
 * </pre>
 */
public class Nayttelyrekisteri {
    private Kissat kissat = new Kissat();
    private Tittelit tittelit = new Tittelit();
    private Nayttelyt nayttelyt = new Nayttelyt();
    private Tulokset tulokset = new Tulokset();
    
    private String hakemisto = "nayttelyrekisteri";
    
    
    /**
     * Lukee kissojen tiedot tiedostosta
     * @param nimi jota käytetään lukemiseen
     * @throws SailoException jos epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.*;
     * #import java.util.*;
     * 
     *   
     *  String hakemisto = "testirekisteri";
     *  File dir = new File(hakemisto);
     *  File ftied  = new File(hakemisto+"/kissat.dat");
     *  File fhtied = new File(hakemisto+"/tittelit.dat");
     *  File fttied = new File(hakemisto+"/tulokset.dat");
     *  File fntied = new File(hakemisto+"/nayttelyt.dat");
     *  dir.mkdir();  
     *  ftied.delete();
     *  fhtied.delete();
     *  fttied.delete();
     *  fntied.delete();
     *  rekisteri = new Nayttelyrekisteri(); // tiedostoja ei ole, tulee poikkeus 
     *  rekisteri.lueTiedostosta(hakemisto); #THROWS SailoException
     *  alustaRekisteri();
     *  rekisteri.tallenna(); 
     *  rekisteri = new Nayttelyrekisteri();
     *  rekisteri.lisaaKissa(kissa2);
     *  rekisteri.lisaaTitteli(titteli1);
     *  rekisteri.tallenna(); // tekee molemmista .bak
     *  ftied.delete()  === true;
     *  fhtied.delete() === true;
     *  fttied.delete();
     *  fntied.delete();
     *  File fbak = new File(hakemisto+"/kissat.bak");
     *  File fhbak = new File(hakemisto+"/tittelit.bak");
     *  File ftbak = new File(hakemisto+"/tulokset.bak");
     *  File fnbak = new File(hakemisto+"/nayttelyt.bak");
     *  fbak.delete() === true;
     *  fhbak.delete() === true;
     *  ftbak.delete();
     *  fnbak.delete();
     *  dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        kissat = new Kissat();
        tittelit = new Tittelit();
        nayttelyt = new Nayttelyt();
        tulokset = new Tulokset();
        
        kissat.lueTiedostosta(nimi);
        tittelit.lueTiedostosta(nimi);
        nayttelyt.lueTiedostosta(nimi);
        tulokset.lueTiedostosta(nimi);
    }
    
    
    /**
     * Palauttaa rekisterissä olevien kissojen lukumäärän
     * @return kissojen määrä
     */
    public int getKissoja() {
        return kissat.getLkm();
    }
    
    
    /**
     * Palauttaa rekisterissä olevien tittelien lukumäärän
     * @return tittelien lukumäärä
     */
    public int getTittelit() {
        return tittelit.getLkm();
    }
    
    
    /**
     * Palauttaa rekisterissä olevien näyttelyiden lukumäärän
     * @return näyttelyiden lukumäärä
     */
    public int getNayttelyt() {
        return nayttelyt.getLkm();
    }
    
    
    /**
     * Palauttaa rekisterissä olevien tuloksien lukumäärä
     * @return tuloksien lukumäärä
     */
    public int getTulokset() {
        return tulokset.getLkm();
    }
    
    
    /**
     * Lisää rekisteriin uuden kissan
     * @param kissa lisättävä kissa
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissa1.rekisteroi(); kissa2.rekisteroi();
     * rekisteri.getKissoja() === 0;
     * rekisteri.lisaaKissa(kissa1); rekisteri.getKissoja() === 1;
     * rekisteri.lisaaKissa(kissa2); rekisteri.getKissoja() === 2;
     * rekisteri.lisaaKissa(kissa1); rekisteri.getKissoja() === 3;
     * rekisteri.getKissoja() === 3;
     * rekisteri.annaKissa(0) === kissa1;
     * rekisteri.annaKissa(1) === kissa2;
     * rekisteri.annaKissa(2) === kissa1;
     * rekisteri.annaKissa(3) === kissa1; #THROWS IndexOutOfBoundsException
     * rekisteri.lisaaKissa(kissa1); rekisteri.getKissoja() === 4;
     * rekisteri.lisaaKissa(kissa1); rekisteri.getKissoja() === 5;
     * </pre>
     */
    public void lisaaKissa(Kissa kissa) {
        kissat.lisaa(kissa);
    }
    
    
    /**
     * Korvaa kissan tietorakenteessa. Ottaa kissan omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kissa. Jos ei löydy niin
     * lisätään uutena kissana
     * @param kissa lisättävän kissan viite
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaRekisteri();
     *  rekisteri.etsi("*",0).size() === 2;
     *  rekisteri.korvaaTaiLisaa(kissa1);
     *  rekisteri.etsi("*",0).size() === 2;
     * </pre>
     */ 
    public void korvaaTaiLisaa(Kissa kissa) throws SailoException {
        kissat.korvaaTaiLisaa(kissa);
    }
    
    
    /**
     * Korvaa tittelin tietorakenteessa. Ottaa tittelin omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva titteli. Jos ei löydy niin
     * lisätään uutena tittelinä
     * @param titteli lisättävän tittelin viite
     * @throws SailoException jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException  
     *  alustaRekisteri();
     *  rekisteri.etsi("*",0).size() === 2;
     *  rekisteri.korvaaTaiLisaa(titteli1);
     *  rekisteri.etsi("*",0).size() === 2;
     * </pre>
     */ 
    public void korvaaTaiLisaa(Titteli titteli) throws SailoException {
        tittelit.korvaaTaiLisaa(titteli);
    }
    
    
    /**
     * Lisää rekisteriin uuden tittelin
     * @param titteli lisättävä titteli
     * @example
     * <pre name="test">
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * Titteli titteli1 = new Titteli(), titteli2 = new Titteli();
     * titteli1.rekisteroi(); titteli2.rekisteroi();
     * rekisteri.getTittelit() === 0;
     * rekisteri.lisaaTitteli(titteli1); rekisteri.getTittelit() === 1;
     * rekisteri.lisaaTitteli(titteli2); rekisteri.getTittelit() === 2;
     * rekisteri.lisaaTitteli(titteli1); rekisteri.getTittelit() === 3;
     * rekisteri.getTittelit() === 3;
     * rekisteri.annaTitteli(0) === titteli1;
     * rekisteri.annaTitteli(1) === titteli2;
     * rekisteri.annaTitteli(2) === titteli1;
     * rekisteri.annaTitteli(3) === titteli1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaaTitteli(Titteli titteli) {
        tittelit.lisaa(titteli);
    }
    
    
    /**
     * Lisää rekisteriin uuden näyttelyn
     * @param nayttely lisättävä näyttely
     * @example
     * <pre name="test">
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely();
     * nayttely1.rekisteroi(); nayttely2.rekisteroi();
     * rekisteri.getNayttelyt() === 0;
     * rekisteri.lisaaNayttely(nayttely1); rekisteri.getNayttelyt() === 1;
     * rekisteri.lisaaNayttely(nayttely2); rekisteri.getNayttelyt() === 2;
     * rekisteri.lisaaNayttely(nayttely1); rekisteri.getNayttelyt() === 3;
     * rekisteri.getNayttelyt() === 3;
     * rekisteri.annaNayttely(0) === nayttely1;
     * rekisteri.annaNayttely(1) === nayttely2;
     * rekisteri.annaNayttely(2) === nayttely1;
     * rekisteri.annaNayttely(3) === nayttely1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaaNayttely(Nayttely nayttely) {
        nayttelyt.lisaa(nayttely);
    }
    
    
    /**
     * Lisää rekisteriin uuden tuloksen
     * @param tulos lisättävä tulos
     * <pre name="test">
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * 
     * Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely();
     * nayttely1.rekisteroi(); nayttely1.alustaNayttely();
     * nayttely2.rekisteroi(); nayttely2.alustaNayttely();
     * 
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissa1.rekisteroi(); kissa1.alustaKissa();
     * kissa2.rekisteroi(); kissa2.alustaKissa();
     * 
     * Tulos tulos1 = new Tulos(kissa1.getTunnusNro());
     * tulos1.rekisteroi(); tulos1.alustaTulos(kissa1.getTunnusNro(), nayttely1.getTunnusNro());
     * Tulos tulos2 = new Tulos(kissa2.getTunnusNro());
     * tulos2.rekisteroi(); tulos2.alustaTulos(kissa2.getTunnusNro(), nayttely2.getTunnusNro());
     * 
     * rekisteri.getTulokset() === 0;
     * rekisteri.lisaaTulos(tulos1);
     * rekisteri.lisaaTulos(tulos2);
     * rekisteri.annaTulos(0) == tulos1 === true;
     * rekisteri.annaTulos(1) == tulos2 === true;
     * rekisteri.annaTulos(3) === tulos1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaaTulos(Tulos tulos) {
        tulokset.lisaa(tulos);
    }
    
    
    /**
     * Palauttaa i:n kissan
     * @param i monesko kissa palautetaan
     * @return viite i:teen kissaan
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Kissa annaKissa(int i) throws IndexOutOfBoundsException {
        return kissat.anna(i);
    }
    
    
    /**
     * Palauttaa i:n tittelin
     * @param i monesko titteli palautetaan
     * @return viite i:teen titteliin
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Titteli annaTitteli(int i) throws IndexOutOfBoundsException {
        return tittelit.anna(i);
    }
    
    
    /**
     * Palauttaa i:n näyttelyn
     * @param i monesko näyttely palautetaan
     * @return viite i:teen näyttelyyn
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Nayttely annaNayttely(int i) throws IndexOutOfBoundsException {
        return nayttelyt.anna(i);
    }
    
    
    /**
     * Palauttaa halutun näyttelyn tunnusnumeron perusteella
     * @param tunnusNro halutun näyttelyn tunnusnumero
     * @return näyttely
     */
    public Nayttely getNayttelyNro(int tunnusNro) {
        return nayttelyt.getNayttelyNro(tunnusNro);
    }
    
    
    /**
     * Palauttaa i:n tuloksen
     * @param i monesko tulos halutaan
     * @return viite i:teen tulokseen
     * @throws IndexOutOfBoundsException jos i väärin
     */
    public Tulos annaTulos(int i) throws IndexOutOfBoundsException {
        return tulokset.anna(i);
    }
    
    
    /**
     * Hakee kaikki kissan tittelit
     * @param kissa kissa jonka tittelit haetaan
     * @return tietorakenne, jossa viitteet löydettyihin titteleihin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissa1.rekisteroi(); kissa2.rekisteroi();
     * 
     * int id1 = kissa1.getTunnusNro();
     * int id2 = kissa2.getTunnusNro();
     * Titteli titteli1 = new Titteli(id1); rekisteri.lisaaTitteli(titteli1);
     * Titteli titteli2 = new Titteli(id1); rekisteri.lisaaTitteli(titteli2);
     * Titteli titteli3 = new Titteli(id2); rekisteri.lisaaTitteli(titteli3);
     * 
     * List<Titteli> loytyneet;
     * loytyneet = rekisteri.annaTittelit(kissa2);
     * loytyneet.size() === 0;
     * loytyneet = rekisteri.annaTittelit(kissa1);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == titteli1 === true;
     * </pre>
     */
    public List<Titteli> annaTittelit(Kissa kissa) {
        return tittelit.annaTittelit(kissa.getTunnusNro());
    }
    
    
    /**
     * Hakee kaikki kissan tulokset
     * @param kissa kissa jonka tittelit haetaan
     * @return tietorakenne, jossa viitteet löydettyihin tuloksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     * Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissa1.rekisteroi(); kissa2.rekisteroi();
     * 
     * int id1 = kissa1.getTunnusNro();
     * int id2 = kissa2.getTunnusNro();
     * Tulos tulos1 = new Tulos(id1, 1); rekisteri.lisaaTulos(tulos1);
     * Tulos tulos2 = new Tulos(id1, 2); rekisteri.lisaaTulos(tulos2);
     * Tulos tulos3 = new Tulos(id2, 1); rekisteri.lisaaTulos(tulos3);
     * 
     * List<Tulos> loytyneet;
     * loytyneet = rekisteri.annaTulokset(kissa2);
     * loytyneet.size() === 0;
     * loytyneet = rekisteri.annaTulokset(kissa1);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == tulos1 === true;
     * </pre>
     */
    public List<Tulos> annaTulokset(Kissa kissa) {
        return tulokset.annaTulokset(kissa.getTunnusNro());
    }
    
    
    /**
     * Tallettaa kissat, tittelit, nayttelyt ja tulokset tiedostoihin (kesken)
     * @throws SailoException jos talletus epäonnistuu
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            kissat.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            nayttelyt.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            tittelit.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            tulokset.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        
        if (!"".equals(virhe)) throw new SailoException(virhe);
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien jäsenten viitteet 
     * @param hakuehto hakuehto  
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä jäsenistä 
     * @throws SailoException Jos jotakin menee väärin
     * @example 
     * <pre name="test">
     *   #THROWS CloneNotSupportedException, SailoException
     *   alustaRekisteri();
     *   Kissa kissa3 = new Kissa(); kissa3.rekisteroi();
     *   kissa3.setRekNimi("Sisu");
     *   rekisteri.lisaaKissa(kissa3);
     *   Collection<Kissa> loytyneet = rekisteri.etsi("*Sisu*",1);
     *   loytyneet.size() === 0;
     * </pre>
     */ 
    public Collection<Kissa> etsi(String hakuehto, int k) throws SailoException { 
        return kissat.etsi(hakuehto, k); 
    } 
    
    
    /**
     * Poistaa rekisteristä kissan, kissan tittelit ja sen tulokset
     * @param kissa kissa joka poistetaan
     * @return montako kissaa poistettiin
     * @example
     * <pre name="test">
     * #THROWS Exception
     *   alustaRekisteri();
     *   rekisteri.etsi("*",0).size() === 2;
     *   rekisteri.annaTittelit(kissa1).size() === 0;
     *   rekisteri.poista(kissa1) === 1;
     *   rekisteri.etsi("*",0).size() === 1;
     *   rekisteri.annaTittelit(kissa1).size() === 0;
     *   rekisteri.annaTittelit(kissa2).size() === 2;
     * </pre>
     */
    public int poista(Kissa kissa) {
        if (kissa == null) return 0;
        int ret = kissat.poista(kissa.getTunnusNro());
        tittelit.poistaKissanTittelit(kissa.getTunnusNro());
        tulokset.poistaKissanTulokset(kissa.getTunnusNro());
        return ret;
    }
    
    
    /**
     * Poistata tietyn tuloksen
     * @param tulos poistettava tulos
     */
    public void poistaTulos(Tulos tulos) {
        tulokset.poista(tulos);
    }
    
    
    /**
     * Testiohjelma rekisterille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Nayttelyrekisteri rekisteri = new Nayttelyrekisteri();
        
        Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
        
        kissa1.rekisteroi();kissa1.alustaKissa();
        kissa2.rekisteroi();kissa2.alustaKissa();
        
        rekisteri.lisaaKissa(kissa1);
        rekisteri.lisaaKissa(kissa2);
        
        System.out.println("============= Rekisterin testi =================");
        
        for (int i = 0; i < rekisteri.getKissoja(); i++) {
            Kissa katti = rekisteri.annaKissa(i);
            System.out.println("Kissa paikassa: " + i);
            katti.tulosta(System.out);
            System.out.println();
        }
        
        //--------------------------------------------------------
        
        Titteli titteli1 = new Titteli(kissa1.getTunnusNro()), titteli2 = new Titteli(kissa2.getTunnusNro());
        
        titteli1.rekisteroi(); titteli1.alustaTitteli(kissa1.getTunnusNro());
        titteli2.rekisteroi(); titteli2.alustaTitteli(kissa2.getTunnusNro());
        
        rekisteri.lisaaTitteli(titteli1);
        rekisteri.lisaaTitteli(titteli2);
        
        titteli1.tulosta(System.out);
        System.out.println();
        titteli2.tulosta(System.out);
        System.out.println();
        
        //--------------------------------------------------------
        
        Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely();
        nayttely1.rekisteroi(); nayttely1.alustaNayttely();
        nayttely2.rekisteroi(); nayttely2.alustaNayttely();
        
        nayttely1.tulosta(System.out);
        System.out.println();
        nayttely2.tulosta(System.out);
        System.out.println();
        
        //--------------------------------------------------------
        
        Tulos tulos1 = new Tulos();
        Tulos tulos2 = new Tulos();
        
        tulos1.rekisteroi(); tulos2.rekisteroi();
        
        tulos1.alustaTulos(kissa1.getTunnusNro(), nayttely1.getTunnusNro());
        tulos2.alustaTulos(kissa2.getTunnusNro(), nayttely2.getTunnusNro());
        
        tulos1.tulosta(System.out);
        System.out.println();
        tulos2.tulosta(System.out);
        System.out.println();
        
    }
}
