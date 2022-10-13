package com.nedstore.minhasfinancasapi.model.repository;

import com.nedstore.minhasfinancasapi.model.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
}
