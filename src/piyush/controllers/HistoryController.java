package piyush.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class HistoryController {

    @FXML
    private ListView historyList;

    public void initializeCalculatiion(ArrayList<String> calculation_history){
        calculation_history.forEach((calculation)->{
            historyList.getItems().add(calculation);
        });
    }
}
