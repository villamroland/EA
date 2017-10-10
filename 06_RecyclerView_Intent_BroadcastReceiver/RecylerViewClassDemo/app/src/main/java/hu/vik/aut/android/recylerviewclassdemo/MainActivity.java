package hu.vik.aut.android.recylerviewclassdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.vik.aut.android.recylerviewclassdemo.adapter.TodoRecyclerAdapter;
import hu.vik.aut.android.recylerviewclassdemo.touch.TodoItemTouchHelperCallback;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etTodo)
    EditText etTodo;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    TodoRecyclerAdapter todoRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        todoRecyclerAdapter = new TodoRecyclerAdapter(this);

        recyclerView.setHasFixedSize(true);
        // RecyclerView layout manager
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(todoRecyclerAdapter);

        ItemTouchHelper.Callback callback =
                new TodoItemTouchHelperCallback(todoRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    @OnClick(R.id.btnAdd)
    public void addClick(Button btnAdd) {
        if (!TextUtils.isEmpty(etTodo.getText())) {
            todoRecyclerAdapter.addTodo(etTodo.getText().toString());
            etTodo.setText("");
        } else {
            etTodo.setError(getString(R.string.error_empty));
        }
    }

}
