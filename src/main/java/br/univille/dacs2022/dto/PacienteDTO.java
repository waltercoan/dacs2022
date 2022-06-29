package br.univille.dacs2022.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

public class PacienteDTO {
    private long id;
    @NotBlank(message = "O campo nome não pode ser deixado em branco")
    @NotNull
    private String nome;
    @Pattern(regexp = "Masculino|Feminino", 
             flags = Pattern.Flag.CANON_EQ,
             message = "Valor inválido, utilize Masculino ou Feminino")
    private String sexo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    
}
