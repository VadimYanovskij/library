package com.senla.training.library.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "category", schema = "library", catalog = "")
public class CategoryEntity {
    private Integer id;
    private Integer parentId;
    private String categoryName;
    private Collection<BookEntity> booksById;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "category_name", nullable = false, length = 45)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "categoryByCategoryId")
    public Collection<BookEntity> getBooksById() {
        return booksById;
    }

    public void setBooksById(Collection<BookEntity> booksById) {
        this.booksById = booksById;
    }
}
