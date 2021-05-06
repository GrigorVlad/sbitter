package me.grigor.sbitter.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity{

    @Column(
            name = "author_id",
            nullable = false
    )
    private Long authorId;

    @Column(
            name = "text",
            nullable = false
    )
    private String text;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Post{");
        sb.append("authorId=").append(authorId);
        sb.append(", text='").append(text).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
