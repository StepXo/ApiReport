package com.emazon.ApiReport.Infrastructure.Persistance.Repository;

import com.emazon.ApiReport.Infrastructure.Persistance.Entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<SaleEntity,Long> {

}
