package rin.site.crud_android;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentListViewAdapter extends BaseAdapter {

    final ArrayList<Student> listStudent;

    StudentListViewAdapter(ArrayList<Student> listStudent) {
        this.listStudent = listStudent;
    }
    @Override
    public int getCount() {
        return listStudent.size();
    }

    @Override
    public Object getItem(int position) {
        return listStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listStudent.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewStudent;
        if(convertView == null) {
            viewStudent = View.inflate(parent.getContext(), R.layout.layout_student, null);
        }
        else {
            viewStudent = convertView;
        }
        Student student = (Student) getItem(position);
        ((TextView) viewStudent.findViewById(R.id.student_name)).setText(String.format("Họ tên = %s", student.get_name()));
        ((TextView) viewStudent.findViewById(R.id.student_code)).setText(String.format("MSSV : %s", student.get_code()));
        ((TextView) viewStudent.findViewById(R.id.student_class)).setText(String.format("Lớp %s", student.get_class()));


        return viewStudent;
    }
}
