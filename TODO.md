Notes on what needs to be done:

The addition of accessors and mutators was successful, but I think that a lot of the mutators can be deleted as I don't believe they are used anywhere (which actually makes the visibiilty of our fields better). Need to delete the mutators that are not used.

Mutant only has addTest capabilities. Does it need deleteTest capabilities?

Do we need tests for each accessor and mutator?

Need to add javadoc comments to all functions?

In Mutator.java:
	 method updateSummary: It doesn't appear that the MutatorVizModel adds any functionality to the method. Why is it an argument to this method?

	 Looks like the updateSummary would also make any "unknown" status mutants into killed. This doesn't seem right to me. I will add a branch to do nothing if it equals "unknown";

	 Actually, it looks like this was supposed to use the model.mutants, but since the class already has a mutants field, it accepted this code - although I believe it is not correct.