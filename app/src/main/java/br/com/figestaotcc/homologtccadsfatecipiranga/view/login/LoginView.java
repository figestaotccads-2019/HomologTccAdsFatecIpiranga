package br.com.figestaotcc.homologtccadsfatecipiranga.view.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Login;

public class LoginView extends ArrayAdapter<Login> {

    private Activity context;
    private List<Login> loginView;

    public LoginView(Activity context,List<Login> loginView){
        super(context, R.layout.view_login, loginView);
        this.context = context;
        this.loginView = loginView;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.view_login,null,true);

        @SuppressLint("WrongViewCast") ImageView imageViewLogin = (ImageView) listViewItem.findViewById(R.id.ivLogin);
        TextView textViewNome = (TextView) listViewItem.findViewById(R.id.tvLoginNome);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.tvLoginEmail);

        Login login = loginView.get(position);
    //    imageViewLogin.setImageView(login.getLoginImage());
        textViewNome.setText(login.getLoginNome());
        textViewEmail.setText(login.getLoginEmail());

        return listViewItem;
    }
}
