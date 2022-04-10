package sample;

import com.jfoenix.controls.JFXDialog;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;

public class Service {

    public void toggleImageVisible(ImageView imageView, boolean visible) {
        imageView.setVisible(visible);
    }

    public void toggleMenuButtonDisable(MenuButton menu, boolean visible) {
        menu.setDisable(visible);
    }

    public void toggleTabPaneVisible(TabPane pane, boolean visible) {
        pane.setVisible(visible);
    }

    public void toggleDialogWindowVisible(JFXDialog window, boolean visible) {
        window.setVisible(visible);
    }

    public void toggleButtonVisible(Button button, boolean visible) {
        button.setVisible(visible);
    }

    public void toggleButtonDisable(Button button, boolean disable) {
        button.setDisable(disable);
    }
}
