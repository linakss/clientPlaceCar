package educationalpractice.placecarclient.Entity;

import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Card {
    private Long idCard; //индивид. номер карты гостя в базе
    private String numberCard; // номер банковской карты
    private String termCard; // срок действия карты
    private String bankCard; // банк карты гостя
    private String nameCard; // имя и фамилия гостя ЛАТИНИЦЕЙ
    private String cvvCard; // cvv-код на оборотной стороне карты

    //@NotNull
    private User user;
    private List<PM> pmList;

}
