package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.Domain.LunchComment;
import dgsw.b1cami.cocode.json.*;

public interface LunchService {

    Response uploadLunch(Lunch lunch, String key);

    LunchResponse getLunch(Long lunchId);

    LunchesResponse getLunches(Long getCount);

    SchoolLunchResponse getSchoolLunch();

    Response deleteLunch(Long lunchId);

    Response addComment(LunchComment lunchComment, String key);

    CommentResponse getComments(Lunch lunch);

    Response deleteComment(Long commentId);

}