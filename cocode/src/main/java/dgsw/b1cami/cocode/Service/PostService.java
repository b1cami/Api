package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.Domain.PostComment;
import dgsw.b1cami.cocode.json.CommentResponse;
import dgsw.b1cami.cocode.json.PostResponse;
import dgsw.b1cami.cocode.json.PostsResponse;
import dgsw.b1cami.cocode.json.Response;

public interface PostService {

    Response uploadPost(Post post, String key);

    PostResponse getPost(Long postId);

    PostsResponse getPosts(Long getCount);

    Response deletePost(Long postId);

    Response addComment(PostComment postComment, String key);

    CommentResponse getComments(Long postId);

    Response deleteComment(Long commentId);

}