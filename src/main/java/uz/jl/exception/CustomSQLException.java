package uz.jl.exception;

import lombok.Getter;
import lombok.Setter;
import uz.jl.enums.HttpStatus;

/**
 * @author Doston Bokhodirov, Tue 9:20 PM. 1/25/2022
 */
@Getter
@Setter
public class CustomSQLException extends BaseException {

    private String friendlyMessage;

    public CustomSQLException(String message, HttpStatus status, String friendlyMessage) {
        super(message, status);
        this.friendlyMessage = friendlyMessage;
        initMessage();
    }

    public CustomSQLException(String message, Throwable cause) {
        super(message, cause);
    }

    private void initMessage() {
        if (friendlyMessage.contains("ERROR_CODE_INTERNAL"))
            super.setStatus(HttpStatus.HTTP_500);
        else if (friendlyMessage.contains("ERROR_CODE_BAD_REQUEST"))
            super.setStatus(HttpStatus.HTTP_400);
        else
            super.setStatus(HttpStatus.UNDEFINED);
    }
}
