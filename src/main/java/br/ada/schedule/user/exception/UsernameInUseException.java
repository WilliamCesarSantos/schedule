package br.ada.schedule.user.exception;

import br.ada.schedule.common.I18nKey;

@I18nKey("user.exception.username-in-use")
public class UsernameInUseException extends UserException {

    public UsernameInUseException() {
        super("Username is already in use");
    }

}
