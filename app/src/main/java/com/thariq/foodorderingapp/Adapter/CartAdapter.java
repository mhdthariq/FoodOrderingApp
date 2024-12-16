package com.thariq.foodorderingapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.thariq.foodorderingapp.Domain.Foods;
import com.thariq.foodorderingapp.Helper.ChangeNumberItemsListener;
import com.thariq.foodorderingapp.Helper.ManagmentCart;
import com.thariq.foodorderingapp.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final List<Foods> foodList;
    private final ManagmentCart cartManager;
    private final ChangeNumberItemsListener changeListener;

    public CartAdapter(@NonNull List<Foods> foodList, @NonNull Context context, @NonNull ChangeNumberItemsListener changeListener) {
        this.foodList = foodList;
        this.cartManager = new ManagmentCart(context);
        this.changeListener = changeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Foods currentFood = foodList.get(position);

        holder.titleTextView.setText(currentFood.getTitle());
        holder.priceTextView.setText(String.format("$%.2f", currentFood.getPrice()));
        holder.totalTextView.setText(String.format("%d * $%.2f", currentFood.getNumberInCart(), currentFood.getPrice()));
        holder.quantityTextView.setText(String.valueOf(currentFood.getNumberInCart()));

        Glide.with(holder.itemView.getContext())
                .load(currentFood.getImagePath())
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.imageView);

        holder.plusButton.setOnClickListener(v -> {
            cartManager.plusNumberItem((ArrayList<Foods>) foodList, position, () -> {
                notifyItemChanged(position);
                changeListener.change();
            });
        });

        holder.minusButton.setOnClickListener(v -> {
            cartManager.minusNumberItem((ArrayList<Foods>) foodList, position, () -> {
                notifyItemChanged(position);
                changeListener.change();
            });
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView titleTextView;
        private final TextView priceTextView;
        private final TextView totalTextView;
        private final TextView quantityTextView;
        private final ImageView imageView;
        private final TextView plusButton;
        private final TextView minusButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTxt);
            priceTextView = itemView.findViewById(R.id.feeEachItem);
            totalTextView = itemView.findViewById(R.id.totalEachItem);
            quantityTextView = itemView.findViewById(R.id.numberItemTxt);
            imageView = itemView.findViewById(R.id.pic);
            plusButton = itemView.findViewById(R.id.plusCartBtn);
            minusButton = itemView.findViewById(R.id.minusCartBtn);
        }
    }
}