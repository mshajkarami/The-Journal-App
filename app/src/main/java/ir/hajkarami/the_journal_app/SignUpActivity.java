package ir.hajkarami.the_journal_app;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class SignUpActivity extends AppCompatActivity {
    // Widgets
    EditText password_create, username_create, email_create;
    Button createBTN ;

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
                if ( currentUser != null){
                    // user Already logged in
                }else {
                    // user not logged in
                }
            }
        };
    }
}