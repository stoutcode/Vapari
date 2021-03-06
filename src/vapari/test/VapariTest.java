package vapari.test;
// Generated by ComTest BEGIN
import java.io.File;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.*;
import static vapari.Vapari.*;
import vapari.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2020.03.23 21:02:26 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class VapariTest {



  // Generated by ComTest BEGIN
  /** 
   * testAnnaHenkilo114 
   * @throws YleisException when error
   */
  @Test
  public void testAnnaHenkilo114() throws YleisException {    // Vapari: 114
    Vapari vapari = new Vapari(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(); 
    assertEquals("From: Vapari line: 118", 0, vapari.getHenkiloita()); 
    vapari.lisaa(eetu1); assertEquals("From: Vapari line: 119", 1, vapari.getHenkiloita()); 
    vapari.lisaa(eetu2); assertEquals("From: Vapari line: 120", 2, vapari.getHenkiloita()); 
    vapari.lisaa(eetu1); assertEquals("From: Vapari line: 121", 3, vapari.getHenkiloita()); 
    assertEquals("From: Vapari line: 122", eetu1, vapari.annaHenkilo(0)); 
    assertEquals("From: Vapari line: 123", eetu2, vapari.annaHenkilo(1)); 
    assertEquals("From: Vapari line: 124", eetu1, vapari.annaHenkilo(2)); 
    assertEquals("From: Vapari line: 125", false, vapari.annaHenkilo(1) == eetu1); 
    assertEquals("From: Vapari line: 126", true, vapari.annaHenkilo(1) == eetu2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa140 
   * @throws YleisException when error
   */
  @Test
  public void testLisaa140() throws YleisException {    // Vapari: 140
    Vapari vapari = new Vapari(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(); 
    assertEquals("From: Vapari line: 144", 0, vapari.getHenkiloita()); 
    vapari.lisaa(eetu1); assertEquals("From: Vapari line: 145", 1, vapari.getHenkiloita()); 
    vapari.lisaa(eetu2); assertEquals("From: Vapari line: 146", 2, vapari.getHenkiloita()); 
    vapari.lisaa(eetu1); assertEquals("From: Vapari line: 147", 3, vapari.getHenkiloita()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa161 
   * @throws YleisException when error
   */
  @Test
  public void testLisaa161() throws YleisException {    // Vapari: 161
    Vapari vapari = new Vapari(); 
    Toiminto t1 = new Toiminto(), t2 = new Toiminto(); 
    assertEquals("From: Vapari line: 165", 0, vapari.getToimintoja()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 166", 1, vapari.getToimintoja()); 
    vapari.lisaa(t2); assertEquals("From: Vapari line: 167", 2, vapari.getToimintoja()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 168", 3, vapari.getToimintoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa182 
   * @throws YleisException when error
   */
  @Test
  public void testLisaa182() throws YleisException {    // Vapari: 182
    Vapari vapari = new Vapari(); 
    Lisatieto t1 = new Lisatieto(), t2 = new Lisatieto(); 
    assertEquals("From: Vapari line: 186", 0, vapari.getLisatietoja()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 187", 1, vapari.getLisatietoja()); 
    vapari.lisaa(t2); assertEquals("From: Vapari line: 188", 2, vapari.getLisatietoja()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 189", 3, vapari.getLisatietoja()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLisaa203 
   * @throws YleisException when error
   */
  @Test
  public void testLisaa203() throws YleisException {    // Vapari: 203
    Vapari vapari = new Vapari(); 
    Tapahtuma t1 = new Tapahtuma(), t2 = new Tapahtuma(); 
    assertEquals("From: Vapari line: 207", 0, vapari.getTapahtumia()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 208", 1, vapari.getTapahtumia()); 
    vapari.lisaa(t2); assertEquals("From: Vapari line: 209", 2, vapari.getTapahtumia()); 
    vapari.lisaa(t1); assertEquals("From: Vapari line: 210", 3, vapari.getTapahtumia()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testPvmToDate374 */
  @Test
  public void testPvmToDate374() {    // Vapari: 374
    assertEquals("From: Vapari line: 375", "2019-11-13", pvmToDate("13-11-2019").toString()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testLueTiedostosta447 
   * @throws YleisException when error
   */
  @Test
  public void testLueTiedostosta447() throws YleisException {    // Vapari: 447
    String hakemisto = "testivapari"; 
    File dir = new File(hakemisto); 
    File ftied1 = new File(hakemisto+"/henkilot.dat"); 
    File ftied2 = new File(hakemisto+"/toiminnot.dat"); 
    File ftied3 = new File(hakemisto+"/lisatiedot.dat"); 
    File ftied4 = new File(hakemisto+"/tapahtumat.dat"); 
    dir.mkdir(); 
    ftied1.delete(); 
    ftied2.delete(); 
    ftied3.delete(); 
    ftied4.delete(); 
    Vapari testi = new Vapari(); 
    testi.setTiedosto(hakemisto); 
    testi.tallenna(); 
    testi.lueTiedostosta(hakemisto); 
    testi = new Vapari(); 
    Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(), eetu3 = new Henkilo(); 
    eetu1.rekisteroi(); 
    eetu1.esimerkkiTiedot(); 
    eetu1.setNimi("Esimerkki Eetu 1"); 
    eetu2.rekisteroi(); 
    eetu2.esimerkkiTiedot(); 
    eetu2.setNimi("Esimerkki Eetu 2"); 
    eetu3.rekisteroi(); 
    eetu3.esimerkkiTiedot(); 
    eetu3.setNimi("Esimerkki Eetu 3"); 
    Toiminto to1 = new Toiminto(), to2 = new Toiminto(), to3 = new Toiminto(); 
    to1.rekisteroi(); 
    to1.esimerkkiTiedot(); 
    to2.rekisteroi(); 
    to2.esimerkkiTiedot(); 
    to3.rekisteroi(); 
    to3.esimerkkiTiedot(); 
    Lisatieto li1 = new Lisatieto(), li2 = new Lisatieto(), li3 = new Lisatieto(); 
    li1.rekisteroi(); 
    li1.esimerkkiTiedot(); 
    li2.rekisteroi(); 
    li2.esimerkkiTiedot(); 
    li3.rekisteroi(); 
    li3.esimerkkiTiedot(); 
    Tapahtuma ta1 = new Tapahtuma(), ta2 = new Tapahtuma(), ta3 = new Tapahtuma(); 
    ta1.rekisteroi(); 
    ta1.esimerkkiTiedot(); 
    ta2.rekisteroi(); 
    ta2.esimerkkiTiedot(); 
    ta3.rekisteroi(); 
    ta3.esimerkkiTiedot(); 
    testi.lisaa(eetu1); 
    testi.lisaa(eetu2); 
    testi.lisaa(eetu3); 
    testi.lisaa(to1); 
    testi.lisaa(to2); 
    testi.lisaa(to3); 
    testi.lisaa(li1); 
    testi.lisaa(li2); 
    testi.lisaa(li3); 
    testi.lisaa(ta1); 
    testi.lisaa(ta2); 
    testi.lisaa(ta3); 
    testi.setTiedosto(hakemisto); 
    testi.tallenna(); 
    testi.lueTiedostosta(hakemisto); 
    assertEquals("From: Vapari line: 513", true, testi.annaHenkilo(0).getNimi().equals("Esimerkki Eetu 1")); 
    assertEquals("From: Vapari line: 514", true, testi.annaHenkilo(1).getNimi().equals("Esimerkki Eetu 2")); 
    assertEquals("From: Vapari line: 515", true, testi.annaHenkilo(2).getNimi().equals("Esimerkki Eetu 3")); 
    ArrayList<Toiminto> toimintoja = testi.haeToiminnot(1); 
    assertEquals("From: Vapari line: 517", "siivous", toimintoja.get(0).getToiminto()); 
    ArrayList<Lisatieto> lisatietoja = testi.haeLisatiedot(1); 
    assertEquals("From: Vapari line: 519", "lisätieto", lisatietoja.get(0).getLisatieto()); 
    ArrayList<Tapahtuma> tapahtumia = testi.haeTapahtumat(1); 
    assertEquals("From: Vapari line: 521", "14-05-2011", tapahtumia.get(0).getPvm()); 
    assertEquals("From: Vapari line: 522", true, ftied1.delete()); 
    assertEquals("From: Vapari line: 523", true, ftied2.delete()); 
    assertEquals("From: Vapari line: 524", true, ftied3.delete()); 
    assertEquals("From: Vapari line: 525", true, ftied4.delete()); 
    assertEquals("From: Vapari line: 526", true, dir.delete()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testHakuString584 */
  @Test
  public void testHakuString584() {    // Vapari: 584
    assertEquals("From: Vapari line: 585", true, hakuString("koirankoppi", "koppi")); 
    assertEquals("From: Vapari line: 586", true, hakuString("Pekka Esimerkki", "Pekka")); 
    assertEquals("From: Vapari line: 587", false, hakuString("Pekka Esimerkki", "Pekko")); 
    assertEquals("From: Vapari line: 588", true, hakuString("Pekka Esimerkki", "Esimer")); 
    assertEquals("From: Vapari line: 589", true, hakuString("pekka@esimerkki.com", "esim")); 
  } // Generated by ComTest END
}