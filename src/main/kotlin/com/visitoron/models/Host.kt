package com.visitoron.models

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener

/**
 *
 * @author Titus Murithi Bundi
 */
@Entity
@Table(name = "hosts")
@EntityListeners(AuditingEntityListener::class)
class Host(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "host_department")
    var hostDepartment: String? = null,

    @Column(name = "host_role")
    var hostRole: String? = null,

    @Column(name = "company_name")
    var companyName: String? = null,


) : BaseAuditModel()
