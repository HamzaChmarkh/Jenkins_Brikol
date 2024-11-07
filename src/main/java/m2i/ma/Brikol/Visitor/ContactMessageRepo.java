package m2i.ma.Brikol.Visitor;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepo extends JpaRepository<ContactMessage, Long> {
}
