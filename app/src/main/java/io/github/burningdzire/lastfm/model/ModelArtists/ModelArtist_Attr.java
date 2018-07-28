package io.github.burningdzire.lastfm.model.ModelArtists;

import com.google.gson.annotations.SerializedName;

public class ModelArtist_Attr {

    @SerializedName("rank")
    private String rank;

    public String getRank() { return this.rank; }

    public void setRank(String rank) { this.rank = rank; }
}
