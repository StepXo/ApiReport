package com.emazon.ApiReport.Infrastructure.Persistance.Repository;

import com.emazon.ApiReport.Infrastructure.Persistance.Entity.SaleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SaleRepository extends MongoRepository<SaleEntity,Long> {

}
