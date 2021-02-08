package fr.isen.escousse.social

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        var loginBtn = findViewById<View>(R.id.login)
        var emailEt = findViewById<EditText>(R.id.editTextTextEmailAddress)
        var passwordEt = findViewById<EditText>(R.id.editTextTextPassword)
        loginBtn.setOnClickListener{
            var email : String = emailEt.text.toString()
            var password : String = passwordEt.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    Log.d("task", task.toString())
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(baseContext, "Authentication Success.",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Feed::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
        }
    }
}}

