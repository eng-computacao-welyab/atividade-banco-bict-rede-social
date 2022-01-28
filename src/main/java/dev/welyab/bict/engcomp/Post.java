package dev.welyab.bict.engcomp;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private String id;
    private String content;

    private List<UserComment> comments;

    public Post(String id, String content) {
        this.id = id;
        this.content = content;
        comments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addComment(String idCommentee, Comment comment) {
        comments.add(new UserComment(idCommentee, comment));
    }

    public List<UserComment> getComments() {
        return comments;
    }
}
