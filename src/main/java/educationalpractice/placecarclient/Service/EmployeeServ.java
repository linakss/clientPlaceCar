package educationalpractice.placecarclient.Service;

import com.google.gson.reflect.TypeToken;
import educationalpractice.placecarclient.Entity.Car;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Entity.Employee;
import educationalpractice.placecarclient.Response.BaseResp;
import educationalpractice.placecarclient.Response.DataResp;
import educationalpractice.placecarclient.Response.ListResp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.List;

import static educationalpractice.placecarclient.MainApplication.empl;
import static educationalpractice.placecarclient.MainApplication.emporio;

public class EmployeeServ {
    @Getter
    private ObservableList<Employee> data = FXCollections.observableArrayList();
    private final HttpServ http = new HttpServ();
    JsonServ service = new JsonServ();
    ClientProperties prop = new ClientProperties();
    private Type dataType = new TypeToken<DataResp<Employee>>() {}.getType();
    private Type listType = new TypeToken<ListResp<Employee>>() {}.getType();

    public void findById(Employee data) {
        String temp = http.get(prop.getFindByIdEmployee() + data.getIdEmployee());
        DataResp<Employee> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            empl.setSurname(data.getSurname());
            empl.setLastname(data.getLastname());
            empl.setName(data.getName());
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    private void sort(){
        data.sort(Comparator.comparing(Employee::getSurname));
    }

    public List<Employee> getAll() {
        ListResp<Employee> data = new ListResp<>();
        data = service.getObject(http.get(prop.getAllEmployee()), listType);
        if (data.isSuccess()) {
            this.data.addAll(data.getData());
            emporio.setLastname(data.getData().toString());
            sort();
            this.data.forEach(System.out::println);
        } else {
            throw new RuntimeException(data.getMessage());
        }
        return data.getData();
    }
    public void add(Employee data) {
        String temp = http.post(prop.getSaveEmployee(), service.getJson(data));
        DataResp<Employee> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.add(response.getData());
            emporio.setRole(data.getRole());
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void update(Employee after, Employee before) {
        System.out.println(before);
        System.out.println(after);
        String temp = http.put(prop.getUpdateEmployee(), service.getJson(after));
        DataResp<Employee> response = service.getObject(temp, dataType);
        if (response.isSuccess()) {
            this.data.remove(before);
            this.data.add(after);
            sort();
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    public void delete(Employee data){
        String temp = http.delete(prop.getDeleteEmployee(), data.getIdEmployee());
        BaseResp response = service.getObject(temp,BaseResp.class);
        if (response.isSuccess()){
            this.data.remove(data);
        }else {
            throw new RuntimeException(response.getMessage());
        }
    }

}
