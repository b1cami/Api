package dgsw.b1cami.cocode.Controller;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Service.PostService;
import dgsw.b1cami.cocode.json.PostResponse;
import dgsw.b1cami.cocode.json.PostsResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Integer postId) {
        System.out.println("post getPost - " + postId);
        return new ResponseEntity<>(postService.getPost(postId), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<Response> uploadPost(@RequestBody Post post, @RequestHeader(name = "Token") String key) {
        System.out.println("post uploadPost - " + post.getTitle());
        return new ResponseEntity<>(postService.uploadPost(post, key), HttpStatus.OK);
    }

    @GetMapping("/getPosts/{getCount}")
    public ResponseEntity<PostsResponse> getPosts(@PathVariable Integer getCount) {
        System.out.println("post getPosts - " + getCount);
        return new ResponseEntity<>(postService.getPosts(getCount), HttpStatus.OK);
    }

}
