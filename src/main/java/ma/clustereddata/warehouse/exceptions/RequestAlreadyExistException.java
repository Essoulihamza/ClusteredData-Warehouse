package ma.clustereddata.warehouse.exceptions;

public class RequestAlreadyExistException extends RuntimeException {
    
    public RequestAlreadyExistException(String message) {
        super(message);
    }
}
