package io.github.burningdzire.lastfm.model.ModelTracks;

import com.google.gson.annotations.SerializedName;

public class ModelTrack_Attr {

    @SerializedName("rank")
    private String rank;

    public String getRank() { return this.rank; }

    public void setRank(String rank) { this.rank = rank; }
}
