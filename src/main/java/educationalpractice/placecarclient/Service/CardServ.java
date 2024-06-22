package educationalpractice.placecarclient.Service;


import com.google.gson.reflect.TypeToken;
import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.Card;
import educationalpractice.placecarclient.Entity.Card;
import educationalpractice.placecarclient.Response.BaseResp;
import educationalpractice.placecarclient.Response.DataResp;
import educationalpractice.placecarclient.Response.ListResp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import static educationalpractice.placecarclient.MainApplication.*;

import java.lang.reflect.Type;
import java.util.Comparator;

public class CardServ {
    @Getter
    private ObservableList<Card> data = FXCollections.observableArrayList();
    private final HttpServ http = new HttpServ();
    JsonServ service = new JsonServ();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResp<Card>>() {}.getType();
    private Type listType = new TypeToken<ListResp<Card>>() {}.getType();

    public void findById(Card data) {
        String temp = http.get(prop.getFindByIdCard() + data.getIdCard());
        DataResp<Card> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            cardFind = response.getData();
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
    private void sort(){
        data.sort(Comparator.comparing(Card::getNameCard));
    }
    public void getAll() {
        ListResp<Card> data = new ListResp<>();
        data = service.getObject(http.get(prop.getAllCard()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
    }

    public void add(Card data) {
        String temp = http.post(prop.getSaveCard(), service.getJson(data));
        DataResp<Card> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
            addCardData = response.getData();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(Card after, Card before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateCard(), service.getJson(after));
        DataResp<Card> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(Card data){
        String temp = http.delete(prop.getDeleteCard(), data.getIdCard());
        BaseResp response = service.getObject(temp,BaseResp.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
