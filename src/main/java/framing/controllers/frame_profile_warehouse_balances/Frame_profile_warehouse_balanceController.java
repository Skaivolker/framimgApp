package framing.controllers.frame_profile_warehouse_balances;

import framing.controllers.view.ViewLoader;
import framing.entities.Frame_profile_warehouse_balance;
import framing.repository.Frame_profile_warehouse_balanceRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

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
    @FXML
    private void deleteFrame_profile_warehouse_balance(ActionEvent event) {
        Frame_profile_warehouse_balance frameprofilewarehousebalance = table.getSelectionModel().getSelectedItem();
        if (frameprofilewarehousebalance == null) {
            return;
        }
        frameprofilewarehousebalanceRepository.delete(frameprofilewarehousebalance);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configureTable();
        populateTable();
    }

    private void configureTable() {
        TableColumn<Frame_profile_warehouse_balance, Long> column1 = new TableColumn<>("Id");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Frame_profile_warehouse_balance, String> column2 = new TableColumn<>("Title");
        column2.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Frame_profile_warehouse_balance, String> column3 = new TableColumn<>("Description");
        column3.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Frame_profile_warehouse_balance, String> column4 = new TableColumn<>("Author");
        column4.setCellValueFactory(new PropertyValueFactory<>("authorFullName"));

        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
    }

    private void populateTable() {
        ObservableList<Frame_profile_warehouse_balance> list = FXCollections.observableArrayList();
        list.addAll(frameprofilewarehousebalanceRepository.findAll());
        table.setItems(list);
    }
}
