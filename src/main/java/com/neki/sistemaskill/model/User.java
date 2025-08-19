package com.neki.sistemaskill.model;


import jakarta.persistence.*;
import java.util.Set;
import java.time.LocalDateTime;

// Entidade que representa o usuário do sistema
@Entity
@Table(name = "usuario")

public class User {
    // Campos do perfil do usuário
    @Column(name = "nome_completo", length = 100)
    // Nome completo do usuário
    private String nomeCompleto;

    @Column(name = "data_nascimento")
    // Data de nascimento do usuário
    private java.time.LocalDate dataNascimento;

    @Column(name = "informacoes_relevantes", length = 1000)
    // Informações relevantes do usuário
    private String informacoesRelevantes;

    @Column(name = "contato", length = 50)
    // Contato do usuário
    private String contato;
    @Id
    // Identificador único do usuário
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "seq_user_id", allocationSize = 1)
    private Integer id;


    @Column(nullable = false, unique = true, length = 50)
    // Login do usuário
    private String login; // pode ser removido futuramente, mas manteremos para compatibilidade

    @Column(nullable = false, unique = true, length = 100)
    // E-mail do usuário
    // Apelido do usuário
    private String email;

    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(nullable = false, length = 255)
    // Senha do usuário
    private String password;



    @Column(name = "data_cadastro", nullable = false)
    // Data de cadastro do usuário
    private LocalDateTime dataCadastro = LocalDateTime.now();

    @Column(name = "ultimo_acesso")
    // Último acesso do usuário
    private LocalDateTime ultimoAcesso;




    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    // Skills associadas ao usuário
    private Set<UserSkill> userSkills;

    // Getters and setters
    public Integer getId() { return id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public java.time.LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(java.time.LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getInformacoesRelevantes() { return informacoesRelevantes; }
    public void setInformacoesRelevantes(String informacoesRelevantes) { this.informacoesRelevantes = informacoesRelevantes; }
    public String getContato() { return contato; }
    public void setContato(String contato) { this.contato = contato; }
    public void setId(Integer id) { this.id = id; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Set<UserSkill> getUserSkills() { return userSkills; }
    public void setUserSkills(Set<UserSkill> userSkills) { this.userSkills = userSkills; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public LocalDateTime getUltimoAcesso() { return ultimoAcesso; }
    public void setUltimoAcesso(LocalDateTime ultimoAcesso) { this.ultimoAcesso = ultimoAcesso; }
}
