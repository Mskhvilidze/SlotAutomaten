package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.io.IOException;

public class HomePagePresenter {
    public static final String FXML = "/fxml/home.fxml";
    @FXML
    private MenuButton menu;
    @FXML
    private TabPane tabPane;
    private ScannerManager manager;

    private Tab createTab() {
        return new Tab();
    }

    private void toggleMenuButtonDisable(MenuButton menu, boolean visible) {
        menu.setDisable(visible);
    }

    private void toggleTabPaneVisible(TabPane pane, boolean visible) {
        pane.setVisible(visible);
    }

    //FXM Method
    @FXML
    public void onSlotKingGame(ActionEvent event) throws IOException {
        toggleTabPaneVisible(tabPane, true);
        Tab kingTab = createTab();
        kingTab.setText("King");
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        kingTab.setContent(manager.showTabSlotKing());
        tabPane.getTabs().add(kingTab);
    }

    @FXML
    public void onInsertCash() {
        manager = new ScannerManager();
        toggleMenuButtonDisable(menu, false);
    }
}
