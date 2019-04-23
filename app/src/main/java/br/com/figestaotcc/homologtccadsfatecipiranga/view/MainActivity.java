package br.com.figestaotcc.homologtccadsfatecipiranga.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Login;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Perfil;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.menu.MenuActivity;

//import androidx.support.annotation.NonNull;
//import androidx.support.annotation.Nullable;


public class MainActivity extends BaseActivity {
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 7117;
    private static final String TAG = "EmailPassword";



    FirebaseDatabase firebaseDatabase;
    DatabaseReference loginReference;
    DatabaseReference perfilReference;
    List<AuthUI.IdpConfig> providers;


    private void configuraFirebase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        loginReference = firebaseDatabase.getReference("Login");
        perfilReference = firebaseDatabase.getReference("PerfilUsuario");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        configuraFirebase ();

        mToolbar = (Toolbar) findViewById( R.id.tb_main );
        setSupportActionBar( mToolbar );

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        showSignInOptions();

    }

    public void showSignInOptions() {

        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setLogo(R.drawable.logo)
                .setTheme(R.style.AppTheme3)
                .build(), RC_SIGN_IN
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if(resultCode == RESULT_OK){
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
                if(user.isEmailVerified() == true){
                    mostrarMenu();
                 Toast.makeText(this, "Welcome " + user.getEmail(),Toast.LENGTH_SHORT).show();
                }else {
                    insertLogin(user);
                    insertPerfil(user);
                    sendEmailVerification();
                    signOut();
                    mostrarMain();
                }

            }else{
                Toast.makeText(this, ""+response.getError().getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();
    }


    private void insertLogin (FirebaseUser user) {

        if (user.getPhotoUrl() != null) {
            String loginImage = user.getPhotoUrl().toString();
           }
        String loginUid = user.getUid();
        String loginEmail = user.getEmail();
        String loginStatus = (getString(R.string.firebase_status,
                user.getEmail(), user.isEmailVerified()));
        String loginNome = user.getDisplayName();

        if (!TextUtils.isEmpty(loginEmail)) {
            Login login = new Login();

            if (user.getPhotoUrl() != null) {
                String loginImage = user.getPhotoUrl().toString();
                login.setLoginImage(loginImage);
            }else login.setLoginImage("");
            login.setLoginUid(loginUid);
            login.setLoginEmail(loginEmail);
            login.setLoginStatus(loginStatus );
            login.setLoginNome(loginNome);
            loginReference.child(loginUid).setValue(login);

           if (!TextUtils.isEmpty(loginEmail)) {

                Toast.makeText(MainActivity.this,
                        "Login added " + "Verification email sent to " + user.getEmail(),
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this,
                        "Failed to add Login ",
                        Toast.LENGTH_LONG).show();
            }
        }


    }

    private void insertPerfil (FirebaseUser user) {


           if (user.getPhotoUrl() != null) {
                String perfilImage = user.getPhotoUrl().toString();
            }
            String loginUid = user.getUid();
            String perfilUid = (UUID.randomUUID().toString());
          //  String perfilEmail = user.getEmail();
          // String perfilStatus = (getString(R.string.firebase_status,
          //          user.getEmail(), user.isEmailVerified()));
          //  String perfilNome = user.getDisplayName();

            if (!TextUtils.isEmpty(loginUid)) {
                Perfil perfil = new Perfil();
                perfil.setLoginUid(loginUid);
                perfil.setPerfilUid(perfilUid);
              //  perfil.setPerfilEmail(perfilEmail);
              //  perfil.setPerfilStatus(perfilStatus);
              //  perfil.setPerfilNome(perfilNome);
                perfilReference.child(loginUid).child(perfilUid).setValue(perfil);

            }
        }





   private void mostrarMenu() {
        Intent intentmenu = new Intent(MainActivity.this, MenuActivity.class);
        startActivity(intentmenu);
        finish();

    }

    public void closeApp(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(MainActivity.this);

    }

    private void mostrarMain() {
        Intent intentmain = new Intent(MainActivity.this, SplashScreenActivity.class);
        MainActivity.this.startActivity(intentmain);
        finish();
    }

    private void sendEmailVerification() {


        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

    }

    }









