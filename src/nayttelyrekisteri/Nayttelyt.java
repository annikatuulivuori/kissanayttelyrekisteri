package nayttelyrekisteri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Näyttelyrekisterin näyttelyt
 * @author atuul
 * @version 26.4.2021
 *
 */
public class Nayttelyt {
    
    private ArrayList<Nayttely> nayttelyt   = new ArrayList<Nayttely>();
    
    
    /**
     * Oletusmudostaja
     */
    public Nayttelyt() {
        
    }
    
    
    /**
     * Lisää uuden näyttelyn tietorakenteeseen. Ottaa nayttelyn omistukseensa.
     * @param nayttely lisättävän näyttelyn viite (Huom! tietorakenne muuttuu omistajaksi)
     * @example
     * <pre name="test">
     *      Nayttelyt nayttelyt = new Nayttelyt();
     *      Nayttely show1 = new Nayttely(), show2 = new Nayttely();
     *      nayttelyt.getLkm() === 0;
     *      nayttelyt.lisaa(show1); nayttelyt.getLkm() === 1;
     *      nayttelyt.lisaa(show2); nayttelyt.getLkm() === 2;
     *      nayttelyt.lisaa(show1); nayttelyt.getLkm() === 3;
     *      nayttelyt.anna(0) === show1;
     *      nayttelyt.anna(1) === show2;
     *      nayttelyt.anna(2) === show1;
     *      nayttelyt.anna(1) == show1 === false;
     *      nayttelyt.anna(1) == show2 === true;
     *      nayttelyt.anna(3) === show1; #THROWS IndexOutOfBoundsException
     * </pre>
     */
    public void lisaa(Nayttely nayttely) {
        nayttelyt.add(nayttely);
    }
    
    
    /**
     * Palauttaa viitteen i:teen näyttelyyn
     * @param i monennenko näyttelyn viite halutaan
     * @return viite näyttelyyn, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei sallittu
     */
    public Nayttely anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || nayttelyt.size() <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return nayttelyt.get(i);
    }
    
    
    /**
     * Antaa halutun näyttelyn tunnusnumeron perusteella
     * @param tunnusNro haluttu tunnusnumero
     * @return haluttu näyttely
     */
    public Nayttely getNayttelyNro(int tunnusNro) {
        for (Nayttely n: nayttelyt) {
            if (n.getTunnusNro() == tunnusNro) return n;
        }
        return null;
    }
    
    
    /**
     * Lukee näyttelyt tiedostosta
     * @param hakemisto tiedoston hakemisto
     * @throws SailoException jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException 
     * #import java.io.File;
     * 
     *  Nayttelyt nayttelyt = new Nayttelyt();
     *  Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely();
     *  nayttely1.alustaNayttely();
     *  nayttely2.alustaNayttely();
     *  String tiedNimi = "testinayttelyt";
     *  File ftied = new File(tiedNimi+"/nayttelyt.dat");
     *  ftied.delete();
     *  nayttelyt.lueTiedostosta(tiedNimi); #THROWS SailoException
     *  nayttelyt.lisaa(nayttely1);
     *  nayttelyt.lisaa(nayttely2);
     *  nayttelyt.tallenna("testinayttelyt");
     *  nayttelyt = new Nayttelyt();            
     *  nayttelyt.lueTiedostosta(tiedNimi);  
     *  nayttelyt.lisaa(nayttely2);
     *  nayttelyt.tallenna("testinayttelyt");
     *  ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/nayttelyt.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = "";
                s = fi.nextLine();
                Nayttely nayttely = new Nayttely();
                nayttely.parse(s);
                lisaa(nayttely);
            }
        } catch (FileNotFoundException e){
            throw new SailoException("Ei osata vielä lukea tiedostosta " + nimi);
        } 

    }
    
    
    /**
     * Tallentaa näyttelyt tiedostoon
     * Tiedoston muoto:
     * <pre>
     * 1 |31102020 |PIROK
     * 2 |31102020 |PIROK
     * 3 |27092020 |KES-KIS
     * </pre>
     * @param tiednimi tallennettavan tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File ftied = new File(tiednimi + "/nayttelyt.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < getLkm(); i++) {
                Nayttely nayttely = anna(i);
                fo.println(nayttely.toString());
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Tiedosto " + ftied.getName() + " ei aukea");
        }
    }
    
    
    /**
     * Palauttaa näyttelyrekisterissä olevien näyttelyiden lukumäärän
     * @return näyttelyiden lukumäärä
     */
    public int getLkm() {
        return nayttelyt.size();
    }
    
    
    /**
     * Testiohjelma näyttelyille
     * @param args ei käytössä
     */
    public static void main(String args[]) {
        Nayttelyt nayttelyt = new Nayttelyt();
        
        try {
            nayttelyt.lueTiedostosta("nayttelyrekisteri");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        Nayttely show1 = new Nayttely(), show2 = new Nayttely();
        
        show1.rekisteroi();
        show1.alustaNayttely();
        
        show2.rekisteroi();
        show2.alustaNayttely();
        
        nayttelyt.lisaa(show1);
        nayttelyt.lisaa(show2);
        
        System.out.println("============= Näyttelyt testi =================");
        
        for (int i = 0; i < nayttelyt.getLkm(); i++) {
            Nayttely show = nayttelyt.anna(i);
            System.out.println("Kissan numero: " + i);
            show.tulosta(System.out);
        }
        
        
        try {
            nayttelyt.tallenna("nayttelyrekisteri");
        } catch (SailoException e) {
            e.printStackTrace();
        }
    }
}
