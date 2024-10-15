package com.emazon.ApiReport.Application.Service;

import com.emazon.ApiReport.Application.Handler.SaleHandler;
import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Application.Response.ItemResponse;
import com.emazon.ApiReport.Application.Response.SaleResponse;
import com.emazon.ApiReport.Domain.Api.SaleServicePort;
import com.emazon.ApiReport.Domain.Model.Sale;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.ApiReport.Application.Utils.AppConstants.ZERO;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleService {
    private final SaleServicePort saleServicePort;
    private final SaleHandler saleHandler;

    public SaleResponse sale() {
        return saleHandler.toResponse(saleServicePort.sale());
    }

}
