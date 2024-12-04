package com.isp.app.ui.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.isp.app.R;

public class ArticleDetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_CONTENT = "content";

    public static ArticleDetailFragment newInstance(String title, String content) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        TextView titleTextView = view.findViewById(R.id.articleDetailTitle);
        TextView contentTextView = view.findViewById(R.id.articleDetailContent);
        ImageView imageView = view.findViewById(R.id.articleDetailImage);

        if (getArguments() != null) {
            String title = getArguments().getString(ARG_TITLE);
            titleTextView.setText(title);
            contentTextView.setText(getArguments().getString(ARG_CONTENT));

            if ("Scott's Tots".equals(title)) {
                imageView.setImageResource(R.drawable.scotts_tots);
                imageView.setVisibility(View.VISIBLE);
            }
        }

        return view;
    }
} 