package com.visitoron.repository

import com.visitoron.models.Host
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


/**
 *
 * @author Titus Murithi Bundi
 */
interface HostRepository : JpaRepository<Host, Long> {

    @Query("SELECT h FROM Host h WHERE CONCAT(h.name, ' ', h.email, ' ', h.phone, ' ', h.hostDepartment, ' ', h.hostRole, ' ', h.companyName) LIKE %?1%")
    fun searchHosts(keyword: String, pageable: Pageable): Page<Host>
}
