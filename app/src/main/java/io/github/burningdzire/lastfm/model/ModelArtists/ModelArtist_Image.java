package io.github.burningdzire.lastfm.model.ModelArtists;

import com.google.gson.annotations.SerializedName;

public class ModelArtist_Image {

    @SerializedName("#text")
    private String text;

    public String getText() { return this.text; }

    public void setText(String text) { this.text = text; }

    @SerializedName("size")
    private String size;

    public String getSize() { return this.size; }

    public void setSize(String size) { this.size = size; }
}
