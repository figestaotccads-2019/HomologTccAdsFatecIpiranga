package br.com.figestaotcc.homologtccadsfatecipiranga.view.perfil;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Login;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Perfil;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.BaseActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.menu.MenuActivity;

public class PerfilUserActivity extends BaseActivity {


    ImageView imageViewImage;
    TextView textViewPerfilUid;
    TextView textViewLoginUid;
    TextView textViewNome;
    TextView textViewEmail;
    TextView textViewStatus;

    TextView textViewEnderecao;
    TextView textViewCidade;
    TextView textViewEstado;
    TextView textViewCep;
    TextView textViewLocalizacao;
    TextView textViewTelefone;

    private Toolbar mToolbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference loginReference;
    DatabaseReference perfilReference;
    // [START declare_auth]
    private FirebaseAuth mAuth;

   private void configuraFirebase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        loginReference = firebaseDatabase.getReference("Login");
        perfilReference = firebaseDatabase.getReference("PerfilUsuario");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_current_user);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);

           //  viewLogin = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        configuraFirebase();

        textViewPerfilUid = findViewById(R.id.uidPerfil);
        textViewLoginUid = findViewById(R.id.uidLogin);
        textViewNome = findViewById(R.id.nomePerfil);
        textViewEmail =findViewById(R.id.emailPerfil);
        textViewStatus = findViewById(R.id.statusPerfil);
        textViewEnderecao = findViewById(R.id.enderecoPerfil);
        textViewCidade = findViewById(R.id.cidadePerfil);
        textViewEstado = findViewById(R.id.estadoPerfil);
        textViewCep = findViewById(R.id.cepPerfil);
        textViewLocalizacao = findViewById(R.id.localizacaoPerfil);
        textViewTelefone = findViewById(R.id.telefonePerfil);

        Bundle dados = getIntent().getExtras();
        Login login = (Login) dados.getSerializable("login");

        textViewLoginUid.setText(login.getLoginUid());
        textViewNome.setText(login.getLoginNome());
        textViewEmail.setText(login.getLoginEmail());
        textViewStatus.setText(login.getLoginStatus());

    }
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfil, menu);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){

        configuraFirebase ();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

       String loginUid= user.getUid();
       String perfilUid="";
       String perfilImage="";
       String perfilEmail=user.getEmail();
       String perfilStatus=(getString(R.string.firebase_status,
               user.getEmail(), user.isEmailVerified()));
       String perfilNome=user.getDisplayName();
       String perfilEndereco="";
       String perfilCidade="";
       String perfilEstado="";
       String perfilCep="";
       String perfilLocalizacao="";
       String perfilTelefone="";
       perfilReference.getKey();


        Perfil perfil = new Perfil(loginUid,perfilUid,perfilImage,perfilEmail,perfilStatus,perfilNome,perfilEndereco, perfilCidade,perfilEstado,perfilCep,perfilLocalizacao,perfilTelefone);

       int id =item.getItemId();
        if(id == R.id.menu_voltar){
            Intent intentmenu = new Intent(PerfilUserActivity.this, MenuActivity.class);
            startActivity(intentmenu);

        } else if(id == R.id.menu_edit){
            Intent intentperfiledit = new Intent(PerfilUserActivity.this, PerfilEditUserActivity.class);

            intentperfiledit.putExtra("perfil", perfil);

            startActivity(intentperfiledit);

        }
        return true;
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
    }




}
