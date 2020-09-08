package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.Domain.LunchOutput;

public class LunchResponse extends Response {

    LunchOutput lunch;

    public LunchResponse(int status, String message) {
        super(status, message);
    }

    public LunchResponse(int status, String message, Lunch lunch) {
        super(status, message);
        this.lunch = new LunchOutput(lunch);
    }

    public LunchOutput getLunch() {
        return lunch;
    }

}
