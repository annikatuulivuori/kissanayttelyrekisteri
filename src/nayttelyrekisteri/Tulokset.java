package nayttelyrekisteri;

import java.io.*;
import java.util.*;

/**
 * @author atuul
 * @version 26.4.2021
 *
 */
public class Tulokset implements Iterable<Tulos> {
    private ArrayList<Tulos> tulokset   = new ArrayList<Tulos>();
    
    /**
     * Oletusmuodostaja
     */
    public Tulokset() {
        
    }
    
    
    /**
     * Lisää uuden tuloksen tietorakenteeseen. Ottaa tuloksen omistukseensa.
     * @param tulos lisättävän tuloksen viite
     * @example
     * <pre name="test">
     *      Tulokset tulokset = new Tulokset();
     *      Tulos tulos1 = new Tulos(), tulos2 = new Tulos();
     *      tulokset.getLkm() === 0;
     *      tulokset.lisaa(tulos1); tulokset.getLkm() === 1;
     *      tulokset.lisaa(tulos2); tulokset.getLkm() === 2;
     *      tulokset.lisaa(tulos1); tulokset.getLkm() === 3;
     *      tulokset.anna(0) === tulos1;
     *      tulokset.anna(1) === tulos2;
     *      tulokset.anna(2) === tulos1;
     *      tulokset.anna(1) == tulos1 === false;
     *      tulokset.anna(1) == tulos2 === true;
     *      tulokset.anna(3) === tulos1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaa(Tulos tulos) {
        tulokset.add(tulos);
    }
    
    
    /**
     * Iteraattori kaikkien tulosten läpikäymiseen
     * @return titteli-iteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Tulokset tulokset = new Tulokset();
     * Tulos tulos1 = new Tulos(1); tulokset.lisaa(tulos1);
     * Tulos tulos2 = new Tulos(1); tulokset.lisaa(tulos2);
     * Tulos tulos3 = new Tulos(2); tulokset.lisaa(tulos3);
     * 
     * 
     * Iterator<Tulos> i2 = tulokset.iterator();
     * i2.next() === tulos1;
     * i2.next() === tulos2;
     * i2.next() === tulos3;
     * i2.next() === tulos3; #THROWS NoSuchElementException
     * 
     * int n = 0;
     * int[] nrot = {1, 1, 2};
     * 
     * for (Tulos tulos:tulokset) {
     *     tulos.getKissaNro() === nrot[n]; 
     *     n++;
     * }
     * n === 3;
     * </pre>
     */
    @Override
    public Iterator<Tulos> iterator() {
        return tulokset.iterator();
    }
    
    
    /**
     * Hakee tietyn kissan kaikki tittelit
     * @param tunnusNro kissan tunnusnumero, jolle harrastuksia haetaan
     * @return tietorakenne jossa viitteet löydettyihin titteleihin
     * @example
     * <pre name="test">
     * Tulokset tulokset = new Tulokset();
     * Tulos tulos1 = new Tulos(1); tulokset.lisaa(tulos1);
     * Tulos tulos2 = new Tulos(1); tulokset.lisaa(tulos2);
     * Tulos tulos3 = new Tulos(2); tulokset.lisaa(tulos3);
     * 
     * List<Tulos> loytyneet;
     * loytyneet = tulokset.annaTulokset(4);
     * loytyneet.size() === 0;
     * loytyneet = tulokset.annaTulokset(1);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == tulos1 === true;
     * </pre>
     */
    public List<Tulos> annaTulokset(int tunnusNro) {
        List<Tulos> loydetyt = new ArrayList<Tulos>();
        for (Tulos tulos: tulokset)
            if (tulos.getKissaNro() == tunnusNro) loydetyt.add(tulos);
        return loydetyt;
    }
    
    
    /**
     * Palauttaa viitteen i:teen tulokseen
     * @param i monennenko tulokseen viite halutaan
     * @return viite tulokseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei sallittu
     * Tulokset tulokset = new Tulokset();
     * Tulos tulos1 = new Tulos(1, 1);
     * Tulos tulos2 = new Titteli(2, 2);
     * tulos1.rekisteroi(); tulos2.rekisteroi();
     * int ind1 = tulos1.getTunnusNro();
     * int ind2 = tulos2.getTunnusNro();
     * ind2 == ind1+1 === true;
     */
    public Tulos anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || tulokset.size() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return tulokset.get(i);
    }
    
    
    /**
     * Lukee tulokset tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Tulokset tulokset = new Tulokset();
     *  Tulos tulos1 = new Tulos(), tulos2 = new Tulos();
     *  tulos1.alustaTulos(1,1);
     *  tulos2.alustaTulos(1,1);
     *  String tiedNimi = "testitulokset";
     *  File ftied = new File(tiedNimi+"/tulokset.dat");
     *  ftied.delete();
     *  tulokset.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  tulokset.lisaa(tulos1);
     *  tulokset.lisaa(tulos2);
     *  tulokset.tallenna("testitulokset");
     *  tulokset = new Tulokset();            
     *  tulokset.lueTiedostosta(tiedNimi);  
     *  tulokset.lisaa(tulos2);
     *  tulokset.tallenna("testitulokset");
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/tulokset.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Tulos tulos = new Tulos();
                tulos.parse(s);
                lisaa(tulos);
            }
        } catch (FileNotFoundException e){
            throw new SailoException("Ei osata vielä lukea tiedostosta " + nimi);
        } 

    }
    
    
    /**
     * Tallentaa tulkokset tiedostoon
     * Tiedoston muoto:
     * <pre>
     *  1 |1        |1           |CAGPIB   |Saarela Veikko  
     *  2 |1        |2           |CAPS     |Wikström Bjarne 
     *  3 |2        |1           |CAC      |Turpeinen Riikka
     *  4 |2        |2           |CAC      |Nyman Pia
     *  5 |2        |3           |EX1 NOM  |Fonsen Mira
     *  6 |2        |4           |EX1      |Turpeinen Riikka
     * </pre>
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File ftied = new File(tiednimi + "/tulokset.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Tulos tulos = anna(i);
                fo.println(tulos.toString());
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa näyttelyrekisterissä olevien tuloksien lukumäärän
     * @return näyttelyiden lukumäärä
     */
    public int getLkm() {
        return tulokset.size();
    }
    
    
    /**
     * Poistaa kaikki tietyn kissan tulokset
     * @param tNro viite siihen, mihin kissaan liittyvät poistetaan
     * @return montako poistettiin
     * @example
     * <pre name="test">
     *  Tulokset tulokset = new Tulokset();
     *  Tulos tulos1 = new Tulos(); tulos1.alustaTulos(2, 1);
     *  Tulos tulos2 = new Tulos(); tulos2.alustaTulos(1, 1);
     *  Tulos tulos3 = new Tulos(); tulos3.alustaTulos(2, 2); 
     *  Tulos tulos4 = new Tulos(); tulos4.alustaTulos(1, 2); 
     *  tulokset.lisaa(tulos1);
     *  tulokset.lisaa(tulos2);
     *  tulokset.lisaa(tulos3);
     *  tulokset.lisaa(tulos4);
     *  tulokset.poistaKissanTulokset(2) === 2;  tulokset.getLkm() === 2;
     *  tulokset.poistaKissanTulokset(1) === 2;  tulokset.getLkm() === 0;
     *  List<Tulos> t = tulokset.annaTulokset(2);
     *  t.size() === 0; 
     * </pre>
     */
    public int poistaKissanTulokset(int tNro) {
        int n = 0;
        for (Iterator<Tulos> it = tulokset.iterator(); it.hasNext();) {
            Tulos tulos = it.next();
            if (tulos.getKissaNro() == tNro ) {
                it.remove();
                n++;
            }
        }
        return n;
    }
    
    
    /**
     * Poistaa valitun tuloksen
     * @param tulos poistettava tulos
     * @return tosi jos löytyi poistettava tulos
     */
    public boolean poista(Tulos tulos) {
        boolean ret = tulokset.remove(tulos);
        return ret;
    }
    
    
    /**
     * Testiohjelma tuloksille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tulokset tulokset = new Tulokset();
        
        try {
            tulokset.lueTiedostosta("nayttelyrekisteri");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        Tulos tulos1 = new Tulos(), tulos2 = new Tulos();
              
        
        tulos1.rekisteroi();
        tulos1.alustaTulos(1, 1);
        
        tulos2.rekisteroi();
        tulos2.alustaTulos(2, 3);
        
        tulokset.lisaa(tulos1);
        tulokset.lisaa(tulos2);
        
        System.out.println("============= Tulokset testi =================");
        
        for (int i = 0; i < tulokset.getLkm(); i++) {
            System.out.println(tulokset.anna(i));
        }
        
        try {
            tulokset.tallenna("nayttelyrekisteri");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
