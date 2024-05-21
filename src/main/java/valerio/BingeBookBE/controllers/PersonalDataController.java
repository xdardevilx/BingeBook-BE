package valerio.BingeBookBE.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.service.PersonalDataServiceImpl;
import valerio.BingeBookBE.utils.ResponseEntityCustom;

@RestController
@RequestMapping("/personal-data")
public class PersonalDataController {

    final private PersonalDataServiceImpl personalDataService;

    @Autowired
    public PersonalDataController(PersonalDataServiceImpl personalDataService) {
        this.personalDataService = personalDataService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPersonalData(@RequestBody @Validated PersonalDataDTO personalDataDTO,
            HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");

        personalDataService.createPersonalDataOfUser(personalDataDTO, userId);

        return ResponseEntityCustom.responseSuccess("Personal data created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPersonalData(@PathVariable Long id) {
        return ResponseEntityCustom.responseSuccess(personalDataService.getPersonalDataById(id), HttpStatus.OK);
    }
    
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updatePersonalData(@PathVariable Long id, @RequestBody @Validated PersonalDataDTO personalDataDTO) {

        personalDataService.updatePersonalData(id, personalDataDTO);

        return ResponseEntityCustom.responseSuccess("Personal data updated successfully", HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> deletePersonalData(@PathVariable Long id) {
        personalDataService.deletePersonalDataById(id);

        return ResponseEntityCustom.responseSuccess("Personal data deleted successfully", HttpStatus.OK);
    }


}
