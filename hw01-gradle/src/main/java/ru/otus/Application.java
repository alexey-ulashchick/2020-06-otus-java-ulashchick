package ru.otus;

import com.google.common.collect.Lists;
import java.util.logging.Logger;


public class Application {

  private static final int DIFF = 'a' - 'A';
  private static final Logger LOGGER = Logger.getLogger(Application.class.getName());

  public static void main(String[] args) {
    Lists.charactersOf("otus")
        .stream()
        .map(ch -> ch - DIFF)
        .map(Character::toChars)
        .map(String::new)
        .forEach(LOGGER::info);
  }
}
