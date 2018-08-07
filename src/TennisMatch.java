import java.util.Scanner;

public class TennisMatch {
	private static int[] sets = new int[2];
	private static int[] games = new int[2];
	private static int[] points = new int[2];

	private static final String[] pointToString = { "0", "15", "30", "40", "AD" };

	public static void main(String[] a) {
		Scanner s=new Scanner(System.in);
		String input=s.nextLine();
		s.close();
		for (int i = 0; i < input.length(); i++)
			updatePoints(input.charAt(i) - 65);
		System.out.println("player:\t" + "A\tB");
		System.out.println("sets:\t" + sets[0] + "\t" + sets[1]);
		System.out.println("games:\t" + games[0] + "\t" + games[1]);
		System.out.println("points:\t" + printScoreFor(0) + "\t" + printScoreFor(1));
	}

	private static String printScoreFor(int player) {
		return points[1 - player] == 4 ? "" : pointToString[points[player]];
	}

	public static void updatePoints(int idx) {
		if ((points[idx] == 3 && points[1 - idx] < 3) || (points[idx] == 4)) {
			updateSets(idx);
		} else if (points[1 - idx] == 4) {
			points[idx] = 3;
			points[1 - idx] = 3;
		} else
			points[idx]++;
	}

	public static void updateSets(int idx) {
		points[idx] = 0;
		points[1 - idx] = 0;
		games[idx]++;
		if (hasWon(idx)) {
			sets[idx]++;
			games[idx] = 0;
			games[1 - idx] = 0;
		}

	}

	public static boolean hasWon(int idx) {
		return games[idx] >= 6 && games[idx] - games[1 - idx] >= 2;
	}
}
