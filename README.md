# CS2030S Labs
 
### AY22/23 S1
Mainly uploaded to keep record of my labs.
Comments document my thinking, particularly for later labs.


## PECS
1. Remember to never include PECS in return type of method.
```
// Not good
 public <N> Transformer<? extends R, ? super N> after(Transformer<? extends P, ? super N> after){
 ...
 }
 
 // Good
  public <N> Transformer<R, N> after(Transformer<? extends P, ? super N> after)
```