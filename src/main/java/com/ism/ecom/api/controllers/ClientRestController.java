package com.ism.ecom.api.controllers;

import com.ism.ecom.web.dto.request.ClientCreateRequestDto;
import com.ism.ecom.web.dto.request.PanierDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


public interface ClientRestController {
    @GetMapping("/clients")//End Point
  ResponseEntity<Map<Object, Object>>  listerClient(
                          @RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "8") int size,
                          @RequestParam( defaultValue = "") String keyword
                         );
    @PostMapping("/clients")
    ResponseEntity<Map<Object, Object>> saveClient(@Valid @RequestBody ClientCreateRequestDto client, BindingResult bindingResult);
}
