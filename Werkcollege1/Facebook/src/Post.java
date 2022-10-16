import java.text.DateFormat;
import java.util.Date;

public class Post implements Comparable<Post> {

    private static int counter = 0;

    private String text;
    private Date date;
    private final Account creator;
    private final Integer postID;

    public Post(Account creator, String text) {
        this.text = text;
        date = new Date();
        this.creator = creator;
        counter++;
        this.postID = counter;
    }

    @Override
    public String toString() {
        DateFormat df = DateFormat.getInstance();
        String result = getPostID() + ";"+ creator +
                ";" + text + ";" + df.format(date) + ";";
        return result;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the creator
     */
    public Account getCreator() {
        return creator;
    }

    /**
     * @return the id
     */
    public Integer getPostID() {
        return postID;
    }

    @Override
    public int compareTo(Post other) {
        if(date.before(other.getDate()))
            return 1;
        else if(date.before(other.getDate()))
            return -1;
        if(this.getPostID() < other.getPostID())
            return 1;
        else if(this.getPostID() > other.getPostID())
            return -1;
        return 0;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Post other = (Post) obj;
        return postID == other.postID;
    }
}