package CSProject;

// ranked pair system of voting
public class RP {
	// max number of candidates
	public final int MAX = 9;

	// preferences[i][j] is number of voters who prefer i over j
	public int[][] preferences = new int[MAX][MAX];

	// locked[i][j] means i is locked in over j
	public boolean[][] locked = new boolean[MAX][MAX];

	// each pair has a winner and loser
	public class Pair {
		public int winner;
		public int loser;
	}

	// candidate arrays
	public String[] candidates = new String[MAX];
	public Pair[] pairs = new Pair[MAX * (MAX - 1) / 2];
   
	public int pairCount;
	public int candidateCount;

	// initialize the Pair array
	public void initPairs() {
		for (int i = 0; i < (MAX * (MAX - 1) / 2); i++) {
			pairs[i] = new Pair();
		}
	}

	// update ranks given a new vote
	public boolean vote(int rank, String name, int[] ranks) {
		for (int i = 0; i < candidateCount; i++) {
			if (candidates[i].equals(name)) {
				ranks[rank] = i; // lower number is better
				return true;
			}
		}
		return false;
	}

	// update preferences when one's voter ranks are provided
	public void recordPreferences(int[] ranks) {
		for (int i = 0; i < candidateCount; i++) {
			for (int j = i + 1; j < candidateCount; j++) {
				preferences[ranks[i]][ranks[j]] += 1;
			}
		}
	}

	// record pairs of candidates where one is preferred over the other
	public void addPairs() { //error; look later
		for (int i = 0; i < candidateCount; i++) {
			for (int j = i + 1; j < candidateCount; j++) {
				if (preferences[i][j] > preferences[j][i]) {
					pairs[pairCount].winner = i;
					pairs[pairCount].loser = j;
					pairCount++;
				}
				else if (preferences[j][i] > preferences[i][j]) {
					pairs[pairCount].winner = j;
					pairs[pairCount].loser = i;
					pairCount++;
				}
			}
		}
	}

	// sort pairs in decreasing order by strength of victory
	public void sortPairs() {
		for (int i = pairCount - 1; i >= 0; i--) {
			for (int j = 0; j <= i - 1; j++) {
				if ((preferences[pairs[j].winner][pairs[j].loser]) < (preferences[pairs[j + 1].winner][pairs[j + 1].loser])) {
					Pair sorting = pairs[j];
					pairs[j] = pairs[j + 1];
					pairs[j + 1] = sorting;
				}
			}
		}
	}

	public boolean cycle(int winner, int loser) {
		while (winner != -1 && winner != loser) {
			boolean found = false;
			for (int i = 0; i < candidateCount; i++) {
				if (locked[i][winner]) {
					found = true;
					winner = i;
				}
			}
			if (!found) {
				winner = -1;
			}
		}
		if (winner == loser) {
			return true;
		}
		return false;
	}

	// lock pairs into the candidate graph in order without creating cycles
	public void lockPairs() {
		for (int i = 0; i < pairCount; i++) {
			if (!cycle(pairs[i].winner, pairs[i].loser)) {
				locked[pairs[i].winner][pairs[i].loser] = true;
			}
		}
	}

	// print the winner of the election
	public void printWinner() {
		for (int i = 0; i < MAX; i++) {
			boolean isRight = true;
			for (int j = 0; j < MAX; j++) {
				if (locked[j][i]) {
					isRight = false;
					break;
				}
			}
			if (isRight) {
				System.out.println(candidates[i]);
				return;
			}
		}
	}
}
