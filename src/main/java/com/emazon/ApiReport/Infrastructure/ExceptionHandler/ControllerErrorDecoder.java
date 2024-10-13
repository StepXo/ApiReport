package com.emazon.ApiReport.Infrastructure.ExceptionHandler;

import com.emazon.ApiReport.Domain.Exeptions.*;
import com.emazon.ApiReport.Infrastructure.Utils.InfraConstants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class ControllerErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            JsonNode errorBody = objectMapper.readTree(response.body().asInputStream());
            String errorMessage = errorBody.path(InfraConstants.MESSAGE).asText();
            if (errorMessage.contains(InfraConstants.ITEM_NOT_FOUND)) {
                return new ItemNotFoundException(InfraConstants.ITEM_NOT_FOUND);
            } else if (errorMessage.contains(InfraConstants.QUANTITY_IS_NOT_ENOUGH)) {
                return new QuantityIsNotEnough(errorMessage);
            } else if(errorMessage.contains(InfraConstants.INVALID_USER)) {
                return new InvalidUserException();
            } else if(errorMessage.contains(InfraConstants.CART_IS_NULL)){
                return new CartIsNullException();
            } else if(errorMessage.contains(InfraConstants.CART_IS_EMPTY)){
                return new EmptyCartException();
            }
            else{
                return new BadRequestException();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
