package com.MoyanoManchon.FinalProject.controller;

import com.MoyanoManchon.FinalProject.exception.AlreadyExistException;
import com.MoyanoManchon.FinalProject.model.Invoice;
import com.MoyanoManchon.FinalProject.model.Product;
import com.MoyanoManchon.FinalProject.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(path = "/")
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) throws AlreadyExistException {
        return new ResponseEntity<>(this.invoiceService.create(invoice), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Invoice> update(@RequestBody Invoice invoice, @PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.update(invoice, id), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(this.invoiceService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<Invoice>> findByAll(){
        return new ResponseEntity<>(this.invoiceService.findByAll(), HttpStatus.OK);
    }

}
