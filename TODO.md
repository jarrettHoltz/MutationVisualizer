Notes on what needs to be done:

The addition of accessors and mutators was successful, but I think that a lot of the mutators can be deleted as I don't believe they are used anywhere (which actually makes the visibiilty of our fields better). Need to delete the mutators that are not used.

In Mutator.java:
	 method updateSummary: It doesn't appear that the MutatorVizModel adds any functionality to the method. Why is it an argument to this method?

	 Looks like the updateSummary would also make any "unknown" status mutants into killed. This doesn't seem right to me. I will add a branch to do nothing if it equals "unknown";

	 Actually, it looks like this was supposed to use the model.mutants, but since the class already has a mutants field, it accepted this code - although I believe it is not correct.

In Summary.java
	We allow for the total to be less than the other numbers. This shouldn't be allowed (since it makes no sense).


Tests in controller to complete:
	TriangleParserTest.java - need to look over again
	BrowserListenerTest.java - need to get testValueChanged working
	CodeLineMouseListenerTest.java - need mouse clicked test
Tests in model to complete:
	MutatorTest.java - needs updateSummary
	TriangleModelTest.java - testConstructor
Tests in view to complete:
	VerticalButtonUITest.java - paint and preferredsize
	SummaryPanelTest.java
	ResizeableJLabelTest.java
	MutantVizWindowTest.java
	MutantTreeCellRendererTest.java
	MutantColorTest.java
	ColorContextTest.java
	CodePanelTest.java
	BrowserPanelTest.java - need to do setTreeSelection, and also check jTree
	BarGraphBarTest.java - dont know how to test private function

Need to add meaningful comments to each test file