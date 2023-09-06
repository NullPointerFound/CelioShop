package com.malik.CelioShop.CelioShop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

/*
    This class for Page entity, it has no relationships,
    only users with admin privileges can create/edit/delete new Pages such (About us, Privacy...etc),
    while all the other users including the ones who aren't authenticated can view Pages.
 */

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "page")
public class Page {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private Integer priority;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    @UpdateTimestamp
    private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return id.equals(page.id) && title.equals(page.title) && Objects.equals(content, page.content) && priority.equals(page.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, priority);
    }
}
