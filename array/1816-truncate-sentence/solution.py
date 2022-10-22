class Solution:
    def truncateSentence(self, s: str, k: int) -> str:
        # create a list of words from string s
        word_list = s.split(' ')
        
        # create an empty string for my truncated words
        truncated_word = ""
        
        # loop through the word_list to k-1 number of times
        for i in range(k):
            # add word to truncated_word string
            truncated_word += word_list[i]
            # add a space between the words as lond as not at last word in string 
            if i < (k - 1):
                truncated_word += " "
            
        return truncated_word 