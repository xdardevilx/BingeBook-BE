package valerio.BingeBookBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import valerio.BingeBookBE.config.RoleEnum;
import valerio.BingeBookBE.entity.Genre;
import valerio.BingeBookBE.entity.Role;
import valerio.BingeBookBE.repositories.GenreDAO;
import valerio.BingeBookBE.repositories.RoleDAO;

@Component
public class DataInit implements CommandLineRunner {

    private final GenreDAO genreDAO;
    private final RoleDAO roleDAO;

    @Autowired
    public DataInit(GenreDAO genreDAO, RoleDAO roleDAO) {
        this.genreDAO = genreDAO;
        this.roleDAO = roleDAO;
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

        if (roleDAO.count() == 0) {
            saveRole(RoleEnum.ADMIN.toString(), roleDAO);
            saveRole(RoleEnum.USER.toString(), roleDAO);
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

    private void saveRole(String name, RoleDAO roleDAO) {
        Role _role = new Role();
        _role.setName(name);
        roleDAO.save(_role);
    }
}