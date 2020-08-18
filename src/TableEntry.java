public class TableEntry<V, T> {
  private final V key;
  private final T value;
  private boolean removed;

  public TableEntry(V key, T value) {
    this.key = key;
    this.value = value;
  }

  public V getKey() {
    return key;
  }

  public T getValue() {
    return value;
  }

  public void remove() {
    removed = true;
  }

  public boolean isRemoved() {
    return removed;
  }
}
