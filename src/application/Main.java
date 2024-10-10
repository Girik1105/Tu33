/*******
 * <p> Main Class </p>
 * 
 * <p> Description: BookedIn is a versatile book management application designed for users who want to discover, track, 
 * and organize their reading journeys. It combines a powerful book search feature with user-friendly reading management 
 * tools, allowing users to keep track of books they want to read and those they've already finished. With two levels of 
 * access—user and admin—the application ensures that both casual readers and administrators have the tools they need to 
 * enjoy and manage their book collections effectively. </p>
 * 
 * <p> Copyright: Girik Manchanda, Becca Hurtado, Nghi Huynh, Saif Thabit © 2024 </p>
 * 
 * @author Girik Manchanda, Becca Hurtado, Nghi Huynh, Saif Thabit
 * 
 * @version 1.00	2024-10-18 The mainline of a JavaFX-based GUI implementation of a User 
 * 					Interface.
 * 
 */
package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPage loginPage = new LoginPage(); // Login page class
        loginPage.start(primaryStage); // Launch LoginPage
    }

    public static void main(String[] args) {
        launch(args);
    }
}