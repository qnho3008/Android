package com.mobile.app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.activity.InfoActivity;
import com.mobile.app.model.Student;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText id;
    private EditText studentClass;
    private EditText phone;
    private RadioGroup gradeRadioGroup,majorRadioGroup;
    private Button submitBtn;
    private ImageButton cameraBtn;

    private static final int REQUEST_IMAGE_CAPTURE = 1888;
    private Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.form_layout);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init ()
    {
        this.name = findViewById(R.id.name);
        this.id = findViewById(R.id.id);
        this.studentClass = findViewById(R.id.studentClass);
        this.phone = findViewById(R.id.phone);

        this.gradeRadioGroup = findViewById(R.id.gradeRadioGroup);
        this.majorRadioGroup = findViewById(R.id.majorRadioGroup);

        this.submitBtn = findViewById(R.id.submitBtn);
        this.cameraBtn = findViewById(R.id.avatar);

        this.submitBtn.setOnClickListener(v -> submit());
        this.cameraBtn.setOnClickListener(v -> takePicture());
    }

    private String getMajor(RadioGroup group)
    {
        int checkedId = group.getCheckedRadioButtonId();

        if (checkedId == -1) {
            return "No major selected";
        }

        RadioButton checkedRadioButton = group.findViewById(checkedId);
        return checkedRadioButton.getContentDescription() != null ?
                checkedRadioButton.getContentDescription().toString() : "Unknown major";
    }

    private String getGrade(RadioGroup group)
    {
        int checkedId = group.getCheckedRadioButtonId();

        if (checkedId == -1) {
            return "No grade selected";
        }

        RadioButton checkedRadioButton = group.findViewById(checkedId);
        return checkedRadioButton.getContentDescription() != null ?
                checkedRadioButton.getContentDescription().toString() : "Unknown grade";
    }

    private void submit()
    {
        String name = this.name.getText().toString();
        String id = this.id.getText().toString();
        String studentClass = this.studentClass.getText().toString();
        String phone = this.phone.getText().toString();

        String major = getMajor(this.majorRadioGroup);
        String grade = getGrade(this.gradeRadioGroup);

        Student st = new Student(name,id,studentClass,phone,grade,major);
        switchIntent(st);
    }

    private void takePicture() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            cameraBtn.setImageBitmap(imageBitmap);
        }
    }

    private void switchIntent(Student st) {
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("student", st);

        if (imageBitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            intent.putExtra("image", byteArray);
        }

        startActivity(intent);
    }
}