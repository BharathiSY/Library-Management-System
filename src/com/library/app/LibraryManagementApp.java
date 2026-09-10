package com.library.app;

import java.util.Scanner;

import com.library.model.Book;
import com.library.model.Student;
import com.library.service.LibraryService;

public class LibraryManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LibraryService library = new LibraryService();

        // Sample books
        library.addBook(new Book("B101", "Life", "Kiram M"));
        library.addBook(new Book("B102", "Facts", "Ram"));
        library.addBook(new Book("B103", "Skills", "Josh"));

        int choice;

        do {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book");
            System.out.println("3. View Available Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Due Date Reminder");
            System.out.println("7. Generate Report");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

            case 1:

                System.out.print("Enter Book ID: ");
                String bookId = sc.nextLine();

                System.out.print("Enter Book Title: ");
                String title = sc.nextLine();

                System.out.print("Enter Author Name: ");
                String author = sc.nextLine();

                library.addBook(new Book(bookId, title, author));

                break;

            case 2:

                System.out.print("Enter Book ID, Title or Author: ");
                String keyword = sc.nextLine();

                library.searchBook(keyword);

                break;

            case 3:

                library.displayAvailableBooks();

                break;

            case 4:

                System.out.print("Enter Book ID: ");
                String issueBookId = sc.nextLine();

                System.out.print("Enter Student ID: ");
                String studentId = sc.nextLine();

                System.out.print("Enter Student Name: ");
                String studentName = sc.nextLine();

                Student student = new Student(studentId, studentName);

                library.issueBook(issueBookId, student);

                break;

            case 5:

                System.out.print("Enter Book ID to return: ");
                String returnBookId = sc.nextLine();

                library.returnBook(returnBookId);

                break;

            case 6:

                library.checkDueDateReminder();

                break;

            case 7:

                library.generateReport();

                break;

            case 8:

                System.out.println("Thank you for using Library Management System.");

                break;

            default:

                System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 8);

        sc.close();
    }
}
