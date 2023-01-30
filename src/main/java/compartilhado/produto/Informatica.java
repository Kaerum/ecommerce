package compartilhado.produto;


public class Informatica extends Produto<Informatica.Categoria, Informatica.Subcategoria> {
    public enum Categoria{
        CELULAR,
        COMPUTADOR_DESKTOP,
        NOTEBOOK,
        TABLET,
        ACESSORIOS
    };
    public enum Subcategoria{
        GAMER
    }
}
