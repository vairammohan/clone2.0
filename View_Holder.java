package com.kalvi.elaptopsale;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by kalvi on 2/22/2018.
 */

class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView newshead,content;
    ImageView newsImage;
    RatingBar ratingBar;

    public View_Holder(final View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cardView);
        newshead = (TextView) itemView.findViewById(R.id.newshead);
        content= itemView.findViewById(R.id.content);
        newsImage=itemView.findViewById( R.id.newsimage);
        ratingBar=itemView.findViewById(R.id.rating);



    }



}
