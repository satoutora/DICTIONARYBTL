package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    Dictionary List = new Dictionary();
    DictionaryManagement management = new DictionaryManagement();
    @FXML
    ListView<String> listView = new ListView<>();
    @FXML
    TextField SearchText = new TextField();
    @FXML
    TextField EText = new TextField();
    @FXML
    TextField TText = new TextField();
    @FXML
    TextField VText = new TextField();
    @FXML
    TextField StatusText = new TextField();
    @FXML
    Button buttonSearch;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonFix;
    @FXML
    Button buttonDel;
    @FXML
    Button buttonSave;

    public void initialize(URL location, ResourceBundle resources) {
        try {
            management.insertFromFile(List);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < List.words.size(); i++) {
            listView.getItems().add(List.words.get(i).getWord_target());
            //listView.getItems().add(List.words.get(i).getWord_explain());
        }
    }

    public void autocompelete(KeyEvent keyEvent) {
        listView.getItems().clear();
        String s = SearchText.getText();
        for(int i=0; i<List.words.size(); i++) {
            if(List.words.get(i).getWord_target().startsWith(s)) {
                listView.getItems().add(List.words.get(i).getWord_target());
            }
        }
    }

    public void Show_word(MouseEvent event) {
        String s = listView.getSelectionModel().getSelectedItem();
        Word word_show = management.get_word(List,s);
        EText.setText(word_show.getWord_target());
        TText.setText(word_show.getWord_type());
        VText.setText(word_show.getWord_means());
        StatusText.clear();
    }

    public void Look_up(ActionEvent event) {
        EText.clear();
        TText.clear();
        VText.clear();
        StatusText.clear();
        listView.getItems().clear();
        String s = SearchText.getText();
        for(int i=0; i<List.words.size(); i++) {
            if(List.words.get(i).getWord_target().startsWith(s)) {
                listView.getItems().add(List.words.get(i).getWord_target());
            }
        }
    }
    public void AddWord(ActionEvent event) {
        String word = EText.getText();
        String mean = VText.getText();
        String type = TText.getText();
        int kt=0;
        for(int i=0; i<List.words.size();i++){
            if(word.equalsIgnoreCase(List.words.get(i).getWord_target())
                    && type.equalsIgnoreCase(List.words.get(i).getWord_type())
                    && !type.equalsIgnoreCase(List.words.get(i).getWord_type())){
                kt=1;
                mean = List.words.get(i).getWord_means() + ", " + mean;
                management.dictionaryFix(List.words.get(i), word, type, mean);
            }
        }
        StatusText.setText("Your word has been added!");
        if(kt==0)management.insertFromText(List, word, type, mean);
        EText.clear();
        TText.clear();
        VText.clear();
        StatusText.clear();
        //Show_word();
    }

    public void DelWord() {
        String a = listView.getSelectionModel().getSelectedItem();
        listView.getItems().remove(a);
        EText.clear();
        TText.clear();
        VText.clear();
        StatusText.clear();
        StatusText.setText("Your word has been deleted!");
        management.dictionaryRemove(List,a);
    }

    public void Fix_word(ActionEvent event) {
        String a = listView.getSelectionModel().getSelectedItem();
        Word word = management.get_word(List,a);
        StatusText.setText("Your word has been fixed!");
        management.dictionaryFix(management.get_word(List,a),EText.getText(),TText.getText(),VText.getText());
        EText.clear();
        TText.clear();
        VText.clear();
        //Show_word();
    }

    public void end_game(ActionEvent event) throws FileNotFoundException, UnsupportedEncodingException {
        management.save_file(List);
    }
}