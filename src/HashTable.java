public class HashTable<V, T> {
  private int size;
  private TableEntry[] table;

  public HashTable(int size) {
    this.size = size;
    table = new TableEntry[size];
  }

  private int findKey(V key) {
    int hash = Math.abs(key.hashCode()) % size;
    while (!(table[hash] == null || table[hash].getKey().equals(key))) {
      hash = (hash + 1) % size;
      if (hash == key.hashCode() % size) {
        return -1;
      }
    }
    return hash;
  }

  public boolean put(V key, T value) {
    int index = findKey(key);
    if (index == -1) {
      rehash();
      index = findKey(key);
    }
    table[index] = new TableEntry(key, value);
    return true;
  }

  public void remove(V key) {
    int index = findKey(key);
    if (index != -1 && table[index] != null) {
      table[index].remove();
    }
  }

  public T get(V key) {
    int index = findKey(key);
    if (index == -1 || table[index] == null) {
      return null;
    }
    return (T) table[index].getValue();
  }

  private void rehash() {
    TableEntry[] buffer = table;
    table = new TableEntry[buffer.length * 2];
    this.size = table.length;
    for (TableEntry entry : buffer) {
      this.put((V) entry.getKey(), (T) entry.getValue());
    }
  }

  public int getSize() {
    return size;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < table.length; i++) {
      if (table[i] == null) {
        sb.append(i + ": null");
      } else {
        sb.append(i + ": key = " + table[i].getKey()
            + ", value = " + table[i].getValue()
            + ", removed=" + table[i].isRemoved());
      }
      if (i < table.length - 1) {
        sb.append('\n');
      }
    }
    return sb.toString();
  }
}
