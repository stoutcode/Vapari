package vapari.test;
// Generated by ComTest BEGIN
import java.io.File;
import static org.junit.Assert.*;
import org.junit.*;
import vapari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.23 21:03:52 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class HenkilotTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa66 
   * @throws YleisException when error
   */
  @Test
  public void testLisaa66() throws YleisException {    // Henkilot: 66
    Henkilot henkilot = new Henkilot(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(); 
    assertEquals("From: Henkilot line: 70", 0, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 71", 1, henkilot.getMaara()); 
    henkilot.lisaa(eetu2); assertEquals("From: Henkilot line: 72", 2, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 73", 3, henkilot.getMaara()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAnna146 
   * @throws YleisException when error
   */
  @Test
  public void testAnna146() throws YleisException {    // Henkilot: 146
    Henkilot henkilot = new Henkilot(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(); 
    assertEquals("From: Henkilot line: 150", 0, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 151", 1, henkilot.getMaara()); 
    henkilot.lisaa(eetu2); assertEquals("From: Henkilot line: 152", 2, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 153", 3, henkilot.getMaara()); 
    assertEquals("From: Henkilot line: 154", eetu1, henkilot.anna(0)); 
    assertEquals("From: Henkilot line: 155", eetu2, henkilot.anna(1)); 
    assertEquals("From: Henkilot line: 156", eetu1, henkilot.anna(2)); 
    assertEquals("From: Henkilot line: 157", false, henkilot.anna(1) == eetu1); 
    assertEquals("From: Henkilot line: 158", true, henkilot.anna(1) == eetu2); 
    try {
    assertEquals("From: Henkilot line: 159", eetu1, henkilot.anna(100000)); 
    fail("Henkilot: 159 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    try {
    assertEquals("From: Henkilot line: 160", eetu1, henkilot.anna(-1)); 
    fail("Henkilot: 160 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testGetMaara187 
   * @throws YleisException when error
   */
  @Test
  public void testGetMaara187() throws YleisException {    // Henkilot: 187
    Henkilot henkilot = new Henkilot(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(); 
    assertEquals("From: Henkilot line: 191", 0, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 192", 1, henkilot.getMaara()); 
    henkilot.lisaa(eetu2); assertEquals("From: Henkilot line: 193", 2, henkilot.getMaara()); 
    henkilot.lisaa(eetu1); assertEquals("From: Henkilot line: 194", 3, henkilot.getMaara()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetTiedostonNimi207 */
  @Test
  public void testGetTiedostonNimi207() {    // Henkilot: 207
    Henkilot henkilot = new Henkilot(); 
    assertEquals("From: Henkilot line: 209", "henkilot.dat", henkilot.getTiedostonNimi()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetKaupunki222 */
  @Test
  public void testGetKaupunki222() {    // Henkilot: 222
    Henkilot henkilot = new Henkilot(); 
    assertEquals("From: Henkilot line: 224", "Jyväskylä", henkilot.getKaupunki()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta265 
   * @throws YleisException when error
   */
  @Test
  public void testLueTiedostosta265() throws YleisException {    // Henkilot: 265
    Henkilot henkilot = new Henkilot(); 
    Henkilo t1 = new Henkilo(), t2 = new Henkilo(); 
    t1.rekisteroi(); 
    t2.rekisteroi(); 
    t1.esimerkkiTiedot(); 
    t2.esimerkkiTiedot(); 
    String tiedosto = "testivapari.dat"; 
    File ftied = new File(tiedosto); 
    ftied.delete(); 
    try {
    henkilot.lueTiedostosta(tiedosto); 
    fail("Henkilot: 277 Did not throw YleisException");
    } catch(YleisException _e_){ _e_.getMessage(); }
    henkilot.lisaa(t1); 
    henkilot.lisaa(t2); 
    henkilot.tallenna(); 
    assertEquals("From: Henkilot line: 281", t1, henkilot.anna(0)); 
    assertEquals("From: Henkilot line: 282", t2, henkilot.anna(1)); 
    henkilot = new Henkilot(); 
    henkilot.lueTiedostosta(tiedosto); 
    henkilot.anna(0).equals(t1); 
    henkilot.anna(1).equals(t2); 
    assertEquals("From: Henkilot line: 287", true, ftied.delete()); 
  } // Generated by ComTest END
}