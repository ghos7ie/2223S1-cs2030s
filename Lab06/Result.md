Perfect work! Good job :)

Also, about your note under `Memo::combine`, type parameters T and S do not require any subtyping relationship between them.
For instance, a Combiner can take in an Integer (T) and String (S) and return a String (R) with no issues, with implementation (a,b)->Integer.toString(a)+b
