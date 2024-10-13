package com.emazon.ApiReport.Infrastructure.Adapters.Jpa;

import com.emazon.ApiReport.Domain.Model.Sale;
import com.emazon.ApiReport.Infrastructure.Persistance.Entity.SaleEntity;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.SaleMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Repository.SaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SaleJpaTest {

    @Mock
    private SaleMapper saleMapper;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleJpa saleJpa;
    private Sale sale;
    private SaleEntity saleEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sale = new Sale();
        saleEntity = new SaleEntity();
    }

    @Test
    void shouldSaveSaleAndReturnMappedSale() {

        when(saleMapper.toSaleEntity(sale)).thenReturn(saleEntity);
        when(saleRepository.save(saleEntity)).thenReturn(saleEntity);
        when(saleMapper.toSale(saleEntity)).thenReturn(sale);

        Sale result = saleJpa.saveSale(sale);

        assertNotNull(result);
        assertEquals(sale, result);

        verify(saleMapper).toSaleEntity(sale);
        verify(saleRepository).save(saleEntity);
        verify(saleMapper).toSale(saleEntity);
    }
}
