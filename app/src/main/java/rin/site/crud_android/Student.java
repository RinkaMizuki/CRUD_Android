package rin.site.crud_android;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class Student {
    private int _id;
    private String _name;
    private String _class;
    private String _code;

    public Student(int _id, String _name, String _class, String _code) {
        this._name = _name;
        this._class = _class;
        this._code = _code;
        this._id = _id;
    }

    public Student() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void set_code(String _code) {
        this._code = _code;
    }

    public String get_name() {
        return _name;
    }

    public String get_class() {
        return _class;
    }

    public String get_code() {
        return _code;
    }
}
