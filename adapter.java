package com.cesargroup.blockcallspam.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.cesargroup.blockcallspam.OpcoesTelefone;
import com.cesargroup.blockcallspam.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

    List<Postagem> postagemList;
    public Adapter(List<Postagem> postagem) {
        this.postagemList = postagem;
        // recebe um OBJ do tipo postagem
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(itemHolder);
    }


    @Override
    public void onBindViewHolder(@NonNull Adapter.Holder holder, int position) {
        Postagem postagem = this.postagemList.get(position);
        holder.nomeContato.setText(postagem.getNome());
        holder.numero.setText(postagem.getNumero());

        // cliick no CardView
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(view.getContext(),
                        holder.nomeContato.getText().toString(),
                        holder.numero.getText().toString());
            }
        });
    }

    // passando os dados para outra activity.
    public static void start(Context context, String nome, String numero) {
        Intent starter = new Intent(context, OpcoesTelefone.class);
        starter.putExtra("nome", nome);
        starter.putExtra("numero", numero);
        context.startActivity(starter);
    }

    @Override
    public int getItemCount() {
        return this.postagemList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView numero;
        TextView nomeContato;
        CardView cardView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            numero = itemView.findViewById(R.id.numero);
            nomeContato = itemView.findViewById(R.id.nomeContato);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
