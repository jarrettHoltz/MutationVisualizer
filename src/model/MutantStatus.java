package model;

public enum MutantStatus {
	FAIL,
	EXCEPTION,
	COVERED, //Note: the mutant-by-mutant information does not provide this; may need to be renamed if that gets added
	LIVE,
	;
	
	private static final MutantStatus[] KILLED = {FAIL, EXCEPTION};
	
	public boolean isKilled() {
		for(MutantStatus status : KILLED) {
			if(this == status) {
				return true;
			}
		}
		return false;
	}
	
	public static MutantStatus[] getKilled() {
		return KILLED;
	}
}
