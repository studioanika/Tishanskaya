package com.example.mobiapp.newtishansjayaapp.classes;

import android.content.Context;

import com.example.mobiapp.newtishansjayaapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mobi app on 22.09.2017.
 */

public class GenerateTypePA {

    Context context;

    public GenerateTypePA(Context context) {
        this.context = context;
    }

    private TypePA getPA(){
        String name = context.getResources().getString(R.string.pa_name);
        String text = context.getResources().getString(R.string.pa_text);
        String desc = context.getResources().getString(R.string.pa_desc);
        TypePA typePA = new TypePA(name, text, desc);

        return typePA;
    }

    private TypePA getPAA(){
        String name = context.getResources().getString(R.string.pa_a_name);
        String text = context.getResources().getString(R.string.pa_a_text);
        String desc = context.getResources().getString(R.string.pa_a_desc);
        TypePA typePA = new TypePA(name, text, desc);

        return typePA;
    }

    private TypePA getA(){
        String name = context.getResources().getString(R.string.a_name);
        String text = context.getResources().getString(R.string.a_text);
        String desc = context.getResources().getString(R.string.a_desc);
        TypePA typePA = new TypePA(name, text, desc);

        return typePA;
    }

    private TypePA getM(){
        String name = context.getResources().getString(R.string.m_name);
        String text = context.getResources().getString(R.string.m_text);
        String desc = context.getResources().getString(R.string.m_desc);
        TypePA typePA = new TypePA(name, text, desc);

        return typePA;
    }

    public List<TypePA> getAllTypePA(){
        List<TypePA> list = new ArrayList<>();
        list.add(getPA());
        list.add(getPAA());
        list.add(getA());
        list.add(getM());

        return list;
    }
}
