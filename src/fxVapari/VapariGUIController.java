/**
 * 
 */
package fxVapari;

import fi.jyu.mit.fxgui.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import vapari.Henkilo;
import vapari.Lisatieto;
import vapari.Tapahtuma;
import vapari.Toiminto;
import vapari.Vapari;
import vapari.YleisException;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * Luokka Vaparin käyttöliittymän tapahtumien hoitamiseksi.
 * Kaupunkeja on tällä hetkellä yksi ja uusien lisäämiseksi ei ole ohjelmassa
 * tarvittavia nappeja ja niiden toiminnallisuutta ohjaavaa koodia.
 * Kuitenkin jatkossa jos haluan laajentaa ohjelmaa, niin tällaisen ominaisuuden
 * teko onnistuu helposti lisäämällä tarvittavat koodit tähän ja VapariGUIControlleriin.
 * @author mikar
 * @version 8 Jan 2020
 *
 */
                                         // Toteuttaa alusta metodin, joka löytyy alta
public class VapariGUIController implements Initializable {
    
    @FXML private MenuItem menuVaihdaKaupunkia;
    @FXML private MenuItem menuTulostusValinta;
    @FXML private MenuItem menuSulje;
    @FXML private MenuItem menuLisaaHenkilo;
    @FXML private MenuItem menuMuokkaaHenkilo;
    @FXML private MenuItem menuMuokkaaLisatiedot;
    @FXML private MenuItem menuMuokkaaToiminnot;
    @FXML private MenuItem menuTapahtumat;
    @FXML private MenuItem menuPoistaHenkilo;
    @FXML private MenuItem menuOhjeet;
    @FXML private TextField hakuSanat;
    @FXML private ComboBoxChooser<String> hakuValinta;
    @FXML private ListChooser<Henkilo> chooserHenkilot; // Henkilölista
    @FXML private Button uusiHenkilo;
    @FXML private Button poistaHenkilo;
    @FXML private ListChooser<Toiminto> henkiloToiminnot;
    @FXML private Button toiminnot;
    @FXML private Button lisatiedot;
    @FXML private ListChooser<Lisatieto> henkiloLisatiedot;
    @FXML private TextField tiedotNimi;
    @FXML private TextField tiedotHetu;
    @FXML private TextField tiedotPuh;
    @FXML private TextField tiedotMail;
    @FXML private TextField tiedotOsoite;
    @FXML private TextField tiedotPostinro;
    @FXML private TextField tiedotAlue;
    @FXML private TextField tiedotRooli;
    @FXML private Button muokkaaHenkilo;
    @FXML private Button tapahtumat;
    
    // Tässä se alusta metodi
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
   
    // Lisaa henkilo - VapariHenkilo
    @FXML private void handleUusiHenkilo() {
        
        Henkilo uusi = new Henkilo();
        uusi.rekisteroi();
        uusi = VapariHenkiloController.avaa(uusi);
        if (uusi.getNimi() != "") {
            try {
                vapari.lisaa(uusi);
                vapari.tallenna();
            } catch (YleisException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
            listaaKaikkiHenkilot();
        }
    }

    
    // Poista henkilö 
    @FXML private void handlePoistaHenkilo() {
        // Tarkistetaan eka että joku henkilö on valittuna listalta
        if (chooserHenkilot.getSelectedObject() != null) {
            boolean vastaus = Dialogs.showQuestionDialog("Poisto", "Haluatko varmasti poistaa henkilön järjestelmästä?", "Kylla" , "Ei" );
            if (vastaus) {
                // Dialogs.showMessageDialog("Ei toimi vielä");
                int kuka = chooserHenkilot.getSelectedObject().getTunnusNro(); 
                try {
                    vapari.poista(kuka);
                    vapari.tallenna();
                    tyhjennaHenkilo();
                    listaaKaikkiHenkilot();
                } catch (YleisException e) {
                    Dialogs.showMessageDialog(e.getMessage());
                }
            }
        }  
    }
    
    // Muokkaa - VapariHenkilo
    @FXML private void handleMuokkaaHenkilo() {
        if (chooserHenkilot.getSelectedObject() != null) {
            VapariHenkiloController.avaa(chooserHenkilot.getSelectedObject());
            try {
                vapari.tallenna();
                tyhjennaHenkilo();
                naytaHenkilo();
                listaaKaikkiHenkilot();
            } catch (YleisException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
    }
    
    
    // Henkilön tapahtumat - VapariTapahtumat
    @FXML private void handleTapahtumat() {
        if (chooserHenkilot.getSelectedObject() != null) {
            henkilonTapahtumat = VapariTapahtumatController.avaa(vapari.haeTapahtumat(henkiloKohdalla.getTunnusNro()), chooserHenkilot.getSelectedObject().getTunnusNro(), vapari);
            try {
                vapari.paivitaTapahtumat(henkilonTapahtumat, henkiloKohdalla.getTunnusNro());
                vapari.tallenna();
            } catch (YleisException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
        
    }
    
    // Muokkaa - VapariToiminnot
    @FXML private void handleMuokkaaToiminnot() {
        if (chooserHenkilot.getSelectedObject() != null) {
            henkilonToiminnot = VapariToiminnotController.avaa(vapari.haeToiminnot(henkiloKohdalla.getTunnusNro()), chooserHenkilot.getSelectedObject().getTunnusNro(), vapari);
            try {
                vapari.paivitaToiminnot(henkilonToiminnot, henkiloKohdalla.getTunnusNro());
                vapari.tallenna();
                tyhjennaToiminnot();
                naytaHenkilonToiminnot(henkiloKohdalla.getTunnusNro());
            } catch (YleisException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
    }
    
    
    // Muokkaa henkilön lisätietoja - VapariLisatiedot
    @FXML private void handleMuokkaaLisatiedot() {
        if (chooserHenkilot.getSelectedObject() != null) {
            henkilonLisatiedot = VapariLisatiedotController.avaa(vapari.haeLisatiedot(henkiloKohdalla.getTunnusNro()), chooserHenkilot.getSelectedObject().getTunnusNro());
            try {
                vapari.paivitaLisatiedot(henkilonLisatiedot, henkiloKohdalla.getTunnusNro());
                vapari.tallenna();
                tyhjennaLisatiedot();
                naytaHenkilonLisatiedot(henkiloKohdalla.getTunnusNro());
            } catch (YleisException e) {
                Dialogs.showMessageDialog(e.getMessage());
            }
        }
    }
    
    // Menu - Vaihda kaupunkia
    @FXML private void handleVaihdaKaupunkia() {
        avaaKaupunki();
    }
    
    // Menu - Sulje
    @FXML private void handleSulje() {
        Platform.exit();
    }
    
    // Menu - Ohjeet
    @FXML private void handleOhjeet() {
       ohjeet();
    }
    
    // Tulostus
    @FXML private void handleTulostusValinta() {
        if (chooserHenkilot.getSelectedObject() != null) {
            VapariTulostusValintaController.avaa(henkiloKohdalla.getTunnusNro(), vapari);
        }
    }
    
    // Hakuehtojen välitys
    @FXML private void handleHakuEhto() {
        String hakuvalinta = hakuValinta.getSelectedObject();
        String hakusanat = hakuSanat.getText().trim();
        haku(hakuvalinta, hakusanat);
    }
    

//==================================================================================
// Tästä eteenpäin muuta toiminnallista koodia
    
    private Vapari vapari;
    private ArrayList<Toiminto> henkilonToiminnot;
    private ArrayList<Lisatieto> henkilonLisatiedot;
    private ArrayList<Tapahtuma> henkilonTapahtumat;
    private Henkilo henkiloKohdalla;
    @SuppressWarnings("unused")
    private String kaupunki;
   
    
    /**
     * Alustetaan asiat pääikkunan avautuessa
     */
    private void alusta() {
        chooserHenkilot.clear();
        chooserHenkilot.addSelectionListener(e -> naytaHenkilo());
        hakuValinta.add("kaikki");
        hakuValinta.add("auttajat");
        hakuValinta.add("autettavat");
        hakuValinta.addSelectionListener(e -> handleHakuEhto());
    }
    
    
    /**
     * Täytetään henkilön tiedot, toiminnot ja lisatiedot valitun henkilön perusteella
     */
    private void naytaHenkilo() {
        henkiloKohdalla = chooserHenkilot.getSelectedObject();
        if (henkiloKohdalla == null) return;
        
        tiedotNimi.setText("");
        tiedotNimi.setText(henkiloKohdalla.getNimi());
        tiedotHetu.setText("");
        tiedotHetu.setText(henkiloKohdalla.getHetu());
        tiedotPuh.setText("");
        tiedotPuh.setText(henkiloKohdalla.getPuh());
        tiedotMail.setText("");
        tiedotMail.setText(henkiloKohdalla.getMail());
        tiedotOsoite.setText("");
        tiedotOsoite.setText(henkiloKohdalla.getOsoite());
        tiedotPostinro.setText("");
        tiedotPostinro.setText(henkiloKohdalla.getPostinro());
        tiedotAlue.setText("");
        tiedotAlue.setText(henkiloKohdalla.getAlue());
        tiedotRooli.setText("");
        tiedotRooli.setText(henkiloKohdalla.getRooli());
        
        tyhjennaToiminnot();
        naytaHenkilonToiminnot(henkiloKohdalla.getTunnusNro());
        
        tyhjennaLisatiedot();
        naytaHenkilonLisatiedot(henkiloKohdalla.getTunnusNro());
    }
    
    
    /**
     * Tyhjentää henkilön lisaätiedot listan henkilön lisätiedoista
     */
    private void tyhjennaLisatiedot() {
        henkiloLisatiedot.clear();
    }
    
    
    /**
     * Tyhjennetään henkilön tiedot ikkunan kentät
     */
    private void tyhjennaHenkilo() {
        tiedotNimi.setText("");
        tiedotHetu.setText("");
        tiedotPuh.setText("");
        tiedotMail.setText("");
        tiedotOsoite.setText("");
        tiedotPostinro.setText("");
        tiedotAlue.setText("");
        tiedotRooli.setText("");
        
        tyhjennaToiminnot();
        tyhjennaLisatiedot();
    }
    
    /**
     * Tyhjentää toiminnot listan henkilön tiedoista
     */
    private void tyhjennaToiminnot() {
        henkiloToiminnot.clear();
    }
    
    
    /**
     * Näyttää henkilön toiminnot id:n perusteella
     * @param id henkilön id
     */
    private void naytaHenkilonToiminnot(int id) {
        henkilonToiminnot = vapari.haeToiminnot(id);
        for (int i = 0; i < henkilonToiminnot.size(); i++) {
            henkiloToiminnot.add(henkilonToiminnot.get(i).getToiminto(), henkilonToiminnot.get(i));
        }
    }
    
    
    /**
     * Näyttää henkilön lisätiedot id:n perusteella
     * @param id henkilön id
     */
    private void naytaHenkilonLisatiedot(int id) {
        henkilonLisatiedot = vapari.haeLisatiedot(id);
        for (int i = 0; i < henkilonLisatiedot.size(); i++) {
            henkiloLisatiedot.add(henkilonLisatiedot.get(i).getLisatieto(), henkilonLisatiedot.get(i));
        }
    }
    
    
    /**
     * Lisätään henkilö listaan id:n perusteella
     * @param id henkilön id
     */
    // Poistui käytöstä lajittelun tullessa käyttöön.
    // Oli aiemmin käytössä uuden henkilön lisäämiseksi listaan.
    // Nyt toistaiseksi vain helpompi hakea kaikki henkilöt uudestaan listaan.
    @SuppressWarnings("unused")
    private void hae(int id) {
        chooserHenkilot.clear();
        
        int indeksi = 0;
        for (int i = 0; i < vapari.getHenkiloita(); i++) {
            Henkilo henkilo = vapari.annaHenkilo(i);
            if (henkilo.getTunnusNro() == id) indeksi = i;
            chooserHenkilot.add(henkilo.getNimi(), henkilo); // Samalle riville henkilo objekti, jotta voidaan tulevaisuudessa kysyä sitä tästä
        }
        tyhjennaToiminnot();
        naytaHenkilonToiminnot(id);
        
        tyhjennaLisatiedot();
        naytaHenkilonLisatiedot(id);
        // ModelViewController malli -> anna dataosuus (getSelectionModel) -> ilmoitetaan että valittuna on indeksi:s indeksi
        chooserHenkilot.getSelectionModel().clearAndSelect(indeksi);
    }
    
    
    /**
     * Haetaan kaikki henkilöt listaan
     */
    private void listaaKaikkiHenkilot() {
        chooserHenkilot.clear();
        ArrayList<String> henkilot = new ArrayList<String>();
        for (int i = 0; i < vapari.getHenkiloita(); i++) {
            henkilot.add(vapari.annaHenkilo(i).getNimi());
        }
        Collections.sort(henkilot);
        for (int i = 0; i < henkilot.size(); i++) {
            for (int u = 0; u < vapari.getHenkiloita(); u++) {
                if (vapari.annaHenkilo(u).getNimi().equals(henkilot.get(i))) {
                    chooserHenkilot.add(vapari.annaHenkilo(u).getNimi(), vapari.annaHenkilo(u));
                    break;
                }      
            }
        }
    } 
    
    
    /**
     * Virhetapahtuma jos textfield on tyhjä. Maalataan punaiseksi.
     * @param teksti TextField joka on toiminnan kohteena
     * @return false jos virheitä, true jos kunnossa
     */
    public static boolean virheTapahtuma(TextField teksti) {
        if (teksti.getText() == null || teksti.getText().trim().isEmpty()) {
            teksti.getStyleClass().add("virhe");
            return false;
        }
        teksti.getStyleClass().removeAll("virhe");
        return true;
    }
    
    
    /**
     * Virhetapahtuma henkilötunnukselle, jos siinä virheitä
     * @param teksti TextField jossa hetu sijaitsee
     * @return false jos virheitä, true jos kunnossa
     */
    public static String virheTapahtumaHetu(TextField teksti) {
        String viesti = Vapari.hetuTarkistus(teksti.getText());
        if (teksti.getText() == null || teksti.getText().trim().isEmpty() || viesti != null) {
            teksti.getStyleClass().add("virhe");
            return viesti;
        }
        teksti.getStyleClass().removeAll("virhe");
        return null;
    }
    
    
    /**
     * Virhetapahtuma jos ComboBox on tyhjä
     * @param boksi comboboksi johon lisätään tai poistetaan väriä
     * @param valinta boksin valittu objekti, jota analysoidaan
     * @return false jos valinta oli tyhjä, true jos valinta ei ollut tyhjä
     */
    public static boolean virheTapahtuma(ComboBoxChooser<String> boksi, String valinta) {
        if (valinta == null || valinta.equals("")) {
            boksi.getStyleClass().add("virhe");
            return false;
        }
        boksi.getStyleClass().removeAll("virhe");
        return true;
    }
    
    
    /**
     * Virhetapahtuma jos datepicker on tyhjä. Maalataan punaiseksi.
     * @param paiva Datepicker joka on toiminnan kohteena
     * @param date DatePicker value arvo
     * @return false jos virheitä, true jos kunnossa
     */
    public static boolean virheTapahtuma(javafx.scene.control.DatePicker paiva, java.time.LocalDate date) {
        if (date == null) {
            paiva.getStyleClass().add("virhe");
            return false;
        }
        paiva.getStyleClass().removeAll("virhe");
        return true;
    }

    
    /**
     * Tehdään tarvittaessa tarkistuksia sovellukselle ennen kuin voi sulkea
     * @return true jos saa sulkea sovelluksen, false jos ei
     */
    public boolean voikoSulkea() {
        return true;
    }
    
    
    /**
     * Luetaan annetun kaupungin tiedosto
     * @param nimi tiedosto josta henkilöt luetaan
     */
    protected void lueTiedosto(String nimi) {
        this.kaupunki = nimi; 
        try {
            vapari.lueTiedostosta(nimi);
            listaaKaikkiHenkilot();
        } catch (YleisException e) {
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
        }
    }
    
    
    /**
     * Kysytään kaupungin nimi VapariStart ikkunassa ja palautetaan true jos kaupunki valittiin.
     * Jos palautetaan false ohjelman käynnistyksen yhteydessä, eli mitää kaupunkia ei ole valittu, niin ohjelma sulkeutuu.
     * Kaupunkia vaihtaessa ohjelma ei sulkeudu, koska edellinen valinta pysyy voimassa vaikka uutta valintaa ei tehtäisi.
     * Välitetään myös valitun kaupungin nimi lueTiedosto metodille, jos kaupunki on valittu.
     * @return true jos kaupunki valittiin ja painettiin ok ja false jos ei
     */
    public boolean avaaKaupunki() {
        String uusiKaupunki = VapariStartController.vaihdaKaupunkia(null);
        if (uusiKaupunki == null || uusiKaupunki == "") return false;
        uusiKaupunki = "Vapari" + uusiKaupunki;
        lueTiedosto(uusiKaupunki);
        return true;
    }
    
    
    /**
     * Asetetaan viite käytettävän kaupungin Vapariin
     * @param vapari valittu kaupunki
     */
    public void setVapari(Vapari vapari) {
        this.vapari = vapari;
    }

    
    /**
     * Haetaan annetuilla tiedoilla henkilöitä. Testit vapari luokassa.
     * @param kategoria vetovalikosta valittu kategoria henkilöille
     * @param hakuehto manuaalisesti kirjoitettu merkkijono, jota etsitään henkilön tiedoista
     */
    private void haku (String kategoria, String hakuehto) {
        // Jos pelkkä kategoria valittu, niin haetaan sen mukaan
        if (hakuehto == null || hakuehto.isEmpty()) {
            if (kategoria != null) {
                switch (kategoria) {
                    case "auttajat":
                        chooserHenkilot.clear();
                        for (int i = 0; i < vapari.getHenkiloita(); i++) {
                            if (vapari.annaHenkilo(i).getRooli().equals("auttaja"))
                                chooserHenkilot.add(vapari.annaHenkilo(i).getNimi(), vapari.annaHenkilo(i));
                        }
                        break;
                    case "autettavat":
                         chooserHenkilot.clear();
                         for (int i = 0; i < vapari.getHenkiloita(); i++) {
                             if (vapari.annaHenkilo(i).getRooli().equals("autettava"))
                                 chooserHenkilot.add(vapari.annaHenkilo(i).getNimi(), vapari.annaHenkilo(i));
                         }
                     break;
                    default:
                        this.listaaKaikkiHenkilot();
                        break;
                }
            }
            else
                this.listaaKaikkiHenkilot();
        }
        
        // Jos hakuehto valittu, mutta ei kategoriaa, niin haetaan hakuehdolla
        if (hakuehto != null && !hakuehto.isEmpty() && (kategoria == null || kategoria.isEmpty()) ) {
            chooserHenkilot.clear();
            // Käydään kaikki henkilöt läpi ja katsotaan täyttyykö hakuehto jossain kentässä
            for (int i = 0; i < vapari.getHenkiloita(); i++) {
                int osumat = 0;
                Henkilo kasiteltava = vapari.annaHenkilo(i);
                // Tarkistetaan osuuko henkilön nimi hakuehtoon
                if (Vapari.hakuString(kasiteltava.getNimi(), hakuehto)) osumat++;
                // Tarkistetaan osuuko hetu
                if (Vapari.hakuString(kasiteltava.getHetu(), hakuehto)) osumat++;
                // Tarkistetaan osuuko puh
                if (Vapari.hakuString(kasiteltava.getPuh(), hakuehto)) osumat++;
                // Tarkistetaan osuuko mail
                if (Vapari.hakuString(kasiteltava.getMail(), hakuehto)) osumat++;
                // Tarkistetaan osuuko alue
                if (Vapari.hakuString(kasiteltava.getAlue(), hakuehto)) osumat++;
                // Tarkistetaan osuuko osoite
                if (Vapari.hakuString(kasiteltava.getOsoite(), hakuehto)) osumat++;
                // Tarkistetaan osuuko postinumero
                if (Vapari.hakuString(kasiteltava.getPostinro(), hakuehto)) osumat++;
                // Tarkistetaan osuuko rooli
                if (Vapari.hakuString(kasiteltava.getRooli(), hakuehto)) osumat++;
                // Tarkistetaan osuuko toiminnot
                ArrayList<Toiminto> toimintoja = vapari.haeToiminnot(kasiteltava.getTunnusNro());
                for (int a = 0; a < toimintoja.size(); a++) {
                    if (Vapari.hakuString(toimintoja.get(a).getToiminto(), hakuehto)) {
                        osumat++;
                        break;
                    }
                }
                // Tarkistetaan osuuko lisätiedot
                ArrayList<Lisatieto> lisatietoja = vapari.haeLisatiedot(kasiteltava.getTunnusNro());
                for (int a = 0; a < lisatietoja.size(); a++) {
                    if (Vapari.hakuString(lisatietoja.get(a).getLisatieto(), hakuehto)) {
                        osumat++;
                        break;
                    }
                }
                if (osumat > 0)
                    chooserHenkilot.add(kasiteltava.getNimi(), kasiteltava);
            } // henkilöitä läpi käyvä loop päättyy
        } // if päättyy
        
        // Viimeisenä, jos sekä kategoria että hakuehto on syötetty.
        if (hakuehto != null && !hakuehto.isEmpty() && kategoria != null && !kategoria.isEmpty() ) {
            chooserHenkilot.clear();
            // Käydään kaikki henkilöt läpi ja katsotaan täyttyykö hakuehto jossain kentässä
            for (int i = 0; i < vapari.getHenkiloita(); i++) {
                int osumat = 0;
                Henkilo kasiteltava = vapari.annaHenkilo(i);
                // Tarkistetaan että henkilön rooli osuu valittuun kategoriaan
                if (!kategoria.equals("kaikki")) 
                    if (kasiteltava.getRooli() != (kategoria))
                        continue;
             // Tarkistetaan osuuko henkilön nimi hakuehtoon
                if (Vapari.hakuString(kasiteltava.getNimi(), hakuehto)) osumat++;
                // Tarkistetaan osuuko hetu
                if (Vapari.hakuString(kasiteltava.getHetu(), hakuehto)) osumat++;
                // Tarkistetaan osuuko puh
                if (Vapari.hakuString(kasiteltava.getPuh(), hakuehto)) osumat++;
                // Tarkistetaan osuuko mail
                if (Vapari.hakuString(kasiteltava.getMail(), hakuehto)) osumat++;
                // Tarkistetaan osuuko alue
                if (Vapari.hakuString(kasiteltava.getAlue(), hakuehto)) osumat++;
                // Tarkistetaan osuuko osoite
                if (Vapari.hakuString(kasiteltava.getOsoite(), hakuehto)) osumat++;
                // Tarkistetaan osuuko postinumero
                if (Vapari.hakuString(kasiteltava.getPostinro(), hakuehto)) osumat++;
                // Tarkistetaan osuuko rooli
                if (Vapari.hakuString(kasiteltava.getRooli(), hakuehto)) osumat++;
                // Tarkistetaan osuuko toiminnot
                ArrayList<Toiminto> toimintoja = vapari.haeToiminnot(kasiteltava.getTunnusNro());
                for (int a = 0; a < toimintoja.size(); a++) {
                    if (Vapari.hakuString(toimintoja.get(a).getToiminto(), hakuehto)) {
                        osumat++;
                        break;
                    }
                }
                // Tarkistetaan osuuko lisätiedot
                ArrayList<Lisatieto> lisatietoja = vapari.haeLisatiedot(kasiteltava.getTunnusNro());
                for (int a = 0; a < lisatietoja.size(); a++) {
                    if (Vapari.hakuString(lisatietoja.get(a).getLisatieto(), hakuehto)) {
                        osumat++;
                        break;
                    }
                }
                if (osumat > 0)
                    chooserHenkilot.add(kasiteltava.getNimi(), kasiteltava);
            } // henkilöitä läpi käyvä loop päättyy
        } // if päättyy
        
    }
    
    /**
     * Avaa vaparin ohjeet henkilökohtaisesta suunnitelmastani
     */
    private void ohjeet() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2020k/ht/mimariep");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    
}
    