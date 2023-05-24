package by.chat.core.exception;

import by.chat.core.enums.UserException;

import java.util.List;
import java.util.Map;

public class RegistrationException extends RuntimeException{
    private final Map<UserException, String> invalidFields;

    public RegistrationException(Map<UserException, String> invalidFields) {
        this.invalidFields = invalidFields;
    }

    public Map<UserException, String> getInvalidFields() {
        return invalidFields;
    }
}
