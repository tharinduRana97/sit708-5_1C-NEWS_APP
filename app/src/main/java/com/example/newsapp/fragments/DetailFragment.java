package com.example.newsapp.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.adapters.RelatedNewsAdapter;
import com.example.newsapp.data.NewsRepository;
import com.example.newsapp.models.NewsItem;

import java.util.List;

public class DetailFragment extends Fragment {
    private String title, description;
    private int imageRes;

    public DetailFragment(String title, String description, int imageRes) {
        this.title = title;
        this.description = description;
        this.imageRes = imageRes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        Toolbar toolbar = view.findViewById(R.id.detail_toolbar);
        toolbar.setNavigationOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());

        ((TextView) view.findViewById(R.id.detail_title)).setText(title);
        ((TextView) view.findViewById(R.id.detail_desc)).setText(description);
        ((ImageView) view.findViewById(R.id.detail_image)).setImageResource(imageRes);

        RecyclerView relatedNews = view.findViewById(R.id.recycler_related_news);
        relatedNews.setLayoutManager(new LinearLayoutManager(getContext()));

        NewsRepository repo = new NewsRepository(requireContext());
        repo.getNewsByCategory("related", new NewsRepository.NewsCallback() {
            @Override
            public void onResult(List<NewsItem> list) {
                requireActivity().runOnUiThread(() -> {
                    relatedNews.setAdapter(new RelatedNewsAdapter(list, getParentFragmentManager()));
                });
            }

            @Override
            public void onError(Exception e) {
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Failed to load related news", Toast.LENGTH_SHORT).show()
                );
            }
        });

        return view;
    }
}
