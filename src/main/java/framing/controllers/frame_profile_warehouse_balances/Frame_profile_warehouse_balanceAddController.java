package framing.controllers.frame_profile_warehouse_balances;

import framing.entities.Frame_profile;
import framing.entities.Frame_profile_warehouse_balance;
import framing.repository.Frame_profileRepository;
import framing.repository.Frame_profile_warehouse_balanceRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Frame_profile_warehouse_balanceAddController implements Initializable {

    private final Frame_profileRepository frameprofileRepository = new Frame_profileRepository();
    private final Frame_profile_warehouse_balanceRepository Frame_profile_warehouse_balanceRepository = new Frame_profile_warehouse_balanceRepository();
    public AnchorPane mainContainer;

    @FXML private TextField frame_id;
    @FXML private TextField description;
    @FXML private TextField authorId;

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
        this.description.setText(frameprofilewarehousebalance.getDescription());
        this.authorId.setText(frameprofilewarehousebalance.getAuthor().getId().toString());
    }

    @FXML
    private void addFrame_profile_warehouse_balance(ActionEvent event) {
        String Frame_profile_warehouse_balanceframe_id = frame_id.getText();
        String Frame_profile_warehouse_balanceDescription = description.getText();
        String Frame_profile_warehouse_balanceAuthorId = authorId.getText();

        if (Frame_profile_warehouse_balanceframe_id.isEmpty() || Frame_profile_warehouse_balanceDescription.isEmpty() || Frame_profile_warehouse_balanceAuthorId.isEmpty()) {
            System.out.println("Aizpildiet visus laukus!");
            //TODO: show user alert that all fields have to be filled
            return;
        }

        Long authorId = Long.parseLong(Frame_profile_warehouse_balanceAuthorId);
        Frame_profile frameprofile = frameprofileRepository.findOne(authorId);
        if (frameprofile == null) {
            System.out.println("Šāds produkts neeksistē");
            //TODO: author with such ID doesn't exist, display error to user!
            return;
        }

        if (editable == null) {
            Frame_profile_warehouse_balanceRepository.save(new Frame_profile_warehouse_balance(Frame_profile_warehouse_balanceframe_id, Frame_profile_warehouse_balanceDescription, frameprofile));
        } else {
            Frame_profile_warehouse_balance frameprofilewarehousebalance = Frame_profile_warehouse_balanceRepository.findOne(editable.getId());
            frameprofilewarehousebalance.setframe_id(Frame_profile_warehouse_balanceframe_id);
            frameprofilewarehousebalance.setDescription(Frame_profile_warehouse_balanceDescription);
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
        description.clear();
        authorId.clear();
    }

    private void closeStage() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }
}
