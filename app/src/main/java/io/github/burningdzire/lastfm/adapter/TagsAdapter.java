package io.github.burningdzire.lastfm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.github.burningdzire.lastfm.R;
import io.github.burningdzire.lastfm.model.Tags.Tag;

public class TagsAdapter extends RecyclerView.Adapter<TagsAdapter.TagsViewHolder> {

    private List<Tag> mTags;
    private int rowLayout;
    private Context context;

//    OnClickListener Implemented here
    final private TagsClickListener mOnClickListener;
    public interface TagsClickListener {
        void onListItemClick(String tag);
    }

//    Constructor for TagsAdapter
    public TagsAdapter(List<Tag> mTags, int rowLayout, Context context, TagsClickListener listener) {
        this.mTags = mTags;
        this.rowLayout = rowLayout;
        this.context = context;
        mOnClickListener = listener;
    }

    @NonNull
    @Override
    public TagsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new TagsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TagsViewHolder tagsApadterViewHolder, int position) {
        String tagName = mTags.get(position).getName();
        tagName = tagName.substring(0,1).toUpperCase() + tagName.substring(1);
        tagsApadterViewHolder.tagName.setText(tagName);
        tagsApadterViewHolder.tagCount.setText("" + mTags.get(position).getCount());
        tagsApadterViewHolder.tagReach.setText("" + mTags.get(position).getReach());
    }

    @Override
    public int getItemCount() {
        return mTags.size();
    }

    public class TagsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        LinearLayout tagsLayout;
        TextView tagName;
        TextView tagCount;
        TextView tagReach;

        public TagsViewHolder(@NonNull View itemView) {
            super(itemView);

            tagsLayout = (LinearLayout) itemView.findViewById(R.id.tags_layout);
            tagName = (TextView) itemView.findViewById(R.id.tv_tag_name);
            tagCount = (TextView) itemView.findViewById(R.id.tv_tag_count);
            tagReach = (TextView) itemView.findViewById(R.id.tv_tag_reach);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            String tag = mTags.get(clickedPosition).getName();
            mOnClickListener.onListItemClick(tag);
        }
    }
}
