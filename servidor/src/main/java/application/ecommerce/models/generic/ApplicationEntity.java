package application.ecommerce.models.generic;

public interface ApplicationEntity<K> {
    public K getId();
    public void setId(K id);
}
