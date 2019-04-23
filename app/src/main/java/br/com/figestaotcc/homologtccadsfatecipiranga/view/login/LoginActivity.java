package br.com.figestaotcc.homologtccadsfatecipiranga.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Login;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.BaseActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.menu.MenuActivity;

public class LoginActivity extends BaseActivity {

    private Toolbar mToolbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference loginReference;
    // [START declare_auth]
    private FirebaseAuth mAuth;

    ListView listViewLogin;
    List<Login> viewLogin;

    EditText etSearchUsuario;
    TextInputLayout tilSearchUsuario;

    private void configuraFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        loginReference = firebaseDatabase.getReference("Login");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mToolbar = (Toolbar) findViewById(R.id.tb_main);
        setSupportActionBar(mToolbar);

        etSearchUsuario = (EditText) findViewById(R.id.etSearchUsuario);
        tilSearchUsuario = (TextInputLayout) findViewById(R.id.tilSearchUsuario);
        listViewLogin = (ListView) findViewById(R.id.listViewLogin);


        viewLogin = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        configuraFirebase();
        eventoEdit();

    }

    private void eventoEdit() {
        etSearchUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String usuario = etSearchUsuario.getText().toString().trim();
                pesquisarUsuario(usuario);
            }
        });
    }

    private void pesquisarUsuario(String usuario) {
        Query query;
        if (usuario.equals("")) {
            query = loginReference.orderByChild("loginNome");
        } else {
            query = loginReference.orderByChild("loginNome").startAt(usuario).endAt(usuario + "\uf8ff");

        }

        viewLogin.clear();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                viewLogin.clear();
                for (DataSnapshot loginSnapshot : dataSnapshot.getChildren()) {
                    Login login = loginSnapshot.getValue(Login.class);

                    viewLogin.add(login);
                }

                LoginView adapter = new LoginView(LoginActivity.this, viewLogin);
                listViewLogin.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        for (int i = 0; i < menu.size(); i++) {
            menu.getItem(i).setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_voltar) {
            Intent intentmenu = new Intent(LoginActivity.this, MenuActivity.class);
            startActivity(intentmenu);

        }
        return true;
    }


    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        pesquisarUsuario("");
    }

    public void onStart() {
        super.onStart();

    }

}
