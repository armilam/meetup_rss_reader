package com.gdg.rssreader.feed;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdg.rssreader.R;
import com.gdg.rssreader.model.RssItem;

public class RssItemDetailFragment extends Fragment {
    private RssItem item;
    private WebView webView;


    public static RssItemDetailFragment instance(RssItem item) {
        RssItemDetailFragment fragment = new RssItemDetailFragment();
        fragment.item = item;
        return fragment;
    }

    public RssItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rssitem_detail, container, false);
        webView = (WebView) rootView.findViewById(R.id.web_story);
        loadUrl(item.getLink());

        getActivity().getActionBar().setTitle(item.getTitle());

        String story = item.getContent();
        Log.e("TAG", "Story: " + story);
        return rootView;

    }

    private void loadUrl(String url) {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }
}
