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
 * @version 2021.04.26 14:47:24 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TuloksetTest {



  // Generated by ComTest BEGIN
  /** testLisaa26 */
  @Test
  public void testLisaa26() {    // Tulokset: 26
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(), tulos2 = new Tulos(); 
    assertEquals("From: Tulokset line: 29", 0, tulokset.getLkm()); 
    tulokset.lisaa(tulos1); assertEquals("From: Tulokset line: 30", 1, tulokset.getLkm()); 
    tulokset.lisaa(tulos2); assertEquals("From: Tulokset line: 31", 2, tulokset.getLkm()); 
    tulokset.lisaa(tulos1); assertEquals("From: Tulokset line: 32", 3, tulokset.getLkm()); 
    assertEquals("From: Tulokset line: 33", tulos1, tulokset.anna(0)); 
    assertEquals("From: Tulokset line: 34", tulos2, tulokset.anna(1)); 
    assertEquals("From: Tulokset line: 35", tulos1, tulokset.anna(2)); 
    assertEquals("From: Tulokset line: 36", false, tulokset.anna(1) == tulos1); 
    assertEquals("From: Tulokset line: 37", true, tulokset.anna(1) == tulos2); 
    try {
    assertEquals("From: Tulokset line: 38", tulos1, tulokset.anna(3)); 
    fail("Tulokset: 38 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator50 */
  @Test
  public void testIterator50() {    // Tulokset: 50
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(1); tulokset.lisaa(tulos1); 
    Tulos tulos2 = new Tulos(1); tulokset.lisaa(tulos2); 
    Tulos tulos3 = new Tulos(2); tulokset.lisaa(tulos3); 
    Iterator<Tulos> i2 = tulokset.iterator(); 
    assertEquals("From: Tulokset line: 61", tulos1, i2.next()); 
    assertEquals("From: Tulokset line: 62", tulos2, i2.next()); 
    assertEquals("From: Tulokset line: 63", tulos3, i2.next()); 
    try {
    assertEquals("From: Tulokset line: 64", tulos3, i2.next()); 
    fail("Tulokset: 64 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int[] nrot = { 1, 1, 2} ; 
    for (Tulos tulos:tulokset) {
    assertEquals("From: Tulokset line: 70", nrot[n], tulos.getKissaNro()); 
    n++; 
    }
    assertEquals("From: Tulokset line: 73", 3, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTulokset87 */
  @Test
  public void testAnnaTulokset87() {    // Tulokset: 87
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(1); tulokset.lisaa(tulos1); 
    Tulos tulos2 = new Tulos(1); tulokset.lisaa(tulos2); 
    Tulos tulos3 = new Tulos(2); tulokset.lisaa(tulos3); 
    List<Tulos> loytyneet; 
    loytyneet = tulokset.annaTulokset(4); 
    assertEquals("From: Tulokset line: 95", 0, loytyneet.size()); 
    loytyneet = tulokset.annaTulokset(1); 
    assertEquals("From: Tulokset line: 97", 2, loytyneet.size()); 
    assertEquals("From: Tulokset line: 98", true, loytyneet.get(0) == tulos1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta134 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta134() throws SailoException {    // Tulokset: 134
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(), tulos2 = new Tulos(); 
    tulos1.alustaTulos(1,1); 
    tulos2.alustaTulos(1,1); 
    String tiedNimi = "testitulokset"; 
    File ftied = new File(tiedNimi+"/tulokset.dat"); 
    ftied.delete(); 
    try {
    tulokset.lueTiedostosta(tiedNimi); 
    fail("Tulokset: 145 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    tulokset.lisaa(tulos1); 
    tulokset.lisaa(tulos2); 
    tulokset.tallenna("testitulokset"); 
    tulokset = new Tulokset(); 
    tulokset.lueTiedostosta(tiedNimi); 
    tulokset.lisaa(tulos2); 
    tulokset.tallenna("testitulokset"); 
    assertEquals("From: Tulokset line: 153", true, ftied.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPoistaKissanTulokset216 */
  @Test
  public void testPoistaKissanTulokset216() {    // Tulokset: 216
    Tulokset tulokset = new Tulokset(); 
    Tulos tulos1 = new Tulos(); tulos1.alustaTulos(2, 1); 
    Tulos tulos2 = new Tulos(); tulos2.alustaTulos(1, 1); 
    Tulos tulos3 = new Tulos(); tulos3.alustaTulos(2, 2); 
    Tulos tulos4 = new Tulos(); tulos4.alustaTulos(1, 2); 
    tulokset.lisaa(tulos1); 
    tulokset.lisaa(tulos2); 
    tulokset.lisaa(tulos3); 
    tulokset.lisaa(tulos4); 
    assertEquals("From: Tulokset line: 226", 2, tulokset.poistaKissanTulokset(2)); assertEquals("From: Tulokset line: 226", 2, tulokset.getLkm()); 
    assertEquals("From: Tulokset line: 227", 2, tulokset.poistaKissanTulokset(1)); assertEquals("From: Tulokset line: 227", 0, tulokset.getLkm()); 
    List<Tulos> t = tulokset.annaTulokset(2); 
    assertEquals("From: Tulokset line: 229", 0, t.size()); 
  } // Generated by ComTest END
}