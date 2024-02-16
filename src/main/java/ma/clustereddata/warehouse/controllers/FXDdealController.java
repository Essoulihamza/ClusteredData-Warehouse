package ma.clustereddata.warehouse.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.clustereddata.warehouse.model.dtos.FXDealDto;
import ma.clustereddata.warehouse.services.FXDealService;

@AllArgsConstructor

@RestController
@RequestMapping(path = "/api/FXdeals")
public class FXDdealController {

    private final FXDealService fxDealService;
    
    @PostMapping
    public ResponseEntity<FXDealDto> create( @Valid @RequestBody FXDealDto newFXDealDto) {
        return new ResponseEntity<>(
            fxDealService.create(newFXDealDto),
            HttpStatus.CREATED  
        );
    }
}
