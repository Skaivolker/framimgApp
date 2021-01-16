package framing.controllers.frame_profile_warehouse_balances;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import framing.entities.Frame_profile;
import framing.repository.Frame_profileRepository;
import framing.entities.Frame_profile_warehouse_balance;
import framing.repository.Frame_profile_warehouse_balanceRepository;

public class Frame_profile_warehouse_balanceAddController implements Initializable {

    private final Frame_profileRepository frameprofileRepository = new Frame_profileRepository();
    private final Frame_profile_warehouse_balanceRepository Frame_profile_warehouse_balanceRepository = new Frame_profile_warehouse_balanceRepository();
    public AnchorPane mainContainer;

    @FXML private TextField frame_id;
    @FXML private TextField frame_bar_count;
    @FXML private TextField frame_total_length;

    @FXML private StackPane rootPane;

    private Frame_profile_warehouse_balance editable;

    private Runnable closeDialogCallback;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void addPostOperationCallback(Runnable callback) {
        this.closeDialogCallback = callback;
    }

    public void setEditable(Frame_profile_warehouse_balance frameprofilewarehousebalance) {
        this.editable = frameprofilewarehousebalance;
        this.frame_id.setText(frameprofilewarehousebalance.getframe_id());
        this.frame_bar_count.setText(frameprofilewarehousebalance.getframe_id());
        this.frame_total_length.setText(frameprofilewarehousebalance.getframe_id().toString());
    }

    @FXML
    private void addFrame_profile_warehouse_balance(ActionEvent event) {
        String Frame_profile_warehouse_balanceframe_id = frame_id.getText();
        String Frame_profile_warehouse_balanceframe_bar_count = frame_bar_count.getText();
        String Frame_profile_warehouse_balanceframe_total_length = frame_total_length.getText();

        if (Frame_profile_warehouse_balanceframe_id.isEmpty() || Frame_profile_warehouse_balanceframe_bar_count.isEmpty() || Frame_profile_warehouse_balanceframe_total_length.isEmpty()) {
            //TODO: show user alert that all fields have to be filled
            return;
        }

        Long frame_total_length = Long.parseLong(Frame_profile_warehouse_balanceframe_total_length);
        Frame_profile frameprofile = frameprofileRepository.findOne(frame_total_length);
        if (frameprofile == null) {
            //TODO: author with such ID doesn't exist, display error to user!
            return;
        }

        if (editable == null) {
            Frame_profile_warehouse_balanceRepository.save(new Frame_profile_warehouse_balance(Frame_profile_warehouse_balanceframe_id, Frame_profile_warehouse_balanceframe_bar_count, frameprofile));
        } else {
            Frame_profile_warehouse_balance frameprofilewarehousebalance = Frame_profile_warehouse_balanceRepository.findOne(editable.getId());
            frameprofilewarehousebalance.setframe_id(Frame_profile_warehouse_balanceframe_id);
            frameprofilewarehousebalance.setDescription(Frame_profile_warehouse_balanceframe_bar_count);
            frameprofilewarehousebalance.setAuthor(frameprofile);
            Frame_profile_warehouse_balanceRepository.merge(frameprofilewarehousebalance);
        }
        clearEntries();
        closeStage();
        closeDialogCallback.run();
    }

    @FXML
    private void cancel(ActionEvent event) {
        closeStage();
    }

    private void clearEntries() {
        editable = null;
        frame_id.clear();
        frame_bar_count.clear();
        frame_total_length.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
