package com.simon.vector;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String avatar_url;
    private String bio;
    private Number buckets_count;
    private String buckets_url;
    private String created_at;
    private Number followers_count;
    private String followers_url;
    private String following_url;
    private Number followings_count;
    private String html_url;
    private Number id;
    private Number likes_count;
    private String likes_url;
    private Links links;
    private String location;
    private Number members_count;
    private String members_url;
    private String name;
    private boolean pro;
    private Number projects_count;
    private String projects_url;
    private Number shots_count;
    private String shots_url;
    private String team_shots_url;
    private String type;
    private String updated_at;
    private String username;

    public String getAvatar_url(){
        return this.avatar_url;
    }
    public void setAvatar_url(String avatar_url){
        this.avatar_url = avatar_url;
    }
    public String getBio(){
        return this.bio;
    }
    public void setBio(String bio){
        this.bio = bio;
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
    public String getCreated_at(){
        return this.created_at;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public Number getFollowers_count(){
        return this.followers_count;
    }
    public void setFollowers_count(Number followers_count){
        this.followers_count = followers_count;
    }
    public String getFollowers_url(){
        return this.followers_url;
    }
    public void setFollowers_url(String followers_url){
        this.followers_url = followers_url;
    }
    public String getFollowing_url(){
        return this.following_url;
    }
    public void setFollowing_url(String following_url){
        this.following_url = following_url;
    }
    public Number getFollowings_count(){
        return this.followings_count;
    }
    public void setFollowings_count(Number followings_count){
        this.followings_count = followings_count;
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
    public Links getLinks(){
        return this.links;
    }
    public void setLinks(Links links){
        this.links = links;
    }
    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }
    public Number getMembers_count(){
        return this.members_count;
    }
    public void setMembers_count(Number members_count){
        this.members_count = members_count;
    }
    public String getMembers_url(){
        return this.members_url;
    }
    public void setMembers_url(String members_url){
        this.members_url = members_url;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public boolean getPro(){
        return this.pro;
    }
    public void setPro(boolean pro){
        this.pro = pro;
    }
    public Number getProjects_count(){
        return this.projects_count;
    }
    public void setProjects_count(Number projects_count){
        this.projects_count = projects_count;
    }
    public String getProjects_url(){
        return this.projects_url;
    }
    public void setProjects_url(String projects_url){
        this.projects_url = projects_url;
    }
    public Number getShots_count(){
        return this.shots_count;
    }
    public void setShots_count(Number shots_count){
        this.shots_count = shots_count;
    }
    public String getShots_url(){
        return this.shots_url;
    }
    public void setShots_url(String shots_url){
        this.shots_url = shots_url;
    }
    public String getTeam_shots_url(){
        return this.team_shots_url;
    }
    public void setTeam_shots_url(String team_shots_url){
        this.team_shots_url = team_shots_url;
    }
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public Team() {

    }

    protected Team(Parcel in) {
        avatar_url = in.readString();
        bio = in.readString();
        buckets_count = (Number) in.readValue(Number.class.getClassLoader());
        buckets_url = in.readString();
        created_at = in.readString();
        followers_count = (Number) in.readValue(Number.class.getClassLoader());
        followers_url = in.readString();
        following_url = in.readString();
        followings_count = (Number) in.readValue(Number.class.getClassLoader());
        html_url = in.readString();
        id = (Number) in.readValue(Number.class.getClassLoader());
        likes_count = (Number) in.readValue(Number.class.getClassLoader());
        likes_url = in.readString();
        links = (Links) in.readValue(Links.class.getClassLoader());
        location = in.readString();
        members_count = (Number) in.readValue(Number.class.getClassLoader());
        members_url = in.readString();
        name = in.readString();
        pro = in.readByte() != 0x00;
        projects_count = (Number) in.readValue(Number.class.getClassLoader());
        projects_url = in.readString();
        shots_count = (Number) in.readValue(Number.class.getClassLoader());
        shots_url = in.readString();
        team_shots_url = in.readString();
        type = in.readString();
        updated_at = in.readString();
        username = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(avatar_url);
        dest.writeString(bio);
        dest.writeValue(buckets_count);
        dest.writeString(buckets_url);
        dest.writeString(created_at);
        dest.writeValue(followers_count);
        dest.writeString(followers_url);
        dest.writeString(following_url);
        dest.writeValue(followings_count);
        dest.writeString(html_url);
        dest.writeValue(id);
        dest.writeValue(likes_count);
        dest.writeString(likes_url);
        dest.writeValue(links);
        dest.writeString(location);
        dest.writeValue(members_count);
        dest.writeString(members_url);
        dest.writeString(name);
        dest.writeByte((byte) (pro ? 0x01 : 0x00));
        dest.writeValue(projects_count);
        dest.writeString(projects_url);
        dest.writeValue(shots_count);
        dest.writeString(shots_url);
        dest.writeString(team_shots_url);
        dest.writeString(type);
        dest.writeString(updated_at);
        dest.writeString(username);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };
}