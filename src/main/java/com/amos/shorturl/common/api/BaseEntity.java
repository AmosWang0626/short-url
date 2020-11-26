package com.amos.shorturl.common.api;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 模块名称: hibernate
 * 模块描述: 实体类基础表
 *
 * @author amos.wang
 * @date 2020/8/7 22:29
 */
@Getter
@Setter
@MappedSuperclass
@Accessors(chain = true)
@Where(clause = "DEL_FLAG=0")
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "base_uuid")
    @GenericGenerator(name = "base_uuid", strategy = "uuid")
    private String id;

    @CreatedDate
    private LocalDateTime createTime;

    @CreatedBy
    private String createUser;

    @Column(name = "DEL_FLAG", nullable = false)
    private Boolean deleteFlag;

    @PrePersist
    public void prePersist() {
        deleteFlag = false;
        createUser = "create";
        createTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
