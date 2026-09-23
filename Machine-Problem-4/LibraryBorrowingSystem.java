package libraryborrowingsystem;

import java.util.Scanner;

public class LibraryBorrowingSystem {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int bookNum = input.nextInt();
        input.nextLine();

        Book[] books = new Book[bookNum];

        for (int i = 0; i < bookNum; i++) {

            System.out.println("\nBook " + (i + 1));

            System.out.print("Book ID: ");
            String bookId = input.nextLine();

            System.out.print("Book title: ");
            String title = input.nextLine();

            books[i] = new Book(bookId, title);
        }

        System.out.print("\nEnter number of members: ");
        int memberNum = input.nextInt();
        input.nextLine();

        Member[] members = new Member[memberNum];

        for (int i = 0; i < memberNum; i++) {

            System.out.println("\nMember " + (i + 1));

            System.out.print("Member ID: ");
            String memberId = input.nextLine();

            System.out.print("Member name: ");
            String name = input.nextLine();

            members[i] = new Member(memberId, name);
        }

        System.out.print("\nHow many actions: ");
        int actionNum = input.nextInt();
        input.nextLine();

        for (int i = 0; i < actionNum; i++) {

            System.out.println("\nAction " + (i + 1));

            System.out.print("Member ID: ");
            String memberId = input.nextLine();

            Member member = null;

            for (int j = 0; j < members.length; j++) {
                if (members[j].getMemberId().equalsIgnoreCase(memberId)) {
                    member = members[j];
                    break;
                }
            }

            if (member == null) {
                System.out.println("Member not found.");
            } else {

                System.out.print("B for borrow or R for return: ");
                String choice = input.nextLine();

                System.out.print("Book ID: ");
                String bookId = input.nextLine();

                if (choice.equalsIgnoreCase("B")) {

                    Book book = null;

                    for (int j = 0; j < books.length; j++) {
                        if (books[j].getBookId().equalsIgnoreCase(bookId)) {
                            book = books[j];
                            break;
                        }
                    }

                    if (book == null) {
                        System.out.println("Book not found.");
                    } else {
                        if (member.borrowBook(book)) {
                            System.out.println("Book borrowed successfully.");
                        } else {
                            System.out.println("Borrowing rejected.");
                        }
                    }

                } else if (choice.equalsIgnoreCase("R")) {

                    if (member.returnBook(bookId)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Return rejected.");
                    }

                } else {
                    System.out.println("Invalid action.");
                }
            }
        }

        System.out.println("\n--- BORROWED BOOKS ---");

        for (int i = 0; i < members.length; i++) {
            members[i].displayBorrowedBooks();
            System.out.println();
        }

        System.out.println("--- AVAILABLE BOOKS ---");

        boolean availableBook = false;

        for (int i = 0; i < books.length; i++) {

            if (books[i].isAvailable()) {
                System.out.println(books[i].getBookId() + " - " + books[i].getTitle());
                availableBook = true;
            }
        }

        if (availableBook == false) {
            System.out.println("No available books.");
        }

        input.close();
    }
}