import java.util.ArrayList;

public class Tournament {
	private ArrayList<Prisoner> prisoners;
	private Prisoner competingPrisoner;
	public static int max = 100000, min = 0;
	
	public Tournament() {
		prisoners = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1; i++){
		Tournament hillClimbingTournament = new Tournament();
		hillClimbingTournament.generatePrisonerPool();
		System.out.println("Score: " + hillClimbingTournament.evaluateTournament(1000));
		
	}
		
	}
	
	private void generatePrisonerPool() {
		prisoners.add(new alwaysCooperatePrisoner());		// Done
		//prisoners.add(new alwaysDefectPrisoner());			// Done
		prisoners.add(new titForTatPrisoner());				// Done
		prisoners.add(new suspiciousTitForTatPrisoner());	// Done
		prisoners.add(new titFor2TatPrisoner());			// Done
		prisoners.add(new suspiciousTitFor2TatPrisoner());  // Done
		//prisoners.add(new grudgerPrisoner());				// Done
		//prisoners.add(new pavlovPrisoner());				// Done
	}
	
	private void setCompetingPrisoner(Prisoner competingPrisoner) {
		this.competingPrisoner = competingPrisoner;
	}

	private float evaluateTournament(int rounds) {
		float average = 0;
		for (Prisoner prisoner : prisoners) {
			Game currentGame = new Game(new HillClimbingPrisoner(prisoner), prisoner);
			System.out.println(HillClimbingPrisoner.class.getClass().getSimpleName() + " vs. " + prisoner.getClass().getSimpleName());
			currentGame.playRounds(rounds);
			currentGame.displayResults();
			average += currentGame.score();
			System.out.println();

		}
		
		return average / prisoners.size() / 1000;
	}
	
}
