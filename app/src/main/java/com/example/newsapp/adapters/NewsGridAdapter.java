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

public class NewsGridAdapter extends RecyclerView.Adapter<NewsGridAdapter.NewsGridViewHolder> {
    private List<NewsItem> newsList;
    private FragmentManager fragmentManager;

    public NewsGridAdapter(List<NewsItem> newsList, FragmentManager fragmentManager) {
        this.newsList = newsList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public NewsGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_grid, parent, false);
        return new NewsGridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsGridViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.title.setText(item.title);
        holder.preview.setText(item.description); // assuming description acts as preview
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
        return newsList.size();
    }

    static class NewsGridViewHolder extends RecyclerView.ViewHolder {
        TextView title, preview;
        ImageView image;

        NewsGridViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            preview = itemView.findViewById(R.id.news_preview);
            image = itemView.findViewById(R.id.news_image);
        }
    }
}
