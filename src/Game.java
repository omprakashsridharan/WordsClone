import java.util.*;

public class Game {

    String secretWord;
    List<String> keysToSearch;
    int wordLength;
    Scanner scan = new Scanner(System.in);
    Random rand = new Random();
    String guessWord="";

    public Game(int wordLength) {
        this.wordLength = wordLength;

        Random rand = new Random();
        keysToSearch = SowpodsUtil.getAnagramKeyset(wordLength);
        ArrayList<String>randomList = SowpodsUtil.getAnagramEquivalent(keysToSearch.get(rand.nextInt(keysToSearch.size())));
        secretWord =randomList.get(rand.nextInt(randomList.size()));
    }

    public void play(){

        int systemGuessedLength=0;
        int i=0;
        boolean hasSystemWon = false;
        while(true){
//          guessWord = userTurn();
//
//            if(guessWord.equals(secretWord)){
//                break;
//            }

            systemGuessedLength = computerTurn();
            if(systemGuessedLength==secretWord.length()) {
                String sortedGuessedWord = SowpodsUtil.sortString(guessWord);
                ArrayList<String> equivalentWords = SowpodsUtil.getAnagramEquivalent(sortedGuessedWord);
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

    private int computerTurn(){
        int systemGuessedLength;
        int randomGuessWordIndex = rand.nextInt(keysToSearch.size());
        guessWord=SowpodsUtil.getAnagramEquivalent(keysToSearch.get(randomGuessWordIndex)).get(0);

        keysToSearch.remove(randomGuessWordIndex);
        System.out.println("Computer's guess word : "+guessWord+"\nEnter no of matches with your secret word");
        systemGuessedLength = scan.nextInt();
        return systemGuessedLength;
    }

    private String userTurn(){
        String guessWord = "";
        System.out.print("Enter guess word");
        guessWord=scan.next();
        System.out.println("No of matches"+noOfMatches(secretWord,guessWord));
        return guessWord;
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
        System.out.println("Size before pruning " + keysToSearch.size());
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



