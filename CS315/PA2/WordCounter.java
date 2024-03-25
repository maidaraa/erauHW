/**
 * Implements a word frequency counter using a binary search tree and a heap.
 */
// credit to: the book author for the BST class 
// credit to: Professor Stansbury for the MaxHeap class
// Mai Evans 12/23

public class WordCounter {

    //DECLARE CLASS VARIABLES HERE
    private BST<String, Integer> bst = new BST<>();
    private MaxHeap<Integer, String> maxHeap = new MaxHeap<>(1500);
    
    void countWords(String [] words) {

        //Impelement your solution here
        for(int i = 0; i < words.length; i++){
            
            if(bst.contains(words[i])){
                bst.put(words[i], bst.get(words[i]) + 1); // if word is already in BST increment its frequency
            }else {
                bst.put(words[i], 1); // if word is not in BST, add it with a frequency of 1
            }
        }

        System.out.println("Ascending\n");
        // print words in ascending order from BST
        Iterable<String> it = bst.keys(); // get keys/words from the BST
        for (String string : it){ // iterate through the words 
            System.out.println(string + ", " + bst.get(string)); // print word and get its frequency then print it
            maxHeap.enqueue(bst.get(string), string); // enqueue word frequency and word into MaxHeap
        }
        
        System.out.println("\nDescending\n");
        // print words in descending order of frequency from MaxHeap
        while (!maxHeap.isEmpty()){
            // get word frequency with maximum priority
            int priority = maxHeap.getMaxPriority(); // credit to: Jack Lee for getMaxPriority()
            System.out.println(maxHeap.dequeue() + ", " + priority); //dequeue and print words with their frequency
        }        
    }

    public static void main(String [] args) {
       
        WordCounter me = new WordCounter();
        me.countWords(InputReader.parseInputFile("input.txt"));
    }
}
