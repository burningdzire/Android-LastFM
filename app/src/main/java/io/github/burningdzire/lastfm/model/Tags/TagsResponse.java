package io.github.burningdzire.lastfm.model.Tags;

import com.google.gson.annotations.SerializedName;

public class TagsResponse {

    @SerializedName("toptags")
    private TopTags toptags;

    public TopTags getToptags() {
        return this.toptags;
    }

    public void setToptags(TopTags toptags) {
        this.toptags = toptags;
    }
}
