# audita-entidades
Uma maneira simples de identificar quais atributos foram alterados entre as entidades. Contato que elas implementen a interface Clonable
a classe AuditoriaUtil faz todo o resto e você salva o resultado onde quiser.

Entidades
---------
As classes entidades devem implementar a Interface Cloneable não obrigartóriamente, mas isso irá facilitar bastante autilização

```
@Override
    public Object clone() throws CloneNotSupportedException {
        EntidadeTeste cloned = (EntidadeTeste) super.clone();
        cloned.setNome(cloned.getNome());
        cloned.setDataDeNascimento(cloned.getDataDeNascimento());
        return cloned;
    }
```
