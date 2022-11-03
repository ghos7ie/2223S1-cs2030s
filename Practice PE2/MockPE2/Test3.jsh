import cs2030s.fp.Immutator
import cs2030s.fp.Reader

Reader<String> strInput = Reader.of("CS", "2030", "S");
Reader<Integer> intInput = strInput.map(s -> s.length());

intInput.consume().read();
intInput.consume().consume().read();
intInput.consume().consume().consume().read();
intInput.consume().consume().consume().consume();