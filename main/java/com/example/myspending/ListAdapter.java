package com.example.myspending;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Vector;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context ctx;

    Vector<SpendingList> vlist;

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText(vlist.get(position).getName());
        holder.tv_price.setText(String.valueOf(vlist.get(position).getPrice()));
        holder.tv_date.setText(vlist.get(position).getDate());

        holder.cv_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, EditList.class);
                intent.putExtra("id", vlist.get(position).getId());
                intent.putExtra("name", vlist.get(position).getName());
                intent.putExtra("price", vlist.get(position).getPrice());
                ctx.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return vlist.size();
    }

    public void setData(Vector<SpendingList> vlist){
        this.vlist = vlist;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_price, tv_date;
        CardView cv_list;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_name);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_date = itemView.findViewById(R.id.tv_date);
            cv_list = itemView.findViewById(R.id.cv_list);

        }
    }

}
