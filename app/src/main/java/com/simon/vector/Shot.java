package com.simon.vector;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Shot implements Parcelable {
    private Number attachments_count;
    private String attachments_url;
    private Number buckets_count;
    private String buckets_url;
    private Number comments_count;
    private String comments_url;
    private String created_at;
    private String description;
    private Number height;
    private String html_url;
    private Number id;
    private Images images;
    private Number likes_count;
    private String likes_url;
    private String projects_url;
    private Number rebounds_count;
    private String rebounds_url;
    private List tags;
    private Team team;
    private String title;
    private String updated_at;
    private User user;
    private Number views_count;
    private Number width;

    public Number getAttachments_count(){
        return this.attachments_count;
    }
    public void setAttachments_count(Number attachments_count){
        this.attachments_count = attachments_count;
    }
    public String getAttachments_url(){
        return this.attachments_url;
    }
    public void setAttachments_url(String attachments_url){
        this.attachments_url = attachments_url;
    }
    public Number getBuckets_count(){
        return this.buckets_count;
    }
    public void setBuckets_count(Number buckets_count){
        this.buckets_count = buckets_count;
    }
    public String getBuckets_url(){
        return this.buckets_url;
    }
    public void setBuckets_url(String buckets_url){
        this.buckets_url = buckets_url;
    }
    public Number getComments_count(){
        return this.comments_count;
    }
    public void setComments_count(Number comments_count){
        this.comments_count = comments_count;
    }
    public String getComments_url(){
        return this.comments_url;
    }
    public void setComments_url(String comments_url){
        this.comments_url = comments_url;
    }
    public String getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Number getHeight(){
        return this.height;
    }
    public void setHeight(Number height){
        this.height = height;
    }
    public String getHtml_url(){
        return this.html_url;
    }
    public void setHtml_url(String html_url){
        this.html_url = html_url;
    }
    public Number getId(){
        return this.id;
    }
    public void setId(Number id){
        this.id = id;
    }
    public Images getImages(){
        return this.images;
    }
    public void setImages(Images images){
        this.images = images;
    }
    public Number getLikes_count(){
        return this.likes_count;
    }
    public void setLikes_count(Number likes_count){
        this.likes_count = likes_count;
    }
    public String getLikes_url(){
        return this.likes_url;
    }
    public void setLikes_url(String likes_url){
        this.likes_url = likes_url;
    }
    public String getProjects_url(){
        return this.projects_url;
    }
    public void setProjects_url(String projects_url){
        this.projects_url = projects_url;
    }
    public Number getRebounds_count(){
        return this.rebounds_count;
    }
    public void setRebounds_count(Number rebounds_count){
        this.rebounds_count = rebounds_count;
    }
    public String getRebounds_url(){
        return this.rebounds_url;
    }
    public void setRebounds_url(String rebounds_url){
        this.rebounds_url = rebounds_url;
    }
    public List getTags(){
        return this.tags;
    }
    public void setTags(List tags){
        this.tags = tags;
    }
    public Team getTeam(){
        return this.team;
    }
    public void setTeam(Team team){
        this.team = team;
    }
    public String getTitle(){
        return this.title;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public User getUser(){
        return this.user;
    }
    public void setUser(User user){
        this.user = user;
    }
    public Number getViews_count(){
        return this.views_count;
    }
    public void setViews_count(Number views_count){
        this.views_count = views_count;
    }
    public Number getWidth(){
        return this.width;
    }
    public void setWidth(Number width){
        this.width = width;
    }

    public Shot() {

    }

    protected Shot(Parcel in) {
        attachments_count = (Number) in.readValue(Number.class.getClassLoader());
        attachments_url = in.readString();
        buckets_count = (Number) in.readValue(Number.class.getClassLoader());
        buckets_url = in.readString();
        comments_count = (Number) in.readValue(Number.class.getClassLoader());
        comments_url = in.readString();
        created_at = in.readString();
        description = in.readString();
        height = (Number) in.readValue(Number.class.getClassLoader());
        html_url = in.readString();
        id = (Number) in.readValue(Number.class.getClassLoader());
        images = (Images) in.readValue(Images.class.getClassLoader());
        likes_count = (Number) in.readValue(Number.class.getClassLoader());
        likes_url = in.readString();
        projects_url = in.readString();
        rebounds_count = (Number) in.readValue(Number.class.getClassLoader());
        rebounds_url = in.readString();
        if (in.readByte() == 0x01) {
            tags = new ArrayList<>();
            in.readList(tags, Shot.class.getClassLoader());//TODO CHECK TO SEE IF WORKING
        } else {
            tags = null;
        }
        team = (Team) in.readValue(Team.class.getClassLoader());
        title = in.readString();
        updated_at = in.readString();
        user = (User) in.readValue(User.class.getClassLoader());
        views_count = (Number) in.readValue(Number.class.getClassLoader());
        width = (Number) in.readValue(Number.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(attachments_count);
        dest.writeString(attachments_url);
        dest.writeValue(buckets_count);
        dest.writeString(buckets_url);
        dest.writeValue(comments_count);
        dest.writeString(comments_url);
        dest.writeString(created_at);
        dest.writeString(description);
        dest.writeValue(height);
        dest.writeString(html_url);
        dest.writeValue(id);
        dest.writeValue(images);
        dest.writeValue(likes_count);
        dest.writeString(likes_url);
        dest.writeString(projects_url);
        dest.writeValue(rebounds_count);
        dest.writeString(rebounds_url);
        if (tags == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(tags);
        }
        dest.writeValue(team);
        dest.writeString(title);
        dest.writeString(updated_at);
        dest.writeValue(user);
        dest.writeValue(views_count);
        dest.writeValue(width);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Shot> CREATOR = new Parcelable.Creator<Shot>() {
        @Override
        public Shot createFromParcel(Parcel in) {
            return new Shot(in);
        }

        @Override
        public Shot[] newArray(int size) {
            return new Shot[size];
        }
    };
}