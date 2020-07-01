package ru.otus;

import com.google.common.collect.Lists;

public class Application {

  private final static int DIFF = 'a' - 'A';

  public static void main(String[] args) {
    Lists.charactersOf("otus")
        .stream()
        .map(ch -> ch - DIFF)
        .map(Character::toChars)
        .forEach(System.out::print);
  }
}
