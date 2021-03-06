package com.example.whgml.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener {
    private final int COUNT_BUTTON = 10;
    private Button[] numberBtns;
    private Button eqBtn;
    private Button acBtn;
    private Button ecBtn;
    private Button prevBtn;
    private Button nextBtn;
    private Button addBtn;
    private Button subBtn;
    private Button mulBtn;
    private Button divBtn;
    private Button startBlankBtn;
    private Button endBlankBtn;
    private Button dotBtn;
    private TextView formulaText;
    private TextView resultText;

    private Stack<CharSequence> formulaStack;
    private Stack<CharSequence> nextFormulaStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitActivity();
    }

    @Override
    public void onClick(View v) {
        StringBuilder builder = new StringBuilder();
        CharSequence result = resultText.getText();
        switch (v.getId()) {
            case R.id.oneBtn:
                builder.append(1);
                formulaText.append(builder);
                break;
            case R.id.twoBtn:
                builder.append(2);
                formulaText.append(builder);
                break;
            case R.id.threeBtn:
                builder.append(3);
                formulaText.append(builder);
                break;
            case R.id.fourBtn:
                builder.append(4);
                formulaText.append(builder);
                break;
            case R.id.fiveBtn:
                builder.append(5);
                formulaText.append(builder);
                break;
            case R.id.sixBtn:
                builder.append(6);
                formulaText.append(builder);
                break;
            case R.id.sevenBtn:
                builder.append(7);
                formulaText.append(builder);
                break;
            case R.id.eightBtn:
                builder.append(8);
                formulaText.append(builder);
                break;
            case R.id.nineBtn:
                builder.append(9);
                formulaText.append(builder);
                break;
            case R.id.zeroBtn:
                builder.append(0);
                formulaText.append(builder);
                break;
            case R.id.addBtn:
                if (result.length() > 0) {
                    formulaStack.push(formulaText.getText());
                    formulaText.setText(result);
                    result = result.subSequence(0, 0);
                    resultText.setText(result);
                    prevBtn.setEnabled(true);
                }
                builder.append('+');
                formulaText.append(builder);
                break;
            case R.id.subsBtn:
                if (result.length() > 0) {
                    formulaStack.push(formulaText.getText());
                    formulaText.setText(result);
                    result = result.subSequence(0, 0);
                    resultText.setText(result);
                    prevBtn.setEnabled(true);
                }
                builder.append('-');
                formulaText.append(builder);
                break;
            case R.id.multiplyBtn:
                if (result.length() > 0) {
                    formulaStack.push(formulaText.getText());
                    formulaText.setText(result);
                    result = result.subSequence(0, 0);
                    resultText.setText(result);
                    prevBtn.setEnabled(true);
                }
                builder.append('*');
                formulaText.append(builder);
                break;
            case R.id.divideBtn:
                if (result.length() > 0) {
                    formulaStack.push(formulaText.getText());
                    formulaText.setText(result);
                    result = result.subSequence(0, 0);
                    resultText.setText(result);
                    prevBtn.setEnabled(true);
                }
                builder.append('/');
                formulaText.append(builder);
                break;
            case R.id.startBlankBtn:
                builder.append('(');
                formulaText.append(builder);
                break;
            case R.id.endBlankBtn:
                builder.append(')');
                formulaText.append(builder);
                break;
            case R.id.acBtn:
                performACBtn();
                break;
            case R.id.ecBtn:
                performECBtn();
                break;
            case R.id.eqBtn:
                performEQBtn();
                break;
            case R.id.nextBtn:
                performNextBtn();
                break;
            case R.id.prevBtn:
                performPrevBtn();
                break;
            case R.id.dotBtn:
                builder.append(".");
                formulaText.append(builder);
                break;
        }
    }

    private void InitActivity() {
        numberBtns = new Button[COUNT_BUTTON];
        numberBtns[0] = (Button) findViewById(R.id.zeroBtn);
        numberBtns[1] = (Button) findViewById(R.id.oneBtn);
        numberBtns[2] = (Button) findViewById(R.id.twoBtn);
        numberBtns[3] = (Button) findViewById(R.id.threeBtn);
        numberBtns[4] = (Button) findViewById(R.id.fourBtn);
        numberBtns[5] = (Button) findViewById(R.id.fiveBtn);
        numberBtns[6] = (Button) findViewById(R.id.sixBtn);
        numberBtns[7] = (Button) findViewById(R.id.sevenBtn);
        numberBtns[8] = (Button) findViewById(R.id.eightBtn);
        numberBtns[9] = (Button) findViewById(R.id.nineBtn);
        eqBtn = (Button) findViewById(R.id.eqBtn);
        prevBtn = (Button) findViewById(R.id.prevBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        acBtn = (Button) findViewById(R.id.acBtn);
        ecBtn = (Button) findViewById(R.id.ecBtn);
        addBtn = (Button) findViewById(R.id.addBtn);
        subBtn = (Button) findViewById(R.id.subsBtn);
        mulBtn = (Button) findViewById(R.id.multiplyBtn);
        divBtn = (Button) findViewById(R.id.divideBtn);
        dotBtn = (Button) findViewById(R.id.dotBtn);

        startBlankBtn = (Button) findViewById(R.id.startBlankBtn);
        endBlankBtn = (Button) findViewById(R.id.endBlankBtn);

        formulaText = (TextView) findViewById(R.id.formulaText);
        resultText = (TextView) findViewById(R.id.resultText);
        formulaStack = new Stack<CharSequence>();
        nextFormulaStack = new Stack<CharSequence>();

        for (Button btn : numberBtns) {
            btn.setOnClickListener(this);
        }
        eqBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
        acBtn.setOnClickListener(this);
        ecBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        subBtn.setOnClickListener(this);
        mulBtn.setOnClickListener(this);
        divBtn.setOnClickListener(this);
        startBlankBtn.setOnClickListener(this);
        endBlankBtn.setOnClickListener(this);
        dotBtn.setOnClickListener(this);
    }

    private void performEQBtn() {
        StringBuilder text = new StringBuilder();
        try {
            Calculator calc = new Calculator(formulaText.getText());
            text.append(calc.getAnswer());
        }
        catch(Exception e)
        {
            text.append("ERR");
        }

        resultText.setText(text);
    }

    private void performPrevBtn() {
        CharSequence text = formulaText.getText();
        if (text.length() > 0) {
            nextFormulaStack.push(text);
            nextBtn.setEnabled(true);
        }
        formulaText.setText(formulaStack.pop());
        if (formulaStack.isEmpty()) {
            prevBtn.setEnabled(false);
        }
    }

    private void performNextBtn() {
        CharSequence text = formulaText.getText();
        if (text.length() > 0) {
            formulaStack.push(text);
            prevBtn.setEnabled(true);
        }
        formulaText.setText(nextFormulaStack.pop());
        if (nextFormulaStack.isEmpty()) {
            nextBtn.setEnabled(false);
        }
    }

    private void performACBtn() {
        CharSequence resText = resultText.getText();
        resText = resText.subSequence(0, 0);
        resultText.setText(resText);

        nextFormulaStack.clear();
        nextBtn.setEnabled(false);
        if (formulaText.getText().length() > 0) {
            formulaStack.push(formulaText.getText());
            prevBtn.setEnabled(true);

            CharSequence forText = formulaText.getText();
            forText = forText.subSequence(0, 0);
            formulaText.setText(forText);
        }
    }

    private void performECBtn() {
        resultText.clearComposingText();
        if (formulaText.length() > 0) {
            CharSequence string = formulaText.getText();
            string = string.subSequence(0, string.length() - 1);
            formulaText.setText(string);
        }
    }
}
