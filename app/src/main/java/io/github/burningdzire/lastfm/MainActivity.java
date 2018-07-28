package io.github.burningdzire.lastfm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.github.burningdzire.lastfm.activities.DetailActivity;
import io.github.burningdzire.lastfm.adapter.TagsAdapter;
import io.github.burningdzire.lastfm.model.Tags.Tag;
import io.github.burningdzire.lastfm.model.Tags.TagsResponse;
import io.github.burningdzire.lastfm.rest.NetworkClient;
import io.github.burningdzire.lastfm.rest.NetworkInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements TagsAdapter.TagsClickListener{

    private RecyclerView recyclerView;
    private TagsAdapter mTagsAdapter;
    private final String API_KEY = "YOUR_API_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkInterface networkInterface = NetworkClient.getClient().create(NetworkInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.rv_tags);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<TagsResponse> call = networkInterface.getTopTags("tag.getTopTags",API_KEY,"json");
        call.enqueue(new Callback<TagsResponse>() {
            @Override
            public void onResponse(Call<TagsResponse> call, Response<TagsResponse> response) {
                List<io.github.burningdzire.lastfm.model.Tags.Tag> mTags = response.body().getToptags().getTag();
                mTagsAdapter = new TagsAdapter(mTags, R.layout.list_item_tag, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(mTagsAdapter);
            }

            @Override
            public void onFailure(Call<TagsResponse> call, Throwable t) {
                Log.e("MainActivity", t.toString());
            }
        });
    }

    @Override
    public void onListItemClick(String tag) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class)
                .putExtra(Intent.EXTRA_TEXT, tag);
        startActivity(intent);
    }
}
