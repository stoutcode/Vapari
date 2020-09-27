/**
 * 
 */
package fxVapari;

import fi.jyu.mit.fxgui.*;
import javafx.stage.Stage;
import javafx.fxml.FXML;

/**
 * Luokka hoitaa VapariStart.fxml ikkunan tapahtumat
 * @author mikar
 * @version 3 Feb 2020
 *
 */
public class VapariStartController implements ModalControllerInterface<String> {
    
    @FXML private ComboBoxChooser<String> kaupunkiValinta;
    @FXML private javafx.scene.control.Button startOk;
    @FXML private javafx.scene.control.Button startSulje;
    private String kaupunki = null;
    
    
    // Ok nappi
    @FXML private void handleStartOk() {
        kaupunki = kaupunkiValinta.getSelectedObject();
        ModalController.closeStage(kaupunkiValinta);
    }
    
    // Sulje nappi
    @FXML private void handleStartSulje() {
        ModalController.closeStage(kaupunkiValinta);
    }

    @Override
    public String getResult() {
        return kaupunki;
    }
    
    /**
     * Default valinta, mutta ei tässä välttämättä tarvita.
     * Menee automaattisen järjestyksen mukaan ComboBoxChooserissa
     */
    @Override
    public void setDefault(String oletus) {
        kaupunkiValinta.add("Jyvaskyla");
        
    }

    
    /**
     * Mitä tehdään kun kysymys on täytetty
     */
    @Override
    public void handleShown() {
        kaupunkiValinta.requestFocus();
    }
    
    
    /**
     * Dialogi kaupungin kysymistä varten
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @return null jos painetaan Cancel, muuten valitun kaupungin nimi 
     */
    public static String vaihdaKaupunkia (Stage modalityStage) {
        return ModalController.showModal(
                VapariStartController.class.getResource("VapariStart.fxml"),
                "Valitse kaupunki",
                modalityStage, null);
    }
}
