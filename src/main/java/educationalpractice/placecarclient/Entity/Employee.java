package educationalpractice.placecarclient.Entity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Employee {
    private Long idEmployee; //индивид. номер сотрудника системы в базе
    //@NotNull
    private String lastname; //отчество сотрудника системы
    //@NotNull
    private String name; //имя сотрудника системы
    //@NotNull
    private String surname; //фамилия сотрудника системы
    //@NotNull
    private String role; //роль сотрудника системы
    //@NotNull
    private String numberPhone; // номер телефона сотрудника
    //@NotNull
    private String login; //логин сотрудника системы
    //@NotNull
    private String password; //пароль сотрудника системы
    private Car car;

    public String getFullName() {
        return String.format("%s %s %s", surname, name, lastname);
    }
    public String getPhoneNumber() {
        return String.format("%s",numberPhone);
    }


    @Override
    public String toString() {
        return surname + ' ' + name + ' ' +
                lastname + ' ' + role;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee that)) return false;
        return Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getPassword(), that.getPassword());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getLogin(), getPassword());
    }
}
