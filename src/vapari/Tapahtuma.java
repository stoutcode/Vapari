/**
 * 
 */
package vapari;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Tapahtuma                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | - tietää 'henkilön tapahtuman', joka esiintyy      |                   | 
 * |   tapahtuma ikkunassa                              |                   |
 * | - osaa tarkistaa että tapahtumalle on asetettu pvm |                   | 
 * | - osaa muuttaa tapahtumat.dat tiedoston rivin      |                   |
 * |   merkkijonot tapahtumaksi ikkunaan                |                   | 
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author mikar
 * @version 27 Feb 2020
 *
 */
public class Tapahtuma {
    
    private int id;                     // Henkilön tunnistenumero
    private int rekisterointiNro;       // Tapahtuman rekisteröintinumero
    private String toiminto;            // Tapahtuman toiminto
    private String pvm;                 // Tapahtuman päivämäärä
    private int toinenId;            // Toisen henkilön id
    private static int seuraavaNro = 1; // Seuraava numero rekisteröintiin
    
    
    /**
     * Alustus ilman parametrejä
     */
    public Tapahtuma() {
        //
    }
    
    
    /**
     * Alustus henkilön id:llä
     * @param id henkilön id
     */
    public Tapahtuma(int id) {
        this.id = id;
    }
    
    
    /**
     * Alustus kaikilla parametreilla
     * @param id henkilön id
     * @param toiminto toiminto joka tehtiin
     * @param pvm päivämäärä
     * @param toinenId toisen henkilön id
     */
    public Tapahtuma(int id, String toiminto, String pvm, int toinenId) {
        this.id = id;
        this.toiminto = toiminto;
        this.pvm = pvm;
        this.toinenId = toinenId;
    }
    
    
    /**
     * Annetaan tapahtumalle oma rekisteröintinumero
     * @return tapahtuman rekisteröintinumero rekisteröintinumero
     * 
     * @example
     * <pre name="test">
     *  Tapahtuma siivous = new Tapahtuma();
     *  int n1 = siivous.rekisteroi();
     *  Tapahtuma lumityot = new Tapahtuma();
     *  int n2 = lumityot.rekisteroi();
     *  n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.rekisterointiNro = seuraavaNro;
        seuraavaNro++;
        return rekisterointiNro;
    }
    
    
    /**
     * Metodi esimerkkitapahtuman täyttämiseksi
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getToiminto() === "siivous";
     * siivous.getTunnusNro() === 1;
     * siivous.getPvm() === "14-05-2011";
     * siivous.getToinenId() === 2;
     * </pre>
     */
    public void esimerkkiTiedot() {
        this.id = 1;
        this.toiminto = "siivous";
        this.pvm = "14-05-2011";
        this.toinenId = 2;
    }
    
    
    /**
     * Palautetaan tapahtuman toiminto
     * @return tapahtuman toiminto
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getToiminto() === "siivous";
     * </pre>
     */
    public String getToiminto() {
        return this.toiminto;
    }
    
    
    /**
     * Palautetaan tapahtuman päivämäärä
     * @return tapahtuman päivämäärä
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getPvm() === "14-05-2011";
     * </pre>
     */
    public String getPvm() {
        return this.pvm;
    }
    
    
    /**
     * Palautetaan tapahtuman toisen henkilön tunnistenumero
     * @return tapahtuman toisen henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getToinenId() === 2;
     * </pre>
     */
    public int getToinenId() {
        return this.toinenId;
    }
    
    
    /**
     * Palautetaan tapahtuman henkilön tunnistenumero
     * @return tapahtuman henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public int getTunnusNro() {
        return this.id;
    }
    
    
    /**
     * Palautetaan tapahtuman rekisterointinumero
     * @return tapahtuman rekisterointiNro
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * Tapahtuma lumityot = new Tapahtuma();
     * siivous.rekisteroi();
     * lumityot.rekisteroi();
     * siivous.getRekisterointiNro() === lumityot.getRekisterointiNro()-1;
     * </pre>
     */
    public int getRekisterointiNro() {
        return this.rekisterointiNro;
    }
    
    
    /**
     * Asetetaan tapahtumalle rekisteröintinumero
     * tätä käytetään kun luodaan tuplatapahtumia, niin ne tunnistetaan samasta rekisteröintinumerosta
     * @param nro haluttu rekisterointinumero
     */
    public void setRekistointiNro(int nro) {
        this.rekisterointiNro = nro;
    }
    
    
    /**
     * Asetetaan toiminto
     * @param toiminto toiminto joka halutaan asettaa
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.setToiminto("siivous");
     * siivous.getToiminto() === "siivous";
     * </pre>
     */
    public void setToiminto(String toiminto) {
        this.toiminto = toiminto;
    }
    
    
    /**
     * Asetetaan pvm
     * @param pvm päivämäärä
     * 
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.setPvm("13.11.2012");
     * siivous.getPvm() === "13.11.2012";
     * </pre>
     */
    public void setPvm(String pvm) {
        this.pvm = pvm;
    }
    
    
    /**
     * Asetetaan toisen henkilön id tapahtumalle
     * @param toinenId toisen henkilön id
     */
    public void setToinenId(int toinenId) {
        this.toinenId = toinenId;
    }
    
    
    /**
     * Asetetaan henkilön tunnistenumero tapahtumalle.
     * @param tunnusNro henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public void setTunnusNro(int tunnusNro) {
        this.id = tunnusNro;
    }
    
    
    /**
     * Asetetaan tapahtumalle haluttu rekisteröintinumero
     * @param rekNro tapahtumat rekisteröintinumero
     * 
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.setRekNro(2);
     * siivous.getRekisterointiNro() === 2;
     * </pre>
     */
    public void setRekNro(int rekNro) {
        this.rekisterointiNro = rekNro;
        if (this.rekisterointiNro >= seuraavaNro) seuraavaNro = this.rekisterointiNro +1;
    }
    
    
    /**
     * Välitetään tulostuspyyntö tulostukselle
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Tulostetaan tapahtuma
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.toiminto);
        out.println(this.pvm);
        out.println(this.toinenId);
    }
    
    
    /**
     * Palauttaa lisätiedon merkkijonona.
     * Eri kentät erotettu | merkillä.
     * @example
     * <pre name="test">
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.parse(" 2 | 3 | 12.3.2015  | Siivous | 2 ");
     * siivous.toString().equals("2|3|12.3.2015|Siivous|2") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + this.getTunnusNro() + "|" +
                this.getToinenId() + "|" +
                this.getPvm()  + "|" +
                this.getToiminto()  + "|" +
                this.getRekisterointiNro();
    }
    
    
    /**
     * Asetetaan henkilölle tiedot tallennetusta merkkijonorivistä,
     * jossa tiedot eroteltu | merkillä toisistaan.
     * @param rivi henkilön tiedot stringinä
     */
    public void parse(String rivi) {
        StringBuilder sb = new StringBuilder(rivi);
        this.id = Mjonot.erota(sb, '|', this.id);
        this.toinenId = Mjonot.erota(sb, '|', this.toinenId);
        this.pvm = Mjonot.erota(sb, '|', this.pvm);
        this.toiminto = Mjonot.erota(sb, '|', this.toiminto);
        this.setRekNro(Mjonot.erota(sb, '|', this.rekisterointiNro));
    }
    
    
}
