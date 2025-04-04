package github.zeldsc.portfoliopro.util;

import github.zeldsc.portfoliopro.entity.Paginacao;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PaginacaoPageableConverter {

    private PaginacaoPageableConverter() {}

    public static Pageable toPageable(Paginacao paginacao) {
        Sort sort = Sort.by(Sort.Direction.valueOf(paginacao.direcao().name()), paginacao.ordenarPor());
        return PageRequest.of(paginacao.pagina(), paginacao.tamanho(), sort);
    }
}