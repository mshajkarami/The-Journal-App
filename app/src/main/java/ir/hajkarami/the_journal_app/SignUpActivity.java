package ir.hajkarami.the_journal_app;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignUpActivity extends AppCompatActivity {
    // Widgets
    EditText password_create, username_create, email_create;
    Button createBTN;

    // Firebase Auth
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseUser currentUser;

    // Firebase Connection
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference mCollectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        createBTN = findViewById(R.id.acc_signUp_btn);
        password_create = findViewById(R.id.password_create);
        username_create = findViewById(R.id.username_create_ET);
        email_create = findViewById(R.id.email_create);

        // Fire Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        // Listening for changes in the authentication
        // state and responds accordingly when the
        // the state changes
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();
                if (currentUser != null) {
                    // user Already logged in
                } else {
                    // user not logged in
                }
            }
        };
        createBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (
                        !TextUtils.isEmpty(email_create.getText().toString())
                                && !TextUtils.isEmpty(password_create.getText().toString())
                                && !TextUtils.isEmpty(username_create.getText().toString())
                ){
                    String email = email_create.getText().toString();
                    String pass = password_create.getText().toString();
                    String username = username_create.getText().toString();
                    CreateUserEmailAccount(email, pass, username);
                }
            }
        });
    }

    private void CreateUserEmailAccount(
            String email,
            String pass,
            String username
    ) {
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(username)) {
            mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // the user is create successfully
                        Toast.makeText(SignUpActivity.this, "Account is create successfully", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}