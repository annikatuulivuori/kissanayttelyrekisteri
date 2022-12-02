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
import nayttelyrekisteri.Nayttely;

/**
 * @author atuul
 * @version 20.4.2021
 *
 * Käsitellään "uusi näyttely"-ikkunan tapahtumat
 */
public class UusiNayttelyController implements ModalControllerInterface<Nayttely>, Initializable {

    @FXML private Button buttonTallenna;
    @FXML private Button buttonPeruuta;
    @FXML private TextField editYhdistys;
    @FXML private TextField editPvm;
    @FXML private Label labelVirhe;
    
    @FXML private void handleTallenna() {
        //tallenna();
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handlePeruuta() {
        nayttelyKohdalla = null;
        ModalController.closeStage(labelVirhe);
    }
    
    @Override
    public Nayttely getResult() {
        return nayttelyKohdalla;
    }

    @Override
    public void handleShown() {
        editYhdistys.requestFocus();
    }

    @Override
    public void setDefault(Nayttely oletus) {
        nayttelyKohdalla = oletus;
        alusta();    
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //alusta();
    }

    
    
    //================================================
    
    private Nayttely nayttelyKohdalla;
    private TextField[] edits;
    
    /*
     * Alustaa näyttelyn
     */
    private void alusta() {
        edits = new TextField[] {editYhdistys, editPvm};
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            if (edit != null)
                edit.setOnKeyReleased(e -> kasitteleMuutosNayttelyyn(k, edit));
        }
    }
    
    
    /*
     * Käsittelee näyttelyn muutokset
     */
    private void kasitteleMuutosNayttelyyn(int k, TextField edit) {
        if (nayttelyKohdalla == null) return;
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = nayttelyKohdalla.setYhdistys(s); break;
            case 2 : virhe = nayttelyKohdalla.setPvm(s); break;
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
     * Luo näyttelyn kysymysdialogin ja palauttaa saman tietueen muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä näyttelyä käytetään
     * @return null jos painetaan Peruuta, muuten tietue
     */
    public static Nayttely kysyNayttely(Stage modalityStage, Nayttely oletus) {
        var resurssi = NayttelytGUIController.class.getResource("UusiNayttelyGUIView.fxml");
        return ModalController.showModal(resurssi, "Uusi näyttely", modalityStage, oletus);
    }

}