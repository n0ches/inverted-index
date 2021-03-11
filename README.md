An inverted index is an index data structure, which is used to map all documents with their content. It keeps a word and all documents containing this word. There are two types of inverted indexes: 
    • Record-level inverted index contains a list of references to documents for each word. 
    • Word-level inverted index contains the positions of each word within a document. 
Inverted index allows fast full text searches and is the most popular data structure used in document retrieval systems, used on a large scale for example in search engines. Its disadvantage is large storage overhead and high maintenance costs on update, delete, and insert.
In this assignment, you are expected to implement a record-level inverted index structure using your own hash table implementation in Java programming language. Your index structure will be used to find all documents that contain a particular word (e.g., return all documents in which "computer" occurs).
To build an inverted index, you should fetch all the documents, ignore any punctuation mark, remove stop words (stop words are most the frequent ones and useless words in documents, such as “I”, “the”, “we”, “is”, “an”), and then index each document with the remaining words.


# Main Functionalities
    • put(Key k, Value v):
        If a word (k) is already present, then add a reference of the document (v) to the index; otherwise create a new entry. 
        You should store the frequency of each word with the document identifier.

    • Value get(Key k):
        Search the given word (k) in the hash table. If the word is available in the table, then return an output as shown below, 
        otherwise return a “not found” message to the user.
        
    • remove(Key k)
        Remove the given word (k) and the associated value from the inverted index.
        
    • resize(int capacity)
        Make the hash table dynamically growable. Put method should double the current table size if the hash table 
        reach the maximum load factor. 
        
# Hash Function
To specify an index corresponding to given string key, firstly you should generate an integer hash code by using a special function. Then, resulting hash code has to be converted to the range 0 to N-1 using a compression function, such as modulus operator (N is the size of hash table). You are expected to implement two different hash functions including simple summation function and polynomial accumulation function.
        
        - Simple Summation Function (SSF)
        - Polynomial Accumulation Function (PAF)
        
# Collision Handling

        - Linear Probing (LP):
            Linear probing handles collisions by placing the colliding item in the next (circularly) available table cell.
            
        - Double Hashing (DH):
             Double hashing uses a secondary hash function d(k) and handles collisions by placing an item in the first available cell of the series.
        
        

