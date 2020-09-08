package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.PostOutput;
import dgsw.b1cami.cocode.Domain.Token;
import dgsw.b1cami.cocode.Domain.User;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.PostRepository;
import dgsw.b1cami.cocode.Repository.TokenRepository;
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
    TokenRepository tokenRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public Response uploadPost(Post post, String key) {
        try {
            if(key == null)
                throw new UserException(400, "Requires Key");

            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User user = userRepository.findByUserId(token.getOwnerId()).orElse(
                    new User("If This Goes Out, It'll Be Fucking Serious Error")
            );

            String uploader = user.getName();
            String title = post.getTitle();
            String content = post.getContent();

            if(uploader == null)
                throw new UserException(400, "Requires Uploader");
            if(title == null)
                throw new UserException(400, "Requires Title");
            if(content == null)
                throw new UserException(400, "Requires Content");

            userRepository.findByUserName(uploader).orElseThrow(
                    () -> new UserException(400, "Undefined Uploader")
            );

            if(title.length() > 100)
                throw new UserException(400, "Title Must Be Shorter Than 100");
            if(content.length() > 10000)
                throw new UserException(400, "Content Must Be Shorter Than 10000");

            post.setUploader(uploader);
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
                    () -> new UserException(400, "Undefined PostId")
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

            int minCount = postRepository.findAll().get(0).getId();
            int postCount = (int) postRepository.count() + minCount;

            getCount = postCount - (getCount * 20);
            if(getCount <= minCount)
                throw new UserException(400, "All Posts Already Returned");

            ArrayList<Post> posts = postRepository.findPosts(getCount);
            ArrayList<PostOutput> postOutputs = new ArrayList<>();

            int length;
            for(Post post : posts) {
                length = Math.min(post.getContent().length(), 100);
                post.setContent(post.getContent().substring(0, length));
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

    @Override
    public Response deletePost(Integer postId) {
        try {
            Post post = postRepository.findByPostId(postId).orElseThrow(
                    () -> new UserException(400, "Undefined PostId")
            );

            postRepository.delete(post);
            return new Response(200, "Success deletePost");
        } catch(UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch(Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

}