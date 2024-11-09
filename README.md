## About this

This java project is an assignment for my Software Architectures modules. The task is to create a Magazine Service Client
with a fully working GUI that can perform CRUD functions. The application is able to load and save the current session into a csv file
for later use. The application is made using JavaFX, all coded without any help of GUI visual tools such as Scene Builder

## Folder Structure

The workspace contains three folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `bin`: the folder that contains compiled output

Inside the workspace directory, I have 2 csv files:

- `loadData.csv`: is the default data I used, which is also the hard coded data inside the program
  this shouldn't be used for saving. It is exists only to be a backup and testing.
- `testSaveData.csv`: is the actual test data to be used for loading and saving current or existing sessions.

## Installation and running

- To compile the Application copy and insert the command below:

- `$javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d bin src/\*.java`

- To run the Application copy and insert the command below:

- `$java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp bin Client`

- note: you will need to add the path to your own javafx-sdk lib directory

## How-to

- To use the program you will need to load the data
