package com.isosceles.nytsearch;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Teletran on 7/28/2016.
 */
public class ArticleArrayAdapter extends ArrayAdapter<Article> {


    public ArticleArrayAdapter(Context context, List<Article> articles)  {
        super(context, android.R.layout.simple_list_item_1, articles);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data item for position
        Article article = this.getItem(position);

        // check to see if existing view is being reused
        // not using a recycled view -> inflate the layout | view holder pattern
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
        }
        // find the image view
        ImageView imageView = (ImageView) convertView.findViewById(R.id.ivImage);
        // clear out recycled image from convertView from last time
        imageView.setImageResource(0);

        // find the textview headline
        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(article.getHeadLine());

        // populate the thumbmail image
        // remote download the image in the background

        String thumbnail = article.getThumbNail();

        if (!TextUtils.isEmpty(thumbnail)) {
            Picasso.with(getContext()).load(thumbnail).fit().into(imageView);
        }

        return convertView;
    }
}
