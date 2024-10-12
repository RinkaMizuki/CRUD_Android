package rin.site.crud_android;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    private static int id = 2;
    private StudentListViewAdapter studentListViewAdapter;
    private ListView listViewStudent;
    private Button btn_delete;
    private Button btn_create;
    private EditText input_name;
    private EditText input_class;
    private EditText input_code;
    private  EditText input_id;
    private ArrayList<Student> listStudent; //Mảng dữ liệu sản phẩm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Khoi tao ListProduct, tự sinh một số dữ liệu
        listStudent = new ArrayList<Student>();
        listStudent.add(new Student(1, "Iphone 6","DH52107825" ,"D21_TH04"));
        listStudent.add(new Student(2, "Iphone 7", "DH52108834", "D21_TH02"));
        studentListViewAdapter = new StudentListViewAdapter(listStudent);

        onControls();
        onEvents();
    }

    private void onControls() {
        btn_delete = findViewById(R.id.btn_delete);
        btn_create = findViewById(R.id.btn_create);
        input_name = findViewById(R.id.input_name);
        input_class = findViewById(R.id.input_class);
        input_code = findViewById(R.id.input_code);
        input_id = findViewById(R.id.input_id);
        listViewStudent = findViewById(R.id.list_item);
        listViewStudent.setAdapter(studentListViewAdapter);
    }

    private void resetData() {
        input_name.setText("");
        input_code.setText("");
        input_class.setText("");
    }

    private void onEvents() {
        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) studentListViewAdapter.getItem(position);
                input_class.setText(student.get_class());
                input_code.setText(student.get_code());
                input_name.setText(student.get_name());
                input_id.setText(String.valueOf(student.get_id()));
                //Làm gì đó khi chọn sản phẩm (ví dụ tạo một Activity hiện thị chi tiết, biên tập ..)
                Toast.makeText(MainActivity.this, student.get_name(), Toast.LENGTH_LONG).show();
            }
        });

        btn_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input_name.getText().toString();
                String code = input_code.getText().toString();
                String classRoom = input_class.getText().toString();
                int studentId = Integer.parseInt(input_id.getText().toString());
                Student currStudent = null;

                // Find the student by ID
                for (int i = 0; i < listStudent.size(); i++) {
                    Student findStudent = listStudent.get(i);
                    if (findStudent.get_id() == studentId) {
                        currStudent = findStudent;
                        break;  // Break instead of return to continue with the logic
                    }
                }

                // Check if fields are empty
                if (name.isEmpty() || code.isEmpty() || classRoom.isEmpty()) {
                    Toast toast = Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    return;
                }

                // Update or add student
                if (currStudent != null) {
                    currStudent.set_name(name);
                    currStudent.set_class(classRoom);
                    currStudent.set_code(code);
                    Toast toast = Toast.makeText(MainActivity.this, "Sửa sinh viên thành công", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else {
                    Student newStudent = new Student(++id, name, classRoom, code);
                    listStudent.add(newStudent);
                    Toast toast = Toast.makeText(MainActivity.this, "Thêm sinh viên thành công", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }

                // Notify adapter of data change and reset form
                studentListViewAdapter.notifyDataSetChanged();
                resetData();
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listStudent.size() > 0) {
                    //Xoá phần tử đầu tiên của danh sách
                    int studentPos = 0;
                    listStudent.remove(studentPos);
                    //Thông báo cho ListView biết dữ liệu đã thay đổi (cập nhật, xoá ...)
                    studentListViewAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(MainActivity.this, "Danh sách sinh viên trống", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}