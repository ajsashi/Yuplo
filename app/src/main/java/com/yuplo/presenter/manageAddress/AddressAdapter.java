package com.yuplo.presenter.manageAddress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yuplo.R;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.ViewHolder> {

    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_address_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView edit;
        TextView delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            edit = itemView.findViewById(R.id.txt_edit);
            delete = itemView.findViewById(R.id.txt_delete);


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, getAdapterPosition()+" Edit Clicked", Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, getAdapterPosition()+" delete Clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
