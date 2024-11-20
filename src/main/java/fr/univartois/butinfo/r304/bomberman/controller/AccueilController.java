package fr.univartois.butinfo.r304.bomberman.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class AccueilController {

    @FXML
    private RadioButton level1;

    @FXML
    private RadioButton level2;

    @FXML
    private RadioButton level3;

    private ToggleGroup levelGroup;

    @FXML
    public void initialize() {
        levelGroup = new ToggleGroup();
        level1.setToggleGroup(levelGroup);
        level2.setToggleGroup(levelGroup);
        level3.setToggleGroup(levelGroup);
    }

    @FXML
    public void startGame(ActionEvent actionEvent) {
        RadioButton selectedRadioButton = (RadioButton) levelGroup.getSelectedToggle();
        String level = selectedRadioButton.getText();

        // Logic to start the game with the selected level
        System.out.println("Starting game with level: " + level);

        // You can add more logic here to initialize the game with the selected level
    }
}
