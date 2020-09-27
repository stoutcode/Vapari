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
import java.util.ArrayList;
import java.util.Arrays;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Toiminnot                           | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | Toiminto          | 
 * | - ylläpitää toiminnot.dat tiedostoa eli osaa       |                   | 
 * |   lukea ja kirjoitaa kyseiseen tiedostoon          |                   | 
 * | - osaa suorittaa hakuja ja lajitteluja             |                   | 
 * |   toimintojen osalta                               |                   |
 * | - osaa poistaa ja lisätä yksittäisiä toimintoja    |                   |
 * | - tietää kaikki vaihtoehtoiset toiminnot           |                   |
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
public class Toiminnot {
    
    private String tiedostonNimi = "toiminnot.dat";   // Mistä tiedostosta luetaan ja mihin kirjoitetaan, tulevaisuutta varten
    
    private final ArrayList<Toiminto> alkiot = new ArrayList<Toiminto>(); // Taulukko toiminnoille
    private final ArrayList<String> toiminnot = new ArrayList<String>(Arrays.asList(
            "siivous", "lumityot", "kauppareissu", "koiran ulkoilutus", "eläimen ulkoilutus",
            "ulkoilureissu", "puutyöt", "ruohonleikkaus", "pienet korjaustyöt", "atk-apu",
            "kulttuurireissu", "pihatyöt", "seuranpito", "asiointireissu"));
    
    /**
     * Perusmuodostaja, jossa Toiminnot saa ominaisuudekseen tyhjän objektilistan
     */
    public Toiminnot() {
        // Pistetään tässä vaiheessa oletustoiminnot järjestykseen
        java.util.Collections.sort(toiminnot);
    }
    
    
    /**
     * Palauttaa vaihtoehtoiset toiminnot listana
     * @return vaihtoehtoiset toiminnot listana
     */
    public ArrayList<String> vaihtoehdot() {
        return this.toiminnot;
    }
    
    
    /**
     * Haetaan tietyn toiminnon indeksi toimintojen vaihtoehtotaulukosta
     * @param toiminto toiminto jonka indeksiä haetaan toimintojen vaihtoehtotaulukosta
     * @return toiminnon indeksi
     */
    public int getToimintoIndex(Toiminto toiminto) {
        int indeksi = 0;
        for (int i = 0; i < this.vaihtoehdot().size(); i++) {
            if (toiminto.getToiminto().toString() == this.vaihtoehdot().get(i).toString()) indeksi = i;
        }
        return indeksi;
    }
    
    
    /**
     * Haetaan tietyn toimintostringin indeksi toimintojen vaihtoehtotaulukosta
     * @param toiminto toiminto stringinä, jonka indeksiä haetaan toimintojen vaihtoehtotaulukosta
     * @return toiminnon indeksi
     */
    public int getToimintoIndex(String toiminto) {
        int indeksi = 0;
        for (int i = 0; i < this.vaihtoehdot().size(); i++) {
            if (toiminto == this.vaihtoehdot().get(i).toString()) indeksi = i;
        }
        return indeksi;
    }
    
    
    /**
     * Lisätään Toimintolistaan haluttu toiminto
     * @param toiminto toiminto joka halutaan lisätä listaan
     * 
     * @example
     * <pre name="test">
     * Toiminnot jyvaskyla = new Toiminnot();
     * Toiminto siivous = new Toiminto();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public void lisaa(Toiminto toiminto) {
        this.alkiot.add(toiminto);
    }
    
    
    /**
     * Poistetaan toiminto listasta
     * @param rekisterointiNro toiminnon rekisteröintinumero 
     * 
     * @example
     * <pre name="test">
     * Toiminnot jyvaskyla = new Toiminnot();
     * Toiminto siivous = new Toiminto();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * jyvaskyla.poista(siivous.getRekisterointiNro());
     * jyvaskyla.getMaara() === 0;
     * </pre>
     */
    public void poista(int rekisterointiNro) {
        for (int i = 0; i < this.alkiot.size(); i++) {
            if (this.alkiot.get(i).getRekisterointiNro() == rekisterointiNro) {
                this.alkiot.remove(i);
                break;
            }
        }
    }
    
    
    /**
     * Poistetaan kaikki henkilön toiminnot id:n perusteella
     * @param id henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Toiminnot jyvaskyla = new Toiminnot();
     * Toiminto siivous = new Toiminto(1, "siivous");
     * siivous.rekisteroi();
     * Toiminto ruoanlaitto = new Toiminto(1, "ruoanlaitto");
     * ruoanlaitto.rekisteroi();
     * Toiminto nuohous = new Toiminto(1, "nuohous");
     * nuohous.rekisteroi();
     * jyvaskyla.getMaara() === 0;
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.lisaa(ruoanlaitto);
     * jyvaskyla.lisaa(nuohous);
     * jyvaskyla.lisaa(nuohous);
     * jyvaskyla.getMaara() === 4;
     * jyvaskyla.poistaHenkilo(1);
     * jyvaskyla.getMaara() === 0;
     * </pre>
     */
    public void poistaHenkilo(int id) {
        for (int i = 0; i < this.getMaara(); i++) {
            if (this.alkiot.get(i).getTunnusNro() == id) {
                this.alkiot.remove(i);
                i--;
            }
        }
    }
    
    
    /**
     * Palauttaa toimintojen määrän listassa
     * @return toimintojen määrä listassa
     * 
     * @example
     * <pre name="test">
     * Toiminnot jyvaskyla = new Toiminnot();
     * Toiminto siivous = new Toiminto();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public int getMaara() {
        return this.alkiot.size();
    }
    
    
    /**
     * Päivitetään kaupungin toiminnot lista henkilön toiminnoilla
     * @param toimintoja lista henkilön toiminnoista, jotka halutaan lisätä kaupungin toimintoihin
     * @param id henkilön id
     */
    public void paivitaToiminnot(ArrayList<Toiminto> toimintoja, int id) {
        // Katsotaan ensin onko henkilön toimintoja poistettu
        // ja päivitetään kaupungin toiminnot vastaamaan tilannetta
        for (int i = 0; i < this.alkiot.size(); i++) {
            boolean poistetaanko = true;
            if (this.alkiot.get(i).getTunnusNro() == id) {
                for (int u = 0; u < toimintoja.size(); u++) {
                    if (this.alkiot.get(i).getRekisterointiNro() == toimintoja.get(u).getRekisterointiNro() ) poistetaanko = false;
                }
                if (poistetaanko) {
                    this.alkiot.remove(i);
                    i--;
                }
            } 
        }
        // Seuraavaksi katsotaan onko päällekkäisyyksiä listoissa
        // Jos on, niin väliaikaiselta listalta poistetaan päälleikkäinen alkio
        for (int i = 0; i < toimintoja.size(); i++) {
            for (int u = 0; u < this.alkiot.size(); u++) {
                if (toimintoja.get(i).getTunnusNro() == this.alkiot.get(u).getTunnusNro()) {
                    if (toimintoja.get(i).getToiminto() == this.alkiot.get(u).getToiminto()) {
                        toimintoja.remove(i);
                        i--;
                        break;
                    }
                        
                }
            }
        }
        // Lopuksi lisätään kaupungin listalle sellaiset toiminnot, jotka ovat yhä toiminnot listalla
        for (int i = 0; i < toimintoja.size(); i++) {
            this.alkiot.add(toimintoja.get(i));
        }
    }
    
    
    /**
     * Haetaan toiminto listasta indeksin perusteella
     * @param i indeksi jota haetaan
     * @return toiminto indeksissä
     */
    public Toiminto haeToiminto(int i) {
        return this.alkiot.get(i);
    }
    
    
    /**
     * Palauttaa henkilön toiminnot listana
     * @param id henkilön tunnistenumero
     * @return lista henkilön toiminnoista
     */
    public ArrayList<Toiminto> haeToiminnot (int id) {
        ArrayList<Toiminto> palautus = new ArrayList<Toiminto>();
        for (Toiminto toiminto : this.alkiot) {
            if (toiminto.getTunnusNro() == id) palautus.add(toiminto);
        }
        return palautus;
    }
    
    
    /**
     * Palautetaan kaikki toiminnot
     * @return viite kaikkien toimintojen listaan
     */
    public ArrayList<Toiminto> getToiminnot() {
        return this.alkiot;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, josta toiminnot luetaan
     * @return tiedoston nimi, josta toiminnotluetaan
     * 
     * @example
     * <pre name="test">
     * Toiminnot toiminnot = new Toiminnot();
     * toiminnot.getTiedostonNimi() === "toiminnot.dat";
     * </pre>
     */
    public String getTiedostonNimi() {
        return this.tiedostonNimi;
    }
    
    
    
    
    /**
     * Asettaa tiedoston nimen, josta toiminnot luetaan
     * @param nimi tiedoston nimi
     */
    public void setTiedostonNimi(String nimi) {
        this.tiedostonNimi = nimi;
    }
    
    
    /**
     * Luetaan toiminnot tiedostosta
     * @param nimi tiedoston nimi
     * @throws YleisException poikkeus jos epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * #import java.io.File;
     * Toiminnot toiminnot = new Toiminnot();
     * Toiminto t1 = new Toiminto(), t2 = new Toiminto();
     * t1.rekisteroi();
     * t2.rekisteroi();
     * t1.esimerkkiTiedot();
     * t2.esimerkkiTiedot();
     * t1.getTunnusNro() === 1;
     * t2.getTunnusNro() === 1;
     * String tiedosto = "testivapari.dat";
     * File ftied = new File(tiedosto);
     * ftied.delete();
     * toiminnot.lueTiedostosta(tiedosto); #THROWS YleisException
     * toiminnot.lisaa(t1);
     * toiminnot.lisaa(t2);
     * toiminnot.tallenna();
     * toiminnot.haeToiminto(0) === t1;
     * toiminnot.haeToiminto(1) === t2;
     * toiminnot = new Toiminnot();
     * toiminnot.lueTiedostosta(tiedosto);
     * toiminnot.haeToiminto(0).equals(t1);
     * toiminnot.haeToiminto(1).equals(t2);
     * ftied.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws YleisException {
        setTiedostonNimi(nimi);
        try (BufferedReader fi = new BufferedReader(new FileReader(this.getTiedostonNimi())) ) {
            String rivi;
            while ((rivi = fi.readLine()) != null) {
                rivi = rivi.trim();
                if ("".equals(rivi)) continue;
                Toiminto toiminto = new Toiminto();
                toiminto.parse(rivi);
                this.lisaa(toiminto);
            }
        } catch (FileNotFoundException e) {
            throw new YleisException("Tiedosto " + this.getTiedostonNimi() + " ei aukea.");
        } catch (IOException e) {
            throw new YleisException("Ongelmia tiedoston kanssa " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan toiminnot tiedostosta
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
            for (int i = 0; i < this.getMaara(); i++) {
                fo.println(this.alkiot.get(i).toString());
            }
        } catch (FileNotFoundException e) {
            throw new YleisException("Tiedosto " + ftied.getName() + " ei aukea.");
        } catch (IOException e) {
            throw new YleisException("Tiedoston " + ftied.getName() + " kirjoittamisessa on ongelmia.");
        }
    }
    
    
    /**
     * Testipääohjelma
     * @param args ei käytössä
     * @throws YleisException jos tallenna heittää virheen
     */
    public static void main(String[] args) throws YleisException {
        Toiminnot jyvaskyla = new Toiminnot();
        Toiminto siivous = new Toiminto(1, "siivous");
        siivous.rekisteroi();
        Toiminto ruoanlaitto = new Toiminto(1, "ruoanlaitto");
        ruoanlaitto.rekisteroi();
        Toiminto nuohous = new Toiminto(1, "nuohous");
        nuohous.rekisteroi();
        System.out.println(jyvaskyla.getMaara());
        jyvaskyla.lisaa(siivous);
        jyvaskyla.lisaa(ruoanlaitto);
        jyvaskyla.lisaa(nuohous);
        jyvaskyla.lisaa(nuohous);
        jyvaskyla.tallenna();
        System.out.println(jyvaskyla.getMaara());
        
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.println(jyvaskyla.alkiot.get(i).getToiminto());
        }
        jyvaskyla.poistaHenkilo(1);
        System.out.println(jyvaskyla.getMaara());
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.println(jyvaskyla.alkiot.get(i).getToiminto());
        }
    }
    
}
