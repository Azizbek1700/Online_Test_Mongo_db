package uz.jl.exception;

import uz.jl.enums.HttpStatus;

/**
 * @author Doston Bokhodirov, Tue 9:26 PM. 1/25/2022
 */
public class InvalidInputException extends BaseException {
    private final HttpStatus status;

    public InvalidInputException(String message, HttpStatus status) {
        super(message, status);
        this.status = HttpStatus.HTTP_400;
    }
}
