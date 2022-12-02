package nayttelyrekisteri;

import java.io.*;
import java.util.*;

/**
 * Näyttelyrekisterin tittelit
 * @author atuul
 * @version 26.4.2021
 *
 */
public class Tittelit implements Iterable<Titteli> {
    private List<Titteli> tittelit      = new ArrayList<Titteli>();
    
    /**
     * Oletusmuodostaja
     */
    public Tittelit() {
        //
    }
    
    
    /**
     * Lisää uuden tittelin tietorakenteeseen. Ottaa tittelin omistukseensa.
     * @param titteli lisättävän tittelin viite
     * @example
     * <pre name="test">
     *      Tittelit tittelit = new Tittelit();
     *      Titteli titteli1 = new Titteli(), titteli2 = new Titteli();
     *      tittelit.getLkm() === 0;
     *      tittelit.lisaa(titteli1); tittelit.getLkm() === 1;
     *      tittelit.lisaa(titteli2); tittelit.getLkm() === 2;
     *      tittelit.lisaa(titteli1); tittelit.getLkm() === 3;
     *      tittelit.anna(0) === titteli1;
     *      tittelit.anna(1) === titteli2;
     *      tittelit.anna(2) === titteli1;
     *      tittelit.anna(1) == titteli1 === false;
     *      tittelit.anna(1) == titteli2 === true;
     *      tittelit.anna(3) === titteli1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaa(Titteli titteli) {
        tittelit.add(titteli);
    }
    
    
    /**
     * Hakee tietyn kissan kaikki tittelit
     * @param tunnusNro kissan tunnusnumero, jolle harrastuksia haetaan
     * @return tietorakenne jossa viitteet löydettyihin titteleihin
     * @example
     * <pre name="test">
     * Tittelit tittelit = new Tittelit();
     * Titteli titteli1 = new Titteli(1); tittelit.lisaa(titteli1);
     * Titteli titteli2 = new Titteli(1); tittelit.lisaa(titteli2);
     * Titteli titteli3 = new Titteli(2); tittelit.lisaa(titteli3);
     * 
     * List<Titteli> loytyneet;
     * loytyneet = tittelit.annaTittelit(4);
     * loytyneet.size() === 0;
     * loytyneet = tittelit.annaTittelit(1);
     * loytyneet.size() === 2;
     * loytyneet.get(0) == titteli1 === true;
     * </pre>
     */
    public List<Titteli> annaTittelit(int tunnusNro) {
        List<Titteli> loydetyt = new ArrayList<Titteli>();
        for (Titteli titteli: tittelit)
            if (titteli.getKissaNro() == tunnusNro) loydetyt.add(titteli);
        return loydetyt;
    }
    
    /**
     * Iteraattori kaikkien tittelien läpikäymiseen
     * @return titteli-iteraattori
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     * Tittelit tittelit = new Tittelit();
     * Titteli titteli1 = new Titteli(1); tittelit.lisaa(titteli1);
     * Titteli titteli2 = new Titteli(1); tittelit.lisaa(titteli2);
     * Titteli titteli3 = new Titteli(2); tittelit.lisaa(titteli3);
     * 
     * Iterator<Titteli> i2 = tittelit.iterator();
     * i2.next() === titteli1;
     * i2.next() === titteli2;
     * i2.next() === titteli3;
     * i2.next() === titteli3; #THROWS NoSuchElementException
     * 
     * int n = 0;
     * int[] nrot = {1, 1, 2};
     * 
     * for (Titteli titteli:tittelit) {
     *     titteli.getKissaNro() === nrot[n]; 
     *     n++;
     * }
     * n === 3;
     * </pre>
     */
    @Override
    public Iterator<Titteli> iterator() {
        return tittelit.iterator();
    }
    
    
    
    /**
     * Korvaa titteli tietorakenteessa. Ottaa tittelin omistukseensa.
     * Etsitään samalla tunnusnumerolla oleva kissa. Jos ei löydy niin
     * lisätään uutena tittelinä
     * @param titteli lisättävän tittelin viite
     * @throws SailoException jos tietorakenne on jo täynnä
     * <pre name="test">
     * #THROWS SailoException,CloneNotSupportedException
     * #PACKAGEIMPORT
     * Tittelit tittelit = new Tittelit();
     * Titteli titteli1 = new Titteli(), titteli2 = new Titteli();
     * titteli1.rekisteroi(); titteli2.rekisteroi();
     * tittelit.getLkm() === 0;
     * tittelit.korvaaTaiLisaa(titteli1); tittelit.getLkm() === 1;
     * tittelit.korvaaTaiLisaa(titteli2); tittelit.getLkm() === 2;
     * Titteli titteli3 = titteli1.clone();
     * tittelit.korvaaTaiLisaa(titteli3); tittelit.getLkm() === 2;
     * </pre>
     */ 
    public void korvaaTaiLisaa(Titteli titteli) throws SailoException {
        int id = titteli.getTunnusNro();
        for (int i = 0; i < getLkm(); i++) {
            if (tittelit.get(i).getTunnusNro() == id) {
                tittelit.set(i, titteli);
                return;
            }
        }
        lisaa(titteli);
    }
    
    
    /**
     * Palauttaa viitteen i:teen titteliin
     * @param i monennenko tittelin viite halutaan
     * @return viite titteliin, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei sallittu
     * @example
     * <pre name="test">
     * Tittelit tittelit = new Tittelit();
     * Titteli titteli1 = new Titteli();
     * Titteli titteli2 = new Titteli();
     * titteli1.rekisteroi(); titteli2.rekisteroi();
     * tittelit.lisaa(titteli1); tittelit.lisaa(titteli2);
     * tittelit.anna(0) == titteli1 === true;
     * </pre>
     */
    public Titteli anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || tittelit.size() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return tittelit.get(i);
    }
    
    
    /**
     * Lukee tittelit tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Tittelit tittelit = new Tittelit();
     *  Titteli titteli1 = new Titteli(), titteli2 = new Titteli();
     *  titteli1.alustaTitteli(1);
     *  titteli2.alustaTitteli(1);
     *  String tiedNimi = "testitittelit";
     *  File ftied = new File(tiedNimi+"/tittelit.dat");
     *  ftied.delete();
     *  tittelit.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  tittelit.lisaa(titteli1);
     *  tittelit.lisaa(titteli2);
     *  tittelit.tallenna("testitittelit");
     *  tittelit = new Tittelit();            
     *  tittelit.lueTiedostosta(tiedNimi);  
     *  tittelit.lisaa(titteli2);
     *  tittelit.tallenna("testitittelit");
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/tittelit.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Titteli titteli = new Titteli();
                titteli.parse(s);
                lisaa(titteli);
            }
        } catch (FileNotFoundException e){
            throw new SailoException("Ei osata vielä lukea tiedostosta " + nimi);
        } 

    }
    
    
    /**
     * Tallentaa tittelit tiedostoon
     * Tiedoston muoto:
     * <pre>
     *  1 |1       |Premior                     |28092019 |03102019
     *  2 |1       |International Premior       |06102019 |22102019
     *  3 |1       |Grand International Premior |31102020 |10112020
     * </pre>
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File ftied = new File(tiednimi + "/tittelit.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Titteli titteli = anna(i);
                fo.println(titteli.toString());
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa näyttelyrekisterissä olevien tittelien lukumäärän
     * @return näyttelyiden lukumäärä
     */
    public int getLkm() {
        return tittelit.size();
    }
    
    
    /**
     * Poistaa kaikki tietyn kissan tittelit
     * @param tNro viite siihen, mihin kissaan liittyvät poistetaan
     * @return montako poistettiin
     * @example
     * <pre name="test">
     *  Tittelit tittelit = new Tittelit();
     *  Titteli titteli1 = new Titteli(); titteli1.alustaTitteli(2);
     *  Titteli titteli2 = new Titteli(); titteli2.alustaTitteli(1);
     *  Titteli titteli3 = new Titteli(); titteli3.alustaTitteli(2); 
     *  Titteli titteli4 = new Titteli(); titteli4.alustaTitteli(1); 
     *  tittelit.lisaa(titteli1);
     *  tittelit.lisaa(titteli2);
     *  tittelit.lisaa(titteli3);
     *  tittelit.lisaa(titteli4);
     *  tittelit.poistaKissanTittelit(2) === 2;  tittelit.getLkm() === 2;
     *  tittelit.poistaKissanTittelit(1) === 2;  tittelit.getLkm() === 0;
     *  List<Titteli> t = tittelit.annaTittelit(2);
     *  t.size() === 0; 
     * </pre>
     */
    public int poistaKissanTittelit(int tNro) {
        int n = 0;
        for (Iterator<Titteli> it = tittelit.iterator(); it.hasNext();) {
            Titteli titteli = it.next();
            if (titteli.getKissaNro() == tNro ) {
                it.remove();
                n++;
            }
        }
        return n;
    }

    
    
    /**
     * Testiohjelma titteleille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Tittelit tittelit = new Tittelit();
        
        try {
            tittelit.lueTiedostosta("nayttelyrekisteri");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        Titteli titteli1 = new Titteli(1), titteli2 = new Titteli(2);
        
        titteli1.rekisteroi();
        titteli1.alustaTitteli(1);
        
        titteli2.rekisteroi();
        titteli2.alustaTitteli(1);
        
        tittelit.lisaa(titteli1);
        tittelit.lisaa(titteli2);
        
        System.out.println("============= Tittelit testi =================");
        
        for (int i = 0; i < tittelit.getLkm(); i++) {
            Titteli titteli = tittelit.anna(i);
            System.out.println("Kissan numero: " + i);
            titteli.tulosta(System.out);
        }
        
        try {
            tittelit.tallenna("nayttelyrekisteri");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
