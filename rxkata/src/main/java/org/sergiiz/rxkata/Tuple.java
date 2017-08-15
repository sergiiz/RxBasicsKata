package org.sergiiz.rxkata;

public final class Tuple<F, S> {
    private final F first;
    private final S second;

    private Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Tuple<F, S> of(F first, S second) {
        return new Tuple<>(first, second);
    }

    @Override
    public String toString() {
        return "Tuple{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (first != null ? !first.equals(tuple.first) : tuple.first != null) return false;
        return second != null ? second.equals(tuple.second) : tuple.second == null;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
