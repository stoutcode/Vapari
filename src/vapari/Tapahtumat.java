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
 * | Luokan nimi:   Tapahtumat                          | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | Tapahtuma         | 
 * | - ylläpitää tapahtumat.dat tiedostoa eli osaa      |                   | 
 * |   lukea ja kirjoitaa kyseiseen tiedostoon          |                   | 
 * | - osaa suorittaa hakuja ja lajitteluja             |                   | 
 * |   tapahtumien osalta                               |                   |
 * | - osaa poistaa ja lisätä yksittäisiä tapahtumia    |                   | 
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
public class Tapahtumat {
    
    private String tiedostonNimi = "tapahtumat.dat";   // Mistä tiedostosta luetaan ja mihin kirjoitetaan, tulevaisuutta varten
    
    private final ArrayList<Tapahtuma> alkiot = new ArrayList<Tapahtuma>(); // Taulukko tapahtumille
    
    
    /**
     * Oletusmuodostaja tapahtumat oliolle
     * Saa ominaisuudekseen tyhjän objektilistan
     */
    public Tapahtumat() {
        // 
    }
    
    
    /**
     * Lisätään tapahtumalistaan haluttu tapahtuma
     * @param tapahtuma tapahtuma joka halutaan lisätä listaan
     * 
     * @example
     * <pre name="test">
     * Tapahtumat jyvaskyla = new Tapahtumat();
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public void lisaa(Tapahtuma tapahtuma) {
        this.alkiot.add(tapahtuma);
    }
    
    
    /**
     * Poistetaan tapahtuma listasta
     * @param rekisterointiNro tapahtuman rekisteröintinumero 
     * 
     * @example
     * <pre name="test">
     * Tapahtumat jyvaskyla = new Tapahtumat();
     * Tapahtuma siivous = new Tapahtuma();
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
     * Poistetaan kaikki henkilön Tapahtumat id:n perusteella
     * @param id henkilön tunnistenumero
     * 
     * @example
     * <pre name="test">
     * Tapahtumat jyvaskyla = new Tapahtumat();
     * Tapahtuma siivous = new Tapahtuma(1, "siivous", "11.2.2011", 2);
     * siivous.rekisteroi();
     * Tapahtuma ruoanlaitto = new Tapahtuma(1, "ruoanlaitto", "12.2.2011", 2);
     * ruoanlaitto.rekisteroi();
     * Tapahtuma nuohous = new Tapahtuma(1, "nuohous", "13.2.2011", 2);
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
            if (this.alkiot.get(i).getTunnusNro() == id || this.alkiot.get(i).getToinenId() == id) {
                this.alkiot.remove(i);
                i--;
            }
        }
    }
    
    
    /**
     * Palauttaa Tapahtumajen määrän listassa
     * @return Tapahtumajen määrä listassa
     * 
     * @example
     * <pre name="test">
     * Tapahtumat jyvaskyla = new Tapahtumat();
     * Tapahtuma siivous = new Tapahtuma();
     * siivous.rekisteroi();
     * jyvaskyla.lisaa(siivous);
     * jyvaskyla.getMaara() === 1;
     * </pre>
     */
    public int getMaara() {
        return this.alkiot.size();
    }
    
    
    /**
     * Päivitetään kaupungin Tapahtumat lista henkilön tapahtumilla
     * Ensin katsotaan onko alkoissa sellaisia tapahtumia, joita ei enää ole henkilön listalla
     * Sitten päivitetään alkoiden tapahtumia henkilön listalla olevien tapahtumien tiedoilla
     * Lopuksi lisätään alkioihin sellaiset tapahtumat, joita ei vielä ollut siellä
     * @param tapahtumia lista henkilön tapahtumista, jotka halutaan lisätä kaupungin Tapahtumiin
     * @param id henkilön id
     */
    public void paivitaTapahtumat(ArrayList<Tapahtuma> tapahtumia, int id) {
        
        // Katsotaan ensin onko henkilön tapahtumia poistettu
        // ja päivitetään kaupungin Tapahtumat vastaamaan tilannetta
        for (int i = 0; i < this.alkiot.size(); i++) {
            boolean poistetaanko = true;
            if (this.alkiot.get(i).getTunnusNro() == id) {
                for (int u = 0; u < tapahtumia.size(); u++) {
                    // Jos alkoiden tapahtumille löytyy rekisterinumero nykyisistä henkilön tapahtumista,
                    // niin poistamista ei tarvitse tehdä.
                    if (this.alkiot.get(i).getRekisterointiNro() == tapahtumia.get(u).getRekisterointiNro())
                        poistetaanko = false;
                }
                if (poistetaanko) {
                    // Poistetaan tapahtuman duplikaatti toiselta henkilöltä
                    for (int u = 0; u < this.alkiot.size(); u++) {
                        if(alkiot.get(i).getRekisterointiNro() == alkiot.get(u).getRekisterointiNro()
                                && alkiot.get(i).getTunnusNro() == alkiot.get(u).getToinenId() ) {
                            this.alkiot.remove(u);
                            // Tiputetaan i:n arvoa, jos se oli ennen o:ta, jotta ulompi looppi toimii oikein
                            if (u < i) i--;
                            break;
                        }
                    }
                    // Poistetaan alkuperäinen tapahtuma
                    this.alkiot.remove(i);
                    i--;
                }
            } 
        }
        
        // Päivitetään toisen osapuolen tapahtuman tiedot tapahtumia listalla olevilla tiedoilla
        for (int i = 0; i < tapahtumia.size(); i++) {
            for (int u = 0; u < alkiot.size(); u++) {
                if(tapahtumia.get(i).getRekisterointiNro() == alkiot.get(u).getRekisterointiNro()
                        && tapahtumia.get(i).getTunnusNro() == alkiot.get(u).getToinenId()) {
                    this.alkiot.get(u).setTunnusNro(tapahtumia.get(i).getToinenId());
                    this.alkiot.get(u).setToiminto(tapahtumia.get(i).getToiminto());
                    this.alkiot.get(u).setPvm(tapahtumia.get(i).getPvm());
                }
            }
        }
        
        // Seuraavaksi katsotaan onko päällekkäisyyksiä listoissa
        // Jos on, niin väliaikaiselta listalta poistetaan päällekkäinen alkio
        for (int i = 0; i < tapahtumia.size(); i++) {
            for (int u = 0; u < this.alkiot.size(); u++) {
                if (tapahtumia.get(i).getTunnusNro() == this.alkiot.get(u).getTunnusNro() 
                        && tapahtumia.get(i).getPvm() == this.alkiot.get(u).getPvm() 
                        && tapahtumia.get(i).getToiminto() == this.alkiot.get(u).getToiminto()) {
                        tapahtumia.remove(i);
                        i--;
                        break;
                }
            }
        }
        
        // Lisätään kaupungin listalle sellaiset Tapahtumat, jotka ovat yhä Tapahtumat listalla
        for (int i = 0; i < tapahtumia.size(); i++) {
            this.alkiot.add(tapahtumia.get(i));
        }
        
        // Lopuksi lisätään lisätyille tapahtumille duplikaatit toisen osapuolen nimiin
        for (int i = 0; i < tapahtumia.size(); i++) {
            Tapahtuma tupla = new Tapahtuma(tapahtumia.get(i).getToinenId(), tapahtumia.get(i).getToiminto(), tapahtumia.get(i).getPvm(), tapahtumia.get(i).getTunnusNro());
            tupla.setRekistointiNro(tapahtumia.get(i).getRekisterointiNro());
            this.lisaa(tupla);
        }
        
    }        
    
    
    /**
     * Haetaan Tapahtuma listasta indeksin perusteella
     * @param i indeksi jota haetaan
     * @return Tapahtuma indeksissä
     */
    public Tapahtuma haeTapahtuma(int i) {
        return this.alkiot.get(i);
    }
    
    
    /**
     * Palauttaa henkilön Tapahtumat listana
     * @param id henkilön tunnistenumero
     * @return lista henkilön tapahtumista
     */
    public ArrayList<Tapahtuma> haeTapahtumat (int id) {
        ArrayList<Tapahtuma> palautus = new ArrayList<Tapahtuma>();
        for (Tapahtuma Tapahtuma : this.alkiot) {
            if (Tapahtuma.getTunnusNro() == id) palautus.add(Tapahtuma);
        }
        return palautus;
    }
    
    
    /**
     * Palautetaan kaikki Tapahtumat
     * @return viite kaikkien Tapahtumajen listaan
     */
    public ArrayList<Tapahtuma> getTapahtumat() {
        return this.alkiot;
    }
    
    
    /**
     * Palauttaa tiedoston nimen, josta tapahtumat luetaan
     * @return tiedoston nimi, josta tapahtumat luetaan
     * 
     * @example
     * <pre name="test">
     * Tapahtumat tapahtumat = new Tapahtumat();
     * tapahtumat.getTiedostonNimi() === "tapahtumat.dat";
     * </pre>
     */
    public String getTiedostonNimi() {
        return this.tiedostonNimi;
    }
    
    
    
    
    /**
     * Asettaa tiedoston nimen, josta tapahtumat luetaan
     * @param nimi tiedoston nimi
     */
    public void setTiedostonNimi(String nimi) {
        this.tiedostonNimi = nimi;
    }
    
    
    /**
     * Luetaan tapahtumat tiedostosta
     * @param nimi tiedoston nimi
     * @throws YleisException poikkeus jos epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * #import java.io.File;
     * Tapahtumat tapahtumat = new Tapahtumat();
     * Tapahtuma t1 = new Tapahtuma(), t2 = new Tapahtuma();
     * t1.rekisteroi();
     * t2.rekisteroi();
     * t1.esimerkkiTiedot();
     * t2.esimerkkiTiedot();
     * t1.getTunnusNro() === 1;
     * t2.getTunnusNro() === 1;
     * String tiedosto = "testivapari.dat";
     * File ftied = new File(tiedosto);
     * ftied.delete();
     * tapahtumat.lueTiedostosta(tiedosto); #THROWS YleisException
     * tapahtumat.lisaa(t1);
     * tapahtumat.lisaa(t2);
     * tapahtumat.tallenna();
     * tapahtumat.haeTapahtuma(0) === t1;
     * tapahtumat.haeTapahtuma(1) === t2;
     * tapahtumat = new Tapahtumat();
     * tapahtumat.lueTiedostosta(tiedosto);
     * tapahtumat.haeTapahtuma(0).equals(t1);
     * tapahtumat.haeTapahtuma(1).equals(t2);
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
                Tapahtuma tapahtuma = new Tapahtuma();
                tapahtuma.parse(rivi);
                this.lisaa(tapahtuma);
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
     * @throws YleisException tallenna voi heittää
     */
    public static void main(String[] args) throws YleisException {
        Tapahtumat jyvaskyla = new Tapahtumat();
        Tapahtuma siivous = new Tapahtuma(1, "siivous", "12-12-2012", 2);
        siivous.rekisteroi();
        Tapahtuma ruoanlaitto = new Tapahtuma(1, "ruoanlaitto", "13-12-2012", 2);
        ruoanlaitto.rekisteroi();
        Tapahtuma nuohous = new Tapahtuma(1, "nuohous", "14-12-2012", 2);
        nuohous.rekisteroi();
        System.out.println(jyvaskyla.getMaara());
        jyvaskyla.lisaa(siivous);
        jyvaskyla.lisaa(ruoanlaitto);
        jyvaskyla.lisaa(nuohous);
        jyvaskyla.lisaa(nuohous);
        System.out.println(jyvaskyla.getMaara());
        jyvaskyla.tallenna();
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.print(jyvaskyla.alkiot.get(i).getPvm() + " ");
            System.out.print(jyvaskyla.alkiot.get(i).getToiminto()+ " ");
            System.out.print(jyvaskyla.alkiot.get(i).getToinenId());
            System.out.println();
        }
        jyvaskyla.poistaHenkilo(1);
        System.out.println(jyvaskyla.getMaara());
        for (int i = 0; i < jyvaskyla.getMaara(); i++) {
            System.out.print(jyvaskyla.alkiot.get(i).getPvm() + " ");
            System.out.print(jyvaskyla.alkiot.get(i).getToiminto() + " ");
            System.out.print(jyvaskyla.alkiot.get(i).getToinenId());
            System.out.println();
        }
    }
    
}
