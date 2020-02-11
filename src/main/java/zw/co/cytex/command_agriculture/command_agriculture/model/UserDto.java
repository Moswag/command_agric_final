package zw.co.cytex.command_agriculture.command_agriculture.model;

import lombok.Data;

/**
 * @author : Webster Moswa
 * @since : 15/01/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@Data
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String access;
    private String status;
    private boolean isFirstTime;
}
