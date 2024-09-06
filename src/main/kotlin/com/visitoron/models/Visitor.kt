package com.visitoron.models

import com.visitoron.enums.VisitPurpose
import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 *
 * \@author Titus Murithi Bundi
 */
@Entity
@Table(name = "visitors")
@EntityListeners(AuditingEntityListener::class)
class Visitor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0,

    @Column(name = "name")
    var name: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "phone")
    var phone: String? = null,

    @Column(name = "id_number")
    var idNumber: String? = null,

    @Column(name = "date_of_birth")
    var dateOfBirth: String? = null,

    @ManyToOne
    @JoinColumn(name = "host_id")
    var host: Host? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "purpose")
    var purpose: VisitPurpose? = null,

    @Column(name = "status")
    var status: String? = null,

    @Column(name = "check_in_time")
    var checkInTime: LocalDateTime? = null,

    @Column(name = "check_out_time")
    var checkOutTime: LocalDateTime? = null
) : BaseAuditModel()
