package io.github.burningdzire.lastfm.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import io.github.burningdzire.lastfm.model.Tags.*;
import io.github.burningdzire.lastfm.model.ModelAlbums.*;
import io.github.burningdzire.lastfm.model.ModelArtists.*;
import io.github.burningdzire.lastfm.model.ModelTracks.*;

public interface NetworkInterface {

//    http://ws.audioscrobbler.com/2.0/?method=tag.getTopTags&api_key=YOUR_API_KEY&format=json
    @GET("2.0")
    Call<TagsResponse> getTopTags(@Query("method") String method,
                                  @Query("api_key") String api_key,
                                  @Query("format") String format);

//    http://ws.audioscrobbler.com/2.0/?method=tag.gettopalbums&tag=disco&api_key=YOUR_API_KEY&format=json
    @GET("2.0")
    Call<ModelAlbums_Root> getTopAlbums(@Query("method") String method,
                                    @Query("tag") String tag,
                                    @Query("api_key") String api_key,
                                    @Query("format") String format);


//    http://ws.audioscrobbler.com/2.0/?method=tag.gettopartists&tag=disco&api_key=YOUR_API_KEY&format=json
    @GET("2.0")
    Call<ModelArtist_Root> getTopArtists(@Query("method") String method,
                                     @Query("tag") String tag,
                                     @Query("api_key") String api_key,
                                     @Query("format") String format);

//    http://ws.audioscrobbler.com/2.0/?method=tag.gettopalbums&tag=disco&api_key=YOUR_API_KEY&format=json
    @GET("2.0")
    Call<ModelTrack_Root> getTopTracks(@Query("method") String method,
                                    @Query("tag") String tag,
                                    @Query("api_key") String api_key,
                                    @Query("format") String format);


}
