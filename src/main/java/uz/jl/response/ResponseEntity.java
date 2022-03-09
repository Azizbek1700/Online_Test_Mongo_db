package uz.jl.response;

import lombok.Getter;
import lombok.Setter;
import uz.jl.enums.HttpStatus;

/**
 * @author Axmadjonov Eliboy, Tue 3:57 PM,1/25/2022
 */
@Getter
@Setter
public class ResponseEntity<D> {
    private D body;
    private Integer status;

    public ResponseEntity(D body) {
        this(body, HttpStatus.HTTP_200);
    }

    public ResponseEntity(D body, HttpStatus status) {
        this.body = body;
        this.status = status.getCode();
    }
}
