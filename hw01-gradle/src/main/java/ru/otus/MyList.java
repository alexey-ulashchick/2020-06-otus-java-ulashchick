package ru.otus;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MyList<E> implements List<E> {

  private final static int DEFAULT_SIZE = 10;
  private final static float GROW_FACTOR = 0.75f;
  private final static float SHRINK_FACTOR = 0.25f;

  private final Object[] array = new Object[DEFAULT_SIZE];
  private final int size = 0;

  private final int nulls = 0;

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
        return (E) array[i++];
      }
    };
  }

  @Override
  public Object[] toArray() {
    return new Object[0];
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return null;
  }

  @Override
  public boolean add(E e) {
    return false;
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
  public boolean addAll(Collection<? extends E> c) {
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
    return null;
  }

  @Override
  public E set(int index, E element) {
    return null;
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
    return 0;
  }

  @Override
  public int lastIndexOf(Object o) {
    return 0;
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
