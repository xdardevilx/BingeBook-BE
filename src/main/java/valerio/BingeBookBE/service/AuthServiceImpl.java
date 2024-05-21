package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.UserLoginDTO;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.security.JWTTools;
import valerio.BingeBookBE.service.interfaces.AuhtService;

@Service
public class AuthServiceImpl implements AuhtService{

    private final UserServiceImpl userService;
    private final JWTTools jwtTools;
    private final PasswordEncoder bcrypt;

    @Autowired
    public AuthServiceImpl(UserServiceImpl userService, JWTTools jwtTools, PasswordEncoder bcrypt) {
        this.userService = userService;
        this.jwtTools = jwtTools;
        this.bcrypt = bcrypt;
    }

    @Override
    public String authenticateUserAndGenerateToken(UserLoginDTO payload) {

        User user = userService.getUserByEmail(payload.email());

        if (!bcrypt.matches(payload.password(), user.getPassword())) {
            throw new RuntimeException(StringConfig.errorEmailAndPassword);
        }

        return jwtTools.createToken(user);
    }

}