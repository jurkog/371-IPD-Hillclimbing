
public class suspiciousTitForTatPrisoner extends Prisoner {

	@Override
	public char decideMove(int currentRound) {
		if (currentRound == 0) {
			return 'D';
		}
		
		return opponentMemory.remove(0);
	}

}
