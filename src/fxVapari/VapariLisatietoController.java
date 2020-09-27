/**
 * 
 */
package fxVapari;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import vapari.Lisatieto;

/**
 * Luokka hoitaa VapariLisatieto.fxml ikkunan tapahtumat
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariLisatietoController implements ModalControllerInterface<Lisatieto> {
    
    @FXML private TextField lisatietoText;
    @FXML private javafx.scene.control.Button lisatietoTallenna;
    @FXML private javafx.scene.control.Button lisatietoPeruuta;
    
    private static Lisatieto tamaLisatieto;
    
    // Tallenna nappi
    @FXML private void handleLisatietoTallenna() {
        if (VapariGUIController.virheTapahtuma(lisatietoText)) {
            tamaLisatieto.setLisatieto(lisatietoText.getText());
            ModalController.closeStage(lisatietoTallenna);
        }
        else {
            VapariGUIController.virheTapahtuma(lisatietoText);
            Dialogs.showMessageDialog("Tyhjää ei voida tallentaa. Täytä lisätietokenttään jotain.");
        }
    }
    
    // Peruuta nappi
    @FXML private void handleLisatietoPeruuta() {
        ModalController.closeStage(lisatietoPeruuta);
    }
    
    
    /**
     * Palautetaan käsitelty lisätieto VapariLisatiedotControllerille
     */
    @Override
    public Lisatieto getResult() {
        return tamaLisatieto;
    }

    @Override
    public void handleShown() {
        lisatietoText.requestFocus();
        
    }

    @Override
    public void setDefault(Lisatieto lisatieto) {
        lisatietoText.setText(lisatieto.getLisatieto());
        
    }

    /**
     * Avaa lisätiedon lisäämisen tai muokkaamisen
     * @param lisatieto lisätieto jota käsitellään
     * @return lisätieto jota käsiteltiin
     */
    public static Lisatieto avaa(Lisatieto lisatieto) {
        tamaLisatieto = lisatieto;
        return ModalController.showModal(VapariLisatietoController.class.getResource("VapariLisatieto.fxml"),
                "Lisätieto", null, lisatieto);
    }

}
