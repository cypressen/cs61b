package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size(); i += 1) {
            if (c.compare(this.get(i), this.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return this.get(maxIndex);
    }
    public T max() {
        return max(c);
    }

}
