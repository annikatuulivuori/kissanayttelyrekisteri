package nayttelyt;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
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
import nayttelyrekisteri.Nayttelyrekisteri;
import nayttelyrekisteri.Tulos;

/**
 * @author atuul
 * @version 21.4.2021
 * ========================
 * TODO: chooseriin näyttelyt järjestykseen toisinpäin
 */
public class UusiTulosController implements ModalControllerInterface<Tulos>, Initializable {

    @FXML private Button buttonTallenna;
    @FXML private Button buttonPeruuta;
    @FXML private ComboBoxChooser<Nayttely> chooserNayttely;
    @FXML private TextField editTulos;
    @FXML private TextField editTuomari;
    @FXML private Label labelVirhe;
    
    @FXML private void handleTallenna() {
        ModalController.closeStage(labelVirhe);
    }
    
    @FXML private void handlePeruuta() {
        tulosKohdalla = null;
        ModalController.closeStage(buttonPeruuta);
    }
    
    @FXML private void handleValitseNayttely() {
        //
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        alusta();
        
    }

    @Override
    public Tulos getResult() {
        return tulosKohdalla;
    }

    @Override
    public void handleShown() {
        //chooserNayttely.requestFocus();
        
    }

    @Override
    public void setDefault(Tulos oletus) {
        tulosKohdalla = oletus;
        //alusta();
    }
   
    
    //===============================================
    
    
    private Nayttelyrekisteri rekisteri;
    private Tulos tulosKohdalla;
    private TextField[] edits;
    private Nayttely nayttelyValittu;
    
    
    /*
     * Alustaa uuden tuloksen
     */
    private void alusta() {
        edits = new TextField[] {editTulos, editTuomari};
        
        int i = 0;
        for (TextField edit : edits) {
            final int k = ++i;
            edit.setOnKeyReleased(e -> kasitteleMuutosTulokseen(k, edit));
        }
    }
    
    
    /*
     * Näyttää rekisterissä olevat näyttelyt chooserissa
     */
    private void naytaTiedot() {
        chooserNayttely.clear();
        for (int i = 0; i < rekisteri.getNayttelyt(); i++) {
            Nayttely apu = rekisteri.annaNayttely(i);
            chooserNayttely.add(apu.getYhdistys() + " " + apu.getPvm(), apu);
        }
        
        chooserNayttely.getSelectionModel().select(0);
    }

    
    /*
     * Käsittelee tulokseen kohdistuvan muutoksen
     * @param k kentän numero
     * @param edit muuttunut kenttä
     */
    private void kasitteleMuutosTulokseen(int k, TextField edit) {
        if (tulosKohdalla == null) return;
        nayttelyValittu = chooserNayttely.getSelectedObject();
        tulosKohdalla.setNayttelyNro(nayttelyValittu.getTunnusNro());
        String s = edit.getText();
        String virhe = null;
        switch (k) {
            case 1 : virhe = tulosKohdalla.setSerti(s); break;
            case 2 : virhe = tulosKohdalla.setTuomari(s); break;
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
     * Asettaa käytettävän rekisterin
     */
    private void setRekisteri(Nayttelyrekisteri rekisteri) {
        this.rekisteri = rekisteri;
        naytaTiedot();
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
     * Luo tuloksen kysymysdialogin ja palauttaa saman tietueen muutettuna tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä kissaa käytetään
     * @param rekisteri rekisteri, josta haetaan tietoja
     * @return null jos painetaan Peruuta, muuten tietue
     */
    public static Tulos kysyTulos(Stage modalityStage, Tulos oletus, Nayttelyrekisteri rekisteri) {
        var resurssi = NayttelytGUIController.class.getResource("UusiTulosGUIView.fxml");
        return ModalController.<Tulos, UusiTulosController>showModal(resurssi, 
                "Uusi tulos", modalityStage, oletus, ctrl -> ctrl.setRekisteri(rekisteri));
        
    }
    
}
