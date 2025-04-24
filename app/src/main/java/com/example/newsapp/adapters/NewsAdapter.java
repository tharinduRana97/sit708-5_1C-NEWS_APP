package com.example.newsapp.adapters;

import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.fragments.DetailFragment;
import com.example.newsapp.models.NewsItem;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsItem> newsList;
    private FragmentManager fragmentManager;

    public NewsAdapter(List<NewsItem> newsList, FragmentManager fragmentManager) {
        this.newsList = newsList;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_horizontal, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
        NewsItem item = newsList.get(position);
        holder.title.setText(item.title);
        holder.image.setImageResource(item.imageRes);

        // Click opens DetailFragment
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

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;
        NewsViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news_title);
            image = itemView.findViewById(R.id.news_image);
        }
    }
}
