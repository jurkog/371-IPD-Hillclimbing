
public class alwaysDefectPrisoner extends Prisoner {

	@Override
	public char decideMove(int currentRound) {
		return 'D';
	}

}
