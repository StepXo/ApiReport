package com.emazon.ApiReport.Application.Handler;

import com.emazon.ApiReport.Application.Response.SaleResponse;
import com.emazon.ApiReport.Application.Utils.AppConstants;
import com.emazon.ApiReport.Domain.Model.Sale;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = AppConstants.SPRING)
public interface SaleHandler {
    @Mapping(target = AppConstants.ITEMS, ignore = true)
    SaleResponse toResponse(Sale sale);
}
