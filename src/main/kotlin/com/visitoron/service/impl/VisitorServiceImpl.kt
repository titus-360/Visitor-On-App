package com.visitoron.service.impl

import com.visitoron.dtos.VisitorDto
import com.visitoron.enums.VisitPurpose
import com.visitoron.models.Visitor
import com.visitoron.repository.HostRepository
import com.visitoron.repository.VisitorRepository
import com.visitoron.service.VisitorService
import org.springframework.stereotype.Service

/**
 *
 * @author Titus Murithi Bundi
 */
@Service("visitor-on-service")
class VisitorServiceImpl(private val visitorRepository: VisitorRepository,  private val hostRepository: HostRepository) : VisitorService {


    override fun createVisitor(visitorDto: VisitorDto): VisitorDto {
        val visitor = Visitor(
            name = visitorDto.name,
            email = visitorDto.email,
            idNumber = visitorDto.idNumber,
            dateOfBirth = visitorDto.dateOfBirth,
            phone = visitorDto.phoneNumber
        )
        val savedVisitor = visitorRepository.save(visitor)
        return VisitorDto(
            id = savedVisitor.id,
            name = savedVisitor.name,
            email = savedVisitor.email,
            idNumber = savedVisitor.idNumber,
            dateOfBirth = savedVisitor.dateOfBirth,
            phoneNumber = savedVisitor.phone
        )
    }

    override fun selectHostPerson(visitorId: Long, hostId: Long): VisitorDto {
        val visitor = visitorRepository.findById(visitorId).orElseThrow { Exception("Visitor not found") }
        val host = hostRepository.findById(hostId).orElseThrow { Exception("Host not found") }
        visitor.host = host
        val updatedVisitor = visitorRepository.save(visitor)
        return VisitorDto(
            id = updatedVisitor.id,
            name = updatedVisitor.name,
            email = updatedVisitor.email,
            idNumber = updatedVisitor.idNumber,
            dateOfBirth = updatedVisitor.dateOfBirth,
            phoneNumber = updatedVisitor.phone
        )
    }

    override fun selectPurposeOfVisit(visitorId: Long, visitPurpose: VisitPurpose): VisitorDto {
        TODO("Not yet implemented")
    }


}
