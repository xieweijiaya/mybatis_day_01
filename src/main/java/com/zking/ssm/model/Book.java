package com.zking.ssm.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@JsonIgnoreProperties(value = {"bookId","img"})
public class Book {
    @NotNull(message = "书本编号不能为空", groups = {ValidateGroups.Edit.class})
    private Integer bookId;

    @NotBlank(message = "书本名称不能为空", groups = {ValidateGroups.Add.class, ValidateGroups.Edit.class})
    private String bookName;

    @NotNull(message = "书本价格不能为空", groups = {ValidateGroups.Add.class, ValidateGroups.Edit.class})
    @Range(min = 100, max = 1000, message = "价格只能在1-1000之间")
    private Float price;

    //临时属性
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createDate;

    // 书本验证分组
    public static interface ValidateGroups {
        // 新增
        public static interface Add {
        }

        // 修改
        public static interface Edit {
        }
    }

    private Long img;

    public Book(Integer bookId, String bookName, Float price, Long img) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.price = price;
        this.img = img;
    }

    public Book() {
        super();
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {

        this.bookName = bookName;
    }

    public Float getPrice() {

        return price;
    }

    public void setPrice(Float price) {

        this.price = price;
    }

    public Long getImg() {
        return img;
    }

    public void setImg(Long img) {

        this.img = img;
    }

    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}