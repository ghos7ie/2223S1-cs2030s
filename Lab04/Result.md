(-0.5) Probably::check should be able to take in any Immutator<Boolean, ? super T> instead of just IsModEq

Note that the Probably::check method should be able to check for any condition specified by the Immutator being passed in, not just IsModEq.
For `Probably::transform`, you should also change the return type to be `Probably<R>` instead to be more specific.
Other than that, good job and keep it up! :)