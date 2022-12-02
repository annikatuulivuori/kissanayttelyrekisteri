package nayttelyt;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import nayttelyrekisteri.Titteli;

/**
 * @author atuul
 * @version 21.4.2021
 */
public class UusiTitteliController implements ModalControllerInterface<Titteli>, Initializable {

    @FXML private Button buttonTallenna;
    @FXML private Button buttonPeruuta;
    @FXML private Label labelVirhe;
    @FXML private TextField editLuokka;
    @FXML private TextField editPvm;
    @FXML private TextField editVahvistus;
    
    @FXML private void handleOK() {
        /*if (edits[0] == null) {
            naytaVirhe("Luokka ei saa olla tyhjä");
            return;
        }*/
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handlePeruuta() {
        titteliKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }

    @Override
    public Titteli getResult() {
        return titteliKohdalla;
    }

    @Override
    public void handleShown() {
        editLuokka.requestFocus();
        
    }

    @Override
    public void setDefault(Titteli oletus) {
        titteliKohdalla = oletus;
        alusta();
        naytaTitteli(edits,titteliKohdalla);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
       // alusta();
    }
    
    
    //==========================================
    
    private Titteli titteliKohdalla;
    private TextField[] edits;
    
    
    /*
     * Alustaa uuden tittelin
     */
    private void alusta() {
        
        edits = new TextField[] {editLuokka, editPvm, editVahvistus};
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosTitteliin(k, edit));
        }
    }
    
    
    /*
     * Käsittelee muutoksen tekstikenttään
     * @param k kentän indeksi
     * @param edit kenttä, johon tuli muutos
     */
    private void kasitteleMuutosTitteliin(int k, TextField edit) {
        if (titteliKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = titteliKohdalla.setLuokka(s); break;
            case 2 : virhe = titteliKohdalla.setPvm(s); break;
            case 3 : virhe = titteliKohdalla.setVahvistus(s); break;
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
     * Näyttää virheen jos tulee virhe
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
    
    
    /**
     * Näytetään tittelin tiedot TextField komponentteihin
     * @param edits taulukko jossa tekstikenttiä
     * @param titteli näytettävä titteli
     */
    public static void naytaTitteli(TextField[] edits, Titteli titteli) {
        if (titteli == null) return;
        edits[0].setText(titteli.getLuokka());
        edits[1].setText(titteli.getPvm());
        edits[2].setText(titteli.getVahvistus());
    }
        
    
    /**
     * Luo tittelin kysymysdialogin ja palauttaa saman tietueen muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä kissaa käytetään
     * @return null jos painetaan Peruuta, muuten tietue
     */
    public static Titteli kysyTitteli(Stage modalityStage, Titteli oletus) {
        var resurssi = NayttelytGUIController.class.getResource("UusiTitteliGUIView.fxml");
        return ModalController.showModal(resurssi, "Uusi titteli", modalityStage, oletus);
        
    }

}
