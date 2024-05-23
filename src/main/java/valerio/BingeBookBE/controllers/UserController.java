package valerio.BingeBookBE.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> getUserDetailById(@PathVariable("idUser") Long idUser) {
        try {
            if (idUser == null) {
                throw new IllegalArgumentException("User ID is missing in the request.");
            }

            User user = userService.getUserById(idUser);
            return ResponseEntityCustom.responseSuccess(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntityCustom.responseError("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> getUserDetail(HttpServletRequest request) {
       try {
            Long userId = (Long) request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("User ID is missing in the request.");
            }

            User user = userService.getUserById(userId);
            return ResponseEntityCustom.responseSuccess(user, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntityCustom.responseError("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getAllUsersWithPagination(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "id") String sortBy) {
    try {
        // Validate page and size parameters
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page number and size must be greater than zero.");
        }

        PaginatedResponse<User> paginatedResponse = userService.getAllUsersWithPagination(page, size, sortBy);
        return ResponseEntityCustom.responseSuccess(paginatedResponse, HttpStatus.OK);
    } catch (IllegalArgumentException e) {
        return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.BAD_REQUEST);
    } catch (Exception e) {
        return ResponseEntityCustom.responseError("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
    @PutMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUserbyId(@PathVariable("idUser") Long idUser) {
        userService.deleteUser(idUser);
        try {
            if (idUser == null) {
                throw new IllegalArgumentException("User ID is missing in the request.");
            }
            userService.deleteUser(idUser);
            return ResponseEntityCustom.responseSuccess("User deleted: " + idUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntityCustom.responseError("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/delete")
    public ResponseEntity<?> deleteUser(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getAttribute("userId");
            if (userId == null) {
                throw new IllegalArgumentException("User ID is missing in the request.");
            }
            userService.deleteUser(userId);
            return ResponseEntityCustom.responseSuccess("User deleted: " + userId, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return ResponseEntityCustom.responseError(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntityCustom.responseError("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
