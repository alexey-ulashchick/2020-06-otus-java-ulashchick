package ru.otus.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;


public class MyList<E> implements List<E> {

  private final static int DEFAULT_SIZE = 8;
  private final static int GROW_FACTOR = 2;
  private final static float SHRINK_FACTOR = 0.25f;

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

      @Override
      public void remove() {

      }

      @Override
      public void forEachRemaining(Consumer<? super E> action) {

      }
    };
  }

  @Override
  public Object[] toArray() {
    return Arrays.copyOf(array, size);
  }

  @Override
  public <T> T[] toArray(T[] a) {
    throw new UnsupportedOperationException("Not implemented yet.");
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

  private void growArray() {
    int newSize = this.array.length * GROW_FACTOR;
    array = Arrays.copyOf(array, newSize);
  }

  private void shrinkArray() {
    int newSize = this.array.length / GROW_FACTOR;
    array = Arrays.copyOf(array, newSize);
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
    return false;
  }


  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return false;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return false;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return false;
  }

  @Override
  public void clear() {

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

  }

  @Override
  public E remove(int index) {
    return null;
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
    return null;
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return null;
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return null;
  }
}
