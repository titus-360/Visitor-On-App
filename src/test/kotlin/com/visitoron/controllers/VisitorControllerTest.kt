package com.visitoron.controllers

import com.visitoron.dtos.VisitorDto
import com.visitoron.models.Host
import com.visitoron.service.VisitorService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus

/**
 *
 * @author Titus Murithi Bundi
 */
class VisitorControllerTest {
    private val visitorService = mock(VisitorService::class.java)
    private val visitorController = VisitorController(visitorService)

    @Test
    fun createNewVisitor_returnsCreatedVisitor() {
        val visitorDto = VisitorDto(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            idNumber = "ID123",
            dateOfBirth = "1990-01-01",
            phoneNumber = "0987654321"
        )
        `when`(visitorService.createVisitor(visitorDto)).thenReturn(visitorDto)

        val response = visitorController.createNewVisitor(visitorDto)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(visitorDto, response.body)
        verify(visitorService, times(1)).createVisitor(visitorDto)
    }

    @Test
    fun selectHost_assignsHostToVisitor() {
        val visitorId = 1L
        val hostId = 2L
        val hostDto = Host(
            name = "Jane Doe",
            email = "jane.doe@example.com",
            phone = "0987654321",
            companyName = "Example Inc."
        )
        `when`(visitorService.selectHostPerson(visitorId, hostId)).thenReturn(hostDto)

        val response = visitorController.selectHost(visitorId, hostId)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(hostDto, response.body)
        verify(visitorService, times(1)).selectHostPerson(visitorId, hostId)
    }

    @Test
    fun selectHost_throwsExceptionWhenVisitorNotFound() {
        val visitorId = 1L
        val hostId = 2L
        `when`(visitorService.selectHostPerson(visitorId, hostId)).thenThrow(RuntimeException("Visitor not found"))

        val exception = assertThrows(RuntimeException::class.java) {
            visitorController.selectHost(visitorId, hostId)
        }

        assertEquals("Visitor not found", exception.message)
        verify(visitorService, times(1)).selectHostPerson(visitorId, hostId)
    }

    @Test
    fun selectHost_throwsExceptionWhenHostNotFound() {
        val visitorId = 1L
        val hostId = 2L
        `when`(visitorService.selectHostPerson(visitorId, hostId)).thenThrow(RuntimeException("Host not found"))

        val exception = assertThrows(RuntimeException::class.java) {
            visitorController.selectHost(visitorId, hostId)
        }

        assertEquals("Host not found", exception.message)
        verify(visitorService, times(1)).selectHostPerson(visitorId, hostId)
    }

}
