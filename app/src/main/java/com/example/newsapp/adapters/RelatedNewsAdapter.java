package com.example.newsapp.adapters;

import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.fragments.DetailFragment;
import com.example.newsapp.models.NewsItem;
import java.util.List;

public class RelatedNewsAdapter extends RecyclerView.Adapter<RelatedNewsAdapter.RelatedViewHolder> {
    private List<NewsItem> relatedList;
    private FragmentManager fragmentManager;

    public RelatedNewsAdapter(List<NewsItem> relatedList, FragmentManager fragmentManager) {
        this.relatedList = relatedList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public RelatedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_vertical, parent, false);
        return new RelatedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelatedViewHolder holder, int position) {
        NewsItem item = relatedList.get(position);
        holder.title.setText(item.title);
        holder.desc.setText(item.description);
        holder.image.setImageResource(item.imageRes);

        holder.itemView.setOnClickListener(v -> {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, new DetailFragment(item.title, item.description, item.imageRes))
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return relatedList.size();
    }

    static class RelatedViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc;
        ImageView image;

        RelatedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.related_title);
            desc = itemView.findViewById(R.id.related_desc);
            image = itemView.findViewById(R.id.related_image);
        }
    }
}
