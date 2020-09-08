package dgsw.b1cami.cocode.Service;

import dgsw.b1cami.cocode.Domain.Post;
import dgsw.b1cami.cocode.json.PostResponse;
import dgsw.b1cami.cocode.json.PostsResponse;
import dgsw.b1cami.cocode.json.Response;

public interface PostService {

    Response uploadPost(Post post, String key);

    PostResponse getPost(Integer postId);

    PostsResponse getPosts(Integer getCount);

}