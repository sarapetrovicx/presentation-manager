package controller.actions.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class MyError {

    Error type;
    String message;

    public MyError(Error type, String message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
