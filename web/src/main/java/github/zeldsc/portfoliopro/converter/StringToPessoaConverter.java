package github.zeldsc.portfoliopro.converter;

import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.service.PessoaService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPessoaConverter implements Converter<String, Pessoa> {

    private final PessoaService pessoaService;

    public StringToPessoaConverter(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Override
    public Pessoa convert(String cpf) {
        if (cpf.isBlank()) {
            return null;
        }
        return pessoaService.buscarPorCpf(cpf).orElseGet(null);
    }
}