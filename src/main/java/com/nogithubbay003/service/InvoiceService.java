package com.nogithubbay003.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.nogithubbay003.domain.Invoice;
import com.nogithubbay003.dto.InvoiceDTO;
import com.nogithubbay003.dto.InvoiceSearchDTO;
import com.nogithubbay003.dto.InvoicePageDTO;
import com.nogithubbay003.dto.InvoiceConvertCriteriaDTO;
import com.nogithubbay003.service.GenericService;
import com.nogithubbay003.dto.common.RequestDTO;
import com.nogithubbay003.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface InvoiceService extends GenericService<Invoice, Integer> {

	List<Invoice> findAll();

	ResultDTO addInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO);

	ResultDTO updateInvoice(InvoiceDTO invoiceDTO, RequestDTO requestDTO);

    Page<Invoice> getAllInvoices(Pageable pageable);

    Page<Invoice> getAllInvoices(Specification<Invoice> spec, Pageable pageable);

	ResponseEntity<InvoicePageDTO> getInvoices(InvoiceSearchDTO invoiceSearchDTO);
	
	List<InvoiceDTO> convertInvoicesToInvoiceDTOs(List<Invoice> invoices, InvoiceConvertCriteriaDTO convertCriteria);

	InvoiceDTO getInvoiceDTOById(Integer invoiceId);







}





