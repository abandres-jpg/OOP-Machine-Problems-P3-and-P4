package libraryborrowingsystem;

public class Member {

    private String memberId;
    private String name;
    private Book[] borrowedBooks;
    private int borrowedCount;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        borrowedBooks = new Book[3];
        borrowedCount = 0;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book book) {

        if (!book.isAvailable()) {
            return false;
        }

        if (borrowedCount >= 3) {
            return false;
        }

        borrowedBooks[borrowedCount] = book;
        borrowedCount++;
        book.borrowBook();

        return true;
    }

    public boolean returnBook(String bookId) {

        for (int i = 0; i < borrowedCount; i++) {

            if (borrowedBooks[i].getBookId().equalsIgnoreCase(bookId)) {

                borrowedBooks[i].returnBook();

                for (int j = i; j < borrowedCount - 1; j++) {
                    borrowedBooks[j] = borrowedBooks[j + 1];
                }

                borrowedBooks[borrowedCount - 1] = null;
                borrowedCount--;

                return true;
            }
        }

        return false;
    }

    public void displayBorrowedBooks() {

        System.out.println("Member: " + name);

        if (borrowedCount == 0) {
            System.out.println("No borrowed books.");
        } else {
            for (int i = 0; i < borrowedCount; i++) {
                System.out.println("- " + borrowedBooks[i].getTitle());
            }
        }
    }
}