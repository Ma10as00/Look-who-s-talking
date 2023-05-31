# etude 05 - Look who is talking



## Program description

Program translating simple sentences (pronoun + verb) from English to Māori.
When using ambiguous pronouns, a specification needs to follow, e.g. 
"We (2 excl)", meaning it is refering to two people, excluding the listener.
The program can only translate the following eight verbs:
- to go
- to make
- to see
- to want
- to call
- to ask
- to read
- to learn

## Structure

The program performs the translation via an intermediate stage of abstract grammatical properties.

The program defines an EnglishReader that abstracts all relevant information from the English input into properties.

The abstraction is then utilized by a MāoriWriter, who builds Māori output based on the properties.

The abstraction holds the following properties:
- Person: First-, second- or third-person, inclusive or exclusive of the listener
- noPeople: The number of people referred to (1, 2 or >2)
- Tense: Past, present or future tense
- Meaning: The semantical meaning of the 8 verbs

## Testing

Tested by a JUnit test file, reading from testInput.txt and making sure, the output matches the data in testOutput.txt.