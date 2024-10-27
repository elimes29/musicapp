package com.aluracursos.musicapp.repository;

import com.aluracursos.musicapp.modelo.Cantante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CantanteRepository extends JpaRepository<Cantante, Long> {

    public Optional<Cantante> getFirstCantanteByNombreContainsIgnoreCase(String nombre);


}
