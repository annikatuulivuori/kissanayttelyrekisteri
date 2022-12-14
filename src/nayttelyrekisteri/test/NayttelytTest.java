package nayttelyrekisteri.test;
// Generated by ComTest BEGIN
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
import nayttelyrekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.26 14:47:57 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NayttelytTest {



  // Generated by ComTest BEGIN
  /** testLisaa35 */
  @Test
  public void testLisaa35() {    // Nayttelyt: 35
    Nayttelyt nayttelyt = new Nayttelyt(); 
    Nayttely show1 = new Nayttely(), show2 = new Nayttely(); 
    assertEquals("From: Nayttelyt line: 38", 0, nayttelyt.getLkm()); 
    nayttelyt.lisaa(show1); assertEquals("From: Nayttelyt line: 39", 1, nayttelyt.getLkm()); 
    nayttelyt.lisaa(show2); assertEquals("From: Nayttelyt line: 40", 2, nayttelyt.getLkm()); 
    nayttelyt.lisaa(show1); assertEquals("From: Nayttelyt line: 41", 3, nayttelyt.getLkm()); 
    assertEquals("From: Nayttelyt line: 42", show1, nayttelyt.anna(0)); 
    assertEquals("From: Nayttelyt line: 43", show2, nayttelyt.anna(1)); 
    assertEquals("From: Nayttelyt line: 44", show1, nayttelyt.anna(2)); 
    assertEquals("From: Nayttelyt line: 45", false, nayttelyt.anna(1) == show1); 
    assertEquals("From: Nayttelyt line: 46", true, nayttelyt.anna(1) == show2); 
    try {
    assertEquals("From: Nayttelyt line: 47", show1, nayttelyt.anna(3)); 
    fail("Nayttelyt: 47 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta86 
   * @throws SailoException when error
   */
  @Test
  public void testLueTiedostosta86() throws SailoException {    // Nayttelyt: 86
    Nayttelyt nayttelyt = new Nayttelyt(); 
    Nayttely nayttely1 = new Nayttely(), nayttely2 = new Nayttely(); 
    nayttely1.alustaNayttely(); 
    nayttely2.alustaNayttely(); 
    String tiedNimi = "testinayttelyt"; 
    File ftied = new File(tiedNimi+"/nayttelyt.dat"); 
    ftied.delete(); 
    try {
    nayttelyt.lueTiedostosta(tiedNimi); 
    fail("Nayttelyt: 97 Did not throw SailoException");
    } catch(SailoException _e_){ _e_.getMessage(); }
    nayttelyt.lisaa(nayttely1); 
    nayttelyt.lisaa(nayttely2); 
    nayttelyt.tallenna("testinayttelyt"); 
    nayttelyt = new Nayttelyt(); 
    nayttelyt.lueTiedostosta(tiedNimi); 
    nayttelyt.lisaa(nayttely2); 
    nayttelyt.tallenna("testinayttelyt"); 
    assertEquals("From: Nayttelyt line: 105", true, ftied.delete()); 
  } // Generated by ComTest END
}