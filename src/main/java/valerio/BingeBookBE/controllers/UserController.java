package valerio.BingeBookBE.controllers;

import java.io.IOException;

import javax.naming.Binding;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.UserPersonalDataRoleDTO;
import valerio.BingeBookBE.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Validated UserPersonalDataRoleDTO userPersonalDataRoleDTO, BindingResult bindingResult)
            throws IOException {

        if (bindingResult.hasErrors()) {
            throw new IOException(StringConfig.errorInsertData);
        }

        userService.saveUser(userPersonalDataRoleDTO.userDTO(), userPersonalDataRoleDTO.personalDataDTO());
    }
}
