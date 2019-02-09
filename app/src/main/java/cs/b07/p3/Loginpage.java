package cs.b07.p3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class Loginpage extends AppCompatActivity implements View.OnClickListener {

    Button loginbtn;
    EditText Username, Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        Username = (EditText) findViewById(R.id.inputname);
        Password = (EditText) findViewById(R.id.inputps);
        loginbtn = (Button) findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginbtn: //when the button is click

                break;
        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    public void regispg(View view) {
        Intent intent = new Intent(this, Regispage.class);
        startActivity(intent);
    }

}
