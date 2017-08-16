package examples.com.fireapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ValueEventListener
{
    private Context context;
    private EditText key,value;
    private Button sendDataButton;
    private TextView resultantTextView;

    Firebase firebaseDBReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        firebaseDBReference = new Firebase("https://fireapp-8237d.firebaseio.com/Employee");

        key = (EditText) findViewById(R.id.key);
        value = (EditText) findViewById(R.id.value);

        sendDataButton = (Button) findViewById(R.id.sendDataButton);

        resultantTextView = (TextView) findViewById(R.id.resultantTextView);

        sendDataButton.setOnClickListener(this);
        firebaseDBReference.addValueEventListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.sendDataButton:

                Firebase firebaseDBChildRefernce = firebaseDBReference.child(key.getText().toString());

                firebaseDBChildRefernce.setValue(value.getText().toString());

                break;
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot)
    {
        Map<String,String> map = dataSnapshot.getValue(Map.class);
        String Employee_Name = map.get("Employee Name");
        String Employee_Address = map.get("Employee Address");
        String Employee_ID = map.get("Employee ID");
        resultantTextView.setText(Employee_Name +"\n"+ Employee_Address +"\n"+ Employee_ID);
    }

    @Override
    public void onCancelled(FirebaseError firebaseError)
    {

    }
}
