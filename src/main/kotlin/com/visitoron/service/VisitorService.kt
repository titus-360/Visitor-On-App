package com.visitoron.service

import com.visitoron.dtos.VisitorDto
import com.visitoron.enums.VisitPurpose

/**
 *
 * @author Titus Murithi Bundi
 */
interface VisitorService {

    fun createVisitor(visitorDto: VisitorDto) : VisitorDto

    fun selectHostPerson(visitorId: Long, hostId: Long) : VisitorDto

    fun selectPurposeOfVisit(visitorId: Long, visitPurpose: VisitPurpose) : VisitorDto
}
