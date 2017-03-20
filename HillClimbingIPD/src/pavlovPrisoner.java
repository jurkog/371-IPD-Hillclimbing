
public class pavlovPrisoner extends Prisoner {
	private char move = 'C';
	
	@Override
	public char decideMove(int currentRound) {
		if (currentRound == 0) {
			return 'C';
		}
		
		if (opponentMemory.remove(0) == 'C') {
			return 'C';
		} else {
			move = move == 'C' ? 'D' : 'C';
			return move;
		}
	}

}
