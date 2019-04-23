package br.com.figestaotcc.homologtccadsfatecipiranga.view.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
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

public class PerfilEditUserActivity extends BaseActivity {

    private Toolbar mToolbar;

    ImageView imageViewImage;
    TextView textViewPerfilUid;
    TextView textViewLoginUid;
    TextView textViewNome;
    TextView textViewEmail;
    TextView textViewStatus;

    EditText etEnderecoEdit;
    TextInputLayout tilEnderecoEdit;
    EditText etCidadeEdit;
    TextInputLayout tilCidadeEdit;
    EditText etEstadoEdit;
    TextInputLayout tilEstadoEdit;
    EditText etCepEdit;
    TextInputLayout tilCepEdit;
    EditText etLocalizacaoEdit;
    TextInputLayout tilLocalizacaoEdit;
    EditText etTelefoneEdit;
    TextInputLayout tilTelefoneEdit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference perfilReference;
    // [START declare_auth]
    private FirebaseAuth mAuth;
    private int origin;

    private void configuraFirebase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        perfilReference = firebaseDatabase.getReference().child("PerfilUsuario");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiledit_current_user);

        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        configuraFirebase ();

        mToolbar = (Toolbar) findViewById( R.id.tb_main );
        setSupportActionBar( mToolbar );

        textViewPerfilUid = findViewById(R.id.uidPerfil);
        textViewLoginUid = findViewById(R.id.uidLogin);
        textViewNome = findViewById(R.id.nomePerfil);
        textViewEmail =findViewById(R.id.emailPerfil);
        textViewStatus = findViewById(R.id.statusPerfil);

        etEnderecoEdit = (EditText) findViewById(R.id.etEnderecoEdit);
        tilEnderecoEdit= (TextInputLayout) findViewById(R.id.tilEnderecoEdit);
        etCidadeEdit = (EditText) findViewById(R.id.etCidadeEdit);
        tilCidadeEdit = (TextInputLayout) findViewById(R.id.tilCidadeEdit);
        etEstadoEdit = (EditText) findViewById(R.id.etEstadoEdit);
        tilEstadoEdit = (TextInputLayout) findViewById(R.id.tilEstadoEdit);
        etCepEdit = (EditText) findViewById(R.id.etCepEdit);
        tilCepEdit = (TextInputLayout) findViewById(R.id.tilCepEdit);
        etLocalizacaoEdit = (EditText) findViewById(R.id.etLocalizacaoEdit);
        tilLocalizacaoEdit = (TextInputLayout) findViewById(R.id.tilLocalizacaoEdit);
        etTelefoneEdit = (EditText) findViewById(R.id.etTelefoneEdit);
        tilTelefoneEdit = (TextInputLayout) findViewById(R.id.tilTelefoneEdit);

        Intent intent = getIntent();
        origin = intent.getIntExtra("origin", 0);
        Bundle dados = getIntent().getExtras();
        Perfil perfil = (Perfil)dados.getSerializable("perfil");

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        etEnderecoEdit.setText(perfil.getPerfilEndereco());
        etCidadeEdit.setText(perfil.getPerfilCidade());
        etEstadoEdit.setText(perfil.getPerfilEstado());
        etCepEdit.setText(perfil.getPerfilCep());
        etTelefoneEdit.setText(perfil.getPerfilTelefone());
        perfilReference.child(perfil.getLoginUid()).child(perfil.getPerfilUid());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        FloatingActionButton fabSave = (FloatingActionButton) findViewById(R.id.fabPerfilEditConfirm);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEnderecoEdit.getText().toString().isEmpty()){
                   Snackbar.make(view, getString(R.string.error_categoria_name_empty), Snackbar.LENGTH_LONG)
                           .show();
                    tilEnderecoEdit.setError(getString(R.string.error_categoria_name_empty));
                } else {
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    updatePerfil(user);
                }
            }
        });

        FloatingActionButton fabDelete = (FloatingActionButton) findViewById(R.id.fabPerfilEditDelete);
        fabDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
                deletarPerfil(user);
                limparCampos();
            }
        });


            }

    private void setSupportActionBar(android.widget.Toolbar toolbar) {
    }


    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

        Bundle dados = getIntent().getExtras();
        Perfil perfil = (Perfil) dados.getSerializable("perfil");
        String perfilUid = perfil.getPerfilUid();
        String loginUid = currentUser.getUid();
        textViewPerfilUid.setText(perfilUid);
        textViewLoginUid.setText(loginUid);
        textViewNome.setText(getString(R.string.firebase_nome, currentUser.getDisplayName()));
        textViewEmail.setText(getString(R.string.firebase_email, currentUser.getEmail()));
        textViewStatus.setText(getString(R.string.firebase_status,
                currentUser.getEmail(), currentUser.isEmailVerified()));
        perfilReference.child(loginUid).getKey();

    }


 public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_perfiledit, menu);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
    }


    private void updatePerfil(FirebaseUser user){
        hideProgressDialog();
        Bundle dados = getIntent().getExtras();
        Perfil perfil = (Perfil) dados.getSerializable("perfil");

        if (user.getPhotoUrl() != null) {
            String perfilImage = user.getPhotoUrl().toString();
        }

        String loginUid = user.getUid();
        String perfilUid = (UUID.randomUUID().toString());

        perfil.setPerfilUid(perfil.getPerfilUid());
        perfil.setLoginUid(perfil.getLoginUid());
        perfil.setPerfilNome(user.getDisplayName());
        perfil.setPerfilEmail(user.getEmail());
        perfil.setPerfilStatus(getString(R.string.firebase_status,
                user.getEmail(), user.isEmailVerified()));
        perfil.setPerfilEndereco(etEnderecoEdit.getText().toString().trim());
        perfil.setPerfilCidade(etCidadeEdit.getText().toString().trim());
        perfil.setPerfilEstado(etEstadoEdit.getText().toString().trim());
        perfil.setPerfilCep(etCepEdit.getText().toString().trim());
        perfil.setPerfilLocalizacao(etLocalizacaoEdit.getText().toString().trim());
        perfil.setPerfilTelefone(etTelefoneEdit.getText().toString().trim());
        perfilReference.child(loginUid).push().setValue(perfil);


    }


    private void deletarPerfil(FirebaseUser user){
        hideProgressDialog();

        Perfil perfil = new Perfil();
        perfil.setPerfilUid(user.getUid());
        perfilReference.child(perfil.getPerfilUid()).removeValue();

    }


    private void limparCampos(){
        etEnderecoEdit.setText("");
        etCidadeEdit.setText("");
        etEstadoEdit.setText("");
        etCepEdit.setText("");
        etLocalizacaoEdit.setText("");
        etTelefoneEdit.setText("");
    }


    @Override
    public void onBackPressed() {
        close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home){
            close();
        }

        return super.onOptionsItemSelected(item);
    }

    private void close(){
        if (origin == 1) {
            Intent intent = new Intent(this, PerfilUserActivity.class);
            this.startActivity(intent);
            finish();
        } else {
            finish();
        }
    }



}
