package educationalpractice.placecarclient;

import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.Service.PMServ;
import educationalpractice.placecarclient.Service.UserServ;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static educationalpractice.placecarclient.Controller.AdminMain.btnEditPMColor;
import static educationalpractice.placecarclient.MainApplication.idPMSer;

public class Places {
    UserServ userServ = new UserServ();
    //    LocalDateTime now = LocalDateTime.now();
//    LocalDateTime twoHoursLater = now.plusHours(2);
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//    String formattedTime = now.format(formatter);
//    String formattedFutureTime = twoHoursLater.format(formatter);
    private PM pm = new PM();
    private User user = new User();
    private PMServ serv = new PMServ();

    public GridPane createGridPanel() {
        final int SIZE = 10;
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
        int bb = letters.length;
        GridPane grid = new GridPane();
        for (int i = 0; i < bb; i++) {
            Label label = new Label("  "+letters[i] );
            GridPane.setConstraints(label, i + 1, 0);
            grid.getChildren().add(label);
        }
        for (int i = 0; i < SIZE; i++) {
            Label label = new Label(String.valueOf(i + 1));
            GridPane.setConstraints(label, 0, i + 1);
            grid.getChildren().add(label);
        }
        Button[][] cells = new Button[bb][SIZE];
        List<PM> allPMs = serv.getAll();
        for (int i = 0; i < bb; i++) {
            for (int j = 0; j < SIZE; j++) {
                Button cell = new Button();
                cell.setPrefSize(25, 25);
                String columnLetter = letters[i];
                int number = j + 1;
                boolean isReserved = allPMs.stream()
                        .anyMatch(pm -> pm.getRyadPM().equals(columnLetter) && pm.getNumberPM() == number);
                if (isReserved) {
                    cell.setStyle("-fx-background-color: #05d5d5;");
                }
                cell.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY) {
                        if (e.getClickCount() == 1) {
                            pm.setRyadPM(columnLetter);
                            pm.setNumberPM(number);
                            try {
                                serv.checkUserData(pm);
                                MainApplication.startAdmin("Автостоянка 'PlaceCar'");
                                Stage stage = (Stage) cell.getScene().getWindow();
                                stage.close();

                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    } else if (e.getButton() == MouseButton.SECONDARY) {
                        if (!cell.getStyle().equals("-fx-background-color: #05d5d5;")) {
                            System.out.println("Порковочное место свободно");
                        } else {
                            pm.setRyadPM(columnLetter);
                            pm.setNumberPM(number);
                            try {
                                serv.checkUserData(pm);
                                serv.delete(idPMSer); // Удаляем данные из базы
                                cell.setStyle("");
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                });
                cells[i][j] = cell;
                GridPane.setConstraints(cell, i + 1, j + 1);
                grid.getChildren().add(cell);
            }
        }
        return grid;
    }
}