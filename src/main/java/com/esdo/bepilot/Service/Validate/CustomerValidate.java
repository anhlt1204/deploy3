package com.esdo.bepilot.Service.Validate;

import com.esdo.bepilot.Exception.ListValidationException;
import com.esdo.bepilot.Model.Request.CustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.util.List;
import java.util.Objects;

@Component
public class CustomerValidate {
    @Autowired
    public ListValidationException validationException ;

    public CheckValid checkValid = new CheckValid() ;

    public void validate(CustomerRequest request) {
        List<ValidationException> listError =  validationException.getValidationExceptions() ;

        if(Objects.isNull(request.getName()) || request.getName().isEmpty() ){
            listError.add(new ValidationException("Invalid Name"));
        }
        if(Objects.isNull(request.getAvatar()) || request.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Avatar"));
        }
        if(Objects.isNull(request.getPassword()) || request.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Password"));
        }
        if(Objects.isNull(request.getEmail()) || request.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Email"));
        }
        if(Objects.isNull(request.getPhone()) || request.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Phone"));
        }
        if(Objects.isNull(request.getCompanyName()) || request.getName().isEmpty()){
            listError.add(new ValidationException("Invalid Company Name"));
        }

        if(listError.size()>0){
            throw validationException;
        }

    }
}
