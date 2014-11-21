package youp.ingesup.com.youp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vincent del Valle on 20/11/2014.
 */
public class Message {


    @SerializedName("Message_id")
    private int id;

    @SerializedName("Topic_id")
    private int topicId;

    @SerializedName("Utilisateur_id")
    private int userId;

    @SerializedName("DatePoste")
    private String datePost;

    @SerializedName("ContenuMessage")
    private String content;

    @SerializedName("Report")
    private boolean isReported;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isReported() {
        return isReported;
    }

    public void setReported(boolean isReported) {
        this.isReported = isReported;
    }
}
