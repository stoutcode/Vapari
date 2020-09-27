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

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Lisatiedot                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | Lisatieto         | 
 * | - ylläpitää lisatiedot.dat tiedostoa eli osaa      |                   | 
 * |   lukea ja kirjoitaa kyseiseen tiedostoon          |                   | 
 * | - osaa suorittaa hakuja ja lajitteluja             |                   | 
 * |   lisätietojen osalta                              |                   |
 * | - osaa poistaa ja lisätä yksittäisiä lisätietoja   |                   | 
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
public class Lisatiedot {
    
    private String tiedostonNimi = "lisatiedot.dat";    // Mistä tiedostosta luetaan ja mihin kirjoitetaan, tulevaisuutta varten
    
    private final ArrayList<Lisatieto> alkiot = new ArrayList<Lisatieto>(); // Taulukko lisätiedoille
    
    /**
     * Muodostaja
     */
    public Lisatiedot() {
        // ei tarvita muuta nyt
    }
    
    
    /**
     * Lisätään lisätietolistaan haluttu lisätieto
     * @param lisatieto lisätieto joka halutaan lisätä listaan
     * 
     * @example
     * <pre name="test">
     * Lisatiedot jyvaskyla = new Lisatiedot();
     * Lisatieto siivous = new Lisatieto();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public void lisaa(Lisatieto lisatieto) {
        this.alkiot.add(lisatieto);
    }
    
    
    /**
     * Poistetaan lisätieto listasta
     * @param rekisterointiNro lisätiedon rekisteröintinumero 
     * 
     * @example
     * <pre name="test">
     * Lisatiedot jyvaskyla = new Lisatiedot();
     * Lisatieto siivous = new Lisatieto();
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
     * Poistetaan kaikki henkilön lisätiedot id:n perusteella
     * @param id henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Lisatiedot jyvaskyla = new Lisatiedot();
     * Lisatieto siivous = new Lisatieto(1, "siivous");
     * siivous.rekisteroi();
     * Lisatieto ruoanlaitto = new Lisatieto(1, "ruoanlaitto");
     * ruoanlaitto.rekisteroi();
     * Lisatieto nuohous = new Lisatieto(1, "nuohous");
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
     * Palauttaa lisätietojen määrän listassa
     * @return toimintojen määrä listassa
     * 
     * @example
     * <pre name="test">
     * Lisatiedot jyvaskyla = new Lisatiedot();
     * Lisatieto siivous = new Lisatieto();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public int getMaara() {
        return this.alkiot.size();
    }
    
    
    /**
     * Päivitetään kaupungin lisätiedot lista henkilön lisätiedoilla
     * @param lisatietoja lista henkilön lisätiedoista, jotka halutaan lisätä kaupungin lisätietoihin
     * @param id henkilön id
     */
    public void paivitaLisatiedot(ArrayList<Lisatieto> lisatietoja, int id) {
        // Katsotaan ensin onko henkilön toimintoja poistettu
        // ja päivitetään kaupungin lisätiedot vastaamaan tilannetta
        for (int i = 0; i < this.alkiot.size(); i++) {
            boolean poistetaanko = true;
            if (this.alkiot.get(i).getTunnusNro() == id) {
                for (int u = 0; u < lisatietoja.size(); u++) {
                    if (this.alkiot.get(i).getRekisterointiNro() == lisatietoja.get(u).getRekisterointiNro() ) poistetaanko = false;
                }
                if (poistetaanko) {
                    this.alkiot.remove(i);
                    i--;
                }
            } 
        }
        // Seuraavaksi katsotaan onko päällekkäisyyksiä listoissa
        // Jos on, niin väliaikaiselta listalta poistetaan päälleikkäinen alkio
        for (int i = 0; i < lisatietoja.size(); i++) {
            for (int u = 0; u < this.alkiot.size(); u++) {
                if (lisatietoja.get(i).getTunnusNro() == this.alkiot.get(u).getTunnusNro()) {
                    if (lisatietoja.get(i).getLisatieto() == this.alkiot.get(u).getLisatieto()) {
                        lisatietoja.remove(i);
                        i--;
                        break;
                    }
                        
                }
            }
        }
        // Lopuksi lisätään kaupungin listalle sellaiset lisätiedot, jotka ovat yhä lisätiedot listalla
        for (int i = 0; i < lisatietoja.size(); i++) {
            this.alkiot.add(lisatietoja.get(i));
        }
    }
    
    
    /**
     * Haetaan lisätieto listasta indeksin perusteella
     * @param i indeksi jota haetaan
     * @return lisätieto indeksissä
     */
    public Lisatieto haeLisatieto(int i) {
        return this.alkiot.get(i);
    }
    
    
    /**
     * Palauttaa henkilön lisätiedot listana
     * @param id henkilön tunnistenumero
     * @return lista henkilön lisätiedoista
     */
    public ArrayList<Lisatieto> haeLisatiedot (int id) {
        ArrayList<Lisatieto> palautus = new ArrayList<Lisatieto>();
        for (Lisatieto lisatieto : this.alkiot) {
            if (lisatieto.getTunnusNro() == id) palautus.add(lisatieto);
        }
        return palautus;
    }
    
    
    /**
     * Palautetaan kaikki lisätiedot
     * @return viite kaikkien lisätietojen listaan
     */
    public ArrayList<Lisatieto> getLisatiedot() {
        return this.alkiot;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, josta lisatiedot luetaan
     * @return tiedoston nimi, josta lisatiedot luetaan
     * 
     * @example
     * <pre name="test">
     * Lisatiedot lisatiedot = new Lisatiedot();
     * lisatiedot.getTiedostonNimi() === "lisatiedot.dat";
     * </pre>
     */
    public String getTiedostonNimi() {
        return this.tiedostonNimi;
    }
    
    
    
    
    /**
     * Asettaa tiedoston nimen, josta lisatiedot luetaan
     * @param nimi tiedoston nimi
     */
    public void setTiedostonNimi(String nimi) {
        this.tiedostonNimi = nimi;
    }
    
    
    /**
     * Luetaan lisatiedot tiedostosta
     * @param nimi tiedoston nimi
     * @throws YleisException poikkeus jos epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * #import java.io.File;
     * Lisatiedot lisatiedot = new Lisatiedot();
     * Lisatieto t1 = new Lisatieto(), t2 = new Lisatieto();
     * t1.rekisteroi();
     * t2.rekisteroi();
     * t1.esimerkkiTiedot();
     * t2.esimerkkiTiedot();
     * t1.getTunnusNro() === 1;
     * t2.getTunnusNro() === 1;
     * String tiedosto = "testivapari.dat";
     * File ftied = new File(tiedosto);
     * ftied.delete();
     * lisatiedot.lueTiedostosta(tiedosto); #THROWS YleisException
     * lisatiedot.lisaa(t1);
     * lisatiedot.lisaa(t2);
     * lisatiedot.tallenna();
     * lisatiedot.haeLisatieto(0) === t1;
     * lisatiedot.haeLisatieto(1) === t2;
     * lisatiedot = new Lisatiedot();
     * lisatiedot.lueTiedostosta(tiedosto);
     * lisatiedot.haeLisatieto(0).equals(t1);
     * lisatiedot.haeLisatieto(1).equals(t2);
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
                Lisatieto lisatieto = new Lisatieto();
                lisatieto.parse(rivi);
                this.lisaa(lisatieto);
            }
        } catch (FileNotFoundException e) {
            throw new YleisException("Tiedosto " + this.getTiedostonNimi() + " ei aukea.");
        } catch (IOException e) {
            throw new YleisException("Ongelmia tiedoston kanssa " + e.getMessage());
        }
    }
    
    
    /**
     * Luetaan tapahtumat tiedostosta
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
     * @throws YleisException jos tallenna heittää poikkeuksen
     */
    public static void main(String[] args) throws YleisException {
        Lisatiedot jyvaskyla = new Lisatiedot();
        Lisatieto siivous = new Lisatieto(1, "auto käytössä");
        siivous.rekisteroi();
        Lisatieto ruoanlaitto = new Lisatieto(1, "musikaalinen");
        ruoanlaitto.rekisteroi();
        Lisatieto nuohous = new Lisatieto(1, "koiraihminen");
        nuohous.rekisteroi();
        System.out.println(jyvaskyla.getMaara());
        jyvaskyla.lisaa(siivous);
        jyvaskyla.lisaa(ruoanlaitto);
        jyvaskyla.lisaa(nuohous);
        jyvaskyla.lisaa(nuohous);
        jyvaskyla.tallenna();
        System.out.println(jyvaskyla.getMaara());
        
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.println(jyvaskyla.alkiot.get(i).getLisatieto());
        }
        jyvaskyla.poistaHenkilo(1);
        System.out.println(jyvaskyla.getMaara());
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.println(jyvaskyla.alkiot.get(i).getLisatieto());
        }
    }

}
