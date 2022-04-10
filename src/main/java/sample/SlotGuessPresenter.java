package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class SlotGuessPresenter implements Initializable {
    public static final String FXML = "/fxml/guess.fxml";
    private Service service;
    private GameService gameService;
    private Image gifView;
    private Image imageHome;
    private Rectangle2D rectangle2D;
    @FXML
    private JFXSlider cash;
    @FXML
    private ChoiceBox combo;
    @FXML
    private Label info;
    @FXML
    private JFXButton dice;
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView gifDice;

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public String toString() {
        return "Deposited money: " + gameService.getMoney();
    }

    public void initialInf() {
        Platform.runLater(() -> {
            info.setText(toString());
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 1; i <= 6; i++) {
            combo.getItems().add(i);
        }
        imageHome = new Image(String.valueOf(getClass().getResource("/image/casino1.jfif")), 633, 310, false, false);
        gifView = new Image(String.valueOf(getClass().getResource("/image/wurf.gif")), 633, 310, false, false);
        service = new Service();
        rectangle2D = new Rectangle2D(30, -20, 700, 410);
        gifDice.setViewport(rectangle2D);
    }

    @FXML
    public void setImageView() {
        Platform.runLater(() -> {
            imageView.setImage(imageHome);
        });
    }

    @FXML
    public void onDiceGame() {
        if (gameService.getMoney() >= (int) cash.getValue()) {
            gifDice.setImage(gifView);
            service.toggleImageVisible(imageView, false);
            service.toggleImageVisible(gifDice, true);
            int betMoney = (int) cash.getValue();
            int[] arr = gameService.onDice();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (Integer.parseInt(combo.getValue().toString()) == arr[0]) {
                        int wonCash = betMoney * 20;
                        gameService.setMoney(wonCash);
                        Platform.runLater(() -> {
                            info.setTextFill(Color.MEDIUMSEAGREEN);
                            info.setText("You won" + "\n" + "Won: " + wonCash + "\n" + "Number: " + arr[0] + "\n" + "The number you chose: " + Integer.parseInt(combo.getValue().toString()) + "\n" + "Your money: " + gameService.getMoney());
                        });
                    } else {
                        Platform.runLater(() -> {
                            info.setTextFill(Color.GOLDENROD);
                            info.setText("You lost" + "\n" + "Number: " + arr[0] + "\n" + "The number you chose: " + Integer.parseInt(combo.getValue().toString()) + "\n" + "Your money: " + gameService.getMoney());
                        });
                    }
                    service.toggleImageVisible(imageView, true);
                    service.toggleImageVisible(gifDice, false);
                    service.toggleButtonDisable(dice, true);
                    t.cancel();
                }
            }, 5000);
        } else {
            info.setText("Please select a number!");
            info.setTextFill(Color.ORANGERED);
        }
    }

    @FXML
    public void onSelectNumber(ActionEvent event) {
        if (combo.getValue() != null && gameService.getMoney() >= (int) cash.getValue()) {
            service.toggleButtonDisable(dice, false);
            gameService.raiseMoney((int) cash.getValue());
            info.setTextFill(Color.WHITE);
            initialInf();
        } else {
            info.setText("Please select a number!");
            info.setTextFill(Color.ORANGERED);
        }
    }
}
