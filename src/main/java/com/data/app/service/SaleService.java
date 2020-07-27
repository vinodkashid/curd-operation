package com.data.app.service;

import com.data.app.entity.Sale;
import com.data.app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Override
    public Sale getSaleById(long saleId) {
        return saleRepository.findById(saleId).get();
    }

    @Override
    public List<Sale> getAllSales() {
        List<Sale> list = new ArrayList<>();
        saleRepository.findAll().forEach(e -> list.add(e));
        return list;
    }

    @Override
    public synchronized boolean addSale(Sale sale) {
        List<Sale> list = saleRepository.findByTitleAndCategory(sale.getTitle(), sale.getCategory());
        if (list.size() > 0) {
            return false;
        } else {
            saleRepository.save(sale);
            return true;
        }
    }

    @Override
    public void updateSale(Sale sale) {
        saleRepository.save(sale);
    }

    @Override
    public void deleteSale(int saleId) {
        saleRepository.delete(getSaleById(saleId));
    }
}
