package com.isp.app.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.isp.app.R;
import com.isp.app.data.models.NewsArticle;
import java.util.Arrays;
import java.util.List;

public class NewsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.newsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Updated data with a second article
        List<NewsArticle> articles = Arrays.asList(
            new NewsArticle("Professor Lori Hogan Awards 100 to Developers", "Our professor Lori Hogan gave the developers a 100 since the project was so amazing."),
            new NewsArticle("Scott's Tots", "Michael Scott launched a program named \"Scott's Tots\", where he promised a group of underprivileged 3rd graders that he would pay their full college tuition, provided they graduate from high school.")
        );

        NewsAdapter adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);

        return view;
    }
} 