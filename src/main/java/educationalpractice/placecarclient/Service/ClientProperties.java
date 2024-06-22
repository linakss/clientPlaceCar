package educationalpractice.placecarclient.Service;
import educationalpractice.placecarclient.MainApplication;
import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class ClientProperties {
    private final Properties properties = new Properties();

    private String allCar;
    private String findByIdCar;
    private String saveCar;
    private String updateCar;
    private String deleteCar;

    private String allCard;
    private String findByIdCard;
    private String saveCard;
    private String updateCard;
    private String deleteCard;

    private String allUser;
    private String findByIdUser;
    private String saveUser;
    private String updateUser;
    private String deleteUser;

    private String allPm;
    private String findByIdPm;
    private String savePm;
    private String updatePm;
    private String deletePm;
    private String checkPm;

    private String allEmployee;
    private String findByIdEmployee;
    private String saveEmployee;
    private String updateEmployee;
    private String deleteEmployee;
    private String checkEmployee;

    private String allAboutHuman;
    private String findByIdAboutHuman;
    private String saveAboutHuman;
    private String updateAboutHuman;
    private String deleteAboutHuman;


    public ClientProperties(){
        try (InputStream input = MainApplication.class.getClassLoader().getResourceAsStream("config.properties")) {
            System.out.println(input);
            properties.load(input);

            allCar = properties.getProperty("car.getAll");
            findByIdCar = properties.getProperty("car.findById");
            saveCar = properties.getProperty("car.save");
            updateCar = properties.getProperty("car.update");
            deleteCar = properties.getProperty("car.del");

            allCard = properties.getProperty("card.getAll");
            findByIdCard = properties.getProperty("card.findById");
            saveCard = properties.getProperty("card.save");
            updateCard = properties.getProperty("card.update");
            deleteCard = properties.getProperty("card.del");

            allUser = properties.getProperty("user.getAll");
            findByIdUser = properties.getProperty("user.findById");
            saveUser = properties.getProperty("user.save");
            updateUser = properties.getProperty("user.update");
            deleteUser = properties.getProperty("user.del");

            allPm = properties.getProperty("pm.getAll");
            checkPm = properties.getProperty("pm.checkini");
            findByIdPm = properties.getProperty("pm.findById");
            savePm = properties.getProperty("pm.save");
            updatePm = properties.getProperty("pm.update");
            deletePm = properties.getProperty("pm.del");

            allEmployee = properties.getProperty("employee.getAll");
            findByIdEmployee = properties.getProperty("employee.findById");
            saveEmployee = properties.getProperty("employee.save");
            updateEmployee = properties.getProperty("employee.update");
            deleteEmployee = properties.getProperty("employee.del");
            checkEmployee = properties.getProperty("employee.check");

            allAboutHuman = properties.getProperty("aboutHuman.getAll");
            findByIdAboutHuman = properties.getProperty("aboutHuman.findById");
            saveAboutHuman = properties.getProperty("aboutHuman.save");
            updateAboutHuman = properties.getProperty("aboutHuman.update");
            deleteAboutHuman = properties.getProperty("aboutHuman.del");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}



