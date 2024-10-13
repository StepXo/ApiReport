package com.emazon.ApiReport.Infrastructure.Adapters.Jpa;

import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Domain.Spi.SalePersistencePort;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.SaleMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Repository.SaleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleJpa implements SalePersistencePort {
    private final SaleMapper mapper;
    private final SaleRepository repository;
    @Override
    public Sale saveSale(Sale sale) {
        return mapper.toSale(repository.save(mapper.toSaleEntity(sale)));
    }
}
