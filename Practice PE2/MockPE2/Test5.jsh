import cs2030s.fp.Immutator
import cs2030s.fp.Reader

Main.reverse("adam");
Main.reverse("eve");
Main.reverse("adi");
Main.reverse("racecar");

List<String> words = List.of("adam", "eve", "adi", "racecar", "madam", "anna");
Main.palindrome(words.stream()).forEach(System.out::println);
