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

}
