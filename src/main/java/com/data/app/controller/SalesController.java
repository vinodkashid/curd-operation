package com.data.app.controller;

import com.data.app.entity.Sale;
import com.data.app.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("v1")
public class SalesController {
    @Autowired
    private ISaleService iSaleService;

    @GetMapping("sale/{id}")
    public ResponseEntity<Sale> getSalesById(@PathVariable("id") Integer id) {
        Sale Sale = iSaleService.getSaleById(id);
        return new ResponseEntity<Sale>(Sale, HttpStatus.OK);
    }

    @GetMapping("sale")
    public ResponseEntity<List<Sale>> getAllSaless() {
        List<Sale> list = iSaleService.getAllSales();
        return new ResponseEntity<List<Sale>>(list, HttpStatus.OK);
    }

    @PostMapping("sale")
    public ResponseEntity<Void> addSales(@RequestBody Sale sale, UriComponentsBuilder builder) {
        boolean flag = iSaleService.addSale(sale);
        if (flag == false) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/sale/{id}").buildAndExpand(sale.getSalesId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("sale")
    public ResponseEntity<Sale> updateSales(@RequestBody Sale sale) {
        iSaleService.updateSale(sale);
        return new ResponseEntity<Sale>(sale, HttpStatus.OK);
    }

    @DeleteMapping("sale/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable("id") Integer id) {
        iSaleService.deleteSale(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}