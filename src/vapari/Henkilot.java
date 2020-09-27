/**
 * 
 */
package vapari;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Henkilot                            | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | Henkilo           | 
 * | - ylläpitää henkilot.dat tiedostoa eli osaa        |                   | 
 * |   lukea ja kirjoitaa kyseiseen tiedostoon          |                   | 
 * | - osaa suorittaa hakuja ja lajitteluja henkilöiden |                   | 
 * |   osalta                                           |                   |
 * | - osaa poistaa ja lisätä yksittäisiä henkilöitä    |                   | 
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |                                                    |                   |
 * |-------------------------------------------------------------------------
 * @author mikar
 * @version 17 Feb 2020
 *
 */
public class Henkilot {
    
    private int         henkilot_max        = 8;  // Henkilöiden maksimimäärä
    private int         henkilot_maara      = 0;       // Henkilöiden määrä
    private String      tiedostonNimi       = "henkilot.dat";      // henkilot tiedoston nimi
    private String      kaupunki            = "Jyväskylä";
    private Henkilo[]   alkiot              = new Henkilo[henkilot_max];;  // Henkilotaulukko, johon henkilot sijoitetaan

    
    
    /**
     * Oletusmuodostaja
     */
    public Henkilot() {
        // Alustetaan henkilotaulukko oletusattribuuteilla
    }
    
    /**
     * Alustetaan halutun kokoinen Henkilot taulukko
     * @param montako haluttu koko taulukolle
     */
    public Henkilot(int montako) {
        this.alkiot = new Henkilo[montako];
    }
    
    
    /**
     * Lisää uuden henkilön tietorakenteeseen ja ottaa kyseisen henkilön omistukseensa.
     * @param henkilo lisättävän henkilön viite. Huom. tietorakenne muuttuu omistajaksi.
     * @throws YleisException jos henkilotaulukko oli täynnä
     * @example
     * <pre name="test">
     *  #THROWS YleisException
     *  Henkilot henkilot = new Henkilot();
     *  Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo();
     *  henkilot.getMaara() === 0;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 1;
     *  henkilot.lisaa(eetu2); henkilot.getMaara() === 2;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 3;
     * </pre>
     */
    public void lisaa(Henkilo henkilo) throws YleisException{
        if (this.henkilot_maara >= this.alkiot.length) {
            lisaaAlkioita();
            // throw new YleisException ("Henkilöiden taulukko (" + henkilot_maara + ") täyttyi. Kasvatatetaan taulukon kokoa yhdellä automaattisesti");
        }
        this.alkiot[this.henkilot_maara] = henkilo;
        this.henkilot_maara++;
    }
    
    /**
     * Lisätään alkioita taulukkoon.
     * 
     */
    public void lisaaAlkioita() {
        Henkilo[] apuTaulukko = new Henkilo[this.henkilot_maara + 10];
        for (int i = 0; i < this.alkiot.length; i++) {
            apuTaulukko[i] = this.alkiot[i];
        }
        this.henkilot_max++;
        this.alkiot = apuTaulukko;
    }
    
    
    /**
     * Poistetaan henkilön tiedot
     * @param id henkilön id
     */
    public void poista(int id) {
        int indeksi = haeIndeksi(id);
        this.henkilot_maara--;
        for (int i = indeksi; i < this.henkilot_maara; i++) {
            this.alkiot[i] = this.alkiot[i+1];
        }
    }
    
    
    /**
     * Haetaan henkilön indeksi listassa tunnusnumeron perusteella
     * @param id henkilön id
     * @return henkilön indeksi listassa
     */
    public int haeIndeksi(int id) {
        int indeksi = 0;
        for (int i = 0; i < this.getMaara(); i++) {
            if (this.alkiot[i].getTunnusNro() == id) indeksi = i;
        }
        return indeksi;
    }
    
    
    /**
     * Haetaan henkilö nimellä
     * @param nimi henkilön nimi jota haetaan
     * @return henkilön henkilö objekti
     */
    public Henkilo haeHenkiloNimella(String nimi) {
        Henkilo haettava = new Henkilo();
        for(int i = 0; i < this.getMaara(); i++) {
            if(this.alkiot[i].getNimi().equalsIgnoreCase(nimi)) haettava = this.alkiot[i];
        }
        return haettava;
    }
    
    
    /**
     * @param i haettavan henkilön viitteen indeksinumero taulukossa
     * @return viite henkilöön, jonka indeksi on i
     * @throws IndexOutOfBoundsException heittää exceptionin jos haettava i ei ole taulukon sisältämällä alueella
     * 
     * @example
     * <pre name="test">
     *  #THROWS YleisException
     *  Henkilot henkilot = new Henkilot();
     *  Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo();
     *  henkilot.getMaara() === 0;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 1;
     *  henkilot.lisaa(eetu2); henkilot.getMaara() === 2;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 3;
     *  henkilot.anna(0) === eetu1;
     *  henkilot.anna(1) === eetu2;
     *  henkilot.anna(2) === eetu1;
     *  henkilot.anna(1) == eetu1 === false;
     *  henkilot.anna(1) == eetu2 === true;
     *  henkilot.anna(100000) === eetu1; #THROWS IndexOutOfBoundsException
     *  henkilot.anna(-1) === eetu1; #THROWS IndexOutOfBoundsException  
     * </pre>
     */
    public Henkilo anna(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (this.henkilot_maara <= i)) throw new IndexOutOfBoundsException("Haettava indeksi (" + i + ") on taulukon rajojen ulkopuolella.");
        return this.alkiot[i];
    }
    
    
    /**
     * Palauttaa kaikki henkilot
     * @return kaikki henkilot
     */
    public Henkilo[] getHenkilot() {
        Henkilo[] taulukko = new Henkilo[this.getMaara()];
        for(int i = 0; i < taulukko.length; i++) {
            taulukko[i] = this.alkiot[i];
        }
        return taulukko;
    }
    
    
    /**
     * Haetaan henkilöiden määrä
     * @return henkilöiden määrä taulukossa
     * 
     * @example
     * <pre name="test">
     *  #THROWS YleisException
     *  Henkilot henkilot = new Henkilot();
     *  Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo();
     *  henkilot.getMaara() === 0;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 1;
     *  henkilot.lisaa(eetu2); henkilot.getMaara() === 2;
     *  henkilot.lisaa(eetu1); henkilot.getMaara() === 3;
     * </pre>
     */
    public int getMaara() {
        return this.henkilot_maara;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, josta henkilöt luetaan
     * @return tiedoston nimi, josta henkilöt luetaan
     * 
     * @example
     * <pre name="test">
     * Henkilot henkilot = new Henkilot();
     * henkilot.getTiedostonNimi() === "henkilot.dat";
     * </pre>
     */
    public String getTiedostonNimi() {
        return this.tiedostonNimi;
    }
    
    
    /**
     * Palauttaa henkilöiden kaupungin
     * @return henkilöiden kaupunki
     * 
     * @example
     * <pre name="test">
     * Henkilot henkilot = new Henkilot();
     * henkilot.getKaupunki() === "Jyväskylä";
     * </pre>
     */
    public String getKaupunki() {
        return this.kaupunki;
    }
    
    
    /**
     * Asettaa kaupungin nimen
     * @param nimi kaupungin nimi
     */
    public void setKaupunki(String nimi) {
        this.kaupunki = nimi;
    }
    
    
    /**
     * Haetaan henkilöiden määrä
     * @param maara paljonko henkilöiden määrä on
     */
    public void setMaara(int maara) {
        this.henkilot_maara = maara;
    }
    
    
    /**
     * Asettaa tiedoston nimen, josta henkilöt luetaan
     * @param nimi tiedoston nimi
     */
    public void setTiedostonNimi(String nimi) {
        this.tiedostonNimi = nimi;
    }
    
    
    /**
     * Luetaan henkilöt tiedostosta
     * @param nimi tiedoston nimi
     * @throws YleisException poikkeus jos epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * #import java.io.File;
     * Henkilot henkilot = new Henkilot();
     * Henkilo t1 = new Henkilo(), t2 = new Henkilo();
     * t1.rekisteroi();
     * t2.rekisteroi();
     * t1.esimerkkiTiedot();
     * t2.esimerkkiTiedot();
     * String tiedosto = "testivapari.dat";
     * File ftied = new File(tiedosto);
     * ftied.delete();
     * henkilot.lueTiedostosta(tiedosto); #THROWS YleisException
     * henkilot.lisaa(t1);
     * henkilot.lisaa(t2);
     * henkilot.tallenna();
     * henkilot.anna(0) === t1;
     * henkilot.anna(1) === t2;
     * henkilot = new Henkilot();
     * henkilot.lueTiedostosta(tiedosto);
     * henkilot.anna(0).equals(t1);
     * henkilot.anna(1).equals(t2);
     * ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws YleisException {
        setTiedostonNimi(nimi);
        try (BufferedReader fi = new BufferedReader(new FileReader(this.getTiedostonNimi())) ) {
            this.kaupunki = fi.readLine();
            if (kaupunki == null) throw new YleisException("Kaupungin nimi puuttuu tiedostosta");
            
            String rivi;
            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi)) continue;
                Henkilo henkilo = new Henkilo();
                henkilo.parse(rivi);
                this.lisaa(henkilo);
            }
        } catch (FileNotFoundException e) {
            throw new YleisException("Tiedosto " + this.getTiedostonNimi() + " ei aukea.");
        } catch (IOException e) {
            throw new YleisException("Ongelmia tiedoston kanssa " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan henkilöt tiedostosta
     * @throws YleisException poikkeus jos jokin menee pieleen
     */
    public void lueTiedostosta() throws YleisException {
        lueTiedostosta(getTiedostonNimi());
    }
    
    
    /**
     * Tallennetaan tiedot tiedostoon
     * @throws YleisException poikkeus jos jotain menee pieleen
     */
    public void tallenna() throws YleisException {
        File ftied = new File(getTiedostonNimi());
        
        try ( PrintWriter fo = new PrintWriter(new FileWriter(ftied.getCanonicalPath())) ) {
            fo.println(getKaupunki());
            for (int i = 0; i < this.henkilot_maara; i++) {
                fo.println(this.alkiot[i].toString());
            }
        } catch (FileNotFoundException e) {
            throw new YleisException("Tiedosto " + ftied.getName() + " ei aukea.");
        } catch (IOException e) {
            throw new YleisException("Tiedoston " + ftied.getName() + " kirjoittamisessa on ongelmia.");
        }
    }
    
    
    /**
     * Testiohjelma luokalle
     * @param args ei käytössä
     * @throws YleisException tallenna saattaa heittää
     */
    public static void main(String[] args) throws YleisException {
        Henkilot henkilot = new Henkilot();
        Henkilo mikko = new Henkilo(), mikko2 = new Henkilo();
        mikko.rekisteroi();
        mikko.esimerkkiTiedot();
        mikko2.rekisteroi();
        mikko2.esimerkkiTiedot();

        // Try-Catch, koska lisaa metodissa on sanottu että voi heittää exceptionin
        // ja jonkun pitää ottaa se kiinni.
         try {
            henkilot.lisaa(mikko);
            henkilot.lisaa(mikko2);
            henkilot.lisaa(mikko2);

            System.out.println("-------------------- JÄSENET TESTI --------------------");
            
            for(int i = 0; i < henkilot.getMaara(); i++) {
                Henkilo henkilo = henkilot.anna(i);
                System.out.println("Henkilö nro: " + i);
                henkilo.tulosta(System.out);
            }
        } catch (YleisException ex) {
            System.err.println(ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
        }
        
        henkilot.tallenna();
    
    }

}
