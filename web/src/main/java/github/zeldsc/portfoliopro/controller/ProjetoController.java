package github.zeldsc.portfoliopro.controller;

import github.zeldsc.portfoliopro.dto.ProjetoDTO;
import github.zeldsc.portfoliopro.entity.Paginacao;
import github.zeldsc.portfoliopro.entity.Pessoa;
import github.zeldsc.portfoliopro.entity.Projeto;
import github.zeldsc.portfoliopro.enums.Risco;
import github.zeldsc.portfoliopro.enums.Status;
import github.zeldsc.portfoliopro.port.ProjetoPaginado;
import github.zeldsc.portfoliopro.service.PessoaService;
import github.zeldsc.portfoliopro.service.ProjetoService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projetos")
public class ProjetoController extends BaseController {

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;

    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String listar(@RequestParam(defaultValue = "0") int pagina,
                         @RequestParam(defaultValue = "10") int tamanho,
                         Model model) {
        ProjetoPaginado paginado = projetoService.listarPaginado(new Paginacao(pagina, tamanho));
        model.addAttribute("projetos", paginado.projetos().stream().map(ProjetoDTO::fromDomain).toList());
        model.addAttribute("pagina", pagina);
        model.addAttribute("total", paginado.total());
        return "projeto/lista";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        prepararFormulario(model, new ProjetoDTO());
        return "projeto/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute ProjetoDTO dto, Model model, HttpServletRequest request) {
        try {
            Projeto projeto = dto.toDomain();
            projetoService.cadastrar(projeto);
            adicionarMensagemSucesso(request, "Projeto cadastrado com sucesso.");
            return "redirect:/projetos";
        } catch (Exception e) {
            adicionarMensagemErro(request, "Erro ao cadastrar projeto: " + e.getMessage());
            prepararFormulario(model, dto);
            return "projeto/form";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model, HttpServletRequest request) {
        try {
            Projeto projeto = projetoService.buscarPorId(id);
            ProjetoDTO dto = ProjetoDTO.fromDomain(projeto);
            prepararFormulario(model, dto);
            return "projeto/form";
        } catch (Exception e) {
            adicionarMensagemErro(request, "Erro ao carregar projeto: " + e.getMessage());
            return "redirect:/projetos";
        }
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute ProjetoDTO dto, Model model, HttpServletRequest request) {
        try {
            projetoService.atualizar(dto.getId(), dto.toDomain());
            adicionarMensagemSucesso(request, "Projeto atualizado com sucesso.");
            return "redirect:/projetos";
        } catch (Exception e) {
            adicionarMensagemErro(request, "Erro ao atualizar projeto: " + e.getMessage());
            prepararFormulario(model, dto);
            return "projeto/form";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id, HttpServletRequest request) {
        try {
            projetoService.excluir(id);
            adicionarMensagemSucesso(request, "Projeto excluído com sucesso.");
        } catch (Exception e) {
            adicionarMensagemErro(request, "Erro ao excluir projeto: " + e.getMessage());
        }
        return "redirect:/projetos";
    }

    private void prepararFormulario(Model model, ProjetoDTO dto) {
        model.addAttribute("projeto", dto);
        model.addAttribute("statusList", Status.values());
        model.addAttribute("riscoList", Risco.values());

        List<Pessoa> pessoas = pessoaService.listar();
        model.addAttribute("funcionarioList", pessoas.stream().filter(Pessoa::isFuncionario).toList());
        model.addAttribute("gerenteList", pessoas.stream().filter(Pessoa::isGerente).toList());
    }
} 
