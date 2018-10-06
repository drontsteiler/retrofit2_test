package com.dormitory.retrofit_test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dormitory.retrofit_test.R;
import com.dormitory.retrofit_test.model.Post;

import java.util.List;

public class PostAdapter extends ArrayAdapter<Post> {

    List<Post> postList;
    Context context;
    private LayoutInflater mInFlater;

    public PostAdapter(Context context, List<Post> oblects) {
        super(context, 0, oblects);
        this.context = context;
        this.mInFlater = LayoutInflater.from(context);
        postList = oblects;
    }

    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final ViewHolder vh;
        if(convertView==null){
            View view= mInFlater.inflate(R.layout.layout_row_view, parent, false);
            vh=ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        Post item = getItem(position);
        vh.textViewTitle.setText(item.getTitle());
        vh.textViewBody.setText(item.getBody());
//        Picasso.with(context).load(item.get)

        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;
        public final ImageView imageView;
        public final TextView textViewTitle;
        public final TextView textViewBody;

        private ViewHolder(RelativeLayout rootView, ImageView imageView, TextView textViewTitle, TextView textViewBody) {
            this.rootView = rootView;
            this.imageView = imageView;
            this.textViewTitle = textViewTitle;
            this.textViewBody = textViewBody;
        }


        public static ViewHolder create(RelativeLayout rootView) {
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageView);
            TextView textViewTitle = (TextView) rootView.findViewById(R.id.textViewTitle);
            TextView textViewBody = (TextView) rootView.findViewById(R.id.textViewBody);
            return new ViewHolder(rootView, imageView, textViewTitle, textViewBody);

        }


    }
}
