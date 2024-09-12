package com.visitoron.service.impl

import com.visitoron.models.Host
import com.visitoron.repository.HostRepository
import com.visitoron.service.HostService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

/**
 *
 * @author Titus Murithi Bundi
 */
@Service("hostService")
class HostServiceImpl(private val hostRepository: HostRepository) : HostService {


    override fun createHost(host: Host): Host {
        val savedHost = hostRepository.save(host)
        return savedHost
    }

    override fun getAllHosts(pageable: Pageable): Page<Host> {
        return hostRepository.findAll(pageable)
    }

    override fun searchHosts(keyword: String, pageable: Pageable): Page<Host> {
        logger.info("Search hosts by keyword: {}", keyword)
        val searchedHost = hostRepository.searchHosts(keyword, pageable)
        return searchedHost
    }

    override fun getHostById(hostId: Long): Host {
        return hostRepository.findById(hostId).orElseThrow { Exception("Host not found") }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(HostServiceImpl::class.java)
    }
}
