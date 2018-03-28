#Idealo Coding Task


## Implementation details
There are 3 services, in 3 packages : readable, extendable and effective. The main application analyzes the inout parameters and 
calls of the those services to get the solution: The services are totally independed, though implement the 
ICodekataService interface.

### Readable solution
This service implements pretty naive and simple alogithm, that filters all the word of size  6, and for each one of them looks for the pair, that matches it
If found, the readable result line is built and added to output. The check algorithm is also simple and naive: we brake the word to 2 parts by all possible ways 
and check if both parts are in the list. If yes, the word is matches the criteria and we have also the prefix and suffix.
The algorithm is not most effective, the size of words to be checked (6) is hardcoded, but we think about readability only.
The code is made simple, just 3 small methods, minimum functional programming, etc. 

### Effective solution
This service implements more complicated algorithm and provides the fastest solution.
First the data sutcture for processing is built it's mapping of all the words of length up to 6, by their length.
The map is built while reading the input file, as parallele stream (the concurrent data structures are used)
From this, the collection of al words of size 6 is taken (done in O(1) time) and matched with pairs of groups of possible prefixes and suffixes for 
any possible length of the prefix (1 to 5). For each word, the prefix/suffix pair is built and check for the presence in the groups for the appropriate length.
All the processing is done in parallel streams. The code is pretty fast (runs in 305 ms for the given input), but less readable 
(the algorithm should be explained explicitely) and the length of the words and rules for matching are hardcoded. 

### Extendable solution
In this solution, I defined the interfaces for the input and output (IProcessible and IProcessibleResult)
and assumed, that the input words should be processed by the set of rules, and teh results for each rule should be combined into the final result.
For the specific requirement of the assignment, the rules are: 
* size of input word is 6 (checked by LengthRule)
* word is result of concatenation of 2 other words (checked by ConcatenationRule)

The rule classes implement IRule interface and set in the IProcessible as the member (and can be updated dynamically)
The implementation algorithm is not very effective, as I'm not resuing and preprocess the input set.
It can be improved, using the approach of the effective solution. The readability is also lacking as, we have too many clases and interfaces, 
but the solution can be easily extended and be updated (use other word length, search for combination of more then 2 words, 
use additional filters, etc)




## Testing
Due to lack of time, I just created the unit tests for Service class of each of the implementations.
I copied the test cases and updated them to match each configuration, again, due to lack of time.
I didn't create the unit tests for main class, and for code, that reads the input file.
I totally understand, that the testing part is missing a lot, but I had to include at least the unit tests for
the service logic.

## Used frameworks and libraries
I implemented the task in pure java, but used Maven as the tool for building the program and Junit as the library for unit tests.
slf4j is used for logging and lombock library for easy logging setup.

## Build and Execution
To build the application run "mvn clean install" command
Then execute the java class EmailApplication with the following arguments: 
* "-i \<path to input file\>" - required
* "-o \<implementation to run\>" - required, should be one of "READABLE", "EFFECTIVE", "EXTENDABLE"
