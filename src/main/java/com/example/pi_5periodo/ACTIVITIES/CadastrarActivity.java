    package com.example.pi_5periodo.ACTIVITIES;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.EditText;
    import android.widget.Toast;

    import com.example.pi_5periodo.R;
    import com.google.android.gms.tasks.OnCompleteListener;
    import com.google.android.gms.tasks.Task;
    import com.google.firebase.auth.AuthResult;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;

    public class CadastrarActivity extends AppCompatActivity {

        private EditText editLogin, editSenha;
        private FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastrar);
            editLogin = findViewById(R.id.editLogin);
            editSenha = findViewById(R.id.editSenha);

            mAuth = FirebaseAuth.getInstance();
        }

        public void Cadastrar(View view) {
            String login = editLogin.getText().toString();
            String senha = editSenha.getText().toString();

            mAuth.createUserWithEmailAndPassword(login, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            }
                            else{
                                Toast.makeText(CadastrarActivity.this, "Falha ao cadastrar. " + task.getException(), Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }

        private void updateUI(FirebaseUser user) {
            if (user != null){
                Intent dashBoard = new Intent(CadastrarActivity.this, DashActivity.class);
                startActivity(dashBoard);
                finish();
            }
        }
    }
