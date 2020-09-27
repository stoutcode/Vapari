/**
 * 
 */
package fxVapari;

import java.util.ArrayList;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import vapari.Tapahtuma;
import vapari.Vapari;

/**
 * Luokka hoitaa VapariTapahtumat.fxml ikkunan toiminnot
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariTapahtumatController implements ModalControllerInterface<ArrayList<Tapahtuma>> {
    
    @FXML private ListChooser<Tapahtuma> tapahtumatLista;
    @FXML private javafx.scene.control.Button tapahtumatLisaa;
    @FXML private javafx.scene.control.Button tapahtumatMuokkaa;
    @FXML private javafx.scene.control.Button tapahtumatPoista;
    @FXML private javafx.scene.control.Button tapahtumatSulje;
    
    private static ArrayList<Tapahtuma> tapahtumia;
    private static int idNr;
    private static Vapari kaupunki;
    private Tapahtuma tapahtumaKohdalla;
    private static int toimintoIndex;
    private static int henkiloIndex;
    
    // Lisää nappi
    @FXML private void handleTapahtumatLisaa() {
        Tapahtuma uusi = new Tapahtuma(idNr);
        uusi.rekisteroi();
        toimintoIndex = 0;
        henkiloIndex = 0;
        uusi = VapariTapahtumaController.avaa(uusi, kaupunki, toimintoIndex, henkiloIndex);
        if (uusi.getPvm() != null) {
            tapahtumia.add(uusi);
            setDefault(tapahtumia);
            tapahtumaKohdalla = null;
        }
    }
    
    // Muokkaa nappi
    @FXML private void handleTapahtumatMuokkaa() {
        if (tapahtumaKohdalla != null) {
            // Tehdään sijoitus tapahtumaKohdalla, vaikkei sille tässä ole nykyisellään käyttöä
            // Return hyödyllinen nykyisellään vain uusille alkioille
            tapahtumaKohdalla = VapariTapahtumaController.avaa(tapahtumaKohdalla, kaupunki, toimintoIndex, henkiloIndex);
            setDefault(tapahtumia);
            tapahtumaKohdalla = null;
        }   
    }
    
    // Poista nappi
    @FXML private void handleTapahtumatPoista() {
        if (tapahtumaKohdalla != null) {
            boolean vastaus = Dialogs.showQuestionDialog("Poisto", "Haluatko varmasti poistaa valitun tapahtuman?", "Kylla" , "Ei" );
            if (vastaus) {
                for(int i = 0; i < tapahtumia.size(); i++) {
                    if (tapahtumia.get(i).getRekisterointiNro() == tapahtumaKohdalla.getRekisterointiNro()) {
                        tapahtumia.remove(i);
                        break;
                    }
                }
            setDefault(tapahtumia);
            tapahtumaKohdalla = null;
            toimintoIndex = 0;
            henkiloIndex = 0;
            }
        }
    }
    
    // Sulje nappi
    @FXML private void handleTapahtumatSulje() {
        ModalController.closeStage(tapahtumatSulje);
    }
    
    
    @Override
    public void handleShown() {
        tapahtumatLisaa.requestFocus();
        
    }
    
    
    /**
     * Palautetaan VapariGUIControllerille käsitelty lista henkilön tapahtumista
     */
    @Override
    public ArrayList<Tapahtuma> getResult() {
        return tapahtumia;
    }
    

    @Override
    public void setDefault(ArrayList<Tapahtuma> tapahtumat) {
        tapahtumatLista.clear();
        for (int i = 0; i < tapahtumat.size(); i++) {
            tapahtumatLista.add(tapahtumat.get(i).getPvm() + " " + tapahtumat.get(i).getToiminto() + " " + kaupunki.haeHenkilo(tapahtumat.get(i).getToinenId()).getNimi(),
                                tapahtumat.get(i));
        }
        tapahtumatLista.addSelectionListener(e -> valitseTapahtuma());
        
    } 
    
    
    /**
     * Asetetaan tapahtumaKohdalla = valittu tapahtuma tapahtumien listalta
     * asetetaan myös henkiloIndex = valitun tapahtuman henkilön indeksi henkilötaulukossa
     * sekä toimintoIndex = valitun tapahtuman toiminnon indeksi toimintovaihtoehtojen listalta 
     */
    private void valitseTapahtuma() {
        tapahtumaKohdalla = tapahtumatLista.getSelectedObject();
        toimintoIndex = kaupunki.getToimintoIndex(tapahtumaKohdalla.getToiminto()) + 1;
        henkiloIndex = kaupunki.getHenkiloIndex(tapahtumaKohdalla.getToinenId()) + 1;
    }
       

    /**
     * Avaa henkilön tapahtumat
     * @param tapahtumat lista henkilön tapahtumista
     * @param id henkilön tunnistenumero
     * @param vapari kaupunki jonka kautta saadaan kaikki toiminnot ja henkilöt
     * @return käsitelty lista henkilön tapahtumista
     */
    public static ArrayList<Tapahtuma> avaa(ArrayList<Tapahtuma> tapahtumat, int id, Vapari vapari) {
        idNr = id;
        tapahtumia = tapahtumat;
        kaupunki = vapari;
        return ModalController.showModal(VapariTapahtumatController.class.getResource("VapariTapahtumat.fxml"),
                "Henkilon tapahtumat", null, tapahtumat);
    }

}
