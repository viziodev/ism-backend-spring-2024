package com.ism.ecom.api.controllers.impl;

import com.ism.ecom.api.controllers.CommandeRestController;
import com.ism.ecom.api.controllers.dto.RestResponse;
import com.ism.ecom.data.entities.Commande;
import com.ism.ecom.services.CommandeService;
import com.ism.ecom.api.controllers.dto.request.PanierDto;
import com.ism.ecom.api.controllers.dto.response.CommandeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class CommandeRestControllerImpl implements CommandeRestController {
    private final CommandeService commandeService;
   // private final ArticleService articleService;
   // private final ClientService clientService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerCommandeUnClient(Long id, int page, int size) {
         Page<CommandeResponseDto> dataDto= getCommandeResponse(id,page,size);
        Map<Object, Object>  model= RestResponse.paginateResponse(dataDto.getContent(),new int[dataDto.getTotalPages()],dataDto.getNumber(),dataDto.getTotalElements(),dataDto.getTotalPages(),HttpStatus.OK);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> listerAllCommandes(int page, int size) {
        Page<CommandeResponseDto> dataDto=getCommandeResponse(null,page,size);
        Map<Object, Object>  model= RestResponse.paginateResponse(dataDto.getContent(),new int[dataDto.getTotalPages()],dataDto.getNumber(),dataDto.getTotalElements(),dataDto.getTotalPages(),HttpStatus.OK);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    private Page<CommandeResponseDto> getCommandeResponse(Long id, int page, int size){
        Page<Commande> commandes =id==null ?
                commandeService.getAllCommande(page, size)
                : commandeService.getCommandeByClientWithPaginate(id, page, size);

        return  commandes.map(CommandeResponseDto::toDto);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> saveCommande(PanierDto panier) {
        commandeService.saveCommande(panier);
        return new ResponseEntity<>(RestResponse.response(null,HttpStatus.NO_CONTENT), HttpStatus.OK);
    }
}
