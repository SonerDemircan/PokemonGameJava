package PokemonGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PokemonApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(PokemonApplication.class.getResource("../fxml/HomeScreen.fxml"));

        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Welcome to the world of Pokémon");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(1000);
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}