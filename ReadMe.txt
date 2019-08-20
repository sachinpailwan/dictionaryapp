Dictionary Application
1. Upload Screen
   http://localhost:8080/
   This screen will help user to upload dictionary content file.
   Application will accept following file content format
    1. Text File
    2. Each word and it's meaning in line by line
    3. word = meaning 1, meaning 2 etc. in single line.
2. http://localhost:8080/upload
   This API will upload file to application.
3. http://localhost:8080/search/{word}
   This API will search given word into application and will redirect to search result page.

Dictionary Storagre DataStructure
 Trie Data structure used for dictionary storage.
  
