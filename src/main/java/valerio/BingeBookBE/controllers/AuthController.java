package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.dto.UserPersonalDataRoleDTO;
import valerio.BingeBookBE.service.AuthServiceImpl;
import valerio.BingeBookBE.service.UserServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final UserServiceImpl userService;

    @Autowired
    public AuthController(AuthServiceImpl authService, UserServiceImpl userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserPersonalDataRoleDTO userPersonalDataRoleDTO,
            BindingResult bindingResult) {

        userService.createUser(userPersonalDataRoleDTO.userDTO(),
                userPersonalDataRoleDTO.personalDataDTO());

        return ResponseEntityCustom.responseSuccess(StringConfig.successCreateUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        return ResponseEntityCustom.responseSuccess(authService.authenticateUserAndGenerateToken(userLoginDTO),
                HttpStatus.OK);
    }
}
