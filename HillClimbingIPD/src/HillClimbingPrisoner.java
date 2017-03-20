import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class HillClimbingPrisoner extends Prisoner {
	private int memorySize = 4;
	protected HashMap<Integer, Character> strategy;
	protected HashMap<Integer, Character> beginStrategy;
	private int currentScore;
	private Random random;

	public HillClimbingPrisoner(Prisoner prisoner) {
		this.myOpponentIs = prisoner;
		random = new Random();
		strategy = getInitialStrategy();
		beginStrategy = getInitialBeginStrategy();
		currentScore = calculateFitness(beginStrategy, strategy);
		System.out.println(beginStrategy.values() + " " + strategy.values());
	}
	
	private HashMap<Integer, Character> getInitialStrategy() {
		HashMap<Integer, Character> strategy = new HashMap<>();
		int size = (int)Math.pow(4, memorySize);
		for (int i = 0; i < size; i++) {
			strategy.put(i , (char)(random.nextInt(2) + 'C'));
		}
			
		return strategy;
	}
	
	private HashMap<Integer, Character> getInitialBeginStrategy() {
		HashMap<Integer, Character> beginStrategy = new HashMap<>();
		for (int i = 0; i < memorySize; i++) {
			beginStrategy.put(i , (char)(random.nextInt(2) + 'C'));
		}
		return beginStrategy;		
	}

	private int calculateFitness(HashMap<Integer, Character> beginStrategy, HashMap<Integer, Character> strategy) {
		try {
		Object object = Class.forName(myOpponentIs.getClass().getCanonicalName()).newInstance();
		Game g = new Game(new strategyPrisoner(beginStrategy, strategy), new titForTatPrisoner());
		g.playRounds(100);
		return g.score();
		} catch (Exception e){
			return -1;
		}

	}

	@Override
	public char decideMove(int currentRound) {
		if (currentRound < memorySize) {
			return beginStrategy.get(random.nextInt(beginStrategy.keySet().size()));
		}
		
		//System.out.println("Score before iteration: " + currentScore);
		HashMap<Integer, Character> succStrategy = mutateStrategy();
		if (calculateFitness(beginStrategy, succStrategy) > currentScore) {
			//System.out.println("i: " + i + " Current score: " + ((double)currentScore)/1000 + " " + strategy.toString().substring(100)) ;
			strategy = succStrategy;
			currentScore = calculateFitness(beginStrategy, succStrategy);
		} 
		
		//System.out.println("Score after iteration: " + currentScore);
		
		int opponentScore = 0;
		for (int i = memorySize - 1; i > 0; i--) {
			opponentScore = (opponentScore << 1) + opponentMemory.get(i) == 'C' ? 0 : 1;
			opponentScore = (opponentScore << 1) + memory.get(i) == 'C' ? 0 : 1;
		}
		
		opponentScore = (opponentScore << 1) + opponentMemory.remove(0) == 'C' ? 0 : 1;
		opponentScore = (opponentScore << 1) + memory.remove(0) == 'C' ? 0 : 1;
		
		
		return strategy.get(opponentScore);
	}


	private HashMap<Integer, Character> mutateStrategy() {
		int index = random.nextInt(strategy.keySet().size());
		HashMap<Integer, Character> modifiedStrategy = (HashMap<Integer, Character>) strategy.clone();
		modifiedStrategy.put(index, modifiedStrategy.get(index) == 'C' ? 'D' : 'C');
		return modifiedStrategy;
	}



}
