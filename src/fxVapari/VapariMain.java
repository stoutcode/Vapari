package fxVapari;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import vapari.Vapari;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 * Pääohjelma joka käynnistää Vaparin
 * @author mikar
 * @version 8 Jan 2020
 *
 */
public class VapariMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		    // Määrätään lataamaan .fxml tiedosto, tässä tapauksessa VapariGUIView.fxml
		    final FXMLLoader ldr = new FXMLLoader(getClass().getResource("VapariGUIView.fxml"));
	        // Määrätään lukemaan ladattu fxml tiedosto. Käytännössä jäljentää fxml kompotentit
	        // vastaavassa rakenteessa java objekteiksi.
	        final Pane root = (Pane)ldr.load();
	        // Otetaan talteen fxml tiedoston controller luokka
	        final VapariGUIController vapariCtrl = (VapariGUIController)ldr.getController(); 
	        // Luodaan scene edellä luoduista java objekteista
	        final Scene scene = new Scene(root);
	        // Laitetaan vapari.css luodun scenen tyylitiedostoksi
	        scene.getStylesheets().add(getClass().getResource("vapari.css").toExternalForm());
	        // Määrätään pääikkuna (primaryStage) näyttämään edellä luotu scene
	        primaryStage.setScene(scene);
	        // Asetetaan pääikkunan otsikko
	        primaryStage.setTitle("Vapari");
	        
	        Vapari vapari = new Vapari();
	        vapariCtrl.setVapari(vapari);
	        
	        
	        // Platform.setImplicitExit(false); // jos tämän laittaa, pitää itse sulkea
	        
	        // Asetetaan tapahtuma sille, kun ohjelmaa yritetään sulkea
	        primaryStage.setOnCloseRequest((event) -> { 
	            // Jos voikoSulkea metodi palauttaa true, niin sulkeminen onnistuu, mutta jos false, niin tämä tapahtuma ohitetaan.
	            if ( !vapariCtrl.voikoSulkea() ) event.consume();
	        });
	        
	        // Laitetaan pääikkuna näkyviin
	        primaryStage.show();
	        // Jos avaaKaupunki() funktio palauttaa false, niin ohjelma sulkeutuu.
	        // Jos palauttaa true, eli kaupunki on valittu, niin ohjelma ei sulkeudu.
	        if (!vapariCtrl.avaaKaupunki() )  Platform.exit();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
		
	}
	
	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
