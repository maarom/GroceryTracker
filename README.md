# Grocery Manager

Grocery Manager is an Angular application that helps users manage their grocery items efficiently. It allows users to add, edit, delete, and search for grocery items.

## Features

- **Add Item**: Users can add new grocery items with details like name, description, price, and quantity.
- **Edit Item**: Users can modify the details of existing grocery items.
- **Delete Item**: Users can delete unwanted grocery items from the list.
- **Search**: Users can search for specific grocery items by name or ID.
- **Sort**: Users can sort the list of grocery items by name, price, or quantity.

## Technologies Used

- **Angular**: Frontend framework for building the user interface.
- **Spring Boot**: Backend framework for handling HTTP requests and data persistence.
- **RESTful API**: Communication between the frontend and backend using RESTful principles.
- **HTML/CSS**: Markup and styling for the user interface.
- **Bootstrap**: Frontend framework for responsive design and UI components.
- **Java Persistence API (JPA)**: Java programming interface for managing relational data in Java applications.

## Getting Started

To run the application locally, follow these steps:

# 1. Clone the repository 
# git clone https://github.com/maarom/GroceryTracker  
# 2. Load the pom.xml file
# You can right-click on pom.file and select *Load the maven project* 
# 3. Start the MYSQl local server
# You can setup your login and password for database inside back-end/src/main/resources/application.properties
# 4. Navigate to the front-end folder via terminal and execute *ng serve* command
# If you are in project folder, just put cd .\front-end\ in terminal and execute the command
# 5. Open your browser and visit http://localhost:4200 to view the application.



## Folder Structure
grocery-manager/
│
├── src/                     # Source files
│   ├── app/                 # Angular application files
│   │   ├── app.component.html      # Main component template
│   │   ├── app.component.ts        # Main component logic
│   │   ├── grocery.service.ts      # Service for managing grocery items
│   │   ├── item.ts                # Interface for representing grocery items
│   │   └── ...                    # Other components, services, and files
│   ├── assets/              # Static assets (images, fonts, etc.)
│   ├── index.html           # Main HTML file
│   └── ...                  # Other configuration and build files
│
├── backend/                 # Backend files (Spring Boot application)
│   ├── src/                 # Source files
│   │   ├── main/            # Main application code
│   │   ├── test/            # Test code
│   │   └── ...              # Other configuration and build files
│   ├── pom.xml              # Maven dependencies and build configuration
│   └── ...                  # Other backend-related files
│
├── README.md               # Project README file
└── ...                     # Other project-related files
