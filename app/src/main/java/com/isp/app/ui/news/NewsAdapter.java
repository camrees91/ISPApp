package com.isp.app.ui.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.NewsArticle;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private final List<NewsArticle> articles;

    public NewsAdapter(List<NewsArticle> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_article, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsArticle article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.itemView.setOnClickListener(v -> {
            FragmentActivity activity = (FragmentActivity) v.getContext();
            NavHostFragment.findNavController(activity.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment))
                    .navigate(R.id.action_newsFragment_to_articleDetailFragment,
                            ArticleDetailFragment.newInstance(article.getTitle(), article.getContent()).getArguments());
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.articleTitle);
        }
    }
} 