//package com.company;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        Scanner userinput = new Scanner(System.in);
//        System.out.println("What is the size of the hidden word you want?");
//        //ask size of hidden word
//        int wordLength = userinput.nextInt();
//        System.out.println("How many guesses before you want to lose?");
//        //guesses before they lose
//        int guesses = userinput.nextInt();
//
//        //calling functions
//        Map<Integer, List<String>> wordlists = createWordLists();
//        List<String> wordlist = wordlists.get(wordLength);
//        game(wordlist, guesses);
//    }
//
//    //create word lists
//    public static Map<Integer, List<String>> createWordLists() {
//        Map<Integer, List<String>> wordLists = new HashMap<>();
//        String fileName = "words.txt";
//        int count = 0;
//
//        try {
//            Scanner scanner = new Scanner(new File(fileName));
//            while (scanner.hasNextLine()) {
//
//                String word = scanner.nextLine();
//                int key = word.length();
//
//                if (wordLists.containsKey(key)) {
//                    wordLists.get(key).add(word);
//                } else {
//                    List<String> list = new ArrayList<>();
//                    list.add(word);
//                    wordLists.put(key, list);
//                }
//
//            }
//        } catch (FileNotFoundException e1) {
//            // TODO Auto-generated catch block
//            e1.printStackTrace();
//        }
//
//
//        return wordLists;
//    }
//
//    //create word families
//    //takes in list of words and guesses that player has made so far
//    public static Map<String, List<String>> createWordFamilies(List<String> wordList, Set<Character> guessed) {
//        Map<String, List<String>> wordFamilies = new HashMap<>();
//
//        for (String word : wordList) {
//            //for each word in wordlist
//            String family = findWordFamily(word, guessed);
//            if (wordFamilies.containsKey(family)) {
//                wordFamilies.get(family).add(word);
//                //if word family has guessed letter
//                //add word to
//
//            } else {
//                List<String> list = new ArrayList<>();
//                list.add(word);
//                wordFamilies.put(word, list);
//            }
//
//        }
//
//
//        return wordFamilies;
//    }
//
//    //figure out the word family for a string
//    public static String findWordFamily(String word, Set<Character> guessedSoFar) {
//        //go thru word and ask if letter has been guessed. If it has, reveal word else leave blank
//
//        //flawed function I think
//        String family = "";
//
//        for (char character : word.toUpperCase(Locale.ROOT).toCharArray()) {
//
//            if (guessedSoFar.contains(character)) {
//                family += character;
//                //add to string if char is guessed
//            } else {
//                //else blank
//                family += "_";
//            }
//        }
//
//        return family;
//    }
//
//    //get best family (most items)
//    public static String getBestFamily(Map<String, List<String>> wordFamilies) {
//        String best = "";
//        int bestFamilySize = 0;
//        for (String family : wordFamilies.keySet()) {
//            if(wordFamilies.get(family).size() > bestFamilySize){
//                best = family;
//                bestFamilySize = wordFamilies.get(family).size();
//            }
//        }
//
//        return best;
//    }
//
//    public static void game(List<String> wordList, int guesses) {
//        //(a) Print out the revealed letters, their wrong guesses, and the remaining
//        //number of wrong guesses
//        //(b) Get the user’s new guess and be nice and ask them to reenter their
//        //letter if they already guessed it.
//        //(c) Separate your list of hidden words into word families based on the
//        //input.
//
//        Set<Character> alreadyGuessed = new HashSet<>();
//        Scanner scanner = new Scanner(System.in);
//        Map<String, List <String>> wordFamilies = createWordFamilies(wordList, alreadyGuessed);
//        while(guesses != 0) {
//            System.out.println("please input a letter: ");
//
//        }
//
//    }
//
//
//}
//
