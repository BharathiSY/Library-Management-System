package com.library.service;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.library.model.Book;
import com.library.model.Student;

public class LibraryService {

    // Store all books
    private ArrayList<Book> books = new ArrayList<>();

    // Store issued books
    private Map<String, Book> issuedBooks = new HashMap<>();

    // Store due dates of issued books
    private Map<String, LocalDate> dueDates = new HashMap<>();

    // Add Book
    public void addBook(Book book) {
        books.add(book);
    }

    // Search Book
    public void searchBook(String keyword) {

        boolean found = false;

        for (Book book : books) {

            if (book.getBookId().equalsIgnoreCase(keyword)
                    || book.getTitle().equalsIgnoreCase(keyword)
                    || book.getAuthor().equalsIgnoreCase(keyword)) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Display Available Books
    public void displayAvailableBooks() {

        System.out.println("\nAvailable Books:");

        for (Book book : books) {

            if (book.isAvailable()) {
                System.out.println(book);
            }
        }
    }

    // Approve and Issue Book
    public void issueBook(String bookId, Student student) {

        for (Book book : books) {

            if (book.getBookId().equalsIgnoreCase(bookId)) {

                if (!book.isAvailable()) {
                    System.out.println("Book is already issued.");
                    return;
                }

                // Librarian approves the issue
                book.setAvailable(false);

                issuedBooks.put(bookId, book);

                // Due date = 14 days from today
                LocalDate dueDate = LocalDate.now().plusDays(14);
                dueDates.put(bookId, dueDate);

                System.out.println("Book issued successfully.");
                System.out.println("Student: " + student.getStudentName());
                System.out.println("Due Date: " + dueDate);

                return;
            }
        }

        System.out.println("Book not found.");
    }

    // Return Book
    public void returnBook(String bookId) {

        Book book = issuedBooks.get(bookId);

        if (book == null) {
            System.out.println("This book is not issued.");
            return;
        }

        LocalDate dueDate = dueDates.get(bookId);

        LocalDate returnDate = LocalDate.now();

        long lateDays = 0;

        if (returnDate.isAfter(dueDate)) {
            lateDays = ChronoUnit.DAYS.between(dueDate, returnDate);
        }

        double fine = calculateFine(lateDays);

        book.setAvailable(true);

        issuedBooks.remove(bookId);
        dueDates.remove(bookId);

        System.out.println("Book returned successfully.");

        if (fine > 0) {
            System.out.println("Late Days: " + lateDays);
            System.out.println("Fine Amount: ₹" + fine);
        } else {
            System.out.println("No fine.");
        }
    }

    // Fine Calculator
    public double calculateFine(long lateDays) {

        double finePerDay = 5.0;

        return lateDays * finePerDay;
    }

    // Due Date Reminder
    public void checkDueDateReminder() {

        LocalDate today = LocalDate.now();

        for (Map.Entry<String, LocalDate> entry : dueDates.entrySet()) {

            String bookId = entry.getKey();
            LocalDate dueDate = entry.getValue();

            if (!dueDate.isAfter(today)) {
                System.out.println(
                        "Reminder: Book " + bookId
                        + " is due today or overdue.");
            }
        }
    }

    // Generate Report
    public void generateReport() {

        System.out.println("\n===== LIBRARY REPORT =====");

        System.out.println("Total Books: " + books.size());
        System.out.println("Issued Books: " + issuedBooks.size());
        System.out.println("Available Books: "
                + (books.size() - issuedBooks.size()));

        System.out.println("==========================");
    }
}
