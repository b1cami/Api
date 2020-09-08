package dgsw.b1cami.cocode.json;

import dgsw.b1cami.cocode.Domain.LunchOutput;

import java.util.ArrayList;

public class LunchesResponse extends Response {

    ArrayList<LunchOutput> lunches;

    public LunchesResponse(int status, String message) {
        super(status, message);
    }

    public LunchesResponse(int status, String message, ArrayList<LunchOutput> lunches) {
        super(status, message);
        this.lunches = lunches;
    }

    public ArrayList<LunchOutput> getLunches() {
        return lunches;
    }

}
