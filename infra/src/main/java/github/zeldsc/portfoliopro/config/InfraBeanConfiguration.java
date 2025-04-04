package github.zeldsc.portfoliopro.config;

import github.zeldsc.portfoliopro.database.repository.PessoaRepositoryAdapter;
import github.zeldsc.portfoliopro.database.repository.ProjetoRepositoryAdapter;
import github.zeldsc.portfoliopro.service.PessoaService;
import github.zeldsc.portfoliopro.service.ProjetoService;
import github.zeldsc.portfoliopro.service.PessoaServiceImpl;
import github.zeldsc.portfoliopro.service.ProjetoServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfraBeanConfiguration {

    @Bean
    public PessoaService pessoaService(PessoaRepositoryAdapter repository) {
        return new PessoaServiceImpl(repository);
    }

    @Bean
    public ProjetoService projetoService(ProjetoRepositoryAdapter repository) {
        return new ProjetoServiceImpl(repository);
    }
}
