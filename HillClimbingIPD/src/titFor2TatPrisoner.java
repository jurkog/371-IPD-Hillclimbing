
public class titFor2TatPrisoner extends Prisoner {

	@Override
	public char decideMove(int currentRound) {
		if (currentRound < 2) {
			return 'C';
		}
		
		if (opponentMemory.remove(0) == 'C')
			if (opponentMemory.get(0) == 'C')
				return 'C';
		
		return 'D';
	}
	
}
