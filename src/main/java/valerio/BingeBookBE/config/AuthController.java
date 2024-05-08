package valerio.BingeBookBE.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.dto.UserDTO;
import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.dto.UserLoginResponseDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.service.AuthService;
import valerio.BingeBookBE.service.UserService;

import java.io.IOException;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final UserService userService;

    @Autowired
    public AuthController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO userLoginDTO) {
        return new UserLoginResponseDTO(this.authService.authenticateDipendenteAndGenerateToken(userLoginDTO));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody @Validated UserDTO userDTO, @RequestBody @Validated PersonalDataDTO personalDataDTO, BindingResult validation) throws IOException {
//        if (validation.hasErrors()) {
//            throw new BadRequestException(validation.getAllErrors());
//        }

        return this.userService.saveUser(userDTO, personalDataDTO);
    }
}
