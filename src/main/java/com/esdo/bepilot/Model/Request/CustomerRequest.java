package com.esdo.bepilot.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotNull
    private String avatar;
    @NotNull
    private String name;
    @NotNull
    private String password ;
    @NotNull
    private String phone;
    @NotNull
    private String email ;
    @NotNull
    private String companyName;

}
