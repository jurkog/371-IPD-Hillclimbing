
public class alwaysCooperatePrisoner extends Prisoner {

	@Override
	public char decideMove(int currentRound) {
		return 'C';
	}

}
