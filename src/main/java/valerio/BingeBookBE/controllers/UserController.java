package valerio.BingeBookBE.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import valerio.BingeBookBE.dto.response.PaginatedResponse;
import valerio.BingeBookBE.entity.User;
import valerio.BingeBookBE.service.UserServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/detail/{idUser}")
    public ResponseEntity<?> getUserDetail(@PathVariable("idUser") Long idUser) {
        return ResponseEntityCustom.responseSuccess(userService.getUserById(idUser), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getAllUsersWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size, String sortBy) {
        PaginatedResponse<User> paginatedResponse = userService.getAllUsersWithPagination(page, size, sortBy);

        return ResponseEntityCustom.responseSuccess(paginatedResponse, HttpStatus.OK);
    }

    @PutMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable("idUser") Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntityCustom.responseSuccess("Usere deleted" + ": " + idUser, HttpStatus.OK);
    }

}
