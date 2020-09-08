package dgsw.b1cami.cocode.Exception;

public class UserException extends RuntimeException {

    int status;

    public UserException(int status, String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
