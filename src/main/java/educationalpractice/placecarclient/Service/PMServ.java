package educationalpractice.placecarclient.Service;

import com.google.gson.reflect.TypeToken;
import educationalpractice.placecarclient.Entity.PM;
import educationalpractice.placecarclient.Places;
import educationalpractice.placecarclient.Response.BaseResp;
import educationalpractice.placecarclient.Response.DataResp;
import educationalpractice.placecarclient.Response.ListResp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

import static educationalpractice.placecarclient.MainApplication.idPMSer;

public class PMServ {
    @Getter
    private ObservableList<PM> data = FXCollections.observableArrayList();
    private final HttpServ http = new HttpServ();
    ErrorAlertServ alertServ = new ErrorAlertServ();
    JsonServ service = new JsonServ();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResp<PM>>() {
    }.getType();
    private Type listType = new TypeToken<ListResp<PM>>() {
    }.getType();
    Places[] place1 = new Places[10];
    Places[] place2 = new Places[10];
    private void sort(){
        data.sort(Comparator.comparing(PM::getStatusPM));
    }
    public List<PM> getAll() {
        ListResp<PM> data = new ListResp<>();
        data = service.getObject(http.get(prop.getAllPm()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
        return data.getData();
    }
    public void add(PM data) {
        String temp = http.post(prop.getSavePm(), service.getJson(data));
        DataResp<PM> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(PM after, PM before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdatePm(), service.getJson(after));
        DataResp<PM> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(PM data){
        String temp = http.delete(prop.getDeletePm(), data.getIdPM());
        BaseResp response = service.getObject(temp,BaseResp.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void checkUserData(PM data) {
        String temp = http.get(prop.getCheckPm() + data.getRyadPM() + "&numberPM=" + data.getNumberPM());
        DataResp<PM> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            idPMSer=response.getData();
            System.out.println(idPMSer);
        } else {
            throw new RuntimeException(response.getMessage());
        }}

}
