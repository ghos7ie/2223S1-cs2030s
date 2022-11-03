import cs2030s.fp.Immutator
import cs2030s.fp.Reader

Reader<Integer> intInput = Reader.of(1, 2);
intInput = intInput.flatMap(x -> Reader.of(x * 10, x * 20, x * 30));

intInput.read();
intInput.consume().read();
intInput.consume().consume().read();
intInput.consume().consume().consume().read();
intInput.consume().consume().consume().consume().read();
intInput.consume().consume().consume().consume().consume();
