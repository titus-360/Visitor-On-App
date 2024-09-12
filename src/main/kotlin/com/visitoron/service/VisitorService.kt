package com.visitoron.service

import com.visitoron.dtos.VisitorDto
import com.visitoron.enums.VisitPurpose
import com.visitoron.models.Host

/**
 *
 * @author Titus Murithi Bundi
 */
interface VisitorService {

    fun createVisitor(visitorDto: VisitorDto) : VisitorDto

    fun selectHostPerson(visitorId: Long, hostId: Long) : Host

    fun selectPurposeOfVisit(visitorId: Long, visitPurpose: VisitPurpose) : VisitorDto
}
