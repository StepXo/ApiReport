package com.emazon.ApiReport.Infrastructure.Persistance.Mapper;

import com.emazon.ApiReport.Application.Response.CartResponse;
import com.emazon.ApiReport.Application.Utils.AppConstants;
import com.emazon.ApiReport.Domain.Model.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.emazon.ApiReport.Infrastructure.Utils.InfraConstants.ITEM;
import static com.emazon.ApiReport.Infrastructure.Utils.InfraConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface CartMapper {
    @Mapping(target = ITEM, ignore = true)
    Cart tocart(CartResponse sale);
}
