package m2i.ma.Brikol.Categorie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {


    Categorie getCategorieById(Long id);
}
