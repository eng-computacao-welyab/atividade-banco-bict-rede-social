package dev.welyab.bict.engcomp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SocialNetwork {

    private Map<String, User> users;
    private Map<String, Set<String>> friendships;

    public SocialNetwork() {
        users = new HashMap<>();
        friendships = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public void addFriendly(String id1, String id2) {
        getUserFriends(id1).add(id2);
        getUserFriends(id2).add(id1);
    }

    private Set<String> getUserFriends(String id) {
        return friendships.computeIfAbsent(id, key -> new HashSet<>());
    }

    public void addPost(String idUsuario, Post post) {
        users.get(idUsuario).addPost(post);
    }

    public void addComment(String idCommentee, String idPostOwner, String idPost, Comment comment) {
        users.get(idPostOwner).addComment(idCommentee, idPost, comment);
    }

    public void liked(String idLiker, String idPostOwner, String idPost) {
        users.get(idPostOwner).addLike(idLiker, idPost);
    }

    public void printUpdates() {
        for (User user : users.values()) {
            System.out.println("------------------------------------------------------------------");
            System.out.printf("Usuário: %s - %s%n", user.getId(), user.getNome());

            System.out.println("  Amigos:");
            for (String idFriend : getUserFriends(user.getId())) {
                User friend = users.get(idFriend);
                System.out.printf("    %s - %s%n", friend.getId(), friend.getNome());
            }

            System.out.println("  Posts:");
            List<Post> posts = user.getPosts();
            if (!posts.isEmpty()) {
                for (Post post : user.getPosts()) {
                    System.out.printf("    >>> %s - %s%n", post.getId(), post.getContent());
                    List<String> likes = user.getLikes(post.getId());
                    System.out.println("    Curtidas:");
                    if (!likes.isEmpty()) {
                        for(String idLiker : likes) {
                            System.out.printf("        %s - %s%n", idLiker, users.get(idLiker).getNome());
                        }
                    } else {
                        System.out.println("      <sem curtidas>");
                    }

                    List<UserComment> comments = post.getComments();
                    System.out.println("    Comentários:");
                    if (!comments.isEmpty()) {
                        for (UserComment comment : comments) {
                            User commentee = users.get(comment.getIdCommentee());
                            System.out.printf(
                                    "        O usuário %s - %s comentou: \"%s\"",
                                    commentee.getId(),
                                    commentee.getNome(),
                                    comment.getComment().getContent()
                            );
                        }
                    } else {
                        System.out.printf("      <sem comentários>");
                    }
                }
            } else {
                System.out.println("    <sem postagens>");
            }

            System.out.println();
        }
    }
}
