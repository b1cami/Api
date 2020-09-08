package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.*;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.LunchRepository;
import dgsw.b1cami.cocode.Repository.TokenRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LunchServiceImpl implements LunchService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    LunchRepository lunchRepository;

    @Override
    public Response uploadLunch(Lunch lunch, String key) {
        try {
            if(key == null)
                throw new UserException(400, "Requires Key");

            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User user = userRepository.findByUserId(token.getOwnerId()).orElse(
                    new User("If This Goes Out, It'll Be Fucking Serious Error")
            );

            String uploader = user.getEmail();
            String foodName = lunch.getFoodName();
            String description = lunch.getDescription();

            if(uploader == null)
                throw new UserException(400, "Requires Uploader");
            if(foodName == null)
                throw new UserException(400, "Requires FoodName");
            if(description == null)
                throw new UserException(400, "Requires Description");

            userRepository.findByUserEmail(uploader).orElseThrow(
                    () -> new UserException(400, "Undefined Uploader")
            );

            if(foodName.length() > 50)
                throw new UserException(400, "FoodName Must Be Shorter Than 50");
            if(description.length() > 100)
                throw new UserException(400, "Description Must Be Shorter Than 100");

            lunch.setUploader(uploader);
            lunchRepository.save(lunch);
            return new Response(200, "Success Upload Lunch");
        } catch(UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public LunchResponse getLunch(Integer lunchId) {
        try {
            Lunch lunch = lunchRepository.findByLunchId(lunchId).orElseThrow(
                    () -> new UserException(400, "Undefined Lunch")
            );

            return new LunchResponse(200, "Success GetLunch", lunch);
        } catch(UserException e) {
            return new LunchResponse(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new LunchResponse(500, e.getMessage());
        }
    }

    @Override
    public LunchesResponse getLunches(Integer getCount) {
        try {
            if(getCount < 0)
                throw new UserException(400, "GetCount Must Ge Bigger Than 0");

            int lunchCount = (int) lunchRepository.count();

            getCount = lunchCount - (getCount * 20);
            if(getCount <= 0)
                throw new UserException(400, "All Lunches Already Returned");

            ArrayList<Lunch> posts = lunchRepository.findLunches(getCount);
            ArrayList<LunchOutput> lunchesOutput = new ArrayList<>();

            for(Lunch lunch : posts)
                lunchesOutput.add(new LunchOutput(lunch));

            return new LunchesResponse(200, "Success getPosts", lunchesOutput);
        } catch(UserException e) {
            return new LunchesResponse(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new LunchesResponse(500, e.getMessage());
        }
    }

}