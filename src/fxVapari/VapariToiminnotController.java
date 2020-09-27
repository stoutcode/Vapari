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
import vapari.Toiminto;
import vapari.Vapari;

/**
 * Luokka hoitaa VapariToiminnot.fxml ikkunan tapahtumat
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariToiminnotController implements ModalControllerInterface<ArrayList<Toiminto>> {
    
    @FXML private ListChooser<Toiminto> toiminnotLista;
    @FXML private javafx.scene.control.Button toiminnotLisaa;
    @FXML private javafx.scene.control.Button toiminnotMuokkaa;
    @FXML private javafx.scene.control.Button toiminnotPoista;
    @FXML private javafx.scene.control.Button toiminnotSulje;
    
    private static ArrayList<Toiminto> toimintoja;
    private static int idNr;
    private Toiminto toimintoKohdalla;
    private static Vapari kaupunki;
    private static int toimintoIndex = 0;
    
    // Lisää nappi
    @FXML private void handleToiminnotLisaa() {
        Toiminto uusi = new Toiminto(idNr);
        uusi.rekisteroi();
        toimintoIndex = 0;
        uusi = VapariToimintoController.avaa(uusi, kaupunki, toimintoIndex);
        if (uusi.getToiminto() != null) {
            toimintoja.add(uusi);
            setDefault(toimintoja);
            toimintoKohdalla = null;
        }
    }
    
    // Muokkaa nappi
    @FXML private void handleToiminnotMuokkaa() {
        if (toimintoKohdalla != null) {
            // Tehdään sijoitus toimintoKohdalla, vaikkei tässä sitä tarvittaisi.
            // Return ainoastaan uusia alkioita varten, kun lisätään toimintoja.
            toimintoKohdalla = VapariToimintoController.avaa(toimintoKohdalla, kaupunki, toimintoIndex);
            setDefault(toimintoja);
            toimintoKohdalla = null;
        }
    }
    
    // Poista nappi
    @FXML private void handleToiminnotPoista() {
        if (toimintoKohdalla != null) {
            boolean vastaus = Dialogs.showQuestionDialog("Poisto", "Haluatko varmasti poistaa valitun toiminnon?", "Kylla" , "Ei" );
            if (vastaus) {
                for(int i = 0; i < toimintoja.size(); i++) {
                    if (toimintoja.get(i).getToiminto() == toimintoKohdalla.getToiminto()) {
                        toimintoja.remove(i);
                        break;
                    }
                }
                setDefault(toimintoja);
                toimintoKohdalla = null;
                toimintoIndex = 0;
            }
        }
        
    }
    
    // Sulje nappi
    @FXML private void handleToiminnotSulje() {
        ModalController.closeStage(toiminnotSulje);
    }
    

    @Override
    public void handleShown() {
        toiminnotLisaa.requestFocus();
        
    }
    
    /**
     * Palautetaan VapariGUIControllerille käsitelty lista henkilön toiminnoista
     */
    @Override
    public ArrayList<Toiminto> getResult() {
        return toimintoja;
    }
    

    /**
     * @param toiminnot henkilön toiminnot lista
     */
    @Override
    public void setDefault(ArrayList<Toiminto> toiminnot) {
        // Haetaan vaihtoehdot listaan
        toiminnotLista.clear();
        for (int i = 0; i < toiminnot.size(); i++) {
            toiminnotLista.add(toiminnot.get(i).getToiminto(), toiminnot.get(i));
        }
        // Seurataan mikä toiminto on valittuna listalta
        toiminnotLista.addSelectionListener(e -> valitseToiminto());
    } 
    
    
    /**
     * Asetetaan toimintoKohdalla = mikä toiminto on valittu listalta ja asetetaan
     * sekä toimintoIndex = mikä on tuon valitun toiminnon indeksi toimintojen vaihtoehtolistalla
     */
    private void valitseToiminto() {
        toimintoKohdalla = toiminnotLista.getSelectedObject();
        toimintoIndex = kaupunki.getToimintoIndex(toimintoKohdalla) + 1;
    }
    

    /**
     * Avaa henkilön toiminnot ikkunan
     * @param toiminnot henkilön toiminnot
     * @param id henkilön id
     * @param vapari minkä kaupungin tietoja käsitellään
     * @return lista henkilön toiminnoista
     */
    public static ArrayList<Toiminto> avaa(ArrayList<Toiminto> toiminnot, int id, Vapari vapari) {
        kaupunki = vapari;
        idNr = id;
        toimintoja = toiminnot;
        return ModalController.showModal(VapariToiminnotController.class.getResource("VapariToiminnot.fxml"),
                "Henkilön toiminnot", null, toiminnot);
    }


}
