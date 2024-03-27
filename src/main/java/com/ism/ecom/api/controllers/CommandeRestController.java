package com.ism.ecom.api.controllers;

import com.ism.ecom.web.dto.request.PanierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/commandes")
public interface CommandeRestController {
    @GetMapping("/client/{id}")
    ResponseEntity<?> listerCommandeUnClient(@PathVariable Long id,
                                             @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size);

    @GetMapping("")
    ResponseEntity<?> listerAllCommandes(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "5") int size);


    @PostMapping("")
    ResponseEntity<?> saveCommande(PanierDto panier );
}
