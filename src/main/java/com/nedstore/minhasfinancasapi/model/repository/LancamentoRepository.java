package com.nedstore.minhasfinancasapi.model.repository;

import com.nedstore.minhasfinancasapi.model.entity.Lancamento;
import com.nedstore.minhasfinancasapi.model.enums.StatusLancamento;
import com.nedstore.minhasfinancasapi.model.enums.TipoLancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    @Query(value =
            "SELECT SUM(L.valor) FROM Lancamento L JOIN L.usuario U "
                    + "WHERE U.id = :idUsuario AND L.tipo = :tipo and L.status = :status GROUP BY U ")
    BigDecimal obterSaldoPorTipoLancamentoEUsuarioEStatus(
            @Param("idUsuario") Long idUsuario,
            @Param("tipo") TipoLancamento tipo,
            @Param("status") StatusLancamento status);
}
