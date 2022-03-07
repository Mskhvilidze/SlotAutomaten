package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ScannerManager {
    private final Stage primaryStage;
    private Scene scene;

    public ScannerManager(Stage stage) throws IOException {
        this.primaryStage = stage;
        initView();
    }

    private void initView() throws IOException {
        showScene();
    }

    private void showScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(HomePagePresenter.FXML));
        HomePagePresenter presenter;
        Parent rootParent = loader.load();

        presenter = loader.getController();

        Platform.runLater(() -> {
            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.setTitle("Platform");
            scene = new Scene(rootParent, 1000, 600);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }
    }
