package nayttelyrekisteri.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import nayttelyrekisteri.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2021.04.23 20:13:55 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TitteliTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi121 */
  @Test
  public void testRekisteroi121() {    // Titteli: 121
    Titteli titteli1 = new Titteli(); 
    assertEquals("From: Titteli line: 123", 0, titteli1.getTunnusNro()); 
    titteli1.rekisteroi(); 
    Titteli titteli2 = new Titteli(); 
    titteli2.rekisteroi(); 
    int n1 = titteli1.getTunnusNro(); 
    int n2 = titteli2.getTunnusNro(); 
    assertEquals("From: Titteli line: 129", n1+1, n2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetPvm200 */
  @Test
  public void testSetPvm200() {    // Titteli: 200
    Titteli titteli = new Titteli(); 
    assertEquals("From: Titteli line: 202", null, titteli.setPvm("26012020")); 
    assertEquals("From: Titteli line: 203", "Päivämäärä väärin", titteli.setPvm("lol")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetVahvistus221 */
  @Test
  public void testSetVahvistus221() {    // Titteli: 221
    Titteli titteli = new Titteli(); 
    assertEquals("From: Titteli line: 223", null, titteli.setVahvistus("26012020")); 
    assertEquals("From: Titteli line: 224", "Vahvistuksen päivämäärä väärin", titteli.setVahvistus("lol")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString278 */
  @Test
  public void testToString278() {    // Titteli: 278
    Titteli titteli = new Titteli(); 
    titteli.parse("3 |1 |Grand International Premior |31102020 |10112020"); 
    assertEquals("From: Titteli line: 281", true, titteli.toString().startsWith("3|1|Grand International Premior|31102020|10112020")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone298 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone298() throws CloneNotSupportedException {    // Titteli: 298
    Titteli titteli = new Titteli(); 
    titteli.parse("   3  |  Premior   | 00000000 | 00000000"); 
    Titteli kopio = titteli.clone(); 
    assertEquals("From: Titteli line: 303", titteli.toString(), kopio.toString()); 
    titteli.parse("   4  |  Supreme Premior   | 11111111 | 11111111"); 
    assertEquals("From: Titteli line: 305", false, kopio.toString().equals(titteli.toString())); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse323 */
  @Test
  public void testParse323() {    // Titteli: 323
    Titteli titteli = new Titteli(); 
    titteli.parse("3 |1 |Grand International Premior |31102020 |10112020"); 
    assertEquals("From: Titteli line: 326", 3, titteli.getTunnusNro()); 
    assertEquals("From: Titteli line: 327", true, titteli.toString().startsWith("3|1|Grand International Premior|")); 
    titteli.rekisteroi(); 
    int n = titteli.getTunnusNro(); 
    titteli.parse(""+(n+20)); 
    titteli.rekisteroi(); 
    assertEquals("From: Titteli line: 333", n+20+1, titteli.getTunnusNro()); 
  } // Generated by ComTest END
}