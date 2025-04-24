package com.example.itube.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itube.R;
import com.example.itube.models.Video;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.VideoViewHolder> {

    public interface OnVideoClickListener {
        void onVideoClick(String url);
    }

    private List<Video> videoList;
    private OnVideoClickListener listener;

    public PlaylistAdapter(List<Video> videoList, OnVideoClickListener listener) {
        this.videoList = videoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videoList.get(position);
        holder.textUrl.setText(video.url);
        holder.itemView.setOnClickListener(v -> listener.onVideoClick(video.url));
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textUrl;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            textUrl = itemView.findViewById(R.id.text_video_url);
        }
    }
}
