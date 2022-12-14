package nayttelyrekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import nayttelyrekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 20:09:05 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NayttelyTest {



  // Generated by ComTest BEGIN
  /** testSetPvm58 */
  @Test
  public void testSetPvm58() {    // Nayttely: 58
    Nayttely nayttely = new Nayttely(); 
    assertEquals("From: Nayttely line: 60", null, nayttely.setPvm("26012020")); 
    assertEquals("From: Nayttely line: 61", "Päivämäärä väärin", nayttely.setPvm("2612020")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testRekisteroi107 */
  @Test
  public void testRekisteroi107() {    // Nayttely: 107
    Nayttely show1 = new Nayttely(); 
    assertEquals("From: Nayttely line: 109", 0, show1.getTunnusNro()); 
    show1.rekisteroi(); 
    Nayttely show2 = new Nayttely(); 
    show2.rekisteroi(); 
    int n1 = show1.getTunnusNro(); 
    int n2 = show2.getTunnusNro(); 
    assertEquals("From: Nayttely line: 115", n1+1, n2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse151 */
  @Test
  public void testParse151() {    // Nayttely: 151
    Nayttely nayttely = new Nayttely(); 
    nayttely.parse(" 3|PIROK|31102020"); 
    assertEquals("From: Nayttely line: 154", 3, nayttely.getTunnusNro()); 
    assertEquals("From: Nayttely line: 155", true, nayttely.toString().startsWith("3|PIROK|31102020")); 
    nayttely.rekisteroi(); 
    int n = nayttely.getTunnusNro(); 
    nayttely.parse(""+(n+20)); 
    nayttely.rekisteroi(); 
    assertEquals("From: Nayttely line: 161", n+20+1, nayttely.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString177 */
  @Test
  public void testToString177() {    // Nayttely: 177
    Nayttely nayttely = new Nayttely(); 
    nayttely.parse("3|PIROK|31102020"); 
    assertEquals("From: Nayttely line: 180", true, nayttely.toString().startsWith("3|PIROK|")); 
  } // Generated by ComTest END
}