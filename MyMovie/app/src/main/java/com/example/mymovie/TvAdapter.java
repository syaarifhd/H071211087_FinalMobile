package com.example.mymovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvHolder> {
    Context context;
    List<Tv> tvList;

    public TvAdapter(Context context , List<Tv> tv){
        this.context = context;
        tvList = tv;
    }
    @NonNull
    @Override
    public TvAdapter.TvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new TvAdapter.TvHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvAdapter.TvHolder holder, int position) {

        Tv tv = tvList.get(position);
        holder.release.setText(tv.getRelease());
        holder.title.setText(tv.getTitle());
        holder.overview.setText(tv.getOverview());
        Glide.with(context).load(tv.getPoster()).into(holder.imageView);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putString("title" , tv.getTitle());
                bundle.putString("overview" , tv.getOverview());
                bundle.putString("poster" , tv.getPoster());
                bundle.putString("release" , tv.getRelease());

                intent.putExtras(bundle);

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tvList.size();
    }

    public class TvHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView title , overview , release;
        RelativeLayout relativeLayout;

        public TvHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title_tv);
            overview = itemView.findViewById(R.id.overview_tv);
            release = itemView.findViewById(R.id.release);
            relativeLayout = itemView.findViewById(R.id.main_layout);
        }
    }
}
