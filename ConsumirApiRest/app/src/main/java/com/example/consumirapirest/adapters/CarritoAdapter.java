package com.example.consumirapirest.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.consumirapirest.CarroCompraActivity;
import com.example.consumirapirest.R;
import com.example.consumirapirest.model.Insumo;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.InsumoViewHolderData> implements View.OnClickListener {

    public Context context;
    private View.OnClickListener listener;
    ArrayList<Insumo> carroCompra;
    TextView tvTotal;
    double total = 0;
    
    public CarritoAdapter(Context context,ArrayList carroCompra,TextView tvTotal){
        this.context = context;
        this.carroCompra = carroCompra;
        this.tvTotal = tvTotal;
    }

    @NonNull
    @Override
    public CarritoAdapter.InsumoViewHolderData onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_carro_compras,null,false);
        view.setOnClickListener(this);
        return new CarritoAdapter.InsumoViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CarritoAdapter.InsumoViewHolderData myLittleViewHolderData, final int i) {
        myLittleViewHolderData.setData(carroCompra.get(i));
        total = total + Double.parseDouble(""+carroCompra.get(i).getIns_pre_uni());
        tvTotal.setText("S/."+total);
    }

    @Override
    public int getItemCount() {
        return carroCompra.size();//obteniendo tamaño del ArrayList
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setOnclickListener(View.OnClickListener listener) { this.listener = listener;}

    public class InsumoViewHolderData extends RecyclerView.ViewHolder {
        TextView tv_nombre;
        TextView tv_precio;
        TextView tv_categoria;
        CircleImageView iv_insumo;
        public InsumoViewHolderData(@NonNull View itemView) {
            super(itemView);
            tv_nombre = (TextView) itemView.findViewById(R.id.tvnombreinsumo);
            tv_precio = (TextView) itemView.findViewById(R.id.tvprecio);
            tv_categoria = (TextView) itemView.findViewById(R.id.tvcategoria);
            iv_insumo = (CircleImageView) itemView.findViewById(R.id.imageView);
        }

        public void setData(final Insumo insumo) {
            tv_nombre.setText(insumo.getIns_nombre());
            tv_precio.setText("S/."+insumo.getIns_pre_uni());
            tv_categoria.setText(insumo.getCat_nombre());
            Picasso.with(context).load(insumo.getIns_imagen()).fit().into(iv_insumo);
        }

    }
}
