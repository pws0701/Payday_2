package com.example.heejung.payday_2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juni on 2015-11-10.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemHolder> {

    LayoutInflater layoutInflater;
    OnItemClickListener onItemClickListener;

    Context mContext;
    private List<Market> marketList;

    public RecyclerViewAdapter(Context context, List<Market> arrMarket){
        layoutInflater = LayoutInflater.from(context);
        marketList = new ArrayList<Market>();
        this.mContext = context;
        this.marketList = arrMarket;
    }

    @Override
    public RecyclerViewAdapter.ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView itemCardView = (CardView)layoutInflater.inflate(R.layout.layout_cardview, parent, false);
        return new ItemHolder(itemCardView, this);
    }



    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ItemHolder holder, int position) {
        holder.setItemImage(marketList.get(position).getBookImageUrl());
        holder.setItemName(marketList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return marketList.size();
    }


    /**public OnItemClickListener getOnItemClickListener(){
        return onItemClickListener;
    }**/

    public interface OnItemClickListener{
        public void onItemClick(View view, int position);
    }

    public void add(int location, String iName){
        //marketList.add(location, iName);
        notifyItemInserted(location);
    }

    public void remove(int location){
        if(location >= marketList.size())
            return;
        marketList.remove(location);
        notifyItemRemoved(location);
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewAdapter parent;
        private CardView cardView;

        TextView itemName;
        ImageView itemImage;

        public ItemHolder(CardView cView, RecyclerViewAdapter parent){
            super(cView);
            cardView = cView;
            this.parent = parent;
            itemName = (TextView)cardView.findViewById(R.id.card_item_name);
            itemImage = (ImageView)cardView.findViewById(R.id.card_item_image);
        }

        public void setItemName(CharSequence name){
            itemName.setText(name);
        }

        public void setItemImage(String imgUrl){
            ImageRequest imageRequest = new ImageRequest(imgUrl,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            itemImage.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {

                        }
                    });

            VolleySingleton.getInstance(mContext).getRequestQueue().add(imageRequest);
        }

        @Override
        public void onClick(View v) {
           // final OnItemClickListener listener = parent.getOnItemClickListener();
            if(onItemClickListener != null){
                onItemClickListener.onItemClick(v, getPosition());

            }
        }
    }
}