package com.data.app.service;

import com.data.app.entity.Sale;

import java.util.List;

public interface ISaleService {
    List<Sale> getAllSales();

    Sale getSaleById(long saleId);

    boolean addSale(Sale sale);

    void updateSale(Sale sale);

    void deleteSale(int saleId);
}
