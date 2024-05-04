package valerio.BingeBookBE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.repositories.GenreDAO;

@Component
public class DataInit implements CommandLineRunner {

    private final GenreDAO genreDAO;

    @Autowired
    public DataInit(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public void run(String... args) throws Exception {

        if (genreDAO.count() == 0) {
            saveGenre("Action", genreDAO);
            saveGenre("Drama", genreDAO);
            saveGenre("Comedy", genreDAO);
            saveGenre("Horror", genreDAO);
            saveGenre("Romance", genreDAO);
            saveGenre("Sci-Fi", genreDAO);
            saveGenre("Thriller", genreDAO);
            saveGenre("Mystery", genreDAO);
            saveGenre("Fantasy", genreDAO);
            saveGenre("Adventure", genreDAO);
            saveGenre("Crime", genreDAO);
            saveGenre("History", genreDAO);
        }

//        SerieTV serieTV1 = new SerieTV();
//        serieTV1.setTitle("Breaking Bad");
//        serieTV1.getGenres().add(genre1);
//        serieTV1.getGenres().add(genre2);
//        serieTVRepository.save(serieTV1);

        // You can add more SerieTV and Genre objects as needed
    }

    private void saveGenre(String name, GenreDAO genreDAO) {
        Genre _genre = new Genre();
        _genre.setName(name);
        genreDAO.save(_genre);
    }
}