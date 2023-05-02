package com.MoyanoManchon.FinalProject.service;

import com.MoyanoManchon.FinalProject.exception.AlreadyExistException;
import com.MoyanoManchon.FinalProject.exception.NotFoundException;
import com.MoyanoManchon.FinalProject.model.Invoice;
import com.MoyanoManchon.FinalProject.repository.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice create(Invoice newInvoice) throws AlreadyExistException {

        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(newInvoice.getId());

        if (invoiceOp.isPresent()){
            log.info("La factura ya existe" + newInvoice);
            throw new AlreadyExistException("La factura ya existe");
        }else{
            return this.invoiceRepository.save(newInvoice);
        }

    }

    public Invoice update(Invoice newInvoice, Long id) throws Exception{

        if (id <= 0){
            throw new Exception("El id no es valido");
        }

        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(newInvoice.getId());

        if(invoiceOp.isEmpty()){
            log.info("La factura ya existe en la base de datos" +  newInvoice);
            throw new NotFoundException("La factura ya existe en la base de datos");
        } else{
            Invoice invoiceBd = invoiceOp.get();
            invoiceBd.setId(newInvoice.getId());
            invoiceBd.setTotal(newInvoice.getTotal());

            return this.invoiceRepository.save(newInvoice);
        }

    }

    public Invoice findById(Long id) throws Exception{

        if (id <= 0){
            throw new Exception("El id no es valido");
        }

        Optional<Invoice> invoiceOp = this.invoiceRepository.findById(id);

        if (invoiceOp.isPresent()){
            log.info("La factura ya existe en la base de datos" + id);
            throw new NotFoundException("La factura ya existe en la base de datos");
        } else{
            return invoiceOp.get();
        }

    }

    public List<Invoice> findByAll(){

        return this.invoiceRepository.findAll();

    }

}
