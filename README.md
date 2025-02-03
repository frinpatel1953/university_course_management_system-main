## DIRECTORY STRUCTURE

```markdown
. 📂 PROJECT
├── 📄 README.md
├── 📄 requirements.txt
└── 📂 src/
│  └── 📂 data/
│    ├── 📄 courses.dat
│    ├── 📄 instructors.dat
│    ├── 📄 students.dat
│  └── 📂 exceptions/
│    ├── 📄 InvalidDataException.java
│    ├── 📄 RecordNotFoundException.java
│  └── 📂 main/
│    ├── 📄 Main.java
│    ├── 📄 Menu.java
│  └── 📂 models/
│    ├── 📄 Course.java
│    ├── 📄 Instructor.java
│    ├── 📄 Student.java
│  └── 📂 storage/
│    ├── 📄 CourseFileHandler.java
│    ├── 📄 InstructorFileHandler.java
│    ├── 📄 StudentFileHandler.java
│  └── 📂 utils/
│    └── 📄 BinaryFileCheck.java
```



## How to Run the Project

Follow these steps to compile and run the project:

1. **Extract the ZIP file**  
   - Unzip the downloaded project archive to a preferred directory.  

2. **Compile the Java source files**  
   - Run the following command to compile all Java files inside the `src/` directory:  
     ```sh
     javac src/**/*.java
     ```
   - This generates `.class` files required for execution.  

3. **Navigate to the source directory**  
   - Move into the `src/` directory:  
     ```sh
     cd src
     ```
   - This ensures the correct working directory for running the application.  

4. **Run the main application**  
   - Execute the following command to start the program:  
     ```sh
     java main.Main
     ```
   - Ensure that `main.Main` is the correct entry point of your application.  
