package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.PostOutput;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.PostRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.PostResponse;
import dgsw.b1cami.cocode.json.PostsResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Response uploadPost(Post post) {
        try {
            Long uploader = post.getUploader();
            String title = post.getTitle();
            String content = post.getContent();

            if(uploader == null)
                throw new UserException(400, "Requires Uploader");
            if(title == null)
                throw new UserException(400, "Requires Title");
            if(content == null)
                throw new UserException(400, "Requires Content");

            userRepository.findByUserId(uploader).orElseThrow(
                    () -> new UserException(400, "Undefined Uploader")
            );

            if(title.length() > 100)
                throw new UserException(400, "Title Must Be Shorter Than 100");
            if(content.length() > 10000)
                throw new UserException(400, "Content Must Be Shorter Than 10000");

            postRepository.save(post);
            return new Response(200, "Success Upload Post");
        } catch(UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public PostResponse getPost(Integer postId) {
        try {
            Post post = postRepository.findByPostId(postId).orElseThrow(
                    () -> new UserException(400, "Undefined Post")
            );

            return new PostResponse(200, "Success GetPost", post);
        } catch(UserException e) {
            return new PostResponse(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new PostResponse(500, e.getMessage());
        }
    }

    @Override
    public PostsResponse getPosts(Integer getCount) {
        try {
            if(getCount < 0)
                throw new UserException(400, "GetCount Must Ge Bigger Than 0");

            Integer postCount = (int) postRepository.count();

            getCount = postCount - (getCount * 20);
            if(getCount <= 0)
                throw new UserException(400, "All Posts Already Returned");

            ArrayList<Post> posts = postRepository.findPosts(getCount);
            ArrayList<PostOutput> postOutputs = new ArrayList<>();

            for(Post post : posts) {
                post.setContent(null);
                postOutputs.add(new PostOutput(post));
            }

            return new PostsResponse(200, "Success getPosts", postOutputs);
        } catch(UserException e) {
            return new PostsResponse(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new PostsResponse(500, e.getMessage());
        }
    }

}