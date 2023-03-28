package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.ErrorEntity;
import at.fhtw.swen3.services.dto.Error;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorMapperTest {
    @Test
    @DisplayName("Error message in ErrorEntity equals error message in mapped ErrorDto.")
    public void entityErrorEqualsDtoError() {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorMessage("This is a test error message.");

        Error error = ErrorMapper.INSTANCE.entityToDto(errorEntity);

        assertEquals(errorEntity.getErrorMessage(), error.getErrorMessage());
    }

    @Test
    @DisplayName("Error message in ErrorDto equals error message in mapped ErrorEntity.")
    public void dtoErrorEqualsEntityError() {
        Error error = new Error();
        error.setErrorMessage("This is a test error message.");

        ErrorEntity errorEntity = ErrorMapper.INSTANCE.dtoToEntity(error);

        assertEquals(error.getErrorMessage(), errorEntity.getErrorMessage());
    }
}