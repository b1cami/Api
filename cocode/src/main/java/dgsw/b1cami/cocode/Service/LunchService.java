package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.json.LunchResponse;
import dgsw.b1cami.cocode.json.LunchesResponse;
import dgsw.b1cami.cocode.json.Response;

public interface LunchService {

    Response uploadLunch(Lunch lunch, String key);

    LunchResponse getLunch(Integer lunchId);

    LunchesResponse getLunches(Integer getCount);

}