package com.example.jdieroller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class HelloController {
    @FXML
    private TextField txtNumDice;
    @FXML
    private ChoiceBox<String> cbType;
    @FXML
    private ChoiceBox<String> cbAbilities;
    @FXML
    private TextArea outBox;

    // populating the Choice Boxes.
    String[] dieType = {"D20", "D12", "D10", "D8", "D6", "D4", "D100"};
    String[] abilityMethod = {"3d6", "4d6"};


    @FXML
    private void btnRollClick() {
        int numDice = Integer.parseInt(txtNumDice.getText());
        String dieType = cbType.getValue();
        int result = roll(numDice, dieType);

        outBox.setText(String.format("You rolled a %d!!!", result));
    }

    @FXML
    private void btnClearClick() {
        txtNumDice.setText("");
        outBox.setText("");
        cbAbilities.setValue("");
        cbType.setValue("");
    }

    @FXML
    private void btnAbilityClick() {
        String style = cbAbilities.getValue();
        int result = abilityRoll(style);

        outBox.setText(String.format("You rolled %d!!!", result));
    }
    protected int roll(int numDice, String dieType) {
        int result = 0;
        int sides = getSides(dieType);
        int dieRoll = 0;
        Random die = new Random();

        for (int i = 1; i <= numDice; i++) {
            dieRoll = die.nextInt(1, sides + 1);
            result += dieRoll;
        }
        return result;
    }

    private int getSides(String dieType) {
        int sides = 0;
        switch (dieType) {
            case "D20":
                sides = 20;
                break;
            case "D12":
                sides = 12;
                break;
            case "D10":
                sides = 10;
                break;
            case "D8":
                sides = 8;
                break;
            case "D6":
                sides = 6;
                break;
            case "D4":
                sides = 4;
                break;
            case "D100":
                sides = 100;
                break;
            default:
                break;
        }
        return sides;
    }

    private int abilityRoll(String style) {
        int result = 0;
        int dieRoll = 0;
        ArrayList<Integer> dieList = new ArrayList<Integer>();

        if (style == "3d6") {
            result = roll(3, "D6");
        } else {
            for (int i = 1; i <= 4; i++) {
                dieRoll = roll(1, "D6");
                dieList.add(dieRoll);
            }
            dieList.sort(Comparator.reverseOrder());
            dieList.remove(0);

            for (int i : dieList) {
                result += i;
            }
        }
        return result;
    }
}