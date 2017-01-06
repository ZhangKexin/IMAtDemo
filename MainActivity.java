package me.zkx.IMAtDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    TextView tvSelectedUsers;
    CursorEdittext etContent;
    Set<User> userSet;
    Set<String> ids;

    private void setupViews() {
        etContent.addTextChangedListener(new TextWatcher() {

            private void checkDeleteUser(CharSequence s) {
                Set<User> toDelete = new HashSet<>();
                Set<String> toDeleteIds = new HashSet<>();
                for (User user : userSet) {
                    if (!s.toString().contains(user.name)) {
                        if (s.toString().indexOf("@" + user.name.substring(1)) > -1) {
                            chooseUser();
                        }
                        toDeleteIds.add(user.id);
                        toDelete.add(user);
                    }
                }
                userSet.removeAll(toDelete);
                ids.removeAll(toDeleteIds);
                tvSelectedUsers.setText(userSet.toString());
            }

            private void checkDeleteAtNext() {

            }

            private void checkInputAtSymbol(CharSequence s, int start, int count) {
                if (count == 1 && s.charAt(start) == '@') {
                    chooseUser();
                }
            }

            private void chooseUser() {
                startActivityForResult(new Intent(MainActivity.this, SelectActivity.class), 1);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkDeleteUser(s);
                checkDeleteAtNext();
                checkInputAtSymbol(s, start, count);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || requestCode != 1 || data == null) return;

        User user = (User) data.getSerializableExtra("result");
        if (ids.add(user.id)) {
            userSet.add(user);
        }

        String origin = etContent.getText().toString();
        int index = etContent.getCursorIndex();
        etContent.setText(origin.substring(0, index) + user.name + origin.substring(index));
        tvSelectedUsers.setText(userSet.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        findViews();
        setupViews();
    }

    private void init() {
        userSet = new HashSet<>();
        ids = new HashSet<>();
    }

    private void findViews() {
        etContent = (CursorEdittext) findViewById(R.id.et_content);
        tvSelectedUsers = (TextView) findViewById(R.id.tv_selected_users);
    }
}
