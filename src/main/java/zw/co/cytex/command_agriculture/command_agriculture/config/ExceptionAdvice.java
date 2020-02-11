package zw.co.cytex.command_agriculture.command_agriculture.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;

/**
 * @author : Webster Moswa
 * @since : 15/01/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestControllerAdvice
public class ExceptionAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    protected ApiResponse handleGlobalException(Exception ex) {
        return new ApiResponse(400, ex.getMessage(), null);
    }


}
