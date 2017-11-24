import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpinWords {
	public String spinWords(String sentence) {
		List<String> cutByWord = new ArrayList<String>();
		String toReturn = "";
		cutByWord = Arrays.asList(sentence.split(" "));

		for (String current : cutByWord) {

			if (current.length() >= 5) {

				String constructReverse = "";

				for (int i = current.length() - 1; i >= 0; i--) {
					constructReverse += current.charAt(i);
				}
				toReturn += constructReverse + " ";

			} else {
				toReturn += current + " ";
			}
		}
		return toReturn.trim();
	}
}
