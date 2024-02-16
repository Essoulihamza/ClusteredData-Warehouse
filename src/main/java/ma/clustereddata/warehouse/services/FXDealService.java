package ma.clustereddata.warehouse.services;

import ma.clustereddata.warehouse.model.dtos.FXDealDto;

public interface FXDealService {
    
    FXDealDto create(final FXDealDto newFXDealDto);
}
