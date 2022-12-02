package nayttelyt;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import nayttelyrekisteri.Kissa;

/**
 * @author atuul
 * @version 21.4.2021
 *
 * Käsitellään muokkaus-ikkunan tapahtumat
 */
public class MuokkaaController implements ModalControllerInterface<Kissa>, Initializable {
    
    @FXML private Button buttonTallenna;
    @FXML private Button buttonPeruuta;
    @FXML private Label labelVirhe;
    @FXML private TextField editKasvattaja;
    @FXML private TextField editRekNimi;
    @FXML private TextField editKutsumanimi;
    @FXML private TextField editSpv;
    @FXML private TextField editSukupuoli;
    @FXML private TextField editRotu;
    @FXML private TextField editEMS;
    @FXML private TextField editRekNum;
    
    @FXML private void handleTallenna() {
        if (kissaKohdalla != null && kissaKohdalla.getNimi().trim().equals("")) {
            naytaVirhe("Nimi ei saa olla tyhjä");
            return;
        }
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handlePeruuta() {
        kissaKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    @Override
    public Kissa getResult() {
        return kissaKohdalla;
    }

    @Override 
    public void handleShown() {
        editKasvattaja.requestFocus();
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public void setDefault(Kissa oletus) {
        kissaKohdalla = oletus;
        naytaKissa(kissaKohdalla);
        
    }

    
    //============================================================================
    
    private Kissa kissaKohdalla;
    private TextField[] edits;
    
    
    /*
     * Alustaa muokkausdialogin
     */
    private void alusta() {
        edits = new TextField[] {editKasvattaja, editRekNimi, editKutsumanimi, editSpv, editSukupuoli, editRotu, editEMS, editRekNum};
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosKissaan(k, edit));
        }
    }
    
    /**
     * Käsitellään kissaan tullut muutos
     * @param k kentän numero
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosKissaan(int k, TextField edit) {
        if (kissaKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = kissaKohdalla.setKasvattaja(s); break;
            case 2 : virhe = kissaKohdalla.setRekNimi(s); break;
            case 3 : virhe = kissaKohdalla.setKutsumanimi(s); break;
            case 4 : virhe = kissaKohdalla.setSpv(s); break;
            case 5 : virhe = kissaKohdalla.setSukupuoli(s); break;
            case 6 : virhe = kissaKohdalla.setRotu(s); break;
            case 7 : virhe = kissaKohdalla.setEMS(s); break;
            case 8 : virhe = kissaKohdalla.setRekNum(s); break;
            default:
        }
        if (virhe == null) {
            Dialogs.setToolTipText(edit,  "");
            edit.getStyleClass().removeAll("virhe");
            naytaVirhe(virhe);
        } else {
            Dialogs.setToolTipText(edit, virhe);
            edit.getStyleClass().add("virhe");
            naytaVirhe(virhe);
        }
    }
    
    
    /*
     * Nayttaa virheen, jos tulee virhe
     * @param virhe virhe joka näytetään
     */
    private void naytaVirhe(String virhe) {
        if (virhe == null || virhe.isEmpty()) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /*
     * Näyttää kissan tiedot
     * @param kissa kissa, jonka tiedot näytetään
     */
    private void naytaKissa(Kissa kissa) {
        naytaKissa(edits, kissa);
    }
    
    
    /**
     * Näytetään kissan tiedot TextField komponentteihin
     * @param edits taulukko jossa tekstikenttiä
     * @param kissa näytettävä kissa
     */
    public static void naytaKissa(TextField[] edits, Kissa kissa) {
        if (kissa == null) return;
        edits[0].setText(kissa.getKasvattaja());
        edits[1].setText(kissa.getNimi());
        edits[2].setText(kissa.getKutsumanimi());
        edits[3].setText(kissa.getSpv());
        edits[4].setText(kissa.getSukupuoli());
        edits[5].setText(kissa.getRotu());
        edits[6].setText(kissa.getEMS());
        edits[7].setText(kissa.getRekNum());
    }
    
    
    /**
     * Luo kissan kysymysdialogin ja palauttaa saman tietueen muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä kissaa käytetään
     * @return null jos painetaan Peruuta, muuten tietue
     */
    public static Kissa kysyKissa(Stage modalityStage, Kissa oletus) {
        var resurssi = NayttelytGUIController.class.getResource("MuokkaaGUIView.fxml");
        return ModalController.showModal(resurssi, "Muokkaa kissaa", modalityStage, oletus);
        
    }
    
    
    
}
