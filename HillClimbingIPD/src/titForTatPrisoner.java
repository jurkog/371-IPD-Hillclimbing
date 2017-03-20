
public class titForTatPrisoner extends Prisoner {
	
	@Override
	public char decideMove(int currentRound) {
		if (currentRound == 0) {
			return 'C';
		}
		
		return opponentMemory.remove(0);
	}
}
