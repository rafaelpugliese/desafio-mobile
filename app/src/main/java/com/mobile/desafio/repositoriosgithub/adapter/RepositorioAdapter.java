package com.mobile.desafio.repositoriosgithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.mobile.desafio.repositoriosgithub.R;
import com.mobile.desafio.repositoriosgithub.activity.GitHubActivity;
import com.mobile.desafio.repositoriosgithub.activity.PullsActivity;
import com.mobile.desafio.repositoriosgithub.asynctask.ListarRepositoriosAsyncTask;
import com.mobile.desafio.repositoriosgithub.dominio.Pagina;
import com.mobile.desafio.repositoriosgithub.dominio.Repositorio;
import com.mobile.desafio.repositoriosgithub.util.imageloader.ImageLoader;

import java.util.List;

public class RepositorioAdapter extends RecyclerView.Adapter<RepositorioAdapter.ViewHolderRepositorio> {

    private ImageLoader imageLoader;
    private GitHubActivity activity;
    private Pagina<Repositorio> ultimaPagina;
    private List<Repositorio> repositorios;

    public RepositorioAdapter(@NonNull Context context, Pagina<Repositorio> pagina) {
        this.imageLoader = new ImageLoader(context);
        this.activity = (GitHubActivity) context;
        this.ultimaPagina = pagina;
        this.repositorios = pagina.getItens();
    }

    @Override
    public int getItemCount() {
        return this.repositorios.size();
    }

    @Override
    public ViewHolderRepositorio onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v1 = inflater.inflate(R.layout.item_repositorio, parent, false);
        return new ViewHolderRepositorio(v1);
    }

    @Override
    public void onBindViewHolder(ViewHolderRepositorio holder, int position) {

        final Repositorio repositorio = this.repositorios.get(position);
        holder.nome.setText(repositorio.getNome());
        holder.descricao.setText(repositorio.getDescricao());
        holder.forks.setText(String.valueOf(repositorio.getForks()));
        holder.stars.setText(String.valueOf(repositorio.getStars()));
        holder.nomeUsuario.setText(repositorio.getDono().getNome());
        holder.nomeUsuarioCompleto.setText(repositorio.getDono().getNomeCompleto());
        this.imageLoader.loadImage(repositorio.getDono().getUrlImagem(), holder.urlUsuario, R.drawable.user);

        holder.bind(repositorio, new OnItemClickListener<Repositorio>() {
            @Override
            public void onItemClick(Repositorio item) {
                Intent intent = new Intent(activity, PullsActivity.class);
                intent.putExtra(PullsActivity.EXTRA_REPOSITORIO, repositorio);
                activity.startActivity(intent);
            }
        });

        if (position == (this.getItemCount() - 3) && ultimaPagina.existeProxima()) {
            ListarRepositoriosAsyncTask listarRepositoriosAsyncTask = new ListarRepositoriosAsyncTask(activity);
            listarRepositoriosAsyncTask.execute(ultimaPagina.getProxima());
        }

    }

    public void addRepositorios(List<Repositorio> repositorios) {
        this.repositorios.addAll(repositorios);
        notifyDataSetChanged();
    }

    public void setUltimaPagina(Pagina<Repositorio> ultimaPagina) {
        this.ultimaPagina = ultimaPagina;
    }

    class ViewHolderRepositorio extends RecyclerView.ViewHolder {

        final TextView nome;
        final TextView descricao;
        final TextView forks;
        final TextView stars;
        final TextView nomeUsuario;
        final TextView nomeUsuarioCompleto;
        final CircularImageView urlUsuario;

        ViewHolderRepositorio(View view) {
            super(view);

            nome = view.findViewById(R.id.ItemRepositorio_Nome);
            descricao = view.findViewById(R.id.ItemRepositorio_Descricao);
            forks = view.findViewById(R.id.ItemRepositorio_Forks);
            stars = view.findViewById(R.id.ItemRepositorio_Stars);
            nomeUsuario = view.findViewById(R.id.ItemRepositorio_NomeUsuario);
            nomeUsuarioCompleto = view.findViewById(R.id.ItemRepositorio_NomeUsuarioCompleto);
            urlUsuario = view.findViewById(R.id.ItemRepositorio_ImagemUsuario);
        }

        void bind(final Repositorio repositorio, final OnItemClickListener<Repositorio> listener) {
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(repositorio);
                }
            });
        }

    }
}