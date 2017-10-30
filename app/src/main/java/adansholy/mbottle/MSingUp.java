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

public class MSingUp extends AppCompatActivity {
    private Button btnSave1;
    private EditText etName1;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etPassword2;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msing_up);
        etEmail=(EditText)findViewById(R.id.ETemail);
        btnSave1=(Button)findViewById(R.id.BTNSave);
        etPassword=(EditText) findViewById(R.id.ETpassword);
        etPassword2=(EditText) findViewById(R.id.ETpass2);
        btnSave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnSave1==view)

                    dataHandler();
            }
        });
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


//        auth = FirebaseAuth.getInstance();
//        firebaseUser = auth.getCurrentUser();
//        if (firebaseUser == null) {
//            // Not signed in, launch the Sign In activity
//            startActivity(new Intent(this, LogIn.class));
//            finish();
//            return;
//        } else {
//            String userName = firebaseUser.getDisplayName();
//            if (firebaseUser.getPhotoUrl() != null) {
//                String   photoUrl = firebaseUser.getPhotoUrl().toString();
//            }
//        }


    }
    //to deal with input data
    private void dataHandler()
    {
        String stEmail=etEmail.getText().toString();
        String stName=etName1.getText().toString();
        String stpassw=etPassword.getText().toString();
        String strepassw=etPassword2.getText().toString();
        boolean isOk=true;
        if(stEmail.length()==0|| stEmail.indexOf('@')<1)
        {
            etEmail.setError("wrongEmail");
            isOk=false;
        }
        if (stpassw.length()<8 || stpassw.equals(strepassw)==false)
        {
            etPassword.setError("Bad Password");
            isOk=false;
        }
        if (isOk)
        {
            creatAcount(stEmail,stpassw);
        }
    }


    private void creatAcount(String email,String passw){
        auth.createUserWithEmailAndPassword(email, passw).addOnCompleteListener(MSingUp.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Toast.makeText(MSingUp.this, "Authentication Successful", Toast.LENGTH_SHORT).show();
                    //updateUserProfile(task.getResult().getUser());
                    finish();
                }
                else
                {
                    Toast.makeText(MSingUp.this,"Authentication failed"+task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });




    }

}
