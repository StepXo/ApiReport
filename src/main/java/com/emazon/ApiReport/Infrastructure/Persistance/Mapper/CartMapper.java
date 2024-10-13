package com.emazon.ApiReport.Infrastructure.Persistance.Mapper;

import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Application.Utils.AppConstants;
import com.emazon.ApiReport.Domain.Model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = AppConstants.SPRING)
public interface CartMapper {
    @Mapping(target = "item", ignore = true)
    Cart tocart(CartResponse sale);
}
