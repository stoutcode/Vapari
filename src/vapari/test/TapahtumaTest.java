package vapari.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import vapari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.23 21:02:06 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class TapahtumaTest {



  // Generated by ComTest BEGIN
  /** testRekisteroi82 */
  @Test
  public void testRekisteroi82() {    // Tapahtuma: 82
    Tapahtuma siivous = new Tapahtuma(); 
    int n1 = siivous.rekisteroi(); 
    Tapahtuma lumityot = new Tapahtuma(); 
    int n2 = lumityot.rekisteroi(); 
    assertEquals("From: Tapahtuma line: 87", n2-1, n1); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testEsimerkkiTiedot101 */
  @Test
  public void testEsimerkkiTiedot101() {    // Tapahtuma: 101
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Tapahtuma line: 105", "siivous", siivous.getToiminto()); 
    assertEquals("From: Tapahtuma line: 106", 1, siivous.getTunnusNro()); 
    assertEquals("From: Tapahtuma line: 107", "14-05-2011", siivous.getPvm()); 
    assertEquals("From: Tapahtuma line: 108", 2, siivous.getToinenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetToiminto124 */
  @Test
  public void testGetToiminto124() {    // Tapahtuma: 124
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Tapahtuma line: 128", "siivous", siivous.getToiminto()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPvm141 */
  @Test
  public void testGetPvm141() {    // Tapahtuma: 141
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Tapahtuma line: 145", "14-05-2011", siivous.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetToinenId158 */
  @Test
  public void testGetToinenId158() {    // Tapahtuma: 158
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.rekisteroi(); 
    siivous.esimerkkiTiedot(); 
    assertEquals("From: Tapahtuma line: 162", 2, siivous.getToinenId()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTunnusNro175 */
  @Test
  public void testGetTunnusNro175() {    // Tapahtuma: 175
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.setTunnusNro(2); 
    assertEquals("From: Tapahtuma line: 178", 2, siivous.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetRekisterointiNro191 */
  @Test
  public void testGetRekisterointiNro191() {    // Tapahtuma: 191
    Tapahtuma siivous = new Tapahtuma(); 
    Tapahtuma lumityot = new Tapahtuma(); 
    siivous.rekisteroi(); 
    lumityot.rekisteroi(); 
    assertEquals("From: Tapahtuma line: 196", lumityot.getRekisterointiNro()-1, siivous.getRekisterointiNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetToiminto219 */
  @Test
  public void testSetToiminto219() {    // Tapahtuma: 219
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.setToiminto("siivous"); 
    assertEquals("From: Tapahtuma line: 222", "siivous", siivous.getToiminto()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetPvm234 */
  @Test
  public void testSetPvm234() {    // Tapahtuma: 234
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.setPvm("13.11.2012"); 
    assertEquals("From: Tapahtuma line: 237", "13.11.2012", siivous.getPvm()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetTunnusNro259 */
  @Test
  public void testSetTunnusNro259() {    // Tapahtuma: 259
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.setTunnusNro(2); 
    assertEquals("From: Tapahtuma line: 262", 2, siivous.getTunnusNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testSetRekNro275 */
  @Test
  public void testSetRekNro275() {    // Tapahtuma: 275
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.setRekNro(2); 
    assertEquals("From: Tapahtuma line: 278", 2, siivous.getRekisterointiNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString311 */
  @Test
  public void testToString311() {    // Tapahtuma: 311
    Tapahtuma siivous = new Tapahtuma(); 
    siivous.parse(" 2 | 3 | 12.3.2015  | Siivous | 2 "); 
    assertEquals("From: Tapahtuma line: 314", true, siivous.toString().equals("2|3|12.3.2015|Siivous|2")); 
  } // Generated by ComTest END
}