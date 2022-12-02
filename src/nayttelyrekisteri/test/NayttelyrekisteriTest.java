package nayttelyrekisteri.test;
// Generated by ComTest BEGIN
import nayttelyrekisteri.SailoException;
import java.io.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import nayttelyrekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.20 21:47:05 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NayttelyrekisteriTest {


  // Generated by ComTest BEGIN  // Nayttelyrekisteri: 14
   private Nayttelyrekisteri rekisteri; 
   private Kissa kissa1; 
   private Kissa kissa2; 
   private int kid1; 
   private int kid2; 
   private Titteli titteli1; 
   private Titteli titteli2; 


   public void alustaRekisteri() {
     rekisteri = new Nayttelyrekisteri(); 
     kissa1 = new Kissa(); kissa1.alustaKissa(); kissa1.rekisteroi(); 
     kissa2 = new Kissa(); kissa2.alustaKissa(); kissa2.rekisteroi(); 
     kid1 = kissa1.getTunnusNro(); 
     kid2 = kissa2.getTunnusNro(); 
     titteli2 = new Titteli(kid2); titteli2.alustaTitteli(kid2); 
     titteli1 = new Titteli(kid1); titteli1.alustaTitteli(kid2); 
     try {
     rekisteri.lisaaKissa(kissa1); 
     rekisteri.lisaaKissa(kissa2); 
     rekisteri.lisaaTitteli(titteli1); 
     rekisteri.lisaaTitteli(titteli2); 
     } catch ( Exception e) {
        System.err.println(e.getMessage()); 
     }
   }
  // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta58 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta58() throws SailoException {    // Nayttelyrekisteri: 58
    String hakemisto = "testirekisteri"; 
    File dir = new File(hakemisto); 
    File ftied  = new File(hakemisto+"/kissat.dat"); 
    File fhtied = new File(hakemisto+"/tittelit.dat"); 
    File fttied = new File(hakemisto+"/tulokset.dat"); 
    File fntied = new File(hakemisto+"/nayttelyt.dat"); 
    dir.mkdir(); 
    ftied.delete(); 
    fhtied.delete(); 
    fttied.delete(); 
    fntied.delete(); 
    rekisteri = new Nayttelyrekisteri();  // tiedostoja ei ole, tulee poikkeus 
    try {
    rekisteri.lueTiedostosta(hakemisto); 
    fail("Nayttelyrekisteri: 76 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    alustaRekisteri(); 
    rekisteri.tallenna(); 
    rekisteri = new Nayttelyrekisteri(); 
    rekisteri.lisaaKissa(kissa2); 
    rekisteri.lisaaTitteli(titteli1); 
    rekisteri.tallenna();  // tekee molemmista .bak
    assertEquals("From: Nayttelyrekisteri line: 83", true, ftied.delete()); 
    assertEquals("From: Nayttelyrekisteri line: 84", true, fhtied.delete()); 
    fttied.delete(); 
    fntied.delete(); 
    File fbak = new File(hakemisto+"/kissat.bak"); 
    File fhbak = new File(hakemisto+"/tittelit.bak"); 
    File ftbak = new File(hakemisto+"/tulokset.bak"); 
    File fnbak = new File(hakemisto+"/nayttelyt.bak"); 
    assertEquals("From: Nayttelyrekisteri line: 91", true, fbak.delete()); 
    assertEquals("From: Nayttelyrekisteri line: 92", true, fhbak.delete()); 
    ftbak.delete(); 
    fnbak.delete(); 
    assertEquals("From: Nayttelyrekisteri line: 95", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaaKissa151 
   * @throws SailoException when error
   */
  @Test
  public void testLisaaKissa151() throws SailoException {    // Nayttelyrekisteri: 151
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Kissa kissa1 = new Kissa(), kissa2 = new Kissa(); 
    kissa1.rekisteroi(); kissa2.rekisteroi(); 
    assertEquals("From: Nayttelyrekisteri line: 156", 0, rekisteri.getKissoja()); 
    rekisteri.lisaaKissa(kissa1); assertEquals("From: Nayttelyrekisteri line: 157", 1, rekisteri.getKissoja()); 
    rekisteri.lisaaKissa(kissa2); assertEquals("From: Nayttelyrekisteri line: 158", 2, rekisteri.getKissoja()); 
    rekisteri.lisaaKissa(kissa1); assertEquals("From: Nayttelyrekisteri line: 159", 3, rekisteri.getKissoja()); 
    assertEquals("From: Nayttelyrekisteri line: 160", 3, rekisteri.getKissoja()); 
    assertEquals("From: Nayttelyrekisteri line: 161", kissa1, rekisteri.annaKissa(0)); 
    assertEquals("From: Nayttelyrekisteri line: 162", kissa2, rekisteri.annaKissa(1)); 
    assertEquals("From: Nayttelyrekisteri line: 163", kissa1, rekisteri.annaKissa(2)); 
    try {
    assertEquals("From: Nayttelyrekisteri line: 164", kissa1, rekisteri.annaKissa(3)); 
    fail("Nayttelyrekisteri: 164 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    rekisteri.lisaaKissa(kissa1); assertEquals("From: Nayttelyrekisteri line: 165", 4, rekisteri.getKissoja()); 
    rekisteri.lisaaKissa(kissa1); assertEquals("From: Nayttelyrekisteri line: 166", 5, rekisteri.getKissoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa181 
   * @throws SailoException when error
   */
  @Test
  public void testKorvaaTaiLisaa181() throws SailoException {    // Nayttelyrekisteri: 181
    alustaRekisteri(); 
    assertEquals("From: Nayttelyrekisteri line: 184", 2, rekisteri.etsi("*",0).size()); 
    rekisteri.korvaaTaiLisaa(kissa1); 
    assertEquals("From: Nayttelyrekisteri line: 186", 2, rekisteri.etsi("*",0).size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaTitteli198 */
  @Test
  public void testLisaaTitteli198() {    // Nayttelyrekisteri: 198
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Titteli titteli1 = new Titteli(), titteli2 = new Titteli(); 
    titteli1.rekisteroi(); titteli2.rekisteroi(); 
    assertEquals("From: Nayttelyrekisteri line: 202", 0, rekisteri.getTittelit()); 
    rekisteri.lisaaTitteli(titteli1); assertEquals("From: Nayttelyrekisteri line: 203", 1, rekisteri.getTittelit()); 
    rekisteri.lisaaTitteli(titteli2); assertEquals("From: Nayttelyrekisteri line: 204", 2, rekisteri.getTittelit()); 
    rekisteri.lisaaTitteli(titteli1); assertEquals("From: Nayttelyrekisteri line: 205", 3, rekisteri.getTittelit()); 
    assertEquals("From: Nayttelyrekisteri line: 206", 3, rekisteri.getTittelit()); 
    assertEquals("From: Nayttelyrekisteri line: 207", titteli1, rekisteri.annaTitteli(0)); 
    assertEquals("From: Nayttelyrekisteri line: 208", titteli2, rekisteri.annaTitteli(1)); 
    assertEquals("From: Nayttelyrekisteri line: 209", titteli1, rekisteri.annaTitteli(2)); 
    try {
    assertEquals("From: Nayttelyrekisteri line: 210", titteli1, rekisteri.annaTitteli(3)); 
    fail("Nayttelyrekisteri: 210 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaNayttely222 */
  @Test
  public void testLisaaNayttely222() {    // Nayttelyrekisteri: 222
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely(); 
    nayttely1.rekisteroi(); nayttely2.rekisteroi(); 
    assertEquals("From: Nayttelyrekisteri line: 226", 0, rekisteri.getNayttelyt()); 
    rekisteri.lisaaNayttely(nayttely1); assertEquals("From: Nayttelyrekisteri line: 227", 1, rekisteri.getNayttelyt()); 
    rekisteri.lisaaNayttely(nayttely2); assertEquals("From: Nayttelyrekisteri line: 228", 2, rekisteri.getNayttelyt()); 
    rekisteri.lisaaNayttely(nayttely1); assertEquals("From: Nayttelyrekisteri line: 229", 3, rekisteri.getNayttelyt()); 
    assertEquals("From: Nayttelyrekisteri line: 230", 3, rekisteri.getNayttelyt()); 
    assertEquals("From: Nayttelyrekisteri line: 231", nayttely1, rekisteri.annaNayttely(0)); 
    assertEquals("From: Nayttelyrekisteri line: 232", nayttely2, rekisteri.annaNayttely(1)); 
    assertEquals("From: Nayttelyrekisteri line: 233", nayttely1, rekisteri.annaNayttely(2)); 
    try {
    assertEquals("From: Nayttelyrekisteri line: 234", nayttely1, rekisteri.annaNayttely(3)); 
    fail("Nayttelyrekisteri: 234 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testLisaaTulos245 */
  @Test
  public void testLisaaTulos245() {    // Nayttelyrekisteri: 245
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely(); 
    nayttely1.rekisteroi(); nayttely1.alustaNayttely(); 
    nayttely2.rekisteroi(); nayttely2.alustaNayttely(); 
    Kissa kissa1 = new Kissa(), kissa2 = new Kissa(); 
    kissa1.rekisteroi(); kissa1.alustaKissa(); 
    kissa2.rekisteroi(); kissa2.alustaKissa(); 
    Tulos tulos1 = new Tulos(kissa1.getTunnusNro()); 
    tulos1.rekisteroi(); tulos1.alustaTulos(kissa1.getTunnusNro(), nayttely1.getTunnusNro()); 
    Tulos tulos2 = new Tulos(kissa2.getTunnusNro()); 
    tulos2.rekisteroi(); tulos2.alustaTulos(kissa2.getTunnusNro(), nayttely2.getTunnusNro()); 
    assertEquals("From: Nayttelyrekisteri line: 261", 0, rekisteri.getTulokset()); 
    rekisteri.lisaaTulos(tulos1); 
    rekisteri.lisaaTulos(tulos2); 
    assertEquals("From: Nayttelyrekisteri line: 264", true, rekisteri.annaTulos(0) == tulos1); 
    assertEquals("From: Nayttelyrekisteri line: 265", true, rekisteri.annaTulos(1) == tulos2); 
    try {
    assertEquals("From: Nayttelyrekisteri line: 266", tulos1, rekisteri.annaTulos(3)); 
    fail("Nayttelyrekisteri: 266 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTittelit323 */
  @Test
  public void testAnnaTittelit323() {    // Nayttelyrekisteri: 323
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Kissa kissa1 = new Kissa(), kissa2 = new Kissa(); 
    kissa1.rekisteroi(); kissa2.rekisteroi(); 
    int id1 = kissa1.getTunnusNro(); 
    int id2 = kissa2.getTunnusNro(); 
    Titteli titteli1 = new Titteli(id1); rekisteri.lisaaTitteli(titteli1); 
    Titteli titteli2 = new Titteli(id1); rekisteri.lisaaTitteli(titteli2); 
    Titteli titteli3 = new Titteli(id2); rekisteri.lisaaTitteli(titteli3); 
    List<Titteli> loytyneet; 
    loytyneet = rekisteri.annaTittelit(kissa2); 
    assertEquals("From: Nayttelyrekisteri line: 338", 0, loytyneet.size()); 
    loytyneet = rekisteri.annaTittelit(kissa1); 
    assertEquals("From: Nayttelyrekisteri line: 340", 2, loytyneet.size()); 
    assertEquals("From: Nayttelyrekisteri line: 341", true, loytyneet.get(0) == titteli1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulokset354 */
  @Test
  public void testAnnaTulokset354() {    // Nayttelyrekisteri: 354
    Nayttelyrekisteri rekisteri = new Nayttelyrekisteri(); 
    Kissa kissa1 = new Kissa(), kissa2 = new Kissa(); 
    kissa1.rekisteroi(); kissa2.rekisteroi(); 
    int id1 = kissa1.getTunnusNro(); 
    int id2 = kissa2.getTunnusNro(); 
    Tulos tulos1 = new Tulos(id1, 1); rekisteri.lisaaTulos(tulos1); 
    Tulos tulos2 = new Tulos(id1, 2); rekisteri.lisaaTulos(tulos2); 
    Tulos tulos3 = new Tulos(id2, 1); rekisteri.lisaaTulos(tulos3); 
    List<Tulos> loytyneet; 
    loytyneet = rekisteri.annaTulokset(kissa2); 
    assertEquals("From: Nayttelyrekisteri line: 369", 0, loytyneet.size()); 
    loytyneet = rekisteri.annaTulokset(kissa1); 
    assertEquals("From: Nayttelyrekisteri line: 371", 2, loytyneet.size()); 
    assertEquals("From: Nayttelyrekisteri line: 372", true, loytyneet.get(0) == tulos1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testEtsi422 
   * @throws CloneNotSupportedException when error
   * @throws SailoException when error
   */
  @Test
  public void testEtsi422() throws CloneNotSupportedException, SailoException {    // Nayttelyrekisteri: 422
    alustaRekisteri(); 
    Kissa kissa3 = new Kissa(); kissa3.rekisteroi(); 
    kissa3.setRekNimi("Sisu"); 
    rekisteri.lisaaKissa(kissa3); 
    Collection<Kissa> loytyneet = rekisteri.etsi("*Sisu*",1); 
    assertEquals("From: Nayttelyrekisteri line: 429", 0, loytyneet.size()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testPoista442 
   * @throws Exception when error
   */
  @Test
  public void testPoista442() throws Exception {    // Nayttelyrekisteri: 442
    alustaRekisteri(); 
    assertEquals("From: Nayttelyrekisteri line: 445", 2, rekisteri.etsi("*",0).size()); 
    assertEquals("From: Nayttelyrekisteri line: 446", 0, rekisteri.annaTittelit(kissa1).size()); 
    assertEquals("From: Nayttelyrekisteri line: 447", 1, rekisteri.poista(kissa1)); 
    assertEquals("From: Nayttelyrekisteri line: 448", 1, rekisteri.etsi("*",0).size()); 
    assertEquals("From: Nayttelyrekisteri line: 449", 0, rekisteri.annaTittelit(kissa1).size()); 
    assertEquals("From: Nayttelyrekisteri line: 450", 2, rekisteri.annaTittelit(kissa2).size()); 
  } // Generated by ComTest END
}