package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.dto.UserLoginResponseDTO;
import valerio.BingeBookBE.dto.UserPersonalDataRoleDTO;
import valerio.BingeBookBE.service.AuthService;
import valerio.BingeBookBE.service.UserService;
import valerio.BingeBookBE.config.StringConfig;

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
        return new UserLoginResponseDTO(this.authService.authenticateUserAndGenerateToken(userLoginDTO));
    }

    @PostMapping("/register")
    public void register(@RequestBody @Validated UserPersonalDataRoleDTO userPersonalDataRoleDTO,
                         BindingResult bindingResult)
            throws IOException {

        if (bindingResult.hasErrors()) {
            throw new IOException(StringConfig.errorInsertData);
        }

        userService.saveUser(userPersonalDataRoleDTO.userDTO(), userPersonalDataRoleDTO.personalDataDTO());
    }
}
