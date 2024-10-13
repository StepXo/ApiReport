package com.emazon.ApiReport.Infrastructure.Persistance.Mapper;

import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Infrastructure.Persistance.Entity.SaleEntity;
import org.mapstruct.Mapper;

import static com.emazon.ApiReport.Infrastructure.Utils.InfraConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface SaleMapper {
    SaleEntity toSaleEntity(Sale sale);
    Sale toSale(SaleEntity saleEntity);
}
