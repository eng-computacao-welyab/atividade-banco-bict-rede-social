package dev.welyab.bict.engcomp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class User {

    private String id;
    private String nome;

    private List<PostInfo> posts;

    public User(String id, String nome) {
        this.id = id;
        this.nome = nome;
        posts = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addPost(Post post) {
        posts.add(new PostInfo(post));
    }

    private PostInfo getPostInfo(String idPost) {
        return posts.stream().filter(p -> p.post.getId().equals(idPost)).findFirst().get();
    }

    public void addComment(String idCommentee, String idPost, Comment comment) {
        getPostInfo(idPost).post.addComment(idCommentee, comment);
    }

    public void addLike(String idLiker, String idPost) {
        getPostInfo(idPost).likes.add(idLiker);
    }

    public List<Post> getPosts() {
        return posts.stream().map(p -> p.post).collect(Collectors.toList());
    }

    public List<String> getLikes(String idPost) {
        return posts.stream()
                .filter(p -> p.post.getId().equals(idPost))
                .flatMap(p -> p.likes.stream())
                .collect(Collectors.toList());
    }

    private static class PostInfo {
        private Post post;
        private List<String> likes;

        PostInfo(Post post) {
            this.post = post;
            likes = new ArrayList<>();
        }
    }
}
