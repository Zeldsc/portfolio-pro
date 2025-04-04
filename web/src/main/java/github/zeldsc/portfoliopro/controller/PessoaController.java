package github.zeldsc.portfoliopro.controller;


import github.zeldsc.portfoliopro.dto.PessoaRequestDTO;
import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> cadastrar(@RequestBody PessoaRequestDTO dto) {
        Pessoa pessoa = Pessoa.builder()
                .nome(dto.getNome())
                .dataNascimento(dto.getDataNascimento())
                .cpf(dto.getCpf())
                .funcionario(dto.isFuncionario())
                .gerente(dto.isGerente())
                .build();

        pessoaService.cadastrar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable String cpf) {
        return pessoaService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
} 
