package com.kalvi.elaptopsale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created by TOEFL PROXY on 05-05-2018.
 */

public class LapAdapter extends RecyclerView.Adapter<View_Holder> {
    List<LapModel> list = Collections.emptyList();
    Context context;
    String UID;
    public LapAdapter(List<LapModel> list, Context context, String UID) {
        this.list = list;
        this.context = context;
        this.UID = UID;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsmodel, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final View_Holder holder, final int position) {
        holder.newshead.setText(list.get(position).getLaphead());
        holder.ratingBar.setRating(list.get(position).getRating());
        holder.content.setText(list.get(position).getLapcontent());
        String imagename=list.get(position).getLapimage();
        new ImageLoadTask(URLs.baseUrl+"/uploads/"+imagename,  holder.newsImage).execute();
        /*holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Message.message(context,""+position+1);
                Intent intent=new Intent(context,HomepageActivity.class);
                intent.putExtra("UID",UID);
                intent.putExtra("RID",list.get(position).getUID());
                intent.putExtra("PT",2);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private ImageView imageView;

        public ImageLoadTask(String url, ImageView imageView) {
            this.url = url;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            imageView.setImageBitmap(result);
        }

    }
}
