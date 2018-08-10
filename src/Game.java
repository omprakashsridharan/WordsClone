import java.lang.reflect.Array;
import java.util.*;

public class Game {

    String secretWord;
    Map<String,ArrayList<String>> anagramMap;
    List<String> keysToSearch;
    int wordLength;

    public Game(int wordLength) {
        this.wordLength = wordLength;
        SowpodsUtil.intSowpodsUtil(wordLength);
        anagramMap=SowpodsUtil.getAnagramMap();
        Random rand = new Random();
        keysToSearch = new ArrayList<String>(anagramMap.keySet());
        ArrayList<String>randomList = anagramMap.get(keysToSearch.get(rand.nextInt(keysToSearch.size())));
        secretWord =randomList.get(rand.nextInt(randomList.size()));
    }

    public void play(){
        Scanner scan = new Scanner(System.in);
        int userGuessedLength=0;
        int systemGuessedLength=0;
        int i=0;
        String guessWord;
        Random rand = new Random();
        boolean hasSystemWon = false;
        while(true){
            System.out.print("Enter guess word");
            guessWord=scan.next();
            System.out.println("No of matches"+noOfMatches(secretWord,guessWord));
            userGuessedLength=noOfMatches(secretWord,guessWord);
            if(guessWord.equals(secretWord)){
                break;
            }
            int randomGuessWordIndex = rand.nextInt(keysToSearch.size());
            guessWord=anagramMap.get(keysToSearch.get(randomGuessWordIndex)).get(0);
            keysToSearch.remove(randomGuessWordIndex);
            System.out.println("Computer's guess word : "+guessWord+"\nEnter no of matches with your secret word");
            systemGuessedLength = scan.nextInt();
            if(systemGuessedLength==secretWord.length()) {
                String sortedGuessedWord = SowpodsUtil.sortString(guessWord);
                ArrayList<String> equivalentWords = anagramMap.get(sortedGuessedWord);
                for (String tmp : equivalentWords) {
                    System.out.println("is the word " + tmp);
                    String response = scan.next();
                    if (response.equals("yes"))
                    {
                        hasSystemWon=true;
                        break;
                    }
                }

                break;

            }
            else
                prune(guessWord,systemGuessedLength);
        }

        if(!hasSystemWon){
            if(guessWord.equals(secretWord))
            System.out.println("Your guess are correct... YOU WIN");
            else
                System.out.println("Wrong Clues.. Game Ended");

        }
        else{
            System.out.println("System guessed your word... SYSTEM WINS");
        }
    }



    private int noOfMatches(String searchWord, String guessWord){
        int matchCount = 0;
        for(int i=0;i<searchWord.length();i++){
            if(guessWord.contains(searchWord.charAt(i)+""))
                matchCount++;
        }
        return matchCount;
    }

    private void prune(String guessWord,int systemGuessedLength){
        ArrayList<String> keysToRemove = new ArrayList<>();
        if(systemGuessedLength==0){
            pruneZero(guessWord);
        }
        else{
            for(String key : keysToSearch) {
                int matchCount = noOfMatches(key,guessWord);
                if(matchCount!=systemGuessedLength){
                    keysToRemove.add(key);
                }

            }
            keysToSearch.removeAll(keysToRemove);
        }
        System.out.println("Size after pruning " + keysToSearch.size());
        }
        private void pruneZero(String guessWord){
            ArrayList<String> keysToRemove = new ArrayList<>();
            for(char c : guessWord.toCharArray()){
            for(String key : keysToSearch)
                if(key.contains(c+""))
                    keysToRemove.add(key);
        }
            keysToSearch.removeAll(keysToRemove);
        }
    }



