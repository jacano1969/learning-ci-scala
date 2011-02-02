package learningci.chapter02.data;

public class Movie {

    public String title;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object other) {
        Movie otherMovie = (Movie) other;
        return this.title.equals(otherMovie.title);
    }

}
