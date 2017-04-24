/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author krisnamourtscf
 */
@Entity
public class EntidadeTeste implements Serializable, Cloneable {

    @Id
    private Long id;
    @Column(length = 70)
    private String nome;
    @Column
    private LocalDate dataDeNascimento;

    public EntidadeTeste() {
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataDeNascimento
     */
    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * @param dataDeNascimento the dataDeNascimento to set
     */
    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        EntidadeTeste cloned = (EntidadeTeste) super.clone();
        cloned.setNome(cloned.getNome());
        cloned.setDataDeNascimento(cloned.getDataDeNascimento());
        // the above is applicable in case of primitive member types, 
        // however, in case of non primitive types
        // cloned.setNonPrimitiveType(cloned.getNonPrimitiveType().clone());
        return cloned;
    }

}
