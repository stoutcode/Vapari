/**
 * 
 */
package vapari;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.HetuTarkistus;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Henkilo                             | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   |
 * | - tietää 'henkilön tiedot', jotka esiintyvät       |                   | 
 * |   henkilön tiedot kentissä                         |                   | 
 * |   (nimi, hetu, osoite, rooli jne.)                 |                   |
 * | - osaa tarkistaa nimi ja hetu kenttien             |                   |
 * |   oikeellisuuden                                   |                   | 
 * | - osaa muuttaa henkilot.dat tiedoston rivin        |                   |
 * |   sisältämät merkkijonot henkilön tiedoiksi        |                   |
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   |
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author mikar
 * @version 17 Feb 2020
 *
 */
public class Henkilo {
    
    private int     tunnusNro;
    private String  nimi        = "";
    private String  hetu        = "";
    private String  puh         = "";
    private String  mail        = "";
    private String  alue        = "";
    private String  osoite      = "";
    private String  postiNro    = "";
    private String  rooli       = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Oletusmuodostaja tyhjälle henkilölle
     */
    public Henkilo() {
        // ei ainakaan nyt mitään spesifimpää alustusta
    }
    
    /**
     * Oletusmuodostaja henkilön tietojen täyttämiseen
     * @param nimi Henkilön nimi
     * @param hetu Henkilön henkilöturvatunnus
     * @param puh Henkilön puhelinnumero
     * @param mail Henkilön sähköpostiosoite
     * @param alue Henkilön kaupunginosa
     * @param osoite Henkilön osoite
     * @param postiNro Henkilön postinumero
     * @param rooli Henkilön rooli
     */
    public Henkilo(String nimi, String hetu, String puh, String mail, String alue, String osoite, String postiNro, String rooli) {
        this.nimi = nimi;
        this.hetu = hetu;
        this.puh = puh;
        this.mail = mail;
        this.alue = alue;
        this.osoite = osoite;
        this.postiNro = postiNro;
        this.rooli = rooli;
        
    }
    
    
    /**
     * Antaa henkilölle seuraavan rekisterinumeron.
     * @return uusi tunnusNro henkilölle
     * 
     * @example
     * <pre name="test">
     * Henkilo mikko1 = new Henkilo();
     * mikko1.getTunnusNro() === 0;
     * mikko1.rekisteroi();
     * Henkilo mikko2 = new Henkilo();
     * mikko2.rekisteroi();
     * int n1 = mikko1.getTunnusNro();
     * int n2 = mikko2.getTunnusNro();
     * n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.tunnusNro = seuraavaNro;
        seuraavaNro++;
        return this.tunnusNro;
    }
    

    /**
     * Apumetodi, jolla saadaan täytettyä Henkilölle valmiit esimerkkitiedot
     * (TÄMÄ ON VAIN DEMOAMISTA VARTEN, EI TARPEELLINEN VALMIISSA OHJELMASSA)
     */
    public void esimerkkiTiedot() {     
        this.nimi = "Esimerkki Eetu " + HetuTarkistus.rand(99, 999);
        this.hetu = kanta.HetuTarkistus.arvoHetu();
        this.puh  = "404-1234567";
        this.mail = "eetu.esimerkki@esimerkki.esim";
        this.alue = "Keskusta";
        this.osoite = "Kauppakatu 22";
        this.postiNro = "40100";
        this.rooli = "auttaja";
    }    
    
    
    /**
     * Palauttaa henkilon tunnusnumeron.
     * @return tunnusNro Henkilön id
     */
    public int getTunnusNro() {
        return this.tunnusNro;
    }
    
    
    /**
     * Palauttaa henkilön nimen
     * @return nimi Henkilön nimi
     */
    public String getNimi() {
        return this.nimi;
    }
    
    
    /**
     * Palauttaa henkilön hetun
     * @return hetu Henkilön hetu
     */
    public String getHetu() {
        return this.hetu;
    }
    
    
    /**
     * Palauttaa henkilön puhelinnumeron
     * @return puh Henkilön puhelinnumero
     */
    public String getPuh() {
        return this.puh;
    }
    
    
    /**
     * Palauttaa henkilön sähköpostiosoitteen
     * @return email Henkilön sähköpostiosoite
     */
    public String getMail() {
        return this.mail;
    }
    
    
    /**
     * Palauttaa henkilön kaupunginosan
     * @return alue Henkilön kaupunginosa
     */
    public String getAlue() {
        return this.alue;
    }
    
    
    /**
     * Palauttaa henkilön osoitteen
     * @return osoite Henkilön osoite
     */
    public String getOsoite() {
        return this.osoite;
    }
    
    
    /**
     * Palauttaa henkilön postinumeron
     * @return postinumero Henkilön postinumero
     */
    public String getPostinro() {
        return this.postiNro;
    }
    
    
    /**
     * Palauttaa henkilön roolin
     * @return rooli Henkilön rooli
     */
    public String getRooli() {
        return this.rooli;
    }
    
    
    /**
     * Asettaa tunnusnumeron henkilölle
     * @param nro tunnusnumero henkilölle
     */
    public void setTunnusnro(int nro) {
        this.tunnusNro = nro;
        if (this.tunnusNro >= seuraavaNro) seuraavaNro = this.tunnusNro +1;
    }
    
    /**
     * Asettaa henkilön nimen
     * @param nimi nimi
     */
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
     * Asettaa henkilön hetun
     * @param hetu hetu
     */
    public void setHetu(String hetu) {
        this.hetu = hetu;
    }
    
    /**
     * Asettaa henkilön puhelinnumeron
     * @param puh puhelinnumero
     */
    public void setPuh(String puh) {
        this.puh = puh;
    }
    
    /**
     * Asettaa henkilön sähköpostiosoitteen
     * @param mail email
     */
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    /**
     * Asettaa henkilön kaupunginosan
     * @param alue kaupunginosa
     */
    public void setAlue(String alue) {
        this.alue = alue;
    }
    
    /**
     * Asettaa henkilön osoitteen
     * @param osoite osoite
     */
    public void setOsoite(String osoite) {
        this.osoite = osoite;
    }
    
    /**
     * Asettaa henkilön postinumeron
     * @param postiNro postinumero
     */
    public void setPostinro(String postiNro) {
        this.postiNro = postiNro;
    }
    
    /**
     * Asettaa henkilön roolin
     * @param rooli henkilön rooli
     */
    public void setRooli(String rooli) {
        this.rooli = rooli;
    }
    
    
    /**
     * Tulostetaan henkilön tiedot.
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println("tunnusnumero: " + this.tunnusNro);
        out.println("nimi: " + this.nimi);
        out.println("henkilötunnus " + this.hetu);
        out.println("puh.: " + this.puh);
        out.println("sähköposti: " + this.mail);
        out.println("osoite: " + osoite + ", " + postiNro + " (" + alue + ")");
        out.println("rooli: " + rooli);
    }
    
    
    /**
     * Tulevaisuutta varten. Tulostetaan henkilön tiedot.
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palauttaa henkilön tiedot merkkijonona, jotta voidaan tallentaa tiedostoon.
     * Eri kentät erotettu | merkillä.
     * @example
     * <pre name="test">
     * Henkilo henkilo = new Henkilo();
     * henkilo.parse(" 2 | Mika Malli | 030383-1333 | 040-1231234 | mika.malli@malli.malli   | Keskusta | Kauppakatu 1 | 40100 | Auttaja ");
     * henkilo.toString().equals("2|Mika Malli|030383-1333|040-1231234|mika.malli@malli.malli|Keskusta|Kauppakatu 1|40100|Auttaja") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + this.getTunnusNro() + "|" +
                this.getNimi() + "|" +
                this.getHetu() + "|" +
                this.getPuh() + "|" +
                this.getMail() + "|" +
                this.getAlue() + "|" +
                this.getOsoite() + "|" +
                this.getPostinro() + "|" +
                this.getRooli();
    }
    
    
    /**
     * Asetetaan henkilölle tiedot tallennetusta merkkijonorivistä,
     * jossa tiedot eroteltu | merkillä toisistaan.
     * @param rivi henkilön tiedot stringinä
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        this.setTunnusnro(Mjonot.erota(sb, '|', this.tunnusNro));
        this.nimi = Mjonot.erota(sb, '|', this.nimi);
        this.hetu = Mjonot.erota(sb, '|', this.hetu);
        this.puh = Mjonot.erota(sb, '|', this.puh);
        this.mail = Mjonot.erota(sb, '|', this.mail);
        this.alue = Mjonot.erota(sb, '|', this.alue);
        this.osoite = Mjonot.erota(sb, '|', this.osoite);
        this.postiNro = Mjonot.erota(sb, '|', this.postiNro);
        this.rooli = Mjonot.erota(sb, '|', this.rooli);
    }

    
    /**
     * Testiohjelma luokalle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Henkilo mikko = new Henkilo();
        Henkilo mikko2 = new Henkilo();
        
        mikko.rekisteroi();
        mikko2.rekisteroi();
        
        mikko.esimerkkiTiedot();
        mikko2.esimerkkiTiedot();
                
        
        Henkilo henkilo = new Henkilo();
        henkilo.parse(" 2 | Mika Malli | 030383-1333 | 040-1231234 | mika.malli@malli.malli | Keskusta | Kauppakatu 1 | 40100 | Auttaja");
        System.out.println(henkilo.toString());

    }

    
}
