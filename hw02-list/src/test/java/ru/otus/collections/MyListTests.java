package ru.otus.collections;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class MyListTests {

  public final List<Integer> list = new MyList<>();

  @Before
  public void fillTheList() {
    list.add(0);
    list.add(1);
    list.add(2);
  }

  @Test
  public void testSize() {
    assertThat(list).hasSize(3);
  }

  @Test
  public void testIsEmpty() {
    MyList<Integer> emptyList = new MyList<>();

    assertThat(emptyList).isEmpty();
    assertThat(list).isNotEmpty();
  }

  @Test
  public void testIterator() {
    Iterator<Integer> iterator = list.iterator();

    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo(0);

    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo(1);

    assertThat(iterator.hasNext()).isTrue();
    assertThat(iterator.next()).isEqualTo(2);

    assertThat(iterator.hasNext()).isFalse();

    Exception exception = assertThrows(NoSuchElementException.class, iterator::next);

    assertThat(exception).hasMessageThat().contains("No more elements in the list");
  }

  @Test
  public void indexOf() {
    list.add(1);

    assertThat(list).hasSize(4);
    assertThat(list.indexOf(1)).isEqualTo(1);
    assertThat(list.lastIndexOf(1)).isEqualTo(3);
  }

  @Test
  public void testToArrayWithNoParameters() {
    Object[] objects = list.toArray();

    assertThat(objects).isEqualTo(new Object[]{0, 1, 2});
  }
}
