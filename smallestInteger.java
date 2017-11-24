
public class smallestInteger {

	public static int findSmallestInt(int[] tabInt) {

		int comparator = tabInt[0];

		for (int current : tabInt) {
			if (current < comparator)
				comparator = current;
		}

		return comparator;
	}

}
