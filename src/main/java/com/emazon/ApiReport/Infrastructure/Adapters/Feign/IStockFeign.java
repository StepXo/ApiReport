package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = InfraConstants.STOCK_API, url = "${stock.api.url}",configuration = FeignConfiguration.class)
public interface IStockFeign {


    @PostMapping(value = InfraConstants.BUY,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Integer>> buy(@RequestBody List<ItemAuxDto> list);
}
