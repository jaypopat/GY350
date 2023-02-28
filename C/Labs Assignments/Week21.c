/*Graded Assignment: Flesch Readability Index
• Write a program which reads all the text in a file and computes the Flesch Readability Index for it.
• The Flesch Readability Index was invented as a simple tool for determining the legibility of a
document without linguistic analysis. It may be implemented using the following 4 steps:
1. Count all words. A word is any sequence of characters delimited by white space.
2. Count all syllables in each word. Each group of adjacent vowels (a, e, i, o, u, y) counts as one
syllable (for example, the "ea" in "real" contributes one syllable, but the "e..a" in "regal" counts
as two syllables). However, an "e" at the end of a word doesn't count as a syllable. Also, each
word has at least one syllable, even if the previous rules give a count of 0.
3. Count all sentences. A sentence is ended by a full stop, colon, semicolon, question mark, or
exclamation mark.
4. The index is computed by the following formula:*/