package uz.jl.exception;

import lombok.Getter;
import lombok.Setter;
import uz.jl.enums.HttpStatus;

/**
 * @author Doston Bokhodirov, Tue 9:17 PM. 1/25/2022
 */
@Getter
@Setter
public abstract class BaseException extends RuntimeException {
    private HttpStatus status;

    public BaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public BaseException(String message, Throwable cause, HttpStatus status) {
        super(message, cause);
        this.status = status;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
