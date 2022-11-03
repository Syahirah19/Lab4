package bits.mobile.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class Registration extends AppCompatActivity {

    DatePickerDialog datePicker;
    ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.editBirthdate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View view, boolean b) {
                fnInvokeDatePicker();
            }
        });

        binding.editBirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fnInvokeDatePicker();
            }
        });

        binding.fabAddUser.setOnClickListener(this::fnAddUser);
    }

    private void fnInvokeDatePicker() {
        final Calender cldr = Calender.getInstance();
        int day = cldr.get(Calender.DAY_OF_MONTH);
        int month = cldr.get(Calender.MONTH);
        int year = cldr.get(Calender.YEAR);

        datePicker = new DatePickerDialog(RegistrationActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        binding.editBirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        datePicker.show();
    }

    private void fnAddUser(View view)
    {
        String strFullName = binding.edtFullName.getText().toString();
        String strPwd = binding.edtPwd.getText().toString();
        String strEmail = binding.edtEmail.getText().toString();
        String strBirth = binding.editBirthdate.getText().toString();
        String strAddress = binding.edtAddress.getText().toString();
        String strGender = "";

        if (binding.rbMale.isChecked())
            strGender = binding.rbMale.getText().toString();
        else if (binding.rbFemale.isChecked())
            strGender = binding.rbFemale.getText().toString();

        User user = new User (strFullName, strPwd, strAddress, strEmail, strBirth, strGender);
        Intent intent = new Intent(this. SecondActiviti.class);
        intent.putExtra("objUser", user);

        startActivity(intent);

    }
}
