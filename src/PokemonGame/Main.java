package PokemonGame;

import PokemonController.HomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../fxml/HomeScreen.fxml"));
        Parent root = fxmlLoader.load();

        primaryStage.setTitle("Welcome to the world of Pokémon");
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(1000);
        Scene scene = new Scene(root);


        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // NIET LETTEN OP ONDERSTAANDE CODE IN COMMENTAAR, KAN MISSCHIEN NOG VAN PAS KOMEN MAAR MOMENTEEL NIET NODIG

    /*@Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../fxml/HomeScreen.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../fxml/GameWorld.fxml"));
        Parent root = fxmlLoader.load();

        //Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Welcome to the world of Pokémon");
        Scene scene = new Scene(root, 750, 750);

        //primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setScene(scene);

        //GameworldController controller = fxmlLoader.getController();
        //scene.setOnKeyPressed(e -> {
          //  controller.keyboardUp(e.getCode());
        //});

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


        // Onderstaand is overbodig (denk ik)
        // HomeScreenController homeScreenController = fxmlLoader.getController();
        // homeScreenController.setHomeScreenScene(scene);
    }*/
}