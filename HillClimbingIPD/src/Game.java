
public class Game {
	private Prisoner competingPrisoner;
	private Prisoner prisoner;
	
	private int competingPrisonerScore = 0;
	private int prisonerScore = 0;
	private int numberOfGames;

	public Game(Prisoner competingPrisoner, Prisoner prisoner) {
		this.competingPrisoner = competingPrisoner;
		this.prisoner = prisoner;
	}

	public void playRounds(int n) {
		numberOfGames = n;
		for (int currentRound = 0; currentRound < n; currentRound++) {
			char competingMove = competingPrisoner.decideMove(currentRound);
			char prisonerMove = prisoner.decideMove(currentRound);
			
			updateScores(competingMove, prisonerMove);
			
			prisoner.memory.add(prisonerMove);
			competingPrisoner.memory.add(competingMove);
			competingPrisoner.informOfMove(prisonerMove);
			prisoner.informOfMove(competingMove);
		}
	}

	private void updateScores(char competingMove, char prisonerMove) {
		if (competingMove == 'C' && prisonerMove == 'C') {
			competingPrisonerScore += 3;
			prisonerScore += 3;
		} else if (competingMove == 'C' && prisonerMove == 'D') {
			prisonerScore += 5;
		} else if (competingMove == 'D' && prisonerMove == 'C') {
			competingPrisonerScore += 5;
		} else if (competingMove == 'D' && prisonerMove == 'D') {
			competingPrisonerScore += 1;
			prisonerScore += 1;
		}
	}

	public void displayResults() {
		if (competingPrisoner instanceof HillClimbingPrisoner)
			System.out.println(((HillClimbingPrisoner) competingPrisoner).beginStrategy.values() + " " + ((HillClimbingPrisoner) competingPrisoner).strategy.values());
		System.out.println(competingPrisonerScore);
		
		
	}

	public int score() {
		return competingPrisonerScore;
	}	

}
