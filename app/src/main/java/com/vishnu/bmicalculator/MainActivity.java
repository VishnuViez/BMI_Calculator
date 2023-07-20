package com.vishnu.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button calculateButton;
    private TextView resultText;
    private EditText weightEdit;
    private EditText inchesEdit;
    private EditText feetEdit;
    private EditText ageEdit;
    private RadioButton radioFemale;
    private RadioButton radioMale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        radioMale = findViewById(R.id.radio_button_male);
        radioFemale = findViewById(R.id.radio_button_female);
        ageEdit = findViewById(R.id.edit_text_age);
        feetEdit = findViewById(R.id.edit_text_feet);
        inchesEdit = findViewById(R.id.edit_text_inches);
        weightEdit = findViewById(R.id.edit_text_weight);
        calculateButton = findViewById(R.id.button_calculate);
        resultText = findViewById(R.id.text_view_result);

    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double bmiResult = calculateBmi();

                String ageText = ageEdit.getText().toString();
                int age = Integer.parseInt(ageText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }
        });
    }

    private double calculateBmi() {
        String feetText = feetEdit.getText().toString();
        String inchesText = inchesEdit.getText().toString();
        String weightText = weightEdit.getText().toString();

        //converting the number 'Strings' into 'int' variables
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchesText);
        int weight = Integer.parseInt(weightText);

        int totalInches = (feet * 12) + inches;

        //Height in meters is the inches multiplied by 0.0254
        double heightInMeters = totalInches * 0.0254;

        // BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + "You are underweight";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + "You are overweight";
        } else {
            //Display healthy
            fullResultString = bmiTextResult + "You are a healthy weight";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
    DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
    String bmiTextResult = myDecimalFormatter.format(bmi);

    String fullResultString;

    if (radioMale.isChecked()) {
        //Display boy guidance
        fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for boys";
    } else if (radioFemale.isChecked()){
        //Display girl guidance
        fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for girls";
    } else {
        //Display general guidance
        fullResultString = bmiTextResult + "- As you are under 18, please consult with your doctor for the healthy range for healthy range";
    }
    resultText.setText(fullResultString);
    }
}