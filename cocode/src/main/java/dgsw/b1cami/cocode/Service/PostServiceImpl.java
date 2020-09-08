package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.*;
import dgsw.b1cami.cocode.Exception.UserException;
import dgsw.b1cami.cocode.Repository.PostCommentRepository;
import dgsw.b1cami.cocode.Repository.PostRepository;
import dgsw.b1cami.cocode.Repository.TokenRepository;
import dgsw.b1cami.cocode.Repository.UserRepository;
import dgsw.b1cami.cocode.json.CommentResponse;
import dgsw.b1cami.cocode.json.PostResponse;
import dgsw.b1cami.cocode.json.PostsResponse;
import dgsw.b1cami.cocode.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostCommentRepository postCommentRepository;

    @Override
    public Response uploadPost(Post post, String key) {
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
    public PostResponse getPost(Long postId) {
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
    public PostsResponse getPosts(Long getCount) {
        try {
            if(getCount < 0)
                throw new UserException(400, "GetCount Must Ge Bigger Than 0");

            Long minCount = postRepository.findAll().get(0).getId();
            long postCount = postRepository.count() + minCount;

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
    public Response deletePost(Long postId) {
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

    @Override
    public Response addComment(PostComment postComment, String key) {
        try {
            if(key == null)
                throw new UserException(400, "Requires Key");

            Token token = tokenRepository.findByTokenKey(key).orElseThrow(
                    () -> new UserException(400, "Undefined Token Key")
            );

            User user = userRepository.findByUserId(token.getOwnerId()).orElseThrow(
                    () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
            );

            String comment = postComment.getComment();
            Long postId = postComment.getPostId();

            if(comment == null)
                throw new UserException(400, "Requires Comment");
            if(postId == null)
                throw new UserException(400, "Requires PostId");

            postRepository.findByPostId(postId).orElseThrow(
                    () -> new UserException(400, "Undefined PostId")
            );

            if(comment.length() > 255)
                throw new UserException(400, "Comment Must Be Shorter Than 255");

            postComment.setUserId(user.getId());
            postCommentRepository.save(postComment);
            return new Response(200, "Success addComment");
        } catch (UserException e) {
            return new Response(e.getStatus(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(500, e.getMessage());
        }
    }

    @Override
    public CommentResponse getComments(Post post) {
        try {
            Long postId = post.getId();

            if (postId == null)
                throw new UserException(400, "Requires PostId");

            ArrayList<PostComment> postComments = postCommentRepository.findByPostId(postId);
            HashMap<String, ArrayList<String>> comments = new HashMap<>();

            User user;
            String name;
            for(PostComment postComment : postComments) {
                user = userRepository.findByUserId(postComment.getUserId()).orElseThrow(
                        () -> new UserException(400, "Unreachable Code - Just For Deleting Yellow Line")
                );
                name = user.getName();

                if(!comments.containsKey(name))
                    comments.put(name, new ArrayList<>());

                comments.get(name).add(postComment.getComment());
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