package com.mobile.desafio.repositoriosgithub.adapter;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.asynctask.ListarPullsAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Pull;
import com.mobile.desafio.repositoriosgithub.dominio.Status;
import com.mobile.desafio.repositoriosgithub.util.imageloader.ImageLoader;

import java.util.List;

public class PullAdapter extends RecyclerView.Adapter<PullAdapter.ViewHolderPull> {

    private ImageLoader imageLoader;
    private Pagina ultimaPagina;
    private GitHubActivity activity;
    private List<Pull> pulls;

    public PullAdapter(@NonNull GitHubActivity context, Pagina pagina) {
        this.imageLoader = new ImageLoader(context);
        this.ultimaPagina = pagina;
        this.activity = context;
        this.pulls = pagina.getItens();
    }

    public void addPulls(List<Pull> pull) {
        this.pulls.addAll(pull);
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return this.pulls.size();
    }

    public void setUltimaPagina(Pagina ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }

    @Override
    public PullAdapter.ViewHolderPull onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = inflater.inflate(R.layout.item_pull, parent, false);
        return new PullAdapter.ViewHolderPull(v1);
    }

    @Override
    public void onBindViewHolder(PullAdapter.ViewHolderPull holder, int position) {

        final Pull pull = this.pulls.get(position);
        holder.titulo.setText(pull.getTitulo());
        holder.descricao.setText(pull.getDescricao());
        holder.nomeUsuario.setText(pull.getDono().getNome());
        holder.nomeUsuarioCompleto.setText(pull.getDono().getNomeCompleto());
        this.imageLoader.loadImage(pull.getDono().getUrlImagem(), holder.urlUsuario, R.drawable.user);

        Status status = Status.getStatus(pull);

        GradientDrawable background = (GradientDrawable) holder.status.getBackground();
        background.setColor(activity.getResources().getColor(status.getCor()));
        holder.status.setText(status.getNome());

        if (position == (getItemCount() - 1) && ultimaPagina.existeProxima()) {
            ListarPullsAsyncTask listarPullsAsyncTask = new ListarPullsAsyncTask(activity);
            listarPullsAsyncTask.execute(ultimaPagina.getProxima());
        }

        holder.bind(pull, new OnItemClickListener<Pull>() {
            @Override
            public void onItemClick(Pull pull) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(pull.getHtml()));
                activity.startActivity(i);
            }
        });

    }

    public class ViewHolderPull extends RecyclerView.ViewHolder {

        final TextView titulo;
        final TextView descricao;
        final TextView nomeUsuario;
        final TextView nomeUsuarioCompleto;
        final CircularImageView urlUsuario;
        final TextView status;

        public ViewHolderPull(View view) {
            super(view);

            titulo = view.findViewById(R.id.ItemPull_Titulo);
            descricao = view.findViewById(R.id.ItemPull_Descricao);
            nomeUsuario = view.findViewById(R.id.ItemPull_NomeUsuario);
            nomeUsuarioCompleto = view.findViewById(R.id.ItemPull_NomeUsuarioCompleto);
            urlUsuario = view.findViewById(R.id.ItemPull_ImagemUsuario);
            this.status = view.findViewById(R.id.ItemPull_Status);
        }

        public void bind(final Pull pull, final OnItemClickListener<Pull> listener) {
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(pull);
                }
            });
        }

    }
}