import java.util.ArrayList;
import java.util.Collections;

public class Facebook {
    private ArrayList<Account> accounts;
    public Facebook(){
        accounts = new ArrayList<>();
    }

    // Accessor
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    // Methods
    public void addAccount(Account acc){
        if(accounts.contains(acc))
            System.exit(0);
        accounts.add(acc);
    }
    public void addFriendship(Account account1, Account account2){
        account1.getFriends().add(account2);
        account2.getFriends().add(account1);
    }
    public boolean removeFriendship(Account account1, String account2){
        for(Account friend : account1.getFriends())
            if(friend.getLogin().equals(account2)){
                account1.getFriends().remove(friend);
                friend.getFriends().remove(account1);
                return true;
            }
        return false;
    }
    public void addPost(Post post, Account accountCreator){
        if(post.getCreator().equals(accountCreator))
            accountCreator.getWall().add(post);
        for(Account friend : accountCreator.getFriends())
            if(post.getCreator().equals(friend))
                accountCreator.getWall().add(post);
        if(!accountCreator.getWall().contains(post))
            System.exit(0);
    }
    public Post search(Integer postID){
        for(Account acc : accounts)
            for(Post post : acc.getWall())
                if(post.getPostID().equals(postID))
                    return post;
        return null;
    }
    public void like(Account account1, Integer postID){
        boolean flag = false;
        for(Account account : accounts)
            for(Post post : account.getWall())
                if(post.getPostID().equals(postID)){
                    account1.getLikes().add(postID);
                    flag = true;
                }
        if(!flag)
            System.exit(0);
    }
    public void removeLike(Account account, Post post){
        account.getLikes().remove(post.getPostID());
    }
    public ArrayList<Post> getNewsFeed(String login){
        ArrayList<Post> walls = new ArrayList<>();
        for(Account acc : accounts)
            if(acc.getLogin().equals(login)){
                walls.addAll(acc.getWall());
                for(Account friend : acc.getFriends())
                    walls.addAll(friend.getWall());
                break;
            }
        Collections.sort(walls);
        return walls;
    }
}