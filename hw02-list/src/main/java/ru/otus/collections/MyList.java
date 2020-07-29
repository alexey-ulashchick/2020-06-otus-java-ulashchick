package ru.otus.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyList<E> implements List<E> {

  private static final int DEFAULT_SIZE = 8;
  private static final int GROW_FACTOR = 2;
  private static final float SHRINK_FACTOR = 0.25f;
  private static final String EXCEPTION_MESSAGE = "Not implemented yet.";

  /**
   * Internal array for holding elements of the list. Will grow and shrink according to {@value
   * GROW_FACTOR} and {@value SHRINK_FACTOR}.
   */
  private Object[] array = new Object[DEFAULT_SIZE];

  // Public size of the list. Internally holds pointer to next insertion position.
  private int size = 0;

  // Holds amount of nulls inserted into the list.
  private int nulls = 0;

  @Override
  public int size() {
    return this.size;
  }

  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  @Override
  public boolean contains(Object o) {
    if (o == null) {
      return nulls > 0;
    }

    for (int i = 0; i < size; i++) {
      if (array[i] == o) {
        return true;
      }
    }

    return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new Iterator<E>() {
      private int i = 0;

      @Override
      public boolean hasNext() {
        return i < size;
      }

      @Override
      public E next() {
        if (i == size) {
          throw new NoSuchElementException("No more elements in the list");
        }

        return (E) array[i++];
      }
    };
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(array, size);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T[] toArray(T[] a) {
    for (int i = 0; i < a.length; i++) {
      a[i] = (T) array[i];
    }

    return a;
  }

  @Override
  public boolean add(E e) {
    if (size == array.length) {
      growArray();
    }
    array[size++] = e;
    nulls += e == null ? 1 : 0;

    return true;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    c.forEach(this::add);
    return true;
  }

  @Override
  public boolean remove(Object o) {
    return false;
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public void clear() {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bound.");
    }

    return (E) array[index];
  }

  @Override
  public E set(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of bound.");
    }

    array[index] = element;

    return element;
  }

  @Override
  public void add(int index, E element) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException();
    }

    E result = (E) array[index];

    while (index < this.size - 1) {
      array[index] = array[index + 1];
      index++;
    }

    size--;

    if (size <= this.array.length * SHRINK_FACTOR) {
      this.shrinkArray();
    }

    return result;
  }

  @Override
  public int indexOf(Object o) {
    for (int i = 0; i < size; i++) {
      if (array[i].equals(o)) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public int lastIndexOf(Object o) {
    int resultIndex = -1;

    for (int i = 0; i < size; i++) {
      if (array[i].equals(o)) {
        resultIndex = i;
      }
    }

    return resultIndex;
  }

  @Override
  public ListIterator<E> listIterator() {
    return new ListIterator<E>() {
      private int index = 0;
      private Integer prevIndex = null;

      @Override
      public boolean hasNext() {
        return index < size;
      }

      @Override
      public E next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        prevIndex = index;
        return (E) array[index++];
      }

      @Override
      public boolean hasPrevious() {
        return index >= 0 && size > 0;
      }

      @Override
      public E previous() {
        prevIndex = index;
        return (E) array[index--];
      }

      @Override
      public int nextIndex() {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
      }

      @Override
      public int previousIndex() {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
      }

      @Override
      public void set(E e) {
        array[prevIndex] = e;
      }

      @Override
      public void add(E e) {
        throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
      }
    };
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    throw new UnsupportedOperationException(EXCEPTION_MESSAGE);
  }

  @Override
  public void sort(Comparator<? super E> c) {
    Arrays.sort((E[]) array, 0, size, c);
  }

  /** Increases array size in GROW_FACTOR times. */
  private void growArray() {
    int newSize = this.array.length * GROW_FACTOR;
    array = Arrays.copyOf(array, newSize);
  }

  /** Shrinks array. */
  private void shrinkArray() {
    int newSize = this.array.length / GROW_FACTOR;
    array = Arrays.copyOf(array, newSize);
  }
}
