package br.ada.schedule.user.exception;

import br.ada.schedule.common.I18nKey;

@I18nKey("user.exception.username-changed")
public class UsernameChangedException extends UserException {
    public UsernameChangedException() {
        super("Username must not be change");
    }
}
