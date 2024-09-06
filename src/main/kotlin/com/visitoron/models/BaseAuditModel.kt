package com.visitoron.models

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

/**
 *
 * @author Titus Murithi Bundi
 */
@MappedSuperclass
abstract class BaseAuditModel {

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    @Column(name = "last_modified_date", nullable = false, updatable = false)
    var lastModifiedDate: LocalDateTime? = null

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false)
    var createdBy: String? = null

    @LastModifiedBy
    @Column(name = "last_modified_by", nullable = false)
    var lastModifiedBy: String? = null
}
