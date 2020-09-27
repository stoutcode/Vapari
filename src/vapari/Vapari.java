/**
 * 
 */
package vapari;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * |------------------------------------------------------------------------|
 * | Luokan nimi:   Vapari                              | Avustajat:        |
 * |-------------------------------------------------------------------------
 * | Vastuualueet:                                      |                   | 
 * |                                                    | Henkilo           |  
 * | - huolehtii avustajien välisestä yhteistyöstä      | Henkilot          |
 * |   ja välittää niiden tietoja tarvittaessa          | Toiminto          |
 * | - hoitaa hakutietojen kysymisen avustajilta        | Toiminnot         |
 * | - esimerkkitapauksia:                              | Lisatieto         |
 * |   - kun henkilöä klikataan päänäkymän listassa,    | Lisatiedot        |
 * |     niin hakee henkilön tiedot, toiminnot ja       | Tapahtuma         |
 * |     lisätiedot päänäkymän ikkunoihin.              | Tapahtumat        |
 * |   - kun henkilön tapahtumaa muokataan tai luodaan  |                   |
 * |     uutta tapahtumaa henkilölle, niin välittää     |                   |
 * |     Tapahtuma luokalle listan toiminnoista         |                   |
 * |     ja kaupungin henkilöistä                       |                   |
 * |   - muodostaa henkilöistä listan päänäkymään       |                   |
 * |     hakutietojen perusteella                       |                   |
 * |-------------------------------------------------------------------------
 * @author mikar
 * @version 19 Feb 2020
 *
 */
public class Vapari {
    
    private Henkilot henkilot = new Henkilot();
    private Toiminnot toiminnot = new Toiminnot();
    private Lisatiedot lisatiedot = new Lisatiedot();
    private Tapahtumat tapahtumat = new Tapahtumat();
    
    
    /**
     * Palautetaan henkilöiden lukumäärä tässä kaupungissa
     * @return henkilöiden lukumäärä
     */
    public int getHenkiloita() {
        return this.henkilot.getMaara();
    }
    
    
    /**
     * Palautetaan toimintojen määrä tässä kaupungissa
     * @return toimintojen määrä
     */
    public int getToimintoja() {
        return this.toiminnot.getMaara();
    }
    
    
    /**
     * Palautetaan lisätietojen määrä tässä kaupungissa
     * @return lisatietojen määrä
     */
    public int getLisatietoja() {
        return this.lisatiedot.getMaara();
    }
    
    
    /**
     * Palautetaan tapahtumien määrä tässä kaupungissa
     * @return tapahtumien määrä
     */
    public int getTapahtumia() {
        return this.tapahtumat.getMaara();
    }
    
    
    /**
     * Palautetaan kaikki kaupungin henkilöt taulukkona
     * @return kaikki kaupungin henkilöt taulukkona
     */
    public Henkilo[] getHenkilot() {
        return this.henkilot.getHenkilot();
    }
    
    
    /**
     * Muutetaan nykyisten henkilöiden määrää
     * @param maara paljonko on henkilöiden määrä
     */
    public void setHenkiloita(int maara) {
        this.henkilot.setMaara(maara);
    }
    
    
    /**
     * Haetaan henkilö nimen perusteella
     * @param nimi henkilön nimi jota haetaan
     * @return henkilön henkilö objekti
     */
    public Henkilo haeHenkiloNimella(String nimi) {
        return this.henkilot.haeHenkiloNimella(nimi);
    }
    
    
    /**
     * Palauttaa i:nnen henkilön kaupungin henkilötaulukosta
     * @param i monesko henkilö palautetaan
     * @return viite i:nteen jäseneen
     * @throws IndexOutOfBoundsException heittää exceptionin jos i ei osu taulukon rajojen sisälle
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * Vapari vapari = new Vapari();
     * Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo();
     * vapari.getHenkiloita() === 0;
     * vapari.lisaa(eetu1); vapari.getHenkiloita() === 1;
     * vapari.lisaa(eetu2); vapari.getHenkiloita() === 2;
     * vapari.lisaa(eetu1); vapari.getHenkiloita() === 3;
     * vapari.annaHenkilo(0) === eetu1;
     * vapari.annaHenkilo(1) === eetu2;
     * vapari.annaHenkilo(2) === eetu1;
     * vapari.annaHenkilo(1) == eetu1 === false;
     * vapari.annaHenkilo(1) == eetu2 === true;
     * </pre>
     */
    public Henkilo annaHenkilo(int i) throws IndexOutOfBoundsException {
        return this.henkilot.anna(i);
    }
    
    
    /**
     * Lisätään henkilö kaupungin henkilötaulukkoon
     * @param henkilo lisättävä henkilö
     * @throws YleisException poikkeus jos tapahtuu virhe
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * Vapari vapari = new Vapari();
     * Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo();
     * vapari.getHenkiloita() === 0;
     * vapari.lisaa(eetu1); vapari.getHenkiloita() === 1;
     * vapari.lisaa(eetu2); vapari.getHenkiloita() === 2;
     * vapari.lisaa(eetu1); vapari.getHenkiloita() === 3;
     * </pre>
     */
    public void lisaa(Henkilo henkilo) throws YleisException {
        this.henkilot.lisaa(henkilo);
    }

    
    /**
     * Lisätään toiminto kaupungin toimintolistaan
     * @param toiminto toiminto joka lisätään
     * @throws YleisException jos tulee virhe lisäyksessä
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * Vapari vapari = new Vapari();
     * Toiminto t1 = new Toiminto(), t2 = new Toiminto();
     * vapari.getToimintoja() === 0;
     * vapari.lisaa(t1); vapari.getToimintoja() === 1;
     * vapari.lisaa(t2); vapari.getToimintoja() === 2;
     * vapari.lisaa(t1); vapari.getToimintoja() === 3;
     * </pre>
     */
    public void lisaa(Toiminto toiminto) throws YleisException {
        this.toiminnot.lisaa(toiminto);
    }
    
    
    /**
     * Lisätään lisätieto kaupungin lisätiedot listaan
     * @param lisatieto lisätieto joka lisätään
     * @throws YleisException jos tulee virhe lisäyksessä
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * Vapari vapari = new Vapari();
     * Lisatieto t1 = new Lisatieto(), t2 = new Lisatieto();
     * vapari.getLisatietoja() === 0;
     * vapari.lisaa(t1); vapari.getLisatietoja() === 1;
     * vapari.lisaa(t2); vapari.getLisatietoja() === 2;
     * vapari.lisaa(t1); vapari.getLisatietoja() === 3;
     * </pre>
     */
    public void lisaa(Lisatieto lisatieto) throws YleisException {
        this.lisatiedot.lisaa(lisatieto);
    }
    
    
    /**
     * Lisätään tapahtuma kaupungin tapahtumalistaan
     * @param tapahtuma tapahtuma joka lisätään
     * @throws YleisException jos tulee virhe lisäyksessä
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * Vapari vapari = new Vapari();
     * Tapahtuma t1 = new Tapahtuma(), t2 = new Tapahtuma();
     * vapari.getTapahtumia() === 0;
     * vapari.lisaa(t1); vapari.getTapahtumia() === 1;
     * vapari.lisaa(t2); vapari.getTapahtumia() === 2;
     * vapari.lisaa(t1); vapari.getTapahtumia() === 3;
     * </pre>
     */
    public void lisaa(Tapahtuma tapahtuma) throws YleisException {
        this.tapahtumat.lisaa(tapahtuma);
    }
    
    
    /**
     * Haetaan henkilo tunnistenumeron perusteella
     * @param id henkilön tunnistenumero
     * @return henkilö olio
     */
    public Henkilo haeHenkilo(int id) {
        for (int i = 0; i < this.henkilot.getMaara(); i++) {
            if (this.henkilot.anna(i).getTunnusNro() == id) return this.henkilot.anna(i);
        }
        return null;
    }
    
    
    /**
     * Haetaan henkilön indeksi henkilöt taulukosta henkilön id:n perusteella
     * @param id henkilön id jota haetaan
     * @return henkilön indeksi henkilöt taulukossa
     */
    public int getHenkiloIndex(int id) {
        return this.henkilot.haeIndeksi(id);
    }
    
    /**
     * Poistetaan henkilö
     * @param tunnus Henkilön tunnus
     */
    public void poista(int tunnus) {
        this.henkilot.poista(tunnus);
        this.toiminnot.poistaHenkilo(tunnus);
        this.lisatiedot.poistaHenkilo(tunnus);
        this.tapahtumat.poistaHenkilo(tunnus);
    }
    
    
    /**
     * Haetaan kaikki toimintovaihtoehdot
     * @return kaikki toimintovaihtoehdot listana
     */
    public ArrayList<String> getToimintoVaihtoehdot() {
        return this.toiminnot.vaihtoehdot();
    }
    
    
    /**
     * Haetaan kaikki nykyiset toiminnot listana
     * @return kaikki toiminnot listana
     */
    public ArrayList<Toiminto> getToiminnot() {
        return this.toiminnot.getToiminnot();
    }
    
    
    /**
     * Haetaan toiminto indeksin perusteella
     * @param i indeksi joka haetaan
     * @return Toiminto toimintojen listalta
     */
    public Toiminto annaToiminto(int i) {
        return this.toiminnot.haeToiminto(i);
    }
    
    
    /**
     * Haetaan henkilön toiminnot listana
     * @param id henkilön tunnistenumero
     * @return henkilön toiminnot listana
     */
    public ArrayList<Toiminto> haeToiminnot(int id) {
        return this.toiminnot.haeToiminnot(id);
    }
    
    
    /**
     * Haetaan toiminnon indeksi toimintojen vaihtoehtotaulukosta
     * @param toiminto toiminto, jonka indeksiä haetaan
     * @return toiminnon indeksi toimintojen vaihtoehtotaulukosta
     */
    public int getToimintoIndex(Toiminto toiminto) {
        return this.toiminnot.getToimintoIndex(toiminto);
    }
    
    
    /**
     * Haetaan toiminnon indeksi toimintojen vaihtoehtotaulukosta
     * @param toiminto toiminto stringinä, jonka indeksiä haetaan
     * @return toiminnon indeksi toimintojen vaihtoehtotaulukosta
     */
    public int getToimintoIndex(String toiminto) {
        return this.toiminnot.getToimintoIndex(toiminto);
    }
    
    
    /**
     * Päivitetään kaupungin toiminnot lista henkilön toiminnot listalla
     * @param toimintoja henkilön toiminnot lista
     * @param id henkilön id
     */
    public void paivitaToiminnot(ArrayList<Toiminto> toimintoja, int id) {
        this.toiminnot.paivitaToiminnot(toimintoja, id);;
    }
    
    
    /**
     * Haetaan kaikki kaupungin lisätiedot
     * @return kaikki kaupungin lisätiedot listana
     */
    public ArrayList<Lisatieto> getLisatiedot() {
        return this.lisatiedot.getLisatiedot();
    }
    
    
    /**
     * Haetaan lisätieto listalta indeksin perusteella
     * @param i indeksi joka haetaan
     * @return Lisätieto listalta
     */
    public Lisatieto annaLisatieto(int i) {
        return this.lisatiedot.haeLisatieto(i);
    }
    
    
    /**
     * Haetaan henkilön lisätiedot listana
     * @param id henkilön tunnistenumero
     * @return henkilön lisätiedot listana
     */
    public ArrayList<Lisatieto> haeLisatiedot(int id) {
        return this.lisatiedot.haeLisatiedot(id);
    }
    
    
    /**
     * Päivitetään kaupungin lisätiedot lista henkilön lisätiedot listalla
     * @param lisatietoja henkilön lisätiedot lista
     * @param id henkilön id
     */
    public void paivitaLisatiedot(ArrayList<Lisatieto> lisatietoja, int id) {
        this.lisatiedot.paivitaLisatiedot(lisatietoja, id);
    }
    
    
    /**
     * Haetaan kaikki kaupungin tapahtumat
     * @return kaikki kaupungin tapahtumat listana
     */
    public ArrayList<Tapahtuma> getTapahtumat() {
        return this.tapahtumat.getTapahtumat();
    }
    
    
    /**
     * Muuttaa päivämäärän string muodosta DatePickerin vaatimaan LocalDate muotoon
     * @param pvm päivmäärä string muodossa
     * @return päivämärää localDate muodossa datepicker kenttää varten
     * 
     * @example
     * <pre name="test">
     * pvmToDate("13-11-2019").toString() === "2019-11-13";
     * </pre>
     */
    public static LocalDate pvmToDate(String pvm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(pvm, formatter);                  
        return localDate;                                                       
    }
    
    
    /**
     * Haetaan tapahtuma indeksin perusteella
     * @param i indeksi joka haetaan
     * @return Tapahtuma haetussa indeksissä
     */
    public Tapahtuma annaTapahtuma(int i) {
        return this.tapahtumat.haeTapahtuma(i);
    }
    
    
    /**
     * Haetaan tietyn henkilön tapahtumat id:n perusteella
     * @param id henkilön id
     * @return henkilön tapahtumat listana
     */
    public ArrayList<Tapahtuma> haeTapahtumat(int id) {
        return this.tapahtumat.haeTapahtumat(id);
    }
    
    
    /**
     * Päivitetään kaupungin tapahtumat henkilön tapahtumat listalla
     * @param tapahtumia henkilön tapahtumat lista, jota käsitelty näytössä
     * @param id henkilön id
     */
    public void paivitaTapahtumat(ArrayList<Tapahtuma> tapahtumia, int id) {
        this.tapahtumat.paivitaTapahtumat(tapahtumia, id);
    }
    
    
    /**
     * Tarkistetaan että hetu on täytetty oikein
     * @param teksti hetu tekstinä
     * @return true jos oli oikein, false jos väärin
     */
    public static String hetuTarkistus(String teksti) {
        return kanta.HetuTarkistus.tarkista(teksti);
    }
    
    
    /**
     * Asettaa tiedostojen nimet
     * @param nimi uusi nimi
     */
    public void setTiedosto(String nimi) {
        File dir = new File(nimi);
        dir.mkdirs();
        String hakemistonNimi = "";
        if ( !nimi.isEmpty() ) hakemistonNimi = nimi +"/";
        henkilot.setTiedostonNimi(hakemistonNimi + "henkilot.dat");
        toiminnot.setTiedostonNimi(hakemistonNimi + "toiminnot.dat");
        lisatiedot.setTiedostonNimi(hakemistonNimi + "lisatiedot.dat");
        tapahtumat.setTiedostonNimi(hakemistonNimi + "tapahtumat.dat");
    }
    
    
    /**
     * Lukee kaupungin tiedot tiedostosta
     * @param nimi jota käyteään lukemisessa
     * @throws YleisException jos lukeminen epäonnistuu
     * 
     * @example
     * <pre name="test">
     * #THROWS YleisException
     * #import java.io.File;
     * #import java.util.ArrayList;
     * String hakemisto = "testivapari";
     * File dir = new File(hakemisto);
     * File ftied1 = new File(hakemisto+"/henkilot.dat");
     * File ftied2 = new File(hakemisto+"/toiminnot.dat");
     * File ftied3 = new File(hakemisto+"/lisatiedot.dat");
     * File ftied4 = new File(hakemisto+"/tapahtumat.dat");
     * dir.mkdir();
     * ftied1.delete();
     * ftied2.delete();
     * ftied3.delete();
     * ftied4.delete();
     * Vapari testi = new Vapari();
     * testi.setTiedosto(hakemisto);
     * testi.tallenna();
     * testi.lueTiedostosta(hakemisto);
     * testi = new Vapari();
     * Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(), eetu3 = new Henkilo();
     * eetu1.rekisteroi();
     * eetu1.esimerkkiTiedot();
     * eetu1.setNimi("Esimerkki Eetu 1");
     * eetu2.rekisteroi();
     * eetu2.esimerkkiTiedot();
     * eetu2.setNimi("Esimerkki Eetu 2");
     * eetu3.rekisteroi();
     * eetu3.esimerkkiTiedot();
     * eetu3.setNimi("Esimerkki Eetu 3");
     * Toiminto to1 = new Toiminto(), to2 = new Toiminto(), to3 = new Toiminto();
     * to1.rekisteroi();
     * to1.esimerkkiTiedot();
     * to2.rekisteroi();
     * to2.esimerkkiTiedot();
     * to3.rekisteroi();
     * to3.esimerkkiTiedot();
     * Lisatieto li1 = new Lisatieto(), li2 = new Lisatieto(), li3 = new Lisatieto();
     * li1.rekisteroi();
     * li1.esimerkkiTiedot();
     * li2.rekisteroi();
     * li2.esimerkkiTiedot();
     * li3.rekisteroi();
     * li3.esimerkkiTiedot();
     * Tapahtuma ta1 = new Tapahtuma(), ta2 = new Tapahtuma(), ta3 = new Tapahtuma();
     * ta1.rekisteroi();
     * ta1.esimerkkiTiedot();
     * ta2.rekisteroi();
     * ta2.esimerkkiTiedot();
     * ta3.rekisteroi();
     * ta3.esimerkkiTiedot();
     * testi.lisaa(eetu1);
     * testi.lisaa(eetu2);
     * testi.lisaa(eetu3);
     * testi.lisaa(to1);
     * testi.lisaa(to2);
     * testi.lisaa(to3);
     * testi.lisaa(li1);
     * testi.lisaa(li2);
     * testi.lisaa(li3);
     * testi.lisaa(ta1);
     * testi.lisaa(ta2);
     * testi.lisaa(ta3);
     * testi.setTiedosto(hakemisto);
     * testi.tallenna();
     * testi.lueTiedostosta(hakemisto);
     * testi.annaHenkilo(0).getNimi().equals("Esimerkki Eetu 1") === true;
     * testi.annaHenkilo(1).getNimi().equals("Esimerkki Eetu 2") === true;
     * testi.annaHenkilo(2).getNimi().equals("Esimerkki Eetu 3") === true;
     * ArrayList<Toiminto> toimintoja = testi.haeToiminnot(1);
     * toimintoja.get(0).getToiminto() === "siivous";
     * ArrayList<Lisatieto> lisatietoja = testi.haeLisatiedot(1);
     * lisatietoja.get(0).getLisatieto() === "lisätieto";
     * ArrayList<Tapahtuma> tapahtumia = testi.haeTapahtumat(1);
     * tapahtumia.get(0).getPvm() === "14-05-2011";
     * ftied1.delete() === true;
     * ftied2.delete() === true;
     * ftied3.delete() === true;
     * ftied4.delete() === true;
     * dir.delete() === true;
     * </pre>
     */
    public void lueTiedostosta(String nimi) throws YleisException {
        // alustetaan oletusparametrit uudestaan.
        this.henkilot = new Henkilot();
        this.toiminnot = new Toiminnot();
        this.lisatiedot = new Lisatiedot();
        this.tapahtumat = new Tapahtumat();
        // asetetaan annettu nimi oletushakemistoksi, josta tiedostot luetaan
        this.setTiedosto(nimi); 
        
        // luetaan tiedostoista tiedot
        this.henkilot.lueTiedostosta();
        this.toiminnot.lueTiedostosta();
        this.lisatiedot.lueTiedostosta();
        this.tapahtumat.lueTiedostosta();
    }


    /**
     * Tallenttaa kaupungin tiedot tiedostoihin.
     * @throws YleisException jos tallettamisessa ongelmia
     */
    public void tallenna() throws YleisException {
        String virhe = "";
        try {
            this.henkilot.tallenna();
        } catch ( YleisException  ex ) {
            virhe = ex.getMessage();
        }
        try {
            this.toiminnot.tallenna();
        } catch ( YleisException  ex ) {
            virhe = ex.getMessage();
        }
        try {
            this.lisatiedot.tallenna();
        } catch ( YleisException  ex ) {
            virhe = ex.getMessage();
        }
        try {
            this.tapahtumat.tallenna();
        } catch ( YleisException  ex ) {
            virhe = ex.getMessage();
        }
        
        if ( !"".equals(virhe) ) throw new YleisException(virhe);
    }
    
    
    /**
     * Tarkistetaan sisältyyko hakuehto annettuun tekstiin
     * @param teksti teksti jota tarkistetaan hakuehdolla
     * @param hakuehto tekstin palanen, joka pyritään löytämään tekstistä
     * @return true jos hakuehto löytyi tekstistä, muuten false
     * 
     * @example
     * <pre name="test">
     * hakuString("koirankoppi", "koppi") === true;
     * hakuString("Pekka Esimerkki", "Pekka") === true;
     * hakuString("Pekka Esimerkki", "Pekko") === false;
     * hakuString("Pekka Esimerkki", "Esimer") === true;
     * hakuString("pekka@esimerkki.com", "esim") === true;
     * </pre>
     */
    public static boolean hakuString (String teksti, String hakuehto) {
        String sisalto = teksti.toLowerCase();
        String haku = hakuehto.toLowerCase();
        boolean osuiko = false;
        for (int o = 0; o < sisalto.length(); o++) {
            if (sisalto.contains(haku)) osuiko = true;
        }
        return osuiko;
    }
    
    
    /**
     * Testiohjelma luokalle
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Vapari vapari = new Vapari();
        
        Henkilo eetu1 = new Henkilo(), eetu2 = new Henkilo(), eetu3 = new Henkilo();
        eetu1.rekisteroi();
        eetu1.esimerkkiTiedot();
        eetu1.setNimi("Esimerkki Eetu 140"); // Asetetaan jotta lueTiedostosta testit toimivat oikein
        eetu2.rekisteroi();
        eetu2.esimerkkiTiedot();
        eetu3.rekisteroi();
        eetu3.esimerkkiTiedot();
        
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
        
        // Try-Catch, koska lisaa metodissa on sanottu että voi heittää exceptionin
        // ja jonkun pitää ottaa se kiinni.
        try {
            // Lisätään henkilöt
            vapari.lisaa(eetu1);
            vapari.lisaa(eetu2);
            vapari.lisaa(eetu3);
            // Lisätään toiminnot
            vapari.lisaa(to1);
            vapari.lisaa(to2);
            vapari.lisaa(to3);
            // Lisätään lisätiedot
            vapari.lisaa(li1);
            vapari.lisaa(li2);
            vapari.lisaa(li3);
            // Lisätään tapahtumat
            vapari.lisaa(ta1);
            vapari.lisaa(ta2);
            vapari.lisaa(ta3);
            // Asetetaan hakemiston nimi ja tallennetaan
            vapari.setTiedosto("VapariTesti");
            vapari.tallenna();
            // Luetaan edellä tallennettu tiedosto
            vapari.lueTiedostosta("VapariTesti");
            // Tulostetaan tiedot
            System.out.println("-------------------- JÄSENET TESTI --------------------");
        
            for (int i = 0; i < vapari.getHenkiloita(); i++) {
                Henkilo henkilo = vapari.annaHenkilo(i);
                System.out.println("Henkilo nro: " + i);
                henkilo.tulosta(System.out);
            }
            
            for (int i = 0; i < vapari.getToimintoja(); i++) {
                Toiminto toiminto = vapari.annaToiminto(i);
                System.out.println("Toiminto nro: " + i);
                toiminto.tulosta(System.out);
            }
            
            for (int i = 0; i < vapari.getLisatietoja(); i++) {
                Lisatieto lisatieto = vapari.annaLisatieto(i);
                System.out.println("Lisätieto nro: " + i);
                lisatieto.tulosta(System.out);
            }
            
            for (int i = 0; i < vapari.getTapahtumia(); i++) {
                Tapahtuma tapahtuma = vapari.annaTapahtuma(i);
                System.out.println("Tapahtuma nro: " + i);
                tapahtuma.tulosta(System.out);
            }
        } catch (YleisException ex) {
            System.err.println(ex.getMessage());
        } catch (IndexOutOfBoundsException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    
}
