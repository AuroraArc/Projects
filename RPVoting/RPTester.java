import java.util.Scanner;
import CSProject.RP;
public class RPTester {
    public static void main(String[] args) {
        RP rp = new RP();
        Scanner input = new Scanner(System.in);
        // check for invalid usage
        if (args.length < 1) {
            System.out.println("Usage: RPTester [candidate ...]");
            System.exit(1);
        }

        // populate array of candidates
        rp.candidateCount = args.length;
        if (rp.candidateCount > rp.MAX) {
            System.out.println("Maximum number of candidates is " + rp.MAX);
            System.exit(2);
        }
        for (int i = 0; i < rp.candidateCount; i++) {
            rp.candidates[i] = args[i]; // maybe args[i + 1]
        }

        // clear graph of locked-in pairs
        for (int i = 0; i < rp.candidateCount; i++) {
            for (int j = 0; j < rp.candidateCount; j++) {
                rp.locked[i][j] = false;
            }
        }

        rp.pairCount = 0;
        System.out.print("Number of voters: ");
        int voterCount = input.nextInt();

        // query for votes
        for (int i = 0; i < voterCount; i++) {
            // ranks[i] is voter's i-th preference
            int[] ranks = new int[rp.candidateCount];

            // query for each rank
            for (int j = 0; j < rp.candidateCount; j++) {
                System.out.print("Rank " + (j + 1) + ": ");
                String name = input.next();

                if (!rp.vote(j, name, ranks)) {
                    System.out.println("Invalid vote.");
                    System.exit(3);
                }
            }
            rp.recordPreferences(ranks);
            System.out.println();
        }

        rp.initPairs();
        rp.addPairs();
        rp.sortPairs();
        rp.lockPairs();
        rp.printWinner();
    }
}
