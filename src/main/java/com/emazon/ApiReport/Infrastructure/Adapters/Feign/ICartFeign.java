package com.emazon.ApiReport.Infrastructure.Adapters.Feign;



import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Infrastructure.Configuration.FeignConfiguration;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = InfraConstants.CART_API, url = "http://localhost:9093/cart",configuration = FeignConfiguration.class)
public interface ICartFeign {

    @GetMapping(InfraConstants.ORDER)
    ResponseEntity<CartResponse> getCart(
            @PathVariable String order,
            @RequestParam(defaultValue = InfraConstants.ZERO) int page,
            @RequestParam(defaultValue = InfraConstants.TEN) int size);

    @PostMapping(value = InfraConstants.BUY,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<CartResponse> buy();
}
