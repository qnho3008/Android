package com.mobile.app.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mobile.app.MainActivity;
import com.mobile.app.R;
import com.mobile.app.model.Student;

public class InfoActivity extends AppCompatActivity {
    TextView name,id,studentClass,phone,grade,major;
    Button backBtn, callBtn, smsBtn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.infor_layout);

        init();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.info), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void init()
    {
        this.name = findViewById(R.id.name);
        this.id = findViewById(R.id.id);
        this.studentClass = findViewById(R.id.studentClass);
        this.phone = findViewById(R.id.phone);
        this.grade = findViewById(R.id.grade);
        this.major = findViewById(R.id.major);

        this.backBtn = findViewById(R.id.backBtn);
        this.callBtn = findViewById(R.id.callBtn);
        this.smsBtn = findViewById(R.id.smsBtn);

        this.imageView = findViewById(R.id.avatar);

        Student student = getIntent().getSerializableExtra("student", Student.class);

        this.backBtn.setOnClickListener(v -> switchIntent());
        this.callBtn.setOnClickListener(v -> call());
        this.smsBtn.setOnClickListener(v -> sms());

        byte[] byteArray = getIntent().getByteArrayExtra("image");
        if (byteArray != null) {
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            this.imageView.setImageBitmap(imageBitmap);
        }

        this.setInfo(student);
    }

    private void setInfo(Student student)
    {
            if(student == null)
            {
                return;
            }
            this.name.setText(student.getName());
            this.id.setText(student.getId());
            this.studentClass.setText(student.getStudentClass());
            this.phone.setText(student.getPhone());
            this.grade.setText(student.getGrade());
            this.major.setText(student.getMajor());
    }

    private void switchIntent()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + this.phone.getText().toString()));
        startActivity(intent);
    }

    private void sms() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + this.phone.getText().toString()));
        startActivity(intent);
    }


}
