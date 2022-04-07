# LogReader

Application is intended for multi-thread file reading to find required tokens. 

Search properties initializes from src/yaml: sourceDirectory, targetDirectory, threadPoolSize, stringsToFind and if you need encoding.

Program starts a separate thread for each file in sourceDirectory (but not more threadPoolSize value) and saves result in targetDirectory as it executes. 

Benefits of using app are lightning-fast searches in arrays of large files, such as log archives, etc.
