package at.fhtw.swen3.services.exception;

public class BLValidationException extends BLException {

    public BLValidationException(Exception innerException, String errorMessage) {
        super(innerException, errorMessage);
    }
}
