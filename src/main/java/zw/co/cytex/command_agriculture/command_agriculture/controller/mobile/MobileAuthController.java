package zw.co.cytex.command_agriculture.command_agriculture.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zw.co.cytex.command_agriculture.command_agriculture.model.Farmer;
import zw.co.cytex.command_agriculture.command_agriculture.model.User;
import zw.co.cytex.command_agriculture.command_agriculture.model.dto.MobileUser;
import zw.co.cytex.command_agriculture.command_agriculture.payload.ApiResponse;
import zw.co.cytex.command_agriculture.command_agriculture.payload.LoginUser;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.FarmerRepository;
import zw.co.cytex.command_agriculture.command_agriculture.repositories.UserRepository;
import zw.co.cytex.command_agriculture.command_agriculture.util.Constants;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author : Webster Moswa
 * @since : 12/02/2020, Wed
 * email: webstermoswa11@gmail.com
 * mobile: 0771407147
 **/

@RestController
@RequestMapping("/api/mobile")
public class MobileAuthController {
    @Autowired
    FarmerRepository farmerRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/register")
    public ApiResponse<MobileUser> register(@Valid MobileUser mobileUser){
        System.out.println(mobileUser.toString());
        Optional<Farmer> farmer=farmerRepository.findByNationalId(mobileUser.getNationalId());
        if(farmer.isPresent()) {
            if (userRepository.existsByUsername(mobileUser.getNationalId())) {
                return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(),"User already registered", null);
            } else {
                User user = new User();
                user.setName(farmer.get().getName());
                user.setSurname(farmer.get().getSurname());
                user.setEmail(farmer.get().getEmail());
                user.setUsername(farmer.get().getNationalId());
                user.setPassword(bcryptEncoder.encode(mobileUser.getPassword()));
                user.setAccess(Constants.ACCESS_FARMER);
                user.setStatus(Constants.STATUS_ACTIVE);
                user.setFirstTime(true);

                return new ApiResponse<>(HttpStatus.OK.value(), "Farmer successfully registered", userRepository.save(user));

            }
        }
        else{
            return new ApiResponse<>(HttpStatus.NOT_FOUND.value(),"Farmer can not be found", null);
        }
    }

    @RequestMapping("/login")
    public ApiResponse<User> login(@Valid  MobileUser loginUser) {
        if (authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNationalId().toLowerCase(), loginUser.getPassword())).isAuthenticated()) {
            return new ApiResponse<>(HttpStatus.OK.value(), "Login successful", null);
        } else {
            return new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Login failed", null);
        }
    }


}
