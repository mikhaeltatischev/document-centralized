package document.centralized.main.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TinNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Tin not found.";

    public TinNotFoundException() {
        super(MESSAGE);
        log.debug(MESSAGE);
    }

}