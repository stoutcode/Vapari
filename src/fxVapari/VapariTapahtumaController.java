/**
 * 
 */
package fxVapari;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import vapari.Henkilo;
import vapari.Tapahtuma;
import vapari.Vapari;

/**
 * Luokka hoitaa VapariTapahtuma.fxml ikkunan tapahtumat
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariTapahtumaController implements ModalControllerInterface<Tapahtuma> {
    
    @FXML private ComboBoxChooser<String> tapahtumaToiminto;
    @FXML private javafx.scene.control.DatePicker tapahtumaPvm;
    @FXML private ComboBoxChooser<String> tapahtumaOsapuoli;
    @FXML private javafx.scene.control.Button tapahtumaTallenna;
    @FXML private javafx.scene.control.Button tapahtumaPeruuta;
    
    private static Tapahtuma tamaTapahtuma;
    private static ArrayList<String> kaikkiToiminnot = new ArrayList<String>();
    private static Henkilo[] kaikkiHenkilot;
    private static Vapari kaupunki; 
    private static int toimintoIndex;
    private static int henkiloIndex;
    
    // Tallenna nappi
    @FXML private void handleTapahtumaTallenna() {
        if (VapariGUIController.virheTapahtuma(tapahtumaPvm, tapahtumaPvm.getValue())
                && VapariGUIController.virheTapahtuma(tapahtumaToiminto, tapahtumaToiminto.getSelectedObject())
                && VapariGUIController.virheTapahtuma(tapahtumaOsapuoli, tapahtumaOsapuoli.getSelectedObject())) {
                    tamaTapahtuma.setToiminto(tapahtumaToiminto.getSelectedObject());
                    tamaTapahtuma.setPvm(tapahtumaPvm.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    tamaTapahtuma.setToinenId(kaupunki.haeHenkiloNimella(tapahtumaOsapuoli.getSelectedObject()).getTunnusNro());
                    ModalController.closeStage(tapahtumaTallenna);
            }
        else {
            VapariGUIController.virheTapahtuma(tapahtumaPvm, tapahtumaPvm.getValue());
            VapariGUIController.virheTapahtuma(tapahtumaToiminto, tapahtumaToiminto.getSelectedObject());
            VapariGUIController.virheTapahtuma(tapahtumaOsapuoli, tapahtumaOsapuoli.getSelectedObject());
            Dialogs.showMessageDialog("Kaikkiin kenttiin tulee täyttää jotain.");
        }
            
    }
    
    // Peruuta nappi
    @FXML private void handleTapahtumaPeruuta() {
        ModalController.closeStage(tapahtumaPeruuta);
    }
    
    
    /**
     * Palautetaan käsitelty tapahtuma VapariTapahtumatControllerille
     */
    @Override
    public Tapahtuma getResult() {
        return tamaTapahtuma;
    }

    
    @Override
    public void handleShown() {
        tapahtumaToiminto.requestFocus();
        
    }

    
    /**
     * Asetetaan oletusjutut alkuun
     */
    @Override
    public void setDefault(Tapahtuma tapahtuma) {
        // Lisätään päivämääräkenttään arvo, jos tapahtumalle oli se asetettu
        if(tapahtuma.getPvm() != null)
            tapahtumaPvm.setValue(Vapari.pvmToDate(tapahtuma.getPvm()));
        
        // Haetaan toiminnot kenttään vaihtoehdot
        for (int i = 0; i < kaikkiToiminnot.size(); i++) {
            tapahtumaToiminto.add(kaikkiToiminnot.get(i));
        }
        // Haetaan henkilöt kenttään vaihtoehdot
        for(int i = 0; i < kaikkiHenkilot.length; i++) {
            if(i == 0) tapahtumaOsapuoli.add("");
            tapahtumaOsapuoli.add(kaikkiHenkilot[i].getNimi());
        }
        
        // Asetetaan valitut indeksit henkilöt ja toiminnot kentistä
        tapahtumaToiminto.setSelectedIndex(toimintoIndex);
        tapahtumaOsapuoli.setSelectedIndex(henkiloIndex);
    }
    

    /**
     * Avaa tapahtuman lisäämisen tai muokkaamisen
     * @param tapahtuma tapahtuma jota käsitellään
     * @param vapari kaupunki, jonka kautta saadaan kaikki henkilöt ja toiminnot
     * @param toiminnonIndeksi tapahtumalle asetetun toiminnon indeksi toimintovaihtoehdot listassa
     * @param henkilonIndeksi tapahtumalle asetetun toisen henkilön indeksi henkilöt taulukossa
     * @return tapahtuma joka käsiteltiin
     */
    public static Tapahtuma avaa(Tapahtuma tapahtuma, Vapari vapari, int toiminnonIndeksi, int henkilonIndeksi) {
        tamaTapahtuma = tapahtuma;
        kaikkiToiminnot = vapari.getToimintoVaihtoehdot();
        kaikkiHenkilot = vapari.getHenkilot();
        kaupunki = vapari;
        toimintoIndex = toiminnonIndeksi;
        henkiloIndex = henkilonIndeksi;
        return ModalController.showModal(VapariTapahtumaController.class.getResource("VapariTapahtuma.fxml"),
                "Tapahtuma", null, tapahtuma);
    }

}
