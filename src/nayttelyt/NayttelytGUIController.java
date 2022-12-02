package nayttelyt;

import java.awt.Desktop;
import java.util.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.StringGrid;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import nayttelyrekisteri.Kissa;
import nayttelyrekisteri.Nayttely;
import nayttelyrekisteri.Nayttelyrekisteri;
import nayttelyrekisteri.SailoException;
import nayttelyrekisteri.Titteli;
import nayttelyrekisteri.Tulos;

/**
 * @author ansakatu
 * @version 21.4.2021
 * 
 * TODO: nayta...-aliohjelmat paremmiksi (nyt karvalakkeja)
 * TODO: tulostaminen?
 * TODO: kenttien sisältöjen haku paremmaksi
 * TODO: kissa, tulos, titteli, nayttely-luokat liian samanlaisia -> fiksummin,
 *       liikaa toistoa
 * TODO: Kontrollerit fiksummin (muokkausdialogit, lisäysdialogit)
 * TODO: Tietoja-ikkuna(!!)
 */
public class NayttelytGUIController implements Initializable {
    //pääikkuna -----------------------------------------------------
    @FXML private TextField textHaku; 
    @FXML private ListChooser<Kissa> chooserKissat; 
    @FXML private ScrollPane panelKissa;
    @FXML TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private TextField editKasvattaja;
    @FXML private TextField editRekNimi;
    @FXML private TextField editKutsumanimi;
    @FXML private TextField editSpv;
    @FXML private TextField editSukupuoli;
    @FXML private TextField editRotu;
    @FXML private TextField editEMS;
    @FXML private TextField editRekNum;
    @FXML private StringGrid<Titteli> tableTittelit;
    @FXML private StringGrid<Tulos> tableTulokset;
    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }

    @FXML private void handleApua() {
        avustus();
    }
    
    @FXML private void handleEditKissa() {
        muokkaaKissaa();
    }
    
    @FXML private void handleHakuehto() {
        hae(0);
    }
    
    @FXML private void handleLisaaKissa() {
        uusiKissa();
    }
    
    @FXML private void handleLisaaNayttely() {
        uusiNayttely();
    }
    
    @FXML private void handleLisaaTitteli() {
        uusiTitteli();
    }
    
    @FXML private void handleEditTitteli() {
        muokkaaTittelia();
    }

    @FXML private void handleLisaaTulos() {
        uusiTulos();
    }
    
    @FXML private void handlePoistaTulos() {
        poistaTulos();
    }
    
    @FXML private void handleLopeta() {
        tallenna();
        Platform.exit();
    }
    
    @FXML private void handlePoistaKissa() {
        poistaKissa();
    }
    
    @FXML private void handleTallenna() {
        tallenna();
    }
    
    @FXML private void handleTietoja() {
        Dialogs.showMessageDialog("Ei toimi vielä!");
        //TODO: tietoikkuna
    }

    
    //=============================================
    
    private Nayttelyrekisteri rekisteri;
    private Kissa kissaKohdalla;
    private String rekisterinNimi = "nayttelyrekisteri";
    private TextField[] edits;
    private static Kissa apukissa = new Kissa();
    
    
    /**
     * Näyttää kissan tiedot käyttöliittymälle
     */
    private void alusta() {
        panelKissa.setFitToHeight(true);
        chooserKissat.clear();
        chooserKissat.addSelectionListener(e -> naytaKissa());
        
        cbKentat.clear();
        for (int k = apukissa.ekaKentta(); k < apukissa.getKenttia(); k++)
            cbKentat.add(apukissa.getKysymys(k), null);
        cbKentat.getSelectionModel().select(0);
        
        edits = new TextField[] {editKasvattaja, editRekNimi, editKutsumanimi, editSpv, editSukupuoli, editRotu, editEMS, editRekNum};
    }
    
    
    /**
     * Kysytään tiedoston nimi ja luetaan se
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        lueTiedosto(rekisterinNimi);
        return true;
    }

    
    
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe
     */
    private String tallenna() {
        try {
            rekisteri.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }

    }
    
    
    /**
     * Näytetään ohjelman suunnitelma selaimessa
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
            try {
                URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/ansakatu");
                    desktop.browse(uri);
                } catch (URISyntaxException e) {
                    return;
                } catch (IOException e) {
                    return;
                }
    }

    
    /**
     * Asetetaan käytettävä rekisteri, jota käytetään
     * @param rekisteri näyttelyrekisteri, jota käytetään
     */
    public void setRekisteri(Nayttelyrekisteri rekisteri) {
        this.rekisteri = rekisteri;
    }
    
    
    /**
     * Hakee kissan tiedot rekisteristä
     * @param nro kissan numero, joka aktivoidaan haun jälkeen,
     *          jos 0 niin aktivoidaan nykyinen kissa
     */
    private void hae(int nro) {
        int kNro = nro;
        if (kNro == 0) {
            Kissa kohdalla = kissaKohdalla;
            if (kohdalla != null) kNro = kohdalla.getTunnusNro();
        }
        int k = cbKentat.getSelectionModel().getSelectedIndex() + apukissa.ekaKentta();
        String ehto = hakuehto.getText();
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*";
        
        chooserKissat.clear();
        
        int index = 0;
        Collection<Kissa> kissat;
        try {
            kissat = rekisteri.etsi(ehto, k);
            int i = 0;
            for (Kissa kissa:kissat) {
                if (kissa.getTunnusNro() == kNro) index = i;
                chooserKissat.add(kissa.getNimi(), kissa);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Jäsenen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserKissat.setSelectedIndex(index);

    }
    
    
    /**
     * Lisätään rekisteriin uusi kissa
     */
    private void uusiKissa() {
        Kissa uusi = new Kissa();
        
        uusi = MuokkaaController.kysyKissa(null, uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaaKissa(uusi);
        hae(uusi.getTunnusNro());
    }
    
    
    /**
     * Tekee uuden tyhjän tittelin editointia varten
     */
    public void uusiTitteli() {
        if (kissaKohdalla == null) return;
        
        Titteli uusi = new Titteli(kissaKohdalla.getTunnusNro()); 
        
        uusi = UusiTitteliController.kysyTitteli(null, uusi);        
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaaTitteli(uusi);
        naytaTittelit(kissaKohdalla);
        tableTittelit.selectRow(1000);
    }
    
    
    /**
     * Tekee uuden tyhjän näyttelyn
     */
    public void uusiNayttely() {
        Nayttely uusi = new Nayttely();
        uusi = UusiNayttelyController.kysyNayttely(null,uusi);
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaaNayttely(uusi);
    }
    
    
    /**
     * Tekee uuden tyhjän tuloksen
     */
    public void uusiTulos() {
        if (kissaKohdalla == null) return;
        Tulos uusi = new Tulos(kissaKohdalla.getTunnusNro());
        uusi = UusiTulosController.kysyTulos(null, uusi, rekisteri);
        if (uusi == null) return;
        uusi.rekisteroi();
        rekisteri.lisaaTulos(uusi);
        naytaTulokset(kissaKohdalla);
    }
    
    
    /**
     * Näyttää kissan tiedot käyttöliittymälle
     */
    private void naytaKissa() {
        kissaKohdalla = chooserKissat.getSelectedObject();
        if (kissaKohdalla == null) return;
        MuokkaaController.naytaKissa(edits, kissaKohdalla);
        naytaTittelit(kissaKohdalla);
        naytaTulokset(kissaKohdalla);

    }
    
    
    /*
     * Nayttaa kissan tittelit käyttöliittymälle
     * @param kissa kissa, jonka tittelit halutaan näyttää
     */
    private void naytaTittelit(Kissa kissa) {
        tableTittelit.clear();
        if (kissa == null) return;
        List<Titteli> tittelit = rekisteri.annaTittelit(kissa);
        if (tittelit.size() == 0) return;
        for (Titteli title : tittelit) {
            naytaTitteli(title);
        }
    }
    
    
    /*
     * Näyttää tittelien tiedot
     * @param titteli viite titteliin, joka halutaan näyttää
     */
    private void naytaTitteli(Titteli titteli) {
        //if (kissaKohdalla == null) return;
        //String[] rivi = titteli.toString().split("\\|"); //TODO: korjaa paremmaksi
        //tableTittelit.add(titteli, rivi[2], rivi[3], rivi[4]);
        int kenttia = 5;
        String[] rivi = new String[kenttia];
        for (int i = 0, k=2; k <= 5; i++, k++) {
            rivi[i] = titteli.anna(k);
            
        }
        tableTittelit.add(titteli, rivi);
    }
    
    
    /*
     * Näyttää kissan tuloksien tiedot käyttöliittymälle
     * @param kissa kissa, jonka tulokset näytetään
     */
    private void naytaTulokset(Kissa kissa) {
        tableTulokset.clear();
        if (kissa == null) return;
        List<Tulos> tulokset = rekisteri.annaTulokset(kissa);
        if (tulokset.size() == 0) return;
        for (Tulos tulos : tulokset) {
            naytaTulos(tulos);
        }
        
    }
    
    
    /*
     * Hakee ja näyttää yhden tulosrivin tiedot
     * @param tulos tulos, joka halutaan näkyviin
     */
    private void naytaTulos(Tulos tulos) {
        /*String[] rivi = tulos.toString().split("\\|"); //TODO: korjaa paremmaksi
        String yhdistys = rekisteri.annaNayttely(Integer.parseInt(rivi[2])).getYhdistys();
        String pvm = rekisteri.annaNayttely(Integer.parseInt(rivi[2])).getPvm();
        tableTulokset.add(tulos, yhdistys, pvm, rivi[3], rivi[4]);*/
        
        
        int kenttia = 5;
        String[] rivi = new String[kenttia];
        int n = tulos.getNayttelyNro();
        rivi[0] = rekisteri.getNayttelyNro(n).getYhdistys();
        rivi[1] = rekisteri.getNayttelyNro(n).getPvm();
        for (int i = 2, k=3; k <= 5; i++, k++) {
            rivi[i] = tulos.anna(k);
            
        }
        tableTulokset.add(tulos, rivi);
    }
    
    
    /*
     * Asettaa otsikon
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }
    
    
    /*
     * Kissan muokkaaminen; avaa uuden dialogi-ikkunan valitun kissan tiedoilla
     * muokkaamista varten
     */
    private void muokkaaKissaa() {
        if (kissaKohdalla == null) return;
        
        Kissa kissa;
        try {
            kissa = kissaKohdalla.clone();
            kissa = MuokkaaController.kysyKissa(null, kissa);
            if (kissa == null) return;
            rekisteri.korvaaTaiLisaa(kissa);
            hae(kissa.getTunnusNro());
        } catch (CloneNotSupportedException e) {
            System.out.println("Virhe: " + e.getMessage());
        } catch (SailoException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
       
    }
    
    
    /*
     * Tittelin muokkaaminen; avaa dialogi-ikkunan valitulle 
     * tittelille muokkamista varten
     */
    private void muokkaaTittelia() {
        if (kissaKohdalla == null) return;
        int r = tableTittelit.getRowNr();
        if (r < 0) return;
        Titteli titteli;
        try {
            titteli = tableTittelit.getObject();
            if (titteli == null) return;
            titteli = UusiTitteliController.kysyTitteli(null, titteli.clone());
            if (titteli == null) return;
            rekisteri.korvaaTaiLisaa(titteli);
            naytaTittelit(kissaKohdalla);
            tableTittelit.selectRow(r);
        } catch (SailoException e) {
            System.out.println("Virhe: " + e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("Virhe: " + e.getMessage());
        }
        
    }
    
    
    /*
     * Poistaa valitun kissan tiedot rekisteristä
     */
    private void poistaKissa() {
        Kissa kissa = kissaKohdalla;
        if (kissa == null) return;
        if (!Dialogs.showQuestionDialog("Poisto", "Poistetaanko kissa: " + kissa.getNimi(), "Kyllä", "Ei")) return;
        rekisteri.poista(kissa);
        int index = chooserKissat.getSelectedIndex();
        hae(0);
        chooserKissat.setSelectedIndex(index);
    }
    
    
    /*
     * Poistaa valitun tuloksen tiedot rekisteristä
     */
    private void poistaTulos() {
        int rivi = tableTulokset.getRowNr();
        if (rivi < 0) return;
        Tulos tulos = tableTulokset.getObject();
        if (tulos == null) return;
        if ( !Dialogs.showQuestionDialog("Poisto", "Poistetaanko tulos?", "Kyllä", "Ei") ) return;

        rekisteri.poistaTulos(tulos);
        naytaTulokset(kissaKohdalla);
        int tuloksia = tableTulokset.getItems().size();
        if (rivi >= tuloksia) rivi = tuloksia -1;
        tableTulokset.getFocusModel().focus(rivi);
        tableTulokset.getSelectionModel().select(rivi);
    }
    
    
    /*private void tulosta(PrintStream os, final Kissa kissa) {
        os.println("----------------------------------------------");
        kissa.tulosta(os);
        os.println("----------------------------------------------");
        List<Titteli> titteleita = rekisteri.annaTittelit(kissa);
        for (Titteli titteli:titteleita) 
            titteli.tulosta(os);
        os.println("----------------------------------------------");
        List<Tulos> tuloksia = rekisteri.annaTulokset(kissa);
        for (Tulos tulos:tuloksia)
            tulos.tulosta(os);
    }*/
    
    
    /**
     * Alustaa rekisterin lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    protected String lueTiedosto(String nimi) {
        rekisterinNimi = nimi;
        setTitle("Rekisteri - " + rekisterinNimi);
        try {
            rekisteri.lueTiedostosta(nimi);
            hae(0);
            return null;
        } catch (SailoException e) {
            hae(0);
            String virhe = e.getMessage(); 
            if ( virhe != null ) Dialogs.showMessageDialog(virhe);
            return virhe;
        }
     }

}
