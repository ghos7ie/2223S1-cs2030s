import cs2030s.fp.Immutator
import cs2030s.fp.Reader

Reader<Integer> intInput = Reader.of(2, 0, 3, 0);
intInput.read();
intInput.read(); // still the same
intInput.hasNext();

Reader<Object> noInput = Reader.of();
noInput.read();
noInput.hasNext();

Reader<String> strInput = Reader.of("CS", "2030", "S");
strInput.read();
strInput.read(); // still the same
strInput.hasNext();
