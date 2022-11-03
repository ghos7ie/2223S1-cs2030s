import cs2030s.fp.Immutator
import cs2030s.fp.Reader

Reader<Integer> intInput = Reader.of(2, 0, 3, 0);
intInput.consume().read();
intInput.consume().consume().read();
intInput.consume().consume().consume().read();
intInput.consume().consume().consume().consume().read();
intInput.consume().consume().consume().consume().consume();

Reader<Object> noInput = Reader.of();
noInput.consume();

Reader.of("CS", "2030", "S").equals(Reader.of(2, 0, 3, 0));
Reader.of("CS", "2030", "S").equals(Reader.of());
Reader.of().equals(Reader.of(2, 0, 3, 0));
Reader.of().equals(Reader.of());
