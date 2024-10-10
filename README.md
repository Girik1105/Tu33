# Booked In: Project Overview

## Project Description
**Booked In** is a user management system that allows users to create accounts, log in, and manage their roles within the application. It is designed to provide a structured environment for admins, students, and professors to interact with the system based on their roles. The application uses JavaFX for its graphical user interface and H2 for database management.

## Key Functionalities
### User Management
- **Create Account**: First-time users can register by providing their username, password, first name, middle name, last name, and preferred name (optional).
- **Log In/Log Out**: Users can log in with their username and password and log out when they have finished using the system.
- **Finish Account Setup**: New users complete their account setup by verifying their details.

### Admin Functions
- **Invite User**: Admins can invite new users (students or professors) by generating one-time invite codes, allowing them to create accounts.
- **Role Management**: Admins can add or remove roles for users, such as "Admin," "Student," or "Professor."
- **Reset Password**: Admins can issue a one-time password for users to reset their accounts securely.
- **Delete User**: Admins can delete user accounts after confirming the action to prevent accidental deletions.
- **View User List**: Admins can see a list of all users, including their first name, middle name, last name, preferred name, and roles.

### Invitation System
- **Invite Code**: Users invited by an admin use a one-time code to create their accounts, setting up a username and password for access.

### User Dashboards
- **Admin Dashboard**: Provides access to manage user roles, invites, and account setups.
- **Student Dashboard**: Allows students to access their relevant sections after logging in.
- **Professor Dashboard**: Provides professors with the appropriate tools and access levels for their role.

## Project Requirements
To run **Booked In**, the following dependencies are required:

### Development Environment
- **Java JDK 11 or above**: Required to compile and run the Java-based application.
- **JavaFX**: For creating the graphical user interface.
- **Maven**: For managing project dependencies and building the application.

### Database
- **H2 Database**: An embedded database for storing user information and managing accounts.

### Additional Libraries
- **JUnit**: For writing and running unit tests.

### IDE Recommendation
- **IntelliJ IDEA** or **Eclipse**: Recommended for Java development and JavaFX integration.

