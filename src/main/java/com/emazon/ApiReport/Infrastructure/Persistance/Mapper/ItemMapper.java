package com.emazon.ApiReport.Infrastructure.Persistance.Mapper;

import com.emazon.ApiReport.Application.Request.ItemAuxDto;
import com.emazon.ApiReport.Domain.Model.Item;
import org.mapstruct.Mapper;

import static com.emazon.ApiReport.Infrastructure.Utils.InfraConstants.SPRING;

@Mapper(componentModel = SPRING)
public interface ItemMapper {

    Item toItem(ItemAuxDto request);

    ItemAuxDto toItemAuxDto(Item itemsId);
}
