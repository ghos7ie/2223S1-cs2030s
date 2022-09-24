(-1) Missing Shop Class
(-1) Missing Customer Class
(-1) Counter should not have a getter for the Counter ID
(-1) Counter should not have an unrestricted setter for availability -- use two non-parameterised methods instead

There are two more objects to encapsulate (i.e. create classes for these):
1. Shop
2. Customer

The Shop should be responsible for initialising and keeping track of all Counters in the shop.

A Customer should contain its ID (private without getters) and serviceTime.

Also, good job on using the @Override annotation. It is not required yet, but it will be covered in the future