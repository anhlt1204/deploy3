package com.esdo.bepilot.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private int page ;
    private int size ;
    private int totalPage ;
    private int totalObject ;
    private T data;
}