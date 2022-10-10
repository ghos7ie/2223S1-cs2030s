# CS2030S Labs
 
### AY22/23 S1

Each lab contains a Notes pdf that contains workings.


Most likely incomplete but documents my thought process (read as mental breakdown) while tackling the lab.



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