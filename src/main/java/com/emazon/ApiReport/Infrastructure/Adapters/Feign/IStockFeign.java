package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Application.Response.ItemAuxDto;
import com.emazon.ApiReport.Application.Response.UserResponse;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "Api-Stock", url = "http://localhost:9091/item",configuration = FeignConfiguration.class)
public interface IStockFeign {


    @PostMapping(value = InfraConstants.BUY,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<List<Integer>> buy(@RequestBody List<ItemAuxDto> list);
}
