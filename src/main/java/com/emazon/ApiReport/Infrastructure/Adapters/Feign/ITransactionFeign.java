package com.emazon.ApiReport.Infrastructure.Adapters.Feign;

import com.emazon.ApiReport.Domain.Spi.TransactionFeignPort;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = InfraConstants.TRANSACTION_API, url = "${transaction.api.url}",configuration = FeignConfiguration.class)
public interface ITransactionFeign extends TransactionFeignPort {

    default String getErrorMessage(List<Long> itemsId){
        return checkDates(itemsId).getBody();
    }

    @GetMapping(value = InfraConstants.GET_SUPPLY,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<String> checkDates(
            @RequestParam List<Long> id);

}
