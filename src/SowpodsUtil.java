import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SowpodsUtil {
    public static Map<String, ArrayList<String>> anagramMap = new TreeMap<>();

    public static void intSowpodsUtil(int noOfChar){

        File file = new File("./src/sowpods.txt");
        System.out.println(file);

        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null ) {
                if (st.length() == noOfChar && hasUniqueChar(st)) {
                    String key = sortString(st);
                    ArrayList<String> temp;
                    if (!anagramMap.containsKey(key)) {
                        temp = new ArrayList<>();
                    } else {
                        temp = anagramMap.get(key);
                    }
                    temp.add(st);
                    anagramMap.put(key, temp);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String sortString(String word){
        char[] letters = word.toCharArray();
        Arrays.sort(letters);
        return new String(letters);
    }

    public static void printMap(Map<String, ArrayList<String>> hashMap) {
        for (Object o : hashMap.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            System.out.println(pair.getKey() + " " + pair.getValue().toString());
        }
    }

    public static Map getAnagramMap()  {
        return anagramMap;
    }

    private static boolean hasUniqueChar(String str){
        int checker = 0;

        for (int i=0; i<str.length(); i++)
        {
            int bitAtIndex = str.charAt(i)-'a';
            if ((checker & (1<<bitAtIndex)) > 0)
                return false;
            checker = checker | (1<<bitAtIndex);
        }

        return true;
    }
}
