package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Transactional
    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar (@PageableDefault(page = 0, size = 10, sort = "nome") Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
    @Transactional
    @PutMapping
    public void atualizar(@RequestBody @Valid DadosAtualizarPaciente dados){
            var paciente = repository.getReferenceById(dados.id());
            paciente.atualizarInformacoes(dados);
        }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        repository.deleteById(id);
    }
}
