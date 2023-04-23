package PokemonGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fmxl/GameWorld.fxml"));
        Parent root = fxmlLoader.load();

        //FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../fmxl/HomeScreen.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Welcome to the world of Pokémon");
        Scene scene = new Scene(root, 750, 750);

        //primaryStage.setScene(new Scene(root, 300, 275));

        //primaryStage.setScene(scene);

        GameworldController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(e -> {
            controller.keyboardUp(e.getCode());
        });

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}