package com.emazon.ApiReport.Infrastructure.Input;

import com.emazon.ApiReport.Application.Response.SaleResponse;
import com.emazon.ApiReport.Application.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.emazon.ApiReport.Infrastructure.Utils.InfraConstants.BUY;

@RestController
@RequestMapping(BUY)
@RequiredArgsConstructor
public class SaleController {
    private final SaleService saleService;
    @PostMapping
    private ResponseEntity<SaleResponse> sale(){
        return ResponseEntity.ok(saleService.sale());

    }
}
