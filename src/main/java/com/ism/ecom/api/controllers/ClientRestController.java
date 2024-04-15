package com.ism.ecom.api.controllers;

import com.ism.ecom.api.controllers.dto.request.ClientCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


public interface ClientRestController {
    @GetMapping("/clients")//End Point
  ResponseEntity<Map<Object, Object>>  listerClient(
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "8") int size,
                          @RequestParam( defaultValue = "") String keyword
                         );
    @GetMapping("/clients/telephone/{tel}")//End Point
    ResponseEntity<Map<Object, Object>>  listerClientParTelephone(
           @PathVariable String tel
    );
    @PostMapping("/clients")
    ResponseEntity<Map<Object, Object>> saveClient(@Valid @RequestBody ClientCreateRequestDto client, BindingResult bindingResult);
}
