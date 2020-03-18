import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Hashcode {
    public static void main(String[] args) {
        int time;
        int all_books;
        int numOfLibraries;
        Scanner inp;
        String filename = "d_tough_choices";

        try {
            inp = new Scanner(new File("C:\\Users\\User\\IdeaProjects\\HashcodeWin\\" + filename + ".txt"));

            all_books = inp.nextInt();
            if(all_books<1 || all_books>100000) {
                return;
            }

            numOfLibraries = inp.nextInt();
            if(numOfLibraries<1 || numOfLibraries>100000) {
                return;
            }

            time = inp.nextInt();
            if(time<1 || time>100000) {
                return;
            }

            Book [] scoreBooks = new Book[all_books];
            for(int i=0;i<scoreBooks.length;i++) {
                scoreBooks[i] = new Book(inp.nextInt(), i);
            }

            Library [] myLibs = new Library[numOfLibraries];
            int allBooksinLibs = 0;
            for(int i=0;i<numOfLibraries;i++) {
                int libBooks = inp.nextInt();
                if(libBooks<1 || libBooks>100000) {
                    return;
                }
                allBooksinLibs += libBooks;
                int libSignUp = inp.nextInt();
                if(libSignUp<1 || libSignUp>100000) {
                    return;
                }
                int booksPerDay = inp.nextInt();
                if(booksPerDay<1 || booksPerDay>100000) {
                    return;
                }
                int [] books = new int[libBooks];
                int totalSc =0;
                for(int j=0;j<libBooks;j++) {
                    books[j] = inp.nextInt();
                    totalSc += scoreBooks[books[j]].getScore();
                }
                float avgScore = (float)totalSc/libBooks;
                avgScore = avgScore * booksPerDay;
                myLibs[i] = new Library(i,booksPerDay,libSignUp,libBooks,books,totalSc,avgScore);
            }
            if(allBooksinLibs > 1000000) {
                return;
            }
//            System.out.print(all_books+" ");
//            System.out.print(numOfLibraries+" ");
//            System.out.print(time+"\n");
//            for(int i=0;i<scoreBooks.length;i++) {
//                System.out.println(scoreBooks[i].getScore());
//            }
//            for(int i=0;i<myLibs.length;i++) {
//                System.out.print(myLibs[i].getSumOfBooks()+" ");
//                System.out.print(myLibs[i].getSignUpDays()+" ");
//                System.out.print(myLibs[i].getNumOfBooksEachDay()+"\n");
//                System.out.println(myLibs[i].getAllBooks());
//            }
            int [] libids = new int[numOfLibraries];
            float maxRatio = 0;
            Library[] sortedMyLibs = new Library[numOfLibraries];
            for(int i=0;i<myLibs.length;i++) {
                sortedMyLibs[i] = new Library(myLibs[i]);
            }

            Library[] finalLibs = new Library[myLibs.length];

            int totalDays = time;
            int counter=0;
            while(totalDays >= 0 && counter != myLibs.length) {
                for(int i=0;i<myLibs.length;i++) {
                    int scanDays = totalDays - myLibs[i].getSignUpDays();
                    if(scanDays < 0) {
                        myLibs[i].setNewRatio(0);
                        continue;
                    }
                    float newRatio = myLibs[i].getRatio()*scanDays;
                    myLibs[i].setNewRatio(newRatio);
                }
                System.out.println(totalDays);
                //sort
                for(int i=0;i<sortedMyLibs.length-1;i++) {
                    int index = i;
                    for(int j=i+1;j<sortedMyLibs.length;j++) {
                        if(sortedMyLibs[j].getRatio() > sortedMyLibs[index].getRatio()) {
                            index = j;
                        }
                    }
                    Library smallNum = new Library(sortedMyLibs[index]);
                    sortedMyLibs[index] = sortedMyLibs[i];
                    sortedMyLibs[i] = smallNum;
                }
                finalLibs[counter] = new Library(sortedMyLibs[0]);
                counter++;
                totalDays -= sortedMyLibs[0].getSignUpDays();
                sortedMyLibs[0].setRatio(0);
            }


            System.out.println(sortedMyLibs.length);
            System.out.println(counter);
            int signup = 0;
            int remain = 0;
            double myCeil;
            int myCeil2;
            for(int i=0;i<counter;i++) {
                signup += finalLibs[i].getSignUpDays();
                remain = time - signup;
                myCeil = finalLibs[i].getSumOfBooks()/finalLibs[i].getNumOfBooksEachDay();
                myCeil = Math.ceil(myCeil);
                if(myCeil <= remain) {
                    continue;
                }
                else {
                    myCeil2 = (int)Math.ceil(myCeil-remain);
                    int sum = (int)myCeil2*finalLibs[i].getNumOfBooksEachDay();
                    if((finalLibs[i].getSumOfBooks()-sum) < 0) {
                        continue;
                    }
                    int [] remBooks = new int[finalLibs[i].getSumOfBooks()-sum];
                    for(int j=0;j<remBooks.length;j++) {
                        remBooks[j] = finalLibs[i].getBook(j);
                    }
                    finalLibs[i].setBooks(remBooks);
                    finalLibs[i].setSumOfBooks(remBooks.length);
                }
            }


            File newFile = new File("output_"+filename+".txt");
            try {
                newFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileWriter myWriter = null;
            try {
                myWriter = new FileWriter(newFile);
                myWriter.write(counter+"\n");
                for (int i=0;i<counter;i++) {
                    myWriter.write(finalLibs[i].getId()+" "+finalLibs[i].getSumOfBooks()+"\n");
                    myWriter.write(""+finalLibs[i].getAllBooks()+"\n");
                }
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            System.out.println();
    }
}
