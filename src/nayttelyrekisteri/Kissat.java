package nayttelyrekisteri;

import java.io.*;
import java.util.*;

import fi.jyu.mit.ohj2.WildChars;

/**
 * Näyttelyrekisterin kissat.
 * Osaa mm. lisätä uuden kissan
 * 
 * @author atuul
 * @version 20.4.2021
 *
 */
public class Kissat {

    private static final int MAX_KISSOJA    = 5;
    private int              lkm            = 0;
    private Kissa            kissat[]       = new Kissa[MAX_KISSOJA];
    
    
    /**
     * Oletusmuodostaja
     */
    public Kissat() {
        
    }
    
    
    /**
     * Lisää uuden kissan tietorakenteeseen. Ottaa kissan omistukseensa.
     * @param kissa lisättävän kissan viite. (Huom! tietorakenne muuttuu omistajaksi)
     * @example
     * <pre name="test">
     * Kissat kissat = new Kissat();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissat.getLkm() === 0;
     * kissat.lisaa(kissa1); kissat.getLkm() === 1;
     * kissat.lisaa(kissa2); kissat.getLkm() === 2;
     * kissat.lisaa(kissa1); kissat.getLkm() === 3;
     * kissat.anna(0) === kissa1;
     * kissat.anna(1) === kissa2;
     * kissat.anna(2) === kissa1;
     * kissat.anna(1) == kissa1 === false;
     * kissat.anna(1) == kissa2 === true;
     * kissat.anna(3) === kissa1; #THROWS IndexOutOfBoundsException
     * kissat.lisaa(kissa1); kissat.getLkm() === 4;
     * kissat.lisaa(kissa1); kissat.getLkm() === 5;
     * </pre>
     */
    public void lisaa(Kissa kissa) {
        if (lkm >= kissat.length) {
            kissat = Arrays.copyOf(kissat, kissat.length+5);
        }
        kissat[lkm++] = kissa;
    }
    
    
    /**
     * Korvaa kissan tietorakenteessa. Ottaa kissan omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kissa. Jos ei löydy niin
     * lisätään uutena kissana
     * @param kissa lisättävän kissan viite
     * @throws SailoException jos tietorakenne on jo täynnä
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Kissat kissat = new Kissat();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     * kissa1.rekisteroi(); kissa2.rekisteroi();
     * kissat.getLkm() === 0;
     * kissat.korvaaTaiLisaa(kissa1); kissat.getLkm() === 1;
     * kissat.korvaaTaiLisaa(kissa2); kissat.getLkm() === 2;
     * Kissa kissa3 = kissa1.clone();
     * kissat.korvaaTaiLisaa(kissa3); kissat.getLkm() === 2;
     * </pre>
     */ 
    public void korvaaTaiLisaa(Kissa kissa) throws SailoException {
        int id = kissa.getTunnusNro();
        for (int i = 0; i < lkm; i++) {
            if (kissat[i].getTunnusNro() == id) {
                kissat[i] = kissa;
                return;
            }
        }
        lisaa(kissa);
    }
    
    
    /**
     * Palauttaa viitteen i:teen kissaan
     * @param i monennenko kissan viite halutaan
     * @return viite kissaan, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallittu
     */
    public Kissa anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return kissat[i];
    }
    
    
    /**
     * Lukee kissat tiedostosta (kesken)
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Kissat kissat = new Kissat();
     *  Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
     *  kissa1.alustaKissa();
     *  kissa2.alustaKissa();
     *  String tiedNimi = "testikissat";
     *  File ftied = new File(tiedNimi+"/kissat.dat");
     *  ftied.delete();
     *  kissat.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  kissat.lisaa(kissa1);
     *  kissat.lisaa(kissa2);
     *  kissat.tallenna("testikissat");
     *  kissat = new Kissat();            
     *  kissat.lueTiedostosta(tiedNimi);  
     *  kissat.lisaa(kissa2);
     *  kissat.tallenna("testikissat");
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/kissat.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Kissa kissa = new Kissa();
                kissa.parse(s);
                lisaa(kissa);
            }
        } catch (FileNotFoundException e){
            throw new SailoException("Ei osata vielä lukea tiedostosta " + nimi);
        } 
    }
    
    
    /**
     * Palauttaa näyttelyrekisterissä olevien kissojen lukumäärän
     * @return kissojen lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * Tallentaa kissat tiedostoon
     * Tiedoston muoto:
     * <pre>
     *      1|Electric India|FI*Siniidan|Tofu|uros|22092018|Norjalainen metsäkissa|NFO ds 03 22|1822560
     *      2|Black Halo|FI*Siniidan|Doris|naaras|26122019|Norjalainen metsäkissa |NFO n 09|2027724
     *      3|Sisu||Sisu|naaras|22102017|Kotikissa|HSC n 09||
     * </pre>
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File ftied = new File(tiednimi + "/kissat.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Kissa kissa = anna(i);
                fo.println(kissa.toString());
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        }
    }
    
    
    /** 
     * Palauttaa "taulukossa" hakuehtoon vastaavien kissojen viitteet 
     * @param hakuehto hakuehto 
     * @param k etsittävän kentän indeksi  
     * @return tietorakenteen löytyneistä kissoista 
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * #import java.util.*;
     * 
     *   Kissat kissat = new Kissat();
     *   Kissa kissa1 = new Kissa(); kissa1.parse("1|Electric India|FI*Siniidan|Tofu|"); 
     *   Kissa kissa2 = new Kissa(); kissa2.parse("2|Black Halo|FI*Siniidan|Doris|"); 
     *   Kissa kissa3 = new Kissa(); kissa3.parse("3|Sisu||Sisu|"); 
     *   kissat.lisaa(kissa1); kissat.lisaa(kissa2); kissat.lisaa(kissa3);
     *   List<Kissa> loytyneet;  
     *   loytyneet = (List<Kissa>)kissat.etsi("*s*",1);  
     *   loytyneet.size() === 2;  
     *   loytyneet.get(0) == kissa1 === true;  
     *   loytyneet.get(1) == kissa1 === false; 
     * </pre>
     */
    public Collection<Kissa> etsi(String hakuehto, int k) { 
        List<Kissa> loytyneet = new ArrayList<Kissa>(); 
        int hk = k;
        if (hk < 0) hk = 1;
        for (int i = 0; i < lkm; i++) {
            String sisalto = kissat[i].anna(k);
            if (WildChars.onkoSamat(sisalto, hakuehto))
                loytyneet.add(kissat[i]);
        }
        Collections.sort(loytyneet, new Kissa.Vertailija(hk));
        return loytyneet; 
    }
    
    
    /**
     * Poistaa kissan, jolla on valittu tunnusnumero
     * @param id poistettavan kissan tunnusnumero
     * @return 1 jos poistettiin, 0 jos ei löydy
     * @example 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kissat kissat = new Kissat();
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa(), kissa3 = new Kissa(); 
     * kissa1.rekisteroi(); kissa2.rekisteroi(); kissa3.rekisteroi(); 
     * int id1 = kissa1.getTunnusNro(); 
     * kissat.lisaa(kissa1); kissat.lisaa(kissa2); kissat.lisaa(kissa3); 
     * kissat.poista(id1+1) === 1; 
     * kissat.getLkm() === 2; 
     * kissat.poista(id1) === 1; kissat.getLkm() === 1; 
     * kissat.poista(id1+3) === 0; kissat.getLkm() === 1; 
     * </pre> 
     */
    public int poista(int id) {
        int ind = etsiId(id);
        if (ind < 0) return 0;
        lkm--;
        for (int i = ind; i < lkm; i++) {
            kissat[i] = kissat[i+1];
        }
        kissat[lkm] = null;
        return 1;
    }
    
    
    /** 
     * Etsii kissan id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return kissa jolla etsittävä id tai null 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kissat kissat = new Kissat(); 
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa(), kissa3 = new Kissa(); 
     * kissa1.rekisteroi(); kissa2.rekisteroi(); kissa3.rekisteroi(); 
     * int id1 = kissa1.getTunnusNro(); 
     * kissat.lisaa(kissa1); kissat.lisaa(kissa2); kissat.lisaa(kissa3); 
     * kissat.annaId(id1  ) == kissa1 === true; 
     * kissat.annaId(id1+1) == kissa2 === true; 
     * kissat.annaId(id1+2) == kissa3 === true; 
     * </pre> 
     */ 
    public Kissa annaId(int id) { 
        for (Kissa kissa : kissat) { 
            if (id == kissa.getTunnusNro()) return kissa; 
        } 
        return null; 
    } 


    /** 
     * Etsii jäsenen id:n perusteella 
     * @param id tunnusnumero, jonka mukaan etsitään 
     * @return löytyneen jäsenen indeksi tai -1 jos ei löydy 
     * <pre name="test"> 
     * #THROWS SailoException  
     * Kissat kissat = new Kissat(); 
     * Kissa kissa1 = new Kissa(), kissa2 = new Kissa(), kissa3 = new Kissa(); 
     * kissa1.rekisteroi(); kissa2.rekisteroi(); kissa3.rekisteroi(); 
     * int id1 = kissa1.getTunnusNro(); 
     * kissat.lisaa(kissa1); kissat.lisaa(kissa2); kissat.lisaa(kissa3); 
     * kissat.etsiId(id1+1) === 1; 
     * kissat.etsiId(id1+2) === 2; 
     * </pre> 
     */ 
    public int etsiId(int id) { 
        for (int i = 0; i < lkm; i++) 
            if (id == kissat[i].getTunnusNro()) return i; 
        return -1; 
    } 
    
    
    /**
     * Testiohjelma kissoille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Kissat kissat = new Kissat();
        
        try {
            kissat.lueTiedostosta("nayttelyrekisteri");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        
        Kissa kissa1 = new Kissa(), kissa2 = new Kissa();
        
        kissa1.rekisteroi();
        kissa1.alustaKissa();
        
        kissa2.rekisteroi();
        kissa2.alustaKissa();
        
        kissat.lisaa(kissa1);
        kissat.lisaa(kissa2);

        System.out.println("============= Kissat testi =================");
        
        for (int i = 0; i < kissat.getLkm(); i++) {
            Kissa katti = kissat.anna(i);
            System.out.println("Kissan numero: " + i);
            katti.tulosta(System.out);
        }
        
        try {
            kissat.tallenna("nayttelyrekisteri");
        } catch (SailoException e) {
            e.printStackTrace();
        }
        
    }
    
}
