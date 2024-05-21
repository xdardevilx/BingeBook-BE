package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import valerio.BingeBookBE.config.StringConfig;
import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.entity.PersonalData;
import valerio.BingeBookBE.repositories.PersonalDataDAO;
import valerio.BingeBookBE.service.interfaces.PersonalDataService;

@Service
public class PersonalDataServiceImpl implements PersonalDataService{
    final private PersonalDataDAO personalDataDAO;
    final private UserServiceImpl userService;

    @Autowired
    PersonalDataServiceImpl(PersonalDataDAO personalDataDAO, @Lazy UserServiceImpl userService) {
        this.personalDataDAO = personalDataDAO;
        this.userService = userService;
    }

    @Override
    public PersonalData getPersonalDataById(Long id) {
        return personalDataDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundPersonalData + ": " + id));
    }

    @Override
    public void createPersonalDataOfUser(PersonalDataDTO personalDataDto, Long userId) {
        PersonalData personalData = new PersonalData();
        personalData.setName(personalDataDto.name());
        personalData.setSurname(personalDataDto.surname());
        personalData.setUser(userService.getUserById(userId));
        personalDataDAO.save(personalData);
    }

    @Override
    public void updatePersonalData(Long id, PersonalDataDTO personalDataDto) {
        if(personalDataDAO.existsById(id)){
            PersonalData personalData = getPersonalDataById(id);
            personalData.setName(personalDataDto.name());
            personalData.setSurname(personalDataDto.surname());
            personalDataDAO.save(personalData);
        } else {
            throw new EntityNotFoundException(StringConfig.errorNotFoundPersonalData + ": " + id);
        }
    }

    @Override
    public void deletePersonalDataById(Long id) {
        if(personalDataDAO.existsById(id)){
            personalDataDAO.deleteById(id);
        } else {
            throw new EntityNotFoundException(StringConfig.errorNotFoundPersonalData + ": " + id);
        }
    }
}
