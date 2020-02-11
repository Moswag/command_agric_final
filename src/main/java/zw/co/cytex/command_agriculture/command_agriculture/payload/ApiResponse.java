package zw.co.cytex.command_agriculture.command_agriculture.payload;

import lombok.Data;

/**
 * @author : Webster Moswa
 * @since : 15/01/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
public class ApiResponse<T> {
    private int status;
    private String message;
    private Object result;

    public ApiResponse(int status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

}
