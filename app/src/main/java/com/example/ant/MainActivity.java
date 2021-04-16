package com.example.ant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText mInputText;    // ประกาศตัวแปร
    private TextView mUSDText;
    private TextView mGBPText;
    private TextView mEURText;
    private TextView mJPYText;
    private TextView mHKDText;
    private TextView mMYRText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        combineWidgets(); // เรียกใฃ้ฟังชั่น combineWidgets
    }

    private void combineWidgets() {     // สร้างฟังค์ฃั่น combine เป็น void (คือไม่มีรีเทิร์น)
        mInputText = /*(EditText)*/ findViewById(R.id.inputEditText); // สร้าง member (mInputText)  รับค่าประเภท EditText(=การ Casting) ของ inputEditText
        mUSDText = /*(TextView)*/ findViewById(R.id.textView_USD);      // ไม่ต้อง casting ก็ได้เพราะเรียกใช้จากชื่อ id โดยตรง
        mGBPText = (TextView) findViewById(R.id.textView_GBP);
        mEURText = (TextView) findViewById(R.id.textView_EUR);
        mJPYText = (TextView) findViewById(R.id.textView_JPY);
        mHKDText = (TextView) findViewById(R.id.textView_HKD);
        mMYRText = /*(TextView)*/ findViewById(R.id.textView_MYR);
    }

    public void onCliclConvet(View view) {  // กำหนดให้คลิกแล้วเกิดอะไรชึ้น ในโปรแกรมนี้คือทำฟังฃั่น convert
        mUSDText.setText(convert("USD"));   // กำหนดเมื่อกดแล้วให้ใช้ฟังชั่น convert แปลงเป็น usd **มโนฟังชั่นและตั้งฃื่อขึ้นมาก่อน
        mGBPText.setText(convert("GBP"));
        mEURText.setText(convert("EUR"));
        mJPYText.setText(convert("JPY"));
        mHKDText.setText(convert("HKD"));
        mMYRText.setText(convert("MYR"));
    }

    private String convert(String contryCode) {     // ทำฟังฃั่นแปลง เป็น String(จะต้องทำการ return ค่ากลับ)
        try {                           // ใฃ้ฟังฃั่น try เพราะถ้ามันเป็น empty มันจะ crash
            double exchageRate = 0;     // ตั้งค่าตั้งต้นเป็น 0
            double baht = Double.valueOf(mInputText.getText().toString());    // กำหนด baht ขึ้นมา จาก mImputText รับเข้ามาด้วย getText แล้วแปลงเป็น String

            if (contryCode.equals("USD")) {
                exchageRate = 31.2745;
            }       // กำหนดว่าถ้ามันเท่ากันจริง ให้ดึงไปค่า exchangeRate ไปใฃ้
            else if (contryCode.equals("GBP")) {
                exchageRate = 42.5931;
            } else if (contryCode.equals("EUR")) {
                exchageRate = 36.9920;
            } else if (contryCode.equals("JPY")) {
                exchageRate = 28.3111;
            } else if (contryCode.equals("HKD")) {
                exchageRate = 3.9964;
            } else if (contryCode.equals("MYR")) {
                exchageRate = 7.4826;
            } else {
                return "0";
            }

            double result = baht / exchageRate;     // สูตรคำนวณ
            //return String.valueOf(result);          // ตอนนี้มันจะ return ออกมาเป็นทศนิยมหลายตำแหน่งไม่สวยต้องจัดรูป
            DecimalFormat numFormatter = new DecimalFormat("#,###,###.00");
            return numFormatter.format(result);     // รีเทืร์นค่าที่จัดรูปไว้
        } catch (Exception other){return "00.00";}      // ถ้าใส่อย่างอื่นนอกจากสิ่งที่อยู่ใน try ให้มาลงที่ catch ให้ return 0 ให้หมด

    }
}