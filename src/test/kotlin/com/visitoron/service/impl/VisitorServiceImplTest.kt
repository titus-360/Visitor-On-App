package com.visitoron.service.impl

import com.visitoron.dtos.VisitorDto
import com.visitoron.models.Host
import com.visitoron.models.Visitor
import com.visitoron.repository.HostRepository
import com.visitoron.repository.VisitorRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mockito.*
import java.util.*
import kotlin.test.Test

/**
 *
 * @author Titus Murithi Bundi
 */
class VisitorServiceImplTest {

    private val visitorRepository = mock(VisitorRepository::class.java)
    private val hostRepository = mock(HostRepository::class.java)
    private val visitorService = VisitorServiceImpl(visitorRepository, hostRepository)

    @Test
    fun createVisitor_savesAndReturnsVisitor() {
        val visitorDto = VisitorDto(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            idNumber = "ID123",
            dateOfBirth = "1990-01-01",
            phoneNumber = "0987654321"
        )
        val visitor = Visitor(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            idNumber = "ID123",
            dateOfBirth = "1990-01-01",
            phone = "0987654321"
        )
        `when`(visitorRepository.save(any(Visitor::class.java))).thenReturn(visitor)

        val result = visitorService.createVisitor(visitorDto)

        assertEquals(visitorDto.name, result.name)
        assertEquals(visitorDto.email, result.email)
        verify(visitorRepository, times(1)).save(any(Visitor::class.java))
    }

    @Test
    fun selectHostPerson_assignsHostToVisitor() {
        val visitorId = 1L
        val hostId = 2L
        val visitor = Visitor(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            idNumber = "ID123",
            dateOfBirth = "1990-01-01",
            phone = "0987654321"
        )
        val host =
            Host(name = "John Doe", email = "john.doe@example.com", phone = "1234567890", companyName = "Example Inc.")
        `when`(visitorRepository.findById(visitorId)).thenReturn(Optional.of(visitor))
        `when`(hostRepository.findById(hostId)).thenReturn(Optional.of(host))
        `when`(visitorRepository.save(visitor)).thenReturn(visitor)

        val result = visitorService.selectHostPerson(visitorId, hostId)

        assertEquals(host, visitor.host)
        assertEquals(visitor.name, result.name)
        verify(visitorRepository, times(1)).findById(visitorId)
        verify(hostRepository, times(1)).findById(hostId)
        verify(visitorRepository, times(1)).save(visitor)
    }

    @Test
    fun selectHostPerson_throwsExceptionWhenVisitorNotFound() {
        val visitorId = 1L
        val hostId = 2L
        `when`(visitorRepository.findById(visitorId)).thenReturn(Optional.empty())

        val exception = assertThrows(Exception::class.java) {
            visitorService.selectHostPerson(visitorId, hostId)
        }

        assertEquals("Visitor not found", exception.message)
        verify(visitorRepository, times(1)).findById(visitorId)
        verify(hostRepository, never()).findById(hostId)
    }

    @Test
    fun selectHostPerson_throwsExceptionWhenHostNotFound() {
        val visitorId = 1L
        val hostId = 2L
        val visitor = Visitor(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            idNumber = "ID123",
            dateOfBirth = "1990-01-01",
            phone = "0987654321"
        )
        `when`(visitorRepository.findById(visitorId)).thenReturn(Optional.of(visitor))
        `when`(hostRepository.findById(hostId)).thenReturn(Optional.empty())

        val exception = assertThrows(Exception::class.java) {
            visitorService.selectHostPerson(visitorId, hostId)
        }

        assertEquals("Host not found", exception.message)
        verify(visitorRepository, times(1)).findById(visitorId)
        verify(hostRepository, times(1)).findById(hostId)
    }
}
