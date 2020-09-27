/**
 * 
 */
package fxVapari;

import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import vapari.Henkilo;
import vapari.Lisatieto;
import vapari.Tapahtuma;
import vapari.Toiminto;
import vapari.Vapari;

import java.io.PrintStream;
import java.util.ArrayList;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;

/**
 * Luokka hoitaa VapariTulostus.fxml ikkunan toiminnot.
 * Eli käytännössä ollaan saatu tulostettavat tiedot ja näytetään ne ikkunassa,
 * sekä tarjotaan vaihtoehtoa tulostaa ne ulos.
 * @author mikar
 * @version 4 Feb 2020
 *
 */
public class VapariTulostusController implements ModalControllerInterface<int[]> {
    
    @FXML private TextArea tulostusText;
    @FXML private javafx.scene.control.Button tulostusTulosta;
    @FXML private javafx.scene.control.Button tulostusPeruuta;
    
    private static Vapari vapari;
    private static Henkilo henkilo;
    private static int[] valinnat;
    
    // Tulosta nappi
    @FXML private void handleTulostusTulosta() {
        PrinterJob job = PrinterJob.createPrinterJob();
        if ( job != null && job.showPrintDialog(null) ) {
            WebEngine webEngine = new WebEngine();
            webEngine.loadContent("<pre>" + tulostusText.getText() + "</pre>");
            webEngine.print(job);
            job.endJob();
        }
    }
    
    // Peruuta nappi
    @FXML private void handleTulostusPeruuta() {
        ModalController.closeStage(tulostusPeruuta);
    }
    
    
    /**
     * Ei tarvitse palauttaa tässä versiossa vielä mitään.
     */
    @Override
    public int[] getResult() {
        return null;
    }
    

    @Override
    public void handleShown() {
        tulostusTulosta.requestFocus();
        
    }
    
    
    /**
     * Haetaan ja näytetään ne tiedot, jotka on valittu näytettäviksi
     * VapariTulostusValintaController luokassa.
     */
    @Override
    public void setDefault(int[] oletus) {
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(tulostusText)) {
            os.println("Henkilö: " + henkilo.getNimi());
            os.println();
            if (valinnat[1] > 0)
                henkilo.tulosta(os);
            if (valinnat[2] > 0) {
                os.println();
                os.println("Toiminnot:");
                ArrayList<Toiminto> toiminnot = vapari.haeToiminnot(valinnat[0]);
                for (int i = 0; i < toiminnot.size(); i++) {
                    toiminnot.get(i).tulosta(os);
                }
            }
            if (valinnat[3] > 0) {
                os.println();
                os.println("Lisätiedot:");
                ArrayList<Lisatieto> lisatiedot = vapari.haeLisatiedot(valinnat[0]);
                for (int i = 0; i < lisatiedot.size(); i++) {
                    lisatiedot.get(i).tulosta(os);
                }
            }
            if (valinnat[4] > 0) {
                os.println();
                os.println("Tapahtumat:");
                ArrayList<Tapahtuma> tapahtumat = vapari.haeTapahtumat(valinnat[0]);
                for (int i = 0; i < tapahtumat.size(); i++) {
                    tapahtumat.get(i).tulosta(os);
                    os.println();
                }
            }     
        }        
    }
    

    /**
     * Avaa tulostuksen esikatselun
     * @param valintaTaulukko Sisältää tulostuksen valinnat: 0 = id, 1 = tiedot, 2 = toiminnot, 3 = lisätiedot, 4 = tapahtumat. Jos valittu joku, niin arvo 1, muuten 0. 
     * @param kaupunki Kaupunki jonka henkilöitä käsitellään
     */
    public static void avaa(int[] valintaTaulukko, Vapari kaupunki) {
        vapari = kaupunki;
        valinnat = valintaTaulukko;
        henkilo = vapari.haeHenkilo(valinnat[0]);
        ModalController.showModal(VapariTulostusController.class.getResource("VapariTulostus.fxml"),
                "Tulostus", null, valintaTaulukko);
    }

}
