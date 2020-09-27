/**
 * 
 */
package fxVapari;

import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
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
public class VapariToimintoController implements ModalControllerInterface<Toiminto> {
    
    @FXML private ComboBoxChooser<String> toimintoToiminto;
    @FXML private javafx.scene.control.Button toimintoTallenna;
    @FXML private javafx.scene.control.Button toimintoPeruuta;
    
    private static Toiminto tamaToiminto;
    private static List<String> kaikkiToiminnot = new ArrayList<String>();
    private static int toimintoIndex;
   
    // Tallenna nappi
    @FXML private void handleToimintoTallenna() {
        if (VapariGUIController.virheTapahtuma(toimintoToiminto, toimintoToiminto.getSelectedObject())) {
            tamaToiminto.setToiminto(toimintoToiminto.getSelectedObject());
            ModalController.closeStage(toimintoTallenna);
        }
        else {
            VapariGUIController.virheTapahtuma(toimintoToiminto, toimintoToiminto.getSelectedObject());
            Dialogs.showMessageDialog("Valitse joku toiminto ennen tallennusta.");
        }
    }
    
    // Peruuta nappi
    @FXML private void handleToimintoPeruuta() {
        ModalController.closeStage(toimintoPeruuta);
    }
    
    
    /**
     * Palautetaan VapariToiminnotControllerille käsitelty toiminto
     */
    @Override
    public Toiminto getResult() {
        return tamaToiminto;
    }

    
    @Override
    public void handleShown() {
        toimintoToiminto.requestFocus();
        
    }
    

    @Override
    public void setDefault(Toiminto toiminto) {
        for (int i = 0; i < kaikkiToiminnot.size(); i++) {
            toimintoToiminto.add(kaikkiToiminnot.get(i));
        }
        toimintoToiminto.setSelectedIndex(toimintoIndex);
        
    }

    
    /**
     * Avaa toiminnon lisäämisen tai muokkauksen
     * @param toiminto toiminto jota käsitellään
     * @param vapari Kaupunki jota käsitellään
     * @param toiminnonIndeksi toiminnon indeksi listassa, 0 jos ei ole valittu toiminnolle toimintoa
     * @return käsitelty toiminto
     */
    public static Toiminto avaa(Toiminto toiminto, Vapari vapari, int toiminnonIndeksi) {
        kaikkiToiminnot = vapari.getToimintoVaihtoehdot();
        tamaToiminto = toiminto;
        toimintoIndex = toiminnonIndeksi;
        return ModalController.showModal(VapariToimintoController.class.getResource("VapariToiminto.fxml"),
                "Toiminto", null, toiminto);
    }

    
}
