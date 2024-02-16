package ma.clustereddata.warehouse.services.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.clustereddata.warehouse.exceptions.RequestAlreadyExistException;
import ma.clustereddata.warehouse.model.dtos.FXDealDto;
import ma.clustereddata.warehouse.model.entities.FXDeal;
import ma.clustereddata.warehouse.repository.FXDealRepository;
import ma.clustereddata.warehouse.services.FXDealService;

@AllArgsConstructor

@Service
public class FXDealServiceImpl implements FXDealService {

    private final FXDealRepository fxDealRepository;
    private final ModelMapper modelMapper;
    
    @Override
    public FXDealDto create(final FXDealDto newFXDealDto) {
        if(fxDealRepository.existsById(newFXDealDto.getId()))
            throw new RequestAlreadyExistException("Request is already imported.");
        return modelMapper.map(
            fxDealRepository.save(
                modelMapper.map(newFXDealDto, FXDeal.class)
            ),
            FXDealDto.class
        );
    }
    
}
