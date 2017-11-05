package adansholy.mbottle;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MSignIn extends AppCompatActivity implements View.OnClickListener {
    private EditText etPass1;
    private EditText etEmail1;
    private Button btnLogin;
    private Button btnSignin;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msign_in);
        btnLogin = (Button) findViewById(R.id.BTsignin);
        btnSignin = (Button) findViewById(R.id.khalil);
        etEmail1 = (EditText) findViewById(R.id.ETusername);
        etPass1 = (EditText) findViewById(R.id.ETpassword);
        btnLogin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


    }

    private void dataHandler() {
        String email = etEmail1.getText().toString();
        String passw = etPass1.getText().toString();
        boolean isOk = true;//to check id all feilds are filled correct!!!!
        if (email.length() == 0 || email.indexOf('@') < 1) {
            etEmail1.setError("Wrong Email");
            isOk = false;
        }
        signIn(email, passw);

    }

    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(passw, email).addOnCompleteListener(MSignIn.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MSignIn.this, "signIn Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), MainListActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(MSignIn.this, "signIn failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
            if (view == btnLogin) {
                dataHandler();
            }


            if (view == btnSignin) {
                Intent intent = new Intent(this, MainListActivity.class);
                startActivity(intent);
            }

        }
    }

