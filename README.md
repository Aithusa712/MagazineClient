# About this

This java project is an assignment for my Software Architectures module. The task is to create a Magazine Service Client with a fully Working GUI that can perform basic Crud functions. This application is able to load and save current sessions by deserializing and serializing the objects into a .ser file. The application is made by using a GUI Framework, JavaFx, and without any help from any GUI visual tools such as scene Builder. This program and documentation are developed and submitted before November 10, 2024.
# Folder Structure

The workspace contains three folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies
- `bin`: the folder that contains compiled output

Inside the workspace directory, I have 2 csv files:

- `loadData.ser`: is the default data I used, which is also the hard coded data inside the program
  this shouldn't be used for saving. It is exists only to be a backup and for testing.
- `testSaveData.ser`: is the actual test data to be used for loading and saving current or existing sessions.

# Installation and running

- To compile the Application, open your terminal into the workspace/project directory

  ![image](https://github.com/user-attachments/assets/444eddd2-1eb0-4c67-9967-744b4fcc3dc7)


- Now copy and paste the command below:
- For Windows: `$javac --module-path "</path/to/javafx-sdk/lib>" --add-modules javafx.controls,javafx.fxml -d bin .\src\*.java`
- For Linux: `$javac --module-path "</path/to/javafx-sdk/lib>" --add-modules javafx.controls,javafx.fxml -d bin src/*.java`

  ![image](https://github.com/user-attachments/assets/493dd3eb-8c4d-4d67-b98d-ec5d2f0a280e)

- To run the Application, open your terminal into the src folder in the workspace directory and copy and insert the command below:
- `$java --module-path "</path/to/javafx-sdk/lib>" --add-modules javafx.controls,javafx.fxml -cp bin Client`

  ![image](https://github.com/user-attachments/assets/0d72b16a-2637-472a-b685-838a9ac30001)

  - note: you will need to add the path to your own javafx-sdk lib directory

# How-to

- To use the program you will need to load the data. There are two ways to do it:

  ![image](https://github.com/user-attachments/assets/7ec26b46-e847-428a-b62d-01a7445a6254)

1.  `Load From File`: loads fileChooser for you to select a previous session or custom session.
    1. Navigate to the project directory and click either of the two .csv files inside.
    2. If you previously saved a session, you can use that session to load the data.
2.  `Load Hard Coded Data`: loads the data by calling loadData() method inside the program.

- If you have chosen `Load From File` or the `Hard Coded Data` options the program will look something like this:

  ![image](https://github.com/user-attachments/assets/fe523ad2-ba38-4b17-8da1-e1792673a76b)

- The default Layout of the program is the View layout. You can change between 3 layouts `View`, `Create`, `Edit`.

  ![image](https://github.com/user-attachments/assets/279d110f-6d30-4ab1-a166-9a56e0ed73c0)

  ## View Layout

- Inside the view layout you can view customers or supplements by clicking on their respective names. At the left top panel are the Magazine and supplements, at the left bottom panel are the customers, and the entire right panel is the information panel. You can view the information of either Customer or Supplements inside the information panel, paying customers will include an additional button in the informational Panel labeled `Show Billing History?`. When pressed, a dialogue will pop up and display the payment breakdown of that customer.

  ![Untitled](https://github.com/user-attachments/assets/50a4efda-2510-46c1-8fa2-62ef47a83498)

  ## Create Layout

- In the create Layout you can select between two things to create, a Magazine Supplement or Customer.

### Create Customer Example

- When you create a customer, that customer will automatically be subscribed to the current magazine issue.

![image](https://github.com/user-attachments/assets/b2eb1e17-4263-46c8-8d95-84ac58328713)

### Create Customer Results

![Screenshot 2024-11-09 151136](https://github.com/user-attachments/assets/51e374de-b836-4fa1-8355-70c901112db3)

### Create Supplement Example

- When a supplement is created, it will be stored somewhere temporarily. It will need to be added into a magazine the Edit View to be saved.

![image](https://github.com/user-attachments/assets/bedd3a31-6388-4d7d-b928-5f465b986700)

### Create Supplement Results

![image](https://github.com/user-attachments/assets/d1f9a611-61c4-4c88-9678-619145c4021b)

![image](https://github.com/user-attachments/assets/1a18eaf4-26ed-4294-8b26-b45792eb69fe)

## Edit Layout

- The Edit layout only consists of buttons that will pop up a series of dialogue.

  - `Edit Supplement`: Edit the name or cost of a supplement.
  - `Edit Customer`: Edit the name or email of a customer.
  - `Edit Magazine`: Edit the name or cost of a magazine.
  - `Add Supplement`: Add a user-created supplement into a existing magazine.
  - `Save to File`: Save current session, containing all the information loaded and changed.
  - `Load a new session`: Clear current session and load a new one.

  ![image](https://github.com/user-attachments/assets/6246c684-ca24-4c32-9330-dc722ef64bcc)



## Save to file and load saved data demo

### The data to be saved (Changes: added new supplement named testSupplement and new paying customer named kimPaying).

![image](https://github.com/user-attachments/assets/031fa6f4-5d6d-47dc-85de-4e3e6abb9fbf)

### Navigate to folder you want to save the data to

![image](https://github.com/user-attachments/assets/76c3eaab-d967-4287-9d51-0ef61eb412fd)


### Receive Success prompt

![image](https://github.com/user-attachments/assets/933964b6-88f9-4b53-bdd1-b9633cb115e6)

### Restart the application and load previous saved data

- it should contain the data from your previous session.

![image](https://github.com/user-attachments/assets/c053f06b-8809-429e-b28f-11a317608812)
