package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePagePresenter implements Initializable {
    public static final String FXML = "/fxml/home.fxml";
    private ScannerManager manager;
    private GameService service;
    @FXML
    private JFXButton accept;
    @FXML
    private JFXTextField insertMoney;
    @FXML
    private Label infOfInsert;
    @FXML
    private JFXDialog window;
    private Image image;
    @FXML
    private ImageView homeImage;
    @FXML
    private MenuButton menu;
    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        manager = new ScannerManager();
        image = new Image(String.valueOf(getClass().getResource("/image/cas.jpg")), 910, 367, false, false);
        service = new GameService();
    }

    public void setService() {
        manager.setGameService(service);
    }

    private Tab createTab() {
        return new Tab();
    }

    private void toggleMenuButtonDisable(MenuButton menu, boolean visible) {
        menu.setDisable(visible);
    }

    private void toggleTabPaneVisible(TabPane pane, boolean visible) {
        pane.setVisible(visible);
    }

    private void toggleDialogWindowVisible(JFXDialog window, boolean visible) {
        window.setVisible(visible);
    }

    private void toggleButtonVisible(Button button, boolean visible) {
        button.setVisible(visible);
    }

    private void toggleLabelVisible(Label label, boolean visible) {
        label.setVisible(visible);
    }

    private void toggleImageViewVisible(ImageView imageView, boolean visible) {
        imageView.setVisible(visible);
    }

    public void viewHomeImage() {
        Platform.runLater(() -> {
            homeImage.setImage(image);
        });
    }

    //FXM Method
    @FXML
    public void onSlotKingGame() throws IOException {
        toggleTabPaneVisible(tabPane, true);
        toggleImageViewVisible(homeImage, false);
        Tab kingTab = createTab();
        kingTab.setText("King");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        kingTab.setContent(manager.showTabSlotKing());
        tabPane.getTabs().add(kingTab);
        closeTab(kingTab);
    }

    public void closeTab(Tab tab) {
        tab.setOnClosed(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (tabPane.getTabs().size() == 0) {
                    toggleTabPaneVisible(tabPane, false);
                    toggleImageViewVisible(homeImage, true);
                }
            }
        });
    }

    @FXML
    public void onInsertCash() {
        toggleDialogWindowVisible(window, true);
        toggleLabelVisible(infOfInsert, true);
        toggleButtonVisible(accept, true);
    }

    @FXML
    public void onAcceptCash() {
        if (Integer.parseInt(insertMoney.getText()) > 0) {
            toggleMenuButtonDisable(menu, false);
            service.setMoney(Integer.parseInt(insertMoney.getText()));
            toggleDialogWindowVisible(window, false);
            toggleLabelVisible(infOfInsert, false);
        }
        toggleButtonVisible(accept, false);
    }
}
