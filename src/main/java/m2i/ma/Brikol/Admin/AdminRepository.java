package m2i.ma.Brikol.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    // Additional custom queries for Admin can go here, if needed
    //
}


