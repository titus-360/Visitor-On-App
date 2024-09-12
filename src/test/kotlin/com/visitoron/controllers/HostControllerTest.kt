package com.visitoron.controllers

import com.visitoron.models.Host
import com.visitoron.service.HostService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus


/**
 *
 * @author Titus Murithi Bundi
 */
class HostControllerTest {
    private val hostService = mock(HostService::class.java)
    private val hostController = HostController(hostService)

    @Test
    fun createNewHost_returnsCreatedHost() {
        val host =
            Host(name = "John Doe", email = "john.doe@example.com", phone = "1234567890", companyName = "Example Inc.")
        `when`(hostService.createHost(host)).thenReturn(host)

        val response = hostController.createNewHost(host)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(host, response.body)
        verify(hostService, times(1)).createHost(host)
    }

    @Test
    fun getAllHosts_returnsPaginatedHosts() {
        val pageable = PageRequest.of(0, 10)
        val hosts = listOf(
            Host(
                name = "John Doe",
                email = "john.doe@example.com",
                phone = "1234567890",
                companyName = "Example Inc."
            )
        )
        val page = PageImpl(hosts, pageable, hosts.size.toLong())
        `when`(hostService.getAllHosts(pageable)).thenReturn(page)

        val response = hostController.getAllHosts(pageable)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(page, response.body)
        verify(hostService, times(1)).getAllHosts(pageable)
    }

    @Test
    fun searchHosts_returnsPaginatedSearchedHosts() {
        val pageable = PageRequest.of(0, 10)
        val keyword = "John"
        val hosts = listOf(
            Host(
                name = "John Doe",
                email = "john.doe@example.com",
                phone = "1234567890",
                companyName = "Example Inc."
            )
        )
        val page = PageImpl(hosts, pageable, hosts.size.toLong())
        `when`(hostService.searchHosts(keyword, pageable)).thenReturn(page)

        val response = hostController.searchHosts(keyword, pageable)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(page, response.body)
        verify(hostService, times(1)).searchHosts(keyword, pageable)
    }

    @Test
    fun getHostById_returnsHost() {
        val hostId = 1L
        val host =
            Host(name = "John Doe", email = "john.doe@example.com", phone = "1234567890", companyName = "Example Inc.")
        `when`(hostService.getHostById(hostId)).thenReturn(host)

        val response = hostController.selectHost(hostId)

        assertEquals(HttpStatus.OK, response.statusCode)
        assertEquals(host, response.body)
        verify(hostService, times(1)).getHostById(hostId)
    }

    @Test
    fun getHostById_throwsExceptionWhenHostNotFound() {
        val hostId = 1L
        `when`(hostService.getHostById(hostId)).thenThrow(RuntimeException("Host not found"))

        val exception = assertThrows(RuntimeException::class.java) {
            hostController.selectHost(hostId)
        }

        assertEquals("Host not found", exception.message)
        verify(hostService, times(1)).getHostById(hostId)
    }


}
