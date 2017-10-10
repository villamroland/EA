package hu.vik.aut.android.recylerviewclassdemo.touch;

public interface TodoTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}

