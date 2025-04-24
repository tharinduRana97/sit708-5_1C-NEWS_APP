// Same package
// Minor change: Now uses repository to get news data
package com.example.newsapp.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.adapters.NewsAdapter;
import com.example.newsapp.adapters.NewsGridAdapter;
import com.example.newsapp.data.NewsRepository;
import com.example.newsapp.models.NewsItem;

import java.util.List;

public class HomeFragment extends Fragment {
    private NewsRepository repo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Toolbar toolbar = view.findViewById(R.id.home_toolbar);
        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);
        ImageView scrollLeft = view.findViewById(R.id.icon_scroll_left);
        ImageView scrollRight = view.findViewById(R.id.icon_scroll_right);
        RecyclerView topStories = view.findViewById(R.id.recycler_top_stories);
        RecyclerView newsRecycler = view.findViewById(R.id.recycler_news);

        repo = new NewsRepository(requireContext());

        topStories.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        newsRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        repo.seedDataIfEmpty(() -> {
                    repo.getNewsByCategory("top", new NewsRepository.NewsCallback() {
                        @Override
                        public void onResult(List<NewsItem> list) {
                            requireActivity().runOnUiThread(() -> {
                                topStories.setAdapter(new NewsAdapter(list, getParentFragmentManager()));
                            });
                        }

                        @Override
                        public void onError(Exception e) {
                            requireActivity().runOnUiThread(() ->
                                    Toast.makeText(getContext(), "Failed to load top stories", Toast.LENGTH_SHORT).show()
                            );
                        }
                    });

                    repo.getNewsByCategory("news", new NewsRepository.NewsCallback() {
                        @Override
                        public void onResult(List<NewsItem> list) {
                            requireActivity().runOnUiThread(() -> {
                                newsRecycler.setAdapter(new NewsGridAdapter(list, getParentFragmentManager()));
                            });
                        }

                        @Override
                        public void onError(Exception e) {
                            requireActivity().runOnUiThread(() ->
                                    Toast.makeText(getContext(), "Failed to load news stories", Toast.LENGTH_SHORT).show()
                            );
                        }
                    });
                });

        scrollLeft.setOnClickListener(v -> topStories.smoothScrollBy(-300, 0));
        scrollRight.setOnClickListener(v -> topStories.smoothScrollBy(300, 0));

        return view;
    }
}
