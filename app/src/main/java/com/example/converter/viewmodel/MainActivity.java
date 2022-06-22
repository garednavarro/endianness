package com.example.converter.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.converter.R;
import com.example.converter.model.DecimalNumber;

/**
 * The MainActivity class is used as bridge between the View and the Model, it send to the model
 * class the user input to perform the convertion between decimal number and endianness value which
 * is displayed in the view.
 *
 * @author Mario Navarro
 * @since 21-06-2022
 * */
public class MainActivity extends AppCompatActivity {
    private EditText userInputNumber;
    private RadioButton bigEndianButton;
    private RadioButton littleEndianButton;
    private Button executeTaskButton;
    private TextView displayTextResult;
    private DecimalNumber decimalNumber;

    /**
     * This method is where the activity is initialized and the basic logic of the app
     * is executed that must occur just once.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initializeVariables();

        setRadioButtonSelectedByDefault();

        switchTheSelection();

        executeTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    decimalNumber = new DecimalNumber(Integer.parseInt(userInputNumber.getText().toString().trim()));
                    displayTextResult.setText(decimalNumber.mainPorcess(bigEndianButton));
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initializeVariables(){
        userInputNumber = (EditText) findViewById(R.id.inputNumber);
        bigEndianButton = (RadioButton) findViewById(R.id.bigendian);
        littleEndianButton = (RadioButton) findViewById(R.id.littleendian);
        executeTaskButton = (Button) findViewById(R.id.querybutton);
        displayTextResult = (TextView) findViewById(R.id.result);
    }

    private void setRadioButtonSelectedByDefault(){
        bigEndianButton.setChecked(true);
        bigEndianButton.setSelected(true);
    }

    private void switchTheSelection(){
        bigEndianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bigEndianButton.isSelected()){
                    littleEndianButton.setChecked(false);
                    littleEndianButton.setSelected(false);
                    bigEndianButton.setChecked(true);
                    bigEndianButton.setSelected(true);
                }

            }
        });

        littleEndianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!littleEndianButton.isSelected()){
                    bigEndianButton.setChecked(false);
                    bigEndianButton.setSelected(false);
                    littleEndianButton.setChecked(true);
                    littleEndianButton.setSelected(true);
                }
            }
        });
    }


}