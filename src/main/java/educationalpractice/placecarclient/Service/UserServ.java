package educationalpractice.placecarclient.Service;
import static educationalpractice.placecarclient.MainApplication.forFind;
import com.google.gson.reflect.TypeToken;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.Entity.User;
import educationalpractice.placecarclient.Response.BaseResp;
import educationalpractice.placecarclient.Response.DataResp;
import educationalpractice.placecarclient.Response.ListResp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

public class UserServ {
    @Getter
    private ObservableList<User> data = FXCollections.observableArrayList();
    private final HttpServ http = new HttpServ();
    JsonServ service = new JsonServ();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResp<User>>() {}.getType();
    private Type listType = new TypeToken<ListResp<User>>() {}.getType();
    public void findById(User data) {
        String temp = http.get(prop.getFindByIdUser() + data.getIdUser());
        System.out.println(temp);
        DataResp<User> response = service.getObject(temp, dataType);
        System.out.println(response);
        if (response.isSuccess()) {
            forFind = response.getData();
            this.data.add(response.getData());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }
    private void sort(){
        data.sort(Comparator.comparing(User::getIdUser));
    }
    public List<User> getAll() {
        ListResp<User> data = new ListResp<>();
        data = service.getObject(http.get(prop.getAllUser()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
        return data.getData();
    }
    public void add(User data) {
        String temp = http.post(prop.getSaveUser(), service.getJson(data));
        DataResp<User> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(User after, User before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateUser(), service.getJson(after));
        DataResp<User> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(User data){
        String temp = http.delete(prop.getDeleteUser(), data.getIdUser());
        BaseResp response = service.getObject(temp,BaseResp.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }
}
