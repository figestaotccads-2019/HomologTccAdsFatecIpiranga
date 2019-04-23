package br.com.figestaotcc.homologtccadsfatecipiranga.view.menu;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import br.com.figestaotcc.homologtccadsfatecipiranga.R;
import br.com.figestaotcc.homologtccadsfatecipiranga.fragments.AboutFragment;
import br.com.figestaotcc.homologtccadsfatecipiranga.fragments.HomeFragment;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Login;
import br.com.figestaotcc.homologtccadsfatecipiranga.model.Perfil;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.BaseActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.MainActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.MapsActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.login.LoginActivity;
import br.com.figestaotcc.homologtccadsfatecipiranga.view.perfil.PerfilUserActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.UUID;

public class MenuActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {


    private final int PERMISSION_REQUEST_CODE = 0;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private RelativeLayout content_menu;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference perfilReference;
    DatabaseReference loginReference;
    List<AuthUI.IdpConfig> providers;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;

    private void configuraFirebase (){
        firebaseDatabase = FirebaseDatabase.getInstance();
        perfilReference = firebaseDatabase.getReference().child("PerfilUsuario");
        loginReference = firebaseDatabase.getReference("Login");


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = (Toolbar) findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);
       //View navView = navigationView.inflateHeaderView(R.layout.nav_header_menu);
        navigationView.setNavigationItemSelectedListener(this);

        postList = (RecyclerView) findViewById(R.id.all_user_post_list);


        Fragment fragment = new HomeFragment();
        changeFragment(fragment);

        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
        configuraFirebase();

   }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }




    @Override
    public void onStart() {
        super.onStart();
        configuraFirebase ();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
        insertLogin(user);
        submitPerfil(user);
            }

    private void updateUI(FirebaseUser user) {
        hideProgressDialog();

    }

    private void insertLogin (FirebaseUser user){
        hideProgressDialog();


        Login login = new Login();
        if (user.getPhotoUrl() != null) {
            login.setLoginImage(user.getPhotoUrl().toString());
        }
        login.setLoginUid(user.getUid());
        login.setLoginNome(user.getDisplayName());
        login.setLoginEmail(user.getEmail());
        login.setLoginStatus(getString(R.string.firebase_status,
                user.getEmail(), user.isEmailVerified()));
        loginReference.child(login.getLoginUid()).setValue(login);

    }

    private void submitPerfil(FirebaseUser user) {
        // [START single_value_read]
        Perfil perfil = new Perfil();
        final String perfilUid = perfil.setPerfilUid(user.getUid());

        perfilReference.child(perfilUid).addListenerForSingleValueEvent( new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        Perfil perfil = dataSnapshot.getValue(Perfil.class);

                        // [START_EXCLUDE]
                        if (perfil== null) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            insertPerfil (user);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
}


    private void insertPerfil (FirebaseUser user) {

        String loginUid = user.getUid();
        String perfilUid = (UUID.randomUUID().toString());

        if (!TextUtils.isEmpty(loginUid)) {
            Perfil perfil = new Perfil();
            perfilReference.child(loginUid).push().setValue(perfil);

        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        configuraFirebase ();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);


        String loginImage = "";
        String loginUid = user.getUid();
        String loginNome = user.getDisplayName();
        String loginEmail = user.getEmail();
        String loginStatus = (getString(R.string.firebase_status,
                user.getEmail(), user.isEmailVerified()));
        loginReference.child(loginUid);


        Login login = new Login(loginImage,loginUid,loginEmail,loginStatus,loginNome);




        Intent intent = null;
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.nav_feed:
                if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                break;
            case R.id.nav_login: {
                intent = new Intent(this, LoginActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.nav_perfil: {
                intent = new Intent(this, PerfilUserActivity.class);
                intent.putExtra("login", login);
                this.startActivity(intent);
                break;
            }
            case R.id.nav_categoria:{
                Toast.makeText(this,"Categoria",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_produtos:{
                Toast.makeText(this,"Produtos",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_estabelecimento:{
                Toast.makeText(this,"Estabelecimento",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_novalistacompras:{
                Toast.makeText(this,"Lista de Compras",Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.nav_oferta:{
                Toast.makeText(this,"Oferta",Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.nav_sobre:
                fragment = new AboutFragment();
                changeFragment(fragment);
                break;

            case R.id.nav_share:
                Intent sharingIntent = new Intent();
                sharingIntent.setAction(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message));
                startActivity(Intent.createChooser(sharingIntent,getString(R.string.share_using)));
                break;

            case R.id.nav_logout:
                signOut();
                break;

            case R.id.nav_close:
                closeApp();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }


    private void changeFragment(Fragment fragment) {
        FrameLayout layout = (FrameLayout) findViewById(R.id.flContainer);
        layout.removeAllViewsInLayout();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.flContainer, fragment)
                .disallowAddToBackStack()
                .commit();
    }

    public void viewMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        this.startActivity(intent);
    }

    public void callMe(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:50610298"));

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CODE);
        } else {
            this.startActivity(intent);
        }

    }

    public void voltarFeed(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        this.startActivity(intent);
    }


   public void signOut() {
        AuthUI.getInstance()
                .signOut(MenuActivity.this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       Intent intentmain = new Intent(MenuActivity.this, MainActivity.class);
                        MenuActivity.this.startActivity(intentmain);
                        finish();
                    }
                });


    }

    public void closeApp(){
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


}