package ru.otus.collections;

import static com.google.common.truth.Truth.assertThat;

import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class MyListTests {

  public final List<Integer> myList = new MyList<>();

  @Test
  public void testAddAll() {
    Collections.addAll(myList, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2);

    assertThat(myList)
        .containsExactly(4, 5, 1, 2, 3, 9, 4, 1, 3, 2, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2)
        .inOrder();
  }

  @Test
  public void testSorting() {
    assertThat(myList).isEmpty();

    Collections.addAll(myList, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2);
    assertThat(myList).hasSize(20);

    Collections.sort(myList);
    assertThat(myList).isInOrder();
  }

  @Test
  public void testCopying() {
    Collections.addAll(myList, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2, 4, 5, 1, 2, 3, 9, 4, 1, 3, 2);
    List<Integer> destination = new MyList<>();

    for (int i = 0; i < myList.size(); i++) {
      destination.add(null);
    }

    assertThat(destination).hasSize(myList.size());
    assertThat(destination).containsNoneIn(myList);

    Collections.copy(destination, myList);

    assertThat(destination).containsExactlyElementsIn(myList).inOrder();
  }
}
