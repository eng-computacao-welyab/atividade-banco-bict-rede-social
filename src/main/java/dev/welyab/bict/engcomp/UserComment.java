package dev.welyab.bict.engcomp;

class UserComment {

    private String idCommentee;
    private Comment comment;

    public UserComment(String idCommentee, Comment comment) {
        this.idCommentee = idCommentee;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getIdCommentee() {
        return idCommentee;
    }

    public void setIdCommentee(String idCommentee) {
        this.idCommentee = idCommentee;
    }
}