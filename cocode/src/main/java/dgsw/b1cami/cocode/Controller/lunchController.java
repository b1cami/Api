package dgsw.b1cami.cocode.Controller;

import dgsw.b1cami.cocode.Domain.Lunch;
import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Service.LunchService;
import dgsw.b1cami.cocode.Service.PostService;
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
    public ResponseEntity<LunchResponse> getPost(@PathVariable Integer lunchId) {
        System.out.println("lunch getLunch - " + lunchId);
        return new ResponseEntity<>(lunchService.getLunch(lunchId), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadLunch(@RequestBody Lunch lunch, @RequestHeader(name = "Token") String key) {
        System.out.println("lunch uploadLunch - " + lunch.getFoodName());
        return new ResponseEntity<>(lunchService.uploadLunch(lunch, key), HttpStatus.OK);
    }

    @GetMapping("/getLunches/{getCount}")
    public ResponseEntity<LunchesResponse> getPosts(@PathVariable Integer getCount) {
        System.out.println("lunch getLunches - " + getCount);
        return new ResponseEntity<>(lunchService.getLunches(getCount), HttpStatus.OK);
    }

}
