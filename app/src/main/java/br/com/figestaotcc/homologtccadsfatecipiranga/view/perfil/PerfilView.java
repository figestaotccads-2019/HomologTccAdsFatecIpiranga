package br.com.figestaotcc.homologtccadsfatecipiranga.view.perfil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Perfil;

public class PerfilView  extends ArrayAdapter<Perfil> {

    private Activity context;
    private List<Perfil> perfilView;

    public PerfilView(Activity context,List<Perfil> perfilView){
        super(context, R.layout.view_perfil, perfilView);
        this.context = context;
        this.perfilView = perfilView;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.view_perfil,null,true);

        @SuppressLint("WrongViewCast") ImageView imageViewPerfil = (ImageView) listViewItem.findViewById(R.id.ivPerfil);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.tvPerfilNome);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.tvPerfilEmail);

        Perfil perfil= perfilView.get(position);
        //    imageViewLogin.setImageView(login.getLoginImage());
        textViewNome.setText(perfil.getPerfilNome());
        textViewEmail.setText(perfil.getPerfilEmail());

        return listViewItem;
    }
}