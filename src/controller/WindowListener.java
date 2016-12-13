package controller;

import view.MutantVizWindow;

public abstract class WindowListener {
	protected MutantVizWindow window;
	
	protected void attachWindow(MutantVizWindow window) {
		this.window = window;
	}
	
}
