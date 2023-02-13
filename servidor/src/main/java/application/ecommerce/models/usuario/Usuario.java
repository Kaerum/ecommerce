package application.ecommerce.models.usuario;

import application.ecommerce.security.GrantedAuthorityImpl;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    private @Id @GeneratedValue Long id;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "usuario_autoridades", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "authorities")
    private List<GrantedAuthorityImpl> authorities;
    private @Column(unique = true) String nome;
    private String senha;
    private final @Builder.Default boolean expired = false;
    private final @Builder.Default boolean locked = false;
    private final @Builder.Default boolean enabled = true;
    private final @Builder.Default boolean credentialExpired = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.credentialExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
