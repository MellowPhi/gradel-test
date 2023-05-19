# Text-Readability-Score TO test with gradle build

Generates a readability score based 
- [Flesch–Kincaid readability](https://en.wikipedia.org/wiki/Flesch%E2%80%93Kincaid_readability_tests)
- [Automated readability index (ARI)](https://en.wikipedia.org/wiki/Automated_readability_index)
- [Simple Measure of Gobbledygook (SMOG index)](https://en.wikipedia.org/wiki/SMOG)

This gives an idea on how difficult a text is read. 


Example output of running this program on a text file `in.txt`
```
Generating the readability score for: 
Readability is the ease with which a reader can understand a written text.
In natural language, the readability of text depends on its content and its presentation.Researchers have used various factors to measure readability.
Readability is more than simply legibility, which is a measure of how easily a reader can distinguish individual letters or characters from each other.Higher readability eases reading effort and speed for any reader, but it is especially important for those who do not have high reading comprehension.
In readers with poor reading comprehension, raising the readability level of a text from mediocre to good can make the difference between success and failure

Words: 108
Sentences: 6
Characters: 580
Syllables: 196
Polysyllables: 20
Automated Readability Index: 12.86
Flesch–Kincaid readability tests: 12.84
Simple Measure of Gobbledygook: 13.55
This text should be understood by 17-18 year-olds.



```
