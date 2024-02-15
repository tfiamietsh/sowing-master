package sowingmaster;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Pair;

import java.util.Map;

public class Controller {
    public void initialize() {
        String[] types = {"Loam", "Peaty", "Silty", "Clay", "Cretaceous", "Sandy"};
        String[] times = {"Spring", "Summer", "Autumn", "Winter"};

        typeComboBox.setItems(FXCollections.observableArrayList(types));
        seedTimeComboBox.setItems(FXCollections.observableArrayList(times));
        cropTimeComboBox.setItems(FXCollections.observableArrayList(times));

        cropSelector = new CropSelector();
    }

    @FXML
    protected void onSubmitClick() {
        boolean floatParsed = true;
        float acidity = -1;

        try { acidity = Float.parseFloat(acidityTextField.getText()); }
        catch (NumberFormatException e) { floatParsed = false; }
        resultTextField.setStyle("-fx-text-fill: red");
        if (typeComboBox.getSelectionModel().getSelectedIndex() > -1 &&
                seedTimeComboBox.getSelectionModel().getSelectedIndex() > -1 &&
                cropTimeComboBox.getSelectionModel().getSelectedIndex() > -1) {
            if (floatParsed && 1 <= acidity && acidity <= 12) {
                Pair<String, Map<String, Double>> resultPair;
                StringBuilder text = new StringBuilder("Params:\n");

                resultTextField.setStyle("-fx-text-fill: black");
                resultPair = Crops.getNearest(cropSelector.evaluate(
                        typeComboBox.getSelectionModel().getSelectedIndex(), acidity,
                        3 * seedTimeComboBox.getSelectionModel().getSelectedIndex() + 4,
                        3 * cropTimeComboBox.getSelectionModel().getSelectedIndex() + 4));
                resultTextField.setText(resultPair.getKey());
                for (Map.Entry<String, Double> entry : resultPair.getValue().entrySet())
                    text.append("%s: %.1f\n".formatted(entry.getKey(), entry.getValue()));
                paramsTextArea.setText(text.toString());
            } else
                resultTextField.setText("Acidity range is [1; 12]");
        } else
            resultTextField.setText("Not all parameters are set");
    }

    @FXML
    private TextField acidityTextField, resultTextField;

    @FXML
    private ComboBox<String> typeComboBox, seedTimeComboBox, cropTimeComboBox;
    @FXML
    private TextArea paramsTextArea;

    private CropSelector cropSelector;
}
