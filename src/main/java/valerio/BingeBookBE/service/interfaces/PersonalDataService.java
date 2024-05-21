package valerio.BingeBookBE.service.interfaces;

import valerio.BingeBookBE.dto.PersonalDataDTO;
import valerio.BingeBookBE.entity.PersonalData;

public interface PersonalDataService {
    PersonalData getPersonalDataById(Long id);

    void createPersonalDataOfUser(PersonalDataDTO personalDataDto, Long userId);

    void updatePersonalData(Long id, PersonalDataDTO personalDataDto);
    
    void deletePersonalDataById(Long id);
}
