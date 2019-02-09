package cs.b07.p3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Regispage extends AppCompatActivity {

    Button cmpregisbtn;
    EditText lsname, fname, email,address, creditcnum,dob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regispage);

        lsname = (EditText) findViewById(R.id.lsinput);
        fname = (EditText) findViewById(R.id.fninput);
        email = (EditText) findViewById(R.id.inputem);
        address = (EditText) findViewById(R.id.inputaddress);
        creditcnum = (EditText) findViewById(R.id.inputpym);
        dob = (EditText) findViewById(R.id.inputdob);

        //cmpregisbtn.setOnClickListener(this);
    }

   /**public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cmpregisbtn:

                break;
        }
    }
    */
}
