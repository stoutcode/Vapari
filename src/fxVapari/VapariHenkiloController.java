/**
 * 
 */
package fxVapari;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import vapari.Henkilo;

/**
 * Luokka hoitaa VapariHenkilo ikkunan tapahtumat
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariHenkiloController implements ModalControllerInterface<Henkilo>{
 // VapariHenkilo
    @FXML private TextField henkiloNimi;
    @FXML private TextField henkiloHetu;
    @FXML private TextField henkiloPuh;
    @FXML private TextField henkiloMail;
    @FXML private TextField henkiloOsoite;
    @FXML private TextField henkiloPostinro;
    @FXML private TextField henkiloAlue;
    @FXML private ComboBoxChooser<String> henkiloRooli;
    @FXML private Label virheLabel;
    @FXML private javafx.scene.control.Button lisaaHenkilo;
    @FXML private javafx.scene.control.Button henkiloTallenna;
    @FXML private javafx.scene.control.Button henkiloPeruuta;
    private static Henkilo henkilo;
    

    // Tallenna nappi
    @FXML private void handleHenkiloTallenna() {
        String viesti = VapariGUIController.virheTapahtumaHetu(henkiloHetu);
        // Tarkistetaan ettei nimessä ja hetussa ole virheitä ja asetetaan tiedot jos olivat ok
        if (VapariGUIController.virheTapahtuma(henkiloNimi) && viesti == null ) {
            virheLabel.getStyleClass().removeAll("virhe");
            henkilo.setNimi(henkiloNimi.getText());
            henkilo.setHetu(henkiloHetu.getText());
            henkilo.setPuh(henkiloPuh.getText());
            henkilo.setMail(henkiloMail.getText());
            henkilo.setOsoite(henkiloOsoite.getText());
            henkilo.setPostinro(henkiloPostinro.getText());
            henkilo.setAlue(henkiloAlue.getText());
            henkilo.setRooli(henkiloRooli.getSelectedObject());
            // Dialogs.showMessageDialog("Tallennettaisiin, mutta ei osata vielä!");
            ModalController.closeStage(henkiloNimi);
        }
        // Muuten ilmoitetaan virheistä
        else {
            VapariGUIController.virheTapahtuma(henkiloNimi);
            VapariGUIController.virheTapahtumaHetu(henkiloHetu);
            if (viesti != null) {
                virheLabel.setText(viesti);
                virheLabel.setTextAlignment(TextAlignment.CENTER);
                virheLabel.getStyleClass().add("virhe");
            }
            else {
                virheLabel.setText(viesti);
                virheLabel.setTextAlignment(TextAlignment.CENTER);
                virheLabel.getStyleClass().add("virhe");
            }    
        }
            
    }
    
    
    // Peruuta nappi
    @FXML private void handleHenkiloPeruuta() {
        ModalController.closeStage(henkiloPeruuta);
    }

    
    /**
     * Palautetaan käsitelty henkilö VapariGUIControllerille
     */
    @Override
    public Henkilo getResult() {
        return henkilo;
    }

    
        @Override
    public void handleShown() {
        henkiloNimi.requestFocus();
        
    }

    
    /** 
     * Asetetaan oletusjutut kun ikkuna avataan
     */
    @Override
    public void setDefault(Henkilo oletus) {
        henkilo = oletus;
        henkiloRooli.add("autettava");
        henkiloRooli.add("auttaja");
        
        int roleIndex = 0;
        if (henkilo.getRooli() != null) {
            if(henkilo.getRooli().equals("autettava")) roleIndex = 1;
        }
        if (henkilo.getRooli() != null) {
            if(henkilo.getRooli().equals("auttaja")) roleIndex = 2;
        }
        henkiloNimi.setText(henkilo.getNimi());
        henkiloHetu.setText(henkilo.getHetu());
        henkiloPuh.setText(henkilo.getPuh());
        henkiloMail.setText(henkilo.getMail());
        henkiloOsoite.setText(henkilo.getOsoite());
        henkiloPostinro.setText(henkilo.getPostinro());
        henkiloAlue.setText(henkilo.getAlue());
        henkiloRooli.setSelectedIndex(roleIndex);
    }
    
    /**
     * Avaa henkilön tiedot ikkunan
     * @param oletus henkilö jota käsitellään
     * @return henkilo olio
     */
    public static Henkilo avaa(Henkilo oletus) {
        henkilo = oletus;
        return ModalController.showModal(VapariHenkiloController.class.getResource("VapariHenkilo.fxml"),
                "Henkilon tiedot", null, oletus);
    }
    
}
