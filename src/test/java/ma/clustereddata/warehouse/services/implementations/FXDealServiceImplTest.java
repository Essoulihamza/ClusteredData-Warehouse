package ma.clustereddata.warehouse.services.implementations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import ma.clustereddata.warehouse.exceptions.RequestAlreadyExistException;
import ma.clustereddata.warehouse.model.dtos.FXDealDto;
import ma.clustereddata.warehouse.model.entities.FXDeal;
import ma.clustereddata.warehouse.repository.FXDealRepository;
import ma.clustereddata.warehouse.services.implementation.FXDealServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@ExtendWith(MockitoExtension.class)
public class FXDealServiceImplTest {

    @Mock
    private FXDealRepository fxDealRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FXDealServiceImpl underTest;

    private static FXDealDto fxDealDto;
    private static FXDeal fxDealEntity;

    @BeforeAll
    static void setUp() {
        fxDealDto = new FXDealDto();
        fxDealEntity = new FXDeal();
    }

    @Test
    @DisplayName("[Create method] Throws already exist Exception.")
    void createMethodShouldThrowRequestAlreadyExistException() {
        
        fxDealDto.setId("test");

        when(fxDealRepository.existsById(fxDealDto.getId())).thenReturn(true);

        assertThatExceptionOfType(RequestAlreadyExistException.class)
        .isThrownBy(() -> underTest.create(fxDealDto))
        .withMessage("Request is already imported.");
        
        verify(fxDealRepository, times(1)).existsById(fxDealDto.getId());
    }

    @Test
    @DisplayName("[Create method] Returns the inserted entity as FXDealdto.")
    void createMethodShouldReturnTheInsertedDealDto() {
        
        fxDealDto.setId("test");

        when(fxDealRepository.existsById(fxDealDto.getId())).thenReturn(false);
        when(modelMapper.map(fxDealDto, FXDeal.class)).thenReturn(fxDealEntity);
        when(fxDealRepository.save(fxDealEntity)).thenReturn(fxDealEntity);
        when(modelMapper.map(fxDealEntity, FXDealDto.class)).thenReturn(fxDealDto);

        assertThat(underTest.create(fxDealDto)).isEqualTo(fxDealDto);

        verify(fxDealRepository, times(1)).existsById(fxDealDto.getId());
        verify(modelMapper, times(1)).map(fxDealDto, FXDeal.class);
        verify(fxDealRepository, times(1)).save(fxDealEntity);
        verify(modelMapper, times(1)).map(fxDealEntity, FXDealDto.class);
    }
}
