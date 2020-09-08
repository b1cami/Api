package dgsw.b1cami.cocode.json;

import java.util.ArrayList;

public class SchoolLunchResponse extends Response {

    ArrayList<String> breakfast;
    ArrayList<String> lunch;
    ArrayList<String> dinner;

    public SchoolLunchResponse(int status, String message) {
        super(status, message);
    }

    public SchoolLunchResponse(int status, String message, ArrayList<ArrayList<String>> menu) {
        super(status, message);
        this.breakfast = menu.get(0);
        this.lunch = menu.get(1);
        this.dinner = menu.get(2);
    }

    public ArrayList<String> getBreakfast() {
        return breakfast;
    }

    public ArrayList<String> getLunch() {
        return lunch;
    }

    public ArrayList<String> getDinner() {
        return dinner;
    }

}
