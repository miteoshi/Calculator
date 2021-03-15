package piyush.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import piyush.Main;
import piyush.utils.EvaluateString;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CalculatorController {
    @FXML
    private Label exp;

    @FXML
    private Label result;

    private ArrayList<String> calculation_history = new ArrayList<>();

    public void insertANS(String answer){
        exp.setText(exp.getText()+answer);
    }

   public void deleteLast(){
        if(!getExp().getText().isEmpty()) {
            StringBuilder text = new StringBuilder(getExp().getText());
            text.deleteCharAt(text.length()-1);
            getExp().setText(text.toString());
        }
    }

    public void insertOperator(String operator){
        exp.setText(exp.getText() + " " + operator + " ");
    }

    public void insertNumber(String number){
        exp.setText(exp.getText()+number);
    }

    public void clearExp(){
        exp.setText("");
    }

    public Label getExp() {
        return exp;
    }
    public Label getResult() {
        return result;
    }


    public void setResult(String new_result){
        this.result.setText("= " + new_result);
    }

    public void openHistoryWindow(){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/piyush/resources/history.fxml"));
            Parent root = loader.load();

            Main.getHistoryStage().setScene(new Scene(root));

            HistoryController historyController = loader.getController();
            historyController.initializeCalculatiion(calculation_history);

            Main.getHistoryStage().show();

        }
        catch (IOException ex){
            System.out.println(ex);
        }
    }

    public void addCalculation(String exp, String result){
        this.calculation_history.add(exp + " = " + result);
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        String buttonText = button.getText();


        switch (buttonText){
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
                insertNumber(buttonText);
                 break;
            case "+":
            case "*":
            case "/":
            case "-":
                insertOperator(buttonText);
                break;
            case "CE": clearExp();
                break;
            case "=":
                int result = EvaluateString.evaluate(this.getExp().getText());
                addCalculation(this.getExp().getText(), String.valueOf(result));
                setResult(String.valueOf(result));
                break;
            case "ANS":
                insertANS(getResult().getText().substring(2));
                break;
            case "DEL":
                deleteLast();
                break;
            case "HIST":
                openHistoryWindow();
                break;


        }



    }
}
