package me.zkx.IMAtDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zkx on 2017/1/6.
 */
public class SelectActivity extends AppCompatActivity {
    List<User> users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        users = new ArrayList<>();
        users.add(new User("123", "用户1"));
        users.add(new User("234", "User2"));
        users.add(new User("345", "用户@3"));

        findViewById(R.id.tv_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double d = Math.random();
                Intent intent = new Intent();
                intent.putExtra("result", users.get((int) (3 * d)));
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
