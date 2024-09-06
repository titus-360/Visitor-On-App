package com.visitoron.service.impl

import com.visitoron.models.Host
import com.visitoron.repository.HostRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.util.*

/**
 *
 * @author Titus Murithi Bundi
 */
class HostServiceImplTest {

    private val hostRepository = mock(HostRepository::class.java)
    private val hostService = HostServiceImpl(hostRepository)

    @Test
    fun createHost_savesAndReturnsHost() {
        val host =
            Host(name = "John Doe", email = "john.doe@example.com", phone = "1234567890", companyName = "Example Inc.")
        `when`(hostRepository.save(host)).thenReturn(host)

        val result = hostService.createHost(host)

        assertEquals(host, result)
        verify(hostRepository, times(1)).save(host)
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
        `when`(hostRepository.findAll(pageable)).thenReturn(page)

        val result = hostService.getAllHosts(pageable)

        assertEquals(page, result)
        verify(hostRepository, times(1)).findAll(pageable)
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
        `when`(hostRepository.searchHosts(keyword, pageable)).thenReturn(page)

        val result = hostService.searchHosts(keyword, pageable)

        assertEquals(page, result)
        verify(hostRepository, times(1)).searchHosts(keyword, pageable)
    }

    @Test
    fun getHostById_returnsHost() {
        val hostId = 1L
        val host =
            Host(name = "John Doe", email = "john.doe@example.com", phone = "1234567890", companyName = "Example Inc.")
        `when`(hostRepository.findById(hostId)).thenReturn(Optional.of(host))

        val result = hostService.getHostById(hostId)

        assertEquals(host, result)
        verify(hostRepository, times(1)).findById(hostId)
    }

    @Test
    fun getHostById_throwsExceptionWhenHostNotFound() {
        val hostId = 1L
        `when`(hostRepository.findById(hostId)).thenReturn(Optional.empty())

        val exception = assertThrows(Exception::class.java) {
            hostService.getHostById(hostId)
        }

        assertEquals("Host not found", exception.message)
        verify(hostRepository, times(1)).findById(hostId)
    }
}
