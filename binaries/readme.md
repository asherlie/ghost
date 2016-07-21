# GHOST.jar
GHOST.jar will only run if in the same directory as a word list with the name **_words.txt_**

**This program has 6 functions:**

  **1)** With the first argument being any amount of lowercase characters, GHOST.jar will find all of the words that contain that sequence of characters.
  
  **2)** with the input of any integer up to 2147483647([the maximum positive value for 32 bit signed binary integers](https://en.wikipedia.org/wiki/2147483647_(number)#In_computing)), GHOST.jar finds all the two letter character combinations with less than or equal to that number of possible outcomes, printing the words that each pair of letters can make. 
  
  **3)** With the first argument "BEST", GHOST.jar will find the best move based on the second argument, which should be the current character or sequence of characters. This is achieved by using a running min for number of possible outcomes, while printing the min each time it is decremented, alongside the proportion of possible outcomes that will guarantee a win in a two person game.
  
  **4)** With the first argument "MAX", GHOST.jar will find the move with the most possible outcomes based on the second argument, which should be the current character or sequence of characters. 
  
 **5)** With no arguments, GHOST.jar will find the combination of two letters with the most possible outcomes by using a running max. Each time the max is incremented, it will be printed alongside the two letter combination.
 
 **6)**(work in progress)
