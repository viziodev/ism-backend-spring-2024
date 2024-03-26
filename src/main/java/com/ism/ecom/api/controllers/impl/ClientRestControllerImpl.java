package com.ism.ecom.api.controllers.impl;

import com.ism.ecom.api.controllers.ClientRestController;
import com.ism.ecom.api.utils.RestResponse;
import com.ism.ecom.data.entities.Client;
import com.ism.ecom.services.ClientService;
import com.ism.ecom.web.dto.request.ClientCreateRequestDto;
import com.ism.ecom.web.dto.response.ClientShowReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class ClientRestControllerImpl implements ClientRestController {
    private final ClientService clientService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerClient(int page, int size, String keyword) {
        Page<Client> clients = clientService.getAllClientWithPaginateAndFilter(keyword, PageRequest.of(page, size));
        Page<ClientShowReponseDto> dataDto = clients.map(ClientShowReponseDto::toDo);
        Map<Object, Object>  model= RestResponse.paginateResponse(dataDto.getContent(),new int[dataDto.getTotalPages()],dataDto.getNumber(),dataDto.getTotalElements(),dataDto.getTotalPages(),HttpStatus.OK);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>>  saveClient(ClientCreateRequestDto client,BindingResult bindingResult) {
        Map<Object, Object> response;
        if (bindingResult.hasErrors()){
            Map<String, String> errors =new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(),fieldError.getDefaultMessage()));
            response= RestResponse.response(errors, HttpStatus.NOT_FOUND);
        }else{
            clientService.addClient(client);
            response= RestResponse.response(client,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
