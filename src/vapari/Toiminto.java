/**
 * 
 */
package vapari;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Toiminto                            | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | - tietää 'henkilön toiminnon', joka esiintyy       |                   | 
 * |   toiminto ikkunassa                               |                   |
 * | - osaa muuttaa toiminnot.dat tiedoston rivin       |                   |
 * |   merkkijonot toiminnoksi                          |                   | 
 * | - osaa antaa merkkijonona i:n kentän tiedot        |                   | 
 * | - osaa laittaa merkkijonon i:neksi kentäksi        |                   | 
 * |                                                    |                   | 
 * |                                                    |                   | 
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
public class Toiminto {
    
    private int id;  // Henkilön tunnistenumero
    private int rekisterointiNro;   // Toiminnon rekisteröintinumero
    private String toiminto;        // Varsinainen toiminto
    
    private static int seuraavaNro = 1; // Seuraava numero rekisteröintiin
    
    
    
    /**
     * Perus alustus ilman parametreja.
     */
    public Toiminto() {
        //
    }
    
    
    /**
     * Alustetaan toiminto id:llä
     * @param id henkilön id
     */
    public Toiminto(int id) {
        this.id = id;
    }
    
    /**
     * Alustus henkilön tunnistenumeron ja halutun toiminnon kanssa.
     * @param tunnusNro henkilön id, joka välitetty parametrina
     * @param toiminto toiminto jolla henkilön toiminto halutaan alustaa
     */
    public Toiminto(int tunnusNro, String toiminto) {
        this.id = tunnusNro;
        this.toiminto = toiminto;
    }
    
    
    /**
     * Annetaan henkilön toiminnolle oma rekisteröintinumero
     * @return henkilön toiminnon rekisteröintinumero
     * 
     * @example
     * <pre name="test">
     *  Toiminto siivous = new Toiminto();
     *  int n1 = siivous.rekisteroi();
     *  Toiminto lumityot = new Toiminto();
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
     * Metodi esimerkkitoiminnon täyttämiseksi
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getToiminto() === "siivous";
     * </pre>
     */
    public void esimerkkiTiedot() {
        this.toiminto = "siivous";
        this.id = 1;
    }
    
    
    /**
     * Palautetaan henkilön toiminto
     * @return henkilön toiminto
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getToiminto() === "siivous";
     * </pre>
     */
    public String getToiminto() {
        return this.toiminto;
    }
    
    
    /**
     * Palautetaan toiminnon henkilön tunnistenumero
     * @return henkilön tunnistenumero, jolle toiminto kuuluu
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public int getTunnusNro() {
        return this.id;
    }
    
    
    /**
     * Palautetaan toiminnon rekisterointinumero
     * @return lisätiedon rekisterointiNro
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * Toiminto lumityot = new Toiminto();
     * siivous.rekisteroi();
     * lumityot.rekisteroi();
     * siivous.getRekisterointiNro() === lumityot.getRekisterointiNro()-1;
     * </pre>
     */
    public int getRekisterointiNro() {
        return this.rekisterointiNro;
    }
    
    
    /**
     * Asetetaan toiminto
     * @param toiminto toiminto joka halutaan asettaa
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * siivous.setToiminto("siivous");
     * siivous.getToiminto() === "siivous";
     * </pre>
     */
    public void setToiminto(String toiminto) {
        this.toiminto = toiminto;
    }
    
    
    /**
     * Asetetaan henkilön tunnistenumero toiminnolle.
     * @param tunnusNro henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public void setTunnusNro(int tunnusNro) {
        this.id = tunnusNro;
    }
    
    
    /**
     * Asetetaan toiminnolle rekisteröintinumero
     * @param rekNro asetettava rekisteröintinumero
     * 
     * @example
     * <pre name="test">
     * Toiminto siivous = new Toiminto();
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
     * Tulostetaan toiminto
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.toiminto);
    }
    
    
    /**
     * Palauttaa lisätiedon merkkijonona.
     * Eri kentät erotettu | merkillä.
     * @example
     * <pre name="test">
     * Toiminto toiminto = new Toiminto();
     * toiminto.parse(" 2 | Siivous | 1 ");
     * toiminto.toString().equals("2|Siivous|1") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + this.getTunnusNro() + "|" +
                this.getToiminto() + "|" +
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
        this.toiminto = Mjonot.erota(sb, '|', this.toiminto);
        this.setRekNro(Mjonot.erota(sb, '|', this.rekisterointiNro));
    }

    
}
