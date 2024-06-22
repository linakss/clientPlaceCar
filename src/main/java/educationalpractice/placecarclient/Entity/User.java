package educationalpractice.placecarclient.Entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {
    private Long idUser; //индивид. номер гостя парковки
    private String timeEntry; //Время въезда
    private String timeDeparture; //Время выезда
    private Employee employee;
    public String getEmployeeFullName() {
        if (employee != null) {
            return employee.getFullName();
        } else {
            return "Данные отсутствуют";
        }
    }
    public String getEmployeePhone() {
        if (employee != null) {
            return employee.getPhoneNumber();
        } else {
            return "Данные отсутствуют";
        }
    }
    @Override
    public String toString() {
        return  " "+employee+'\'';
    }
}