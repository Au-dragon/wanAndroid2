package com.example.oujunlong.wanandroid2.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * creation time 2019/5/9
 * author oujunlong
 */
@Entity
public class DaoBean {
    @Id
    private Long id;
    @Property
    private String author;
    @Property
    private String superChapterName;
    @Property
    private String title;
    @Property
    private String niceDate;
    @Generated(hash = 144529049)
    public DaoBean(Long id, String author, String superChapterName, String title,
            String niceDate) {
        this.id = id;
        this.author = author;
        this.superChapterName = superChapterName;
        this.title = title;
        this.niceDate = niceDate;
    }
    @Generated(hash = 405743142)
    public DaoBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getSuperChapterName() {
        return this.superChapterName;
    }
    public void setSuperChapterName(String superChapterName) {
        this.superChapterName = superChapterName;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNiceDate() {
        return this.niceDate;
    }
    public void setNiceDate(String niceDate) {
        this.niceDate = niceDate;
    }

    @Override
    public String toString() {
        return "DaoBean{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", superChapterName='" + superChapterName + '\'' +
                ", title='" + title + '\'' +
                ", niceDate='" + niceDate + '\'' +
                '}';
    }
}
