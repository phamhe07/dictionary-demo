package dictionary;

import com.sun.corba.se.impl.encoding.BufferQueue;
import com.sun.org.apache.xml.internal.dtm.ref.DTMManagerDefault;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();
    @FXML
    public Button search;
    @FXML
    public TextArea meaning;

    @FXML
    public TextField searchBar;
    @FXML
    public ListView listView;
    @FXML
    public Button add;
    @FXML
    public Button delete;
    @FXML
    public Button update;

    List<Word> currentDictionary = new ArrayList<>();

    @FXML
    public void searchWord() {
        if (searchBar.getText().equals("")) {
            listView.getItems().clear();
            meaning.setText("");
        }
        listView.getItems().clear();
        currentDictionary = dictionaryManagement.currentDictionary(searchBar.getText(), dictionaryManagement.Dictionary);
        for (int i = 0; i < currentDictionary.size(); i++) {
            listView.getItems().add(currentDictionary.get(i).getWord_target());
        }
        if (currentDictionary.size() > 0) {
            meaning.setText(currentDictionary.get(0).getWord_explain());
        }
    }

    @FXML
    public void click() {
        int index = listView.getSelectionModel().getSelectedIndex();
        searchBar.setText(currentDictionary.get(index).getWord_target());
        meaning.setText(currentDictionary.get(index).getWord_explain());

    }

    @FXML
    public void add() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addWord.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    TextField target1;
    @FXML
    TextField explain1;
    @FXML
    Button saveadd;
    @FXML
    AnchorPane scence;

    @FXML
    public void savingadd() {
        String target = target1.getText();
        String explain = explain1.getText();
        Word word = new Word(target, explain);

        dictionaryManagement.Dictionary.add(word);
        //scence.getScene().getWindow().hide();
        System.out.println(dictionaryManagement.Dictionary.size());
    }

    public void delete() {
        String s = searchBar.getText();
        dictionaryManagement.delete(s, dictionaryManagement.Dictionary);
    }
    public void speaking(){
        voice Voice=new voice("kevin16");
        Voice.say(searchBar.getText());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (dictionaryManagement.Dictionary.size() ==0 ) {
                dictionaryManagement.Dictionary = dictionaryManagement.insertFromFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(dictionaryManagement.Dictionary.size());

    }
}

