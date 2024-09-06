package com.visitoron.service

import com.visitoron.models.Host
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 *
 * @author Titus Murithi Bundi
 */
interface HostService {

    fun createHost(host: Host) : Host

    fun getAllHosts(pageable: Pageable): Page<Host>

    fun searchHosts(keyword: String, pageable: Pageable): Page<Host>

    fun getHostById(hostId: Long): Host
}
