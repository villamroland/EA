package hu.vik.aut.android.recylerviewclassdemo.data;

import com.orm.SugarRecord;

public class Todo extends SugarRecord {

    private String createDate;
    private String todo;
    private boolean done;

    public Todo(){
    }

    public Todo(String createDate, String todo, boolean done) {
        this.createDate = createDate;
        this.todo = todo;
        this.done = done;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
