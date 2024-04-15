package com.ism.ecom.services;

import com.ism.ecom.data.entities.Commande;
import com.ism.ecom.api.controllers.dto.request.PanierDto;
import org.springframework.data.domain.Page;


public interface CommandeService {
      Page<Commande> getCommandeByClientWithPaginate(Long id,int page, int size);
      Page<Commande> getAllCommande(int page, int size);
      void saveCommande(PanierDto panierDto);

}
