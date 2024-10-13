package com.emazon.ApiReport.Infrastructure.Configuration;


import com.emazon.ApiReport.Application.Handler.SaleHandler;
import com.emazon.ApiReport.Application.Service.SaleService;
import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Spi.*;
import com.emazon.ApiReport.Domain.UseCase.SaleUseCase;
import com.emazon.ApiReport.Infrastructure.Adapters.Feign.*;
import com.emazon.ApiReport.Infrastructure.Adapters.Jpa.SaleJpa;
import com.emazon.ApiReport.Infrastructure.Adapters.SecurityConfig.jwtconfiguration.JwtService;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.CartMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.ItemMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Mapper.SaleMapper;
import com.emazon.ApiReport.Infrastructure.Persistance.Repository.SaleRepository;
import com.emazon.ApiReport.Infrastructure.Utils.UserExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final SaleMapper mapper;
    private final SaleRepository saleRepository;
    private final SaleHandler saleHandler;
    private final JwtService jwtService;
    private final IStockFeign iStockFeign;
    private final IUserFeign userFeign;
    private final ICartFeign cartFeign;
    private final ItemMapper itemMapper;
    private final CartMapper cartMapper;

    @Autowired
    private ITransactionFeign transactionFeign;

    @Bean
    public SalePersistencePort saleRepositoryPort() {
        return new SaleJpa(mapper, saleRepository);
    }


    @Bean
    public UserExtractor userExtractor() {
        return new UserExtractor(jwtService, userFeign);
    }

    @Bean
    public StockFeignPort stockFeignPort() {
        return new StockFeign(iStockFeign, itemMapper);
    }

    @Bean
    public CartFeignPort cartFeignPort() {
        return new CartFeign(cartFeign, cartMapper, itemMapper);
    }

    @Bean
    public TransactionFeignPort transactionFeignPort() {
        return transactionFeign;
    }

    @Bean
    public SaleServicePort saleServicePort() {
        return new SaleUseCase(saleRepositoryPort(), userExtractor(), stockFeignPort(), cartFeignPort(), transactionFeignPort());
    }

    @Bean
    public SaleService saleService() {
        return new SaleService(saleServicePort(), saleHandler);
    }
}

