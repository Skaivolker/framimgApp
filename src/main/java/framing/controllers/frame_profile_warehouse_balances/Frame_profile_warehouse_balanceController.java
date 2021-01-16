package framing.controllers.frame_profile_warehouse_balances;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import framing.entities.Frame_profile_warehouse_balance;
import framing.repository.Frame_profile_warehouse_balanceRepository;
import framing.controllers.view.ViewLoader;

public class Frame_profile_warehouse_balanceController implements Initializable {

    private final Frame_profile_warehouse_balanceRepository frameprofilewarehousebalanceRepository = new Frame_profile_warehouse_balanceRepository();

    @FXML private TableView<Frame_profile_warehouse_balance> table;

    @FXML
    private void addFrame_profile_warehouse_balance(ActionEvent event) {
        framing.controllers.frame_profile_warehouse_balances.Frame_profile_warehouse_balanceAddController controller = (Frame_profile_warehouse_balanceAddController) ViewLoader
                .load(getClass().getResource("/ui/Frame_profile_warehouse_balance/add_Frame_profile_warehouse_balance.fxml"), "Add Frame_profile_warehouse_balance");
        controller.addPostOperationCallback(this::populateTable);
    }

    @FXML
    private void editFrame_profile_warehouse_balance(ActionEvent event) {
        Frame_profile_warehouse_balance frameprofilewarehousebalance = table.getSelectionModel().getSelectedItem();
        if (frameprofilewarehousebalance == null) {
            return;
        }
        framing.controllers.frame_profile_warehouse_balances.Frame_profile_warehouse_balanceAddController controller = (Frame_profile_warehouse_balanceAddController) ViewLoader.load(getClass()
                .getResource("/ui/Frame_profile_warehouse_balance/add_Frame_profile_warehouse_balance.fxml"), "Edit Frame_profile_warehouse_balance");
        controller.setEditable(frameprofilewarehousebalance);
        controller.addPostOperationCallback(this::populateTable);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    private void configureTable() {
        TableColumn<Frame_profile_warehouse_balance, Long> column1 = new TableColumn<>("frame_id");
        column1.setCellValueFactory(new PropertyValueFactory<>("frame_id"));

        TableColumn<Frame_profile_warehouse_balance, String> column2 = new TableColumn<>("frame_bar_count");
        column2.setCellValueFactory(new PropertyValueFactory<>("frame_bar_count"));

        TableColumn<Frame_profile_warehouse_balance, String> column3 = new TableColumn<>("frame_total_length");
        column3.setCellValueFactory(new PropertyValueFactory<>("frame_total_length"));


        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
    }

    private void populateTable() {
        ObservableList<Frame_profile_warehouse_balance> list = FXCollections.observableArrayList();
        list.addAll(frameprofilewarehousebalanceRepository.findAll());
        table.setItems(list);
    }
}
