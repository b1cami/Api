package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.json.LunchResponse;
import dgsw.b1cami.cocode.json.LunchesResponse;
import dgsw.b1cami.cocode.json.Response;
import dgsw.b1cami.cocode.json.SchoolLunchResponse;

public interface LunchService {

    Response uploadLunch(Lunch lunch, String key);

    LunchResponse getLunch(Integer lunchId);

    LunchesResponse getLunches(Integer getCount);

    SchoolLunchResponse getSchoolLunch();

    Response deleteLunch(Integer lunchId);

}