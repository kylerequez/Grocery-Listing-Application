package com.itf3.group10.grocerylistapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.itf3.group10.grocerylistapplication.R;

public class CalculatorFragment extends Fragment implements View.OnClickListener{
    Button btnPercent, btnCE, btnC, btnDelete, btnSeven, btnEight, btnNine, btnDivide, btnFour, btnFive, btnSix, btnMultiply, btnOne, btnTwo, btnThree, btnSubtract, btnPoint, btnZero, btnAdd, btnEquals;
    TextView tvInput;
    float currentInput, previousInput = 0.0f;
    String current = "0", previous = "0";
    String operation = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        tvInput = view.findViewById(R.id.tvInput);
        tvInput.setText(current);

        btnPercent = view.findViewById(R.id.btnPercent);
        btnPercent.setOnClickListener(this::onClick);
        btnCE = view.findViewById(R.id.btnCE);
        btnCE.setOnClickListener(this::onClick);
        btnC = view.findViewById(R.id.btnC);
        btnC.setOnClickListener(this::onClick);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this::onClick);

        btnSeven = view.findViewById(R.id.btnSeven);
        btnSeven.setOnClickListener(this::onClick);
        btnEight = view.findViewById(R.id.btnEight);
        btnEight.setOnClickListener(this::onClick);
        btnNine = view.findViewById(R.id.btnNine);
        btnNine.setOnClickListener(this::onClick);
        btnDivide = view.findViewById(R.id.btnDivide);
        btnDivide.setOnClickListener(this::onClick);

        btnFour = view.findViewById(R.id.btnFour);
        btnFour.setOnClickListener(this::onClick);
        btnFive = view.findViewById(R.id.btnFive);
        btnFive.setOnClickListener(this::onClick);
        btnSix = view.findViewById(R.id.btnSix);
        btnSix.setOnClickListener(this::onClick);
        btnMultiply = view.findViewById(R.id.btnMultiply);
        btnMultiply.setOnClickListener(this::onClick);

        btnOne = view.findViewById(R.id.btnOne);
        btnOne.setOnClickListener(this::onClick);
        btnTwo = view.findViewById(R.id.btnTwo);
        btnTwo.setOnClickListener(this::onClick);
        btnThree = view.findViewById(R.id.btnThree);
        btnThree.setOnClickListener(this::onClick);
        btnSubtract = view.findViewById(R.id.btnSubtract);
        btnSubtract.setOnClickListener(this::onClick);

        btnPoint = view.findViewById(R.id.btnPoint);
        btnPoint.setOnClickListener(this::onClick);
        btnZero = view.findViewById(R.id.btnZero);
        btnZero.setOnClickListener(this::onClick);
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this::onClick);
        btnEquals = view.findViewById(R.id.btnEquals);
        btnEquals.setOnClickListener(this::onClick);

        loadTheme();
        return view;
    }

    @Override
    public void onClick(View v) {
            if (v.getId() == btnPercent.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Percent", Toast.LENGTH_LONG).show();
                if (!current.equals("0") && operation == null) {
                    current = String.valueOf(Float.valueOf(current) * 0.01f);
                    tvInput.setText(current);
                } else if(!current.equals("0") && operation != null){
                    currentInput = (Float.valueOf(current) * 0.01f) * Float.valueOf(previous);
                    current = String.valueOf((Float.valueOf(current) * 0.01f) * Float.valueOf(previous));

                    tvInput.setText(current);
                }
            } else if (v.getId() == btnCE.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "CE", Toast.LENGTH_LONG).show();
                current = "0";
                tvInput.setText(current);
            } else if (v.getId() == btnC.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "C", Toast.LENGTH_LONG).show();
                current = "0";
                currentInput = 0.0f;
                previousInput = 0.0f;
                operation = null;
                tvInput.setText(current);
            } else if (v.getId() == btnDelete.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
                if (!current.equals("0") && tvInput.getText().toString().length() >= 1) {
                    if (tvInput.getText().toString().length() > 1) {
                        current = tvInput.getText().toString().substring(0, tvInput.getText().toString().length() - 1);
                    } else if (tvInput.getText().toString().length() == 1){
                        current = "0";
                    }
                    tvInput.setText(current);
                }
            } else if (v.getId() == btnSeven.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Seven", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "7";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "7";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnEight.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Eight", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "8";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "8";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnNine.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Nine", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "9";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "9";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnDivide.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Divide", Toast.LENGTH_LONG).show();
                if(!current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "/";
                } else if(current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "/";
                } else {
                    if(previous.contains(".") || tvInput.getText().toString().contains(".")){
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf(performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "/";
                    } else {
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf((int)performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "/";
                    }
                }
            } else if (v.getId() == btnFour.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Four", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "4";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "4";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnFive.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Five", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "5";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "5";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnSix.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Six", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "6";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "6";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnMultiply.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Multiply", Toast.LENGTH_LONG).show();
                if(!current.equals("0") && operation == null){
                    previousInput = Float.valueOf(current);
                    previous = current;
                    current = "0";
                    operation = "*";
                } else if(current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "*";
                } else {
                    if(previous.contains(".") || tvInput.getText().toString().contains(".")){
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf(performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "*";
                    } else {
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf((int)performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "*";
                    }
                }
            } else if (v.getId() == btnOne.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "One", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "1";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "1";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnTwo.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Two", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "2";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "2";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnThree.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Three", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "3";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "3";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnSubtract.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Subtract", Toast.LENGTH_LONG).show();
                if(!current.equals("0")){
                    previousInput = Float.valueOf(current);
                    previous = current;
                    current = "0";
                    operation = "-";
                } else if(current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "-";
                } else {
                    if(previous.contains(".") || tvInput.getText().toString().contains(".")){
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf(performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "-";
                    } else {
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf((int)performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "-";
                    }
                }
            } else if (v.getId() == btnPoint.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Point", Toast.LENGTH_LONG).show();
                if(!current.contains(".") && tvInput.getText().toString().length() <= 7){
                    current = current + ".";
                    tvInput.setText(current);
                }
            } else if (v.getId() == btnZero.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Zero", Toast.LENGTH_LONG).show();
                if (current.equals("0")) {
                    current = "0";
                    currentInput = Float.valueOf(current);
                } else if (!current.equals("0") && tvInput.getText().toString().length() <= 7) {
                    current = current + "0";
                    currentInput = Float.valueOf(current);
                }
                tvInput.setText(current);
            } else if (v.getId() == btnAdd.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Add", Toast.LENGTH_LONG).show();
                if(!current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "+";
                } else if(current.equals("0") && operation == null){
                    previous = current;
                    previousInput = Float.valueOf(current);
                    current = "0";
                    currentInput = Float.valueOf(current);
                    operation = "+";
                } else {
                    if(previous.contains(".") || tvInput.getText().toString().contains(".")){
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf(performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "+";
                    } else {
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf((int)performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);

                        previous = current;
                        current = "0";
                        operation = "+";
                    }
                }
            } else if (v.getId() == btnEquals.getId()) {
                //Toast.makeText(v.getContext().getApplicationContext(), "Equals", Toast.LENGTH_LONG).show();
                if (previousInput != 0.0f && operation != null) {
                    if(current.contains(".") || previous.contains(".")){
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf(performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);
                        previous = null;
                    } else {
                        currentInput = Float.valueOf(tvInput.getText().toString());
                        current = String.valueOf((int)performOperation());

                        tvInput.setText(current);
                        previousInput = Float.valueOf(current);
                        previous = null;
                    }
                    operation = null;
                    //Toast.makeText(v.getContext().getApplicationContext(), "Current: " + currentInput, Toast.LENGTH_LONG).show();
                }
            }
        //Toast.makeText(v.getContext().getApplicationContext(), "Operation: " + operation, Toast.LENGTH_LONG).show();
    }

    public float performOperation(){
        float output = 0.0f;

        if(operation.equals("+")){
            output = previousInput + currentInput;
        } else if(operation.equals("-")){
            output = previousInput - currentInput;
        } else if(operation.equals("*")){
            output = previousInput * currentInput;
        } else if(operation.equals("/")){
            output = previousInput / currentInput;
        }

        return output;
    }

    public void loadTheme(){
        String theme = PreferenceManager.getDefaultSharedPreferences(this.getContext()).getString("prefTheme", "LIGHT");

        switch(theme){
            case "LIGHT":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case "DARK":
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
    }
}