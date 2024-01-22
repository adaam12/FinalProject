package com.example.finalproject.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;


import com.example.finalproject.R;
import com.example.finalproject.interfaces.onNewsClick;
import com.example.finalproject.model.FeedModel;

import java.util.List;


public class SavedFeedAdapter extends RecyclerView.Adapter<SavedFeedAdapter.ViewHolder>{
    private List<FeedModel> listdata;
    private onNewsClick listner;
    public SavedFeedAdapter(List<FeedModel> listdata, onNewsClick listner) {
        this.listdata = listdata;
        this.listner = listner;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.llist_item_feeds, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        try{
            holder.tv_title.setText(listdata.get(position).getTitle());
            holder.tv_description.setText(listdata.get(position).getDescription());
            holder.tv_date.setText(listdata.get(position).getDate());
            holder.tv_link.setText(listdata.get(position).getLink());
            // Set the link text with HTML and make it clickable
            String linkText = listdata.get(position).getLink();
            holder.tv_link.setText(Html.fromHtml("<u><font color='#0000FF'>" + linkText + "</font></u>"));

// Make the link clickable and handle the click event
            holder.tv_link.setMovementMethod(LinkMovementMethod.getInstance());
            holder.tv_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event, for example, open the link in a browser
                    String url = listdata.get(position).getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
            holder.cb_favorite.setVisibility(View.GONE);
            holder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listner.onNewsClick(listdata.get(position),true);

                }
            });
        }catch (Exception e){
            Log.d("FeedsAdapter",""+e.toString());
        }


    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title,tv_description,tv_date,tv_link;
       public FrameLayout frameLayout;
        public AppCompatCheckBox cb_favorite;
        public ViewHolder(View itemView) {
            super(itemView);

            this.tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            this.tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            this.tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            this.tv_link = (TextView) itemView.findViewById(R.id.tv_link);
            this.cb_favorite = (AppCompatCheckBox) itemView.findViewById(R.id.cb_favorite);
            this.frameLayout = (FrameLayout) itemView.findViewById(R.id.framelayout);

        }
    }
}


