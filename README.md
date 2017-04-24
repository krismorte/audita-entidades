# audita-entidades
Uma maneira simples de identificar e auditar quais atributos foram alterados na sua entidade. A classe AuditoriaUtil identifica que atributos foram alterados e te informa em texto e você decide onde salvar essa informação.

Entidades
---------
As classes entidades devem implementar a Interface Cloneable não obrigartóriamente, mas isso irá facilitar bastante autilização

```Java
    @Override
    public Object clone() throws CloneNotSupportedException {
        EntidadeTeste cloned = (EntidadeTeste) super.clone();
        cloned.setNome(cloned.getNome());
        cloned.setDataDeNascimento(cloned.getDataDeNascimento());
        return cloned;
    }
```

Como Utilizar
-------------
O uso é bem simples antes de alterar sua entidade obtenha um clone dela para comparação após o MERGE de identificar quais atributos foram alterados. 

```Java
        AuditoriaUtil auditoriaUtil = new AuditoriaUtil();

        EntidadeTeste entidade1 = new EntidadeTeste();
        entidade1.setId(new Long(45));
        entidade1.setNome("Entidade Teste");
        entidade1.setDataDeNascimento(LocalDate.of(1990, 3, 15));

        EntidadeTeste entidade2 = null;
        try {
            entidade2 = (EntidadeTeste) entidade1.clone();
        } catch (CloneNotSupportedException ex) {
            ex.printStackTrace();
        }

        entidade2.setNome("Nova Entidade");
        System.out.println(auditoriaUtil.auditoriaCompleta(entidade1, entidade2));
```
