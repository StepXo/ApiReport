package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Application.Response.ItemAuxDto;
import com.emazon.ApiReport.Application.Response.UserResponse;
import com.emazon.ApiReport.Domain.Model.Cart;
import com.emazon.ApiReport.Domain.Spi.CartFeignPort;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ApiCart", url = "http://localhost:9093/cart",configuration = FeignConfiguration.class)
public interface ICartFeign {

    @GetMapping(InfraConstants.ORDER)
    ResponseEntity<CartResponse> getCart(
            @PathVariable String order,
            @RequestParam(defaultValue = InfraConstants.ZERO) int page,
            @RequestParam(defaultValue = InfraConstants.TEN) int size);

    @GetMapping(value = InfraConstants.BUY,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<CartResponse> buy();
}
