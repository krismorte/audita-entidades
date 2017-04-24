/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auditoria;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import javax.persistence.Id;

/**
 *
 * @author krisnamourtscf
 */
public class AuditoriaUtil {

    /**
     * Método utilizado que encontra o ID do objeto criado/removido e monta a
     * mensagem de retorno para efeitos de auditoria.
     *
     * @param object Objeto Entity 
     * @param mensage Texto informativo
     * @return
     */
    public String auditoriaSimples(Object object, String mensage) {
        try {
            String id = encontraID(object);

            return "" + object.getClass().getCanonicalName() + " ID{" + id + "} " + mensage;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Método utilizado para identificar quais atributos sofreram alterações
     * entre as versão do objetos passados
     *
     * @param oldObject Versão inicial do Objeto Entity
     * @param newObject Nova versão do Objeto Entity
     * @return
     */
    public String auditoriaCompleta(Object oldObject, Object newObject) {
        try {
            String id = encontraID(newObject);
            String mensage = encontraAlteracoes(oldObject, newObject);

            return "" + newObject.getClass().getCanonicalName() + " ID{" + id + "} " + mensage;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Encontra o ID do objeto que está sendo auditado
     *
     * @param newObject
     * @return
     * @throws Exception
     */
    private String encontraID(Object newObject) throws Exception {
        String id = "";

        for (Field field : newObject.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (annotation.annotationType().equals(Id.class)) {
                    if (field.get(newObject) != null) {
                        id += "," + field.get(newObject).toString();
                        break;
                    }
                }
            }
        }
        id = id.replace(",", "");
        return id;
    }

    /**
     * Busca as alterações feitas entre uma versão antiga e a nova da entidade
     * em questão
     *
     * @param oldObject
     * @param newObject
     * @return
     * @throws Exception
     */
    private String encontraAlteracoes(Object oldObject, Object newObject) throws Exception {
        String mensage = "";

        for (Field field : oldObject.getClass().getDeclaredFields()) {
            Field newField = newObject.getClass().getDeclaredField(field.getName());
            field.setAccessible(true);
            newField.setAccessible(true);

            if (field.get(oldObject) != null) {

                if (!field.get(oldObject).equals(newField.get(newObject))) {
                    mensage += "Field{" + field.getName() + "} oldValue=[" + field.get(oldObject).toString() + "],newValue=[" + newField.get(newObject).toString() + "]";
                }
            }

        }
        return mensage;
    }

}
