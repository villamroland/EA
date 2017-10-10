package hu.vik.aut.android.recylerviewclassdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import hu.vik.aut.android.recylerviewclassdemo.R;
import hu.vik.aut.android.recylerviewclassdemo.data.Todo;
import hu.vik.aut.android.recylerviewclassdemo.touch.TodoTouchHelperAdapter;

public class TodoRecyclerAdapter extends
        RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder>
        implements TodoTouchHelperAdapter {

    private Context context;
    private List<Todo> todoList;

    public TodoRecyclerAdapter(Context context) {
        this.context = context;
        todoList = Todo.listAll(Todo.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row, parent, false);
        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Todo tmpTodo = todoList.get(position);

        holder.tvCreateDate.setText(tmpTodo.getCreateDate());
        holder.cbTodo.setText(tmpTodo.getTodo());
        holder.cbTodo.setChecked(tmpTodo.isDone());

        holder.cbTodo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean todoDone) {
                tmpTodo.setDone(todoDone);
                tmpTodo.save();
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void addTodo(String todo) {
        Todo newTodo = new Todo(new Date(System.currentTimeMillis()).toString(),
                todo,
                false);
        todoList.add(newTodo);

        newTodo.save();

        notifyDataSetChanged();
    }

    public void deleteTodo(int index) {
        Todo todo = todoList.get(index);
        todoList.remove(index);
        todo.delete();
        notifyItemRemoved(index);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(todoList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(todoList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        deleteTodo(position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCreateDate;
        private CheckBox cbTodo;


        public ViewHolder(View itemView) {
            super(itemView);

            tvCreateDate = itemView.findViewById(R.id.tvDate);
            cbTodo = itemView.findViewById(R.id.cbTodo);
        }
    }

}
