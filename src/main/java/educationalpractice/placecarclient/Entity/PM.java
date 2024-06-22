package educationalpractice.placecarclient.Entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class PM {
    private Long idPM; //индивид. номер парковочного места в базе
    private String ryadPM; //ряд парковочного места от A до Z
    private int numberPM; // номер парковочного места от 1 до 10
    private String statusPM; // статус парковочного места (свободно, забронировано, занято)
    private String pricePM; // цена парковочного места
    private Card card;
    private User user;

    public char getRyadPMm() {
        // Возвращаем первый символ строки ryadPM
        return ryadPM.charAt(0);
    }
}
