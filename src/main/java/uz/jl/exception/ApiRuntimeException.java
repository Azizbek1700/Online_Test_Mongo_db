package uz.jl.exception;

import lombok.Getter;
import uz.jl.enums.HttpStatus;

/**
 * @author Doston Bokhodirov, Tue 9:24 PM. 1/25/2022
 */
public class ApiRuntimeException extends RuntimeException {
    @Getter
    private final Integer status;

    public ApiRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status.getCode();
    }
}
