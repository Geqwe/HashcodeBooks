public class Library {
    private int id;
    private int sumOfBooks;
    private int numOfBooksEachDay;
    private int signUpDays;
    private int [] books;
    private float ratio;
    private int totalScore;
    private float newRatio;

    public float getNewRatio() {
        return newRatio;
    }

    public void setNewRatio(float newRatio) {
        this.newRatio = newRatio;
    }

    public Library(int id, int numOfBooksEachDay, int signUpDays, int sumOfBooks, int []books, int totalScore, float ratio) {
        this.id = id;
        this.numOfBooksEachDay = numOfBooksEachDay;
        this.signUpDays = signUpDays;
        this.sumOfBooks = sumOfBooks;
        this.books = books;
        this.totalScore = totalScore;
        this.ratio = ratio;
    }

    public Library(Library oldLib) {
        this.id = oldLib.id;
        this.numOfBooksEachDay = oldLib.numOfBooksEachDay;
        this.signUpDays = oldLib.signUpDays;
        this.sumOfBooks = oldLib.sumOfBooks;
        this.books = oldLib.books;
        this.totalScore = oldLib.totalScore;
        this.ratio = oldLib.ratio;
        this.newRatio = oldLib.ratio;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int[] getBooks() {
        return books;
    }

    public String getAllBooks() {
        String booklets = "";
        for(int i=0;i<this.books.length;i++) {
            if(i==this.books.length-1) {
                booklets = booklets + this.books[i];
            }
            else {
                booklets = booklets + this.books[i] + " ";
            }
        }
        return booklets;
    }

    public int getBook(int i) {
        return books[i];
    }

    public void setBooks(int[] books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSumOfBooks() {
        return sumOfBooks;
    }

    public void setSumOfBooks(int sumOfBooks) {
        this.sumOfBooks = sumOfBooks;
    }

    public int getNumOfBooksEachDay() {
        return numOfBooksEachDay;
    }

    public void setNumOfBooksEachDay(int numOfBooksEachDay) {
        this.numOfBooksEachDay = numOfBooksEachDay;
    }

    public int getSignUpDays() {
        return signUpDays;
    }

    public void setSignUpDays(int signUpDays) {
        this.signUpDays = signUpDays;
    }


}
