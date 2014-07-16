package com.gdg.rssreader.feed;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdg.rssreader.R;
import com.gdg.rssreader.model.RssItem;

import java.util.List;

public class RssItemAdapter extends RecyclerView.Adapter<RssItemAdapter.ViewHolder>{

    private List<RssItem> items;
    public RssItemAdapter(List<RssItem> items){
       this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_summary, null);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detailIntent = new Intent(view.getContext(), RssItemDetailActivity.class);
                detailIntent.putExtra(RssItemDetailActivity.ITEM_POSITION, 0);
                view.getContext().startActivity(detailIntent);
            }
        };

        ViewHolder viewHolder = new ViewHolder(v, clickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setRssItem(items.get(i));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //View Holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView description;
        private RssItem item;
        public ViewHolder(View v, View.OnClickListener clickListener){
            super(v);
            v.setOnClickListener(clickListener);
            title = (TextView)v.findViewById(R.id.text_story_title);
            description = (TextView)v.findViewById(R.id.text_story_description);
        }

        public void setRssItem(RssItem item){
            this.item = item;
            this.title.setText(item.getTitle());
            this.description.setText(item.getDescription());
        }

    }
}
