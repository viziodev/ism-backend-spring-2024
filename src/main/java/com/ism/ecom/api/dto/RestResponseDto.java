package com.ism.ecom.api.dto;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class RestResponseDto {
    public static Map<Object, Object> response(Object results, Object pages,
                                               Object currentPage, Object totalItems,
                                               Object totalPages, HttpStatus status){
        Map<Object, Object>  reponse=new HashMap<Object, Object>();
        reponse.put("status",status.value());
        reponse.put("results",results);
        reponse.put("pages",pages);
        reponse.put("currentPage",currentPage);
        reponse.put("totalItems",totalItems);
        reponse.put("totalPages",totalPages);
        return reponse;
    }

    public static Map<Object, Object> response(Object results, HttpStatus status){
        Map<Object, Object>  reponse=new HashMap<Object, Object>();
        reponse.put("status",status.value());
        reponse.put("results",results);
        return reponse;
    }
}
