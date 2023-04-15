package br.ada.schedule.user.exception;

public abstract class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}
