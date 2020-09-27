/**
 * 
 */
package fxVapari;

import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import vapari.Vapari;

/**
 * Luokka hoitaa VapariTulostusValinta.fxml ikkunan tapahtumat.
 * Käytännössä valitaan mitä halutaan tulostaa ja välitetään valinnat
 * VapariTulostusControllerille.
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariTulostusValintaController implements ModalControllerInterface<Integer> {
    
    @FXML private javafx.scene.control.Button tulostusValintaOk;
    @FXML private javafx.scene.control.Button tulostusValintaPeruuta;
    @FXML private CheckBox tulostusValintaTiedot;
    @FXML private CheckBox tulostusValintaToiminnot;
    @FXML private CheckBox tulostusValintaLisatiedot;
    @FXML private CheckBox tulostusValintaTapahtumat;
    
    private static Vapari vapari;
    private static int id;
    private static int[] valinnat = new int[5];

    // Ok nappi
    @FXML private void handleTulostusValintaOk() {
        valinnat[0] = id;
        if (tulostusValintaTiedot.isSelected())
            valinnat[1]++;
        if (tulostusValintaToiminnot.isSelected())
            valinnat[2]++;
        if (tulostusValintaLisatiedot.isSelected())
            valinnat[3]++;
        if (tulostusValintaTapahtumat.isSelected())
            valinnat[4]++;
        VapariTulostusController.avaa(valinnat, vapari);
        valinnat = new int[5];
    }
    
    // Peruuta nappi
    @FXML private void handleTulostusValintaPeruuta() {
        ModalController.closeStage(tulostusValintaPeruuta);
    }
    
    
    /**
     * Ei tarvitse palautaa mitään
     */
    @Override
    public Integer getResult() {
        return null;
    }
    

    @Override
    public void handleShown() {
        tulostusValintaTiedot.requestFocus();
    }
    

    @Override
    public void setDefault(Integer oletus) {
        tulostusValintaTiedot.isSelected();
    }
    

    /**
     * Avaa tulostuksen valinnat
     * @param tunnus listasta valitun henkilön id
     * @param kaupunki Kaupunki jota käsitellään
     */
    public static void avaa(int tunnus, Vapari kaupunki) {
        id = tunnus;
        vapari = kaupunki;
        ModalController.showModal(VapariHenkiloController.class.getResource("VapariTulostusValinta.fxml"),
                "Tulostuksen valinnat", null, tunnus);
    }

}
