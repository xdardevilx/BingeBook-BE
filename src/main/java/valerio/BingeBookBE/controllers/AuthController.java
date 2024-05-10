package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.dto.UserLoginResponseDTO;
import valerio.BingeBookBE.dto.UserPersonalDataRoleDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.service.AuthService;
import valerio.BingeBookBE.service.UserService;

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

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserPersonalDataRoleDTO userPersonalDataRoleDTO,
            BindingResult bindingResult)
            throws Exception {

        User user = userService.saveUser(userPersonalDataRoleDTO.userDTO(), userPersonalDataRoleDTO.personalDataDTO());

        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        UserLoginResponseDTO userLoginResponseDTO = this.authService.authenticateUserAndGenerateToken(userLoginDTO);

        return new ResponseEntity<>(userLoginResponseDTO, HttpStatus.OK);
    }
}
