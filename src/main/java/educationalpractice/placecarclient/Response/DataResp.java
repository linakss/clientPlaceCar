package educationalpractice.placecarclient.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResp<T> extends BaseResp {
    private T data;
    public DataResp(boolean success, String message, T data){
        super(success,message);
        this.data=data;
    }
}