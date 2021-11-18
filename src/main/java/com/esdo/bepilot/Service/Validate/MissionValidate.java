package com.esdo.bepilot.Service.Validate;

import com.esdo.bepilot.Exception.ListValidationException;
import com.esdo.bepilot.Model.Request.MissionRequest;
import com.esdo.bepilot.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@Component
public class MissionValidate {
    @Autowired
    private ListValidationException validationException;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Hàm check điều kiện đầu vào .
     * @param missionRequest
     */
    public void validate(MissionRequest missionRequest){
        List<ValidationException> listError = validationException.getValidationExceptions();
        listError.clear();
        if(!Objects.isNull(missionRequest.getCustomerId()) && customerRepository.findCustomerById(missionRequest.getCustomerId())==null){
            listError.add(new ValidationException("Invalid Customer"));
        }
        if(Objects.isNull(missionRequest.getName()) || missionRequest.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Name"));
        }
        if(Objects.isNull(missionRequest.getMissionType()) || missionRequest.getMissionType().isEmpty()){
            listError.add(new ValidationException("Invalid Mission Type"));
        }
        if(Objects.isNull(missionRequest.getLink()) || missionRequest.getLink().isEmpty()){
            listError.add(new ValidationException("Invalid Link"));
        }
        if(Objects.isNull(missionRequest.getCommunication()) || missionRequest.getCommunication().isEmpty()){
            listError.add(new ValidationException("Invalid Communication"));
        }
        if(Objects.isNull(missionRequest.getPriceUnit()) || missionRequest.getPriceUnit().doubleValue()<0){
            listError.add(new ValidationException("Invalid Price Unit"));
        }
        if(Objects.isNull(missionRequest.getQuantity()) || missionRequest.getQuantity().doubleValue()<0){
            listError.add(new ValidationException("Invalid Quantity"));
        }
        if(listError.size()>0){
            throw validationException;
        }
    }
}
