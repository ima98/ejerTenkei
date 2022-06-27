package concesionario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


interface Repositorio extends JpaRepository<Coche, Long> {
	List<Coche> findBymarca(String marca);

}