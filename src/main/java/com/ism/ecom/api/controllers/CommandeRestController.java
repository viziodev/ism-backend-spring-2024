package com.ism.ecom.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface CommandeRestController {
    @GetMapping("commandes/client/{id}")
    ResponseEntity<Map<Object, Object>> listerCommandeUnClient(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size);
    @GetMapping("/commandes")
    ResponseEntity<Map<Object, Object>>  listerAllCommandes(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size);
}
