//package valerio.BingeBookBE.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import valerio.BingeBookBE.config.StringConfig;
//import valerio.BingeBookBE.dto.PersonalDataDTO;
//import valerio.BingeBookBE.dto.RoleDTO;
//import valerio.BingeBookBE.entity.PersonalData;
//import valerio.BingeBookBE.entity.Role;
//import valerio.BingeBookBE.repositories.PersonalDataDAO;
//
//import java.math.BigInteger;
//
///TODO: paused
//public class PersonalDataService {
//    final private PersonalDataDAO personalDataDAO;
//
//    @Autowired
//    PersonalDataService(PersonalDataDAO personalDataDAO) {
//        this.personalDataDAO = personalDataDAO;
//    }
//
//    ///CREATE
//    public PersonalData createRole(PersonalDataDTO personalDataDTO) {
//        PersonalData personalData = new PersonalData();
//        personalData.setName(personalDataDTO.name());
//        personalData.setSurname(personalDataDTO.surname());
//
//        return personalDataDAO.save(personalData);
//    }
//
//    ///READ
//    public PersonalData getPersonalData(int page, int size, String sortBy) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//        return personalDataDAO.findAll(pageable);
//    }
//
//    ///UPDATE
//    public Role updateRole(BigInteger id, RoleDTO roleDTO) {
//        Role role = personalDataDAO.findById(id).orElseThrow(() -> new IllegalArgumentException(StringConfig.errorNotFoundRole + ": " + id));
//        role.setName(roleDTO.name());
//        return personalDataDAO.save(role);
//    }
//
//    ///DELETE
//    public void deleteRole(BigInteger id) {
//        personalDataDAO.deleteById(id);
//    }
//}
