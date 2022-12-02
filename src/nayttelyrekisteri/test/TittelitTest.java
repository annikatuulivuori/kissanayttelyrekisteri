package nayttelyrekisteri.test;
// Generated by ComTest BEGIN
import nayttelyrekisteri.*;
import java.util.*;
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.26 14:45:37 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TittelitTest {



  // Generated by ComTest BEGIN
  /** testLisaa27 */
  @Test
  public void testLisaa27() {    // Tittelit: 27
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(), titteli2 = new Titteli(); 
    assertEquals("From: Tittelit line: 30", 0, tittelit.getLkm()); 
    tittelit.lisaa(titteli1); assertEquals("From: Tittelit line: 31", 1, tittelit.getLkm()); 
    tittelit.lisaa(titteli2); assertEquals("From: Tittelit line: 32", 2, tittelit.getLkm()); 
    tittelit.lisaa(titteli1); assertEquals("From: Tittelit line: 33", 3, tittelit.getLkm()); 
    assertEquals("From: Tittelit line: 34", titteli1, tittelit.anna(0)); 
    assertEquals("From: Tittelit line: 35", titteli2, tittelit.anna(1)); 
    assertEquals("From: Tittelit line: 36", titteli1, tittelit.anna(2)); 
    assertEquals("From: Tittelit line: 37", false, tittelit.anna(1) == titteli1); 
    assertEquals("From: Tittelit line: 38", true, tittelit.anna(1) == titteli2); 
    try {
    assertEquals("From: Tittelit line: 39", titteli1, tittelit.anna(3)); 
    fail("Tittelit: 39 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTittelit52 */
  @Test
  public void testAnnaTittelit52() {    // Tittelit: 52
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(1); tittelit.lisaa(titteli1); 
    Titteli titteli2 = new Titteli(1); tittelit.lisaa(titteli2); 
    Titteli titteli3 = new Titteli(2); tittelit.lisaa(titteli3); 
    List<Titteli> loytyneet; 
    loytyneet = tittelit.annaTittelit(4); 
    assertEquals("From: Tittelit line: 60", 0, loytyneet.size()); 
    loytyneet = tittelit.annaTittelit(1); 
    assertEquals("From: Tittelit line: 62", 2, loytyneet.size()); 
    assertEquals("From: Tittelit line: 63", true, loytyneet.get(0) == titteli1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator77 */
  @Test
  public void testIterator77() {    // Tittelit: 77
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(1); tittelit.lisaa(titteli1); 
    Titteli titteli2 = new Titteli(1); tittelit.lisaa(titteli2); 
    Titteli titteli3 = new Titteli(2); tittelit.lisaa(titteli3); 
    Iterator<Titteli> i2 = tittelit.iterator(); 
    assertEquals("From: Tittelit line: 87", titteli1, i2.next()); 
    assertEquals("From: Tittelit line: 88", titteli2, i2.next()); 
    assertEquals("From: Tittelit line: 89", titteli3, i2.next()); 
    try {
    assertEquals("From: Tittelit line: 90", titteli3, i2.next()); 
    fail("Tittelit: 90 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int[] nrot = { 1, 1, 2} ; 
    for (Titteli titteli:tittelit) {
    assertEquals("From: Tittelit line: 96", nrot[n], titteli.getKissaNro()); 
    n++; 
    }
    assertEquals("From: Tittelit line: 99", 3, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testKorvaaTaiLisaa115 
   * @throws SailoException when error
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testKorvaaTaiLisaa115() throws SailoException,CloneNotSupportedException {    // Tittelit: 115
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(), titteli2 = new Titteli(); 
    titteli1.rekisteroi(); titteli2.rekisteroi(); 
    assertEquals("From: Tittelit line: 121", 0, tittelit.getLkm()); 
    tittelit.korvaaTaiLisaa(titteli1); assertEquals("From: Tittelit line: 122", 1, tittelit.getLkm()); 
    tittelit.korvaaTaiLisaa(titteli2); assertEquals("From: Tittelit line: 123", 2, tittelit.getLkm()); 
    Titteli titteli3 = titteli1.clone(); 
    tittelit.korvaaTaiLisaa(titteli3); assertEquals("From: Tittelit line: 125", 2, tittelit.getLkm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnna146 */
  @Test
  public void testAnna146() {    // Tittelit: 146
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(); 
    Titteli titteli2 = new Titteli(); 
    titteli1.rekisteroi(); titteli2.rekisteroi(); 
    tittelit.lisaa(titteli1); tittelit.lisaa(titteli2); 
    assertEquals("From: Tittelit line: 152", true, tittelit.anna(0) == titteli1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta167 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta167() throws SailoException {    // Tittelit: 167
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(), titteli2 = new Titteli(); 
    titteli1.alustaTitteli(1); 
    titteli2.alustaTitteli(1); 
    String tiedNimi = "testitittelit"; 
    File ftied = new File(tiedNimi+"/tittelit.dat"); 
    ftied.delete(); 
    try {
    tittelit.lueTiedostosta(tiedNimi); 
    fail("Tittelit: 178 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    tittelit.lisaa(titteli1); 
    tittelit.lisaa(titteli2); 
    tittelit.tallenna("testitittelit"); 
    tittelit = new Tittelit(); 
    tittelit.lueTiedostosta(tiedNimi); 
    tittelit.lisaa(titteli2); 
    tittelit.tallenna("testitittelit"); 
    assertEquals("From: Tittelit line: 186", true, ftied.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoistaKissanTittelit246 */
  @Test
  public void testPoistaKissanTittelit246() {    // Tittelit: 246
    Tittelit tittelit = new Tittelit(); 
    Titteli titteli1 = new Titteli(); titteli1.alustaTitteli(2); 
    Titteli titteli2 = new Titteli(); titteli2.alustaTitteli(1); 
    Titteli titteli3 = new Titteli(); titteli3.alustaTitteli(2); 
    Titteli titteli4 = new Titteli(); titteli4.alustaTitteli(1); 
    tittelit.lisaa(titteli1); 
    tittelit.lisaa(titteli2); 
    tittelit.lisaa(titteli3); 
    tittelit.lisaa(titteli4); 
    assertEquals("From: Tittelit line: 256", 2, tittelit.poistaKissanTittelit(2)); assertEquals("From: Tittelit line: 256", 2, tittelit.getLkm()); 
    assertEquals("From: Tittelit line: 257", 2, tittelit.poistaKissanTittelit(1)); assertEquals("From: Tittelit line: 257", 0, tittelit.getLkm()); 
    List<Titteli> t = tittelit.annaTittelit(2); 
    assertEquals("From: Tittelit line: 259", 0, t.size()); 
  } // Generated by ComTest END
}