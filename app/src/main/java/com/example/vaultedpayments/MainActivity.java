package com.example.vaultedpayments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clover.sdk.v1.Intents;

public class MainActivity extends AppCompatActivity {
  Button payButton;
  EditText amountET;
  Long amount;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    payButton = findViewById(R.id.pay_button);
    amountET = findViewById(R.id.amount);
    amountET.addTextChangedListener(amountTextWatcher);
    payButton.setOnClickListener(onPayButtonClick);
  }

  View.OnClickListener onPayButtonClick = new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      amount = Long.parseLong(amountET.getText().toString());
      try {
        Intent intent = new Intent(Intents.ACTION_SECURE_PAY);
        intent.putExtra(Intents.EXTRA_ORDER_ID, currentOrder.getId());
        intent.putExtra(Intents.EXTRA_AMOUNT, currentOrder.getTotal());
        intent.putExtra(Intents.EXTRA_TAX_AMOUNT, 0L);
        intent.putExtra(Intents.EXTRA_TIP_AMOUNT, 0L);

        startActivityForResult(intent, 1003);
      } catch (Exception e){
        e.printStackTrace();
      }
    }
  };

  TextWatcher amountTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      //wait
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      if (count>0) {
        payButton.setEnabled(true);
      }
    }

    @Override
    public void afterTextChanged(Editable s) {
      //do nothing
    }
  };

}
