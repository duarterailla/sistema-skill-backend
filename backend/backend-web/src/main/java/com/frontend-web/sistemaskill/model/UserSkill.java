package com.neki.sistemaskill.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_skills", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "skill_id"}))
public class UserSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_skill_seq")
    @SequenceGenerator(name = "user_skill_seq", sequenceName = "seq_user_skill_id", allocationSize = 1)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(nullable = false)
    private String level;

    @Column(length = 255)
    private String descricao;

    // Getters and setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
