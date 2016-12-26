package kot.chatchawan.tutorialapplication;

import android.content.Intent;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CalculatorApp extends AppCompatActivity {
    BoxOperation boxOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_app);
        boxOperation = new BoxOperation();
    }

    public void nine(View view) {
        boxOperation.addNumber("9");
        this.showDisplay(view);
    }
    public void eight(View view) {
        boxOperation.addNumber("8");
        this.showDisplay(view);
    }
    public void seven(View view) {
        boxOperation.addNumber("7");
        this.showDisplay(view);
    }
    public void six(View view) {
        boxOperation.addNumber("6");
        this.showDisplay(view);
    }
    public void five(View view) {
        boxOperation.addNumber("5");
        this.showDisplay(view);
    }
    public void four(View view) {
        boxOperation.addNumber("4");
        this.showDisplay(view);
    }
    public void three(View view) {
        boxOperation.addNumber("3");
        this.showDisplay(view);
    }
    public void two(View view) {
        boxOperation.addNumber("2");
        this.showDisplay(view);
    }
    public void one(View view) {
        boxOperation.addNumber("1");
        this.showDisplay(view);
    }
    public void zero(View view) {
        boxOperation.addNumber("0");
        this.showDisplay(view);
    }
    public void dot(View view) {
        boxOperation.addNumber(".");
        this.showDisplay(view);
    }

    public void add(View view) {
        boxOperation.setOperation("add");
        this.showDisplay(view);
    }
    public void sub(View view) {
        boxOperation.setOperation("sub");
        this.showDisplay(view);
    }
    public void mul(View view) {
        boxOperation.setOperation("mul");
        this.showDisplay(view);
    }
    public void div(View view) {
        boxOperation.setOperation("div");
        this.showDisplay(view);
    }

    public void submit(View view) {
        boxOperation.doOperation();
        this.showDisplay(view);
    }

    public void allClear(View view) {
        boxOperation = new BoxOperation();
        this.showDisplay(view);
    }

    public void changeSign(View view) {
        boxOperation.doComplement();
        this.showDisplay(view);
    }

    public void sqrt(View view) {
        boxOperation.doSqrt();
        this.showDisplay(view);
    }

    public void showDisplay(View view) {
        TextView textView = (TextView) findViewById(R.id.resultCal);
        BoxNumber boxNumber = boxOperation.sendBoxToDisplay();
        String str = boxNumber.getBox();
        textView.setText(str);
    }
}

class BoxOperation {
    private BoxNumber boxNumber1;
    private BoxNumber boxNumber2;
    private BoxNumber temp;
    private String operation;
    private boolean isOperationChange;

    public BoxOperation() {
        boxNumber1 = new BoxNumber();
        boxNumber2 = null;
        temp = null;
        operation = "";
        isOperationChange = false;
    }

    public void doOperation() {
        Double firstOperand = Double.parseDouble(boxNumber1.getBox());
        Double secondOperand = null;
        if(boxNumber2==null) {
            if(temp == null) {
                if(isOperationChange)
                    secondOperand = firstOperand;
                else {
                    secondOperand = 0.0;
                }
            }
            else secondOperand = Double.parseDouble(temp.getBox());
        }
        else secondOperand = Double.parseDouble(boxNumber2.getBox());
        double result = 0;
        switch (operation) {
            case "add":
                result = firstOperand + secondOperand;
                break;
            case "sub":
                result = firstOperand - secondOperand;
                break;
            case "mul":
                result = firstOperand * secondOperand;
                break;
            case "div":
                result = firstOperand / secondOperand;
                break;
            default: result = firstOperand + 0;
        }
        if(boxNumber2 != null) temp = boxNumber2;
        else {
            if(isOperationChange) {
                temp = boxNumber1.clone();
                isOperationChange = false;
            }
        }
        boxNumber2 = null;
        boxNumber1.setBox(result);
        sendBoxToDisplay();
    }

    public void doSqrt() {
        BoxNumber tmp = null;
        if(boxNumber2 == null) tmp = boxNumber1;
        else tmp = boxNumber2;
        Double value = Double.parseDouble(tmp.getBox());
        value = Math.sqrt(value);
        tmp.setBox(value);
    }

    public void doComplement() {
        BoxNumber tmp = null;
        if(boxNumber2 == null) tmp = boxNumber1;
        else tmp = boxNumber2;
        Double value = -1.0*Double.parseDouble(tmp.getBox());
        tmp.setBox(value);
    }

    public void setOperation(String operation) {
        if(this.operation != operation) isOperationChange = true;
        else isOperationChange = false;
        this.operation = operation;
        if(boxNumber2 != null) doOperation();
    }

    public BoxNumber getBoxNumber() {
        return boxNumber1;
    }

    public void addNumber(String charEnd) {
        if(operation == "") {
            boxNumber1.appendChar(charEnd);
        } else {
            if(boxNumber2 == null) boxNumber2 = new BoxNumber();
            boxNumber2.appendChar(charEnd);
        }
        sendBoxToDisplay();
    }

    public BoxNumber sendBoxToDisplay() {
        if(boxNumber2 == null) return boxNumber1;
        return boxNumber2;
    }
}

class BoxNumber {
    private StringBuilder box;
    private boolean isZero;
    private boolean isFloating;

    public BoxNumber() {
        box = new StringBuilder();
        isZero = true;
        isFloating = false;
    }

    public void appendChar(String charEnd) {
        if(isFloating && charEnd == ".") return;
        switch (charEnd) {
            case ".": isFloating = true; isZero = false;
            case "0": if(isZero) {break;};
            default: isZero = false;
        }
        if(!isZero) {
            if(box.toString()=="" && charEnd == ".") box.append("0");
            box.append(charEnd);
        }
    }

    public String getBox() {
        if(box.length()== 0) return "0";
        return box.toString();
    }

    public void setBox(Double value) {
        String str = "";
        if(value != 0.0) {
            if(value.longValue()==value) str = Long.toString(value.longValue());
            else str = Double.toString(value);
        }
        box = new StringBuilder(str);
    }

    public BoxNumber clone() {
        BoxNumber obj = new BoxNumber();
        obj.box = this.box;
        obj.isZero = this.isZero;
        obj.isFloating = this.isFloating;
        return obj;
    }

}
