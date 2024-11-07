package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.ShoppingCart;
import com.nogithubbay003.dto.ShoppingCartDTO;
import com.nogithubbay003.dto.ShoppingCartSearchDTO;
import com.nogithubbay003.dto.ShoppingCartPageDTO;
import com.nogithubbay003.dto.ShoppingCartConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShoppingCartService extends GenericService<ShoppingCart, Integer> {

	List<ShoppingCart> findAll();

	ResultDTO addShoppingCart(ShoppingCartDTO shoppingCartDTO, RequestDTO requestDTO);

	ResultDTO updateShoppingCart(ShoppingCartDTO shoppingCartDTO, RequestDTO requestDTO);

    Page<ShoppingCart> getAllShoppingCarts(Pageable pageable);

    Page<ShoppingCart> getAllShoppingCarts(Specification<ShoppingCart> spec, Pageable pageable);

	ResponseEntity<ShoppingCartPageDTO> getShoppingCarts(ShoppingCartSearchDTO shoppingCartSearchDTO);
	
	List<ShoppingCartDTO> convertShoppingCartsToShoppingCartDTOs(List<ShoppingCart> shoppingCarts, ShoppingCartConvertCriteriaDTO convertCriteria);

	ShoppingCartDTO getShoppingCartDTOById(Integer shoppingCartId);







}





