
public class grudgerPrisoner extends Prisoner {
	private boolean hasGrudge = false;
	
	@Override
	public char decideMove(int currentRound) {
		if (currentRound == 0) {
			return 'C';
		}
		
		if (opponentMemory.remove(0) == 'D') {
			hasGrudge = true;
		}
		
		return hasGrudge ? 'D' : 'C';
	}

}
