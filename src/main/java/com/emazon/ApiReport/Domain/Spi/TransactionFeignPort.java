package com.emazon.ApiReport.Domain.Spi;

import java.util.List;

public interface TransactionFeignPort {
    String getErrorMessage(List<Long> itemsId);
}
