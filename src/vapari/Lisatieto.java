/**
 * 
 */
package vapari;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Lisatieto                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    |                   | 
 * | - tietää 'henkilön lisätiedon', joka esiintyy      |                   | 
 * |   lisätieto ikkunassa                              |                   |
 * | - osaa tarkistaa lisätieto kentän, ettei ole tyhjä |                   | 
 * | - osaa muuttaa lisatiedot.dat tiedoston rivin      |                   |
 * |   merkkijonot lisätiedoksi ikkunaan                |                   | 
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
public class Lisatieto {
    
    private int id;                 // Henkilön tunnistenumero
    private int rekisterointiNro;   // Lisatiedon rekisteröintinumero
    private String lisatieto;       // Varsinainen lisatieto
    
    private static int seuraavaNro = 1; // Seuraava numero rekisteröintiin
    
    

    /**
     * Perus alustus ilman parametreja.
     */
    public Lisatieto() {
        //
    }
    
    
    /**
     * Alustetaan lisätieto id:llä
     * @param id henkilön id
     */
    public Lisatieto(int id) {
        this.id = id;
    }
    
    /**
     * Alustus henkilön tunnistenumeron ja halutun lisatiedon kanssa.
     * @param tunnusNro henkilön id, joka välitetty parametrina
     * @param tieto lisätieto jolla henkilön lisätieto halutaan alustaa
     */
    public Lisatieto(int tunnusNro, String tieto) {
        this.id = tunnusNro;
        this.lisatieto = tieto;
    }
    
    
    /**
     * Annetaan henkilön lisätiedolle oma rekisteröintinumero
     * @return henkilön lisätiedon rekisteröintinumero
     * 
     * @example
     * <pre name="test">
     *  Lisatieto siivous = new Lisatieto();
     *  int n1 = siivous.rekisteroi();
     *  Lisatieto lumityot = new Lisatieto();
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
     * Metodi esimerkkilisätiedon täyttämiseksi
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getLisatieto() === "lisätieto";
     * </pre>
     */
    public void esimerkkiTiedot() {
        this.lisatieto = "lisätieto";
        this.id = 1;
    }
    
    
    /**
     * Palautetaan henkilön lisätieto
     * @return henkilön lisätieto
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * siivous.rekisteroi();
     * siivous.esimerkkiTiedot();
     * siivous.getLisatieto() === "lisätieto";
     * </pre>
     */
    public String getLisatieto() {
        return this.lisatieto;
    }
    
    
    /**
     * Palautetaan lisätiedon omaavan henkilön tunnistenumero
     * @return henkilön tunnistenumero, jolle lisätieto kuuluu
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public int getTunnusNro() {
        return this.id;
    }
    
    
    /**
     * Palautetaan lisätiedon rekisterointinumero
     * @return lisätiedon rekisterointiNro
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * Lisatieto lumityot = new Lisatieto();
     * siivous.rekisteroi();
     * lumityot.rekisteroi();
     * siivous.getRekisterointiNro() === lumityot.getRekisterointiNro()-1;
     * </pre>
     */
    public int getRekisterointiNro() {
        return this.rekisterointiNro;
    }
    
    
    /**
     * Asetetaan lisätieto
     * @param lisatieto lisätieto joka halutaan asettaa
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * siivous.setLisatieto("iso kämppä siivottavaksi");
     * siivous.getLisatieto() === "iso kämppä siivottavaksi";
     * </pre>
     */
    public void setLisatieto(String lisatieto) {
        this.lisatieto = lisatieto;
    }
    
    
    /**
     * Asetetaan henkilön tunnistenumero lisätiedolle.
     * @param tunnusNro henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
     * siivous.setTunnusNro(2);
     * siivous.getTunnusNro() === 2;
     * </pre>
     */
    public void setTunnusNro(int tunnusNro) {
        this.id = tunnusNro;
    }
    
    
    /**
     * Asetetaan rekisteröintinumero lisätiedolle.
     * @param rekNro rekisteröintinumero lisätiedolle
     * 
     * @example
     * <pre name="test">
     * Lisatieto siivous = new Lisatieto();
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
     * Tulostetaan lisätieto
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(this.lisatieto);
    }
    
    
    /**
     * Palauttaa lisätiedon merkkijonona.
     * Eri kentät erotettu | merkillä.
     * @example
     * <pre name="test">
     * Lisatieto tieto = new Lisatieto();
     * tieto.parse(" 2 | Auto kaytossa | 4 ");
     * tieto.toString().equals("2|Auto kaytossa|4") === true;
     * </pre>
     */
    @Override
    public String toString() {
        return "" + this.getTunnusNro() + "|" +
                this.getLisatieto() + "|" +
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
        this.lisatieto = Mjonot.erota(sb, '|', this.lisatieto);
        this.setRekNro(Mjonot.erota(sb, '|', this.rekisterointiNro));
    }

    
    /**
     * Testipääohjelma
     * @param argos ei käytössä
     */
    public static void main(String[] argos) {
        Lisatieto tieto = new Lisatieto();
        tieto.parse("2  | Koiranomistaja  | 4  ");
        System.out.println(tieto.toString());
    }

}
