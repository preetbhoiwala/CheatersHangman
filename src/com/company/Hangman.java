package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Hangman {
    public static Map<Integer, List<String>> createWordLists() throws FileNotFoundException {
        Map<Integer, List<String>> wordLists = new HashMap<>();
        String fileName = "words.txt";
        int count = 0;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {

                String word = scanner.nextLine();
                int key = word.length();
                if (wordLists.containsKey(key)) {
                    wordLists.get(key).add(word);
                } else {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    wordLists.put(key, list);
                }

            }
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }


        return wordLists;
    }

    //create word families
    public static Map<String, List<String>> createWordFamilies(List<String> wordList, Set<Character> guessed) {

        Map<String, List<String>> wordFamilies = new HashMap<>();
        for (String word : wordList) {
            String family = findWordFamily(word, guessed);
            if (wordFamilies.containsKey(family)) {
                wordFamilies.get(family).add(word);

            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                wordFamilies.put(family, list);
            }

        }


        return wordFamilies;
    }

    //figure out the word family for the string
    public static String findWordFamily(String word, Set<Character> guessed) {
        String family = "";
        for (char c : word.toLowerCase(Locale.ROOT).toCharArray()) {
            if (guessed.contains(c)) {
                family += c;
            } else {
                family += "_";
            }
        }

        return family;
    }

    //get best family
    public static String getBestFamily(Map<String, List<String>> wordFamilies) {
        //choose the largest family
        String bestKey = "";
        int bestkeysize = 0;
        for (String word : wordFamilies.keySet()) {
            int keysize = wordFamilies.get(word).size();
            if (keysize > bestkeysize) {
                bestKey = word;
                bestkeysize = keysize;

            }
        }

        return bestKey;
    }

    public static void game(List<String> wordList, int guesses) {
        Set<Character> guessedLetters = new HashSet<>();
        Scanner userinput = new Scanner(System.in);
        Map<String, List<String>> wordfamilies = createWordFamilies(wordList, guessedLetters);
        String newGameState = getBestFamily(wordfamilies);
        while (guesses != 0) {
            System.out.println("Please input a letter. ");
            char letter = userinput.next().toLowerCase(Locale.ROOT).charAt(0);
            while (!guessedLetters.add(letter)) {
                System.out.println("you have already entered this letter please re-enter another letter.");
                letter = userinput.next().toLowerCase(Locale.ROOT).charAt(0);
            }
            wordfamilies = createWordFamilies(wordfamilies.get(newGameState), guessedLetters);
            newGameState = getBestFamily(wordfamilies);
            System.out.println("Your words guessed are " + guessedLetters);
            System.out.println("Hidden word: "+findWordFamily(newGameState, guessedLetters));
            if (!findWordFamily(newGameState,guessedLetters).contains(String.valueOf(letter))){
                guesses--;
            }
            if (guesses == 0) {
                System.out.println("Game over. The word was " +wordfamilies.get(newGameState).get(1)+".");
            }
            else if (!findWordFamily(newGameState, guessedLetters).contains("_")) {
                System.out.println("you win");
                guesses = 0;
            }
            System.out.println("You have " + guesses + " remaining guesses.");
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        Scanner userinput = new Scanner(System.in);
        System.out.println("What is the size of the hidden word you want?");
        int wlength = userinput.nextInt();
        System.out.println("How many guesses before you want to lose?");
        int guesses = userinput.nextInt();
        Map<Integer, List<String>> wordlists = createWordLists();
        List<String> wordlist = wordlists.get(wlength);
        game(wordlist, guesses);
        String answer;
        do{
            System.out.println("Do you want to play again Yes or No?");
            answer = userinput.next();
            if (answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("y")) {
                System.out.println("What is the size of the hidden word you want?");
                wlength = userinput.nextInt();
                System.out.println("How many guesses before you want to lose?");
                guesses = userinput.nextInt();
                wordlist = wordlists.get(wlength);
                game(wordlist, guesses);
            }else{
                System.out.println("Thank you for playing.");
                break;
            }
        }while (answer.equalsIgnoreCase("yes")||answer.equalsIgnoreCase("y"));

    }

}
