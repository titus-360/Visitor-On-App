package com.visitoron.dtos

import com.visitoron.enums.VisitPurpose

/**
 *
 * @author Titus Murithi Bundi
 */
data class VisitorDto(
    var id : Long? = null,

    var name : String? = null,

    var email : String? = null,

    var idNumber : String? = null,

    var dateOfBirth : String? = null,

    var phoneNumber : String? = null,

    var visitPurpose: VisitPurpose? = null
)
