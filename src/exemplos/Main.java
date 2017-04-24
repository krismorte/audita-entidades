/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplos;

import auditoria.AuditoriaUtil;
import java.time.LocalDate;

/**
 *
 * @author krisnamourtscf
 */
public class Main {

    public static void main(String[] args) {
        AuditoriaUtil auditoriaUtil = new AuditoriaUtil();

        EntidadeTeste entidade1 = new EntidadeTeste();
        entidade1.setId(new Long(45));
        entidade1.setNome("Entidade Teste");
        entidade1.setDataDeNascimento(LocalDate.of(1990, 3, 15));

        //Trecho de código besta, pois o ID logicamente será gerado pelo Banco De Dados e não pela aplicação
        System.out.println(auditoriaUtil.auditoriaSimples(entidade1, "Objeto Criado"));

        EntidadeTeste entidade2 = null;
        try {
            entidade2 = (EntidadeTeste) entidade1.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        entidade2.setNome("Nova Entidade");

        //Nesse trecho as duas versões serão comparadas p/ identificar quais campos foram alterados
        System.out.println(auditoriaUtil.auditoriaCompleta(entidade1, entidade2));

    }

}
