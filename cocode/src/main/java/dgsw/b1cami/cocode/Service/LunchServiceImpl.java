package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.*;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.LunchCommentRepository;
import dgsw.b1cami.cocode.Repository.LunchRepository;
import dgsw.b1cami.cocode.Repository.TokenRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

@Service
public class LunchServiceImpl implements LunchService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    LunchRepository lunchRepository;

    @Autowired
    LunchCommentRepository lunchCommentRepository;

    @Override
    public Response uploadLunch(Lunch lunch, String key) {
        try {
            if(key == null)
                throw new UserException(400, "Requires Key");

            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User user = userRepository.findByUserId(token.getOwnerId()).orElseThrow(
                    () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
            );

            String uploader = user.getName();
            String foodName = lunch.getFoodName();
            String description = lunch.getDescription();

            if(uploader == null)
                throw new UserException(400, "Requires Uploader");
            if(foodName == null)
                throw new UserException(400, "Requires FoodName");
            if(description == null)
                throw new UserException(400, "Requires Description");

            userRepository.findByUserName(uploader).orElseThrow(
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
    public LunchResponse getLunch(Long lunchId) {
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
    public LunchesResponse getLunches(Long getCount) {
        try {
            if(getCount < 0)
                throw new UserException(400, "GetCount Must Ge Bigger Than 0");

            Long minCount = lunchRepository.findAll().get(0).getId();
            long postCount = lunchRepository.count() + minCount;

            getCount = postCount - (getCount * 20);
            if(getCount <= minCount)
                throw new UserException(400, "All Posts Already Returned");

            ArrayList<Lunch> lunches = lunchRepository.findLunches(getCount);
            ArrayList<LunchOutput> lunchOutputs = new ArrayList<>();

            for(Lunch lunch : lunches)
                lunchOutputs.add(new LunchOutput(lunch));

            return new LunchesResponse(200, "Success getPosts", lunchOutputs);
        } catch(UserException e) {
            return new LunchesResponse(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new LunchesResponse(500, e.getMessage());
        }
    }

    @Override
    public SchoolLunchResponse getSchoolLunch() {
        try {
            Calendar calendar = Calendar.getInstance();

            //Yellow Line..... Why Not StringBuilder?
            StringBuilder url = new StringBuilder(
                    "https://stu.dge.go.kr/sts_sci_md00_001.do?schulCode=D100000282&schulCrseScCode=4&schulKndScCode=04&schYm="
            );
            url.append(calendar.get(Calendar.YEAR));
            url.append(String.format("%02d", calendar.get(Calendar.MONTH) + 1));
            url.append("&");

            Document doc = Jsoup.connect(url.toString()).get();
            Element ele = doc.select("tbody").select("td").get(calendar.get(Calendar.DATE) + 1).selectFirst("div");
            String[] split = ele.text().split(" ");

            ArrayList<ArrayList<String>> menu = new ArrayList<>();

            for(int i = 0; i < 3; i++)
                menu.add(new ArrayList<>());
            int index = 0;

            for(String s : split) {
                s = s.replaceAll("[0-9.]", "");

                if(s.equals(""))
                    continue;

                switch (s) {
                    case "[조식]":
                        index = 0;
                        continue;
                    case "[중식]":
                        index = 1;
                        continue;
                    case "[석식]":
                        index = 2;
                        continue;
                }

                menu.get(index).add(s);
            }

            return new SchoolLunchResponse(200, "Success GetSchoolLunch", menu);
        } catch (Exception e) {
            e.printStackTrace();
            return new SchoolLunchResponse(500, e.getMessage());
        }
    }

    @Override
    public Response deleteLunch(Long lunchId) {
        try {
            Lunch lunch = lunchRepository.findByLunchId(lunchId).orElseThrow(
                    () -> new UserException(400, "Undefined LunchId")
            );

            lunchRepository.delete(lunch);
            return new Response(200, "Success deletePost");
        } catch(UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public Response addComment(LunchComment lunchComment, String key) {
        try {
            if(key == null)
                throw new UserException(400, "Requires Key");

            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User user = userRepository.findByUserId(token.getOwnerId()).orElseThrow(
                    () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
            );

            String comment = lunchComment.getComment();
            Long lunchId = lunchComment.getLunchId();

            if(comment == null)
                throw new UserException(400, "Requires Comment");
            if(lunchId == null)
                throw new UserException(400, "Requires LunchId");

            lunchRepository.findByLunchId(lunchId).orElseThrow(
                    () -> new UserException(400, "Undefined LunchId")
            );

            if(comment.length() > 255)
                throw new UserException(400, "Comment Must Be Shorter Than 255");

            lunchComment.setUserId(user.getId());
            lunchCommentRepository.save(lunchComment);
            return new Response(200, "Success addComment");
        } catch (UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public CommentResponse getComments(Lunch lunch) {
        try {
            Long lunchId = lunch.getId();

            if (lunchId == null)
                throw new UserException(400, "Requires LunchId");

            ArrayList<LunchComment> lunchComments = lunchCommentRepository.findByLunchId(lunchId);
            HashMap<String, ArrayList<String>> comments = new HashMap<>();

            User user;
            String name;
            for(LunchComment lunchComment : lunchComments) {
                user = userRepository.findByUserId(lunchComment.getUserId()).orElseThrow(
                        () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
                );
                name = user.getName();

                if(!comments.containsKey(name))
                    comments.put(name, new ArrayList<>());

                comments.get(name).add(lunchComment.getComment());
            }

            return new CommentResponse(200, "Success getComments", comments);
        } catch (UserException e) {
            return new CommentResponse(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new CommentResponse(500, e.getMessage());
        }
    }

}