package com.emazon.ApiReport.Domain.Spi;

import com.emazon.ApiReport.Domain.Model.Sale;

public interface SalePersistencePort {
    Sale saveSale(Sale sale);
}
