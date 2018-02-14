package com.example.buttonedittext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * TODO: document your custom view class.
 */
public class ButtonEditText extends LinearLayout implements View.OnClickListener{

    private EditText email,passwd;
    private Button iniciar;
    private onButtonEditTextClickedListener mCallback = null;

    public ButtonEditText(Context context){
        super(context);
        init(null,0);
    }

    public ButtonEditText(Context context,AttributeSet attrs){
        super(context,attrs);
        init(attrs,0);
    }
    public ButtonEditText(Context context,AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        init(attrs,defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.button_edit_text,this);

        email = (EditText) findViewById(R.id.email);
        passwd = (EditText) findViewById(R.id.passwd);
        iniciar = (Button) findViewById(R.id.iniciar);

        iniciar.setOnClickListener(this);

        TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.ButtonEditText,defStyle,0);

        CharSequence f = a.getString(R.styleable.ButtonEditText_hintFirstText);
        if (f != null) { setFirstText(f.toString()); }

        CharSequence s = a.getString(R.styleable.ButtonEditText_hintSecondText);
        if (s != null) { setSecondText(s.toString()); }

        CharSequence t = a.getString(R.styleable.ButtonEditText_text);
        if (t != null) { setText(t.toString()); }

        int textSize = a.getDimensionPixelOffset(R.styleable.ButtonEditText_textSize,16);
        if(textSize > 0) { setTextSize(textSize); }

        a.recycle();
    }

    public void setText(String text){
        iniciar.setText(text);
        invalidate();
        requestLayout();
    }

    public void setFirstText(String text){
        email.setHint(text);
        invalidate();
        requestLayout();
    }

    public void setSecondText(String text){
        passwd.setHint(text);
        invalidate();
        requestLayout();
    }

    public void setTextSize(int size){
        email.setTextSize(size);
        passwd.setTextSize(size);
        invalidate();
        requestLayout();
    }


    public interface onButtonEditTextClickedListener{
        public void onButtonEditTextClicked(ButtonEditText source, String email, String passwd);
    }

    public void setOnButtonEditTextClickedListener(onButtonEditTextClickedListener listener){
        mCallback = listener;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        return true;
    }

    @Override
    public void onClick(View view) {

        if(!passwd.getText().toString().isEmpty()|| !email.getText().toString().isEmpty()){
            mCallback.onButtonEditTextClicked(this,email.getText().toString(),passwd.getText().toString());
        }else{
            Toast.makeText(getContext(),"Faltan Cosas",Toast.LENGTH_LONG).show();
        }


    }
}
