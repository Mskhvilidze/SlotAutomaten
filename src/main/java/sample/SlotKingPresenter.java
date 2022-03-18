package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SlotKingPresenter implements Initializable {
    public static final String FXML = "/fxml/king.fxml";
    private Image kingImage;
    private Image gif;
    private GameService gameService;
    @FXML
    private ImageView gifView;
    @FXML
    private JFXSlider cash;
    @FXML
    private Label info;
    @FXML
    private JFXButton dice;
    @FXML
    private ImageView kingView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kingImage = new Image(String.valueOf(getClass().getResource("/image/king.jpg")));
        gif = new Image(String.valueOf(getClass().getResource("/image/wurf.gif")), 550, 290, false, false);
    }

    public void viewImage() {
        Platform.runLater(() -> {
            kingView.setImage(kingImage);
        });
    }

    public void setService(GameService service) {
        this.gameService = service;
    }

    public String toString() {
        return "Deposited money: " + gameService.getMoney();
    }

    public void initialInf() {
        Platform.runLater(() -> {
            info.setText(toString());
        });
    }

    private void toggleMenuButtonDisable(JFXButton button, boolean visible) {
        button.setDisable(visible);
    }

    private void toggleImageViewVisible(ImageView imageView, boolean visible) {
        imageView.setVisible(visible);
    }

    @FXML
    public void onDiceGame() {
        gifView.setImage(gif);
        toggleImageViewVisible(kingView, false);
        toggleImageViewVisible(gifView, true);
        int[] arr = gameService.onDice();
        int betMoney = (int) cash.getValue();
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                if (arr[0] == arr[1]) {
                    int wonCash = betMoney * 10;
                    gameService.setMoney(wonCash);
                    Platform.runLater(() -> {
                        info.setTextFill(Color.MEDIUMSEAGREEN);
                        info.setText("You won" + "\n" + "Won: " + wonCash + "\n" + "Number 1: " + arr[0] + "\n" + "Number 2: " + arr[1] + "\n" + "Your money: " + gameService.getMoney());
                    });
                } else {
                    Platform.runLater(() -> {
                        info.setTextFill(Color.GOLDENROD);
                        info.setText("You lost" + "\n" + "Number 1: " + arr[0] + "\n" + "Number 2: " + arr[1] + "\n" + "Your money: " + gameService.getMoney());
                    });
                }

                toggleImageViewVisible(kingView, true);
                toggleImageViewVisible(gifView, false);
                toggleMenuButtonDisable(dice, true);
                t.cancel();
            }
        }, 5000);
    }

    @FXML
    public void onRaiseMoney(ActionEvent event) {
        toggleMenuButtonDisable(dice, false);
        if (gameService.getMoney() >= (int) cash.getValue()) {
            gameService.raiseMoney((int) cash.getValue());
        } else {
            Platform.runLater(() -> {
                info.setText("Your budget is not" + "\n" + "sufficient, please deposit it");
                info.setTextFill(Color.ORANGERED);
            });
        }
    }
}
