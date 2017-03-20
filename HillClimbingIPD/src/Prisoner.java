import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class Prisoner {
	protected ArrayList<Character> memory = new ArrayList<>();
	protected ArrayList<Character> opponentMemory = new ArrayList<>();
	public Prisoner myOpponentIs;

	public abstract char decideMove(int currentRound);

	public void informOfMove(char prisonerMove) {
		opponentMemory.add(prisonerMove);
	}

}
