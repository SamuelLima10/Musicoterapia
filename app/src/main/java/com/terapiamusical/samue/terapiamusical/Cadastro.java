package com.terapiamusical.samue.terapiamusical;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class Cadastro extends AppCompatActivity {

    private EditText editCadastroEmail, editCadastroSenha;
    private Button btnCadastroRegistrar, btnCadastroVoltar;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponentes();
        eventoClicks();
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void eventoClicks() {
        btnCadastroVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnCadastroRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String email = editCadastroEmail.getText().toString().trim();
                String senha = editCadastroSenha.getText().toString().trim();
                if(!email.equals("") && !senha.equals("")){
                    criaUser(email, senha);
                }else{
                    progressBar.setVisibility(View.INVISIBLE);
                    alert("Insira um email e uma senha");
                }

            }
        });
    }

    private void criaUser(String email, String senha) {
        auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            alert("Usuário cadastrado com sucesso");
                            Intent i = new Intent(Cadastro.this,Camera.class);
                            startActivity(i);
                            progressBar.setVisibility(View.INVISIBLE);
                            finish();
                        }else{
                            alert("Erro de Cadsatro");
                        }
                    }
                });
    }

    private void alert(String msg){
        Toast.makeText(Cadastro.this, msg,Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        editCadastroEmail = (EditText) findViewById(R.id.editCadastroEmail);
        editCadastroSenha = (EditText) findViewById(R.id.editCadastroSenha);
        btnCadastroRegistrar = (Button) findViewById(R.id.btnCadastroRegistrar);
        btnCadastroVoltar = (Button) findViewById(R.id.btnCadastroVoltar);
        progressBar = (ProgressBar) findViewById(R.id.progressBarCadastro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.back_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.back_page) {
            Intent i = new Intent(Cadastro.this, Login.class);
            startActivity(i);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
