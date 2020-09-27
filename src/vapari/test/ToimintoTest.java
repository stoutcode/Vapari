package vapari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import vapari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.23 21:02:21 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class ToimintoTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi78 */
  @Test
  public void testRekisteroi78() {    // Toiminto: 78
    Toiminto siivous = new Toiminto(); 
    int n1 = siivous.rekisteroi(); 
    Toiminto lumityot = new Toiminto(); 
    int n2 = lumityot.rekisteroi(); 
    assertEquals("From: Toiminto line: 83", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEsimerkkiTiedot97 */
  @Test
  public void testEsimerkkiTiedot97() {    // Toiminto: 97
    Toiminto siivous = new Toiminto(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Toiminto line: 101", "siivous", siivous.getToiminto()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetToiminto115 */
  @Test
  public void testGetToiminto115() {    // Toiminto: 115
    Toiminto siivous = new Toiminto(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Toiminto line: 119", "siivous", siivous.getToiminto()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTunnusNro132 */
  @Test
  public void testGetTunnusNro132() {    // Toiminto: 132
    Toiminto siivous = new Toiminto(); 
    siivous.setTunnusNro(2); 
    assertEquals("From: Toiminto line: 135", 2, siivous.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetRekisterointiNro148 */
  @Test
  public void testGetRekisterointiNro148() {    // Toiminto: 148
    Toiminto siivous = new Toiminto(); 
    Toiminto lumityot = new Toiminto(); 
    siivous.rekisteroi(); 
    lumityot.rekisteroi(); 
    assertEquals("From: Toiminto line: 153", lumityot.getRekisterointiNro()-1, siivous.getRekisterointiNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetToiminto166 */
  @Test
  public void testSetToiminto166() {    // Toiminto: 166
    Toiminto siivous = new Toiminto(); 
    siivous.setToiminto("siivous"); 
    assertEquals("From: Toiminto line: 169", "siivous", siivous.getToiminto()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTunnusNro182 */
  @Test
  public void testSetTunnusNro182() {    // Toiminto: 182
    Toiminto siivous = new Toiminto(); 
    siivous.setTunnusNro(2); 
    assertEquals("From: Toiminto line: 185", 2, siivous.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetRekNro198 */
  @Test
  public void testSetRekNro198() {    // Toiminto: 198
    Toiminto siivous = new Toiminto(); 
    siivous.setRekNro(2); 
    assertEquals("From: Toiminto line: 201", 2, siivous.getRekisterointiNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString231 */
  @Test
  public void testToString231() {    // Toiminto: 231
    Toiminto toiminto = new Toiminto(); 
    toiminto.parse(" 2 | Siivous | 1 "); 
    assertEquals("From: Toiminto line: 234", true, toiminto.toString().equals("2|Siivous|1")); 
  } // Generated by ComTest END
}