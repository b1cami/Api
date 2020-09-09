package dgsw.b1cami.cocode.Controller;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.Domain.LunchComment;
import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Service.LunchService;
import dgsw.b1cami.cocode.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lunch")
public class lunchController {

    @Autowired
    LunchService lunchService;

    @GetMapping("/getLunch/{lunchId}")
    public ResponseEntity<LunchResponse> getPost(@PathVariable Long lunchId) {
        System.out.println("lunch getLunch - " + lunchId);
        return new ResponseEntity<>(lunchService.getLunch(lunchId), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadLunch(@RequestBody Lunch lunch, @RequestHeader(name = "Token") String key) {
        System.out.println("lunch uploadLunch - " + lunch.getFoodName());
        return new ResponseEntity<>(lunchService.uploadLunch(lunch, key), HttpStatus.OK);
    }

    @GetMapping("/getLunches/{getCount}")
    public ResponseEntity<LunchesResponse> getLunches(@PathVariable Long getCount) {
        System.out.println("lunch getLunches - " + getCount);
        return new ResponseEntity<>(lunchService.getLunches(getCount), HttpStatus.OK);
    }

    @GetMapping("/getSchoolLunch")
    public ResponseEntity<SchoolLunchResponse> getSchoolLunch() {
        System.out.println("lunch getSchoolLunch");
        return new ResponseEntity<>(lunchService.getSchoolLunch(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{lunchId}")
    public ResponseEntity<Response> deleteLunch(@PathVariable Long lunchId) {
        System.out.println("lunch deleteLunch - " + lunchId);
        return new ResponseEntity<>(lunchService.deleteLunch(lunchId), HttpStatus.OK);
    }

    @PostMapping("/comment")
    public ResponseEntity<Response> addComment(@RequestBody LunchComment lunchComment, @RequestHeader(name = "Token") String key) {
        System.out.println("lunch addComment - " + lunchComment.getComment());
        return new ResponseEntity<>(lunchService.addComment(lunchComment, key), HttpStatus.OK);
    }

    @GetMapping("/getComments/{lunchId}")
    public ResponseEntity<CommentResponse> getComments(@PathVariable Long lunchId) {
        System.out.println("lunch getComments - " + lunchId);
        return new ResponseEntity<>(lunchService.getComments(lunchId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<Response> deleteComment(@PathVariable Long commentId) {
        System.out.println("lunch deleteComment - " + commentId);
        return new ResponseEntity<>(lunchService.deleteComment(commentId), HttpStatus.OK);
    }

}
