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
import vapari.Lisatieto;

/**
 * Luokka hoitaa Lisatiedot.fxml ikkunan tapahtumat.
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariLisatiedotController implements ModalControllerInterface<ArrayList<Lisatieto>> {
    
    @FXML private ListChooser<Lisatieto> lisatiedotLista;
    @FXML private javafx.scene.control.Button lisatiedotLisaa;
    @FXML private javafx.scene.control.Button lisatiedotMuokkaa;
    @FXML private javafx.scene.control.Button lisatiedotPoista;
    @FXML private javafx.scene.control.Button lisatiedotSulje;
    
    private static ArrayList<Lisatieto> lisatietoja;
    private static int idNr;
    private Lisatieto lisatietoKohdalla;
   
    // Lisää nappi
    @FXML private void handleLisatiedotLisaa() {
        Lisatieto uusi = new Lisatieto(idNr);
        uusi.rekisteroi();
        uusi = VapariLisatietoController.avaa(uusi);
        if (uusi.getLisatieto() != null) {
            lisatietoja.add(uusi);
            setDefault(lisatietoja);
            lisatietoKohdalla = null;
        }
    }
    
    // Muokkaa nappi
    @FXML private void handleLisatiedotMuokkaa() {
        if (lisatietoKohdalla != null) {
            // Tehdään sijoitus lisatietoKohdalla, vaikka sitä ei tässä tarvita.
            // Return lähinnä vain uusia alkioita varten.
            lisatietoKohdalla = VapariLisatietoController.avaa(lisatietoKohdalla);
            setDefault(lisatietoja);
            lisatietoKohdalla = null;
        }
           
    }
    
    // Poista nappi
    @FXML private void handleLisatiedotPoista() {
        if (lisatietoKohdalla != null) {
            boolean vastaus = Dialogs.showQuestionDialog("Poisto", "Haluatko varmasti poistaa valitun lisätiedon?", "Kylla" , "Ei" );
            if (vastaus) {
                for(int i = 0; i < lisatietoja.size(); i++) {
                    if (lisatietoja.get(i).getLisatieto() == lisatietoKohdalla.getLisatieto()) {
                        lisatietoja.remove(i);
                        break;
                    }
                }
                setDefault(lisatietoja);
                lisatietoKohdalla = null;
            }
        }
    }
    
    // Sulje nappi
    @FXML private void handleLisatiedotSulje() {
        ModalController.closeStage(lisatiedotSulje);
    }
    
    
    /**
     * Palautetaan VapariGUIControllerille muokattu henkilön lisätiedot lista
     */
    @Override
    public ArrayList<Lisatieto> getResult() {
        return lisatietoja;
    }
    

    @Override
    public void handleShown() {
        lisatiedotLisaa.requestFocus();
        
    }

    
    /**
     * Lisätään henkilön lisätiedot alussa listaan
     */
    @Override
    public void setDefault(ArrayList<Lisatieto> lisatiedot) {
        lisatiedotLista.clear();
        for (int i = 0; i < lisatiedot.size(); i++) {
            lisatiedotLista.add(lisatiedot.get(i).getLisatieto(), lisatiedot.get(i));
        }
        lisatiedotLista.addSelectionListener(e -> valitseLisatieto());
    }
    
    
    /**
     * Asetetaan lisatietoKohdalla = valittu lisätieto listalta
     */
    private void valitseLisatieto() {
        lisatietoKohdalla = lisatiedotLista.getSelectedObject();
    }
    

    /**
     * Avaa henkilön lisätiedot ikkunan
     * @param lisatiedot henkilön lisätiedot
     * @param id henkilön id
     * @return lista henkilön lisätiedoista
     */
    public static ArrayList<Lisatieto> avaa(ArrayList<Lisatieto> lisatiedot, int id) {
        idNr = id;
        lisatietoja = lisatiedot;
        return ModalController.showModal(VapariLisatiedotController.class.getResource("VapariLisatiedot.fxml"),
                "Henkilön lisätiedot", null, lisatiedot);
    }

}
