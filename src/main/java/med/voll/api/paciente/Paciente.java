package med.voll.api.paciente;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;

    @NotBlank @Pattern(regexp = "\\d{11}")
    private String cpf;

    @NotBlank @Pattern(regexp = "\\d{11}")
    private String telefone;

    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dados){
            this.nome = dados.nome();
            this.email = dados.email();
            this.telefone = dados.telefone();
            this.endereco = new Endereco(dados.endereco());
            this.cpf = dados.cpf();

    }

    public void atualizarInformacoes(DadosAtualizarPaciente dados) {
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }
    }
}
