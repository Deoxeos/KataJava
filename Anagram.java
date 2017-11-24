import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Anagram {
	private static Stream<String> content;
	private static Map<Integer, List<String>> byLength;
	private static float[] time2 = new float[6];

	public static void main(String[] args) throws IOException {
		timeTestor(100);

	}
	
	public static void timeTestor(int nbTour) throws IOException {
		int idMax = 0;
		int idMin = 0;

		float tmpTempo = 0;
		float tmpMax = 0;
		float tmpMin = 0;

		for (int i = 0; i < nbTour; i++) {

			if (i == 0) {
				tmpTempo = secondTry();
				tmpMax = tmpTempo;
				tmpMin = tmpTempo;
			} else {

				tmpTempo = secondTry();
				if (tmpMax < tmpTempo) {
					tmpMax = tmpTempo;
					idMax = i;
				}

				if (tmpMin > tmpTempo) {
					tmpMin = tmpTempo;
					idMin = i;
				}
			}

			System.out.println("Tour nb : " + (i + 1) + " avec max = " + tmpMax + " et min = " + tmpMin + " TEMPS ACTUEL -> " + tmpTempo);

		}
		System.out.println("Temps max -> " + tmpMax + " au tour " + (idMax + 1));
		System.out.println("Temps min -> " + tmpMin + " au tour " + (idMin + 1));
		
		 idMax = 0;
		 idMin = 0;

		 tmpTempo = 0;
		 tmpMax = 0;
		 tmpMin = 0;

		for (int i = 0; i < nbTour; i++) {

			if (i == 0) {
				tmpTempo = firstTry();
				tmpMax = tmpTempo;
				tmpMin = tmpTempo;
			} else {

				tmpTempo = firstTry();
				if (tmpMax < tmpTempo) {
					tmpMax = tmpTempo;
					idMax = i;
				}

				if (tmpMin > tmpTempo) {
					tmpMin = tmpTempo;
					idMin = i;
				}
			}

			System.out.println("Tour nb : " + (i + 1) + " avec max = " + tmpMax + " et min = " + tmpMin + " TEMPS ACTUEL -> " + tmpTempo);

		}
		System.out.println("Temps max -> " + tmpMax + " au tour " + (idMax + 1));
		System.out.println("Temps min -> " + tmpMin + " au tour " + (idMin + 1));


	}

	public static float firstTry() throws IOException {
		Date d1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		content = Files.lines(Paths.get("test2.txt"));
		List<String> caseSensitiv = content.map(x -> x.toLowerCase()).collect(Collectors.toList());
		Map<Integer, List<String>> byLength = caseSensitiv.stream().collect(Collectors.groupingBy(x -> x.length()));

		for (Integer i : byLength.keySet()) {
			Map<String, Integer> length = new HashMap<String, Integer>();
			for (String current : byLength.get(i)) {

				if ((length.get(sortedLetter(current).toString())) == null) {
					length.put(sortedLetter(current).toString(), 0); //
				}
				length.put(sortedLetter(current).toString(), length.get(sortedLetter(current).toString()) + 1);
			}
		}

		Date d2 = new Date();
		return splitTime(sdf.format(d1), sdf.format(d2));

	}

	public static List<String> sortedLetter(String byLenghtAndInd) {
		return Arrays.asList(byLenghtAndInd.split("")).stream().sorted().collect(Collectors.toList());
	}

	public static List<String> bringListByLenght(Integer indice) {
		List<String> test = new ArrayList<String>();

		test = byLength.get(indice);

		return test;

	}

	public static float secondTry() throws IOException {
		Date d1 = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

		Stream<String> stream = Files.lines(Paths.get("listAnagrams.txt"));
		Stream<String> strea;


		strea = stream.map(x -> x.toLowerCase());
		HashSet<String> doublon = new HashSet<String>();
		Long cpt = (long) 0;
		Map<Integer, List<String>> collectedByLength = strea.collect(Collectors.groupingBy(x -> x.length()));
		for (Integer i : collectedByLength.keySet()) {
			HashSet<String> finaly = new HashSet<String>();

			List<String> newList = collectedByLength.get(i).stream().sorted().collect(Collectors.toList());

			for (String s : newList) {

				String stu = Arrays.asList(s.split("")).stream().collect(Collectors.joining(""));

				if (!doublon.add(stu)) {
					finaly.add(stu);
				}

			}

			cpt += (long) finaly.size();

			strea.close();
			stream.close();
		}

		Date d2 = new Date();
		return splitTime(sdf.format(d1), sdf.format(d2));
	}

	public static float calculTemp(float time3, float time4, float time5, float time22, float time23, float time24) {
		float total1 = (time3 * 60 * 60) + (time4 * 60) + time5;
		float total2 = (time22 * 60 * 60) + (time23 * 60) + time24;
		return (total2 - total1);
	}

	public static float splitTime(String t2, String t1) {
		int h = 0;
		for (String y : t2.split(":")) {
			time2[h] = Float.parseFloat(y);
			h++;
		}

		for (String y : t1.split(":")) {

			time2[h] = Float.parseFloat(y);
			h++;
		}

		return calculTemp(time2[0], time2[1], time2[2], time2[3], time2[4], time2[5]);

	}
}
