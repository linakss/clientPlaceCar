package educationalpractice.placecarclient.Service;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonServ {

    private final Gson  gson = new Gson();
    public <T>T getObject (String response, Type type){
        return (T) gson.fromJson(response,type);
    }

    public <T>String getJson (T data){
        return  gson.toJson(data);
    }
}