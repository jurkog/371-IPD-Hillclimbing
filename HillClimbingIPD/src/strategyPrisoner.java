import java.util.ArrayList;
import java.util.HashMap;

public class strategyPrisoner extends Prisoner {

	private HashMap<Integer, Character> beginStrategy;
	private HashMap<Integer, Character> strategy;

	public strategyPrisoner(HashMap<Integer, Character> beginStrategy, HashMap<Integer, Character> strategy) {
		this.beginStrategy = beginStrategy;
		this.strategy = strategy;
	}

	@Override
	public char decideMove(int currentRound) {
		if (currentRound < beginStrategy.keySet().size())
			return beginStrategy.get(currentRound);
		
		return strategy.get(currentRound - beginStrategy.keySet().size());
	}

}
